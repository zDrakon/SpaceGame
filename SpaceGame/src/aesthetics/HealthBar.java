package aesthetics;

import entities.Player;
import processing.core.PApplet;

public class HealthBar {
	private int x;
	private int y;
	private int width;
	private int height;
	private int paddingScale;

	public HealthBar(int x, int y, int width, int height, int paddingScale) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.paddingScale = paddingScale;
	}

	public void drawInitialHealthBar(Player player, PApplet app) {
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale));
		app.fill(255, 0, 0);
		app.rect(x, y, width, height);

	}

	public void update(Player player, PApplet app) {

		float ratio = player.getHp() / player.getMaxHp();
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale));

		app.fill(255, 0, 0);
		app.rect(x, y, width * ratio, height);
	}

}
