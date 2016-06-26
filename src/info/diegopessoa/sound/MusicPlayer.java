/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.sound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author diegopessoa
 */
public class MusicPlayer implements SoundListener, KeyListener {
    
    private Map<String, Sound> playList;
    private Thread loopThread;
    private boolean globalLooping;

    public MusicPlayer() {
        playList = new HashMap<String, Sound>();
    }
    
    public void addToPlayList(String name, Sound sound) {
        playList.put(name, sound);
    }
    
    public void removeFromPlayList(String name) {
        playList.remove(name);
    }
    
    public void playAll() {
       Iterator<Sound> sounds = playList.values().iterator();
       while (sounds.hasNext()) {
           play(sounds.next());
       }
    }
    
    public void stopAll() {
    	Iterator<String> names = playList.keySet().iterator();
        while (names.hasNext()) {
            this.stop(names.next());
        }
    }
    
    public void play(String name) {
        play(playList.get(name));
    }
    
    public void play(final Sound sound) {
        if (!sound.containsListener(this)) {
            sound.addSoundListener(this);
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    sound.play();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }
    
    public void loop(final String name, final long loopInterval, final int times) {
        
        loopThread = new Thread(new Runnable() {
            
            int loops = times;
            
            public void run() {
            	globalLooping = true;
            	
                Sound sound = playList.get(name);
                
                while (globalLooping) {
                    try {
                        if (loops != -1) {
                            loops--;
                            if (loops == 0) globalLooping = false;
                        }
                        
                        if (!sound.isRunning())
                            play(sound);
                        
                        if (!globalLooping) {
                        	sound.stop();
                        }
                        
                        Thread thread = loopThread;
                        synchronized (thread) {
                            thread.wait(loopInterval);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        loopThread.start();
        
    }
    
    public Sound getSound(String name) {
        return playList.get(name);
    }
    
    public void pause(String name) {
    	getSound(name).pause();
    }
    
    public void stop(String name) {
    	getSound(name).stop();
    	globalLooping = false;
    }
    
    final static MusicPlayer player = new MusicPlayer();
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        
        Sound beginSound = SoundManager.loadSound("sounds/pacman/pacman_beginning.wav");
        Sound chompSound = SoundManager.loadSound("sounds/pacman/pacman_chomp.wav");
        Sound deathSound = SoundManager.loadSound("sounds/pacman/pacman_death.wav");
        Sound eatFruitSound = SoundManager.loadSound("sounds/pacman/pacman_eatfruit.wav");
        Sound eatGhostSound = SoundManager.loadSound("sounds/pacman/pacman_eatghost.wav");
        Sound extraPackSound = SoundManager.loadSound("sounds/pacman/pacman_extrapac.wav");
        Sound intermissionSound = SoundManager.loadSound("sounds/pacman/pacman_intermission.wav");
        
        Sound mp3 = SoundManager.loadSound("sounds/music.mp3");
        
        player.addToPlayList("begin", beginSound);
        player.addToPlayList("chomp", chompSound);
        player.addToPlayList("death", deathSound);
        player.addToPlayList("fruit", eatFruitSound);
        player.addToPlayList("ghost", eatGhostSound);
        player.addToPlayList("pack", extraPackSound);
        player.addToPlayList("intermission", intermissionSound);
        player.addToPlayList("mp3", mp3);
        
//        player.playAll();
//        player.loop("chomp", 5000, 3);
//        player.loop("death", 2000, -1);
//        player.play("begin");
        
        JFrame m = new JFrame();
		m.setTitle("Teste M�sica");
		m.pack();
		m.setSize(300, 300);
		m.setLocationRelativeTo(null);
		m.setVisible(true);
		m.addKeyListener(player);
        
        
        player.loop("chomp", 1, -1);
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        player.stopAll();
        
    }

    public void updateSoundFinished() {
        
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		player.getSound("mp3").pause();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}
