package Client.Views;
import Game.Square;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import Client.Views.Components.*;
public class Game extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame grid;
	private JLabel titleLbl;
	private JLabel barriereLbl1;
	private JLabel nameLbl1;
	private JLabel nameLbl2;
	private JLabel barriereLbl2;
	private RoundedButton saveBtn;
	
	/**
	 * Constructor of Game
	 */
	public Game() {
		this.initComponent();
	}
	
	public void initComponent() {
		ImagePanel back = new ImagePanel("src/resources/background_Game.jpg");
		back.setLayout(new BorderLayout());
		back.setPreferredSize(new Dimension(1000,600));
		this.setResizable(false);
		setTitle("Quoridor");
		
		this.nameLbl1 = new JLabel("Name: ");
		this.barriereLbl1 = new JLabel("Il vous reste: "+" barrieres");
		ImagePanel panG = new ImagePanel("src/resources/background_Game.jpg");
		panG.setLayout(new GridLayout(2,1));
		panG.add(this.nameLbl1);
		panG.add(this.barriereLbl1);
		
		GamePanel pan = new GamePanel();
		
		
		this.nameLbl2 = new JLabel("Name: ");
		this.barriereLbl2 = new JLabel("Il vous reste: "+" barrieres");
		ImagePanel panD = new ImagePanel("src/resources/background_Game.jpg");
		panD.setLayout(new BorderLayout());
		panD.add(this.nameLbl2,BorderLayout.WEST);
		panD.add(this.barriereLbl2,BorderLayout.WEST);
		
		this.saveBtn = new RoundedButton(50,Color.WHITE);
		this.saveBtn.setText("Save and quit");
		ImagePanel panS = new ImagePanel("src/resources/background_Game.jpg");
		panS.setPreferredSize(new Dimension(40,40));
		panS.add(this.saveBtn);
		
		this.titleLbl = new JLabel(new ImageIcon("src/resources/titre.jpg"));
		ImagePanel panT = new ImagePanel("src/resources/background_Game.jpg");
		panT.setPreferredSize(new Dimension(10,10));
		panT.add(this.titleLbl);
		
		back.add(panT,BorderLayout.NORTH);
		back.add(panD,BorderLayout.WEST);
		back.add(pan,BorderLayout.CENTER);
		back.add(panG,BorderLayout.EAST);
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
