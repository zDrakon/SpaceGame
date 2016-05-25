package entities;

import processing.core.PApplet;

public class Asteroid extends Entity {

	public Asteroid(PApplet app, double x, double y, double xspeed, double yspeed, double w, double h, double angle) {
		super(p, x, y, xspeed, yspeed, w, h);
		this.angle = angle;
	}

}
