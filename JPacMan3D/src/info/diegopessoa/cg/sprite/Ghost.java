/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.service.Tabuleiro;
import info.diegopessoa.sound.MusicPlayer;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.media.opengl.GL;


/**
 *
 * @author diegopessoa
 */
public class Ghost extends AnimatedCharacter {

    private static final String path = "objetos/ghost/ghost";
    private static final Ponto posicaoInicial = new Ponto(0.0f, 0.43f, 0.0f);
    private static final Ponto escala = new Ponto(0.3f, 0.3f, 0.3f);
    
    private Tabuleiro<Character> tabuleiro;
    private MusicPlayer musicPlayer;
    private int animeCount;
    
    public Ghost(GL gl) {
        super(gl, path, posicaoInicial, escala, DIRECAO_NORTE);
        animeCount = 0;
    }
    
    public Ghost(GL gl, Ponto posicao, int direcaoOriginal, Tabuleiro<Character> tabuleiro, MusicPlayer musicPlayer) {
        super(gl, path, posicao, escala, direcaoOriginal);
        this.tabuleiro = tabuleiro;
        this.musicPlayer = musicPlayer;
        animeCount = 0;
    }
    
    @Override
    public void doAnimation() {
        if (animeCount == getSleepTime()/500) {
            
            int direcao = new Random().nextInt(4);
            boolean move = false;
            
            if (tabuleiro.isFreeSpace(this, direcao)) {
            	move = true;
        	} else {
        		Point pontoObjeto = tabuleiro.getNextPoint(this, direcao);
        		if (!tabuleiro.isOutOfSpace(pontoObjeto.x, pontoObjeto.y)) {
        			List<Character> itens = tabuleiro.getObjects(pontoObjeto.x, pontoObjeto.y);
        			if (itens != null) {
        				Wall wall = null;
        				Ghost ghost = null;
        				Pacman pacman = null;
        				for (Character item : itens) {
							if (item instanceof Wall) {
								wall = (Wall) item;
							} else if (item instanceof Ghost) {
								ghost = (Ghost) item;
							} else if (item instanceof Pacman) {
								pacman = (Pacman) item;
							}
						}
        				
        				if (wall == null && ghost == null) {
        					move = true;
        					if (pacman != null) {
        						musicPlayer.play("death");
        						pacman.die();
                				Point pontoCharacter = tabuleiro.getPosition(pacman);
            					tabuleiro.clearPosition(pontoCharacter.x, pontoCharacter.y);
            					pacman.setPosicao(pacman.getPosicaoInicial());
            					tabuleiro.setposition(0, 0, pacman);
        					}
        				}
        				
        			}

        		}
        	}
            
            if (move) {
            	move(direcao);
        		tabuleiro.move(this, direcao);
            }
            
            animeCount = 0;
        } else {
            animeCount++;
        }
    }
    
}
