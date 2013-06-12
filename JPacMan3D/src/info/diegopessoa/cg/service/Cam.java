/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.service;


import info.diegopessoa.cg.bean.Ponto;

import javax.media.opengl.glu.GLU;

/**
 *
 * @author diegopessoa
 */
public class Cam {
    
    private GLU glu;
    
    private Ponto eyeView;
    private Ponto centerView;
    private Ponto upView;
    
    private double fovy, aspect, zNear, zFar;
    private float raio;
    
    public Cam() {
        eyeView = new Ponto(0, 19, 0.01f);
        centerView = new Ponto(0, 0, 0);
        upView = new Ponto(0, 1, 0);
        glu = new GLU();
        this.raio = 10f;
        this.fovy = 45.0f;
        this.zNear = 1.0f;
        this.zFar = 50.0f;
    }
    
    public Cam(Ponto eyeView, Ponto centerView, Ponto upView, float fovy, float zNear, float zFar, float raio) {
    	this.eyeView = eyeView;
    	this.centerView = centerView;
    	this.upView = upView;
    	this.glu = new GLU();
    	this.fovy = fovy;
    	this.zNear = zNear;
    	this.zFar = zFar;
    }
    
    public Cam(GLU glu) {
        this();
        this.glu = glu;
    }
    
    public void transladar(float amount) {
        
    }
    
    public void orbitar(float amount, int grau, Ponto pivo) {
        setPontoReferenciaRaio(pivo);
        // Grau deve estar entre 0 e 360.
        eyeView.setX(raio*(float)Math.sin(amount));
        eyeView.setY(raio*(float)Math.sin(amount));
        eyeView.setZ(raio*(float)Math.cos(amount));
    }
    
    public void girar(float amount) {
//        centerView = new Ponto(posicao.getX(), posicao.getY(), posicao.getZ()-500);
    }
    
    public void updateLookAt() {
    	glu.gluLookAt(eyeView.getX(), eyeView.getY(), eyeView.getZ(), centerView.getX(), centerView.getY(), centerView.getZ(), upView.getX(), upView.getY(), upView.getZ());
    }
    
    public final void pespectiva(GLU glu, float aspect) {
    	this.glu = glu;
    	this.aspect = aspect;
        this.glu.gluPerspective(fovy, aspect, zNear, zFar);
    }
    
    public final void pespectiva(float aspect) {
    	this.aspect = aspect;
        this.glu.gluPerspective(fovy, aspect, zNear, zFar);
    }
    
    public final void pespectiva() {
        this.glu.gluPerspective(fovy, aspect, zNear, zFar);
    }
    
    public void setPontoReferenciaRaio(Ponto ref) {
        // raio = distancia entre o camera e a referência.
    }
    
    public Ponto getEyeView() {
        return eyeView;
    }

    public void setEyeView(Ponto eyeView) {
        this.eyeView = eyeView;
    }

    public Ponto getCenterView() {
        return centerView;
    }

    public void setCenterView(Ponto centerView) {
        this.centerView = centerView;
    }

    public Ponto getUpView() {
        return upView;
    }

    public void setUpView(Ponto upView) {
        this.upView = upView;
    }
    
}
