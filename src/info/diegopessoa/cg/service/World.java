/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.service;

import info.diegopessoa.cg.bean.Cor;
import info.diegopessoa.cg.bean.Ponto;
import info.diegopessoa.cg.sprite.Character;
import info.diegopessoa.cg.sprite.Food;
import info.diegopessoa.cg.sprite.Ghost;
import info.diegopessoa.cg.sprite.Pacman;
import info.diegopessoa.sound.MusicPlayer;
import info.diegopessoa.sound.SoundManager;

import java.awt.AWTKeyStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import com.sun.opengl.util.j2d.TextRenderer;

/**
 *
 * @author diegopessoa
 */
public class World implements KeyListener {

	private float teta;
	private float beta;
	private float raio;
	private Character player;
	private List<Character> sprites;
	private boolean primeiraPessoa;
	private Tabuleiro<Character> tabuleiro;
	private MusicPlayer musicPlayer;
	private TextRenderer textRendererCreditos;
	private TextRenderer textRendererPontos;
	private TextRenderer textRendererGameOver;
	private TextRenderer textRendererWin;
	private TextRenderer textRendererAjuda;
	private boolean fim;
	private boolean running;
	private boolean ajuda;
	private List<String> helps;

	private GameGLCanvasListener gameListener;
	private GameGLWindow gameWindow;

	public static void main(String args[]) {
		new World();
	}

	public World()  {
		createWorld();
	}
	
