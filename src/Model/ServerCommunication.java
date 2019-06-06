package Model;

import Server.*;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import Client.*;
import Game.*;

public class ServerCommunication {

	public ArrayList<Server> servers;

	public void listen() {

	}

	/**
	 * 
	 * @param data
	 */
	public static void analyzeData(String data) {

	}

	/**
	 * 
	 * @param data
	 * @param players
	 */
	public void sendDataAll(String data, LinkedList<Player> players) {

	}

	/**
	 * 
	 * @param socket
	 * @param data
	 */
	public void sendData(Socket socket, String data) {

	}

	/**
	 * 
	 * @param grid
	 * @param color
	 */
	public String turnPacket(Square[][] grid, Color color) {
		return null;
	}

	/**
	 * 
	 * @param color
	 * @param pos
	 */
	public String newPlayerPacket(Color color, Square pos) {
		return null;
	}

	/**
	 * 
	 * @param color
	 */
	public String endPacket(Color color) {
		return null;
	}

	/**
	 * 
	 * @param color
	 * @param data
	 */
	public String errorPacket(Color color, String data) {
		return null;
	}

}