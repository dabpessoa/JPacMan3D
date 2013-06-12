package info.diegopessoa.cg.core;

import info.diegopessoa.cg.service.World;

import javax.swing.SwingUtilities;

public class Run {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new World();
            }
        });
	}
	
}
