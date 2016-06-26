/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.objreader.GLModel;
import info.diegopessoa.cg.service.Transformacao;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;


/**
 *
 * @author diegopessoa
 */
public class Pacman extends AnimatedCharacter {
    
    private static final String path = "objetos/pacman/pacman1";
    private static final String path1 = "objetos/pacman/pacman2";
    private static final String path2 = "objetos/pacman/pacman3";
    private static final Ponto posicaoInicial = new Ponto(0.0f, 0.43f, 0.0f);
    private static final Ponto escala = new Ponto(0.3f, 0.3f, 0.3f);
    
    private int animeCount;
    private List<GLModel> models;
    private int modelChoose;
    private int vidas;
    private int pontos;
    
    public Pacman(GL gl) {
        super(gl, path, posicaoInicial, escala, DIRECAO_NORTE);
        initModels(gl);
    }
    
    public Pacman(GL gl, int direcaoOriginal) {
        super(gl, path, posicaoInicial, escala, direcaoOriginal);
        initModels(gl);
    }
    
    public Pacman(GL gl, Ponto posicao, int direcaoOriginal) {
        super(gl, path, posicao, escala, direcaoOriginal);
        initModels(gl);
    }
    
    public Pacman(GL gl, Ponto posicao, Ponto escala, int direcaoOriginal) {
        super(gl, path, posicao, escala, direcaoOriginal);
        initModels(gl);
    }
    
    public Pacman(GL gl, Ponto posicao) {
        super(gl, path, posicao, escala, DIRECAO_NORTE);
        initModels(gl);
    }
    
    public final void initModels(GL gl) {
        models = new ArrayList<GLModel>();
        models.add(Transformacao.getObjectModel(gl, path));
        models.add(Transformacao.getObjectModel(gl, path1));
        models.add(Transformacao.getObjectModel(gl, path2));
        modelChoose = 0;
        vidas = 3;
        pontos = 0;
    }

    @Override
    public void doAnimation() {
        
        if (animeCount == getSleepTime()/800) {
            setModel(models.get(modelChoose)); 
            if (modelChoose == models.size()-1) modelChoose = 0;
            else modelChoose++;
            animeCount = 0;
        } else {
            animeCount++;
        }
        
    }
    
    @Override
    public void direcionar() {
    
         if (getDirecaoOriginal() == DIRECAO_NORTE) {

            switch (getDirecao()) {
                case DIRECAO_NORTE: rotate(0); break;
                case DIRECAO_SUL: rotate(180); break;
                case DIRECAO_LESTE: rotate(-90); break;
                case DIRECAO_OESTE: rotate(90); break;
            }

        } else if (getDirecaoOriginal() == DIRECAO_SUL) {

            switch (getDirecao()) {
                case DIRECAO_NORTE: rotate(180); break;
                case DIRECAO_SUL: rotate(0); break;
                case DIRECAO_LESTE: rotate(-90); break;
                case DIRECAO_OESTE: rotate(90); break;
            }

        } else if (getDirecaoOriginal() == DIRECAO_LESTE) {

            switch (getDirecao()) {
                case DIRECAO_NORTE: rotate(90); break;
                case DIRECAO_SUL: rotate(-90); break;
                case DIRECAO_LESTE: rotate(0); break;
                case DIRECAO_OESTE: rotate(180); break;
            }

        } else if (getDirecaoOriginal() == DIRECAO_OESTE) {

            switch (getDirecao()) {
                case DIRECAO_NORTE: rotate(-90); break;
                case DIRECAO_SUL: rotate(90); break;
                case DIRECAO_LESTE: rotate(180); break;
                case DIRECAO_OESTE: rotate(0); break;
            }

        } else throw new RuntimeException("Sentido Original Inválido: "+getDirecaoOriginal());
        
    }
    
    public void die() {
    	vidas--;
    }
    
    public int getVidas() {
		return vidas;
	}
    
    public void setVidas(int vidas) {
		this.vidas = vidas;
	}
    
    public void addPontos() {
    	pontos++;
    }
    
    public int getPontos() {
		return pontos;
	}
    
}
