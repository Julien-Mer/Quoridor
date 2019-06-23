package Client.Views.Models;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	 * @param dimension the dimension of the view
	 * @param component the component of the view
	 */
	public BasicView(Dimension dimension, Component component) {
		this.component = component;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImagePanel backgroundPanel = new ImagePanel(Resources.BACKGROUND_IMAGE);
		backgroundPanel.setLayout(new BorderLayout());
		this.setPreferredSize(dimension);
		this.setResizable(false);
		setTitle("Quoridor");
		this.getContentPane().setLayout(new BorderLayout());
		try {
			this.setIconImage((new ImageIcon(ImageIO.read(getClass().getResource(Resources.LOGO_IUT_BIG_IMAGE)))).getImage());
		} catch (IOException e) { }
		
		TopPanel topPanel = new TopPanel();
		
		JPanel logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.setLayout(new BorderLayout());
		JLabel logoIutLabel = null;
		try {
			logoIutLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(Resources.LOGO_IUT_IMAGE))));
		} catch (IOException e) { }
		logoPanel.add(logoIutLabel, BorderLayout.WEST);
		
		JPanel bottomUbsPanel = new JPanel();
		bottomUbsPanel.setOpaque(false);
		bottomUbsPanel.setLayout(new BorderLayout());
		JLabel logoUbsLabel = null;
		try {
			logoUbsLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(Resources.LOGO_UBS_IMAGE))));
		} catch (IOException e) { 	}
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
