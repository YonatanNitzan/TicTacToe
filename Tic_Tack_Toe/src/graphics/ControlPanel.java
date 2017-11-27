package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements ActionListener {
	
	// TODO: Add buttons for PVP/PVC and Restart.
	// TODO: Add a panel to show turn, who's turn, and the likes.

	private GamePanel game;
	private final int WIDTH = 600, HEIGHT = 200;
	private final String PVP = "PVP", PVC = "PVC";
	private JButton reset, switcher;
	private JLabel turnsStatus, modeStatus;
	private String notMode;
	
	public ControlPanel(GamePanel game) {
		this.game = game;
		notMode = PVC;
		
		turnsStatus = new JLabel("X's turn; Turn #1");
		modeStatus = new JLabel("Game mode: Player vs Player");
		
		reset = new JButton("Reset");
		switcher = new JButton("Switch to " + notMode);
		
		
		reset.addActionListener(this);
		switcher.addActionListener(this);
		
		
		add(reset);
		add(switcher);
		add(turnsStatus);
		add(modeStatus);
	}
	
	public void updateLabels (char sign, int turn, boolean computerOp) {
		String text = String.format("%c's turn; Turn #%d", sign, turn);
		turnsStatus.setText(text);
		if (!computerOp) {
			modeStatus.setText("Game mode: Player vs Player");
			notMode = PVC;
		} else {
			modeStatus.setText("Game mode: Player vs Computer");
			notMode = PVP;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawRect(0, 0, WIDTH, HEIGHT);
	}
	
	@Override
	public int getWidth() {
		return WIDTH + 10;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT + 10;
	}
	
	public Dimension getDimensions() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(reset))
			game.reset();
		else if (e.getSource().equals(switcher)) {
			game.switchModes();
			switcher.setText("Switch to " + notMode);
		}
	}
}
