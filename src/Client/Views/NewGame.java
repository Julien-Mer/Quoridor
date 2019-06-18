package Client.Views;


import javax.swing.*;
import Client.Views.Components.ImageButton;
import Client.Views.Components.RoundedButton;
import Client.Views.Components.ImagePanel;
import javax.swing.JComboBox;


import java.awt.*;

public class NewGame extends JFrame {
	private int number=0;
	private static final long serialVersionUID = -8380809043760417461L;
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
	@SuppressWarnings("rawtypes")
	private JComboBox choiceLvlIA;
	private JLabel choix;
	
	
	/**
	* Create the GUI and show it. For thread safety,
	* this method should be invoked from the
	* event-dispatching thread.
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewGame() {
		ImagePanel panel = new ImagePanel("src/resources/background.jpg");
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(5, 1));
		panel.setPreferredSize(new Dimension(500,500));
		
		ImagePanel namePanel = new ImagePanel("src/resources/background_first_panel.jpg");
		namePanel.setBackground(Color.WHITE);
		this.nameLbl = new JLabel("Votre nom: ");
		this.nameTextField = new JTextField();
		this.nameTextField.setPreferredSize(new Dimension(100,20));
		this.confirmBtn = new RoundedButton(50,Color.WHITE);
		this.confirmBtn.setText("Confirmer");
		namePanel.add(this.nameLbl);
		namePanel.add(this.nameTextField);
		
		Object[] levels = new Object[] {"Facile","Moyen","Difficile"};
		this.choiceLvlIA = new JComboBox(levels);
		this.choix = new JLabel("Niveau de l'IA: ");
		
		this.playerLbl = new JLabel("Nombre de joueurs: ");
		
		this.countLblPlayer = new JLabel("2");
		this.countLblPlayer.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		this.countLblRobot = new JLabel("0");
		this.countLblRobot.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		this.robotLbl = new JLabel("Nombre d'IA: ");
		
		this.addButtonPlayer = new ImageButton("bouton_plus.png");
		this.removeButtonPlayer = new ImageButton("bouton_moins.png");
		this.removeButtonPlayer.setVisible(false);
		
		this.addButtonRobot = new ImageButton("bouton_plus.png");
		this.removeButtonRobot = new ImageButton("bouton_moins.png");
		this.removeButtonRobot.setVisible(false);
		
		ImagePanel buttonPanelPlayer = new ImagePanel("src/resources/background_second_panel.jpg");
		buttonPanelPlayer.setBackground(Color.WHITE);
		buttonPanelPlayer.add(this.playerLbl);
		buttonPanelPlayer.add(this.removeButtonPlayer);
		buttonPanelPlayer.add(countLblPlayer);
		buttonPanelPlayer.add(this.addButtonPlayer);
		
		ImagePanel buttonPanelRobot = new ImagePanel("src/resources/background_third_panel.jpg");
		buttonPanelRobot.setBackground(Color.WHITE);
		buttonPanelRobot.add(this.robotLbl);
		buttonPanelRobot.add(this.removeButtonRobot);
		buttonPanelRobot.add(countLblRobot);
		buttonPanelRobot.add(this.addButtonRobot);
		
		ImagePanel choiceLvl = new ImagePanel("src/resources/background_fourth_panel.jpg");
		choiceLvl.add(this.choix);
		choiceLvl.add(this.choiceLvlIA);
		
		ImagePanel panelConfirm = new ImagePanel("src/resources/background_fifth_panel.jpg");
		buttonPanelRobot.setBackground(Color.WHITE);
		panelConfirm.add(this.confirmBtn);
		
		panel.add(namePanel);
		panel.add(buttonPanelPlayer);
		panel.add(buttonPanelRobot);
		panel.add(choiceLvl);
		panel.add(panelConfirm);
		add(panel);
		pack();
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
	@SuppressWarnings("rawtypes")
	public JComboBox getChoiceLvl() {
		return this.choiceLvlIA;
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
