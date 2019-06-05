package Model;

import Server.*;
import Client.*;
import Game.*;

public class ServerCommunication {

	public ArrayList<Server> servers;

	public void listen() {
		// TODO - implement ServerCommunication.listen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 */
	public void analyzeData(String data) {
		// TODO - implement ServerCommunication.analyzeData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 * @param players
	 */
	public void sendDataAll(String data, LinkedList<Player> players) {
		// TODO - implement ServerCommunication.sendDataAll
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param socket
	 * @param data
	 */
	public void sendData(Socket socket, String data) {
		// TODO - implement ServerCommunication.sendData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param grid
	 * @param color
	 */
	public String turnPacket(Square[][] grid, Color color) {
		// TODO - implement ServerCommunication.turnPacket
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param color
	 * @param pos
	 */
	public String newPlayerPacket(Color color, Square pos) {
		// TODO - implement ServerCommunication.newPlayerPacket
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param color
	 */
	public String endPacket(Color color) {
		// TODO - implement ServerCommunication.endPacket
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param color
	 * @param data
	 */
	public void errorPacket(Color color, String data) {
		// TODO - implement ServerCommunication.errorPacket
		throw new UnsupportedOperationException();
	}

}