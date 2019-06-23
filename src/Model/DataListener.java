package Model;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import Client.*;
import Server.Player;

public class DataListener {

	/**
	 * The socket listened by the instance
	 */
	private Socket socket;
	
	/**
	 * The data received
	 */
	private TreeMap<Character, Object> dataReceived;
	
	/**
	 * The thread of the listener
	 */
	private Thread thread;
	
	/**
	 * Get the thread used by the listener
	 * @return the thread
	 */
	public Thread getThread() {
		return this.thread;
	}
	
	/**
	 * set the thread used by the listener
	 * @param thread the thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	/**
	 * The player owning the socket
	 */
	private Player player;
	
	/**
	 * Listen for data sent by a socket
	 */
	public void listenSocket() {
		while(this.getSocket() != null && !this.getSocket().isClosed()) {
			Entry<Character, Object> entry = BasicCommunication.readData(this);
			this.addDataReceived(entry);
		}
	}
	
	/**
	 * Get the socket listened
	 * @return the socket used
	 */
	public Socket getSocket() {
		return this.socket;
	}
	
	/**
	 * Set the socket to listen
	 * @param socket the new socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Get the player linked to the socket
	 * @return the player linked
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Set the player linked to the socket
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Get the data received from the socket
	 * @return the data received
	 */
	public Entry<Character, Object> getFirstDataReceived() {
		Entry<Character, Object> res = null;
		try {
			if(this.dataReceived.size() > 0) {
				res = this.dataReceived.firstEntry();
				this.dataReceived.remove(res.getKey());
			}
		} catch(Exception ex) { }
		return res;
	}

	/**
	 * Set the new data received
	 * @param key the key
	 * @param object the object
	 */
	public void addDataReceived(char key, Object object) {
		this.dataReceived.put(key, object);
	}
	
	/**
	 * Set the new data received
	 * @param data the entry
	 * @param object the object
	 */
	public void addDataReceived(Entry<Character, Object> data) {
		this.addDataReceived(data.getKey(), data.getValue());
	}
	
	/**
	 * Create a listener for the socket
	 */
	public DataListener(Socket socket) {
		this.dataReceived = new TreeMap<Character, Object>();
		this.socket = socket;
	}
	
}
