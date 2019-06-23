package Client.Controllers;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;

import Client.*;
import Client.Controllers.Helpers.Functions;
import Client.Views.Game;
import Client.Views.NewGame;
import Client.Views.Components.GamePanel;
import Client.Views.Components.PanelInfoPlayer;
import Client.Views.Models.BasicController;
import Game.Square;
import Server.Player;

public class GameController extends BasicController {
	
	
	/**
	 * Constructor of GameController
	 * @param save if the game allows save or not
	 */
	public GameController(boolean save) {
		Game game = new Game(save);
		super.initView(new Dimension(950,850), game);
		if(save)
			game.getSaveBtn().addActionListener(new Listener(this));
		game.getBarrierBtn().addActionListener(new Listener(this));
	}	
	
	/**
	 * Update the game view
	 * @param grid the new grid
	 * @param players the list of players
	 */
	public void updateGame(Square[][] grid, LinkedList<Player> players) {
		Player[] playersList = new Player[4];
		for(Player p : players) {
			int i = Functions.getNumberPlayer(p.getColor());
			playersList[i] = p;
		}
		Game game = (Game) this.getView().getComponent();
		game.getPanLeft().refreshPanel(playersList[0], playersList[2]);
		game.getPanRight().refreshPanel(playersList[1], playersList[3]);
		game.setGrid(grid);
		for(int i=0;i<game.getGamePanel().getGridLabels().length;i++) 
			for(int j=0;j<game.getGamePanel().getGridLabels()[0].length;j++) 
				game.getGamePanel().getGridLabels()[i][j].addMouseListener(new Listener(this));
		
	}

}
