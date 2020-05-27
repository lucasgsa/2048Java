package com.kpnzstudios;

public class LinhaHorizontal {
	int[] linha;
	public LinhaHorizontal(int arg0, int arg1, int arg2, int arg3) {
		linha = new int[]{arg0, arg1, arg2, arg3};
	}
	
	public int[] getValues() {
		return linha;
	}
	
	/**
	 * Retorna true caso haja mudança e false caso não.
	 * @return boolean
	 */
	public boolean jogarDireitaMovimentar() {
		int[] temp = {linha[0], linha[1], linha[2], linha[3]};
		for (int i = 0; i < 4; i++) {
			int j = i;
			if (linha[j] != 0) continue;
			while (j != 0) {
				int tempAnterior = linha[j-1];
				linha[j-1] = linha[j];
				linha[j] = tempAnterior;
				j--;
			}
		}
		
		if (temp[0] == linha[0] && temp[1] == linha[1] && temp[2] == linha[2] && temp[3] == linha[3]) {
			return false;
		}
		return true;
	}
	
	/**
	 * Retorna true caso haja mudança e false caso não.
	 * @return boolean
	 */
	public boolean jogarDireitaFundir() {
		int[] temp = {linha[0], linha[1], linha[2], linha[3]};
		for (int i = 3; i != 0; i--) {
			if (linha[i] == 0) continue;
			if (linha[i] == linha[i-1]) {
				linha[i] = linha[i]*2;
				linha[i-1] = 0;
			}
		}
		if (temp[0] == linha[0] && temp[1] == linha[1] && temp[2] == linha[2] && temp[3] == linha[3]) {
			return false;
		}
		return true;
	}
	
	/**
	 * Retorna true caso haja mudança e false caso não.
	 * @return boolean
	 */
	public boolean jogarDireita() {
		boolean b1 = this.jogarDireitaMovimentar();
		boolean b2 = this.jogarDireitaFundir();
		boolean b3 = this.jogarDireitaMovimentar();
		return (b1 | b2 | b3);
	}
	
	public int[] reverseValues() {
		int[] temp = {linha[3], linha[2], linha[1], linha[0]};
		return temp;
	}
}
