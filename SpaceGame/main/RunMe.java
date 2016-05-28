package main;

import java.util.ArrayList;
import java.util.Random;

import aesthetics.Bar;
import aesthetics.HealthBar;
import entities.Asteroid;
import entities.Player;
import entities.Projectile;
import entities.powerups.Multishot;
import entities.powerups.Regeneration;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class RunMe extends PApplet {

	String[] fontList = PFont.list();

	SpaceGame game = new SpaceGame();
	HealthBar healthbarOne;
	HealthBar healthbarTwo;

	Random rand;

	PImage fighterOne;
	PImage fighterTwo;
	PImage asteroid;
	PImage bulletOne;
	PImage bulletTwo;
	PImage trash;
	PImage multiImage;

	Bar borderRight;
	Bar borderLeft;
	Bar borderUp;
	Bar borderDown;

	Boolean[] pressedKeys;

	ArrayList<Asteroid> asteroids;

	ArrayList<Regeneration> regens;
	ArrayList<Multishot> multis;

	// ShootCooldownBar shootOne = new ShootCooldownBar(this, 800, 300, 100, 15,
	// 10, 8);
	// ShootCooldownBar shootTwo = new ShootCooldownBar(this, 800, 450, 100, 15,
	// 10, 8);

	ArrayList<Projectile> bullitsOne;
	ArrayList<Projectile> bullitsTwo;

	private int sizeX = 1000, sizeY = 800;

	public void setup() {

		fighterOne = loadImage("../assets/FighterOne.png");
		fighterTwo = loadImage("../assets/FighterTwo.png");
		asteroid = loadImage("../assets/Asteroid.png");
		bulletOne = loadImage("../assets/BulletOne.png");
		bulletTwo = loadImage("../assets/BulletTwo.png");
		trash = loadImage("../assets/trash.png");
		multiImage = loadImage("../assets/Upgrade.png");

		fighterOne.resize(30, 30);
		fighterTwo.resize(30, 30);
		asteroid.resize(50, 50);
		bulletOne.resize(2, 10);
		bulletTwo.resize(2, 10);
		trash.resize(30, 30);
		multiImage.resize(10, 10);

		rand = new Random();
		asteroids = new ArrayList<Asteroid>();
		regens = new ArrayList<Regeneration>();
		multis = new ArrayList<Multishot>();

		for (int i = 0; i < 15; i++) {
			Asteroid a = new Asteroid(this, 200 + rand.nextInt(500), 200 + rand.nextInt(300), 0, 0, 50, 50,
					rand.nextDouble() * Math.PI);
			asteroids.add(a);
		}

		for (int i = 0; i < 10; i++) {
			Regeneration r = new Regeneration(this, 100 + rand.nextInt(500), 200 + rand.nextInt(300), 5, 5,
					rand.nextDouble() * Math.PI);
			for (int a = 0; a < asteroids.size(); a++) {
				if (r.isHitting(asteroids.get(a))) {

					r.setCenterX(200 + rand.nextInt(500));
					r.setCenterY(200 + rand.nextInt(300));
				}
			}

			regens.add(r);
		}

		for (int i = 0; i < 10; i++) {
			Multishot m = new Multishot(this, 100 + rand.nextInt(500), 200 + rand.nextInt(300), 5, 5);

			for (int a = 0; a < asteroids.size(); a++) {
				if (m.isHitting(asteroids.get(a))) {

					m.setCenterX(200 + rand.nextInt(500));
					m.setCenterY(200 + rand.nextInt(300));
				}
			}
			multis.add(m);
		}

		game.playerOne = new Player(this, 110, 110, 0, 0, 30, 30, Math.PI, 100, 2);
		game.playerTwo = new Player(this, 890, 590, 0, 0, 30, 30, 0, 100, 2);

		game.playerOne.setImage(fighterOne);
		game.playerTwo.setImage(fighterTwo);

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

		fill(0);
		rect(50, 50, 900, 600);

		if (game.getWinner() != 0) {
			background(255);

			text("GAME OVER!" + " PLAYER " + game.getWinner() + "  WINS", 400, 300);
			return;
		}

		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			a.setImage(asteroid);
			a.draw();
		}

		for (int i = 0; i < regens.size(); i++) {
			Regeneration r = regens.get(i);
			r.setImage(trash);
			r.draw();
		}

		for (Multishot m : multis) {

			m.setImage(multiImage);
			m.draw();
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

		doActions(game.playerOne);
		doActions(game.playerTwo);

		game.checkCollision(asteroids, multis, regens);
	}

	public void doActions(Player p) {
		if (p.getPowerup() == 1) {
			p.count();
			if (!p.multiShotActivated()) {
				p.setPowerup(0);
			}
		}
		p.countReloadTimer();
		p.move();
		p.preventEscape();
		p.draw();
	}

	public void keyPressed() {

		if (keyPressed == true) {
			if (key == 'r') {
				if (game.playerOne.canShoot()) {
					if (game.playerOne.getPowerup() == 0) {
						Projectile b = game.playerOne.shoot();
						bullitsOne.add(b);
					} else if (game.playerOne.getPowerup() == 1) {
						ArrayList<Projectile> bullets = game.playerOne.multiShoot();
						for (Projectile b : bullets) {
							bullitsOne.add(b);
						}

					}
				}
			}
			if (key == 'p') {
				if (game.playerTwo.canShoot()) {
					if (game.playerTwo.getPowerup() == 0) {
						Projectile b = game.playerTwo.shoot();
						bullitsTwo.add(b);
					} else if (game.playerTwo.getPowerup() == 1) {
						ArrayList<Projectile> bullets = game.playerTwo.multiShoot();
						for (Projectile b : bullets) {
							bullitsTwo.add(b);
						}

					}
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
				bullitsOne.remove(b);
			}
			if (b.isHitting(game.playerTwo)) {
				bullitsOne.remove(b);
				game.playerTwo.damageSelf(2);
			}

			for (int a = asteroids.size() - 1; a >= 0; a--) {
				if (b.isHitting(asteroids.get(a))) {
					bullitsOne.remove(b);
				}
			}
			if (b.isAlive() != false) {
				b.setImage(bulletOne);
				b.move();
				b.draw();
			} else {
				bullitsOne.remove(b);
			}

		}
	}

	public void doBulletTwoActions() {
		for (int i = 0; i < bullitsTwo.size(); i++) {
			Projectile b = bullitsTwo.get(i);
			b.countLifetime();
			if (b.isOutOfBounds()) {
				bullitsTwo.remove(b);
			}
			if (b.isHitting(game.playerOne)) {
				bullitsTwo.remove(b);
				game.playerOne.damageSelf(2);
			}
			for (int a = asteroids.size() - 1; a >= 0; a--) {
				if (b.isHitting(asteroids.get(a))) {
					bullitsTwo.remove(b);
				}
			}
			if (b.isAlive() != false) {
				b.setImage(bulletTwo);
				b.move();
				b.draw();
			} else {
				bullitsTwo.remove(b);
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
