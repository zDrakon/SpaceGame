package aesthetics;

import entities.Player;
import processing.core.PApplet;

public class HealthBar extends Bar {

	public HealthBar(PApplet app, int x, int y, int width, int height, int paddingScale, int round) {
		super(app, x, y, width, height, paddingScale, round);

	}

	public void updateHealthBar(Player player, PApplet app) {

		float ratio = player.getHp() / player.getMaxHp();
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale), round);

		if (ratio >= 0) {
			app.fill(255, 0, 0);
			app.rect(x, y, width * ratio, height, round);
		}
	}
}
