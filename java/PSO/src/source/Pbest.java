package source;

public class Pbest {
	private int ID;
	private double fitness;
	private double[] position;
	private double[] velocity;
	private int iteration;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fit) {
		this.fitness = fit;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	public double[] getVelocity() {
		return velocity;
	}
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}
	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

}
