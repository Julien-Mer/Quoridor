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
	 * Listen for new clients and creates servers
	 */
	public static void listen() {
		 ServerSocket listener = null;
		  try {
			  System.out.println("Lancement du serveur !");
			  listener = new ServerSocket(1111);
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
	 * Get the data to send to show an error to a player
	 * @param data the text of the error
	 * @return the packet to show an error to a player
	 */
	public static String getErrorPacket(String data) {
		return ERROR_PREFIX + ";" + data;
	}

}