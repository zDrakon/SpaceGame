package main;

import java.util.ArrayList;

import entities.Asteroid;
import entities.Player;
import entities.powerups.Powerup;
import entities.powerups.Regeneration;

public class SpaceGame {

	Player playerOne, playerTwo;

	private int winner;

	public int getWinner() {

		return winner;

	}

	public void checkForWinner() {

		if (playerOne.getHp() <= 0) {

			winner = 2;

			System.out.println("Player " + winner + " has won!");

		} else if (playerTwo.getHp() <= 0) {

			winner = 1;

			System.out.println("Player " + winner + " has won!");

		}

	}

	public SpaceGame() {

		super();

		this.winner = 0;

	}

	public void checkCollision(ArrayList<Asteroid> asteroids, ArrayList<Powerup> powerups) {
		if (playerOne.isHitting(playerTwo) || playerOne.isHitting(playerTwo)) {

			collidePlayer(playerOne);
			collidePlayer(playerTwo);

		}

		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			if (playerOne.isHittingAsteroid(a)) {
				collidePlayer(playerOne);
			}
			if (playerTwo.isHittingAsteroid(a)) {
				collidePlayer(playerTwo);
			}
		}

		for (int a = 0; a < powerups.size(); a++) {
			Regeneration r = (Regeneration) powerups.get(a);
			if (playerOne.isHitting(r)) {
				r.healPlayer(playerOne);
				powerups.remove(r);
			}
			if (playerTwo.isHitting(r)) {
				r.healPlayer(playerTwo);
				powerups.remove(r);
			}
		}
	}

	public void collidePlayer(Player p1) {
		p1.damageSelf(5);

		p1.subtractPosition(p1.getVelocity());
		p1.subtractPosition(p1.getVelocity());

		p1.setVelocity(270 - p1.getAngle(), p1.getSpeed());
		p1.rotate(150 + Math.random() * 60);
	}

}