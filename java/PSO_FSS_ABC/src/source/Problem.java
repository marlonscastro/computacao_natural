package source;

public class Problem {
	public static double DIMENSION_HIGH = 100; //SPHERE
	public static double DIMENSION_LOW = -100; 

	//Rotated Rastrigin
//	public static final double DIMENSION_HIGH = 5.12; 
//	public static final double DIMENSION_LOW = -5.12;

	//Rosenbrock
//	public static final double DIMENSION_HIGH = 30; 
//	public static final double DIMENSION_LOW = -30;


	public static int PROBLEM_TYPE = 3;	//Sphere
//	public static final int PROBLEM_TYPE = 2;	//Rotated Rastrigin
//	public static final int PROBLEM_TYPE = 3;	//Rosenbrock


	public static double evaluate(double[] dimension) {
		double fitness = 0.0d;

		switch (PROBLEM_TYPE) {
		case 1:
			for (int i = 0; i < dimension.length; i++) {
				fitness += Math.pow(dimension[i], 2);
			}
			break;

		case 2:
			//Rastring
			for (int i=0; i<dimension.length; i++){
				double cosineValue = Math.cos(2*Math.PI*dimension[i]);
				double pow = (Math.pow(dimension[i], 2));
				fitness = fitness + (pow - (10*cosineValue) + 10);
			}
			
//			for (int i = 0; i < dimension.length; i++) {
//				fitness += Math.pow(dimension[i], 2) - (10 * Math.cos(2 * Math.PI * dimension[i])) + 10;
//			}
			break;
			//Rosenbrock
		case 3:
			for (int i = 0; i < dimension.length-1; i++) {
				double temp1 = (dimension[i] * dimension[i]) - dimension[i + 1];
				double temp2 = dimension[i] - 1.0;
				fitness += (100.0 * temp1 * temp1) + (temp2 * temp2);
			}

			break;
		}

		return fitness;
	}

}
