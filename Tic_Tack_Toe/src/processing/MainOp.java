package processing;

import java.util.Scanner;

import graphics.Panel;

public class MainOp {

	private Panel panel;
	private Board board;
	private Computer comp;
	private boolean computerOp, running;
	public int turns = 1;
	private final char SIGN1 = 'X', SIGN2 = 'O';
	private char currentSign = SIGN1;

	private static Scanner in = new Scanner(System.in);

	public MainOp(Panel p) {
		panel = p;
		board = new Board();
		comp = new Computer(SIGN1, SIGN2, board);

		System.out.print("Pick mode: ");

		if (in.nextInt() == 1)
			computerOp = false;
		else
			computerOp = true;

		running = true;

		in.close();
	}

	/**
	 * Generate player action
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 */
	private void playerTurn(int r, int c) {
		panel.updateBoard(board.getBoard());

		board.putSign(r, c, currentSign);

		if (board.checkBoard(currentSign))
			endGame();
		else if (turns == 9)
			endGame();
		else
			nextTurn();

	}

	/**
	 * Generate computer action
	 */
	private void computerTurn() {
		if (currentSign == SIGN2)
			comp.turn();

		if (board.checkBoard(currentSign))
			endGame();
		else if (turns == 9)
			endGame();
		else
			nextTurn();
	}

	private void endGame() {
		running = false;
		panel.updateBoard(board.getBoard());

		if (turns == 9)
			System.out.println("TIE");
		else
			System.out.println(currentSign + " WON");
	}
	
	private void nextTurn() {
		if (currentSign == SIGN1)
			currentSign = SIGN2;
		else
			currentSign = SIGN1;
		
		turns++;
	}

	public void input(int r, int c) {
		if (running) {
			playerTurn(r, c);
			
			if (computerOp)
				computerTurn();

			panel.updateBoard(board.getBoard());
		}
	}
}