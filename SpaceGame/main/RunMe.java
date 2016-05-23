package main;

import java.util.ArrayList;

import aesthetics.Bar;
import aesthetics.HealthBar;
import entities.Asteroid;
import entities.Player;
import entities.Projectile;
import processing.core.PApplet;
import processing.core.PFont;

public class RunMe extends PApplet {

	String[] fontList = PFont.list();

	SpaceGame game = new SpaceGame();
	HealthBar healthbarOne;
	HealthBar healthbarTwo;

	Bar borderRight;
	Bar borderLeft;
	Bar borderUp;
	Bar borderDown;

	Boolean[] pressedKeys;

	ArrayList<Asteroid> asteroids;

	// ShootCooldownBar shootOne = new ShootCooldownBar(this, 800, 300, 100, 15,
	// 10, 8);
	// ShootCooldownBar shootTwo = new ShootCooldownBar(this, 800, 450, 100, 15,
	// 10, 8);

	ArrayList<Projectile> bullitsOne;
	ArrayList<Projectile> bullitsTwo;

	private int sizeX = 1000, sizeY = 800;

	public void setup() {
		asteroids = new ArrayList<Asteroid>();

		for (int i = 0; i < 50; i++) {
			Asteroid a = new Asteroid(this, 200 + Math.random() * 500, 200 + Math.random() * 300, 0, 0, 50, 50,
					Math.random() * Math.PI);
			asteroids.add(a);
		}

		game.playerOne = new Player(this, 110, 110, 0, 0, 30, 30, Math.PI, 100, 2);
		game.playerTwo = new Player(this, 890, 590, 0, 0, 30, 30, 0, 100, 2);

		bullitsOne = new ArrayList<Projectile>();
		bullitsTwo = new ArrayList<Projectile>();

		healthbarOne = new HealthBar(this, 80, 670, 100, 10, 6, 8);
		healthbarTwo = new HealthBar(this, 820, 670, 100, 10, 6, 8);

		borderRight = new Bar(this, 950, 50, 1, 600, 0, 0);
		borderLeft = new Bar(this, 50, 50, 1, 600, 0, 0);
		borderUp = new Bar(this, 50, 50, 900, 1, 0, 0);
		borderDown = new Bar(this, 50, 650, 900, 1, 0, 0);

		size(sizeX, sizeY);
		healthbarOne.drawInitialBar("Red");
		healthbarTwo.drawInitialBar("RED");

		pressedKeys = new Boolean[8];
		for (int i = 0; i < pressedKeys.length; i++) {
			pressedKeys[i] = false;
		}

	}

	public void draw() {
		background(255);
		if (game.getWinner() != 0) {
			background(255);
			text("GAME OVER!" + " PLAYER " + game.getWinner() + "  WINS", 400, 300);
			return;
		}

		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			a.draw();
		}

		game.checkForWinner();

		healthbarOne.updateHealthBar(game.playerOne, this);
		healthbarTwo.updateHealthBar(game.playerTwo, this);

		borderRight.draw();
		borderLeft.draw();
		borderUp.draw();
		borderDown.draw();

		checkMoveKeysPressed();
		doBulletOneActions();
		doBulletTwoActions();

		doActions(game.playerOne, game.playerTwo);
		doActions(game.playerTwo, game.playerOne);

		game.checkCollision(asteroids);
	}

	public void doActions(Player p1, Player p2) {
		p1.countReloadTimer();
		p1.move();
		p1.preventEscape();
		p1.draw();
	}

	public void keyPressed() {

		if (keyPressed == true) {
			if (key == 'r') {
				if (game.playerOne.canShoot()) {
					game.playerOne.shoot();
					bullitsOne.add(game.playerOne.getBullet());
				}
			}
			if (key == 'p') {
				if (game.playerTwo.canShoot()) {
					game.playerTwo.shoot();
					bullitsTwo.add(game.playerTwo.getBullet());

				}
			}
			if (key == 'w') {

				pressedKeys[0] = true;
			}
			if (key == 's') {

				pressedKeys[1] = true;
			}

			if (key == 'a') {

				pressedKeys[2] = true;
			}
			if (key == 'd') {

				pressedKeys[3] = true;
			}
			if (key == CODED) {
				if (keyCode == UP) {

					pressedKeys[4] = true;
				}
				if (keyCode == DOWN) {

					pressedKeys[5] = true;
				}
				if (keyCode == LEFT) {

					pressedKeys[6] = true;
				}
				if (keyCode == RIGHT) {

					pressedKeys[7] = true;
				}
			}
		}

	}

	public void keyReleased() {
		if (key == 'w') {

			pressedKeys[0] = false;
		}
		if (key == 's') {

			pressedKeys[1] = false;
		}

		if (key == 'a') {

			pressedKeys[2] = false;
		}
		if (key == 'd') {

			pressedKeys[3] = false;
		}
		if (key == CODED) {
			if (keyCode == UP) {

				pressedKeys[4] = false;
			}
			if (keyCode == DOWN) {

				pressedKeys[5] = false;
			}
			if (keyCode == LEFT) {

				pressedKeys[6] = false;
			}
			if (keyCode == RIGHT) {

				pressedKeys[7] = false;
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "SpaceGame" });
	}

	public void doBulletOneActions() {
		for (int i = 0; i < bullitsOne.size(); i++) {
			Projectile b = bullitsOne.get(i);
			b.countLifetime();
			if (b.isOutOfBounds()) {
				bullitsOne.remove(i);
			}
			if (b.isHitting(game.playerTwo)) {
				bullitsOne.remove(i);
				game.playerTwo.damageSelf(2);
			}
			for (int a = asteroids.size() - 1; a >= 1; a--) {
				if (b.isHitting(asteroids.get(a))) {
					bullitsOne.remove(i);
				}
			}
			if (b.isAlive() != false) {
				b.move();
				b.draw();
			} else {
				bullitsOne.remove(i);
			}

		}
	}

	public void doBulletTwoActions() {
		for (int i = 0; i < bullitsTwo.size(); i++) {
			Projectile b = bullitsTwo.get(i);
			b.countLifetime();
			if (b.isOutOfBounds()) {
				bullitsTwo.remove(i);
			}
			if (b.isHitting(game.playerOne)) {
				bullitsTwo.remove(i);
				game.playerOne.damageSelf(2);
			}
			for (int a = asteroids.size() - 1; a >= 1; a--) {
				if (b.isHitting(asteroids.get(a))) {
					bullitsTwo.remove(i);
				}
			}
			if (b.isAlive() != false) {
				b.move();
				b.draw();
			} else {
				bullitsTwo.remove(i);
			}

		}
	}

	public void checkMoveKeysPressed() {

		if (pressedKeys[0] == true) {
			game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 1);

		}
		if (pressedKeys[1] == true) {
			game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 0);

		}

		if (pressedKeys[2] == true) {
			game.playerOne.rotate(-1);

		}
		if (pressedKeys[3] == true) {
			game.playerOne.rotate(1);

		}

		if (pressedKeys[4] == true) {
			game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 1);

		}
		if (pressedKeys[5] == true) {
			game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 0);

		}
		if (pressedKeys[6] == true) {
			game.playerTwo.rotate(-1);

		}
		if (pressedKeys[7] == true) {
			game.playerTwo.rotate(1);

		}

	}

}
