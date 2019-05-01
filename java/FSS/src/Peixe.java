import java.util.concurrent.ThreadLocalRandom;

class Peixe{
	private double[] pos;		// posicao peixe
	private double peso;		// peso peixe
	private double fitness;
	public Peixe(int valuePos){
		pos = new double[valuePos];
		//Inicialize todos os peixes em posições xi(0) aleatórias e distantes do mínimo global;
		for (int i = 0; i < valuePos; i++) {
			pos[i] = ThreadLocalRandom.current().nextDouble(-1,1);
		}

		//Inicialize aleatoriamente o peso ~wi(0) de todos os peixes;
		this.setPeso(ThreadLocalRandom.current().nextDouble(1,10));
		this.fitness = 0;
	}
	public double[] getPos() {
		return pos;
	}
	public void setPos(double[] pos) {
		this.pos = pos;
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
}