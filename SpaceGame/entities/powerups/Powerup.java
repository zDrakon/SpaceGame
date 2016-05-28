package entities.powerups;

import entities.Entity;
import processing.core.PApplet;

public class Powerup extends Entity {
	protected double duration;

	public Powerup(PApplet p, double x, double y, double xspeed, double yspeed, double xacceleration,
			double yacceleration) {
		super(p, x, y, xspeed, yspeed, xacceleration, yacceleration);
	}

}
