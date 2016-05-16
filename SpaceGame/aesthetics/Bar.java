package aesthetics;

import entities.Player;
import processing.core.PApplet;

public class Bar {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int paddingScale;

	public Bar(int x, int y, int width, int height, int paddingScale) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.paddingScale = paddingScale;
	}

	public void drawInitialBar(Player player, PApplet app, String color) {
		app.fill(0, 0, 0);
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale));

		if (color.equalsIgnoreCase("Red")) {
			app.fill(255, 0, 0);
		}

		if (color.equalsIgnoreCase("Green")) {
			app.fill(0, 255, 0);
		}
		if (color.equalsIgnoreCase("Blue")) {
			app.fill(0, 0, 255);
		}
		app.rect(x, y, width, height);

	}

}
