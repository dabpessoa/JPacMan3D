/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;

import javax.media.opengl.GL;

/**
 *
 * @author diegopessoa
 */
public class Cat extends Character {
    
    private static final String path = "objetos/cat/Cat";
    private static final Ponto posicaoInicial = new Ponto(0.0f, 0.43f, 0.0f);
    private static final Ponto escala = new Ponto(1.0f, 1.0f, 1.0f);
    
    public Cat(GL gl) {
        super(gl, path, posicaoInicial, escala, DIRECAO_NORTE);
    }
    
    public Cat(GL gl, int direcao) {
        super(gl, path, posicaoInicial, escala, direcao);
    }
    
    public Cat(GL gl, Ponto posicao, int direcaoOriginal) {
        super(gl, path, posicao, escala, direcaoOriginal);
    }
    
}
