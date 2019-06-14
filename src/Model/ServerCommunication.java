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
	public static void sendDataAll(String data, LinkedList<Player> players) {
		for(Player p : players)
			try {
				if(p.getSocket() != null) {
					System.out.println("Data -> " + p.getColor().toString());
					BasicCommunication.sendData(data, p.getSocket());
				}
			} catch (IOException e) {
				System.out.println("Le joueur: " + p.getName() + " est déconnecté");
			}
	}
	
	/**
	 * Send the serialized data to all the players
	 * @param data the send data to send all the players
	 * @param players all the players
	 */
	public static void sendObjectAll(Object object, LinkedList<Player> players) {
		LinkedList<Player> playersList = (LinkedList<Player>) players.clone(); // Evite une erreur de modification concourrante 
		for(Player p : playersList)
			try {
				if(p.getSocket() != null) {
					System.out.println("Object -> " + p.getColor().toString());
					BasicCommunication.sendObject(object, p.getSocket());
				}
			} catch (IOException e) {
				System.out.println("Le joueur: " + p.getName() + " est déconnecté");
			}
	}

	/**
	 * Get the data to send to create a new player
	 * @param pos the position of the player
	 * @return the packet to add a player to the game
	 */
	public static String getNewPlayerPacket() {
		return NEW_PLAYER_PREFIX + ";";
	}

	/**
	 * Get the data to send to end the game
	 * @return the packet to end the game
	 */
	public static String getEndPacket() {
		return END_GAME_PREFIX + ";";
	}

	/**
	 * Get the data to send to show an error to a player
	 * @param data the text of the error
	 * @return the packet to show an error to a player
	 */
	public static String getErrorPacket(String data) {
		return ERROR_PREFIX + ";" + data;
	}
	
	/**
	 * Get the data to send to start a new turn
	 * @return the packet of turn of the game
	 */
	public static String getTurnPacket() {
		return TURN_PREFIX + ";";
	}
	
	/**
	 * Get the data to send to update the board
	 * @return the packet to notify an update of the board
	 */
	public static String getBoardPacket() {
		return BOARD_PREFIX + ";";
	}

}