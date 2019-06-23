package Client.Controllers;

import java.awt.Dimension;
import Client.Views.Home;
import Client.Views.Models.BasicController;
import Model.File;

public class HomeController extends BasicController {

	/**
	 * Constructor of HomeController
	 */
	public HomeController() {
		boolean resumeGame = false;
		if(File.readObject(File.SAVE_FILE) != null)
			resumeGame = true;
		Home home = new Home(resumeGame);
		Dimension dimension = new Dimension(600, 375);
		if(resumeGame)
			dimension = new Dimension(600, 450);
		super.initView(dimension, home);
		
		home.getNewGameBtn().addActionListener(new Listener(this));
		if(resumeGame)
			home.getResumeGameBtn().addActionListener(new Listener(this));
		home.getLearningModeBtn().addActionListener(new Listener(this));
	}	

}
