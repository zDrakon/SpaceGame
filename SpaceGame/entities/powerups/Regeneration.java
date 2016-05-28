package entities.powerups;

import entities.Player;
import processing.core.PApplet;

public class Regeneration extends Powerup {

	public Regeneration(PApplet app, double x, double y, double width, double height, double angle) {
		super(app, x, y, width, height, 0, 0);
		this.duration = 0;
		this.angle = angle;
	}

	public void healPlayer(Player p) {
		p.setHp(p.getHp() + (10));

		if (p.getHp() >= p.getMaxHp()) {
			p.setHp(p.getMaxHp());
		}
	}
}
