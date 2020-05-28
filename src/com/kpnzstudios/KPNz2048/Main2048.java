package com.kpnzstudios.KPNz2048;

import java.util.Random;

public class Main2048 {
	
	/**
	 * Armazena uma matriz de arrays 4x4.
	 */
	public int[][] matriz;
	
	/**
	 * Matriz backup, serve para quando você deseja voltar uma jogada, ela sempre salva a jogada anterior.
	 */
	private int[][] matrizBackup;
	
	/**
	 * Valores possíveis de aparecer de forma inicial ou quando há um movimento.
	 * Todos os valores têm a mesma chance, portanto caso queira que um apareça mais que outro,
	 * escreva-o mais de uma vez.
	 * Ex: {2,2,2,4}
	 * 	2: 75%
	 * 	3: 25%
	 */
	int[] initRandom = {2, 2, 2, 4};
	
	/**
	 * Armazena se o jogador já chegou a 2048 em alguma peça e portanto ganhou o jogo.
	 */
	boolean ganhou = false;
	
	/**
	 * Construtor da classe.
	 */
	public Main2048() {
		matriz = new int[4][4];
		adicionarAleatorio();
		matrizBackup = matriz;
	}
	
	/**
	 * Define novos valores possíveis de aparecer de forma inicial ou quando há um movimento.
	 * @param array - array de inteiros.
	 */
	public void setInitRandom(int[] array) {
		initRandom = array;
	}
	
	/**
	 * Adiciona um valor aletório em alguma posição que não contenha nada(valor 0).
	 * O valor adicionado é escolhido randomicamente da array initRandom que pode ser modificado.
	 */
	public void adicionarAleatorio() {
		Random gerador = new Random();
		while (true){
			int x = gerador.nextInt(4);
			int y = gerador.nextInt(4);	
			if (matriz[x][y] == 0) {
				matriz[x][y] = initRandom[gerador.nextInt(initRandom.length)];
				break;
			}
		}
	}
	
