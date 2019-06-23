package Server;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import Game.ColorSquare;
import Game.Square;
import Model.*;

public class Player implements Serializable {

	/**
	 * The color of the player
	 */
	private Color color;
	
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The socket used by the player
	 */
	private transient DataListener listener;
	
	/**
	 * The barriers placed by the player
	 */
	private ArrayList<Square> barriers;
	
	/**
	 * The position of the player
	 */
	private Square position;

	/**
	 * The number of barriers
	 */
	private int nbrBarriers;
	
	/**
	 * The server of the player
	 */
	private GameServer server;
	
	/**
	 * Creates a player
	 * @param name the name of the player
	 * @param color the color of the player
	 * @param listener the listener of the player
	 * @param position the position of the player
	 * @param server the server of the player
	 */
	public Player(String name, Color color, DataListener listener, Square position, GameServer server) {
		this.name = name;
		this.color = color;
		this.listener = listener;
		this.position = position;
		this.position.setColor(this.getColor());
		this.server = server;
		this.barriers = new ArrayList<Square>();
		this.nbrBarriers = Board.BARRIERS;
	}
	
	/**
	 * The play method of the player
	 */
	public void play() {
		System.out.println("Play");
	}
	
	/**
	 * Get the listener used by the player
	 * @return the listener used by the player
	 */
	public DataListener getListener() {
		return this.listener;
	}
	
	/**
	 * Set the listener used by the player
	 * @param listener the listener used by the player
	 */
	public void setListener(DataListener listener) {
		this.listener = listener;
	}

	/**
	 * Get the name of the player
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the color of the player
	 * @return the color of the player
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Get the position of the player
	 * @return the position of the player
	 */
	public Square getPosition() {
		return this.position;
	}
	
	/**
	 * Get the number of barriers left
	 * @return the number of barriers left
	 */
	public int getNumberBarriersLeft() {
		return this.nbrBarriers - this.barriers.size()/2;
	}
	
	/**
	 * Get the server of the listener
	 * @return the server of the listener
	 */
	public GameServer getServer() {
		return this.server;
	}
	
	/**
	 * Set the server of the player
	 * @param server the server of the player
	 */
	public void setServer(GameServer server) {
		this.server = server;
	}
	
	/**
	 * Moves the player
	 * @param square the new position of the player
	 */
	public void movePlayer(Square square) {
		this.getPosition().setColor(ColorSquare.FREE);
		this.position = square;
		square.setColor(this.getColor());
		this.server.nextTurnPlayer();
	}
	
	/**
	 * Add a barrier on a square
	 * @param square1 the first square to place a barrier
	 * @param square2 the second square to place a barrier
	 */
	public void addBarrier(Square square1, Square square2) {
		this.barriers.add(square1);
		this.barriers.add(square2);
		square1.setColor(ColorSquare.BARRIER);
		square2.setColor(ColorSquare.BARRIER);
		this.server.nextTurnPlayer();
	}

}