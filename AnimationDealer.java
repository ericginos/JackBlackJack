import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimationDealer extends JPanel implements ActionListener {
	JLabel label;
	Timer timer = new Timer(1, this);
	int x = 0;
	int y = 0;
	int xLoc = 0;
	int yLoc = 0;
	int velocityX = 1;

	public AnimationDealer(JLabel j) {
		this.xLoc = j.getX();
		this.yLoc = j.getY();
		this.label = j;
		label.setIcon(new ImageIcon("resources/cards/" + "b.gif"));

	}

	public void start() {
		label.setLocation(0, 0);
		timer.start();
	}

	public boolean running() {
		return timer.isRunning();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			if (x < xLoc) {
				x = x + 20;
			}
			if (y < yLoc) {
				y = y + 4;
			}
			repaint();
			System.out.println(label);
			label.setLocation(x, y);

			if (y >= yLoc) {
				label.setLocation(xLoc, yLoc);
				timer.stop();
				;
			}
			
		}
}
