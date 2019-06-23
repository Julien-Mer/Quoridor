package Model;

import java.io.*;
import java.io.IOException;
import java.net.*;

import Client.*;
import Game.Square;
import Server.Player;

public class ClientCommunication extends BasicCommunication {

	/**
	 * Initializes the connection between the client and the server
	 * @param ip the ip of the server
	 * @param port the port of the server
	 * @return the socket created
	 * @throws IOException if there is an error with the communication
	 * @throws UnknownHostException if there is an error with the communication
	 */
	public static Socket initialize(String ip, int port) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(ip), port);
	    return socket;
	}

	/**
	 * Get the data to send to place a barrier
	 * @param x1 the x1 position of the barrier
	 * @param y1 the y1 position of the barrier
	 * @param x2 the x2 position of the barrier
	 * @param y2 the y2 position of the barrier
	 * @return the placeBarrierPacket
	 */
	public static String getPlaceBarrierPacket(int x1, int y1, int x2, int y2) {
		return x1 + ";" + y1 + ";" + x2 + ";" + y2;
	}

	/**
	 * Get the data to send to move
	 * @param x the x position to move
	 * @param y the y position to move
	 * @return the movePacket
	 */
	public static String getMovePacket(int x, int y) {
		return x + ";" + y;
	}

	/**
	 * Get the data to send to create a game
	 * @param bots The number of AutoPlayers
	 * @param players The number of players
	 * @param name The name of the player
	 * @param difficulty The difficulty of the AutoPlayers
	 * @return the newGamePacket
	 */
	public static String getNewGamePacket(int bots, int players, String name, int difficulty) {
		return bots + ";" + players + ";" + name + ";" + difficulty;
	}

}