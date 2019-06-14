package Client.Controllers.Helpers;
import javax.swing.*;

public class Functions {

	/**
	 * Replace the current view
	 * @param actualFrame the view to replace
	 * @param newFrame the new view
	 */
	public static void changeView(JFrame actualFrame, JFrame newFrame) {
		int x = actualFrame.getX();
		int y = actualFrame.getY();
		actualFrame.dispose();
		newFrame.setLocation(x, y);
	}
}