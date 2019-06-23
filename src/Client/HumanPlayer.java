package Client;

import java.awt.Color;
import java.io.*;
import java.util.Scanner;

import Game.*;
import Model.*;
import Model.File;
import Server.GameServer;
import Server.Player;

public class HumanPlayer extends Player implements Serializable {

	/**
	 * Creates a player
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param listener the listener of the player
	 * @param position the position of the player
	 * @param server the server of the player
	 */
	public HumanPlayer(String name, Color color, DataListener listener, Square position, GameServer server) {
		super(name, color, listener, position, server);
	}

	/**
	 * Ask the HumanPlayer to play (only if in console mode)
	 */
	public void play() {
		Scanner scan = new Scanner(System.in);
		showInfos();
		boolean played = false;
		System.out.println("C'est à votre tour de jouer\n\n"
				+ "Que voulez-vous faire ?\n"
				+ "- Poser une barrière: P x1,y1 x2,y2\n"
				+ "ex: P 1,0 1,2 pour poser une barriere entre les cases 0,0/2,0 et 2,0/2,2\n"
				+ "Attention la barrière doit être droite et doit avoir un écart de une case entre ses deux coordonnées\n"
				+ "- Vous déplacer: M x,y \n"
				+ "ex: M 2,0 pour aller sur la case de coordonnées 2,0\n"
				+ "Attention les coordonnées impaires sont réservées au barrières et inversement les coordonnées paires sont réservées aux joueurs.\n"
				+ "- Afficher les règles: rules");
		if(this.getServer().getNbAutoJoueurs() == this.getServer().getNbPlayers()-1)
			System.out.println("- Sauvegarder et quitter la partie: save");
		
		while(!played) {
			try {
				String ins = scan.nextLine();
				played = true;
				if(ins.toUpperCase().contains("M")) {
					String[] values = ins.split(" ");
					int x = Integer.valueOf(values[1].split(",")[0]);
					int y = Integer.valueOf(values[1].split(",")[1]);
					movePlayer(x, y);
				} else if(ins.toUpperCase().contains("P")) {
					String[] values = ins.split(" ");
					int x1 = Integer.valueOf(values[1].split(",")[0]);
					int y1 = Integer.valueOf(values[1].split(",")[1]);
					int x2 = Integer.valueOf(values[2].split(",")[0]);
					int y2 = Integer.valueOf(values[2].split(",")[1]);
					placeBarrier(x1, y1, x2, y2);
				} else if(ins.equalsIgnoreCase("rules")) {
					showRules();
					played = false;
				} else if(ins.equalsIgnoreCase("save") && this.getServer().getNbAutoJoueurs() == this.getServer().getNbPlayers()-1) {
					((Client)this.getListener()).saveGame(this.getServer());
				} else {
					System.out.println("Entrée invalide !");
					played = false;
				}
			} catch(Exception ex) {
				System.out.println("Commande invalide. Veuillez réessayer."); 
				played = false;
			}
		}
	}
	
	/**
	 * Asks the server to place a barrier
	 * @param x1 the x position of the first square
	 * @param y1 the y position of the fisrt square
	 * @param x2 the x position of the second square
	 * @param y2 the y position of the second square
	 * @throws IOException if the socket is unavailable
	 */
	public void placeBarrier(int x1, int y1, int x2, int y2) throws IOException {
		BasicCommunication.sendData(BasicCommunication.PLACE_BARRIER_PREFIX, ClientCommunication.getPlaceBarrierPacket(x1, y1, x2, y2), this.getListener());
	}
	
	/**
	 * Asks the server to send the rules
	 * @throws IOException if the socket is unavailable
	 */
	public void showRules() throws IOException {
		BasicCommunication.sendData(BasicCommunication.RULES_PREFIX, "", this.getListener());
	}

	/**
	 * Try to move the player
	 * @param x : the horizontal position
	 * @param y : the vertical position 
	 * @throws IOException Exception if the socket is unavailable
	 */
	public void movePlayer(int x, int y) throws IOException {
		BasicCommunication.sendData(BasicCommunication.MOVE_PREFIX, ClientCommunication.getMovePacket(x, y), this.getListener());
	}

	/**
	 * Show informations about the player
	 */
	public void showInfos() {
		System.out.println("Vous êtes le joueur: " + this.getName() + "\n"
				+ "Votre couleur est: " + getLetterColor(this.getColor()) + "\n"
				+ "Vous avez " + this.getNumberBarriersLeft() + " barrière(s)\n"
				+ "Vos mouvements possibles sont représenté par: " + getLetterColor(ColorSquare.POSSIBILITY) + "\n"
				+ "Vous êtes en [" + this.getPosition().getX() + "," + this.getPosition().getY() + "]");
	}
	
	/**
	 * Displays the grid 
	 * @param grid the grid to display
	 */
	 public static void showGrid(Square[][] grid) {
		 String res = "";
		 for(int x = 0; x <= grid[0].length+1; x++) 
			 res += "_";
		 res += "\n";
		 for(int y = 0; y < grid.length; y++) {
			 res += "|";
				for(int x = 0; x < grid[0].length; x++) {
					res += getLetterColor(grid[x][y].getColor());
				}
		 	res += "|\n";
	 	}
		 for(int x = 0; x <= grid[0].length+1; x++) 
			 res += "_";
		 res += "\n";
		System.out.println(res);
	 }
	 
	 /**
	  * Get the letter of a color
	  * @param color the color
	  * @return the letter of the color
	  */
	 public static String getLetterColor(Color color) {
		 String res = "";
		 if(color.equals(ColorSquare.BARRIER))
				res += "X";
			else if(color.equals(ColorSquare.YELLOW))
				res += "Y";
			else if(color.equals(ColorSquare.BLUE))
				res += "B";
			else if(color.equals(ColorSquare.GREEN))
				res += "G";
			else if(color.equals(ColorSquare.RED))
				res += "R";
			else if(color.equals(ColorSquare.POSSIBILITY))
				res += "P";
			else
				res += " ";
		return res;
	 }
	
}
