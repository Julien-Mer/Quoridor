package Client.Views;
import Client.Views.Components.*;
import Client.Views.Models.Resources;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {

	private RoundedButton newGameBtn;
	private RoundedButton resumeGameBtn;
	private RoundedButton learningModeBtn;


	/**
	 * Constructor of Home
	 * @param activeResumeGame if the game can be resumed or not
	 */
	public Home(boolean activeResumeGame) {
		this.setOpaque(false);
		if(activeResumeGame)
			this.setLayout(new GridLayout(3, 1));
		else
			this.setLayout(new GridLayout(2, 1));
	
		JPanel firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		this.newGameBtn = new RoundedButton(50, Resources.BTN_COLOR);
		this.newGameBtn.setText("Nouvelle partie");
		this.newGameBtn.setForeground(Resources.BTN_TEXT_COLOR);
		firstPanel.add(this.newGameBtn);
		this.add(firstPanel);
		
		if(activeResumeGame) { // Seulement s'il y a une sauvegarde
			JPanel secondPanel = new JPanel();
			secondPanel.setOpaque(false);
			this.resumeGameBtn = new RoundedButton(50, Resources.BTN_COLOR);
			this.resumeGameBtn.setText("Reprendre la partie");
			this.resumeGameBtn.setForeground(Resources.BTN_TEXT_COLOR);
			secondPanel.add(resumeGameBtn);
			this.add(secondPanel);
		}
		
		JPanel thirdPanel = new JPanel();
		thirdPanel.setOpaque(false);
		this.learningModeBtn = new RoundedButton(50, Resources.BTN_COLOR);
		this.learningModeBtn.setText("Mode pédagogique");
		this.learningModeBtn.setForeground(Resources.BTN_TEXT_COLOR);
		thirdPanel.add(this.learningModeBtn);
		this.add(thirdPanel);
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
