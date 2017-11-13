package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	// TODO: Add buttons for PVP/PVC.
	// TODO: Add a panel to show turn, who's turn, and the likes.

	private final static int WIDTH = 600, HEIGHT = 600; // Dimensions
	private final static int X_MARGIN = 20, Y_MARGIN = 50; // Panel offsets
	private Panel panel;

	public static void main(String[] args) {
		new Frame();
	}

	public Frame() {
		/* Frame setup */
		setTitle("Tic Tac Toe!");
		setResizable(true);
		setMinimumSize(new Dimension(WIDTH + X_MARGIN, HEIGHT + Y_MARGIN)); // Sets the size of the frame
		setVisible(true);

		// Ends program on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Adds panel */
		panel = new Panel(this, WIDTH);
		add(panel);

		validate();
		pack();
	}
}