package Client.Views.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ImageButton extends JButton {
	
	private boolean isMouseOver;
	private BufferedImage image;
    
    public ImageButton(String image) {
		try { 
			this.image = ImageIO.read(getClass().getResource("/resources/" + image));
		} catch (IOException e) {}
		this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addMouseListener(new mouseAdapter());
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(this.image, 0, 0, this);
        if (this.isMouseOver || !isEnabled())
	        g.setColor(new Color(255,255,255,128));
        else
        	g.setColor(new Color(255,0,0,0));
        
        g.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
        
    }
    
    public void setMouseOver(boolean state) {
    	this.isMouseOver = state;
    }

    public class mouseAdapter extends MouseAdapter {
    	
        @Override
        public void mouseEntered(MouseEvent e) {
           	((ImageButton) e.getSource()).setMouseOver(true);
        }

        @Override
       public void mouseExited(MouseEvent e) {
        	((ImageButton) e.getSource()).setMouseOver(false);
        }

    }
    
}
