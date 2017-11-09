package processing;

import java.util.Scanner;

import graphics.Panel;

public class Main {

	// TODO: Connect main and JPanel
	
	private Panel panel;
	private Board board;
	public int turns = 1;
	private final char SIGN1 = 'X', SIGN2 = 'O';
	private char currentSign = SIGN2;

	private static Scanner in = new Scanner(System.in);

	public Main(Panel p, Board b) {
		panel = p;
		board = b;
			
		System.out.println("Enter preffered game mode: "
				+ "(1 - PVP/2 - PVC)");
		if (in.nextInt() == 1)
			PVP();
		else
			PVC();

		endGame();
	}

	/**
	 * Player versus Player game mode
	 */
	private void PVP() {
		while (true) {
			if (currentSign == SIGN1)
				currentSign = SIGN2;
			else
				currentSign = SIGN1;

			System.out.println("<---------------------------->");
			System.out.println("turn " + turns);
			System.out.println(currentSign + "'s turn");
			System.out.println("");
			System.out.println("Enter row and column number");
			board.printBoard();

			board.putSign(in.nextInt() - 1, in.nextInt() - 1, currentSign);

			if (board.checkBoard(currentSign))
				break;

			turns++;

			if (turns > 9)
				break;
		}
	}

	/**
	 * Player versus Computer game mode
	 */
	private void PVC() {
		Computer computer = new Computer(SIGN1, SIGN2, board);
		
		while (true) {
			if (currentSign == SIGN1)
				currentSign = SIGN2;
			else
				currentSign = SIGN1;

			System.out.println("<---------------------------->");
			System.out.println("turn " + turns);
			System.out.println(currentSign + "'s turn \n");
			System.out.println("Enter row and column number");
			board.printBoard();

			if (currentSign == SIGN1)
				board.putSign(in.nextInt() - 1, in.nextInt() - 1, currentSign);
			else
				computer.turn();

			if (board.checkBoard(currentSign))
				break;

			turns++;

			if (turns > 9)
				break;
		}
	}

	private void endGame() {
		System.out.println("");
		board.printBoard();
		System.out.println("");

		if (turns > 9)
			System.out.println("TIE");
		else
			System.out.println(currentSign + " WON");
	}
}