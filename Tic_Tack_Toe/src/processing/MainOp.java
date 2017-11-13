package processing;

import java.util.Scanner;

import graphics.Panel;

public class MainOp {
	
	private Panel panel;
	private Board board;
	private boolean computerOp = false, running = true;
	public int turns = 1;
	private final char SIGN1 = 'X', SIGN2 = 'O';
	private char currentSign = SIGN1;

	private static Scanner in = new Scanner(System.in);

	public MainOp(Panel p) {
		panel = p;
		board = new Board();
		
		in.close();
	}

	/**
	 * Player versus Player game mode
	 */
	private void PVP() {
			panel.updateBoard(board.getBoard());

			if (board.checkBoard(currentSign))
				endGame();

			turns++;

			if (turns > 9)
				endGame();
			
			if (currentSign == SIGN1)
				currentSign = SIGN2;
			else
				currentSign = SIGN1;

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

			/*
			System.out.println("<---------------------------->");
			System.out.println("turn " + turns);
			System.out.println(currentSign + "'s turn \n");
			System.out.println("Enter row and column number");
			panel.updateBoard(board.getBoard());
			*/

			if (currentSign == SIGN2)
				computer.turn();

			if (board.checkBoard(currentSign))
				break;

			turns++;

			if (turns > 9)
				break;
		}
	}
	
	private void run() {
		if (computerOp)
			PVC();
		else
			PVP();
	}

	private void endGame() {
		running = false;
		panel.updateBoard(board.getBoard());

		if (turns > 9)
			System.out.println("TIE");
		else
			System.out.println(currentSign + " WON");
	}

	public void input(int r, int c) {
		if (running) {
			board.putSign(r, c, currentSign);
		
			System.out.printf("Turn: %d \nPlayer: %s \n", turns, currentSign);
			
			run();

			panel.updateBoard(board.getBoard());
		}
	}
}