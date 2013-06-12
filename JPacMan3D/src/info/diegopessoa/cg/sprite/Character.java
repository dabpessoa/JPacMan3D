/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.objreader.GLModel;
import info.diegopessoa.cg.service.Transformacao;

import javax.media.opengl.GL;

/**
 *
 * @author diegopessoa
 */
public class Character {
    
    public static final int DIRECAO_NORTE = 0;
    public static final int DIRECAO_SUL = 1;
    public static final int DIRECAO_LESTE = 2;
    public static final int DIRECAO_OESTE = 3;
    public static final int DIRECAO_TOP = 4;
    public static final int DIRECAO_BOTTOM = 5;
    public static final float MOVE_SPACE = 0.97f;
    
    private Ponto posicao;
    private Ponto posicaoInicial;
    private Ponto escala;
    private GLModel model;
    private GL gl;
    private int direcao;
    private int direcaoOriginal;

    public Character() {}
    
    public Character(GL gl, Ponto posicaoInicial) {
        this.gl = gl;
        this.posicao = posicaoInicial;
        if (posicaoInicial != null)
        	this.posicaoInicial = new Ponto(posicaoInicial.getX(), posicaoInicial.getY(), posicaoInicial.getZ());
    }

    public Character(GL gl, String path, Ponto posicaoInicial, Ponto escala, int direcaoOriginal) {
        this.gl = gl;
        this.posicao = posicaoInicial;
        if (posicaoInicial != null)
        	this.posicaoInicial = new Ponto(posicaoInicial.getX(), posicaoInicial.getY(), posicaoInicial.getZ());
        this.model = Transformacao.getObjectModel(gl, path);
        this.escala = escala;
        this.direcao = direcaoOriginal;
        this.direcaoOriginal = direcaoOriginal;
        this.gl.glTranslatef(posicao.getX(), posicao.getY(), posicao.getZ());
    }
    
    public void desenhar() {
        gl.glScalef(escala.getX(), escala.getY(), escala.getZ());
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        model.opengldraw(gl);
    }
    
    public void rotate(int grau) {
        gl.glRotatef(grau, 0, 1, 0);
    }
    
    public void posicionar() {
        this.gl.glTranslatef(posicao.getX(), posicao.getY(), posicao.getZ());
    }
    
    public void process() {
        gl.glPushMatrix();
        posicionar();
        direcionar();
        desenhar();
        gl.glPopMatrix();
    }

    public GL getGl() {
        return gl;
    }
    
     public void direcionar() {
        
         if (direcaoOriginal == DIRECAO_NORTE) {

            switch (direcao) {
                case DIRECAO_NORTE: rotate(0); break;
                case DIRECAO_SUL: rotate(180); break;
                case DIRECAO_LESTE: rotate(90); break;
                case DIRECAO_OESTE: rotate(-90); break;
            }

        } else if (direcaoOriginal == DIRECAO_SUL) {

            switch (direcao) {
                case DIRECAO_NORTE: rotate(180); break;
                case DIRECAO_SUL: rotate(0); break;
                case DIRECAO_LESTE: rotate(90); break;
                case DIRECAO_OESTE: rotate(-90); break;
            }

        } else if (direcaoOriginal == DIRECAO_LESTE) {

            switch (direcao) {
                case DIRECAO_NORTE: rotate(-90); break;
                case DIRECAO_SUL: rotate(90); break;
                case DIRECAO_LESTE: rotate(0); break;
                case DIRECAO_OESTE: rotate(180); break;
            }

        } else if (direcaoOriginal == DIRECAO_OESTE) {

            switch (direcao) {
                case DIRECAO_NORTE: rotate(90); break;
                case DIRECAO_SUL: rotate(-90); break;
                case DIRECAO_LESTE: rotate(180); break;
                case DIRECAO_OESTE: rotate(0); break;
            }

        } else throw new RuntimeException("Sentido Original Inválido: "+direcaoOriginal);
        
    }
    
    public void move(int direcaoDestino) {
        switch (direcaoDestino) {
            case DIRECAO_NORTE: posicao.setZ(posicao.getZ()-MOVE_SPACE); break;
            case DIRECAO_SUL: posicao.setZ(posicao.getZ()+MOVE_SPACE); break;
            case DIRECAO_LESTE: posicao.setX(posicao.getX()+MOVE_SPACE); break;
            case DIRECAO_OESTE: posicao.setX(posicao.getX()-MOVE_SPACE); break;
        }           
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public Ponto getPosicao() {
        return posicao;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    public void setModel(GLModel model) {
        this.model = model;
    }

    public int getDirecaoOriginal() {
        return direcaoOriginal;
    }
    
    public Ponto getPosicaoInicial() {
		return new Ponto(posicaoInicial.getX(), posicaoInicial.getY(), posicaoInicial.getZ());
	}
    
}
