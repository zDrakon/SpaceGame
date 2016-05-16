package entities;

public class Projectile extends Entity {

	public Projectile(double x, double y, double xSpeed, double ySpeed, double width, double height) {
		super(p, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.currentHp = 100;
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

}
