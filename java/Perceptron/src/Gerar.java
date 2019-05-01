import java.util.Random;

public class Gerar {

	public static void main(String[] args) {
		Random rand = new Random();
		float x,y,z;
		System.out.printf("X     Y    Z      Saida\n");
		for (int i = 0; i < 30; i++) {
			x = rand.nextFloat();
			y = rand.nextFloat();
			z = rand.nextFloat();
			System.out.printf("{%fv%fv%fv%f}p\n", x, y, z, (2*(Math.pow(x, 2)) + 3*z)/(3*Math.pow(y, 3)));			
		}
	}

}
