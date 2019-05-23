package util;

import java.util.ArrayList;

import source.Particle;
public class Util {
	public static int getMinPos(ArrayList<Particle> particles) {
		int pos = 0;
		double minValue = particles.get(0).getFitness();
		
		for(int i=0; i<particles.size(); i++) {
			if(particles.get(i).getFitness() < minValue) {
				pos = i;
				minValue = particles.get(i).getFitness();
			}
		}
		
		return pos;
	}
		
	public static void isDecay() {
		Constants.W = Constants.W - (Constants.W_INI - Constants.W_FINAL) / Constants.MAX_ITERATION;
	}

}
