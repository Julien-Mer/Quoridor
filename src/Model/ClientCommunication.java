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
		return x + ";" + y + ";" + orientation;
	}

	/**
	 * Get the data to send to move
	 * @param x the x position to move
	 * @param y the y position to move
	 */
	public static String getMovePacket(int x, int y) {
		return x + ";" + y;
	}

	/**
	 * Get the data to send to create a game
	 * @param bots The number of AutoPlayers
	 * @param players The number of players
	 * @param name The name of the player
	 */
	public static String getNewGamePacket(int bots, int players, String name) {
		return bots + ";" + players + ";" + name;
	}

}