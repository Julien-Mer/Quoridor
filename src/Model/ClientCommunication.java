package Model;

import Client.*;

public class ClientCommunication {

	/**
	 * 
	 * @param client
	 */
	public void listenData(Client client) {
		// TODO - implement ClientCommunication.listenData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 * @param out
	 */
	public void sendData(String data, BufferedWriter out) {
		// TODO - implement ClientCommunication.sendData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 * @param client
	 */
	public void analyzeData(String data, Client client) {
		// TODO - implement ClientCommunication.analyzeData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ip
	 * @param port
	 */
	public BufferedWriter initialize(String ip, String port) {
		// TODO - implement ClientCommunication.initialize
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param orientation
	 */
	public String placeBarrierPacket(int x, int y, int orientation) {
		// TODO - implement ClientCommunication.placeBarrierPacket
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public String movePacket(int x, int y) {
		// TODO - implement ClientCommunication.movePacket
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param players
	 */
	public String gamePacket(int players) {
		// TODO - implement ClientCommunication.gamePacket
		throw new UnsupportedOperationException();
	}

}