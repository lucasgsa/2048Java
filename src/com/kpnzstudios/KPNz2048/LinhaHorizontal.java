package com.kpnzstudios.KPNz2048;

/**
 * 
 * Essa classe considera uma array de inteiros de 4 valores, podendo realizar opera��es para direita e esquerda.
 * J� que no jogo em quest�o, qualquer tipo de movimento pode ser transformado e divido em apenas uma linha,
 * e independente da dire��o, pode-se usar a mesma classe LinhaHorizontal para todos os tipos de movimenta��o,
 * precisando apenas de uma simples convers�o de dire��o.
 * 
 * @author Lucasgsa
 *
 */
public class LinhaHorizontal {
	
	/**
	 * Guarda os 4 valores da linha.
	 */
	int[] linha;
	
	/**
	 * Construtor
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public LinhaHorizontal(int arg0, int arg1, int arg2, int arg3) {
		linha = new int[]{arg0, arg1, arg2, arg3};
	}
	
	/**
	 * Retorna uma array com os valores da linha.
	 * @return
	 */
	public int[] getValues() {
		return linha;
	}
	
	/**
	 * Retorna uma array com os valores da linha de forma inversa.
	 * Deve ser usada quando se tem um movimento para esquerda.
	 * @return
	 */
	private int[] reverseValues() {
		int[] temp = new int[linha.length];
		for (int i = 0; i < linha.length; i++) {
			temp[linha.length-i-1] = linha[i];
		}
		return temp;
	}
	
	/**
	 * Movimenta todos os quadrados para direita caso haja espa�o.
	 * Deixando apenas os 0 na esquerda caso houver.
	 * Retorna true caso haja mudan�a e false caso n�o.
	 * @return boolean
	 */
	public boolean jogarDireitaMovimentar() {
		
		// Aqui foi implementada uma insertion sort apenas com os 0, sempre que acha um 0 ele move ele para o come�o da array.
		
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
		
		// Verifica se houve alguma mudan�a na linha ap�s esse movimento.
		if (temp[0] == linha[0] && temp[1] == linha[1] && temp[2] == linha[2] && temp[3] == linha[3]) {
			return false;
		}
		return true;
	}
	
	/**
	 * Funde quadrados de valores iguais que estejam lado a lado, sendo considerado espa�os(valor 0),
	 * por isso deve ser sempre utilizada juntamente com a jogarDireitaMovimentar.
	 * Retorna true caso haja mudan�a e false caso n�o.
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
		
		// Verifica se houve alguma mudan�a na linha ap�s esse movimento.
		if (temp[0] == linha[0] && temp[1] == linha[1] && temp[2] == linha[2] && temp[3] == linha[3]) {
			return false;
		}
		return true;
	}
	
	/**
	 * Faz uma jun��o entre as duas fun��es de movimenta��o da linha anteriormente criadas,
	 * de forma que fa�am com que elas funcionem de forma harm�nica.
	 * Retorna true caso haja mudan�a e false caso n�o.
	 * @return boolean
	 */
	public boolean jogarDireita() {
		boolean b1 = this.jogarDireitaMovimentar();
		boolean b2 = this.jogarDireitaFundir();
		boolean b3 = this.jogarDireitaMovimentar();
		return (b1 | b2 | b3);
	}
	
	/**
	 * Faz uma jun��o entre as duas fun��es de movimenta��o da linha anteriormente criadas,
	 * de forma que fa�am com que elas funcionem de forma harm�nica.
	 * Retorna true caso haja mudan�a e false caso n�o.
	 * @return
	 */
	public boolean jogarEsquerda() {
		// Para mover pra esquerda basta inverter a array, mover para a direita e reverter novamente.
		linha = reverseValues();
		boolean b1 = this.jogarDireita();
		linha = reverseValues();
		return b1;
	}
}
