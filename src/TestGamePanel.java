import Client.Controllers.GameController;
import Client.Views.*;
import Game.*;
public class TestGamePanel {
	public static void main(String[] args) {
		
		GameController g = new GameController();
		Square[][] gridNew = new Square[17][17];
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				gridNew[i][j]  = new Square(i,j);
				gridNew[i][j].setColor(ColorSquare.FREE);
			}
		}
		gridNew[16][4].setColor(ColorSquare.BLUE);
		g.updateGame(gridNew);
	}
}
