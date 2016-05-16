package aesthetics;

import entities.Player;
import processing.core.PApplet;

public class HealthBar extends Bar {

	public HealthBar(int x, int y, int width, int height, int paddingScale) {
		super(x, y, width, height, paddingScale);

	}

	public void updateHealthBar(Player player, PApplet app) {

		float ratio = player.getHp() / player.getMaxHp();
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale));

		if (ratio >= 0) {
			app.fill(255, 0, 0);
			app.rect(x, y, width * ratio, height);
		}
	}
}
