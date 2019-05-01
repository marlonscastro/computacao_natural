import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


class FSS {
	ArrayList<Peixe> peixes;
	int iteracoes = 0;
	int dim = 0;
	Double step_individual_ini = 0.1;
	Double step_individual_final = 0.001;
	Double step_volitivo_ini = 0.01;
	Double step_volitivo_final = 0.001;
	ArrayList<Double> fitness;
	// Recebe quantidade de Peixes e Iterações: provisório
	public FSS(int qPeixes, int iteracoes, int dimensions){
		fitness = new ArrayList<Double>();
		dim = dimensions;
		peixes = new ArrayList<Peixe>();
		for(int i=0;i<qPeixes;i++){
			peixes.add(new Peixe(dimensions));
		}
		this.iteracoes = iteracoes;
	}
	
	// Executa o algoritmo 
	public void executar(){
		double[] posVizinhanca = new double[dim];
		for(int i=0;i<this.iteracoes;i++){
			
			for (Peixe peixe : peixes) {// ->>>>>>> para cada peixe faça
				//Encontre posição vizinha (2.1);
				for (int j = 0; j < posVizinhanca.length; j++) {
					posVizinhanca[j] = peixe.getPos()[j]*step_individual_ini*ThreadLocalRandom.current().nextDouble(-1,1);
				}
				//Avalie a variação no fitness de acordo com (2.2) e mova o peixe
				//apenas se o fitness melhorou;
				double fitnessAtual = Functions.sphere(peixe.getPos());
				double fitnessVizinhanca = Functions.sphere(posVizinhanca);
				if(fitnessVizinhanca>fitnessAtual){
					fitness.add(fitnessVizinhanca);
					//Alimente os peixes utilizando (2.7);
					peixe.setPeso(peixe.getPeso()+fitnessVizinhanca/Collections.max(fitness));
				}
			}
			
			//Calcule o vetor do movimento instintivo utilizando (2.3); 	
			
			for (Peixe peixe : peixes) { //->>>>>>>>> para cada peixe faça
				//Execute o movimento instintivo utilizando (2.5);	
			}			
			
			// Calcule o baricentro utilizando (2.6);
			
			
			for (Peixe peixe : peixes) { //->>>>>>>para cada peixe faça
				//Execute o movimento volitivo usando (2.8);
			}		
	
			//Atualize sind e svol.
		}
	}
	public void imprimir(){
		int i = 1;
		for (Peixe peixe : peixes) {
			System.out.printf("peixe No. %d, pos: %.3f, peso: %.3f, fitness: %.3f\n", i++, peixe.getPos()[1], peixe.getPeso(), peixe.getFitness());
		}
	}
}