package com.kpnzstudios;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game2048 extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	final int[] windowsSize = {800,1000};
	
	MainGameBruto game = new MainGameBruto();
	
	KeyBoardQueue keyState = new KeyBoardQueue();
	
	public Game2048() {
		this.setPreferredSize(new Dimension(windowsSize[0], windowsSize[1]));
		this.addKeyListener(this);
	}
	
	@Override
	public void run() {
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checarMovimento() {
		if (keyState.hasUp()) {
			game.paraCima();
		}
		if (keyState.hasDown()) {
			game.paraBaixo();
		}
		if (keyState.hasLeft()) {
			game.paraEsquerda();
		}
		if (keyState.hasRight()) {
			game.paraDireita();
		}
		if (keyState.hasR()) {
			game.resetar();
		}
		if (keyState.hasZ()) {
			game.voltar();
		}
	}
	
	public void tick() {
		checarMovimento();
	}
	
	public void desenharEsqueleto(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, windowsSize[0], windowsSize[1]);
		
		g.setColor(Color.BLACK);
		g.drawLine(200, 0, 200, 800);
		
		g.drawLine(400, 0, 400, 800);
		
		g.drawLine(600, 0, 600, 800);
		
		g.drawLine(0, 200, 800, 200);
		
		g.drawLine(0, 400, 800, 400);
		
		g.drawLine(0, 600, 800, 600);
		
		g.setColor(Color.LIGHT_GRAY);
		
		g.fillRect(0, 800, 800, 200);
	}
	
	public void desenharNumeros(Graphics g) {
		g.setFont(new Font("Arial", 1, 40));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (game.matriz[j][i] != 0 ) {
					int corInt = (int) (Math.sqrt(game.matriz[j][i])*10);
					g.setColor(new Color(corInt, corInt, corInt));
					g.fillRect(200*i, 200*j, 200, 200);
				}
				g.setColor(Color.BLUE);
				g.drawString(game.matriz[j][i]+"", 200*i+50, 200*j+50);	
			}
		}
	}
	
	public void desenharPlacarEMenu(Graphics g) {
		g.setFont(new Font("Times New Roman", 1, 40));
		if (game.ganhou) g.setColor(Color.GREEN);
		g.drawString("PONTUAÇÃO: "+game.placar(), 230, 850);	
		g.setFont(new Font("Times New Roman", 1, 20));
		g.setColor(Color.BLACK);
		g.drawString("Pressiona Z - para voltar a jogada. ", 200, 900);	
		g.drawString("Pressione R - para resetar a partida. (Cuidado)", 200, 950);	
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		desenharEsqueleto(g);
		
		desenharNumeros(g);
		
		desenharPlacarEMenu(g);
		
	    g.dispose();
		
		bs.show();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyState.pressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
