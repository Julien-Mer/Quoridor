package Client.Views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import Client.Views.Components.*;
public class Game extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
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

		PanelInfoPlayer panLbl1 = new PanelInfoPlayer("Pierre","10","Julien","10","src/resources/background_Game.jpg");
		PanelInfoPlayer panLbl2 = new PanelInfoPlayer("Baptiste","10","Max","10","src/resources/background_Game.jpg");
		
		ImagePanel gridGame = new ImagePanel("src/resources/background_Game.jpg");
		gridGame.setLayout(new GridLayout(1,3));
		GamePanel pan = new GamePanel();
		gridGame.add(panLbl1);
		gridGame.add(pan);
		gridGame.add(panLbl2);
		
		
		this.saveBtn = new RoundedButton(30,Color.WHITE);
		this.saveBtn.setText("Save and leave");
		ImagePanel panS = new ImagePanel("src/resources/background_Bouton.jpg");
		panS.setLayout(new BorderLayout());
		panS.setPreferredSize(new Dimension(40,40));
		panS.add(this.saveBtn,BorderLayout.WEST);
		
		this.titleLbl = new JLabel(new ImageIcon("src/resources/titre.png"));
		ImagePanel panT = new ImagePanel("src/resources/background_Game.jpg");
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
	/*public void setGrid(JFrame grid) {
		this.grid = grid;
	}*/

}
