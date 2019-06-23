package Client.Views.Components;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel{
	private Image img;

	/**
	 * Creates an ImagePanel
	 * @param img the image
	 */
	public ImagePanel(String img) {
		if(img != null) 
			try { 
				this.img = ImageIO.read(getClass().getResource(img));
			} catch (IOException e) {}
	}

	/**
	 * Creates an ImagePanel
	 * @param img the image
	 */
	public ImagePanel(Image img) {
		this.img = img;
	}

	/**
	 * Paint the component
	 */
	public void paintComponent(Graphics g) {
		if(this.img != null)
			g.drawImage(img, 0, 0, null);
	}
}


