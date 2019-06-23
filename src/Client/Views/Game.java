package Client.Views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;

import Game.Square;
import Client.Controllers.Helpers.Functions;
import Client.Views.Components.*;
import Client.Views.Models.Resources;

public class Game extends JPanel {

	private RoundedButton saveBtn;
	private RoundedButton placeBarrierBtn;
	private GamePanel gamePanel;
	private JLabel stateLbl;
	
	PanelInfoPlayer panLeft;
	PanelInfoPlayer panRight;
	
	/**
	 * Get the left panel
	 * @return the left panel
	 */
	public PanelInfoPlayer getPanLeft() {
		return this.panLeft;
	}
	
	/**
	 * Set a text to the state JLabel
	 * @param text the next text of the label
	 */
	public void setStateLbl(String text) {
		this.stateLbl.setText(text);;
	}
	
	/**
	 * Get the right panel
	 * @return the right panel
	 */
	public PanelInfoPlayer getPanRight() {
		return this.panRight;
	}
	
	/**
	 * Constructor of Game
	 */
	public Game() {
		this.setOpaque(false);
		JPanel back = new JPanel();
		back.setLayout(new BorderLayout());
		back.setOpaque(false);
		
		JPanel spacePanel = new JPanel();
		spacePanel.setOpaque(false);
		
		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		northPanel.setLayout(new GridLayout(2,1));
		JPanel statePanel = new JPanel();
		statePanel.setOpaque(false);
		statePanel.setLayout(new GridBagLayout());
		this.stateLbl = new JLabel("En attente...");
		this.stateLbl.setForeground(Resources.BORDER);
		this.stateLbl.setFont(new Font(Font.SERIF,Font.BOLD,25));
		statePanel.add(this.stateLbl);
		northPanel.add(statePanel);
		
		JPanel gridGame = new JPanel();
		gridGame.setOpaque(false);
		gridGame.setLayout(new GridLayout(1,3));
		this.gamePanel = new GamePanel();
		this.panLeft = new PanelInfoPlayer(null, null);
		this.panRight = new PanelInfoPlayer(null, null);
		gridGame.add(this.panLeft);
		gridGame.add(gamePanel);
		gridGame.add(this.panRight);
		
		this.saveBtn = new RoundedButton(50, Resources.BTN_COLOR);
		this.saveBtn.setText("Sauvegarder et quitter");
		this.saveBtn.setForeground(Resources.BTN_TEXT_COLOR);
		
		this.placeBarrierBtn = new RoundedButton(50, Resources.BTN_COLOR);
		this.placeBarrierBtn.setText("Placer une barrière");
		this.placeBarrierBtn.setForeground(Resources.BTN_TEXT_COLOR);

		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setLayout(new BorderLayout());
		southPanel.setPreferredSize(new Dimension(40,40));
		southPanel.add(this.saveBtn,BorderLayout.WEST);
		southPanel.add(this.placeBarrierBtn,BorderLayout.EAST);
		
		JPanel spacerSouthPanel = new JPanel();
		spacerSouthPanel.setOpaque(false);
		spacerSouthPanel.setLayout(new GridLayout(3,1));
		spacerSouthPanel.add(spacePanel);
		spacerSouthPanel.add(southPanel,BorderLayout.CENTER);
		
		back.add(northPanel,BorderLayout.NORTH);
		back.add(gridGame,BorderLayout.CENTER);
		back.add(spacerSouthPanel,BorderLayout.SOUTH);
		this.setLayout(new GridLayout(1, 1));
		this.add(back);
	}

	public void setGrid(Square[][] grid) {
		this.gamePanel.removeAll();
  
    	for(int i=0;i<grid.length;i++) {
    		for(int j=0;j<grid[i].length;j++) {
    			if(i%2 == 0 && j%2 == 0) {
    				this.gamePanel.getGridLabels()[j/2][i/2] = this.gamePanel.createLbl(Functions.getPathSquare(grid[i][j].getColor()));
    				this.gamePanel.getGridLabels()[j/2][i/2].setBorder(Functions.getBorder(grid, i, j));
    			}
    		}
    	}
    	this.gamePanel.initComponent();
    	this.gamePanel.revalidate();
    	this.gamePanel.repaint();
	}
	
	public JLabel getLbl(int x, int y) {
		return this.gamePanel.getGridLabels()[x][y];
	}
	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	
	public RoundedButton getSaveBtn() {
		return this.saveBtn;
	}
	
	public RoundedButton getBarrierBtn() {
		return this.placeBarrierBtn;
	}
	
}
