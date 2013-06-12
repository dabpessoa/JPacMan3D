package info.diegopessoa.cg.service;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GameGLCanvasListener implements GLEventListener {

	private World world;
	private GL gl;
	private GLU glu;
	private Cam cam;
	
	public GameGLCanvasListener(World world, Cam cam) {
		System.out.println("Construtor do GLListener...............................");
		this.world = world;
		this.cam = cam;
		this.glu = new GLU();
	}
	
	public void init(GLAutoDrawable drawable) {

		System.out.println("INIT..........................");
		
		gl = drawable.getGL();

		// Enable VSync
		gl.setSwapInterval(1);

		// Setup the drawing area and shading mode
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f);      // set clear depth value to farthest
		gl.glEnable(GL.GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL.GL_LEQUAL);  // the type of depth test to do
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST); // best perspective correction
		gl.glShadeModel(GL.GL_SMOOTH); // blends colors nicely, and smoothes out lighting

		// Habilitando Iluminação
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		gl.glEnable(GL.GL_LIGHTING);

		// Habilitando Textura
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
		gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);

		// Iniciar cen�rio no mundo
		world.iniciarCenario();
		
	}
	
	public void display(GLAutoDrawable drawable) {
//		        System.out.println("DILPLAY.....................................");

		gl = drawable.getGL();

		// Clear the drawing area
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// Reset the current matrix to the "identity"
		gl.glLoadIdentity();

		// Drawing code area.
		world.processarInformacoes();
		world.desenharCenario();

		// Flush all drawing operations to the graphics card
		gl.glFlush();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.out.println("RESHAPE....................................");
  
		gl = drawable.getGL();
		glu = new GLU();

		if (height == 0) height = 1;   // prevent divide by zero
	    float aspect = (float)width / height;

	    // Set the view port (display area) to cover the entire window
	    gl.glViewport(0, 0, width, height);
	      
	    // Setup perspective projection, with aspect ratio matches viewport
	    gl.glMatrixMode(GL.GL_PROJECTION);  // choose projection matrix
	    gl.glLoadIdentity();             // reset projection matrix
	    
	    cam.pespectiva(glu, aspect);
		
		// Enable the model-view transform
	    gl.glMatrixMode(GL.GL_MODELVIEW);
	    gl.glLoadIdentity(); // reset
	    
	}
	
	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		System.out.println("Display Changed................................");
	}	
	
	public GL getGL() {
		return gl;
	}
	
	public GLU getGlu() {
		return glu;
	}
	
	public Cam getCam() {
		return cam;
	}
	
}
