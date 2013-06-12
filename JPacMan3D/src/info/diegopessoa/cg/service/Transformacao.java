/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.service;

import info.diegopessoa.cg.bean.Cor;
import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.objreader.GLModel;
import info.diegopessoa.cg.sprite.Character;
import info.diegopessoa.cg.sprite.Food;
import info.diegopessoa.cg.sprite.Ghost;
import info.diegopessoa.cg.sprite.Wall;
import info.diegopessoa.sound.MusicPlayer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

/**
 *
 * @author diegopessoa
 */
public class Transformacao {
        
	public static List<Character> criarListaPersonagens(GL gl, GLU glu, Tabuleiro<Character> tabuleiro, MusicPlayer musicPlayer) {
		
		List<Character> personas = new ArrayList<Character>();
		
		// GHOSTS
		Ghost ghost1 = new Ghost(gl, new Ponto(-0.485f, 0.7f, -0.485f), Character.DIRECAO_SUL, tabuleiro, musicPlayer); 
		tabuleiro.setposition(4, 5, ghost1);
		personas.add(ghost1);
		Ghost ghost2 = new Ghost(gl, new Ponto(0.485f, 0.7f, -0.485f), Character.DIRECAO_SUL, tabuleiro, musicPlayer);
		tabuleiro.setposition(5, 5, ghost2);
		personas.add(ghost2);
		Ghost ghost3 = new Ghost(gl, new Ponto(-0.485f, 0.7f, 0.485f), Character.DIRECAO_SUL, tabuleiro, musicPlayer);
		tabuleiro.setposition(4, 4, ghost3);
		personas.add(ghost3);
		Ghost ghost4 = new Ghost(gl, new Ponto(0.485f, 0.7f, 0.485f), Character.DIRECAO_SUL, tabuleiro, musicPlayer);
		tabuleiro.setposition(5, 4, ghost4);
		personas.add(ghost4);

		// FOODS
		Food food1 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, 2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 2, food1);
		personas.add(food1);
		Food food2 = new Food(gl, glu, new Ponto(0.485f, 0.7f, 2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 2, food2);
		personas.add(food2);
		Food food3 = new Food(gl, glu, new Ponto(-1.455f, 0.7f, 2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(3, 2, food3);
		personas.add(food3);		
		Food food4 = new Food(gl, glu, new Ponto(1.455f, 0.7f, 2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(6, 2, food4);
		personas.add(food4);
		
		Food food5 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, -2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 7, food5);
		personas.add(food5);
		Food food6 = new Food(gl, glu, new Ponto(0.485f, 0.7f, -2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 7, food6);
		personas.add(food6);
		Food food7 = new Food(gl, glu, new Ponto(-1.455f, 0.7f, -2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(3, 7, food7);
		personas.add(food7);		
		Food food8 = new Food(gl, glu, new Ponto(1.455f, 0.7f, -2.425f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(6, 7, food8);
		personas.add(food8);
		
		Food food9 = new Food(gl, glu, new Ponto(1.455f, 0.7f, 1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(6, 3, food9);
		personas.add(food9);
		Food food10 = new Food(gl, glu, new Ponto(-1.455f, 0.7f, 1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(3, 3, food10);
		personas.add(food10);
		
		Food food11 = new Food(gl, glu, new Ponto(1.455f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(6, 6, food11);
		personas.add(food11);
		Food food12 = new Food(gl, glu, new Ponto(-1.455f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(3, 6, food12);
		personas.add(food12);
		
		Food food13 = new Food(gl, glu, new Ponto(-2.425f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(2, 6, food13);
		personas.add(food13);
		Food food14 = new Food(gl, glu, new Ponto(-2.425f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(2, 5, food14);
		personas.add(food14);
		Food food15 = new Food(gl, glu, new Ponto(-2.425f, 0.7f, -1.455f+0.97f*2), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(2, 4, food15);
		personas.add(food15);
		Food food16 = new Food(gl, glu, new Ponto(-2.425f, 0.7f, -1.455f+0.97f*3), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(2, 3, food16);
		personas.add(food16);
		
		Food food17 = new Food(gl, glu, new Ponto(2.425f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(7, 6, food17);
		personas.add(food17);
		Food food18 = new Food(gl, glu, new Ponto(2.425f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(7, 5, food18);
		personas.add(food18);
		Food food19 = new Food(gl, glu, new Ponto(2.425f, 0.7f, -1.455f+0.97f*2), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(7, 4, food19);
		personas.add(food19);
		Food food20 = new Food(gl, glu, new Ponto(2.425f, 0.7f, -1.455f+0.97f*3), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(7, 3, food20);
		personas.add(food20);
		
		Food food21 = new Food(gl, glu, new Ponto(-3.395f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(1, 5, food21);
		personas.add(food21);
		Food food22 = new Food(gl, glu, new Ponto(-3.395f, 0.7f, -1.455f+1.94f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(1, 4, food22);
		personas.add(food22);
		
		Food food23 = new Food(gl, glu, new Ponto(3.395f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(8, 5, food23);
		personas.add(food23);
		Food food24 = new Food(gl, glu, new Ponto(3.395f, 0.7f, -1.455f+1.94f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(8, 4, food24);
		personas.add(food24);
		
		Food food25 = new Food(gl, glu, new Ponto(-4.365f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(0, 5, food25);
		personas.add(food25);
		Food food26 = new Food(gl, glu, new Ponto(-4.365f, 0.7f, -1.455f+1.94f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(0, 4, food26);
		personas.add(food26);
		Food food27 = new Food(gl, glu, new Ponto(-4.365f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(0, 6, food27);
		personas.add(food27);
		Food food28 = new Food(gl, glu, new Ponto(-4.365f, 0.7f, -1.455f+2.91f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(0, 3, food28);
		personas.add(food28);
		
		Food food29 = new Food(gl, glu, new Ponto(4.365f, 0.7f, -1.455f+0.97f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(9, 5, food29);
		personas.add(food29);
		Food food30 = new Food(gl, glu, new Ponto(4.365f, 0.7f, -1.455f+1.94f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(9, 4, food30);
		personas.add(food30);
		Food food31 = new Food(gl, glu, new Ponto(4.365f, 0.7f, -1.455f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(9, 6, food31);
		personas.add(food31);
		Food food32 = new Food(gl, glu, new Ponto(4.365f, 0.7f, -1.455f+2.91f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(9, 3, food32);
		personas.add(food32);
		
		Food food33 = new Food(gl, glu, new Ponto(0.485f, 0.7f, 3.395f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 1, food33);
		personas.add(food33);
		Food food34 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, 3.395f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 1, food34);
		personas.add(food34);
		Food food35 = new Food(gl, glu, new Ponto(0.485f, 0.7f, 4.365f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 0, food35);
		personas.add(food35);
		Food food36 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, 4.365f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 0, food36);
		personas.add(food36);
		
		Food food37 = new Food(gl, glu, new Ponto(0.485f, 0.7f, -3.395f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 8, food37);
		personas.add(food37);
		Food food38 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, -3.395f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 8, food38);
		personas.add(food38);
		Food food39 = new Food(gl, glu, new Ponto(0.485f, 0.7f, -4.365f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(5, 9, food39);
		personas.add(food39);
		Food food40 = new Food(gl, glu, new Ponto(-0.485f, 0.7f, -4.365f), 0.15f, new Ponto(1.0f, 1.0f, 0.0f));
		tabuleiro.setposition(4, 9, food40);
		personas.add(food40);
		
		// PAREDES
		Ponto medidaVertical = new Ponto(0.37f, 1.3f, 0.97f);
		Ponto medidaHorizontal = new Ponto(0.97f, 1.3f, 0.37f);
		
		Wall wall1 = new Wall(gl, new Ponto(-1.735f, 0.25f, 1.24f), medidaVertical);
		tabuleiro.setposition(1, 2, wall1);
		personas.add(wall1);
		Wall wall2 = new Wall(gl, new Ponto(-1.735f, 0.25f, 1.24f - Character.MOVE_SPACE/2), medidaVertical);
		tabuleiro.setposition(1, 3, wall2);
		personas.add(wall2);
		Wall wall3 = new Wall(gl, new Ponto(-1.735f, 0.25f, 1.20f - Character.MOVE_SPACE*2f), medidaVertical);
		tabuleiro.setposition(1, 6, wall3);
		personas.add(wall3);
		Wall wall4 = new Wall(gl, new Ponto(-1.735f, 0.25f, 1.20f - Character.MOVE_SPACE*2.5f), medidaVertical);
		tabuleiro.setposition(1, 7, wall4);
		personas.add(wall4);
		
		Wall wall5 = new Wall(gl, new Ponto(-1.223f, 0.25f, 0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(2, 1, wall5);
		personas.add(wall5);
		Wall wall6 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE/2.0f, 0.25f, 0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(3, 1, wall6);
		personas.add(wall6);
		Wall wall7 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE*2.02f, 0.25f, 0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(6, 1, wall7);
		personas.add(wall7);
		Wall wall8 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE*2.53f, 0.25f, 0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(7, 1, wall8);
		personas.add(wall8);
		
		Wall wall9 = new Wall(gl, new Ponto(1.735f, 0.25f, 1.24f), medidaVertical);
		tabuleiro.setposition(8, 2, wall9);
		personas.add(wall9);
		Wall wall10 = new Wall(gl, new Ponto(1.735f, 0.25f, 1.24f - Character.MOVE_SPACE/2), medidaVertical);
		tabuleiro.setposition(8, 3, wall10);
		personas.add(wall10);
		Wall wall11 = new Wall(gl, new Ponto(1.735f, 0.25f, 1.20f - Character.MOVE_SPACE*2f), medidaVertical);
		tabuleiro.setposition(8, 6, wall11);
		personas.add(wall11);
		Wall wall12 = new Wall(gl, new Ponto(1.735f, 0.25f, 1.20f - Character.MOVE_SPACE*2.5f), medidaVertical);
		tabuleiro.setposition(8, 7, wall12);
		personas.add(wall12);
		
		Wall wall13 = new Wall(gl, new Ponto(-1.223f, 0.25f, -0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(2, 8, wall13);
		personas.add(wall13);
		Wall wall14 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE/2.0f, 0.25f, -0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(3, 8, wall14);
		personas.add(wall14);
		Wall wall15 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE*2.02f, 0.25f, -0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(6, 8, wall15);
		personas.add(wall15);
		Wall wall16 = new Wall(gl, new Ponto(-1.223f + Character.MOVE_SPACE*2.53f, 0.25f, -0.2425f*Character.MOVE_SPACE*7.4f), medidaHorizontal);
		tabuleiro.setposition(7, 8, wall16);
		personas.add(wall16);
		
		Wall wall17 = new Wall(gl, new Ponto(-0.7185f, 0.25f, 0.2407f), medidaVertical);
		tabuleiro.setposition(3, 4, wall17);
		personas.add(wall17);
		Wall wall18 = new Wall(gl, new Ponto(-0.7185f, 0.25f, 0.2407f -Character.MOVE_SPACE/1.95f), medidaVertical);
		tabuleiro.setposition(3, 5, wall18);
		personas.add(wall18);
		
		Wall wall19 = new Wall(gl, new Ponto(0.7185f, 0.25f, 0.258f), medidaVertical);
		tabuleiro.setposition(6, 4, wall19);
		personas.add(wall19);
		Wall wall20 = new Wall(gl, new Ponto(0.7185f, 0.25f, 0.258f -Character.MOVE_SPACE/1.95f), medidaVertical);
		tabuleiro.setposition(6, 5, wall20);
		personas.add(wall20);
		
		Wall wall21 = new Wall(gl, new Ponto(-0.2425f, 0.25f, 0.785f), medidaHorizontal);
		tabuleiro.setposition(4, 3, wall21);
		personas.add(wall21);
		Wall wall22 = new Wall(gl, new Ponto(0.2425f, 0.25f, -0.785f), medidaHorizontal);
		tabuleiro.setposition(5, 6, wall22);
		personas.add(wall22);
		
		return personas;
		
	}
	
    public static void desenharFaceQuadrada(GL gl, Ponto p1, Ponto p2, Ponto p3, Ponto p4, float r, float g, float b) {
        // setar cor
        gl.glColor3f (r, g, b);
        // Drawing Using Triangles
        gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
            gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
            gl.glVertex3f(p3.getX(), p3.getY(), p3.getZ());
            gl.glVertex3f(p4.getX(), p4.getY(), p4.getZ());
            gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        // Finished Drawing The Triangle
        gl.glEnd();
    }
    
    public static void desenharFaceQuadrada(GL gl, Ponto p1, Ponto p2, Ponto p3, Ponto p4, Texture texture, Point[] pontosCoordenadaTex) {
        gl.glPushMatrix();
            gl.glNormal3f(0, 1, 0);
            texture.enable();
            texture.bind();
            gl.glColor4f(1f, 1f, 1f, 1f);
            gl.glBegin(GL.GL_POLYGON);
                gl.glTexCoord2f((float)pontosCoordenadaTex[0].getX(), (float)pontosCoordenadaTex[0].getY());
                gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());

                gl.glTexCoord2f((float)pontosCoordenadaTex[1].getX(), (float)pontosCoordenadaTex[1].getY());
                gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());

                gl.glTexCoord2f((float)pontosCoordenadaTex[2].getX(), (float)pontosCoordenadaTex[2].getY());
                gl.glVertex3f(p3.getX(), p3.getY(), p3.getZ());

                gl.glTexCoord2f((float)pontosCoordenadaTex[3].getX(), (float)pontosCoordenadaTex[3].getY());
                gl.glVertex3f(p4.getX(), p4.getY(), p4.getZ());
            gl.glEnd();
            texture.disable();
        gl.glPopMatrix();
    }
    
    public static void desenharParalelepipedo(GL gl, float largura, float altura, float profundidade, Ponto pivo) {
        
        float l = largura/2;
        float h = altura/2;
        float p = profundidade/2;
        
        Ponto pontos[] = new Ponto[8];
        
        pontos[0] = new Ponto(pivo.getX() + l, pivo.getY() + h, pivo.getZ() - p);
        pontos[1] = new Ponto(pivo.getX() + l, pivo.getY() - h, pivo.getZ() - p);
        pontos[2] = new Ponto(pivo.getX() - l, pivo.getY() - h, pivo.getZ() - p);
        pontos[3] = new Ponto(pivo.getX() - l, pivo.getY() + h, pivo.getZ() - p);
        
        for (int i = 0 ; i < 4 ; i++) {
            pontos[i+4] = new Ponto(pontos[i].getX(), pontos[i].getY(), pontos[i].getZ() + profundidade);
        }
        
        desenharFaceQuadrada(gl, pontos[0], pontos[1], pontos[2], pontos[3], 1.0f, 1.0f, 1.0f);
        desenharFaceQuadrada(gl, pontos[4], pontos[5], pontos[6], pontos[7], 1.0f, 1.0f, 0.0f);
        desenharFaceQuadrada(gl, pontos[0], pontos[3], pontos[7], pontos[4], 1.0f, 0.0f, 1.0f);
        desenharFaceQuadrada(gl, pontos[2], pontos[1], pontos[5], pontos[6], 0.0f, 1.0f, 1.0f);
        desenharFaceQuadrada(gl, pontos[1], pontos[0], pontos[4], pontos[5], 0.0f, 0.0f, 1.0f);
        desenharFaceQuadrada(gl, pontos[3], pontos[2], pontos[6], pontos[7], 0.0f, 1.0f, 1.0f);
        
    }
    
    public static void desenharParalelepipedo(GL gl, float largura, float altura, float profundidade, Ponto pivo, Texture texture, Point[] coords) {
        
        float l = largura/2;
        float h = altura/2;
        float p = profundidade/2;
        
        Ponto pontos[] = new Ponto[8];
        
        pontos[0] = new Ponto(pivo.getX() + l, pivo.getY() + h, pivo.getZ() - p);
        pontos[1] = new Ponto(pivo.getX() + l, pivo.getY() - h, pivo.getZ() - p);
        pontos[2] = new Ponto(pivo.getX() - l, pivo.getY() - h, pivo.getZ() - p);
        pontos[3] = new Ponto(pivo.getX() - l, pivo.getY() + h, pivo.getZ() - p);
        
        for (int i = 0 ; i < 4 ; i++) {
            pontos[i+4] = new Ponto(pontos[i].getX(), pontos[i].getY(), pontos[i].getZ() + profundidade);
        }
        
        desenharFaceQuadrada(gl, pontos[0], pontos[1], pontos[2], pontos[3], texture, coords);
        desenharFaceQuadrada(gl, pontos[4], pontos[5], pontos[6], pontos[7], texture, coords);
        desenharFaceQuadrada(gl, pontos[0], pontos[3], pontos[7], pontos[4], texture, coords);
        desenharFaceQuadrada(gl, pontos[2], pontos[1], pontos[5], pontos[6], texture, coords);
        desenharFaceQuadrada(gl, pontos[1], pontos[0], pontos[4], pontos[5], texture, coords);
        desenharFaceQuadrada(gl, pontos[3], pontos[2], pontos[6], pontos[7], texture, coords);
        
    }
    
    public static void desenharParalelepipedo(GL gl, float largura, float altura, float profundidade, Ponto pivo, String texturePath, Point[] coords) {
        
        Texture texture = loadTexture(texturePath);
        
        float l = largura/2;
        float h = altura/2;
        
        Ponto pontos[] = new Ponto[8];
        
        pontos[0] = new Ponto(pivo.getX() + l, pivo.getY() + h, pivo.getZ());
        pontos[1] = new Ponto(pivo.getX() + l, pivo.getY() - h, pivo.getZ());
        pontos[2] = new Ponto(pivo.getX() - l, pivo.getY() - h, pivo.getZ());
        pontos[3] = new Ponto(pivo.getX() - l, pivo.getY() + h, pivo.getZ());
        
        for (int i = 0 ; i < 4 ; i++) {
            pontos[i+4] = new Ponto(pontos[i].getX(), pontos[i].getY(), pontos[i].getZ() + profundidade);
        }
        
        desenharFaceQuadrada(gl, pontos[0], pontos[1], pontos[2], pontos[3], texture, coords);
        desenharFaceQuadrada(gl, pontos[4], pontos[5], pontos[6], pontos[7], texture, coords);
        desenharFaceQuadrada(gl, pontos[0], pontos[3], pontos[7], pontos[4], texture, coords);
        desenharFaceQuadrada(gl, pontos[2], pontos[1], pontos[5], pontos[6], texture, coords);
        desenharFaceQuadrada(gl, pontos[1], pontos[0], pontos[4], pontos[5], texture, coords);
        desenharFaceQuadrada(gl, pontos[3], pontos[2], pontos[6], pontos[7], texture, coords);
        
    }
    
    public static void aplicarIluminacao(GL gl) {
  
//        float light_ka[] = {1.00f, 1.00f, 1.00f, 1.00f};
//        
//        // Ativa o uso da luz ambiente.
//        gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, light_ka, 1);
//
  
        gl.glEnable(GL.GL_COLOR_MATERIAL); 

        // Light 0
        gl.glEnable(GL.GL_LIGHT0);

        float light_pos0[] = {-5.00f, 10.00f, 5.00f, 1.00f};
        float light_ka0[] = {0.00f, 0.00f, 0.00f, 1.00f};
        float light_kd0[] = {1.00f, 1.00f, 1.00f, 1.00f};
        float light_ks0[] = {1.00f, 1.00f, 1.00f, 1.00f};

        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_pos0, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ka0, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_kd0, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_ks0, 0);
       
//        // Material 
//        float material_Ka[] = {0.11f, 0.06f, 0.11f, 1.00f};
//        float material_kd[] = {0.43f, 0.47f, 0.54f, 1.00f};
//        float material_ks[] = {0.33f, 0.33f, 0.52f, 1.00f};
//        float material_ke[] = {0.00f, 0.00f, 0.00f, 0.00f};
//        int material_se = 10;
//
//        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, material_Ka, 1);
//        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, material_kd, 1);
//        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, material_ks, 1);
//        gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, material_ke, 1);
//        gl.glMateriali(GL.GL_FRONT, GL.GL_SHININESS, material_se);   
   
    }
    
    public static void criarSpot(GL gl) {
       gl.glEnable(GL.GL_LIGHT1);
        
       float spot_direction[] = {0.00f, 0.00f, 0.00f};
       int spot_exponent = 30;
       int spot_cutoff = 180;

       gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPOT_DIRECTION, spot_direction, 0);
       gl.glLighti(GL.GL_LIGHT1, GL.GL_SPOT_EXPONENT, spot_exponent);
       gl.glLighti(GL.GL_LIGHT1, GL.GL_SPOT_CUTOFF, spot_cutoff);

       float kc = 1.00f;
       float kl = 0.00f;
       float kq = 0.00f;

       gl.glLightf(GL.GL_LIGHT1, GL.GL_CONSTANT_ATTENUATION, kc);
       gl.glLightf(GL.GL_LIGHT1, GL.GL_LINEAR_ATTENUATION, kl);
       gl.glLightf(GL.GL_LIGHT1, GL.GL_QUADRATIC_ATTENUATION, kq);
    }
    
    public static Texture loadTexture(String path){
        BufferedImage im;
        try {
            im = ImageIO.read(new File(path));
            return TextureIO.newTexture(im,true);
        } catch (IOException ex) {
            System.out.println("ERRO ao tentar carregar textura: "+ex.getMessage());
        }
        return null;
    }
    
    public static void rotacionar (GLU glu, Ponto eyeView, Ponto centerView, Ponto upView) {
        glu.gluLookAt(eyeView.getX(), eyeView.getY(), eyeView.getZ(), centerView.getX(), centerView.getY(), centerView.getZ(), upView.getX(), upView.getY(), upView.getZ());
    }
    
    public static void aplicarPerspectiva(GLU glu, double fovy, double aspect, double zNear, double zFar) {
        glu.gluPerspective(fovy, aspect, zNear, zFar);
    }
    
    public static double distanciaEntrePontos(Ponto p1, Ponto p2) {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2) + Math.pow(p2.getZ() - p1.getZ(), 2));
    }
    
    public static void lerObjeto(GL gl, String path) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(path+".obj");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
            GLModel glModel = new GLModel(buffer, true, path+".mtl", gl);
            glModel.opengldraw(gl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static GLModel getObjectModel(GL gl, String path) {
         FileInputStream file = null;
        try {
            file = new FileInputStream(path+".obj");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
            GLModel glModel = new GLModel(buffer, true, path+".mtl", gl);
            return glModel;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void desenharTabuleiro(GL gl) {
        // Desenhar tabuleiro
        gl.glPushMatrix();
            Texture tex1 = Transformacao.loadTexture("objetos/chao.jpg");
            Texture tex2 = Transformacao.loadTexture("objetos/parede.jpg");
            Point coords[] = {new Point(0,0),new Point(0,1), new Point(1,1), new Point(1,0)};
        
            gl.glNormal3f(0, 1, 0);
            Transformacao.desenharParalelepipedo(gl, 10.0f, 0.5f, 10.0f, new Ponto(0.0f, 0.0f, 0.0f), tex1, coords);
            Transformacao.desenharParalelepipedo(gl, 0.3f, 1.5f, 10.6f, new Ponto(5.15f, 0.5f, 0.0f), tex2, coords);
            Transformacao.desenharParalelepipedo(gl, 0.3f, 1.5f, 10.6f, new Ponto(-5.15f, 0.5f, 0.0f), tex2, coords);
            Transformacao.desenharParalelepipedo(gl, 10.0f, 1.5f, 0.3f, new Ponto(0.0f, 0.5f, 5.15f), tex2, coords);
            Transformacao.desenharParalelepipedo(gl, 10.0f, 1.5f, 0.3f, new Ponto(0.0f, 0.5f, -5.15f), tex2, coords);
        gl.glPopMatrix();
    }
    
    public static void textureExample(GL gl) {
        gl.glPushMatrix();
        gl.glNormal3f(0, 1, 0);
        Texture texture = Transformacao.loadTexture("objetos/grama.jpg");
        texture.enable();
        texture.bind();
        gl.glColor4f(0.6f,0.6f, 0.6f, 1.0f);
	gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(-8,-1.5f, 8);

            gl.glTexCoord2f(0,1);
            gl.glVertex3f(8,-1.5f, 8);
            
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(8,-1.5f, -8);
            
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(-8,-1.5f, -8);
        gl.glEnd();
        texture.disable();
        gl.glPopMatrix();
    }
    
    public static void testeDesenharComida(GL gl, GLU glu) {
         // Desenhar comida
        gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.7f, 0.0f);
            gl.glColor3f(1.0f, 1.0f, 0.0f);
            GLUquadric food = glu.gluNewQuadric();
            glu.gluQuadricDrawStyle(food, GLU.GLU_FILL);
            glu.gluQuadricNormals(food, GLU.GLU_FLAT);
            glu.gluQuadricOrientation(food, GLU.GLU_OUTSIDE);
            final float radius = 0.15f;
            final int slices = 10;
            final int stacks = 10;
            glu.gluSphere(food, radius, slices, stacks);
            glu.gluDeleteQuadric(food);
            
            gl.glTranslatef(0.0f, 0.7f, 1.0f);
            gl.glColor3f(1.0f, 1.0f, 0.0f);
            GLUquadric food2 = glu.gluNewQuadric();
            glu.gluQuadricDrawStyle(food2, GLU.GLU_FILL);
            glu.gluQuadricNormals(food2, GLU.GLU_FLAT);
            glu.gluQuadricOrientation(food2, GLU.GLU_OUTSIDE);
            glu.gluSphere(food2, radius, slices, stacks);
            glu.gluDeleteQuadric(food2);    
        gl.glPopMatrix();
    }
    
    public static void desenharTexto(TextRenderer textRenderer, String texto, Point pos, Cor cor, Dimension dimension) {
        textRenderer.beginRendering(dimension.width, dimension.height, true);
        textRenderer.setColor(cor.r, cor.g, cor.b, cor.alpha);
        textRenderer.draw(texto, pos.x, pos.y);
        textRenderer.endRendering();
        textRenderer.flush();
    }
    
    public static void desenharChao(GL gl, float tamanho, float diametro, Ponto pivo) {
        
        gl.glPushMatrix();
            float tam = tamanho;
            float d = diametro;

            boolean flagx, flagz;

            gl.glTranslatef(pivo.getX(), pivo.getY(), pivo.getZ());
            gl.glNormal3f(0, 1, 0);
            
            gl.glBegin(GL.GL_QUADS);
                flagx = false;
                for (float x=-tam;x<tam;x+=d) {
                    if(flagx) flagz = false;
                    else flagz = true;
                    for (float z=-tam;z<tam;z+=d) {
                        if(flagz) gl.glColor3f(0.4f, 0.4f, 0.4f);
                        else gl.glColor3f(1, 1, 1);

                        gl.glVertex3f(x, 0, z);
                        gl.glVertex3f(x+d, 0, z);
                        gl.glVertex3f(x+d, 0, z+d);
                        gl.glVertex3f(x, 0, z+d);

                        flagz=!flagz;

                    }
                    flagx=!flagx;
                }
            gl.glEnd();
        gl.glPopMatrix();
        
    }
    
}
