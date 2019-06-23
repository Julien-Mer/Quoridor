package Client.Views;
import javax.swing.*;

public class Message extends JFrame{

	/**
	 * Display a message on the screen
	 * @param message the message to display
	 */
	public Message(String message) {
		if(message!=null) {
			JOptionPane.showMessageDialog(this, message,"Quoridor",JOptionPane.PLAIN_MESSAGE);
			this.setVisible(false);
		}
	}
}
