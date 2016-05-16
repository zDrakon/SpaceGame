package main;

import entities.Player;

public class SpaceGame {

	Player playerOne, playerTwo;

	private int winner;
	private int constraintX, constraintY; // TODO: make these the player
											// boundaries with a line so they
											// don't escape

	public int getWinner() {

		return winner;

	}

	public void checkForWinner() {

		if (playerOne.getHp() == 0) {

			winner = 1;

			System.out.println("Player " + winner + " has won!");

		} else if (playerTwo.getHp() == 0) {

			winner = 2;

			System.out.println("Player " + winner + " has won!");

		}

	}

	public void preventEscape() {

	}

	public SpaceGame() {

		super();

		this.winner = 0;

	}

}