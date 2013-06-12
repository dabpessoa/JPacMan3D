/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.sprite;

import info.diegopessoa.cg.bean.Ponto;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


/**
 *
 * @author diegopessoa
 */
public class Food extends Character {

    private GLU glu;
    private Ponto color;
    private float raio;
    private GLUquadric food;
    private final int slices = 10;
    private final int stacks = 10;
    
    public Food(GL gl, GLU glu, Ponto posicaoInicial, float raio, Ponto color) {
        super(gl, posicaoInicial);
        this.raio = raio;
        this.color = color;
        this.glu = glu;
        this.food = glu.gluNewQuadric();
    }

    @Override
    public void desenhar() {
    	getGl().glNormal3f(0, 1, 0);
        getGl().glColor4f(color.getX(), color.getY(), color.getZ(), 1.0f);
        glu.gluQuadricDrawStyle(food, GLU.GLU_FILL);
        glu.gluQuadricNormals(food, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(food, GLU.GLU_OUTSIDE); 
        glu.gluSphere(food, raio, slices, stacks);
        glu.gluDeleteQuadric(food);
    }
    
}