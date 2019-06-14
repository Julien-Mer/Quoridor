package Client.Views;


import javax.swing.*;
import javax.swing.border.EmptyBorder;


import Client.Views.Components.ImageButton;
import Client.Views.Components.RoundedButton;
import Client.Views.Components.ImagePanel;


import java.awt.*;

public class NewGame extends JFrame {

	private int number = 0;
	private ImageButton addButtonPlayer;
	private ImageButton removeButtonPlayer;
	private JLabel countLblPlayer;
	private JLabel countLblRobot;
	private JLabel playerLbl;
	private JLabel robotLbl;
	private ImageButton addButtonRobot;
	private ImageButton removeButtonRobot;
	private JLabel nameLbl;
	private JTextField nameTextField;
	private RoundedButton confirmBtn;
	
	/**
	* Create the GUI and show it. For thread safety,
	* this method should be invoked from the
	* event-dispatching thread.
	*/
	public NewGame() {
		ImagePanel panel = new ImagePanel("src/resources/background.jpg");
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(4, 1));
		panel.setPreferredSize(new Dimension(500,500));
		
		ImagePanel namePanel = new ImagePanel("src/resources/background.jpg");
		namePanel.setBackground(Color.WHITE);
		this.nameLbl = new JLabel("Votre nom: ");
		this.nameTextField = new JTextField();
		this.nameTextField.setPreferredSize(new Dimension(100,20));
		this.confirmBtn = new RoundedButton(50,Color.WHITE);
		this.confirmBtn.setText("Confirmer");
		namePanel.add(this.nameLbl);
		namePanel.add(this.nameTextField);
		
	
		this.playerLbl = new JLabel("Nombre de joueurs: ");
		
		this.countLblPlayer = new JLabel("2");
		this.countLblPlayer.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		this.countLblRobot = new JLabel("0");
		this.countLblRobot.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		
		

		this.robotLbl = new JLabel("Nombre d'IA: ");
		
		this.addButtonPlayer = new ImageButton("bouton_plus.png");
		this.removeButtonPlayer = new ImageButton("bouton_moins.png");
		if(Integer.parseInt(this.countLblPlayer.getText())==4) {
			this.addButtonPlayer.setVisible(false);
		}else {
			this.removeButtonPlayer.setVisible(true);
		}
		if(Integer.parseInt(this.countLblPlayer.getText())==2) {
			this.removeButtonPlayer.setVisible(false);
		}else {
			this.removeButtonPlayer.setVisible(true);
		}
		
		this.addButtonRobot = new ImageButton("bouton_plus.png");
		this.removeButtonRobot = new ImageButton("bouton_moins.png");
		
		ImagePanel buttonPanelPlayer = new ImagePanel("src/resources/background.jpg");
		buttonPanelPlayer.setBackground(Color.WHITE);
		buttonPanelPlayer.add(this.playerLbl);
		buttonPanelPlayer.add(this.removeButtonPlayer);
		buttonPanelPlayer.add(countLblPlayer);
		buttonPanelPlayer.add(this.addButtonPlayer);
		
		ImagePanel buttonPanelRobot = new ImagePanel("src/resources/background.jpg");
		buttonPanelRobot.setBackground(Color.WHITE);
		buttonPanelRobot.add(this.robotLbl);
		buttonPanelRobot.add(this.removeButtonRobot);
		buttonPanelRobot.add(countLblRobot);
		buttonPanelRobot.add(this.addButtonRobot);
		
		ImagePanel panelConfirm = new ImagePanel("src/resources/background.jpg");
		buttonPanelRobot.setBackground(Color.WHITE);
		panelConfirm.add(this.confirmBtn);
		
		panel.add(namePanel);
		panel.add(buttonPanelPlayer);
		panel.add(buttonPanelRobot);
		panel.add(panelConfirm);
		add(panel);
		pack();
	}
	
	
	/**
	 * Add the value to number
	 * @param number the value to add
	 */
	public void addNumber(int number) {
		this.number += number;
	}
	
	
	/**
	 * Get the add button
	 * @return the add button
	 */
	public ImageButton getAddButtonPlayer() {
		return this.addButtonPlayer;
	}
	
	/**
	 * Get the remove button
	 * @return the remove button
	 */
	public ImageButton getRemoveButtonPlayer() {
		return this.removeButtonPlayer;
	}
	
	/**
	 * Get the count JLabel
	 * @return the count JLabel
	 */
	public JLabel getCountLblPlayer() {
		return this.countLblPlayer;
	}
} 
