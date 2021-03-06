package util;

import source.Problem;

public class Constants {
	public static int SWARM_SIZE = 30; 			// Quantidade de Particulas
	public static int MAX_ITERATION = 10000;	// Quantidade de Iterações
	public static int PROBLEM_DIMENSION = 30;	// Dimensões
	public static final int GLOBAL = 1;
	public static final int LOCAL = 2;
	public static final int FOCAL = 3;
	
	public static final int SPHERE = 1;
	public static final int RASTRIGIN = 2;	//Rotated Rastrigin
	public static final int ROSENBROCK = 3;	//Rosenbrock

	/* Topologias
	 * 1 - Gbest
	 * 2 - lbest
	 * 3 - Focal  
	 * 
	 * Problem
	 * 1 - Sphere
	 * 2 - Rotated Rastrigin
	 * 3 - Rosenbrock
	 */
	
	public static int TOPOLOGY_TYPE = 0;
	public static int FOCAL_BEST_PARTICLE = 0;
	
	public static int SIMULATION_SIZE = 30;
	public static int CURRENT_SIMULATION = 0;

	public static double C1 = 2.05;
	public static double C2 = 2.05;
	public static double DIMENSION = 30;
	public static double V_MIN = 0.01 * Problem.DIMENSION_LOW;
	public static double V_MAX = 0.01 * Problem.DIMENSION_HIGH;
	
	public static boolean T_CLERC = false;
	public static double CLERC = 0.729;  // Fator de constricao de CLERC
	
	public static double W = 0.8;
	
	public static double W_INI = 0.9;
	public static double W_FINAL = 0.4;
	public static boolean DECAY = true; 
	public static boolean STORAGE_RESUTLS = false;
}
