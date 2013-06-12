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
public class AnimatedDog extends AnimatedCharacter {

    private static final String path = "objetos/dog_02/dog_02";
    private static final Ponto posicaoInicial = new Ponto(0.0f, 0.43f, 0.0f);
    private static final Ponto escala = new Ponto(1.0f, 1.0f, 1.0f);
    
    private int animeCount;
    private boolean flag;
    
    public AnimatedDog(GL gl) {
        super(gl, path, posicaoInicial, escala, DIRECAO_NORTE);
        animeCount = 0;
    }
    
    public AnimatedDog(GL gl, Ponto posicao, int direcaoOriginal) {
        super(gl, path, posicao, escala, direcaoOriginal);
        animeCount = 0;
    }
    
    @Override
    public void doAnimation() {
        if (animeCount == getSleepTime()/200) {
            
            if (flag) {
                move(DIRECAO_LESTE);
                move(DIRECAO_LESTE);
            } else {
                move(DIRECAO_OESTE);
                move(DIRECAO_OESTE);
            }
            flag = !flag;
            
            animeCount = 0;
        } else {
            animeCount++;
        }
    }
    
}
