/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.bean;

/**
 *
 * @author diegopessoa
 */
public class Cor {
    
    public float r;
    public float g;
    public float b;
    public float alpha;

    public Cor() {}
    
    public Cor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = 1.0f;
    }
    
    public Cor(float r, float g, float b, float alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }
    
}
