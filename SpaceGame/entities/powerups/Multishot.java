package entities.powerups;

import processing.core.PApplet;

public class Multishot extends Powerup {

	private int counter = 0;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Multishot(PApplet app, double x, double y, double width, double height) {
		super(app, x, y, width, height, 0, 0);
		this.duration = 1000;
	}

}