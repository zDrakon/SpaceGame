package entities;

import entities.powerups.Powerup;
import processing.core.PApplet;

public class Player extends Entity {

	private Powerup powerup;
	private int level;
	private Projectile bullet;
	private final int RELOADTIME = 10;
	private int reloadTimer;
	private int collideTimer;

	public Player(PApplet app, double x, double y, double xSpeed, double ySpeed, double width, double height,
			double angle, int maxHp, int level) {
		super(app, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.level = level;
		this.reloadTimer = 0;
		this.angle = angle;

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
		this.bullet = new Projectile(this.getCenterX(), this.getCenterY(), 0, 0, 1, 10);
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
		}

		return false;

	}

	public void countReloadTimer() {
		reloadTimer++;
	}

	public void takeDamage(int amt) {
		currentHp = currentHp - amt;
	}

	public void preventEscape() {
		if (this.getCenterX() > 950 - (Math.sqrt(2) * this.width / 2)) {
			this.setCenterX((int) (950 - (Math.sqrt(2) * this.width / 2)));
		}
		if (this.getCenterX() < 50 + (Math.sqrt(2) * this.width / 2)) {
			this.setCenterX((int) (50 + 1 + (Math.sqrt(2) * this.width / 2)));
		}

		if (this.getCenterY() > 650 - (Math.sqrt(2) * this.height / 2)) {
			this.setCenterY((int) (650 - (Math.sqrt(2) * this.height / 2)));
		}

		if (this.getCenterY() < 50 + (Math.sqrt(2) * this.height / 2)) {
			this.setCenterY((int) (50 + 1 + (Math.sqrt(2) * this.height / 2)));
		}
	}

	public void countCollideTimer() {
		collideTimer++;
	}

	public boolean isHittingAsteroid(Asteroid a) {

		if (this.getDistanceTo(a.getCenterX(), a.getCenterY(), (int) this.getLeftSideX(),
				(int) this.getTopSideY()) <= a.getWidth() / 2) {
			return true;
		}
		if (this.getDistanceTo(a.getCenterX(), a.getCenterY(), (int) this.getLeftSideX(),
				(int) this.getBottomSideY()) <= a.getWidth() / 2) {
			return true;
		}
		if (this.getDistanceTo(a.getCenterX(), a.getCenterY(), (int) this.getRightSideX(),
				(int) this.getTopSideY()) <= a.getWidth() / 2) {
			return true;
		}
		if (this.getDistanceTo(a.getCenterX(), a.getCenterY(), (int) this.getRightSideX(),
				(int) this.getBottomSideY()) <= a.getWidth() / 2) {
			return true;
		}
		return false;
	}

}
