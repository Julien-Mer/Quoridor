package Model;

import java.io.BufferedWriter;

import Client.*;

public class ClientCommunication {

	/**
	 * 
	 * @param client
	 */
	public static void listenData(Client client) {

	}

	/**
	 * 
	 * @param data
	 * @param out
	 */
	public static void sendData(String data, BufferedWriter out) {

	}

	/**
	 * 
	 * @param data
	 * @param client
	 */
	public static void analyzeData(String data, Client client) {

	}

	/**
	 * 
	 * @param ip
	 * @param port
	 */
	public static BufferedWriter initialize(String ip, String port) {
		return null;

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param orientation
	 */
	public static String placeBarrierPacket(int x, int y, int orientation) {
		return null;

	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public static String movePacket(int x, int y) {
		return null;

	}

	/**
	 * 
	 * @param players
	 */
	public static String gamePacket(int players) {
		return null;

	}

}