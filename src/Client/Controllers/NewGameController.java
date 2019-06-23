package Client.Controllers;

import java.awt.Dimension;
import Client.Views.NewGame;
import Client.Views.Models.BasicController;

public class NewGameController extends BasicController {
	
	/**
	 * Constructor of NewGameController
	 */
	public NewGameController() {
		NewGame newGame = new NewGame();
		super.initView(new Dimension(500, 650), newGame);
		
		newGame.getAddButtonPlayer().addActionListener(new Listener(this));
		newGame.getAddButtonRobot().addActionListener(new Listener(this));
		newGame.getRemoveButtonPlayer().addActionListener(new Listener(this));
		newGame.getRemoveButtonRobot().addActionListener(new Listener(this));
		newGame.getConfirmBtn().addActionListener(new Listener(this));
		newGame.getChoiceLvl().addActionListener(new Listener(this));
		newGame.getTheme().addActionListener(new Listener(this));
	}	


}
