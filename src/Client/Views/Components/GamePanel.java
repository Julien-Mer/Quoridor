package Client.Views.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Client.Views.Models.Resources;

public class GamePanel extends JPanel {

	private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private JLabel[][] fieldGrid = new JLabel[MAX_ROWS][MAX_ROWS];
    JPanel[][] panels;
    JPanel mainPanel;
    
	/**
	 * Initialize the components
	 */
    public void initComponent() {
    	this.mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        this.mainPanel.setBackground(Color.WHITE);
        
        this.panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
            	this.panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                this.mainPanel.add(panels[i][j]);
            }
        }
        for (int row = 0; row < this.fieldGrid.length; row++) {
            for (int col = 0; col < this.fieldGrid[row].length; col++) {
            	int i = row / 3;
                int k = col / 3;
                this.panels[i][k].add(this.fieldGrid[row][col]);
            }
	        setLayout(new BorderLayout());
	        add(this.mainPanel, BorderLayout.CENTER);
        }
    }
    
    /**
     * Get the gridLabels
     * @return the gridLabels
     */
    public JLabel[][] getGridLabels(){
    	return this.fieldGrid;
    }
    
    /**
     * Create a JLabel with an ImageIcon
     * @param pathToImage the path to the image
     * @return the new JLabel
     */
    public JLabel createLbl(String pathToImage) {
    	JLabel lbl = null;
    	try { 
    		lbl = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(pathToImage))));
    		 lbl.setHorizontalAlignment(JTextField.CENTER);
    	     lbl.setEnabled(true);
		} catch (IOException e) {}
        return lbl;
    } 

}