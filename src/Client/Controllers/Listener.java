package Client.Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Client.Controllers.*;
import Client.Controllers.Helpers.*;

public class Listener implements ActionListener {
	private HomeController homeController;
	private GameController gameController;
	private NewGameController newGameController;
	/**
	 * Construct the ActionListener for the HomeController
	 * @param homeController the HomeController
	 */
	public Listener(HomeController homeController) {
		this.homeController = homeController;
	}

	/**
	 * Construct the ActionListener for the GameController
	 * @param gameController the GameController
	 */
	public Listener(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * Construct the ActionListener for the NewGameController
	 * @param newGameController the NewGameController
	 */
	public Listener(NewGameController newGameController) {
		this.newGameController = newGameController;
	}

	/**
	 * ActionPerform method inherited of ActionListener
	 * @param event the ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		if(this.homeController!=null) {
			if(event.getSource()==this.homeController.getView().getNewGameBtn()) {
				Functions.changeView(this.homeController.getView(), new NewGameController(this.homeController.getClient()).getView());
			}
			if(event.getSource()==this.homeController.getView().getResumeGameBtn()) {
				//TODO
			}
		}
	}

}