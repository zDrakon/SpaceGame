package aesthetics;

import entities.Projectile;
import processing.core.PApplet;

public class ShootCooldownBar extends Bar {

	public ShootCooldownBar(int x, int y, int width, int height, int paddingScale) {
		super(x, y, width, height, paddingScale);
	}

	public void updateCooldownbar(Projectile bullet, PApplet app) {
		float ratio = bullet.getHp() / bullet.getMaxHp();
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale));

		if (ratio >= 0) {
			app.fill(0, 255, 0);
			app.rect(x, y, width * ratio, height);
		}
	}

}
