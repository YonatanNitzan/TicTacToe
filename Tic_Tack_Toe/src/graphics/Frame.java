
package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private final static int X_MARGIN = 20, Y_MARGIN = 50; // Panel offsets
	private GamePanel game;
	private ControlPanel control;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame();
            }
        });
	}

	public Frame() {
		/* Frame setup */
		setTitle("Tic Tac Toe!");
		setResizable(false);
		setVisible(true);
		
		// Ends program on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Creates panel */
		game = new GamePanel(this);
		control = new ControlPanel(game);
		
		/* Sets the sizes of the panels */
		game.setPreferredSize(game.getDimensions());
		control.setPreferredSize(control.getDimensions());
		
		/* Adds the panels */
		add(game, BorderLayout.SOUTH);
		add(control, BorderLayout.NORTH);
		
		// Sets the size of the frame
		setMinimumSize(calculateDimensions());
		
		/* Finishing touches for the frame */
		validate();
		pack();
	}

	private Dimension calculateDimensions () {
		return new Dimension(calculateWidth(), calculateHeight());
	}
	
	private int calculateWidth() {
		return X_MARGIN + game.getWidth() + 10;
	}
	
	private int calculateHeight() {
		return Y_MARGIN + game.getHeight() + control.getHeight() + 10;
	}

	public ControlPanel getControlPanel() {
		return control;
	}
}