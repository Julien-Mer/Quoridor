package Model;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.AbstractMap;
import java.util.Map.Entry;

import Client.Client;
import Client.HumanPlayer;
import Server.ServerListener;

public class BasicCommunication {
	
	public static final char PLAYER_PREFIX = 'A';
	public static final char END_GAME_PREFIX = 'E';
	public static final char MESSAGE_PREFIX = 'I';
	public static final char MOVE_PREFIX = 'M';
	public static final char NEW_GAME_PREFIX = 'N';
	public static final char PLACE_BARRIER_PREFIX = 'P';
	public static final char RULES_PREFIX = 'R';
	public static final char SAVE_PREFIX = 'S';
	
	/**
	 * Serialize an object and send it to the client
	 * @param key the key to send
	 * @param object the object to serialize
	 * @param listener the listener sending the object
	 * @throws IOException If the object can't be send
	 */
	public static void sendData(char key, Object object, DataListener listener) throws IOException {
		try {
			if(listener != null && !listener.getSocket().isClosed()) {
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
		} catch (Exception ex) { 
			if(ex instanceof EOFException || ex instanceof SocketException) { // Si c'est une déconnexion
				try { listener.getSocket().close(); } catch (IOException e) { }
				if(listener instanceof Client)
					((Client) listener).showMessage("Vous avez été déconnecté du serveur", true);
				
				if(listener instanceof ServerListener)
					if(listener.getPlayer() != null) { // Si le client était en jeu
						ServerListener server = ((ServerListener) listener);
						if(server.getPlayer().getServer().isEmptyGame()) // Si la partie est maintenant vide
							ServerListener.servers.remove(server.getPlayer().getServer());
						else {
							server.getPlayer().getServer().refreshInfos(); // On refresh le jeu
							ServerCommunication.sendDataAll(MESSAGE_PREFIX, "Le joueur " + listener.getPlayer().getName() + " s'est déconnecté.", listener.getPlayer().getServer().getTimeLine());
						}
					}
			}
		}
		return new AbstractMap.SimpleEntry<Character, Object>(key, res);
	}

}
