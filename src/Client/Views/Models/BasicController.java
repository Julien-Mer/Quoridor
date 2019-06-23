package Client.Views.Models;

import java.awt.Component;
import java.awt.Dimension;
import Client.Client;

public class BasicController {

	/**
	 * The view used
	 */
	private BasicView view;
	
	/**
	 * Initialize a view on the controller
	 * @param dimension the dimension of his first view
	 * @param component the component of his first view
	 */
	public void initView(Dimension dimension, Component component) {
		this.view = new BasicView(dimension, component);
		Client.client.view = this.view;
	}	
	
	/**
	 * Method which return the current view
	 * @return the current view
	 */
	public BasicView getView() {
		return this.view;
	}
	
}
