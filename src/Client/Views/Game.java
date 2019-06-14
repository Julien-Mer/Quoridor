package Client.Views;

import java.awt.Dimension;

import javax.swing.*;
import Client.Views.Components.*;
public class Game extends JFrame{

	private JFrame grid;
	
	/**
	 * Constructor of Game
	 */
	public Game() {
		this.initComponent();
	}
	
	public void initComponent() {
		ImagePanel back = new ImagePanel("src/resources/background_Game.jpg");
		back.setPreferredSize(new Dimension(1000,600));
		setTitle("Quoridor");
		GamePanel pan = new GamePanel();
		back.add(pan);
		add(back);
		pack();
	}

	/**
	 * Set the grid
	 * @param grid the new grid
	 */
	public void setGrid(JFrame grid) {
		this.grid = grid;
	}

}
