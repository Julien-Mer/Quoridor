package Server;

import java.net.Socket;
import java.util.LinkedList;

import Client.*;

public class Server {

	private int NBBARRIERS;
	private LinkedList<Player> timeLine;
	private int nbJoueurs;

	/**
	 * 
	 * @param nbJoueurs
	 */
	public Server(int nbJoueurs) {

	}

	public void start() {

	}

	public void endOfGame() {

	}

	public String rules() {
		return null;
	}

	/**
	 * 
	 * @param object
	 */
	public Server(Object object) {

	}

	/**
	 * 
	 * @param client
	 */
	public void sendSave(Socket client) {

	}

	public void movePlayer() {

	}

	/**
	 * 
	 * @param socket
	 */
	public void addPlayer(Socket socket) {

	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

}