package info.diegopessoa.cg.service;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

public class GameGLWindow extends GLCanvas {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private FPSAnimator animator;
	private GraphicsDevice device;
	private Dimension originalSize;
	private boolean fullscream;
	
	public GameGLWindow(String title, Dimension size) {
		// Create the top-level container frame
		// Swing's JFrame or AWT's Frame
		frame = new JFrame(title); 
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(this);
		this.setFocusable(true);
		this.requestFocus();
		// Create a animator that drives canvas' display() at the specified FPS.
		animator = new FPSAnimator(this, 60, true);
		this.originalSize = size;
	}
	
	public void fullscreen() {
		
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		boolean fullScreenSupported = device.isFullScreenSupported();
		
		if (frame.isDisplayable()) {
			frame.dispose();
		}
		
		if (fullScreenSupported && !fullscream) {

			// Full-screen mode
			try {
				
				frame.setUndecorated(fullScreenSupported);
				frame.setResizable(!fullScreenSupported);
				
				device.setFullScreenWindow(frame);
				frame.validate();
				fullscream = true;
				
			} catch(Exception e) {
				device.setFullScreenWindow(null);
			}
			
		} else {
			
			// Windowed mode
			frame.setSize(originalSize);
			frame.setUndecorated(false);
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.validate();
			fullscream = false; 
			
		}
		
	}
	
	public void startGameLoop() {
		animator.start(); // start the animation loop
	}
	
	public void stopGameLoop() {
		if (animator.isAnimating()) animator.stop();
	}
	
	public void close() {
		stopGameLoop();
		frame.dispose();
	}
	
	public void showFrame() {
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public boolean isFullscream() {
		return fullscream;
	}
	
}
