public class Main {
	public static void main(String[] args) {
		//FSS(Peixes, Iteracoes, Dimensoes, Problema);
		FSS f= new FSS(30, 1000, 30, Constantes.SPHERE);
		f.executar();
		
		/* Sphere: (0,0,..) = 0
		 * Rastrigin (0,0,..) = 0
		 * Rosenbrock (1,1,..) = 0
		 * parabola (0,0,..) = 0
		 */
		
		/*double[] valor = new double[30];
		for (int i = 0; i < valor.length; i++) {
			valor[i] = -2.4;
		}
		System.out.printf("f(valor): %f", Functions.sphere(valor));*/
		
	}
}
