package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import source.Pbest;
import source.Problem;

public class SalveSimulations {
	private static String local = "simulations/w-0.8/";

	public static void storageSimulationFile(ArrayList<Pbest> gBests)
			throws IOException {

		StringBuilder sb = new StringBuilder();
		String topologyName = "";
		String problemyName = "";

		switch (Constants.TOPOLOGY_TYPE) {
		case 1:
			topologyName = "gbest";
			break;
		case 2:
			topologyName = "lbest";
			break;
		case 3:
			topologyName = "focal";
			break;
		}

		switch (Problem.PROBLEM_TYPE) {
		case 1:
			problemyName = "sphere";
			break;
		case 2:
			problemyName = "rotated_rastrigin";
			break;
		case 3:
			problemyName = "rosenbrock";
			break;
		}

		String file = local + topologyName + "/" + topologyName + "_"
				+ problemyName + "_" + (Constants.CURRENT_SIMULATION + 1)
				+ ".csv";

		String bestFitness = local + topologyName + "/bestfitness/"
				+ topologyName + "_" + problemyName + "_best_fitness.csv";

		System.out.println(file);
		FileWriter writer = new FileWriter(file);

		StringBuilder bestFitnessValue = new StringBuilder();

		FileReader ler = new FileReader(bestFitness);
		BufferedReader reader = new BufferedReader(ler);
		String line;

		while ((line = reader.readLine()) != null) {
			bestFitnessValue.append(line);
			bestFitnessValue.append("\n");
		}

		for (int i = 0; i < gBests.size(); i++) {
			sb.append(gBests.get(i).getFitness());
			sb.append("\n");
			if (i + 1 == gBests.size()) {
				bestFitnessValue.append(gBests.get(i).getFitness());
			}
		}

		FileWriter writerBestfitness = new FileWriter(bestFitness);

		writer.append(sb);
		writer.flush();
		writer.close();
		reader.close();

		writerBestfitness.append(bestFitnessValue);
		writerBestfitness.flush();
		writerBestfitness.close();

	}

}
