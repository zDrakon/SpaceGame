package aesthetics;

import processing.core.PApplet;

public class Bar {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int paddingScale;
	protected int round;
	protected PApplet app;

	public Bar(PApplet app, int x, int y, int width, int height, int paddingScale, int round) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.paddingScale = paddingScale;
		this.round = round;
		this.app = app;
	}

	public void drawInitialBar(String color) {
		this.app.fill(0, 0, 0);
		this.app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale),
				round);

		if (color.equalsIgnoreCase("Red")) {
			this.app.fill(255, 0, 0);
		}

		if (color.equalsIgnoreCase("Green")) {
			this.app.fill(0, 255, 0);
		}
		if (color.equalsIgnoreCase("Blue")) {
			this.app.fill(0, 0, 255);
		}
		app.rect(x, y, width, height, round);

	}

	public void draw() {
		app.rect(x - paddingScale, y - paddingScale, width + (2 * paddingScale), height + (2 * paddingScale), round);
	}

}
