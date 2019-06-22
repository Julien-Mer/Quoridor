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
	
	private GamePanelController gamePanelController;
	/**
	 * The constructor of GameController
	 * @param client the client used by the game
	 */
	@SuppressWarnings("static-access")
	public GameController() {
		this.gamePanelController = new GamePanelController();
		this.view = new Game();
		this.view.setVisible(true);
		this.view.getSaveBtn().addActionListener(new Listener(this));
		this.view.getBarrierBtn().addActionListener(new Listener(this));
		Client.client.view = this.view;
	}
	
	public void updateGame(Square[][] square) {
		this.view.setGrid(square);
	}
	
	public void updatePlayers(LinkedList<Player> players) {
		this.view.updateP(players);
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
	public Game getView() {
		return this.view;
	}

}
