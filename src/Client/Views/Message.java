package Client.Views;
import javax.swing.*;

public class Message extends JFrame{
	private String err;
	public Message(String error) {
		if(error!=null) {
			this.err=error;
			this.initComponent();
		}
	}
	
	public void initComponent() {
		JOptionPane.showMessageDialog(this,this.err,"Quoridor",JOptionPane.PLAIN_MESSAGE);
	}
}
