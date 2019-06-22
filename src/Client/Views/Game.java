package Client.Views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.*;
import Game.Square;
import Server.Player;
import Client.Controllers.GamePanelController;
import Client.Views.Components.*;
public class Game extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
	private RoundedButton saveBtn;
	private RoundedButton placeBarrierBtn;
	private GamePanelController pan;
	private JPanel back;	
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	private String nbBarr1;
	private String nbBarr2;
	private String nbBarr3;
	private String nbBarr4;
	private LinkedList<Player> players;
	private ImagePanel gridGame;
	/**
	 * Constructor of Game
	 */
	public Game() {
		this.initComponent();
	}
	
	public void initComponent() {
		back = new JPanel();
		back.setLayout(new BorderLayout());
		back.setPreferredSize(new Dimension(1000,600));
		this.setResizable(false);
		setTitle("Quoridor");
		
		//PanelInfoPlayer panLbl1 = new PanelInfoPlayer(name1,nbBarr1,name2,nbBarr2,"src/resources/background_game_haut_gauche.jpg","src/resources/background_game_bas_gauche.jpg");
		//PanelInfoPlayer panLbl2 = new PanelInfoPlayer(name3,nbBarr3,name4,nbBarr4,"src/resources/background_game_haut_droit.jpg","src/resources/background_game_bas_droit.jpg");
		
		PanelInfoPlayer panLbl1 = new PanelInfoPlayer(null,null,"Jujum","10","src/resources/background_game_haut_gauche.jpg","src/resources/background_game_bas_gauche.jpg",null,"src/resources/Images/pion-red.png");
		PanelInfoPlayer panLbl2 = new PanelInfoPlayer("Flash","10","Baptou","10","src/resources/background_game_haut_gauche.jpg","src/resources/background_game_bas_gauche.jpg","src/resources/Images/pion-green.png","src/resources/Images/pion-blue.png");
		
		gridGame = new ImagePanel("src/resources/background_Game.jpg");
		gridGame.setLayout(new GridLayout(1,3));
		this.pan = new GamePanelController();
		gridGame.add(panLbl1);
		gridGame.add(pan.getPan());
		gridGame.add(panLbl2);
		
		
		this.saveBtn = new RoundedButton(30,Color.WHITE);
		this.saveBtn.setText("Save and leave");
		this.placeBarrierBtn = new RoundedButton(30,Color.WHITE);
		this.placeBarrierBtn.setText("Place a barrier");
		ImagePanel panS = new ImagePanel("src/resources/background_Bouton.jpg");
		panS.setLayout(new BorderLayout());
		panS.setPreferredSize(new Dimension(40,40));
		panS.add(this.saveBtn,BorderLayout.WEST);
		panS.add(this.placeBarrierBtn,BorderLayout.EAST);
		
		this.titleLbl = new JLabel(new ImageIcon("src/resources/titre.png"));
		ImagePanel panT = new ImagePanel("src/resources/background_game_title.jpg");
		panT.add(this.titleLbl);
		
		back.add(panT,BorderLayout.NORTH);
		back.add(gridGame,BorderLayout.CENTER);
		back.add(panS,BorderLayout.SOUTH);
		add(back);
		pack();
	}

	/**
	 * Set the grid
	 * @param grid the new grid
	 */
	public void setGrid(Square[][] grid) {
	    this.pan.updatePanel(grid);
	}
	
	public RoundedButton getSaveBtn() {
		return this.saveBtn;
	}
	
	public RoundedButton getBarrierBtn() {
		return this.placeBarrierBtn;
	}
	
	public void updateP(LinkedList<Player> players) {
		this.players=players;
		if(players.size()==2) {
			this.name1 = players.get(0).getName();
			this.nbBarr1 = String.valueOf(players.get(0).getNumberBarriersLeft());
			this.name2 = players.get(1).getName();
			this.nbBarr2 = String.valueOf(players.get(1).getNumberBarriersLeft());
		}else if(players.size()==3) {
			this.name1 = players.get(0).getName();
			this.nbBarr1 = String.valueOf(players.get(0).getNumberBarriersLeft());
			this.name2 = players.get(1).getName();
			this.nbBarr2 = String.valueOf(players.get(1).getNumberBarriersLeft());
			this.name3 = players.get(2).getName();
			this.nbBarr3 = String.valueOf(players.get(2).getNumberBarriersLeft());
		}else {
			this.name1 = players.get(0).getName();
			this.nbBarr1 = String.valueOf(players.get(0).getNumberBarriersLeft());
			this.name2 = players.get(1).getName();
			this.nbBarr2 = String.valueOf(players.get(1).getNumberBarriersLeft());
			this.name3 = players.get(2).getName();
			this.nbBarr3 = String.valueOf(players.get(2).getNumberBarriersLeft());
			this.name4  = players.get(3).getName();
			this.nbBarr4 = String.valueOf(players.get(3).getNumberBarriersLeft());
		}
		
	}

}
