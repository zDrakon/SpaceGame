package entities;

import main.Powerup;
import processing.core.PApplet;

public class Player extends Entity {

	private Powerup powerup;
	private int level;
	private Projectile bullet;
	private final int RELOADTIME = 5;
	private int reloadTimer;

	public Player(PApplet app, double x, double y, double xSpeed, double ySpeed, double width, double height, int hp,
			int level) {
		super(app, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.hp = hp;
		this.level = level;
		this.reloadTimer = 0;

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Powerup getPowerup() {
		return powerup;
	}

	public void applyPowerup(Powerup powerup) {
		this.powerup = powerup;
	}

	public void shoot() {
		this.bullet = new Projectile(this.getCenterX(), this.getCenterY(), 0, 0, 5, 20);
		this.bullet.setCenter(this.getCenterX(), this.getCenterY());
		this.bullet.rotate(this.getAngle());
		this.bullet.setVelocity(90 - this.getAngle(), 5);
	}

	public Projectile getBullet() {
		return this.bullet;
	}

	public boolean canShoot() {
		if (this.reloadTimer >= this.RELOADTIME) {
			reloadTimer = 0;
			return true;
		} else {
			reloadTimer++;
			return false;
		}
	}

}
