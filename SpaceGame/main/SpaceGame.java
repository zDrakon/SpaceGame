package main;

import entities.Player;

public class SpaceGame {

	Player playerOne, playerTwo;

	private int winner;

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

	public SpaceGame() {

		super();

		this.winner = 0;

	}

}