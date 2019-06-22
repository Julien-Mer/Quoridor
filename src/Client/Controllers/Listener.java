package Client.Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import Client.Client;
import Client.Controllers.*;
import Client.Controllers.Helpers.*;
import Client.Views.Game;
import Client.Views.InputBarrier;
import Client.Views.Message;

public class Listener extends MouseAdapter implements ActionListener {
	private HomeController homeController;
	private GameController gameController;
	private NewGameController newGameController;
	private GamePanelController gamePanelController;
	private String name;
	private int nbIA;
	private int nbJ;
	private int difficultyIA;
	private boolean terminal;
	/**
	 * Construct the ActionListener for the HomeController
	 * @param homeController the HomeController
	 */
	public Listener(HomeController homeController) {
		this.homeController = homeController;
	}

	/**
	 * Construct the ActionListener for the GameController
	 * @param gameController the GameController
	 */
	public Listener(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * Construct the ActionListener for the NewGameController
	 * @param newGameController the NewGameController
	 */
	public Listener(NewGameController newGameController) {
		this.newGameController = newGameController;
	}
	
	/**
	 * Construct the MouseListener for the GamePanelController
	 * @param gamePanelController the GamePanelController
	 */
	public Listener(GamePanelController gamePanelController) {
		this.gamePanelController = gamePanelController;
	}

	/**
	 * ActionPerform method inherited of ActionListener
	 * @param event the ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		if(this.homeController!=null) {
			if(event.getSource()==this.homeController.getView().getNewGameBtn()) {
				NewGameController ngc = new NewGameController();
				Functions.changeView(this.homeController.getView(), ngc.getView());
			}
			if(event.getSource()==this.homeController.getView().getResumeGameBtn()) {
				GameController gc = new GameController();
				Functions.changeView(this.homeController.getView(),gc.getView());
			}
			if(event.getSource()==this.homeController.getView().getLearningModeBtn()) {
				//TODO
			}
		}
		
		if(this.newGameController!=null) {
			if(event.getSource()==this.newGameController.getView().getRemoveButtonPlayer()) {
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==3) {
					this.newGameController.getView().setRemoveButtonPlayer(false);
					this.newGameController.getView().changeLabelPlayer(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())-1);
					this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())-1);
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) {
						this.newGameController.getView().setRemoveButtonRobot(false);
					}else {
						this.newGameController.getView().setRemoveButtonRobot(true);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1) {
						this.newGameController.getView().setAddButtonRobot(false);
					}else {
						this.newGameController.getView().setAddButtonRobot(true);
					}
				}else {
					this.newGameController.getView().setRemoveButtonPlayer(true);
					this.newGameController.getView().setAddButtonPlayer(true);
					this.newGameController.getView().changeLabelPlayer(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())-1);
					this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())-1);
				}
			}
			
			if(event.getSource()==this.newGameController.getView().getAddButtonPlayer()) {
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==3) {
					this.newGameController.getView().setAddButtonPlayer(false);
					this.newGameController.getView().changeLabelPlayer(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())+1);
					this.newGameController.getView().setAddButtonRobot(true);
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) {
						this.newGameController.getView().setRemoveButtonRobot(false);
					}else {
						this.newGameController.getView().setRemoveButtonRobot(true);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==3) {
						this.newGameController.getView().setAddButtonRobot(false);
					}else {
						this.newGameController.getView().setAddButtonRobot(true);
					}
					
				}else {
					this.newGameController.getView().setAddButtonPlayer(true);
					this.newGameController.getView().setRemoveButtonPlayer(true);
					this.newGameController.getView().changeLabelPlayer(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())+1);
					this.newGameController.getView().setAddButtonRobot(true);
				}
			}
			
			if(event.getSource()==this.newGameController.getView().getAddButtonRobot()) {
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==2) {
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) {
						this.newGameController.getView().setRemoveButtonRobot(true);
						this.newGameController.getView().setAddButtonRobot(false);
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())+1);
					}
				}
				
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==3) {
					if((Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1))  {
						this.newGameController.getView().setRemoveButtonRobot(true);
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())+1);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==2) {
						this.newGameController.getView().setAddButtonRobot(false);
					}
				}
				
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==4) {
					if((Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==2))  {
						this.newGameController.getView().setRemoveButtonRobot(true);
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())+1);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==3) {
						this.newGameController.getView().setAddButtonRobot(false);
					}
				}
			}
			
			if(event.getSource()==this.newGameController.getView().getRemoveButtonRobot()) {
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==2) {
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1) {
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())-1);
						this.newGameController.getView().setRemoveButtonRobot(false);
						this.newGameController.getView().setAddButtonRobot(true);
					}
				}
				
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==3) {
					if((Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==2)) {
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())-1);
						this.newGameController.getView().setAddButtonRobot(true);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) {
						this.newGameController.getView().setRemoveButtonRobot(false);
					}
				}
				
				if(Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText())==4) {
					if((Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==1) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==2) || (Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==3)) {
						this.newGameController.getView().changeLabelRobot(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())-1);
						this.newGameController.getView().setAddButtonRobot(true);
					}
					if(Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText())==0) {
						this.newGameController.getView().setRemoveButtonRobot(false);
					}
				}	
			}
			
			if(event.getSource()==this.newGameController.getView().getConfirmBtn()) {
				if(this.newGameController.getView().getNameTextField().getText().matches("\\w+\\.?")) {
					this.name = this.newGameController.getView().getNameTextField().getText();
					this.nbIA = Integer.parseInt(this.newGameController.getView().getCountLblRobot().getText());
					this.nbJ = Integer.parseInt(this.newGameController.getView().getCountLblPlayer().getText());
					if(this.newGameController.getView().getChoiceLvl().equals("Facile")) {
						this.difficultyIA = 1;
					} else if(this.newGameController.getView().getChoiceLvl().equals("Moyen")) {
						this.difficultyIA = 2;
					}
					if(this.newGameController.getView().getChoiceLvl().equals("Difficile")){
						this.difficultyIA = 3;
					}
					if(this.newGameController.getView().getTerm().isSelected()) {
						this.terminal = true;
					}else {
						this.terminal = false;
					}
					GameController g = new GameController();
					Client.client.newGame(this.nbJ, this.nbIA, this.name, this.difficultyIA);
					//Client.client.setTerminal(terminal);
				}else {
					Message message = new Message("Veuillez entrez un nom valide s'il vous plait");
				}
			}
		}
		
		if(this.gameController!=null) {
			if(event.getSource()==this.gameController.getView().getSaveBtn()) {
				Message msg = new Message("Partie sauvegardee!");
				this.gameController.saveGame();
			}
			if(event.getSource()==this.gameController.getView().getBarrierBtn()) {
				InputBarrier barr = new InputBarrier();
				int[] res = barr.choice();
				//Client.client.getPlayer().placeBarrier(res[0],res[1],res[2],res[3]);
			}
		}
	}
	
	public void mouseClicked(MouseEvent evt) {
		if(this.gamePanelController!=null) {
			for(int i=0;i<this.gamePanelController.getGrid().length;i++) {
				for(int j=0;j<this.gamePanelController.getGrid().length;j++) {
					if(evt.getSource()==this.gamePanelController.getLbl(i,j)) {
						//Client.client.getPlayer().movePlayer(i,j);
					}
				}
			}
		}
	}
}