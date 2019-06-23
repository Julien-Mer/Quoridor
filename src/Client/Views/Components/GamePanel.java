package Client.Views.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class GamePanel extends JPanel {

	private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private JLabel[][] fieldGrid = new JLabel[MAX_ROWS][MAX_ROWS];
    JPanel[][] panels;
    JPanel mainPanel;
    
    public GamePanel() {
    	
    }
    
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
    
    public JLabel[][] getGridLabels(){
    	return this.fieldGrid;
    }
    
    public JLabel createLbl(String pathToImage) {
        JLabel lbl = new JLabel(new ImageIcon(pathToImage));
        lbl.setHorizontalAlignment(JTextField.CENTER);
        lbl.setEnabled(true);
        return lbl;
    } 

}