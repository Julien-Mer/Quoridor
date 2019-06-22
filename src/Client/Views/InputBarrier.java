package Client.Views;
import javax.swing.*;
public class InputBarrier extends JFrame{
	public InputBarrier() {
	}
	
	public int[] choice() {
		int[] res = new int[4];
		String x1 = JOptionPane.showInputDialog("Enter a x1");
		String y1 = JOptionPane.showInputDialog("Enter a y1");
		String x2 = JOptionPane.showInputDialog("Enter a x2");
		String y2 = JOptionPane.showInputDialog("Enter a y2");
		
		try {
			res[0] = Integer.parseInt(x1);
			res[1] = Integer.parseInt(y1);
			res[2] = Integer.parseInt(x2);
			res[3] = Integer.parseInt(y2);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this,"Entrez un nombre valide", "Quoridor",JOptionPane.PLAIN_MESSAGE);
			this.choice();
		}
		return res;
	}
}
