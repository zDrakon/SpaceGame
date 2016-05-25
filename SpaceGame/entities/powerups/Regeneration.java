package entities.powerups;

import entities.Player;
import processing.core.PApplet;

public class Regeneration extends Powerup {

	public Regeneration(PApplet app, double x, double y, double width, double height, String color) {
		super(app, x, y, width, height, 0, color);
		this.duration = 0;
	}

	public void healPlayer(Player p) {
		p.setHp(p.getHp() + (50));

		if (p.getHp() >= p.getMaxHp()) {
			p.setHp(p.getMaxHp());
		}
	}
}
