package entities.powerups;

import entities.Entity;
import processing.core.PApplet;

public class Powerup extends Entity {
	protected int duration;
	protected String color;

	public Powerup(PApplet app, double x, double y, double width, double height, int duration, String color) {
		super(app, x, y, 0, 0, 0, 0, width, height);
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
