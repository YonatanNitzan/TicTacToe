package processing;

/**
 * 
 * @author Ofri "Oftzik" Marxs
 *
 */
public class Board {
	public char[][] board = new char[3][3];

	/**
	 * Constructor creates an empty board.
	 * 
	 * @param panel
	 *            - Game panel
	 */
	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				board[i][j] = '-';
		}
	}

	/**
	 * Returns whether the values are in range.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 */
	private boolean inRange(int r, int c) {
		if ((r >= 0 && r < 3) && (c >= 0 && c < 3))
			return true;
		else
			return false;
	}

	public char[][] getBoard() {
		return board;
	}

	/**
	 * Inserts a sign in a given position.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 * @param sign
	 *            - The sign to be inserted
	 */
	public boolean putSign(int r, int c, char sign) {
		// Checks whether position is out of bounds or taken
		if (!inRange(r, c) || !(board[r][c] == '-')) {
			System.out.println("Place taken or out of range. Enter a different location");
			return false;
		} else {
			board[r][c] = sign; // Inserts the sign
			return true;
		}
	}

	/**
	 * Clears a given position.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 */
	public void eraseSign(int r, int c) {
		board[r][c] = '-';
	}

	/**
	 * Checks if an opponent won.
	 * 
	 * @param sign
	 *            - The sign of the tested opponent
	 */
	public boolean checkBoard(char sign) {
		int test;

		// Test for column win
		for (int i = 0; i < 3; i++) {
			test = 0;
			for (int j = 0; j < 3; j++)
				test += board[i][j];

			if (test / 3 == sign)
				return true;
		}

		// Test for row win
		for (int i = 0; i < 3; i++) {
			test = 0;
			for (int j = 0; j < 3; j++)
				test += board[j][i];

			if (test / 3 == sign)
				return true;
		}

		// Test for diagonal win (left to right)
		test = 0;
		for (int i = 0; i < 3; i++)
			test += board[i][i];

		if (test / 3 == sign)
			return true;

		// Test for diagonal win (right to left)
		test = 0;
		for (int i = 0; i < 3; i++)
			test += board[i][2 - i];

		if (test / 3 == sign)
			return true;

		// No win situation
		return false;
	}
}