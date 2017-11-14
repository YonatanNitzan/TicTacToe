package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class GridPiece extends Rectangle {
	
	// TODO: Change player representation from colors to X and O.

	// Colors for the players
	private final Color P1 = Color.BLUE, P2 = Color.RED;
	// Outline color
	private final Color OUTLINE = Color.BLACK;
	Color c = OUTLINE;

	/**
	 * Sets the piece in the given bounds.
	 * 
	 * @param bounds
	 */
	public GridPiece(Rectangle bounds) {
		this.setBounds(bounds);
	}

	/**
	 * Returns the color of the grid piece.
	 * 
	 * @return Color
	 */
	public Color getColor() {
		return c;
	}

	/**
	 * 
	 * @param sign
	 */
	public void setPlayer(char sign) {
		if (sign == 'X')
			c = P1;
		else if (sign == 'O')
			c = P2;
		else
			c = OUTLINE;
	}

	/**
	 * Draws the grid piece.
	 * 
	 * @param g
	 *            - Graphics2D
	 */
	public void draw(Graphics2D g) {
		// Fills the grid piece with the players color.
		if (c != OUTLINE) {
			g.setColor(getColor());
			g.fill(this);
		}

		// Draws outline
		g.setColor(OUTLINE);
		g.draw(this);
	}
}