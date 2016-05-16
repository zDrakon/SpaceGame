package main;

import java.util.ArrayList;

import aesthetics.HealthBar;
import aesthetics.ShootCooldownBar;
import entities.Player;
import entities.Projectile;
import processing.core.PApplet;
import processing.core.PFont;

public class RunMe extends PApplet {

	String[] fontList = PFont.list();

	SpaceGame game = new SpaceGame();
	HealthBar barOne = new HealthBar(20, 500, 100, 15, 10);
	HealthBar barTwo = new HealthBar(420, 500, 100, 15, 10);

	ShootCooldownBar shootOne = new ShootCooldownBar(800, 300, 100, 15, 10);
	ShootCooldownBar shootTwo = new ShootCooldownBar(800, 450, 100, 15, 10);

	ArrayList<Projectile> bullitsOne;
	ArrayList<Projectile> bullitsTwo;

	private int sizeX = 1000, sizeY = 600;

	public void setup() {
		size(sizeX, sizeY);
		barOne.drawInitialBar(game.playerOne, this, "Red");
		barTwo.drawInitialBar(game.playerTwo, this, "RED");

		shootOne.drawInitialBar(game.playerOne, this, "GREEN");
		shootTwo.drawInitialBar(game.playerTwo, this, "green");

		game.playerOne = new Player(this, 100, 100, 0, 0, 50.0, 50.0, 100, 2);
		game.playerTwo = new Player(this, 100, 400, 0, 0, 50.0, 50.0, 100, 2);

		bullitsOne = new ArrayList<Projectile>();
		bullitsTwo = new ArrayList<Projectile>();
	}

	public void draw() {
		background(255);
		if (game.getWinner() != 0) {
			background(255);
			text("GAME OVER", 500, 300);
			return;
		}

		game.checkForWinner();

		barOne.updateHealthBar(game.playerOne, this);
		barTwo.updateHealthBar(game.playerTwo, this);

		shootOne.drawInitialBar(game.playerOne, this, "green");
		shootTwo.drawInitialBar(game.playerTwo, this, "green");

		for (int i = 0; i < bullitsOne.size(); i++) {
			shootOne.updateCooldownbar(game.playerOne.getBullet(), this);
		}
		for (int i = 0; i < bullitsTwo.size(); i++) {
			shootTwo.updateCooldownbar(game.playerTwo.getBullet(), this);
		}
		checkMoveKeysPressed();
		doBulletOneActions();
		doBulletTwoActions();

		game.playerOne.countReloadTimer();
		game.playerOne.move();
		game.playerOne.draw();

		game.playerTwo.countReloadTimer();
		game.playerTwo.move();
		game.playerTwo.draw();

	}

	public void keyPressed() {

		if (key == 'z') {
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

	public void doBulletOneActions() {
		for (int i = 0; i < bullitsOne.size(); i++) {
			Projectile b = bullitsOne.get(i);
			b.countLifetime();
			if (b.isHitting(game.playerTwo)) {
				bullitsOne.remove(i);
				game.playerTwo.damageSelf(10);
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
			if (b.isHitting(game.playerOne)) {
				bullitsTwo.remove(i);
				game.playerOne.damageSelf(10);
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
		if (keyPressed == true) {
			if (key == 'w') {
				game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 1);
			}
			if (key == 's') {
				game.playerOne.setVelocity(90 - game.playerOne.getAngle(), 0);
			}

			if (key == 'a') {
				game.playerOne.rotate(-1);
			}
			if (key == 'd') {
				game.playerOne.rotate(1);

			}
			if (key == CODED) {
				if (keyCode == UP) {
					game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 1);
				}
				if (keyCode == DOWN) {
					game.playerTwo.setVelocity(90 - game.playerTwo.getAngle(), 0);
				}
				if (keyCode == LEFT) {
					game.playerTwo.rotate(-1);
				}
				if (keyCode == RIGHT) {
					game.playerTwo.rotate(1);
				}
			}
		}
	}

}
