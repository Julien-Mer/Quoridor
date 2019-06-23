package Client.Views;


import javax.swing.*;
import Client.Views.Components.ImageButton;
import Client.Views.Components.RoundedButton;
import Client.Views.Models.Resources;
import Client.Views.Components.ImagePanel;
import javax.swing.JComboBox;


import java.awt.*;

public class NewGame extends JPanel {
	
	private int number=0;
	private ImageButton addButtonPlayer;
	private ImageButton removeButtonPlayer;
	private JLabel countLblPlayer;
	private JLabel countLblRobot;
	private JLabel playerLbl;
	private ImageButton addButtonRobot;
	private ImageButton removeButtonRobot;
	private JTextField nameTextField;
	private RoundedButton confirmBtn;
	private JComboBox choiceLvlIA;
	private JComboBox choiceTheme;
	
	/**
	 * Constructor of the newGame Panel
	 */
	public NewGame() {
		JPanel spacePanel1 = new JPanel();
		spacePanel1.setOpaque(false);
		
		this.setOpaque(false);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(7, 1));
		
		Font font = new Font("Dialog", Font.PLAIN, 18);
		
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		JLabel nameLbl = new JLabel("Votre nom: ");
		nameLbl.setForeground(Resources.TEXT_COLOR);
		nameLbl.setFont(font);
		this.nameTextField = new JTextField();
		this.nameTextField.setPreferredSize(new Dimension(100,30));
		namePanel.add(nameLbl);
		namePanel.add(this.nameTextField);
		 
		this.playerLbl = new JLabel("Nombre de joueurs: ");
		playerLbl.setForeground(Resources.TEXT_COLOR);
		playerLbl.setFont(font);
		
		this.countLblPlayer = new JLabel("2");
		countLblPlayer.setForeground(Resources.TEXT_COLOR);
		countLblPlayer.setFont(font);
		
		this.countLblRobot = new JLabel("0");
		countLblRobot.setForeground(Resources.TEXT_COLOR);
		countLblRobot.setFont(font);
		
		JLabel robotLbl = new JLabel("Nombre d'IA: ");
		robotLbl.setForeground(Resources.TEXT_COLOR);
		robotLbl.setFont(font);
		
		this.addButtonPlayer = new ImageButton("more.png");
		this.removeButtonPlayer = new ImageButton("less.png");
		this.removeButtonPlayer.setVisible(false);
		
		this.addButtonRobot = new ImageButton("more.png");
		this.removeButtonRobot = new ImageButton("less.png");
		this.removeButtonRobot.setVisible(false);
		
		JPanel buttonPanelPlayer = new JPanel();
		buttonPanelPlayer.setOpaque(false);
		buttonPanelPlayer.add(this.playerLbl);
		buttonPanelPlayer.add(this.removeButtonPlayer);
		buttonPanelPlayer.add(countLblPlayer);
		buttonPanelPlayer.add(this.addButtonPlayer);
		
		JPanel buttonPanelRobot = new JPanel();
		buttonPanelRobot.setOpaque(false);
		buttonPanelRobot.add(robotLbl);
		buttonPanelRobot.add(this.removeButtonRobot);
		buttonPanelRobot.add(countLblRobot);
		buttonPanelRobot.add(this.addButtonRobot);
		
		
		Object[] levels = new Object[] {"Facile","Moyen","Difficile"};
		this.choiceLvlIA = new JComboBox(levels);
		JLabel choixIaLbl = new JLabel("Niveau de l'IA: ");
		choixIaLbl.setForeground(Resources.TEXT_COLOR);
		choixIaLbl.setFont(font);
		JPanel choiceIaPanel = new JPanel();
		choiceIaPanel.setOpaque(false);
		choiceIaPanel.add(choixIaLbl);
		choiceIaPanel.add(this.choiceLvlIA);
		
