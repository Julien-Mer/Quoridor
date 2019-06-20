package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;

import Client.Client;
import Model.BasicCommunication;
import Model.DataListener;
import Model.ServerCommunication;

public class ServerListener extends DataListener {

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
		this.setThread(new Thread( new Runnable() {
	        public void run()  {
	        	analyzeData(listener);
	        }
	    }));
		this.getThread().start();
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
							listener.setPlayer(server.newPlayer(name, ((DataListener)listener), server)); // On ajoute ce joueur au serveur qu'on vient de créer
							break;
						case BasicCommunication.MOVE_PREFIX:
							values = ((String) entry.getValue()).split(";");
							int x = Integer.valueOf(values[0]);
							int y = Integer.valueOf(values[1]);
							if(listener.getPlayer() == listener.getPlayer().getServer().getTurnPlayer() && listener.getPlayer().getServer().getPlaces() == 0) {
								if(listener.getPlayer().getServer().getBoard().checkPos(x, y) && listener.getPlayer().getServer().getBoard().canMove(listener.getPlayer().getPosition(), listener.getPlayer().getServer().getBoard().getGrid()[x][y]))
									listener.getPlayer().movePlayer((listener.getPlayer().getServer().getBoard().getGrid()[x][y]));
								else { // On lui dit que c'est son tour avec une erreur
									listener.getPlayer().getServer().refreshInfos();
									ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Impossible de vous déplacer à cet endroit"), listener); // On lui envoie un message
								}
							} else {
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Ce n'est pas à votre tour de jouer"), listener); // On lui envoie un message
							}
							break;
						case BasicCommunication.PLACE_BARRIER_PREFIX:
							values = ((String) entry.getValue()).split(";");
							int x1 = Integer.valueOf(values[0]);
							int y1 = Integer.valueOf(values[1]);
							int x2 = Integer.valueOf(values[2]);
							int y2 = Integer.valueOf(values[3]);
							if(listener.getPlayer() == listener.getPlayer().getServer().getTurnPlayer() && listener.getPlayer().getServer().getPlaces() == 0) {
								if(listener.getPlayer().getServer().getBoard().checkPos(x1, y1) && listener.getPlayer().getServer().getBoard().checkPos(x2, y2) && listener.getPlayer().getServer().getBoard().checkBarriers(listener.getPlayer().getServer().getBoard().getGrid()[x1][y1], listener.getPlayer().getServer().getBoard().getGrid()[x2][y2], listener.getPlayer().getServer().getTimeLine()))
									listener.getPlayer().addBarrier(listener.getPlayer().getServer().getBoard().getGrid()[x1][y1], listener.getPlayer().getServer().getBoard().getGrid()[x2][y2]);
								else { // On lui dit que c'est son tour avec une erreur
									listener.getPlayer().getServer().refreshInfos();
									ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Impossible de placer cette barrière"), listener); // On lui envoie un message
								}
							} else {
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Ce n'est pas à votre tour de jouer"), listener); // On lui envoie un message
							}
							break;
						case BasicCommunication.RULES_PREFIX:
							if(listener.getPlayer() != null && listener.getPlayer().getServer() != null) 
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, listener.getPlayer().getServer().rules(), listener);
					}
				} else Thread.sleep(500); // On fait une petite pause pour ne pas boucler pour rien (Ecologique)
			}
		} catch(Exception ex) {
			System.out.println("Impossible d'analyser les données reçues: " + ex.toString());
			ex.printStackTrace();
		}
	}
	
}
