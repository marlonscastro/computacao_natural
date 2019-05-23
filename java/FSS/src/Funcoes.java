class Funcoes {
	public static Double parabola(double[] dimension){
		double fitness = 0;
		for (int i = 0; i < dimension.length; i++) {
			fitness += Math.pow(dimension[i], 2);
		}
		return fitness;
	}
	
	public static Double sphere(double[] dimension){
		double fitness = 0;
		for (int i = 0; i < dimension.length; i++) {
			fitness += Math.pow(dimension[i], 2);
		}
		return fitness;
	}
	
	public static Double rastrigin(double[] dimension){
		double fitness = 0;
		for (int i=0; i<dimension.length; i++){
			double cos = Math.cos(2*Math.PI*dimension[i]);
			double pow = (Math.pow(dimension[i], 2));
			fitness = fitness + (pow - (10*cos) + 10);
		}
		return fitness;
	}
	
	public static Double rosenbrock(double[] dimension){
		double fitness = 0;
		for (int i = 0; i < dimension.length-1; i++) {
			double temp1 = (dimension[i] * dimension[i]) - dimension[i + 1];
			double temp2 = dimension[i] - 1.0;
			fitness += (100.0 * temp1 * temp1) + (temp2 * temp2);
		}	
		return fitness;
	}
	
}