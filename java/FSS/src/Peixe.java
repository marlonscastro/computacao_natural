import java.util.concurrent.ThreadLocalRandom;

class Peixe{
	private double[] pos;		// posicao peixe
	private double peso;		// peso peixe
	private double fitness;
	private double variacaoFitness;
	private double[] variacao_pos; // Variação das ultimas possicoes

	public double[] getVariacao_pos() {
		return variacao_pos;
	}
	public void setVariacao_pos(double[] variacao_pos) {
		this.variacao_pos = variacao_pos;
	}
	public Peixe(int valuePos){
		pos = new double[valuePos];
		variacao_pos = new double[valuePos];
		//Inicialize todos os peixes em posições xi(0) aleatórias e distantes do mínimo global;
		double x = 0;
		for (int i = 0; i < valuePos; i++) {
			x = Constantes.U(-1, 1);
			pos[i] = (x<0) ? x+(-4): x+4;
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
		for (int i = 0; i < novaPos.length; i++) {
			variacao_pos[i] = novaPos[i] - this.pos[i];
		}
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
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

}