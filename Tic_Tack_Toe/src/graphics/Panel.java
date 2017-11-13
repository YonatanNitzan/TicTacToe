package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import processing.MainOp;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	private GridPiece[][] grid = new GridPiece[3][3];
	private int side; // squares' dimensions
	private Frame frame;
	private MainOp game;

	/**
	 * Initiates the 3x3 grid.
	 * 
	 * @param frame
	 *            - The frame the panel is on
	 * @param d
	 *            - The dimension of the squares
	 */
	public Panel(Frame frame, int d) {
		this.frame = frame;
		side = d / 3;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j] = new GridPiece(new Rectangle(j * side, i * side, side, side));

		game = new MainOp(this);

		setMouse();
	}

	/**
	 * Draws the board.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j].draw(g2);
	}

	/**
	 * Returns the frame the panel is working on.
	 * 
	 * @return JFrame
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * Sets up mouse listener to know which rectangle is clicked.
	 */
	private void setMouse() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) { // When the mouse is clicked
				super.mouseClicked(me);

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (grid[i][j].contains(me.getPoint())) { // Checks if a square is clicked

							// Prints the number of the clicked square
							System.out.printf("Clicked rectangle (%d, %d)\n", i, j);
							// Squares numbered 0-8 from left to right and up to down

							game.input(i, j);

							repaint();
						}
					}
				}
			}
		});
	}

	public void updateBoard(char[][] charBoard) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j].setPlayer(charBoard[i][j]);
	}
}