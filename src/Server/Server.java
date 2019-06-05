package Server;

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
		// TODO - implement Server.Server
		throw new UnsupportedOperationException();
	}

	public void start() {
		// TODO - implement Server.start
		throw new UnsupportedOperationException();
	}

	public void endOfGame() {
		// TODO - implement Server.endOfGame
		throw new UnsupportedOperationException();
	}

	public String rules() {
		// TODO - implement Server.rules
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param object
	 */
	public Server(Object object) {
		// TODO - implement Server.Server
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param client
	 */
	public void sendSave(Socket client) {
		// TODO - implement Server.sendSave
		throw new UnsupportedOperationException();
	}

	public void movePlayer() {
		// TODO - implement Server.movePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param socket
	 */
	public void addPlayer(Socket socket) {
		// TODO - implement Server.addPlayer
		throw new UnsupportedOperationException();
	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

}