/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;

/**
 *
 * @author diegopessoa
 */
public abstract class AnimatedCharacter extends Character implements Animatable, Runnable {

    private boolean animate;
    private Thread thread;
    private Long sleepTime;
    
    public AnimatedCharacter(GL gl, String path, Ponto posicaoInicial, Ponto escala, int sentidoOriginal) {
        super(gl, path, posicaoInicial, escala, sentidoOriginal);
        sleepTime = 5000L;
    }

    @Override
    public void animate() {
        thread = new Thread(this);
        thread.start();
        animate = true;
    }

    @Override
    public void kill() {
        animate = false;
    }
    
    public void sleep(long timeMillis) throws InterruptedException {
        Thread.sleep(timeMillis);
    }

    @Override
    public void process() {
        getGl().glPushMatrix();
        posicionar();
        direcionar();
        if (thread == null || !thread.isAlive()) {
            doAnimation();
        }
        desenhar();
        getGl().glPopMatrix();
    }
    
    public abstract void doAnimation();

    @Override
    public void run() {
        while (animate) { System.out.println("Dentro do run... thread rodando!");
            doAnimation();
            if (sleepTime != null) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AnimatedCharacter.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erro ao colocar thread para dormir.");
                }
            }
        }
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Long getSleepTime() {
        return sleepTime;
    }
    
}
