package temp;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Teste2 implements KeyListener {

	GraphicsDevice device;

	public static void main(String[] args) {
		new Teste2();
	}

	public Teste2() {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		fullScreen();
	}

	public void fullScreen() {

		JFrame frame = new JFrame("Teste");
		
		boolean isFullScreen = device.isFullScreenSupported();
		frame.setUndecorated(isFullScreen);
		frame.setResizable(!isFullScreen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		if (isFullScreen) {

			// Full-screen mode
			try {
				device.setFullScreenWindow(frame);
				frame.validate();
			} catch(Exception e) {
				device.setFullScreenWindow(null);
			}
			
		} else {
			
			// Windowed mode
			frame.setSize(640, 480);
			frame.setUndecorated(false);
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		System.exit(0);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
