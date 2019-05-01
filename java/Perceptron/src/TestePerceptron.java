
public class TestePerceptron {
	public static void main(String[] args) {
		Perceptron p = new Perceptron(0.5f);
		p.imprimeEntradas();
		System.out.printf("		0    0    %d \n", (int) p.verificar(0f, 0f));
		System.out.printf("		0    1    %d \n", (int) p.verificar(0f, 1f));
		System.out.printf("		1    0    %d \n", (int) p.verificar(1f, 0f));
		System.out.printf("		1    1    %d \n", (int) p.verificar(1f, 1f));	
	
		float [][]matriz = {{0,0,0}, 
							{0,1,0}, 
							{1,0,0}, 
							{1,1,1}
		};

		System.out.println("\n\n################# Treinamento ################# ");		
		p.treinar(matriz, 10); // executar o treinamento com 10 Ciclos
		
		System.out.println("\n\n################ Teste da rede ##################");
		System.out.println("		X    Y  SAIDA");
		System.out.printf("		0    0    %d \n", (int) p.verificar(0f, 0f));
		System.out.printf("		0    1    %d \n", (int) p.verificar(0f, 1f));
		System.out.printf("		1    0    %d \n", (int) p.verificar(1f, 0f));
		System.out.printf("		1    1    %d \n", (int) p.verificar(1f, 1f));		
	}
}