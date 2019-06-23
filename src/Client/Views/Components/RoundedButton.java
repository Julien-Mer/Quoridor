package Client.Views.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RoundedButton extends JButton {
	
	/**
	 * The color of the button
	 */
	private Color color;
	
	/**
	 * The state of the button
	 */
	private boolean isMouseOver;
	
	/**
	 * The radius of the button
	 */
    private int radius;
    
	/**
	 * Creates a rounded button
	 * @param radius the radius of the button
	 * @param color the color of the button
	 */
    public RoundedButton(int radius, Color color) {
    	this.color = color;
        this.radius = radius;
        this.setFont(new Font("Dialog", Font.PLAIN, 30));
        
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addMouseListener(new mouseAdapter());
    }

    /**
     * Paint the component
     */
    public void paintComponent(Graphics g){
    	Graphics2D g2 = (Graphics2D)g.create();
        if (this.isMouseOver && isEnabled())
        	g2.setPaint(new Color(color.getRed(), color.getGreen(), color.getBlue(),210));
          
        else
            g2.setPaint(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), this.radius, this.radius);
        super.paintComponent(g);
    }
    
    /**
     * Button mouseOver state changer
     * @param state the new state
     */
    public void setMouseOver(boolean state) {
    	this.isMouseOver = state;
    }

    public class mouseAdapter extends MouseAdapter {
    	
        @Override
        public void mouseEntered(MouseEvent e) {
           	((RoundedButton) e.getSource()).setMouseOver(true);
        }

        @Override
       public void mouseExited(MouseEvent e) {
        	((RoundedButton) e.getSource()).setMouseOver(false);
        }

    }
    
}