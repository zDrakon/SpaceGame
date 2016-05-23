package entities;

public class Projectile extends Entity {

	public Projectile(double x, double y, double xSpeed, double ySpeed, double width, double height) {
		super(p, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.maxHp = 100;
		this.currentHp = this.maxHp;
	}

	public void setCenter(double x, double y) {
		this.setXPosition(x - (this.width / 2));
		this.setYPosition(y - (this.height / 2));
	}

	public void countLifetime() {
		this.currentHp--;
	}

	public boolean isAlive() {
		if (this.currentHp <= 0) {
			return false;
		}
		return true;
	}

	public boolean isOutOfBounds() {

		if (this.getCenterX() > 950 - (Math.sqrt(2) * this.getWidth() / 2)) {
			return true;
		}
		if (this.getCenterX() < 50 + (Math.sqrt(2) * this.getWidth() / 2)) {
			return true;
		}

		if (this.getCenterY() > 650 - (Math.sqrt(2) * this.getHeight() / 2)) {
			return true;
		}

		if (this.getCenterY() < 50 + (Math.sqrt(2) * this.getHeight() / 2)) {
			return true;
		}
		return false;
	}

}
