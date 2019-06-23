package Client.Views.Models;

import java.awt.Color;

public class Resources {
	
	public final static Object[] themes = new Object[] {"Classique", "Moderne", "Iutiste", "Console" };
	public static String theme = "Moderne";
	
	public static void refreshResources() {
		switch(theme) {
			case "Classique": 
				BORDER = Color.BLACK;
				TEXT_COLOR = Color.BLACK;
				BTN_COLOR = Color.BLACK;
				BTN_TEXT_COLOR = Color.WHITE;
				break;
			case "Moderne":
				BORDER = new Color(215, 215, 215);
				TEXT_COLOR = Color.WHITE;
				BTN_COLOR = Color.WHITE;
				BTN_TEXT_COLOR = Color.BLACK;
				break;
			case "Iutiste":
				BORDER = new Color(215, 215, 215);
				TEXT_COLOR = Color.BLACK;
				BTN_COLOR = Color.BLACK;
				BTN_TEXT_COLOR = Color.WHITE;
				break;
		}
		BACKGROUND_IMAGE = "src/resources/" + theme + "/background.jpg";
		TITLE_IMAGE = "src/resources/" + theme + "/title.png";
		LOGO_IUT_IMAGE = "src/resources/logo-iut-vannes.png";
		LOGO_UBS_IMAGE = "src/resources/logo-ubs.png";
		LOGO_IUT_BIG_IMAGE = "src/resources/logo-iut-big-vannes.png";
		
		GREEN_PAWN_SQUARE_IMAGE = "src/resources/" + theme + "/square-pawn-green.png";
		RED_PAWN_SQUARE_IMAGE = "src/resources/" + theme + "/square-pawn-red.png";
		YELLOW_PAWN_SQUARE_IMAGE = "src/resources/" + theme + "/square-pawn-yellow.png";
		BLUE_PAWN_SQUARE_IMAGE = "src/resources/" + theme + "/square-pawn-blue.png";
		SQUARE_IMAGE = "src/resources/" + theme + "/square.png";
		POSSIBILITY_SQUARE_IMAGE = "src/resources/" + theme + "/square-possibility.png";

		GREEN_PAWN_IMAGE = "src/resources/" + theme + "/pawn-green.png";
		RED_PAWN_IMAGE = "src/resources/" + theme + "/pawn-red.png";
		YELLOW_PAWN_IMAGE = "src/resources/" + theme + "/pawn-yellow.png";
		BLUE_PAWN_IMAGE = "src/resources/" + theme + "/pawn-blue.png";
	}
	
	public static Color BORDER =  null;
	public static Color TEXT_COLOR =  null;
	public static Color BTN_COLOR =  null;
	public static Color BTN_TEXT_COLOR =  null;
	
	public static String BACKGROUND_IMAGE = null;
	public static String PANEL_PLAYERS_IMAGE = null;
	
	public static String TITLE_IMAGE = null;
	public static String LOGO_IUT_IMAGE = null;
	public static String LOGO_UBS_IMAGE = null;
	public static String LOGO_IUT_BIG_IMAGE = null;
	
	public static String GREEN_PAWN_SQUARE_IMAGE = null;
	public static String RED_PAWN_SQUARE_IMAGE = null;
	public static String YELLOW_PAWN_SQUARE_IMAGE = null;
	public static String BLUE_PAWN_SQUARE_IMAGE = null;
	public static String SQUARE_IMAGE = null;
	public static String POSSIBILITY_SQUARE_IMAGE = null;
	
	public static String GREEN_PAWN_IMAGE = null;
	public static String RED_PAWN_IMAGE = null;
	public static String YELLOW_PAWN_IMAGE = null;
	public static String BLUE_PAWN_IMAGE = null;
	
}
