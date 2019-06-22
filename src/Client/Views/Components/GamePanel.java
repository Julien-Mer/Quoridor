package Client.Views.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import Game.ColorSquare;
import Game.Square;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private JLabel[][] fieldGrid = new JLabel[MAX_ROWS][MAX_ROWS];
    JPanel[][] panels;
    JPanel mainPanel;
    
    public GamePanel() {
        this.initComponent(false);
    }
    
    public void initComponent(boolean isVerifying) {
    	this.mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        this.mainPanel.setBackground(Color.BLACK);
        int j=0;
        this.panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++) {
            for (j = 0; j < panels[i].length; j++) {
            	this.panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                this.mainPanel.add(panels[i][j]);
            }
        }
        for (int row = 0; row < this.fieldGrid.length; row++) {
            for (int col = 0; col < this.fieldGrid[row].length; col++) {
            	int i = row / 3;
                int k = col / 3;
                if(!isVerifying) {
                	this.fieldGrid[row][col] = this.createLbl("src/resources/Images/brown.png");
                	this.panels[i][k].add(this.fieldGrid[row][col]);
                }else {
                	this.panels[i][k].add(this.fieldGrid[row][col]);

                }
            }
        
        revalidate();
        
        setLayout(new BorderLayout());
        add(this.mainPanel, BorderLayout.CENTER);
        }
    }
    
    public JLabel[][] getGridLabels(){
    	return this.fieldGrid;
    }
    
    private JLabel createLbl(String pathToImage) {
        JLabel lbl = new JLabel(new ImageIcon(pathToImage));
        lbl.setHorizontalAlignment(JTextField.CENTER);
        lbl.setEnabled(true);
        return lbl;
    }
    
   public void updateP(Square[][] grid) {
	   removeAll();
    	for(int i=0;i<grid.length;i++) {
    		for(int j=0;j<grid[i].length;j++) {
       				    			
       			if(grid[i][j].getColor()!=ColorSquare.FREE && grid[i][j].getColor()!=ColorSquare.BARRIER) { 
    				this.fieldGrid[i/2][j/2] = this.createLbl(this.getPath(grid[i][j].getColor()));
       			}
       			if(i%2 == 0 && j%2 == 0) 
       				this.fieldGrid[j/2][i/2].setBorder(getBorder(grid, i, j));
       			
    		}
    	}	
    	this.initComponent(true);
   }
   
   public boolean validSquare(int x, int y, int length) {
	   return x >= 0 && x < length && y >= 0 && y < length;
   }
   
   public MatteBorder getBorder(Square[][] grid, int x, int y) {
	   grid[2][1].setColor(ColorSquare.BARRIER);
	   grid[1][2].setColor(ColorSquare.BARRIER);
	   grid[3][2].setColor(ColorSquare.BARRIER);
	   grid[2][3].setColor(ColorSquare.BARRIER);
	   grid[2][2].setColor(ColorSquare.GREEN);
	   int right = 0, left = 0, top = 0, bottom = 0;
	   if(validSquare(x+1, y, grid.length))
		   if(grid[x+1][y].getColor().equals(ColorSquare.BARRIER))
			   right = 4;
	   if(validSquare(x-1, y, grid.length))
		   if(grid[x-1][y].getColor().equals(ColorSquare.BARRIER))
			   left = 4; 
	   if(validSquare(x, y-1, grid.length))
		   if(grid[x][y-1].getColor().equals(ColorSquare.BARRIER))
			   top = 4; 
	   if(validSquare(x, y+1, grid.length))
		   if(grid[x][y+1].getColor().equals(ColorSquare.BARRIER))
			   bottom = 4;
	   return BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK);
   }
    
   public String getPath(Color col) {
    	String res=null;
    	if(col==ColorSquare.YELLOW) res = "src/resources/Images/pion-yellow.png";
    	else if(col==ColorSquare.RED) res = "src/resources/Images/pion-red.png";
    	else if(col==ColorSquare.GREEN) res = "src/resources/Images/pion-green.png";
    	else if(col==ColorSquare.BLUE) res = "src/resources/Images/pion-blue.png";
    	else if(col==ColorSquare.FREE) res="src/resources/Images/brown.png";
    	return res;
    }
}