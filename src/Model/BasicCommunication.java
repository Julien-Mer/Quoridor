package Model;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.AbstractMap;
import java.util.Map.Entry;

import Client.HumanPlayer;

public class BasicCommunication {
	
	public static final char TURN_PREFIX = 'T';
	public static final char PLAYER_PREFIX = 'A';
	public static final char END_GAME_PREFIX = 'F';
	public static final char MESSAGE_PREFIX = 'I';
	public static final char NEW_GAME_PREFIX = 'N';
	public static final char BOARD_PREFIX = 'B';
	public static final char PLACE_BARRIER_PREFIX = 'P';
	public static final char MOVE_PREFIX = 'M';
	public static final char RULES_PREFIX = 'R';
	
	/**
	 * Serialize an object and send it to the client
	 * @param key the key to send
	 * @param object the object to serialize
	 * @param listener the listener sending the object
	 * @throws IOException If the object can't be send
	 */
	public static void sendData(char key, Object object, DataListener listener) throws IOException {
		try {
			if(listener != null) {
				ObjectOutputStream out = new ObjectOutputStream(listener.getSocket().getOutputStream());
				out.writeChar(key);
				System.out.println(">> " + key);
				if(object != null)
					out.writeObject(object);
				out.flush();
			}
		} catch(Exception ex) { ex.printStackTrace(); } // On fait une petite pause
	}
	
	/**
	 * Unserialize an object received
	 * @param listener the listener reading the object
	 * @return the entry containing the key and the object
	 */
	public static Entry<Character, Object> readData(DataListener listener) {
		Object res = null;
		char key = 0;
		try {
			ObjectInputStream in = new ObjectInputStream(listener.getSocket().getInputStream());
			key = in.readChar();
			res = in.readObject();
		} catch (Exception ex) { }
		return new AbstractMap.SimpleEntry<Character, Object>(key, res);
	}

}
