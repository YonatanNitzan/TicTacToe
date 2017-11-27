package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import processing.MainOp;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private GridPiece[][] grid = new GridPiece[3][3];
	private final int SIDE = 200; // squares' dimensions
	private Frame frame;
	private MainOp op;

	/**
	 * Initiates the 3x3 grid.
	 * 
	 * @param frame
	 *            - The frame the panel is on
	 * @param d
	 *            - The dimension of the squares
	 */
	public GamePanel(Frame frame) {
		this.frame = frame;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid[i][j] = new GridPiece(new Rectangle(j * SIDE, i * SIDE, SIDE, SIDE));

		op = new MainOp(this);

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
							System.out.printf("Clicked square (%d, %d)\n", i, j);
							// Squares numbered 0-8 from left to right and up to down

							op.input(i, j);
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
		
		frame.getControlPanel().updateLabels(op.getSign(), op.getTurn(), op.isComputerOp());
		
		repaint();
	}
	
	@Override
	public int getWidth() {
		return SIDE * 3 + 10;
	}
	
	@Override
	public int getHeight() {
		return SIDE * 3 + 10;
	}

	public Dimension getDimensions() {
		return new Dimension(getWidth(), getHeight());
	}

	public void reset() {
		op.restart();
	}

	public void switchModes() {
		op.switchModes();
	}
}