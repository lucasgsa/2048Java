package com.kpnzstudios;

import java.util.Random;

public class MainGameBruto {
	
	public int[][] matriz;
	private int[][] matrizBackup;
	int[] initRandom = {2,4};
	boolean ganhou = false;
	
	public MainGameBruto() {
		matriz = new int[4][4];
		adicionarAleatorio();
		matrizBackup = matriz;
	}
	
	public void adicionarAleatorio() {
		Random gerador = new Random();
		while (true){
			int x = gerador.nextInt(4);
			int y = gerador.nextInt(4);	
			if (matriz[x][y] == 0) {
				matriz[x][y] = initRandom[gerador.nextInt(2)];
				break;
			}
		}
	}
	
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
	
	public void resetar() {
		matrizBackup = matriz;
		matriz = new int[4][4];
		adicionarAleatorio();
	}
	
	public void voltar() {
		matriz = matrizBackup;
	}
	
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
	
	public int[][] clone(){
		int[][] temp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[i][j] = matriz[i][j];
			}
		}
		return temp;
	}
	
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
}
