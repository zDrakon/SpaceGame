package entities;

import main.Powerup;
import processing.core.PApplet;

public class Player extends Entity {

	private Powerup powerup;
	private int level;

	public Player(PApplet app, double x, double y, double xSpeed, double ySpeed, double width, double height, int hp,
			int level) {
		super(app, x, y, xSpeed, ySpeed, 0, 0, width, height);
		this.hp = hp;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Powerup getPowerup() {
		return powerup;
	}

	public void applyPowerup(Powerup powerup) {
		this.powerup = powerup;
	}

}