	/**
	 * Retorna o placar do jogador até o momento.
	 * @return
	 */
	public int placar() {
		int soma = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (matriz[i][j] == 2048) ganhou = true;
				soma += matriz[i][j];
			}
		}
		return soma;
	}
	
	/**
	 * Retorna se o jogador já chegou a 2048 em alguma peça durante a rodada.
	 * @return boolean
	 */
	public boolean ganhou() {
		return ganhou;
	}
	
	/**
	 * Reseta o jogo para o começo.
	 * É possível utilizar a função voltar, caso tenha feito um reset por engano.
	 */
	public void resetar() {
		matrizBackup = matriz;
		matriz = new int[4][4];
		adicionarAleatorio();
	}
	
	/**
	 * Retorna as posições da ultima jogada.
	 */
	public void voltar() {
		matriz = matrizBackup;
	}
	
	/**
	 * Função de jogar a matriz para direita.
	 */
	public void paraDireita() {
		int[][] tempBackup = clone();
		LinhaHorizontal linha1 = new LinhaHorizontal(matriz[0][0], matriz[0][1], matriz[0][2], matriz[0][3]);
		boolean b1 = linha1.jogarDireita();
		matriz[0] = linha1.getValues();
		
		LinhaHorizontal linha2 = new LinhaHorizontal(matriz[1][0], matriz[1][1], matriz[1][2], matriz[1][3]);
		boolean b2 = linha2.jogarDireita();
		matriz[1] = linha2.getValues();
		
		LinhaHorizontal linha3 = new LinhaHorizontal(matriz[2][0], matriz[2][1], matriz[2][2], matriz[2][3]);
		boolean b3 = linha3.jogarDireita();
		matriz[2] = linha3.getValues();
		
		LinhaHorizontal linha4 = new LinhaHorizontal(matriz[3][0], matriz[3][1], matriz[3][2], matriz[3][3]);
		boolean b4 = linha4.jogarDireita();
		matriz[3] = linha4.getValues();
		
		if (b1 | b2 | b3 | b4) {
			adicionarAleatorio();
			matrizBackup = tempBackup;
		}
	}
	
	/**
	 * Função para jogar a matriz para esquerda.
	 */
	public void paraEsquerda() {
		int[][] tempBackup = clone();
		LinhaHorizontal linha1 = new LinhaHorizontal(matriz[0][3], matriz[0][2], matriz[0][1], matriz[0][0]);
		boolean b1 = linha1.jogarDireita();
		matriz[0] = linha1.reverseValues();
		
		LinhaHorizontal linha2 = new LinhaHorizontal(matriz[1][3], matriz[1][2], matriz[1][1], matriz[1][0]);
		boolean b2 = linha2.jogarDireita();
		matriz[1] = linha2.reverseValues();
		
		LinhaHorizontal linha3 = new LinhaHorizontal(matriz[2][3], matriz[2][2], matriz[2][1], matriz[2][0]);
		boolean b3 = linha3.jogarDireita();
		matriz[2] = linha3.reverseValues();
		
		LinhaHorizontal linha4 = new LinhaHorizontal(matriz[3][3], matriz[3][2], matriz[3][1], matriz[3][0]);
		boolean b4 = linha4.jogarDireita();
		matriz[3] = linha4.reverseValues();
		
		if (b1 | b2 | b3 | b4) {
			adicionarAleatorio();
			matrizBackup = tempBackup;
		}
	}
	
	/**
	 * Função para jogar a matriz para baixo.
	 */
	public void paraBaixo() {
		int[][] tempBackup = clone();
		LinhaHorizontal linha1 = new LinhaHorizontal(matriz[0][0], matriz[1][0], matriz[2][0], matriz[3][0]);
		boolean b1 = linha1.jogarDireita();
		matriz[0][0] = linha1.getValues()[0];
		matriz[1][0] = linha1.getValues()[1];
		matriz[2][0] = linha1.getValues()[2];
		matriz[3][0] = linha1.getValues()[3];
		
		LinhaHorizontal linha2 = new LinhaHorizontal(matriz[0][1], matriz[1][1], matriz[2][1], matriz[3][1]);
		boolean b2 = linha2.jogarDireita();
		matriz[0][1] = linha2.getValues()[0];
		matriz[1][1] = linha2.getValues()[1];
		matriz[2][1] = linha2.getValues()[2];
		matriz[3][1] = linha2.getValues()[3];
		
		LinhaHorizontal linha3 = new LinhaHorizontal(matriz[0][2], matriz[1][2], matriz[2][2], matriz[3][2]);
		boolean b3 = linha3.jogarDireita();
		matriz[0][2] = linha3.getValues()[0];
		matriz[1][2] = linha3.getValues()[1];
		matriz[2][2] = linha3.getValues()[2];
		matriz[3][2] = linha3.getValues()[3];
		
		LinhaHorizontal linha4 = new LinhaHorizontal(matriz[0][3], matriz[1][3], matriz[2][3], matriz[3][3]);
		boolean b4 = linha4.jogarDireita();
		matriz[0][3] = linha4.getValues()[0];
		matriz[1][3] = linha4.getValues()[1];
		matriz[2][3] = linha4.getValues()[2];
		matriz[3][3] = linha4.getValues()[3];
		
		if (b1 | b2 | b3 | b4) {
			adicionarAleatorio();
			matrizBackup = tempBackup;
		}
	}
	
	/**
	 * Função para jogar a matriz para cima.
	 */
	public void paraCima() {
		int[][] tempBackup = clone();
		LinhaHorizontal linha1 = new LinhaHorizontal(matriz[3][0], matriz[2][0], matriz[1][0], matriz[0][0]);
		boolean b1 = linha1.jogarDireita();
		matriz[0][0] = linha1.getValues()[3];
		matriz[1][0] = linha1.getValues()[2];
		matriz[2][0] = linha1.getValues()[1];
		matriz[3][0] = linha1.getValues()[0];
		
		LinhaHorizontal linha2 = new LinhaHorizontal(matriz[3][1], matriz[2][1], matriz[1][1], matriz[0][1]);
		boolean b2 = linha2.jogarDireita();
		matriz[0][1] = linha2.getValues()[3];
		matriz[1][1] = linha2.getValues()[2];
		matriz[2][1] = linha2.getValues()[1];
		matriz[3][1] = linha2.getValues()[0];
		
		LinhaHorizontal linha3 = new LinhaHorizontal(matriz[3][2], matriz[2][2], matriz[1][2], matriz[0][2]);
		boolean b3 = linha3.jogarDireita();
		matriz[0][2] = linha3.getValues()[3];
		matriz[1][2] = linha3.getValues()[2];
		matriz[2][2] = linha3.getValues()[1];
		matriz[3][2] = linha3.getValues()[0];
		
		LinhaHorizontal linha4 = new LinhaHorizontal(matriz[3][3], matriz[2][3], matriz[1][3], matriz[0][3]);
		boolean b4 = linha4.jogarDireita();
		matriz[0][3] = linha4.getValues()[3];
		matriz[1][3] = linha4.getValues()[2];
		matriz[2][3] = linha4.getValues()[1];
		matriz[3][3] = linha4.getValues()[0];
		
		if (b1 | b2 | b3 | b4) {
			adicionarAleatorio();
			matrizBackup = tempBackup;
		}
	}
	
	/**
	 * Função retorna uma matriz clone da principal.
	 */
	public int[][] clone(){
		int[][] temp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[i][j] = matriz[i][j];
			}
		}
		return temp;
	}
}
