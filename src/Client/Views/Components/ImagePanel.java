package Client.Views.Components;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagePanel extends JPanel{
	private Image img;

	public ImagePanel(String img) {
		if(img != null) 
			this.img = new ImageIcon(img).getImage();
	}

	public ImagePanel(Image img) {
		this.img = img;
	}

	public void paintComponent(Graphics g) {
		if(this.img != null)
			g.drawImage(img, 0, 0, null);
	}
}


