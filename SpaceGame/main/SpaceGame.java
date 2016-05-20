package main;

import java.util.ArrayList;

import entities.Asteroid;
import entities.Player;

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

	public void checkCollision(ArrayList<Asteroid> asteroids) {
		if (playerOne.isHitting(playerTwo) || playerOne.isHitting(playerTwo)) {

			collidePlayer(playerOne);
			collidePlayer(playerTwo);

		}

		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			if (playerOne.isHitting(a)) {
				collidePlayer(playerOne);
			}
			if (playerTwo.isHitting(a)) {
				collidePlayer(playerTwo);
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