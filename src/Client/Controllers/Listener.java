package Client.Controllers;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTable;

import Client.Client;
import Client.HumanPlayer;
import Client.Controllers.*;
import Client.Controllers.Helpers.*;
import Client.Views.*;
import Client.Views.Models.BasicController;
import Model.File;

public class Listener extends MouseAdapter implements ActionListener {
	private BasicController controller;
	
	private String name;
	private int nbIA;
	private int nbJ;
	private int difficultyIA;
	private boolean terminal;
	
	/**
	 * Construct the ActionListener for the BasicController
	 * @param controller the BasicController
	 */
	public Listener(BasicController controller) {
		this.controller = controller;
	}
	/**
	 * ActionPerform method inherited of ActionListener
	 * @param event the ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		Component component = (Component) this.controller.getView().getComponent();
		if(this.controller instanceof HomeController) {
			if(event.getSource() == ((Home)component).getNewGameBtn()) {
				NewGameController ngc = new NewGameController();
				Functions.changeView(controller.getView(), ngc.getView());
			}
			if(event.getSource() == ((Home)component).getResumeGameBtn()) {
				try {
					Client.client.sendSave(File.readObject(File.SAVE_FILE));
				} catch (IOException e) {
					Client.client.showMessage("Impossible de restaurer la partie", true);
				}
			}
			if(event.getSource() == ((Home)component).getLearningModeBtn()) {
				Client.client.newPedagoGame();
			}
		}
		
		if(this.controller instanceof NewGameController) {
			if(event.getSource()==((NewGame)component).getRemoveButtonPlayer()) {
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==3) {
					((NewGame)component).setRemoveButtonPlayer(false);
					((NewGame)component).changeLabelPlayer(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())-1);
					((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())-1);
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) {
						((NewGame)component).setRemoveButtonRobot(false);
					}else {
						((NewGame)component).setRemoveButtonRobot(true);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1) {
						((NewGame)component).setAddButtonRobot(false);
					}else {
						((NewGame)component).setAddButtonRobot(true);
					}
				}else {
					((NewGame)component).setRemoveButtonPlayer(true);
					((NewGame)component).setAddButtonPlayer(true);
					((NewGame)component).changeLabelPlayer(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())-1);
					((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())-1);
				}
			}
			
			if(event.getSource()==((NewGame)component).getAddButtonPlayer()) {
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==3) {
					((NewGame)component).setAddButtonPlayer(false);
					((NewGame)component).changeLabelPlayer(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())+1);
					((NewGame)component).setAddButtonRobot(true);
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) {
						((NewGame)component).setRemoveButtonRobot(false);
					}else {
						((NewGame)component).setRemoveButtonRobot(true);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==3) {
						((NewGame)component).setAddButtonRobot(false);
					}else {
						((NewGame)component).setAddButtonRobot(true);
					}
					
				}else {
					((NewGame)component).setAddButtonPlayer(true);
					((NewGame)component).setRemoveButtonPlayer(true);
					((NewGame)component).changeLabelPlayer(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())+1);
					((NewGame)component).setAddButtonRobot(true);
				}
			}
			
			if(event.getSource()==((NewGame)component).getAddButtonRobot()) {
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==2) {
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) {
						((NewGame)component).setRemoveButtonRobot(true);
						((NewGame)component).setAddButtonRobot(false);
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())+1);
					}
				}
				
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==3) {
					if((Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1))  {
						((NewGame)component).setRemoveButtonRobot(true);
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())+1);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==2) {
						((NewGame)component).setAddButtonRobot(false);
					}
				}
				
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==4) {
					if((Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==2))  {
						((NewGame)component).setRemoveButtonRobot(true);
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())+1);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==3) {
						((NewGame)component).setAddButtonRobot(false);
					}
				}
			}
			
			if(event.getSource()==((NewGame)component).getRemoveButtonRobot()) {
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==2) {
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1) {
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())-1);
						((NewGame)component).setRemoveButtonRobot(false);
						((NewGame)component).setAddButtonRobot(true);
					}
				}
				
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==3) {
					if((Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==2)) {
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())-1);
						((NewGame)component).setAddButtonRobot(true);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) {
						((NewGame)component).setRemoveButtonRobot(false);
					}
				}
				
				if(Integer.parseInt(((NewGame)component).getCountLblPlayer().getText())==4) {
					if((Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==1) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==2) || (Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==3)) {
						((NewGame)component).changeLabelRobot(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())-1);
						((NewGame)component).setAddButtonRobot(true);
					}
					if(Integer.parseInt(((NewGame)component).getCountLblRobot().getText())==0) {
						((NewGame)component).setRemoveButtonRobot(false);
					}
				}	
			}
			
			if(event.getSource()==((NewGame)component).getConfirmBtn()) {
				if(((NewGame)component).getNameTextField().getText().matches("\\w+\\.?")) {
					this.name = ((NewGame)component).getNameTextField().getText();
					this.nbIA = Integer.parseInt(((NewGame)component).getCountLblRobot().getText());
					this.nbJ = Integer.parseInt(((NewGame)component).getCountLblPlayer().getText());
					if(((NewGame)component).getChoiceLvl().getSelectedItem().toString().equals("Facile")) {
						this.difficultyIA = 1;
					} else if(((NewGame)component).getChoiceLvl().getSelectedItem().toString().equals("Moyen")) {
						this.difficultyIA = 2;
					} else if(((NewGame)component).getChoiceLvl().getSelectedItem().toString().equals("Difficile")){
						this.difficultyIA = 3;
					}
					if(((NewGame)component).getTheme().getSelectedItem().toString().equals("Console")) 
						Client.client.setTerminal(true);
					else
						Functions.setTheme(((NewGame)component).getTheme().getSelectedItem().toString());
					
					
					Client.client.newGame(this.nbJ, this.nbIA, this.name, this.difficultyIA);
				}else {
					Message message = new Message("Veuillez entrez un nom valide s'il vous plait");
				}
			}
		}
		
		if(this.controller instanceof GameController) {
			if(event.getSource()==((Game)component).getSaveBtn()) {
				if(!Client.finished) {
					Client.client.showMessage("Partie sauvegardée !", true);
					Client.client.saveGame(Client.client.getPlayer().getServer());
				} else 
					Client.client.showMessage("La partie est déjà terminée !", true);
			}
			if(event.getSource()==((Game)component).getBarrierBtn()) {
				int[] res = Functions.choiceBarriers(((Game)component));
				try {
					((HumanPlayer)Client.client.getPlayer()).placeBarrier(res[0], res[1], res[2], res[3]);
				} catch (IOException e) {
					Client.client.showMessage("Impossible d'envoyer l'action au serveur", true);
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent evt) {
		Component component = (Component) this.controller.getView().getComponent();
		if(this.controller instanceof GameController) {
			for(int i=0;i<((Game)component).getGamePanel().getGridLabels().length;i++) {
				for(int j=0;j<((Game)component).getGamePanel().getGridLabels()[0].length;j++) {
					if(evt.getSource()==((Game)component).getLbl(i,j)) {
						try {
							((HumanPlayer)Client.client.getPlayer()).movePlayer(j*2,i*2);
						} catch (IOException e) {
							Client.client.showMessage("Impossible d'envoyer l'action au serveur", true);
						}
					}
				}
			}
		}
	}
	
}