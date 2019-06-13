package Model;

import Server.*;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import Client.*;
import Game.*;

public class ServerCommunication {

	/**
	 * All the servers running
	 */
	public ArrayList<Server> servers;

	/**
	 * Listen for new servers
	 */
	public static void listen() {

	}

	/**
	 * Analyze the data received
	 * @param data the data received
	 */
	public static void analyzeData(String data) {

	}

	/**
	 * Send the data to all the players
	 * @param data the send data to send all the players
	 * @param players all the players
	 */
	public static void sendDataAll(String data, LinkedList<Player> players) {

	}

	/**
	 * Send data to the Socket
	 * @param socket the socket to send data
	 * @param data the data to send
	 */
	public static void sendData(Socket socket, String data) {

	}

	/**
	 * Get the data to send to start a new turn
	 * @param grid the grid of game
	 * @param color the color of the player playing
	 */
	public static String turnPacket(Square[][] grid, Color color) {
		return null;
	}

	/**
	 * Get the data to send to create a new player
	 * @param color the color of the player
	 * @param pos the position of the player
	 */
	public static String newPlayerPacket(Color color, Square pos) {
		return null;
	}

	/**
	 * Get the data to send to end the game
	 * @param color the color of the winner
	 */
	public static String endPacket(Color color) {
		return null;
	}

	/**
	 * Get the data to send to show an error to a player
	 * @param color the color of the player concerned
	 * @param data the text of the error
	 */
	public static String errorPacket(Color color, String data) {
		return null;
	}

}