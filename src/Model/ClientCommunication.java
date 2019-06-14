package Model;

import java.io.*;
import java.io.IOException;
import java.net.*;

import Client.*;
import Game.Square;
import Server.Player;

public class ClientCommunication extends BasicCommunication {

	/**
	 * Listen the data sent by the server
	 * @param client the client to send the data
	 */
	public static void listenData(Client client) {
		String received = null;
		while (client.getSocket() != null) {
			try {
				received = readLine(client.getSocket());
				if(received != null) // Si l'entrée est valide
					analyzeData(received, client);
			} catch(Exception ex) { }
		}
	}

	/**
	 * Analyzes the data and send a command to the client
	 * @param data the date to analyze
	 * @param client the client to send command
	 */
	public static void analyzeData(String data, Client client) {
		String[] values = data.split(";");
		Player player = null;
		try {
			switch(values[0]) { // On s'intéresse au préfixe
				case BasicCommunication.NEW_PLAYER_PREFIX: // On va créér un nouveau joueur
					if(client.getPlayer() == null) // On vérifie que le client n'a pas déjà un joueur
						player = (Player) BasicCommunication.readObject(client.getSocket());
						client.createPlayer(player.getName(), player.getColor(), player.getPosition());
					break;
				case BasicCommunication.BOARD_PREFIX:
					client.getPlayer().updateBoard((Square[][]) BasicCommunication.readObject(client.getSocket()));
					break;
				case BasicCommunication.TURN_PREFIX:
					player = (Player) BasicCommunication.readObject(client.getSocket());
					System.out.println("C'est au joueur: " + player.getName() + " de jouer");
					break;
			}
		} catch(Exception ex) {
			System.out.println("Une erreur est survenue lors de l'analyse: " + ex.toString());
		}
	}

	/**
	 * Initializes the connection between the client and the server
	 * @param ip the ip of the server
	 * @param port the port of the server
	 * @return the socket created
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static Socket initialize(String ip, int port) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(ip), port);
	    return socket;
	}

	/**
	 * Get the data to send to place a barrier
	 * @param x the x position of the barrier
	 * @param y the y position of the barrier
	 * @param orientation the orientation of the barrier
	 */
	public static String getPlaceBarrierPacket(int x, int y, int orientation) {
		return MOVE_PREFIX + ";" + x + ":" + y + ":" + orientation;
	}

	/**
	 * Get the data to send to move
	 * @param x the x position to move
	 * @param y the y position to move
	 */
	public static String getMovePacket(int x, int y) {
		return MOVE_PREFIX + ";" + x + ":" + y;
	}

	/**
	 * Get the data to send to create a game
	 * @param bots The number of AutoPlayers
	 * @param players The number of players
	 * @param name The name of the player
	 */
	public static String getNewGamePacket(int bots, int players, String name) {
		return NEW_GAME_PREFIX + ";" + bots + ";" + players + ";" + name;
	}

}