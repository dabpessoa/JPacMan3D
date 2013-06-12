package temp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;

public class Media extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	// some lock somewhere...
	private static Object lock = new Object();
	// some paused variable
	private static volatile boolean paused = false;
	// on the user thread:
	public void userPressedPause() {
	 paused = true;
	}
	 
	public void userPressedPlay() {
	 synchronized(lock) {
	  paused = false;
	  lock.notifyAll();
	 }
	}
	
	public static void main(String[] args) {
		
		
		Media m = new Media();
		m.setTitle("Teste M�sica");
		m.pack();
		m.setSize(300, 300);
		m.setLocationRelativeTo(null);
		m.setVisible(true);
		m.addKeyListener(m);
		
		
		m.tocar("sounds/music.mp3");  
		
		
	}
	
	public void tocar(final String path) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				
				AudioInputStream din = null;
				try {
					AudioInputStream in = AudioSystem.getAudioInputStream(new File(path));
					AudioFormat baseFormat = in.getFormat();
					AudioFormat decodedFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
							baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
							false);
					din = AudioSystem.getAudioInputStream(decodedFormat, in);
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
					SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
					if(line != null) {
						line.open(decodedFormat);
						byte[] data = new byte[4096];
						// Start
						line.start();
						
						int nBytesRead;
						synchronized (lock) {
							while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
								while (paused) {
									if(line.isRunning()) {
										line.stop();
									}
									try {
										lock.wait();
									}
									catch(InterruptedException e) {
									}
								}
								if(!line.isRunning()) {
									line.start();
								}
								line.write(data, 0, nBytesRead);
							}
						}
						// Stop
						line.drain();
						line.stop();
						line.close();
						din.close();
					}
				
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					if(din != null) {
						try { din.close(); } catch(IOException e) { }
					}
				}
				
				
				
				
			}
		}).start();
		
		
		
			
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	boolean flag = false;
	@Override                  
	public void keyPressed(KeyEvent e) {
		System.out.println("teste...");
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!flag) 
				userPressedPause();  
			else
				userPressedPlay();
			flag = !flag;  
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
