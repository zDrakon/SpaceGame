package main;

import entities.Player;
import processing.core.PApplet;

public class RunMe extends PApplet {

	SpaceGame game = new SpaceGame();

	private int sizeX = 1000, sizeY = 600;

	public void setup() {
		size(sizeX, sizeY);
		game.playerOne = new Player(this, 100, 100, 0, 0, 50.0, 50.0, 100, 1);
		game.playerTwo = new Player(this, 200, 100, 0, 0, 50.0, 50.0, 100, 1);
	}

	public void draw() {
		background(255);
		game.playerOne.move();
		game.playerOne.draw();
		game.playerTwo.move();
		game.playerTwo.draw();
	}

	public SpaceGame getGame() {
		return game;
	}

	public void setGame(SpaceGame game) {
		this.game = game;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "SpaceGame" });
	}

}
