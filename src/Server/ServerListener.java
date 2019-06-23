package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map.Entry;

import Client.Client;
import Client.HumanPlayer;
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
	 * @param socket the socket of the server
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
		while(listener.getSocket() != null && !listener.getSocket().isClosed()) { // Tant que le joueur sera connecté
			try {
				Entry<Character, Object> entry = listener.getFirstDataReceived();
				if(entry != null) {
					switch(entry.getKey()) { // On s'intérresse au préfixe
						case BasicCommunication.NEW_GAME_PREFIX:
							String[] values = ((String) entry.getValue()).split(";");
							int bots = Integer.valueOf(values[0]);
							int players = Integer.valueOf(values[1]);
							String name = values[2];
							int difficulty = Integer.valueOf(values[3]);
							GameServer server = null;
							for(GameServer s : ServerListener.servers) {
								if(s.getNbAutoJoueurs() == bots && s.getNbPlayers() == players && s.getDifficulty() == difficulty && !s.getPedagoMode() && s.getPlaces() > 0) { // Si un serveur existant correspond
									server = s;
									break;
								}
							}
							if(server == null) {
								server = new GameServer(players, bots, difficulty); // On créé un serveur avec les bots
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
								if(listener.getPlayer().getNumberBarriersLeft() != 0) {
									if(listener.getPlayer().getServer().getBoard().checkPos(x1, y1) && listener.getPlayer().getServer().getBoard().checkPos(x2, y2) && listener.getPlayer().getServer().getBoard().checkBarriers(listener.getPlayer().getServer().getBoard().getGrid()[x1][y1], listener.getPlayer().getServer().getBoard().getGrid()[x2][y2], listener.getPlayer().getServer().getTimeLine()))
										listener.getPlayer().addBarrier(listener.getPlayer().getServer().getBoard().getGrid()[x1][y1], listener.getPlayer().getServer().getBoard().getGrid()[x2][y2]);
									else { // On lui dit que c'est son tour avec une erreur
										listener.getPlayer().getServer().refreshInfos();
										ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Impossible de placer cette barrière"), listener); // On lui envoie un message
									}
								} else {
									ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Vous n'avez plus de barrièreM 0,1"), listener); // On lui envoie un message
								}
							} else {
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Ce n'est pas à votre tour de jouer"), listener); // On lui envoie un message
							}
							break;
						case BasicCommunication.RULES_PREFIX:
							if(listener.getPlayer() != null && listener.getPlayer().getServer() != null) 
								ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, listener.getPlayer().getServer().rules(), listener);
							break;
						case BasicCommunication.SAVE_PREFIX:
							server = (GameServer) entry.getValue(); // On récupère le serveur
							servers.add(server); // On ajoute le serveur récupèré à la liste
							for(Player p : server.getTimeLine())
								if(p instanceof HumanPlayer) {
									listener.setPlayer(p); // On ajoute le joueur au listener et inversement
									p.setListener(listener);
								}
							server.refreshInfos();
							server.start();
							break;
					}
				} else Thread.sleep(500); // On fait une petite pause pour ne pas boucler pour rien (Ecologique)
			} catch(Exception ex) {
				try {
					ServerCommunication.sendData(BasicCommunication.MESSAGE_PREFIX, ServerCommunication.getMessagePacket("Données reçues corrompues"), listener);
					ex.printStackTrace();
				} catch (IOException e) { } // On lui envoie un message
			}
		}
	}
	
}
