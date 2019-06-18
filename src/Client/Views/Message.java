package Client.Views;
import javax.swing.*;

public class Message extends JFrame{
	private static final long serialVersionUID = -5278808222561806778L;
	private String err;
	public Message(String error) {
		if(error!=null) {
			this.err=error;
			JOptionPane.showMessageDialog(this,this.err,"Quoridor",JOptionPane.PLAIN_MESSAGE);
		}
	}
}
