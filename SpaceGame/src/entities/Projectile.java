package entities;

public class Projectile extends Thing {

	public Projectile(int x, int y, int xSpeed, int ySpeed, int width, int height) {
		super(p, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.hp = 1;
	}

}
