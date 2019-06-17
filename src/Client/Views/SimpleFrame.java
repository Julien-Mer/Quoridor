package Client.Views;

import javax.swing.JFrame;
/**
 * SimpleFrame permet d'avoir les méthodes de gestion de la fenêtre
 */
public class SimpleFrame extends JFrame {
  public SimpleFrame()
  {
     this.setSize(500,450);/*@\label{setsize:line}@*/
     this.setLocation(200,200);/*@\label{setloc:line}@*/
     // quitte l'application quand on ferme la fenetre
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*@\label{closeOp:line}@*/
  }

  /**
  * Makes the frame visible.
  */
  public void showIt(){
    this.setVisible(true);
  }

  /**
  * Makes the frame visible and sets the title text.
  * @param title : the title of the window
  */
  public void showIt(String title){
    this.setTitle(title);
    this.setVisible(true);
  }

  /**
     *Makes the frame visible and sets the title text
  and the position of the window.
  @param title : the title of the window
  @param x : x position
  @param y : y position
  */
  public void showIt(String title,int x, int y){
    this.setTitle(title);
    this.setLocation(x,y);
    this.setVisible(true);
  }

  /**
   *  Makes the frame invisible
   */
  public void hideIt(){
    this.setVisible(false);
  }
}