	public void createWorld() {
		
		Ponto eyeView = new Ponto(0, 19, 0.01f);
		Ponto centerView = new Ponto(0, 0, 0);
		Ponto upView = new Ponto(0, 1, 0);
		this.raio = 10f;
		float fovy = 45.0f;
		float zNear = 1.0f;
		float zFar = 50.0f;
		this.teta = 0.0f;
		this.beta = 0.0f;
		helps = getTextosAjuda();
		this.sprites = new ArrayList<Character>();
		this.tabuleiro = new Tabuleiro<Character>(10, 10);

		Cam cam = new Cam(eyeView, centerView, upView, fovy, zNear, zFar, raio);
		gameListener = new GameGLCanvasListener(this, cam);
		gameWindow = new GameGLWindow("JPacMan3D - Diego Pessoa", new Dimension(640, 480));
		
		gameWindow.fullscreen();
		
		gameWindow.addKeyListener(this);
		gameWindow.addGLEventListener(gameListener);
		gameWindow.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Set<AWTKeyStroke> empty = Collections.emptySet();  
		gameWindow.getFrame().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, empty);  
		gameWindow.getFrame().setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, empty);  
		
		gameWindow.getFrame().addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() {

					public void run() {
						gameWindow.stopGameLoop();
						System.exit(0);
					}
				}).start();
			}
		});
		
		this.initTextos();
		
		gameWindow.startGameLoop();
		gameWindow.showFrame();
		
	}
	
	public void initTextos() {
		if (gameWindow.isFullscream()) {
			this.textRendererPontos = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 45), true, true, null, true);
			this.textRendererGameOver = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 75), true, true, null, true);
			this.textRendererWin = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 75), true, true, null, true);
		} else {
			this.textRendererPontos = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 25), true, true, null, true);
			this.textRendererGameOver = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 35), true, true, null, true);
			this.textRendererWin = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 35), true, true, null, true);
		}
		this.textRendererCreditos = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 11), true, true, null, true);
		this.textRendererAjuda = new TextRenderer(new Font("Arial", Font.TRUETYPE_FONT, 20), true, true, null, true);
	}

	public void gameResetScreenMode() {
		musicPlayer.stopAll();
		sprites.clear();
		tabuleiro.init();
		initTextos();
	}
	
	public void gameReset() {
		gameWindow.close();
		this.createWorld();
	}

	public void iniciarCenario() {

		if (running) {
			gameResetScreenMode();
		}

		player = new Pacman(gameListener.getGL(), new Ponto(-4.34f, 0.67f, 4.34f), Character.DIRECAO_OESTE);
		player.setDirecao(Character.DIRECAO_NORTE);
		tabuleiro.setposition(0, 0, player);

		musicPlayer = new MusicPlayer();
		sprites = Transformacao.criarListaPersonagens(gameListener.getGL(), gameListener.getGlu(), tabuleiro, musicPlayer);
		sprites.add(player);

		try {

			musicPlayer.addToPlayList("playback", SoundManager.loadSound("sounds/playback.wav"));
			musicPlayer.addToPlayList("start", SoundManager.loadSound("sounds/start.wav"));
			musicPlayer.addToPlayList("movimentoProibido", SoundManager.loadSound("sounds/pacman/pacman_chomp.wav"));
			musicPlayer.addToPlayList("death", SoundManager.loadSound("sounds/die.wav"));
			musicPlayer.addToPlayList("win", SoundManager.loadSound("sounds/win.mp3"));
			musicPlayer.addToPlayList("eat", SoundManager.loadSound("sounds/pacman/pacman_eatfruit.wav"));
			musicPlayer.addToPlayList("scream", SoundManager.loadSound("sounds/scream.wav"));
			musicPlayer.addToPlayList("gameover", SoundManager.loadSound("sounds/gameover.mp3"));
			musicPlayer.addToPlayList("eatghost", SoundManager.loadSound("sounds/pacman/pacman_eatghost.wav"));

			musicPlayer.loop("playback", 1000, -1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		running = true;

	}

	public void processarInformacoes() {
		if (!primeiraPessoa)
			gameListener.getCam().updateLookAt();
		else {
			Ponto posicao = player.getPosicao();

			if (player.getDirecao() == Character.DIRECAO_NORTE) {
				gameListener.getCam().setCenterView(new Ponto(posicao.getX(), posicao.getY(), posicao.getZ()-500));
			} else if (player.getDirecao() == Character.DIRECAO_SUL) {
				gameListener.getCam().setCenterView(new Ponto(posicao.getX(), posicao.getY(), posicao.getZ()+500));
			} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
				gameListener.getCam().setCenterView(new Ponto(posicao.getX()+500, posicao.getY(), posicao.getZ()));
			} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
				gameListener.getCam().setCenterView(new Ponto(posicao.getX()-500, posicao.getY(), posicao.getZ()));
			}

			gameListener.getCam().setEyeView(new Ponto(posicao.getX(), posicao.getY()+0.1f, posicao.getZ()));
			gameListener.getCam().updateLookAt();
		}    
	}
	
	public List<String> getTextosAjuda() {
		List<String> textos = new ArrayList<String>();
		textos.add("Ajuda do Jogo:");
		textos.add("Movimentação no tabuleiro: use as setas do teclado.");
		textos.add("Modo Tela Cheia: F");
		textos.add("Pausar música de fundo: P");
		textos.add("Reiniciar o jogo: R");
		return textos;
	}

	public void desenharCenario() {

		Transformacao.aplicarIluminacao(gameListener.getGL());
		//		Transformacao.desenharChao(gl, 5, 1, new Ponto(0.0f, 0.26f, 0.0f));
		Transformacao.desenharTabuleiro(gameListener.getGL());

		// Desenhar personagens
		for(Character sprite : sprites) {
			sprite.process();
		}

		// Inserindo Texto
		if (gameWindow.isFullscream()) {
			Transformacao.desenharTexto(textRendererCreditos, "Diego Pessoa (dabpessoa@gmail.com)", new Point(gameWindow.getWidth()-255, 40), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			Transformacao.desenharTexto(textRendererPontos, "Pontos: "+((Pacman)player).getPontos(), new Point(20, 115), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			Transformacao.desenharTexto(textRendererPontos, "Vidas: "+((Pacman)player).getVidas(), new Point(47, 60), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
		} else {
			Transformacao.desenharTexto(textRendererCreditos, "Diego Pessoa (dabpessoa@gmail.com)", new Point(gameWindow.getWidth()-205, 15), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			Transformacao.desenharTexto(textRendererPontos, "Pontos: "+((Pacman)player).getPontos(), new Point(10, 50), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			Transformacao.desenharTexto(textRendererPontos, "Vidas: "+((Pacman)player).getVidas(), new Point(25, 15), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
		}
		

		if (ajuda) {
			int deltaY = 30;
			for (String ajuda : helps) {
				Transformacao.desenharTexto(textRendererAjuda, ajuda, new Point(20, gameWindow.getHeight()-deltaY), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
				deltaY += 20;
			}
		}

		if (((Pacman)player).getVidas() <= 0) {
			Transformacao.desenharTexto(textRendererGameOver, "GAME OVER", new Point((gameWindow.getWidth()/2)-210, (gameWindow.getHeight()/2)+30), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			if (!fim) {
				musicPlayer.stopAll();
				musicPlayer.play("scream");
				musicPlayer.play("gameover");
				fim = true;
			}
		}

		if (((Pacman)player).getPontos() >= 40) {
			Transformacao.desenharTexto(textRendererWin, "PARABÉNS", new Point((gameWindow.getWidth()/2)-210, (gameWindow.getHeight()/2)+30), new Cor(1.0f, 1.0f, 1.0f), gameWindow.getSize());
			if (!fim) {
				musicPlayer.stopAll();
				musicPlayer.play("win");
				fim = true;
			}
		}

	}

	public void worldMove(Character character, Tabuleiro<Character> tabuleiro, int direcao) {
		if (tabuleiro.isFreeSpace(character, direcao)) {
			character.move(direcao);
			tabuleiro.move(character, direcao);
		} else {
			Point pontoObjeto = tabuleiro.getNextPoint(character, direcao);
			if (!tabuleiro.isOutOfSpace(pontoObjeto.x, pontoObjeto.y)) {
				List<Character> itens = tabuleiro.getObjects(pontoObjeto.x, pontoObjeto.y);
				if (itens != null) {

					Ghost ghost = null;
					Food food = null;
					for (Character item : itens) {
						if (item instanceof Ghost)
							ghost = (Ghost) item;
						else if (item instanceof Food)
							food = (Food) item;
					}

					if (ghost != null) {
						musicPlayer.play("death");
						((Pacman)player).die();
						character.setPosicao(character.getPosicaoInicial());
						Point pontoCharacter = tabuleiro.getPosition(character);
						tabuleiro.clearPosition(pontoCharacter.x, pontoCharacter.y);
						tabuleiro.setposition(0, 0, character);
					} else if (food != null) {
						sprites.remove(food);
						tabuleiro.clearPosition(pontoObjeto.x, pontoObjeto.y);
						food = null;
						musicPlayer.play("eat");
						character.move(direcao);
						tabuleiro.move(character, direcao);
						((Pacman)player).addPontos();
					} else {
						musicPlayer.play("movimentoProibido");
					}

				}

			} else {
				musicPlayer.play("movimentoProibido");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		float amountX, amountY, amountZ;
		Ponto eyeView = gameListener.getCam().getEyeView();

		switch (e.getKeyCode()) {
		case KeyEvent.VK_L:
			// TURN RIGHT
			System.out.println("right");

			teta += 4;
			amountX = raio*(float)Math.cos(Math.toRadians(teta));
			amountZ = raio*(float)Math.sin(Math.toRadians(teta));

			eyeView.setX(amountX);
			eyeView.setZ(amountZ);

			break;
		case KeyEvent.VK_J:
			// TURN LEFT
			System.out.println("left");
			teta -= 4;
			amountX = raio*(float)Math.cos(Math.toRadians(teta));
			amountZ = raio*(float)Math.sin(Math.toRadians(teta));

			eyeView.setX(amountX);
			eyeView.setZ(amountZ);

			break;
		case KeyEvent.VK_I:
			// TURN UP
			System.out.println("up");

			beta += 4;
			amountY = raio*(float)Math.sin(Math.toRadians(beta));
			amountZ = raio*(float)Math.cos(Math.toRadians(beta));

			eyeView.setY(amountY);
			eyeView.setZ(amountZ);

			break;
		case KeyEvent.VK_K:
			// TURN DOWN
			System.out.println("down");

			beta -= 4;
			amountY = raio*(float)Math.sin(Math.toRadians(beta));
			amountZ = raio*(float)Math.cos(Math.toRadians(beta));

			eyeView.setY(amountY);
			eyeView.setZ(amountZ);

			break;
		case KeyEvent.VK_UP:
			// MOVE OBJECT FRONT WAY
			if (primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					worldMove(player, tabuleiro, Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_OESTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_NORTE);
				worldMove(player, tabuleiro, Character.DIRECAO_NORTE);
			}
			
			break;
		case KeyEvent.VK_DOWN:
			// TURN OBJECT 180 graus
			if (primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_LESTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_SUL);
				worldMove(player, tabuleiro, Character.DIRECAO_SUL);
			}
			
			break;
		case KeyEvent.VK_RIGHT:
			// TURN OBJECT RIGHT
			if (primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_NORTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_LESTE);
				worldMove(player, tabuleiro, Character.DIRECAO_LESTE);
			}
			break;
		case KeyEvent.VK_LEFT:
			// TURN OBJECT LEFT
			if (primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				}
			} else {
				player.setDirecao(Character.DIRECAO_OESTE);
				worldMove(player, tabuleiro, Character.DIRECAO_OESTE);
			}
			break;
		case KeyEvent.VK_W:
			// MOVE OBJECT NORTH
			if (!primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					worldMove(player, tabuleiro, Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					worldMove(player, tabuleiro, Character.DIRECAO_OESTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_NORTE);
				worldMove(player, tabuleiro, Character.DIRECAO_NORTE);
			}
			break;
		case KeyEvent.VK_S:
			// MOVE OBJECT SOUTH
			if (!primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_LESTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_SUL);
				worldMove(player, tabuleiro, Character.DIRECAO_SUL);
			}
			break;
		case KeyEvent.VK_A:
			// MOVE OBJECT LATERALMENTE LEFT
			if (!primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_NORTE);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				}
			} else {
				player.setDirecao(Character.DIRECAO_OESTE);
				worldMove(player, tabuleiro, Character.DIRECAO_OESTE);
			}
			break;
		case KeyEvent.VK_D:
			// MOVE OBJECT LATERALMENTE RIGHT
			if (!primeiraPessoa) {
				if (player.getDirecao() == Character.DIRECAO_NORTE) {
					player.setDirecao(Character.DIRECAO_LESTE);
				} else if (player.getDirecao() == Character.DIRECAO_SUL) {
					player.setDirecao(Character.DIRECAO_OESTE);
				} else if (player.getDirecao() == Character.DIRECAO_LESTE) {
					player.setDirecao(Character.DIRECAO_SUL);
				} else if (player.getDirecao() == Character.DIRECAO_OESTE) {
					player.setDirecao(Character.DIRECAO_NORTE);
				}
			} else {
				player.setDirecao(Character.DIRECAO_LESTE);
				worldMove(player, tabuleiro, Character.DIRECAO_LESTE);
			}
			
			break;
		case KeyEvent.VK_1:
			// CAMERA 1ª PESSOA
			primeiraPessoa = true;
			break;
		case KeyEvent.VK_3:
			// CAMERA 3ª PESSOA
			primeiraPessoa = false;
			gameListener.getCam().setCenterView(new Ponto(0, 0, 0));
			gameListener.getCam().setEyeView(new Ponto(0, 19, 0.01f));
			break;
		case KeyEvent.VK_ESCAPE: {
			// quit
			// Use a dedicate thread to run the stop() to ensure that the
			// animator stops before program exits.
			new Thread() {
				@Override
				public void run() {
					gameWindow.stopGameLoop();
					System.exit(0);
				}
			}.start();
			break;
		}
		case KeyEvent.VK_F: {
			gameWindow.fullscreen();
			break;
		}
		case KeyEvent.VK_R: {
			gameReset();
			break;
		}
		case KeyEvent.VK_P: {
			musicPlayer.pause("playback");
			break;
		}
		case KeyEvent.VK_TAB: {
			ajuda = true;
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			ajuda = false;
		}
	}

}
