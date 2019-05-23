

class Peixe{
	private double[] pos;			// posicao peixe
	private double[] variacao_pos; 	// Variação das ultimas possicoes
	private double peso;			// peso peixe
	private double variacaoPeso;	// Variação no peso do Peixe
	private double fitness;			// Fitness 
	private double variacaoFitness;	// Variação do Fitness


	public double[] getVariacao_pos() {
		return variacao_pos;
	}
	public void setVariacao_pos(double[] variacao_pos) {
		this.variacao_pos = variacao_pos;
	}
	public Peixe(int dimensoes){
		pos = new double[dimensoes];
		variacao_pos = new double[dimensoes];
		//Inicialize todos os peixes em posições xi(0) aleatórias e distantes do mínimo global;
		double x = 0;
		for (int i = 0; i < dimensoes; i++) {
			x = Constantes.U(-1, 1);
			pos[i] = (x<0) ? x+(-5.12): x+5.12;
			/**
			 * Como o Minimo Global da Sphere é em {0,0,..} foi adicionado 4 para colocar o cardume
			 * longe do Minimo
			 */
		}

		//Inicialize aleatoriamente o peso ~wi(0) de todos os peixes;
		this.setPeso(Constantes.U(1,  10));
		this.fitness = 0;
	}
	public double[] getPos() {
		return pos;
	}
	public void setPos(double[] novaPos) {
		this.pos = novaPos;
		for (int i = 0; i < pos.length; i++) {
			variacao_pos[i] = novaPos[i] - this.pos[i];
		}
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		// Guarda a variacao do peso do peixe
		this.variacaoPeso = peso - this.peso;
		this.peso = peso;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public double getVariacaoFitness() {
		return variacaoFitness;
	}
	public void setVariacaoFitness(double variacaoFitness) {
		this.variacaoFitness = variacaoFitness;
	}
	public double getVariacaoPeso() {
		return variacaoPeso;
	}
	public void setVariacaoPeso(double variacaoPeso) {
		this.variacaoPeso = variacaoPeso;
	}

}