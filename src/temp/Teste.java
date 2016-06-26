package temp;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

import com.sun.opengl.util.Animator;

public class Teste extends JFrame implements GLEventListener {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		Frame frame = new Frame("JOGL example");
		GLCanvas canvas = new GLCanvas();
		
		canvas.addGLEventListener(new Teste());
		frame.add(canvas);
		frame.setSize(640, 480);
		final Animator animator = new Animator(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		
		System.out.println("Teste");
		
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	

}
