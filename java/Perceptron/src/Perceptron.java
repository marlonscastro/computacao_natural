import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
	private ArrayList<Entrada> entradas;
	private float taxa;
	private boolean treinada = false;
	
	public Perceptron(float taxa){
		this.taxa = taxa;
		this.entradas = new ArrayList<Entrada>();
		Random rand = new Random();
		this.entradas.add(0, (new Entrada(1, rand.nextFloat())));
		for(int i = 1;i < 3; i++){
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
		if(valor >= 0)
			return 1f;
		else
			return 0f;
	}
	
	// Derivada da Função de Ativação 
	private float derivada_f(){
		return 1;
	}
	
	private void entradaLiquida(float x, float y, float saidaEsperada){
		float met_i = 0;
		this.entradas.get(1).setValor(x);
		this.entradas.get(2).setValor(y);
		for (Entrada entrada : entradas) {
			met_i = met_i + entrada.getPeso() * entrada.getValor();
		}
		//System.out.printf("met_i = %f\n", met_i);
		float f = f(met_i); //y
	
		System.out.printf("\nx: %f, %f, d: %f,  y: %f", x, y, saidaEsperada, f);
		if(f != saidaEsperada){
			// Reajustar os pesos de todas as entradas
			for (Entrada entrada : entradas) {
				entrada.setPeso(
					entrada.getPeso() + this.taxa * erro(saidaEsperada,f)*derivada_f()*entrada.getValor());
			}
			int z = 0;
			System.out.printf(" -->> pesos atualizados: ");
			for (Entrada entrada : entradas) {
				System.out.printf("%d: %f", z++, entrada.getPeso());				
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
			for (int i = 0; i < 4; i++) {
				entradaLiquida(matriz[i][0], matriz[i][1], matriz[i][2]);
			}
			x++;
		}
	}
	
	public float verificar(float x, float y){
		float met_i = this.entradas.get(0).getPeso()*(+1f)
					+ this.entradas.get(1).getPeso()*x 
					+ this.entradas.get(2).getPeso()*y;
		return f(met_i);
	}
}
