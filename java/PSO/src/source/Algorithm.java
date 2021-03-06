package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import util.*;

public class Algorithm {
	private ArrayList<Particle> swarm = new ArrayList<Particle>();
	private Pbest g = new Pbest();
	private Pbest gLocal = new Pbest();

	private ArrayList<Pbest> gBests = new ArrayList<Pbest>();
	int CURRENT_ITERATION = 0;
	int CURRENT_ITERATION_SWARM = 0;
	Random rand = new Random();

	protected void initializeSwarm() {
		Particle p;
		for (int i = 0; i < Constants.SWARM_SIZE - Constants.FOCAL_BEST_PARTICLE; i++) {
			p = new Particle();

			double[] dimension = new double[Constants.PROBLEM_DIMENSION];
			for (int x = 0; x < Constants.PROBLEM_DIMENSION; x++) {
				dimension[x] = Problem.DIMENSION_LOW + rand.nextDouble()*(Problem.DIMENSION_HIGH - Problem.DIMENSION_LOW);
			}

			double[] velocity = new double[Constants.PROBLEM_DIMENSION];
			for (int x = 0; x < Constants.PROBLEM_DIMENSION; x++) {
				velocity[x] = Constants.V_MIN + rand.nextDouble()*(Constants.V_MAX - Constants.V_MIN);
			}

			p.setPosition(dimension);
			p.setVelocity(velocity);
			p.setFitness(Problem.evaluate(dimension));
			updatePbest(p);

			swarm.add(p);
		}
	}

	protected void updatePbest(Particle p) {
		if (CURRENT_ITERATION <= 0	|| (p.getFitness() < p.getPbest().getFitness())) {
			Pbest pbest = new Pbest();
			pbest.setFitness(p.getFitness());
			pbest.setID(p.getID());
			pbest.setPosition(p.getPosition());
			pbest.setVelocity(p.getVelocity());
			p.setPbest(pbest);
		}
	}

	protected void updateVelocity(Particle p) {
		double r1 = rand.nextDouble();
		double r2 = rand.nextDouble();

		double[] newVel = new double[Constants.PROBLEM_DIMENSION];

		for (int k = 0; k < Constants.PROBLEM_DIMENSION; k++) {
			double newValAux = 0;

			if (!Constants.T_CLERC) {
				newValAux = (Constants.W * p.getVelocity()[k])
						+ ((r1 * Constants.C1) * (p.getPbest().getPosition()[k] 
						- p.getPosition()[k]))
						+ ((r2 * Constants.C2) * (g.getPosition()[k] - p
								.getPosition()[k]));

			} else {
				newValAux = Constants.CLERC
						* (p.getVelocity()[k] + r1
								* Constants.C1
								* (p.getPbest().getPosition()[k] - p
										.getPosition()[k]))
						+ (r2 * Constants.C2 * (g.getPosition()[k] - p
								.getPosition()[k]));
			}

			if (newValAux < Constants.V_MIN)
				newValAux = Constants.V_MIN;
			else if (newValAux > Constants.V_MAX)
				newValAux = Constants.V_MAX;

			newVel[k] = newValAux;
		}

		p.setVelocity(newVel);

	}

	protected void updatePosition(Particle p) {
		double[] newLoc = new double[Constants.PROBLEM_DIMENSION];
		for (int j = 0; j < Constants.PROBLEM_DIMENSION; j++) {
			double velAux = 0;
			velAux = p.getPosition()[j] + p.getVelocity()[j];
			if (velAux < Problem.DIMENSION_LOW)
				velAux = Problem.DIMENSION_LOW;
			else if (velAux > Problem.DIMENSION_HIGH)
				velAux = Problem.DIMENSION_HIGH;
			newLoc[j] = velAux;
		}

		p.setPosition(newLoc);

	}

	protected void updateGbest() {

		int bestParticleIndex = Util.getMinPos(swarm);

		if (CURRENT_ITERATION == 0
				|| swarm.get(bestParticleIndex).getFitness() < g.getFitness()) {
			g = swarm.get(bestParticleIndex).getPbest();
			g.setIteration(CURRENT_ITERATION);
		}
		gBests.add(g);
	}

	// Focal
	protected void updateFocalbest() {

		int bestParticleIndex = Util.getMinPos(swarm);

		if (CURRENT_ITERATION == 0
				|| swarm.get(bestParticleIndex).getFitness() < g.getFitness()) {
			g = swarm.get(bestParticleIndex).getPbest();
			g.setIteration(CURRENT_ITERATION);
		}
		gBests.add(g);
	}

	protected void updateLocalGbest() {

		int bestParticleIndex = Util.getMinPos(swarm);

		if (CURRENT_ITERATION == 0
				|| swarm.get(bestParticleIndex).getFitness() < gLocal
						.getFitness()) {
			gLocal = swarm.get(bestParticleIndex).getPbest();
			gLocal.setIteration(CURRENT_ITERATION);
		}

		gBests.add(gLocal);
	}

