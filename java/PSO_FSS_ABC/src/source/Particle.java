package source;

public class Particle {
	private int ID;
	private double fitness;
	private double[] position;
	private double[] velocity;
	private Pbest pbest;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
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
	public Pbest getPbest() {
		return pbest;
	}
	public void setPbest(Pbest pbest) {
		this.pbest = pbest;
	}
}
