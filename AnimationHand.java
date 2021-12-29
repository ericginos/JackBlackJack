import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationHand extends JPanel implements ActionListener {
	JLabel label;
	Timer timer = new Timer(1, this);
	int y = 0;
	int xLoc = 0;
	int yLoc = 0;
	int velocityX = 1;

	public AnimationHand(JLabel j) {
		this.xLoc = j.getX();
		this.yLoc = j.getY();
		y = yLoc-10;
		this.label = j;

	}

	public void start() {
		label.setLocation(xLoc, y);
		timer.start();
	}

	public boolean running() {
		return timer.isRunning();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			if (y < yLoc) {
				y = y + 1;
			}
			repaint();
			System.out.println(label);
			label.setLocation(xLoc, y);

			if (y >= yLoc) {
				label.setLocation(xLoc, yLoc);
				timer.stop();
				;
			}
			
		}
}
