package Client.Views;
import Client.Views.Components.*;
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame{

	private static final long serialVersionUID = 1L;
	private ImagePanel backgroundPanel;
	private JLabel titreLabel;
	private RoundedButton newGameBtn;
	private RoundedButton resumeGameBtn;
	private RoundedButton learningModeBtn;
	private boolean activeResumeGame;


	/**
	 * Constructor of Home
	 */
	public Home(boolean activeResumeGame) {
		 this.activeResumeGame = activeResumeGame;
		 this.initComponent();
	}

	/**
	 * Method which initialize all the component of the JFrame.
	 **/
	public void initComponent(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Quoridor");
		this.setResizable(false);

		this.newGameBtn = new RoundedButton(50,Color.WHITE);
		this.newGameBtn.setText("Nouvelle partie");

		this.resumeGameBtn = new RoundedButton(50,Color.WHITE);
		this.resumeGameBtn.setText("Reprendre la partie");
		if(this.activeResumeGame) {
			this.resumeGameBtn.setVisible(true);
		}else {
			this.resumeGameBtn.setVisible(false);
		}
		//this.resumeGameBtn.addActionListener(new HomeController(this));

		this.learningModeBtn = new RoundedButton(50,Color.WHITE);
		this.learningModeBtn.setText("Mode pédagogique");
		//this.learningModeBtn.addActionListener(new HomeController(this));

		this.titreLabel = new JLabel(new ImageIcon("src/resources/titre.png"));
		
		this.backgroundPanel=new ImagePanel("src/resources/background.jpg");
		this.backgroundPanel.setPreferredSize(new Dimension(500,400));
		this.backgroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
		this.backgroundPanel.add(this.titreLabel);
		this.backgroundPanel.add(this.newGameBtn);
		this.backgroundPanel.add(this.resumeGameBtn);
		this.backgroundPanel.add(this.learningModeBtn);
		add(this.backgroundPanel);
		pack();


	}


	/**
	 * Get the newGame button
	 * @return the newGame button
	 */
	public JButton getNewGameBtn() {
		return this.newGameBtn;
	}

	/**
	 * Get the resumeGame button
	 * @return the resumeGame button
	 */
	public JButton getResumeGameBtn() {
		return this.resumeGameBtn;
	}

	/**
	 * Get the learningModeBtn
	 * @return the learningMode button
	 */
	public JButton getLearningModeBtn() {
		return this.learningModeBtn;
	}

}
