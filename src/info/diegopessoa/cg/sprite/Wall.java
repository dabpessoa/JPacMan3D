/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.service.Transformacao;

import java.awt.Point;

import javax.media.opengl.GL;


import com.sun.opengl.util.texture.Texture;

/**
 *
 * @author diegopessoa
 */
public class Wall extends Character {

	private Texture texture;
    private Ponto medidas;
    private Point coords[] = {new Point(0,0),new Point(0,1), new Point(1,1), new Point(1,0)};
    
    public Wall(GL gl, Ponto posicaoInicial, Ponto medida) {
        super(gl, posicaoInicial);
        this.medidas = medida;
        texture = Transformacao.loadTexture("objetos/parede.jpg");
    }

    @Override
    public void desenhar() {
    	getGl().glNormal3f(0, 1, 0);
        Transformacao.desenharParalelepipedo(getGl(), medidas.getX(), medidas.getY(), medidas.getZ(), getPosicao(), texture, coords);
    }
    
}
