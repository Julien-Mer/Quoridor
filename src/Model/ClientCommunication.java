package Model;

import java.io.BufferedWriter;

import Client.*;

public class ClientCommunication {

	/**
	 * Listen the data sent by the server
	 * @param client the client to send the data
	 */
	public static void listenData(Client client) {

	}

	/**
	 * Send data to the server
	 * @param data Data to send to the server
	 * @param out BufferedWriter to send the data
	 */
	public static void sendData(String data, BufferedWriter out) {

	}

	/**
	 * Analyzes the data and send a command to the client
	 * @param data the date to analyze
	 * @param client the client to send command
	 */
	public static void analyzeData(String data, Client client) {

	}

	/**
	 * Initializes the connection between the client and the server
	 * @param ip the ip of the server
	 * @param port the port of the server
	 */
	public static BufferedWriter initialize(String ip, String port) {
		return null;

	}

	/**
	 * Get the data to send to place a barrier
	 * @param x the x position of the barrier
	 * @param y the y position of the barrier
	 * @param orientation the orientation of the barrier
	 */
	public static String placeBarrierPacket(int x, int y, int orientation) {
		return null;

	}

	/**
	 * Get the data to send to move
	 * @param x the x position to move
	 * @param y the y position to move
	 */
	public static String movePacket(int x, int y) {
		return null;

	}

	/**
	 * Get the data to send to create a game
	 * @param players The number of players
	 */
	public static String gamePacket(int players) {
		return null;

	}

}