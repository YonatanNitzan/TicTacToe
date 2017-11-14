package processing;

import java.util.Scanner;

import javax.swing.JOptionPane;

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
		
		panel.getFrame().reOpen();

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
		if (board.putSign(r, c, currentSign)) {
			
			panel.updateBoard(board.getBoard());
			
			if (board.checkBoard(currentSign))
				endGame();
			else if (turns == 9)
				endGame();
			else
				nextTurn();
		}
	}

	/**
	 * Generate computer action
	 */
	private void computerTurn() {
		// TODO: Add a delay before the computer's action.
		if (currentSign == SIGN2) {
			comp.turn();
	
			panel.updateBoard(board.getBoard());
			
			if (board.checkBoard(currentSign))
				endGame();
			else
				nextTurn();
		}
	}

	private void endGame() {
		running = false;

		if (turns == 9)
			JOptionPane.showMessageDialog(panel,
					"TIE",
					"Game over!", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(panel,
					currentSign + " WON",
					"Game over!", JOptionPane.WARNING_MESSAGE);
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