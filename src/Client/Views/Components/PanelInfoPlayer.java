package Client.Views.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Controllers.Helpers.Functions;
import Client.Views.Models.Resources;
import Server.Player;

public class PanelInfoPlayer extends JPanel {

	/**
	 * Creates a PanelInfoPlayer
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public PanelInfoPlayer(Player player1, Player player2) {
		super();
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(800,525));
		this.refreshPanel(player1, player2);
	}
	
	/**
	 * Get the panel of a player
	 * @param player the player concerned
	 * @return the panel of the player
	 */
	public JPanel getPanel(Player player) {
		ImagePanel infosPlayer = new ImagePanel(Resources.PANEL_PLAYERS_IMAGE);
		if(Resources.PANEL_PLAYERS_IMAGE == null)
			this.setOpaque(false);
		infosPlayer.setLayout(new FlowLayout(FlowLayout.CENTER,20,50));
		infosPlayer.setOpaque(false);
		if(player != null) {
			JLabel nameLabel = new JLabel("<html> Nom: " + player.getName() + " [" + player.getPosition().getX()/2 + ", " + player.getPosition().getY()/2 + "]<br> Barrière(s) restante(s): " + player.getNumberBarriersLeft() + "</html>");
			nameLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
			nameLabel.setForeground(Resources.TEXT_COLOR);
			JLabel pawnLabel = null;
			try {
				pawnLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(Functions.getPathPlayer(player.getColor())))));
			} catch (IOException e) { }
			infosPlayer.add(nameLabel);
			infosPlayer.add(pawnLabel);
		}
		return infosPlayer;
	}
	
	/**
	 * Refresh players panels
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public void refreshPanel(Player player1, Player player2) {
		this.removeAll();
		this.setLayout(new GridLayout(2,1));
		this.add(this.getPanel(player1));
		this.add(this.getPanel(player2));
		this.revalidate();
		this.repaint();
	}
	

}
