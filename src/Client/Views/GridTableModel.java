package Client.Views;

import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import Game.Square;
import Game.ColorSquare;

/**
 * GridTableModel : the graphical table
 */
public class GridTableModel extends AbstractTableModel {

  private int noOfRows, noOfCols;
  private Square[][] grid;
  private static final String PATH = "src/Images/";    // the folder
  // the image are directly created from the files
  private String imageFree= "brown.png";
  private String imageRed= "pion-red.png";
  private String imageYellow= "pion-yellow.png";
  private String imageBarrier="barrier.png" ;
  private String imageGreen = "pion-green.png";
  private String imageBlue = "pion-blue.png";
  
 /*
  * Constructor
  * @param grid : the table to display
  */
  public GridTableModel(Square[][] grid) {
    this.grid = grid;
    noOfRows = this.grid.length;
    noOfCols = this.grid[0].length;
  }

// Implementing the tree inherited abstract methods:
  
   public int getRowCount() {
    return(noOfRows);
  }
  public int getColumnCount() {
    return(noOfCols);
  }

  public Object getValueAt(int r,int c) {
    Object result = new Object();
    Square sq = grid[r][c];
    if (sq.isFree()) result= new ImageIcon(PATH + imageFree);
    else if (sq.isBarrier()) result= new ImageIcon(PATH + imageBarrier);
    else if (sq.getColor() == ColorSquare.RED) result= new ImageIcon(PATH + imageRed); 
    else if(sq.getColor() == ColorSquare.YELLOW) result = new ImageIcon(PATH + imageYellow);
    else if(sq.getColor() == ColorSquare.GREEN) result = new ImageIcon(PATH + imageGreen);
    else if(sq.getColor() == ColorSquare.BLUE) result = new ImageIcon(PATH + imageBlue);
    return result;
  }
 
 
  /**
   * get the name of the column
   * @param c : the number of the column
   * @return a String for the name of the column
   */
  public String getColumnName(int c){
    return (new Integer(c).toString());
  }
  
   /**
   * get the class of the object at column c
   * @param c : the number of the column
   * @return the class of the object at column c
   */
   public Class getColumnClass(int c) {
      return this.getValueAt(0, c).getClass();
   }
}