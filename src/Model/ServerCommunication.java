package Model;

import Server.*;
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.*;
import Client.*;
import Game.*;

public class ServerCommunication extends BasicCommunication {

	/**
	 * Port du serveur
	 */
	private final static int PORT = 1111;
	
	/**
	 * Listen for new clients and creates servers
	 */
	public static void listen() {
		 ServerSocket listener = null;
		  try {
			  System.out.println("Lancement du serveur !");
			  listener = new ServerSocket(PORT);
			  while(true) {
				  Socket clientSocket = listener.accept();
				  new Thread( new Runnable() {
				        public void run()  {
				        	new ServerListener(clientSocket);
				        }
				    } ).start();
			  }
		  } catch (Exception ex) {
		      System.out.println("Impossible de lancer le serveur: " + ex.toString());
		  }
	}

	

	/**
	 * Send the data to all the players
	 * @param data the send data to send all the players
	 * @param players all the players
	 */
	public static void sendDataAll(char key, Object object, LinkedList<Player> players) {
		for(Player p : players)
			try {
				if(p.getListener() != null && p.getListener().getSocket() != null) {
					BasicCommunication.sendData(key, object, p.getListener());
				}
			} catch (IOException e) {
				System.out.println("Le joueur: " + p.getName() + " est déconnecté");
			}
	}

	/**
	 * Get the data to send to show a message to a player
	 * @param data the text of the message
	 * @return the packet to show a message to a player
	 */
	public static String getMessagePacket(String data) {
		return data;
	}

}