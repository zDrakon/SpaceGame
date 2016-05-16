package aesthetics;

import entities.Projectile;
import processing.core.PApplet;

public class ShootCooldownBar extends Bar {

	// TODO: fix the coooldown bars

	public ShootCooldownBar(int x, int y, int width, int height, int paddingScale, int round) {
		super(x, y, width, height, paddingScale, round);
	}

	public void updateCooldownbar(Projectile bullet, PApplet app) {
		float ratio = bullet.getHp() / bullet.getMaxHp();
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale), round);

		if (ratio >= 0) {
			app.fill(0, 255, 0);
			app.rect(x, y, width * ratio, height, round);
		}
	}

}
