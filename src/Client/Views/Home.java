package Client.Views;

import javax.swing.JButton;

public class Home {

	private JButton newGameBtn;
	private JButton resumeGameBtn;
	private JButton learningModeBtn;
	
	/**
	 * Constructor of Home
	 */
	public Home() {

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
