package Client.Controllers;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Client.Views.Components.GamePanel;
import Game.ColorSquare;
import Game.Square;

public class GamePanelController {
	private GamePanel g;
	private JLabel[][] grid;
	
	public GamePanelController() {
		this.g = new GamePanel();
		this.grid = this.g.getGridLabels();
		for(int i=0;i<this.grid.length;i++) {
			for(int j=0;j<this.grid.length;j++) {
				this.grid[i][j].addMouseListener(new Listener(this));
			}
		}
		
	}
	
	public void updatePanel(Square[][] grid) {
		this.g.updateP(grid);
	}
	
	public JLabel[][] getGrid(){
		return this.grid;
	}
	
	public JLabel getLbl(int x, int y) {
		return this.grid[x][y];
	}
	
	public GamePanel getPan() {
		return this.g;
	}
	
}
