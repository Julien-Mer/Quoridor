package Client.Views.Models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	
	public TopPanel() {		
		this.setOpaque(false);
		this.setBackground(new Color(0,0,0,0));
		this.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = null;
		try { 
			titleLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(Resources.TITLE_IMAGE))));
		} catch (IOException e) {}

		titlePanel.add(titleLabel);
		titlePanel.setOpaque(false);
		this.add(titlePanel, BorderLayout.CENTER);
		
		JPanel delimiterSpacedPanel = new JPanel();
		delimiterSpacedPanel.setOpaque(false);
		delimiterSpacedPanel.setLayout(new BorderLayout(2,1));
		
		JPanel delimiterPanel = new JPanel();
		delimiterPanel.setBackground(Resources.BORDER);
		delimiterPanel.setPreferredSize(new Dimension(1, 2));
		
		JPanel invisibleSpace = new JPanel();
		invisibleSpace.setPreferredSize(new Dimension(1, 30));
		invisibleSpace.setOpaque(false);
		
		delimiterSpacedPanel.add(delimiterPanel, BorderLayout.NORTH);
		delimiterSpacedPanel.add(invisibleSpace, BorderLayout.SOUTH);
		
		this.add(delimiterSpacedPanel, BorderLayout.SOUTH);
	}
	
}
