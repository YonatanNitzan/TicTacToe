package processing;

import javax.swing.JOptionPane;

import graphics.GamePanel;

public class MainOp {

	private GamePanel panel;
	private Board board;
	private Computer comp;
	private boolean computerOp = false, running = true;
	public int turns = 1;
	private final char SIGN1 = 'X', SIGN2 = 'O';
	private char currentSign = SIGN1;

	public MainOp(GamePanel p) {
		panel = p;
		board = new Board();
		comp = new Computer(SIGN1, SIGN2, board);
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
			
			if (board.checkBoard(currentSign) || turns == 9)
				endGame(board.checkBoard(currentSign));
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
				endGame(true);
			else
				nextTurn();
		}
	}

	private void endGame(boolean win) {
		running = false;

		if (win)
			JOptionPane.showMessageDialog(panel,
					currentSign + " WON",
					"Game over!", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(panel,
					"TIE",
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
	
	public void restart() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board.eraseSign(i, j);
		
		turns = 1;
		currentSign = SIGN1;
		running = true;
		
		panel.updateBoard(board.getBoard());
	}

	public boolean isComputerOp() {
		return computerOp;
	}

	public int getTurn() {
		return turns;
	}

	public char getSign() {
		return currentSign;
	}

	public void switchModes() {
		computerOp = !computerOp;
		if (computerOp)
			computerTurn();
		panel.updateBoard(board.getBoard());
	}
}