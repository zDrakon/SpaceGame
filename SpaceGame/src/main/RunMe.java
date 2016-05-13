package main;

import entities.Player;
import processing.core.PApplet;

public class RunMe extends PApplet {

	SpaceGame game = new SpaceGame();

	private int sizeX = 1000, sizeY = 600;

	public void setup() {
		size(sizeX, sizeY);
		game.playerOne = new Player(this, 100, 100, 0, 0, 50.0, 50.0, 100, 1);
		game.playerTwo = new Player(this, 900, 500, 0, 0, 50.0, 50.0, 100, 1);
	}

	public void draw() {
		background(255);
		game.playerOne.move();
		game.playerOne.draw();
		game.playerTwo.move();
		game.playerTwo.draw();
	}

	public void keyPressed() {
		if (key == 'w') {
			game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 1);
		}
		if (key == 's') {
			game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 0);
		}

		if (key == 'a') {
			game.playerOne.rotate(-5);
		}
		if (key == 'd') {
			game.playerOne.rotate(5);

		}
		if (key == CODED) {
			if (keyCode == UP) {
				game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 1);
			}
			if (keyCode == DOWN) {
				game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 0);
			}
			if (keyCode == LEFT) {
				game.playerTwo.rotate(-5);
			}
			if (keyCode == RIGHT) {
				game.playerTwo.rotate(5);
			}
		}
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
