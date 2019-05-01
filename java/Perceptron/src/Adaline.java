import java.util.ArrayList;
import java.util.Random;


public class Adaline {
	private ArrayList<Entrada> entradas;
	private float taxa;
	private boolean treinada = false;
	private float erro = 0; 
	
	public Adaline(float taxa){
		this.taxa = taxa;
		this.entradas = new ArrayList<Entrada>();
		Random rand = new Random();
		this.entradas.add(0, (new Entrada(1, rand.nextFloat())));
		for(int i = 1;i < 4; i++){
			this.entradas.add(i, (new Entrada(1, rand.nextFloat())));			
		}
	}
	
	public void imprimeEntradas(){
		int i = 1;
		System.out.println("Nº      Peso         Valor");
		for (Entrada entrada : entradas) {
			System.out.printf("%d:    %f     %f\n", i++, entrada.getPeso(), entrada.getValor());
		}
		System.out.printf("====== alpha(taxa de aprendizado): %f ====", this.taxa);
	}
	
	// Função de ativação do Neuronio Perceptron
	private float f(float valor){
		return (float) (1.0f/(1.0f+Math.pow(2.71829, -valor)));
	}
	
	// Derivada da Função de Ativação 
	private float derivada(float y){
		return (y*(1-y));
	}
	
	private void entradaLiquida(float x, float y, float z, float saidaEsperada){
		float met_i = 0;
		this.entradas.get(1).setValor(x);
		this.entradas.get(2).setValor(y);
		this.entradas.get(3).setValor(z);
		for (Entrada entrada : entradas) {
			met_i = met_i + entrada.getPeso() * entrada.getValor();
		}
		
		float f = f(met_i); //y
	
		System.out.printf("\nx: %f, %f, %f, d: %f,  y: %f", x, y, z, saidaEsperada, f);
		if(f != saidaEsperada){
			// Reajustar os pesos de todas as entradas
			this.erro = erro(saidaEsperada,f);
			for (Entrada entrada : entradas) {
				entrada.setPeso(
					entrada.getPeso() + this.taxa * this.erro * derivada(f) * entrada.getValor());
			}
			int z1 = 0;
			System.out.printf(" -->> pesos atualizados: ");
			for (Entrada entrada : entradas) {
				System.out.printf(" ,%d: %f", z1++, entrada.getPeso());				
			}
		}
	}
	
	private float erro(float esperado, float calculado){
		return (esperado - calculado);
	}
	
	public void treinar(float [][]matriz, int ciclos){
		int x = 0;
		while (x<ciclos && !this.treinada) {
			System.out.printf("\n================= %do Ciclo ==================", x+1);
			for (int i = 0; i < 20; i++) {
				entradaLiquida(matriz[i][0], matriz[i][1], matriz[i][2], matriz[i][3]);
			}
			x++;
			System.out.printf("\nERRO: %f", Math.pow(this.erro, 2));
		}
	}
	
	public float verificar(float x, float y, float z){
		float met_i = this.entradas.get(0).getPeso()*(+1f)
					+ this.entradas.get(1).getPeso()*x 
					+ this.entradas.get(2).getPeso()*y
					+ this.entradas.get(3).getPeso()*z;
		return f(met_i);
	}
}
