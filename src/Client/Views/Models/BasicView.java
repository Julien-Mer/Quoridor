package Client.Views.Models;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;

import Client.Views.Components.ImagePanel;

public class BasicView extends JFrame{
	
	/**
	 * Get the component displayed on the view
	 * @return the component of the view
	 */
	public Component getComponent() {
		return this.component;
	}
	
	/**
	 * The component displayed on the BasicView
	 */
	private Component component;
	
	/**
	 * Constructor of BasicView
	 */
	public BasicView(Dimension dimension, Component component) {
		this.component = component;
		
		ImagePanel backgroundPanel = new ImagePanel(Resources.BACKGROUND_IMAGE);
		backgroundPanel.setLayout(new BorderLayout());
		this.setPreferredSize(dimension);
		this.setResizable(false);
		setTitle("Quoridor");
		this.getContentPane().setLayout(new BorderLayout());
		this.setIconImage((new ImageIcon(Resources.LOGO_IUT_BIG_IMAGE)).getImage());
		
		TopPanel topPanel = new TopPanel();
		
		JPanel logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.setLayout(new BorderLayout());
		JLabel logoIutLabel = new JLabel(new ImageIcon(Resources.LOGO_IUT_IMAGE));
		logoPanel.add(logoIutLabel, BorderLayout.WEST);
		
		JPanel bottomUbsPanel = new JPanel();
		bottomUbsPanel.setOpaque(false);
		bottomUbsPanel.setLayout(new BorderLayout());
		JLabel logoUbsLabel = new JLabel(new ImageIcon(Resources.LOGO_UBS_IMAGE));
		bottomUbsPanel.add(logoUbsLabel, BorderLayout.SOUTH);
		
		logoPanel.add(bottomUbsPanel, BorderLayout.EAST);
		
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(component, BorderLayout.CENTER);
		backgroundPanel.add(logoPanel, BorderLayout.SOUTH);
		this.getContentPane().add(backgroundPanel);
		this.setVisible(true);
		pack();
		this.setLocationRelativeTo(null);
	}
	
}
