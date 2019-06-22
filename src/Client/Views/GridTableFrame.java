package Client.Views;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;

import Game.ColorSquare;
import Game.Square;
/**
 * GridTableFrame : frame for GridTable
 */
public class GridTableFrame {
  private final int rowHeight = 40;  //en pixel
  private JTable tab;
  private ColorSquare color; // La couleur du joueur qui perçoit la fenêtre
  
  /**
   * Constructor
   * It creates the GridTableModel
   * @param grid : the data table to display
   */
  public GridTableFrame(Square[][] grid) {
    // set the grid size
  }
  
  public void setColor(ColorSquare color) {
	  this.color = color;
  }
  
  public ColorSquare getColor() {
	  return this.color;
  }
  
  public JTable getTable() {
	  return this.tab;
  }
  
  public void refresh(Square[][] grid, Color color) {
	  GridTableModel otmodel = new GridTableModel(grid);
	  this.tab = new JTable(otmodel);
	  // to adjust some parameters
	  tab.setShowGrid(true);
	  // color for the grid lines
	  tab.setGridColor(Color.BLUE);
	  tab.setRowHeight(rowHeight);
	  JScrollPane SP = new JScrollPane(tab);

  }
}