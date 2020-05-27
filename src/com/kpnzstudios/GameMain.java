package com.kpnzstudios;

import javax.swing.JFrame;

public class GameMain {
	public static void main(String[] args) {
		Game2048 game = new Game2048();
		JFrame frame = new JFrame("2048");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		new Thread(game).start();
	}
}
