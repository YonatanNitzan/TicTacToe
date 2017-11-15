package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class GridPiece extends Rectangle {

	// TODO: Fix delay in PVC.

	private BufferedImage img;
	// Colors for the players
	private final String P1 = "./images/BlueX.png";
	private final String P2 = "./images/RedO.png";
	// Outline color
	private final Color OUTLINE = Color.BLACK;

	/**
	 * Sets the piece in the given bounds.
	 * 
	 * @param bounds
	 */
	public GridPiece(Rectangle bounds) {
		this.setBounds(bounds);
	}

	/**
	 * 
	 * @param sign
	 * @throws IOException 
	 */
	public void setPlayer(char sign) {
		try {
			if (sign == 'X')
				img = ImageIO.read(new File(P1));
			else if (sign == 'O')
				img = ImageIO.read(new File(P2));
			else
				img = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws the grid piece.
	 * 
	 * @param g
	 *            - Graphics2D
	 */
	public void draw(Graphics2D g) {
		// Fills the grid piece with the player's sign.
		if (img != null) {
			g.drawImage(img,
					getBounds().x, getBounds().y,
					getBounds().width, getBounds().width, null);
		}

		// Draws outline
		g.setColor(OUTLINE);
		g.draw(this);
	}
}