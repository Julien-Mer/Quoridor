package Client.Controllers;

import Client.*;
import Client.Views.Home;

public class HomeController {

	/**
	 * The client of game
	 */
	private Client client;
	
	/**
	 * The Home view
	 */
	private Home view;
	
	/**
	 * Constructor of HomeController
	 * @param client The client used by the game 
	 */
	public HomeController(Client client,Home h) {
		this.client = client;
		this.view = h;
	}
	
	/**
	 * Method which return the current view
	 * @return the current view
	 */
	public Home getView() {
		return this.view;
	}
	
	/**
	 * Method which return the current Client
	 * @return the current client
	 */
	public Client getClient() {
		return this.client;
	}
	
	

}
