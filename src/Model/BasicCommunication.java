package Model;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class BasicCommunication {
	
	public static final String TURN_PREFIX = "T";
	public static final String NEW_PLAYER_PREFIX = "A";
	public static final String END_GAME_PREFIX = "F";
	public static final String ERROR_PREFIX = "E";
	public static final String NEW_GAME_PREFIX = "N";
	public static final String BOARD_PREFIX = "B";
	public static final String PLACE_BARRIER_PREFIX = "P";
	public static final String MOVE_PREFIX = "M";
	public static final String OBJECT_PREFIX = "O";
	
	/**
	 * Send data to the Socket
	 * @param socket the socket to send data
	 * @param data the data to send
	 * @throws IOException Exception si le BufferedWriter ne fonctionne pas
	 */
	public static void sendData(String data, Socket socket) throws IOException {
		BufferedWriter out = getBufferedWriter(socket);
		System.out.println(">> " + data);
		out.write(data);
		out.newLine(); // Permet de terminer la ligne et le buffer en face prendra en compte la ligne
	    out.flush();
	}
	
	/**
	 * Get the BufferedWriter of a socket
	 * @param socket the socket of the communication
	 * @return the BufferedWriter of the socket
	 * @throws IOException exception if the BufferedWriter is not available
	 */
	public static BufferedWriter getBufferedWriter(Socket socket) throws IOException {
		return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	/**
	 * Get the BufferedReader of a socket
	 * @param socket the socket of the communication
	 * @return the BufferedReader of the socket
	 * @throws IOException exception if the BufferedReader is not available
	 */
	public static BufferedReader getBufferedReader(Socket socket) throws IOException {
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	/**
	 * Serialize an object and send it to the client
	 * @param object the object to serialize
	 * @throws IOException If the object can't be send
	 */
	public static void sendObject(Object object, Socket socket) throws IOException {
		String ready = "";
		int id = (int) (Math.random() * 100);
		socket.setSoTimeout(100);
		while(!ready.equals(getObjectPacket())) {
			System.out.print("SendObjet-" + id + "-");
			try {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(object);
				out.flush();
				ready = getBufferedReader(socket).readLine();
				System.out.println("data " + id + ": " + ready);
			} catch(Exception ex) { }
		}
		socket.setSoTimeout(0); // On remet un timeout infini
	}
	
	/**
	 * Read a line on the Socket Output Stream
	 * @param socket the socket of the connection
	 * @return the String
	 */
	public static String readLine(Socket socket) throws Exception {
		return getBufferedReader(socket).readLine();
	}
	
	/**
	 * Unserialize an object received
	 * @param socket the socket of the connection
	 * @return the object
	 */
	public static Object readObject(Socket socket) {
		Object res = null;
		while(res == null) { // Tant qu'on a pas notre objet
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				res = in.readObject();  
				sendData(getObjectPacket(), socket); // On prévient le socket qu'on a reçu l'objet pour qu'il arrête
			} catch (Exception ex) { }
		}
		return res;
	}
	
	/**
	 * Get the data to send an object to the socket
	 * @return the packet to send an object
	 */
	public static String getObjectPacket() {
		return OBJECT_PREFIX + ";";
	}
	
}
