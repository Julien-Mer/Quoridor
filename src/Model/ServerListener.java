package Model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;

import Client.Client;
import Server.Player;
import Server.GameServer;

public class ServerListener extends Listener {

	/**
	 * All the servers running
	 */
	public static ArrayList<GameServer> servers = new ArrayList<GameServer>();
	
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
							int bots = Integer.valueOf(values[1]);
							int players = Integer.valueOf(values[2]);
							String name = values[3];
							GameServer server = null;
							for(GameServer s : ServerListener.servers) {
								if(s.getNbAutoJoueurs() == bots && s.getNbPlayers() == players && s.getPlaces() > 0) {
									server = s;
									break;
								}
							}
							if(server == null) {
								server = new GameServer(players, bots); // On créé un serveur avec les bots
								servers.add(server); // On ajoute le serveur créé à la liste
								server.initialize();
							}
							listener.setPlayer(server.newPlayer(name, listener)); // On ajoute ce joueur au serveur qu'on vient de créer
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
