package Model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Client.Client;
import Server.Player;
import Server.Server;

public class ServerListener {

	/**
	 * All the servers running
	 */
	public static ArrayList<Server> servers = new ArrayList<Server>();
	
	/**
	 * The socket listened by the instance
	 */
	private Socket socket;
	
	/**
	 * The player owning the socket
	 */
	private Player player;
	
	/**
	 * Create a listener for the socket
	 */
	public ServerListener(Socket socket) {
		this.socket = socket;
		this.listenSocket();
	}
	
	/**
	 * Listen for data sent by a socket
	 */
	public void listenSocket() {
		try {
			while(this.socket.getInputStream() != null) {
				try {
				String received = BasicCommunication.readLine(this.socket);
				if(received != null) // Si l'entrée est valide
					System.out.println("Received: " + received);
					this.analyzeData(received);
				} catch(Exception ex) { }
			}
		} catch (IOException e) {
			System.out.println("Le joueur a été déconnecté");
		}
	}
	
	/**
	 * Analyze the data received
	 * @param data the data received
	 */
	public void analyzeData(String data) {
		try {
		String[] values = data.split(";");
		switch(values[0]) { // On s'intérresse au préfixe
			case BasicCommunication.NEW_GAME_PREFIX:
				int bots = Integer.valueOf(values[1]);
				int players = Integer.valueOf(values[2]);
				String name = values[3];
				Server server = null;
				for(Server s : this.servers) {
					if(s.getNbAutoJoueurs() == bots && s.getNbPlayers() == players && s.getPlaces() > 0) {
						server = s;
						break;
					}
				}
				if(server == null) {
					server = new Server(players, bots); // On créé un serveur avec les bots
					servers.add(server); // On ajoute le serveur créé à la liste
					server.initialize();
				}
				this.player = server.newPlayer(name, this.socket); // On ajoute ce joueur au serveur qu'on vient de créer
				break;
		}
		} catch(Exception ex) {
			System.out.println("Impossible d'analyser les données reçues: " + ex.toString());
		}
	}
	
}
