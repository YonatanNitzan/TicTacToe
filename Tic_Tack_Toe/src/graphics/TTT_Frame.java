package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TTT_Frame extends JFrame {

	/* Variable declaration */
	private final static int WIDTH = 617, HEIGHT = 648; // Finals for the frame's size
	private TTT_Panel panel;

	public static void main(String[] args) {
		new TTT_Frame();
	}

	public TTT_Frame() {
		/* Frame setup */
		setTitle("Tic Tac Toe!"); // Sets the frame's title
		setResizable(false); // Makes the frame's dimensions permanent
		setMinimumSize(new Dimension(WIDTH, HEIGHT)); // Sets the size of the frame
		setVisible(true); // Makes the frame visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the program terminate when the frame closes

		/* Adds panel */
		panel = new TTT_Panel(this);
		add(panel);

		validate();
		pack();
	}

	/* Gives the panel */
	public TTT_Panel getPanel() {
		return panel;
	}

}
