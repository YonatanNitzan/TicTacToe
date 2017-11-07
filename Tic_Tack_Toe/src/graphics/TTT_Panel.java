package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TTT_Panel extends JPanel {

	/* Variable declaration */
	private final static int WIDTH = 600, HEIGHT = WIDTH; // Finals for the panel's size
	private final ArrayList<TTT_Grid_Piece> grid;
	private boolean playerTurn = true;

	public TTT_Panel(TTT_Frame frame) {
		grid = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				grid.add(
						new TTT_Grid_Piece(new Rectangle2D.Double(j * WIDTH / 3, i * HEIGHT / 3, WIDTH / 3, HEIGHT / 3),
								Color.BLUE, Color.RED)); // Creates a 3x3 grid
		}

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) { // When the mouse is clicked
				super.mouseClicked(me);
				for (TTT_Grid_Piece gp : grid) { // Runs a loop on all squares

					if (gp.getBounds().contains(me.getPoint())) { // Checks if a square is clicked

						// Prints the number of the clicked square
						System.out.println("Clicked square " + (grid.indexOf(gp)));
						// Squares numbered 0-8 from left to right and up to down

						// Draws/erases an X in the clicked square
						if (!gp.isOccupied && playerTurn) {
							gp.toggle();
							gp.getX().toggle();
							System.out.println("toggled " + (grid.indexOf(gp)));
							playerTurn = false;
						}
						repaint();
					}
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (TTT_Grid_Piece gp : grid) {
			gp.getX().tryPaint(g2);
			g2.draw(gp.getBounds());
			if (!playerTurn) {
				gp.getO().tryPaint(g2);
				playerTurn = false;
			}
		}
	}

	public void generateO() {

	}
}