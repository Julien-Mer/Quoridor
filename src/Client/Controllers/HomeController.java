package Client.Controllers;
import Client.Views.Home;

public class HomeController {

	/**
	 * The Home view
	 */
	private Home view;
	
	/**
	 * Constructor of HomeController
	 * @param client The client used by the game 
	 */
	public HomeController() {
		this.view = new Home(true);
		this.view.setVisible(true);
		this.view.getNewGameBtn().addActionListener(new Listener(this));
		this.view.getResumeGameBtn().addActionListener(new Listener(this));
		this.view.getLearningModeBtn().addActionListener(new Listener(this));
	}
	
	/**
	 * Method which return the current view
	 * @return the current view
	 */
	public Home getView() {
		return this.view;
	}
	

}
