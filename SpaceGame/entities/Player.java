package entities;

import java.util.ArrayList;

import processing.core.PApplet;

public class Player extends Entity {

	private int powerup;
	private int level;
	private final int RELOADTIME = 10;
	private int reloadTimer;
	private int shotCounter;

	public Player(PApplet app, double x, double y, double xSpeed, double ySpeed, double width, double height,
			double angle, int maxHp, int level) {
		super(app, x, y, xSpeed, ySpeed, 0, 0, width, height);

		this.maxHp = maxHp;
		this.powerup = 0;
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

	public int getPowerup() {
		return powerup;
	}

	public void setPowerup(int p) {
		this.powerup = p;
	}

	public Projectile shoot() {
		Projectile bullet;
		bullet = new Projectile(this.getCenterX(), this.getCenterY(), 0, 0, 2, 10);
		bullet.setCenter(this.getCenterX(), this.getCenterY());
		bullet.rotate(this.getAngle());
		bullet.setVelocity(90 - this.getAngle(), 5);

		return bullet;
	}

	public ArrayList<Projectile> multiShoot() {
		ArrayList<Projectile> bullets = new ArrayList<Projectile>();

		double bulletXShift = this.width * Math.cos(Math.PI * (this.getAngle()) / 180) / 2;
		double bulletYShift = this.width * Math.sin(Math.PI * (this.getAngle()) / 180) / 2;

		Projectile upperBullet = new Projectile(this.getCenterX() + bulletXShift, this.getCenterY() + bulletYShift, 0,
				0, 2, 10);
		Projectile lowerBullet = new Projectile(this.getCenterX() - bulletXShift, this.getCenterY() - bulletYShift, 0,
				0, 2, 10);

		upperBullet.setCenter(this.getCenterX() + bulletXShift, this.getCenterY() + bulletYShift);
		lowerBullet.setCenter(this.getCenterX() - bulletXShift, this.getCenterY() - bulletYShift);

		upperBullet.rotate(this.getAngle() - 3);
		lowerBullet.rotate(this.getAngle() + 3);

		upperBullet.setVelocity(90 - this.getAngle() + 3, 5);
		lowerBullet.setVelocity(90 - this.getAngle() - 3, 5);

		bullets.add(upperBullet);
		bullets.add(lowerBullet);

		return bullets;

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

	public boolean multiShotActivated() {

		if (this.shotCounter > 500) {
			this.shotCounter = 0;
			return false;
		}
		return true;
	}

	public void count() {
		this.shotCounter++;
	}
}
