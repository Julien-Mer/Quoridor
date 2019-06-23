package Client.Controllers.Helpers;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import Client.Client;
import Client.Views.Game;
import Client.Views.Models.Resources;
import Game.ColorSquare;
import Game.Square;
import Model.File;

public class Functions {

	/**
	 * Set the new theme of the game
	 * @param theme the new theme
	 */
	public static void setTheme(String theme) {
		Resources.theme = theme;
		Resources.refreshResources();
		File.writeFile("theme.conf", theme);
	}
	
	/**
	 * Apply the theme of the game
	 */
	public static void getTheme() {
		boolean res = false;
		String theme = File.readFile("theme.conf").trim();
		switch(theme) { 
			case "Moderne":
				res = true;
				break;
			case "Classique": 
				res = true;
				break;
			case "Iutiste":
				res = true;
				break;
		}
		if(!res) // Si un petit malin a essayé de modifier un thème, cela peut provoquer une faille de sécurité :)
			theme = "Classique";
		setTheme(theme);
	}
	
	/**
	 * Replace the current view
	 * @param actualFrame the view to replace
	 * @param newFrame the new view
	 */
	public static void changeView(JFrame actualFrame, JFrame newFrame) {
		//int x = actualFrame.getX();
		//int y = actualFrame.getY();
		actualFrame.dispose();
		//newFrame.setLocation(x, y);
	}
	
	/**
	 * Get the path to display an image on the grid
	 * @param col the color of the square
	 * @return the path of the image
	 */
	public static String getPathSquare(Color col) {
		String res=null;
		if(col.equals(ColorSquare.YELLOW)) res = Resources.YELLOW_PAWN_SQUARE_IMAGE;
	    else if(col.equals(ColorSquare.RED)) res = Resources.RED_PAWN_SQUARE_IMAGE;
	    else if(col.equals(ColorSquare.GREEN)) res = Resources.GREEN_PAWN_SQUARE_IMAGE;
	    else if(col.equals(ColorSquare.BLUE))  res = Resources.BLUE_PAWN_SQUARE_IMAGE;
	    else if(col.equals(ColorSquare.POSSIBILITY))  res = Resources.POSSIBILITY_SQUARE_IMAGE;
	    else  res = Resources.SQUARE_IMAGE;
	    return res;
	}
	
	/**
	 * Get the path to display an image of the pawn
	 * @param col the color of the square
	 * @return the path of the image
	 */
	public static String getPathPlayer(Color col) {
		String res=null;
		if(col.equals(ColorSquare.YELLOW)) res = Resources.YELLOW_PAWN_IMAGE;
	    else if(col.equals(ColorSquare.RED)) res = Resources.RED_PAWN_IMAGE;
	    else if(col.equals(ColorSquare.GREEN)) res = Resources.GREEN_PAWN_IMAGE;
	    else if(col.equals(ColorSquare.BLUE))  res = Resources.BLUE_PAWN_IMAGE;
	    return res;
	}
	
	/**
	 * Get the number of the player
	 * @param col the color of the player
	 * @return the number of the player
	 */
	public static int getNumberPlayer(Color col) {
		int res=0;
		if(col.equals(ColorSquare.BLUE)) res = 0;
	    else if(col.equals(ColorSquare.GREEN)) res = 1;
	    else if(col.equals(ColorSquare.RED)) res = 2;
	    else if(col.equals(ColorSquare.YELLOW)) res = 3;
	    return res;
	}
	
	
	/**
	 * Return a boolean if the square is valid or not
	 * @param x the x position of the square
	 * @param y the y position of the square
	 * @param length the length of the board
	 * @return a boolean if the square is valid or not
	 */
	public static boolean validSquare(int x, int y, int length) {
		return x >= 0 && x < length && y >= 0 && y < length;
	}
	   
	/**
	 * Get the border to apply to a square, depending of the barriers around
	 * @param grid the grid of the board
	 * @param x the x position of the square
	 * @param y the y position of the square
	 * @return the MatteBorder of the square
	 */
	public static MatteBorder getBorder(Square[][] grid, int x, int y) {
		int right = 0, left = 0, top = 0, bottom = 0;
		if(validSquare(x+1, y, grid.length))
			if(grid[x+1][y].getColor().equals(ColorSquare.BARRIER))
				right = 4;
		if(validSquare(x-1, y, grid.length))
			if(grid[x-1][y].getColor().equals(ColorSquare.BARRIER))
				left = 4; 
		if(validSquare(x, y-1, grid.length))
			if(grid[x][y-1].getColor().equals(ColorSquare.BARRIER))
				top = 4; 
		if(validSquare(x, y+1, grid.length))
			if(grid[x][y+1].getColor().equals(ColorSquare.BARRIER)) 
				bottom = 4;
		return BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK);
	}
	
	/**
	 * Open a dialog message to ask values to place a barrier
	 * @param game the game used
	 * @return an array of int, representating the square around the barrier
	 */
	public static int[] choiceBarriers(Game game) {
		JFrame frame = new JFrame();
		int[] res = new int[4];
		String x = JOptionPane.showInputDialog("Position x du début de la barrière (de 0 à " + (game.getGamePanel().getGridLabels().length-1) + ")");
		String y = JOptionPane.showInputDialog("Position y du début de la barrière (de 0 à " + (game.getGamePanel().getGridLabels()[0].length-1) + ")");
		String ori = JOptionPane.showInputDialog("Entrez une orientation (0: gauche, 1: haut: 2: droite, 3: bas)");
		try {
			int x1 = Integer.parseInt(x)*2;
			int y1 = Integer.parseInt(y)*2;
			int x2 = 0, y2 = 0;
			int oriCode = Integer.parseInt(ori);
			if(oriCode == 0) {
				x1--;
				x2 = x1;
				y2 = y1 + 2;
			} else if(oriCode == 1) {
				x2 = x1 + 2;
				y1--;
				y2 = y1;
			} else if(oriCode == 2) {
				x1++;
				x2 = x1;
				y2 = y1 + 2;
			} else if(oriCode == 3) {
				x2 = x1 + 2;
				y1++;
				y2 = y1;
			}
			res[0] = x1;
			res[1] = y1;
			res[2] = x2;
			res[3] = y2;
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Entrez un nombre valide", "Quoridor", JOptionPane.PLAIN_MESSAGE);
		}
		return res;
	}
	   
}