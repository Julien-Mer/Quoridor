package Model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;

import Client.Client;
import Server.Player;
import Server.GameServer;

public class ServerListener extends DataListener {

	/**
	 * All the servers running
	 */
	public static ArrayList<GameServer> servers = new ArrayList<GameServer>();
	
	/**
	 * The server of the player listened
	 */
	private GameServer server;
	
	/**
	 * Create a listener for the socket
	 */
	public ServerListener(Socket socket) {
		super(socket);
		ServerListener listener = this;
		new Thread( new Runnable() {
	        public void run()  {
	        	analyzeData(listener);
	        }
	    } ).start();
		this.listenSocket();
	}
	
	/**
	 * Get the server of the listener
	 * @return the server of the listener
	 */
	public GameServer getServer() {
		return this.server;
	}
	
	/**
	 * Set the server of the player
	 * @param server the server of the player
	 */
	public void setServer(GameServer server) {
		this.server = server;
	}
	
	/**
	 * Analyze the data received
	 * @param listener the server listener
	 */
	public static void analyzeData(ServerListener listener) {
		try {
			while(listener.getSocket() != null) { // Tant que le joueur sera connecté
				Entry<Character, Object> entry = listener.getFirstDataReceived();
				if(entry != null) {
					switch(entry.getKey()) { // On s'intérresse au préfixe
						case BasicCommunication.NEW_GAME_PREFIX:
							String[] values = ((String) entry.getValue()).split(";");
							int bots = Integer.valueOf(values[0]);
							int players = Integer.valueOf(values[1]);
							String name = values[2];
							GameServer server = null;
							for(GameServer s : ServerListener.servers) {
								if(s.getNbAutoJoueurs() == bots && s.getNbPlayers() == players && s.getPlaces() > 0) { // Si un serveur existant correspond
									server = s;
									break;
								}
							}
							if(server == null) {
								server = new GameServer(players, bots); // On créé un serveur avec les bots
								servers.add(server); // On ajoute le serveur créé à la liste
								server.initialize();
							}
							listener.setServer(server);
							listener.setPlayer(server.newPlayer(name, ((DataListener)listener))); // On ajoute ce joueur au serveur qu'on vient de créer
							break;
						case BasicCommunication.MOVE_PREFIX:
							
							values = ((String) entry.getValue()).split(";");
							int x = Integer.valueOf(values[0]);
							int y = Integer.valueOf(values[1]);
							if(listener.getPlayer() == listener.getServer().getTurnPlayer() && listener.getServer().getPlaces() == 0) {
								System.out.println("ui");
								if(listener.getServer().getBoard().canMove(listener.getPlayer().getPosition(), listener.getServer().getBoard().getGrid()[x][y]))
									System.out.println("Le mouvement est valide");
							} else {
								if(listener.getServer().getBoard().canMove(listener.getPlayer().getPosition(), listener.getServer().getBoard().getGrid()[x][y]))
									System.out.println("Le mouvement est valide");
								else
									System.out.println("Mouvement invalide");
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Ce n'est pas à votre tour de jouer"), listener); // On lui envoie un message
							}
							break;
					}
				} else Thread.sleep(500); // On fait une petite pause pour ne pas boucler pour rien (Ecologique)
			}
		} catch(Exception ex) {
			System.out.println("Impossible d'analyser les données reçues: " + ex.toString());
			ex.printStackTrace();
		}
	}
	
}
