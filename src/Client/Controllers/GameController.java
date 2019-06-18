package Client.Controllers;

import javax.swing.JFrame;

import Client.*;
import Client.Views.Game;

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
