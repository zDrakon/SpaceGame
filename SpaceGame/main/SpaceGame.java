package main;

import java.util.ArrayList;

import entities.Asteroid;
import entities.Player;
import entities.powerups.Multishot;
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

	public void checkCollision(ArrayList<Asteroid> asteroids, ArrayList<Multishot> multis,
			ArrayList<Regeneration> regens) {
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

		for (int a = 0; a < regens.size(); a++) {
			Regeneration r = regens.get(a);
			if (playerOne.isHitting(r)) {
				r.healPlayer(playerOne);
				regens.remove(r);
			}
			if (playerTwo.isHitting(r)) {
				r.healPlayer(playerTwo);
				regens.remove(r);
			}
		}
		for (int a = 0; a < multis.size(); a++) {
			Multishot m = multis.get(a);
			if (playerOne.isHitting(m)) {
				playerOne.setPowerup(1);
				multis.remove(m);
			}
			if (playerTwo.isHitting(m)) {
				playerTwo.setPowerup(1);
				multis.remove(m);
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