	protected void updateLbest(int particlePos) {
		Pbest gbestAux = swarm.get(particlePos).getPbest();
		int posBefore, posAfter;

		// First one
		if (particlePos == 0) {
			posBefore = Constants.SWARM_SIZE - 1;
			posAfter = 1;

			// Last one
		} else if (particlePos == (Constants.SWARM_SIZE - 1)) {
			posBefore = particlePos - 1;
			// posAfter = SWARM_SIZE-1;
			
			posAfter = 0;

		} else {
			posBefore = particlePos - 1;
			posAfter = particlePos + 1;
		}

		if (swarm.get(posBefore).getFitness() < gbestAux.getFitness()) {
			gbestAux.setFitness(swarm.get(posBefore).getFitness());
			gbestAux.setID(swarm.get(posBefore).getID());
			gbestAux.setIteration(CURRENT_ITERATION);
			gbestAux.setPosition(swarm.get(posBefore).getPosition());
			gbestAux.setVelocity(swarm.get(posBefore).getVelocity());

		} else if (swarm.get(posAfter).getFitness() < gbestAux.getFitness()) {
			gbestAux.setFitness(swarm.get(posAfter).getFitness());
			gbestAux.setID(swarm.get(posAfter).getID());
			gbestAux.setIteration(CURRENT_ITERATION);
			gbestAux.setPosition(swarm.get(posAfter).getPosition());
			gbestAux.setVelocity(swarm.get(posAfter).getVelocity());
		}

		// localBest
		g = gbestAux;
	}

	protected void gBestToplogy() throws IOException {
		initializeSwarm();
		updateGbest();

		for (CURRENT_ITERATION = 1; CURRENT_ITERATION < Constants.MAX_ITERATION; CURRENT_ITERATION++) {
			
			if (Constants.DECAY)
				isDecay();
			
			for (CURRENT_ITERATION_SWARM = 0; 
				 CURRENT_ITERATION_SWARM < Constants.SWARM_SIZE; 
				 CURRENT_ITERATION_SWARM++) {

				updateVelocity(swarm.get(CURRENT_ITERATION_SWARM));
				updatePosition(swarm.get(CURRENT_ITERATION_SWARM));
				swarm.get(CURRENT_ITERATION_SWARM).setFitness(
						Problem.evaluate(swarm.get(CURRENT_ITERATION_SWARM)
								.getPosition()));

				updatePbest(swarm.get(CURRENT_ITERATION_SWARM));
			}
			updateGbest();
		}
		// printResult();
		if(Constants.STORAGE_RESUTLS)SalveSimulations.storageSimulationFile(gBests);
	}

	protected void lBestTopology() throws IOException {
		initializeSwarm();
		updateLocalGbest();// Fitness list

		for (CURRENT_ITERATION = 1; CURRENT_ITERATION < Constants.MAX_ITERATION; CURRENT_ITERATION++) {
			if (Constants.DECAY)
				isDecay();

			for (CURRENT_ITERATION_SWARM = 0; CURRENT_ITERATION_SWARM < Constants.SWARM_SIZE; CURRENT_ITERATION_SWARM++) {

				updatePbest(swarm.get(CURRENT_ITERATION_SWARM));
				updateLbest(CURRENT_ITERATION_SWARM);

				updateVelocity(swarm.get(CURRENT_ITERATION_SWARM));
				updatePosition(swarm.get(CURRENT_ITERATION_SWARM));
				swarm.get(CURRENT_ITERATION_SWARM).setFitness(
						Problem.evaluate(swarm.get(CURRENT_ITERATION_SWARM)
								.getPosition()));

			}
			updateLocalGbest();
		}
		 //printResult();
		if(Constants.STORAGE_RESUTLS)SalveSimulations.storageSimulationFile(gBests);

	}

	protected void setFocalGbest() {
		g = swarm.get(Util.getMinPos(swarm)).getPbest();
	}

	protected void focalBestTopology() throws IOException {
		// Initiate particles
		initializeSwarm();
		setFocalGbest();

		for (CURRENT_ITERATION = 1; CURRENT_ITERATION <= Constants.MAX_ITERATION; CURRENT_ITERATION++) {

			if (Constants.DECAY)
				isDecay();

			for (CURRENT_ITERATION_SWARM = 0; CURRENT_ITERATION_SWARM < Constants.SWARM_SIZE
					- Constants.FOCAL_BEST_PARTICLE; CURRENT_ITERATION_SWARM++) {

				updateVelocity(swarm.get(CURRENT_ITERATION_SWARM));
				updatePosition(swarm.get(CURRENT_ITERATION_SWARM));
				swarm.get(CURRENT_ITERATION_SWARM).setFitness(
						Problem.evaluate(swarm.get(CURRENT_ITERATION_SWARM)
								.getPosition()));

				updatePbest(swarm.get(CURRENT_ITERATION_SWARM));
			}
			updateGbest();
		}
		// printResult();
		if(Constants.STORAGE_RESUTLS) SalveSimulations.storageSimulationFile(gBests);
	}

	protected void isDecay() {

		double decayValue = 0.0d;
		if (CURRENT_ITERATION > 0) {
			decayValue = ((Constants.W_INI - Constants.W_FINAL) / Constants.MAX_ITERATION);
			decayValue = decayValue * CURRENT_ITERATION;
			decayValue = Constants.W_INI - decayValue;
		}else{
			decayValue = Constants.W; 	
		}
		Constants.W = decayValue;
	}

	/*protected void printResult() {
		System.out.println("----- RESULTS -------");
		System.out.println();
		for (int i = 0; i < Constants.MAX_ITERATION
				- Constants.FOCAL_BEST_PARTICLE; i++) {
			System.out.println("Iteration: " + (i + 1));
			System.out.println("Fitness: " + gBests.get(i).getFitness());
			System.out.println("--------------------------");
		}

	}*/

	public ArrayList<Pbest> execute() throws IOException {
		switch (Constants.TOPOLOGY_TYPE) {
		case 1:
			gBestToplogy();
			break;

		case 2:
			lBestTopology();
			break;

		case 3:
			focalBestTopology();
			break;
		}
		return gBests;
	}
}
