import java.util.concurrent.ThreadLocalRandom;

public class Constantes {
	// Função distribuição de Probabilidade U
	public static final int SPHERE = 1;
	public static final int RASTRIGIN = 2;
	public static final int ROSENBROCK = 3;
	public static final int PARABOLA = 4;
	public static double U(double min, double max){
		return ThreadLocalRandom.current().nextDouble(min,max);
	}
	
}
