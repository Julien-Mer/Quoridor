package Client.Controllers;

import java.util.LinkedList;

import javax.swing.JFrame;

import Client.*;
import Client.Views.Game;
import Game.Square;
import Server.Player;

public class GameController {
	
	/**
	 * The Game view
	 */
	private Game view;

	/**
	 * The constructor of GameController
	 * @param client the client used by the game
	 */
	public GameController() {
		this.view = new Game();
		this.view.setVisible(true);
		Client.client.view = this.view;
	}
	
	public void updateGame(Square[][] square) {
		//UPDATE ON BOARD AVEC MES SQUARES
		//Cette méthode est appelée dès qu'un joueur joue par exemple
	}
	
	public void updatePlayers(LinkedList<Player> players) {
		// Je t'envoie les joueurs de la partie
		// A chaque fois que quelqu'un joue ou au début de la partie aussi ou dès que quelqu'un rejoint
		// les éléments sont dans l'ordre de jeu, le get(0) est donc le joueur qui doit jouer
	}
	
	/**
	 * Saves the game
	 */
	public void saveGame() {

	}
	
	/**
	 *  Method which return the current frame of the game
	 * @return the current frame of the game
	 */
	public JFrame getView() {
		return this.view;
	}

}
