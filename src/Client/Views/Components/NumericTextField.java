package Client.Views.Components;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class NumericTextField extends JTextField {

	boolean doub;
	
	public NumericTextField(boolean doub) {
		this.doub = doub;
	}
	
    @Override
    public void processKeyEvent(KeyEvent ev) {
    	if(ev.getKeyChar() == ',')
    		ev.setKeyChar('.');
        if (ev.getKeyCode() == ev.VK_DELETE || ev.getKeyCode() == ev.VK_RIGHT || ev.getKeyCode() == ev.VK_LEFT || ev.getKeyCode() == ev.VK_BACK_SPACE || Character.isDigit(ev.getKeyChar()) || ev.getKeyChar() == '.' && doub && !this.getText().contains(".")) 
            super.processKeyEvent(ev);
        
        	ev.consume();
        return;
    }

    /**
     * Converts the text content to a long value
     * @return the double value of the number
     */
    public Double getNumberDouble() {
    	Double result = null;
        String text = this.getText();
        if (text != null && !text.equals("")) {
            result = Double.valueOf(text);
        }
        if(result == null)
        	result = 0.0;
        return result;
    }
    
    /**
     * Converts the text content to a long value
     * @return the long value of the number
     */
    public Long getNumberLong() {
    	Long result = null;
        String text = this.getText();
        if (text != null && !text.equals("")) {
            result = Long.valueOf(text);
        }
        if(result == null)
        	result = (long) 0;
        return result;
    }
    
}

