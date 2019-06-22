package Client.Controllers;

import Client.*;
import Client.Views.NewGame;

public class NewGameController {
	private String name;
	/**
	 * The NewGame view
	 */
	private NewGame view;

	/**
	 * Constructor of NewGameController
	 * @param client the client used by the game
	 */
	public NewGameController() {
		this.view = new NewGame();
		Client.client.view = this.view;
		this.view.setVisible(true);
		this.view.getAddButtonPlayer().addActionListener(new Listener(this));
		this.view.getAddButtonRobot().addActionListener(new Listener(this));
		this.view.getRemoveButtonPlayer().addActionListener(new Listener(this));
		this.view.getRemoveButtonRobot().addActionListener(new Listener(this));
		this.view.getConfirmBtn().addActionListener(new Listener(this));
		this.view.getChoiceLvl().addActionListener(new Listener(this));
		this.view.getTerm().addActionListener(new Listener(this));
	}
	
	/**
	 *  Method which return the current view
	 * @return the current view
	 */
	public NewGame getView() {
		return this.view;
	}

}