		this.choiceTheme = new JComboBox(Resources.themes);
		this.choiceTheme.setSelectedItem(Resources.theme);
		JLabel choixThemeLbl = new JLabel("Theme du jeu: ");
		choixThemeLbl.setForeground(Resources.TEXT_COLOR);
		choixThemeLbl.setFont(font);
		JPanel choiceThemePanel = new JPanel();
		choiceThemePanel.setOpaque(false);
		choiceThemePanel.add(choixThemeLbl);
		choiceThemePanel.add(this.choiceTheme);
		
		JPanel panelConfirm = new JPanel();
		panelConfirm.setOpaque(false);
		this.confirmBtn = new RoundedButton(50, Resources.BTN_COLOR);
		this.confirmBtn.setText("Commencer");
		this.confirmBtn.setForeground(Resources.BTN_TEXT_COLOR);
		panelConfirm.add(this.confirmBtn);
		
		panel.add(namePanel);
		panel.add(buttonPanelPlayer);
		panel.add(buttonPanelRobot);
		panel.add(choiceIaPanel);
		panel.add(spacePanel1);
		panel.add(choiceThemePanel);
		panel.add(panelConfirm);
		add(panel);
	}
	
	/**
	 * Method which return the name of the player
	 * @return the name of the player
	 */
	public JTextField getNameTextField() {
		return this.nameTextField;
	}
	
	/**
	 * Method which return the JComboBox
	 * @return the JComboBox
	 */
	public JComboBox getChoiceLvl() {
		return this.choiceLvlIA;
	}
	
	/**
	 * Method which return the JComboBox
	 * @return the JComboBox
	 */
	public JComboBox getTheme() {
		return this.choiceTheme;
	}
	
	/**
	 * Method which return the confirmation button
	 * @return the confirmation button
	 */
	public RoundedButton getConfirmBtn() {
		return this.confirmBtn;
	}
	
	/**
	 * Method which enable or disable the confirm button
	 * @param b true or false depending the case
	 */
	public void setConfirmBtn(boolean b) {
		this.confirmBtn.setEnabled(b);
	}
	
	/**
	 * Change the value of the label player
	 * @param number the value to change
	 */
	public void changeLabelPlayer(int number) {
		this.countLblPlayer.setText(String.valueOf(number));
	}
	
	/**
	 * Change the value of the label robot
	 * @param number the value to change
	 */
	public void changeLabelRobot(int number) {
		this.countLblRobot.setText(String.valueOf(number));
	}
	
	
	/**
	 * Get the add button of the number of player
	 * @return the add button
	 */
	public ImageButton getAddButtonPlayer() {
		return this.addButtonPlayer;
	}
	
	/**
	 * Get the remove button of the number of player
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
	
	/**
	 * Get the count JLabel
	 * @return the count JLabel
	 */
	public JLabel getCountLblRobot() {
		return this.countLblRobot;
	}
	
	/**
	 * Get the remove button of the number of robot
	 * @return the remove button
	 */
	public ImageButton getRemoveButtonRobot() {
		return this.removeButtonRobot;
	}
	
	/**
	 * Get the add button of the number of robot
	 * @return the add button
	 */
	public ImageButton getAddButtonRobot() {
		return this.addButtonRobot;
	}
	
	/**
	 * Method which enable or disable the add button player
	 * @param b true or false depending the case
	 */
	public void setAddButtonPlayer(boolean b) {
		this.addButtonPlayer.setVisible(b);
	}
	
	/**
	 * Method which enable or disable the remove button player
	 * @param b true or false depending the case
	 */
	public void setRemoveButtonPlayer(boolean b) {
		this.removeButtonPlayer.setVisible(b);
	}
	
	/**
	 * Method which enable or disable the remove button robot
	 * @param b true or false depending the case
	 */
	public void setRemoveButtonRobot(boolean b) {
		this.removeButtonRobot.setVisible(b);
	}
	
	/**
	 * Method which enable or disable the add button robot
	 * @param b true or false depending the case
	 */
	public void setAddButtonRobot(boolean b) {
		this.addButtonRobot.setVisible(b);
	}
} 
