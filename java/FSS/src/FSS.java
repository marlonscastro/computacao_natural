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
	double[] vetorInstintivo;
	double fitnessAtual;
	double fitnessVizinhanca;
	double varFitness;
	// Recebe quantidade de Peixes e Iterações: provisório
	public FSS(int qPeixes, int iteracoes, int dimensions){
		fitness = new ArrayList<Double>();
		peixes = new ArrayList<Peixe>();
		vetorInstintivo = new double[dimensions]; 
		dim = dimensions;
		for(int i=0;i<qPeixes;i++){
			peixes.add(new Peixe(dimensions));
		}
		this.iteracoes = iteracoes;
	}
	
	// Executa o algoritmo 
	public void executar(){
		double[] posVizinhanca = new double[dim];
		for(int i=0;i<this.iteracoes;i++){
			for (Peixe peixe : peixes) {    // ->>>>>>> para cada peixe faça
				//Encontre posição vizinha (2.1);
				for (int j = 0; j < posVizinhanca.length; j++) {
					posVizinhanca[j] = peixe.getPos()[j]*step_individual_ini*ThreadLocalRandom.current().nextDouble(-1,1);
				}
				//Avalie a variação no fitness de acordo com (2.2) e mova o peixe
				//apenas se o fitness melhorou;
				fitnessAtual = Functions.sphere(peixe.getPos());
				fitnessVizinhanca = Functions.sphere(posVizinhanca);
				varFitness = (fitnessVizinhanca - fitnessAtual);
				if(varFitness > 0){
					fitness.add(varFitness);
					peixe.setVariacao_fitness(varFitness);
					/* 
					 * Movendo o peixe para a nova posição
					 * já salva a variacao na Posição individual também 
					 */
					peixe.setPos(posVizinhanca);
					//Alimente os peixes utilizando (2.7);
					peixe.setPeso(peixe.getPeso()+varFitness/Collections.max(fitness));
				}
				else{
					fitness.add(0.0);
					peixe.setVariacao_fitness(0);
				}
			}
			
			//Calcule o vetor do movimento Coletivo instintivo utilizando (2.3); 	
			double Soma_de_Todos_os_Fitness = 0.0;
			double Soma_de_Todas_as_posicoes_Atuais = 0.0;
			double Soma_Produto = 0.0;
			double[] varPosTemp = new double[dim];
			double[] SomaVarPosTemp = new double[dim];
			for (int j = 0; j < SomaVarPosTemp.length; j++) {
				SomaVarPosTemp[j] = varPosTemp[j] = 0;
			}
			for (Peixe peixe : peixes) {
				Soma_de_Todos_os_Fitness += peixe.getVariacao_fitness();
				for (int j = 0; j < peixe.getVariacao_pos().length; j++) {
					varPosTemp[j] += peixe.getVariacao_pos()[j]*peixe.getVariacao_fitness();
				}
				
				for (int j = 0; j < peixe.getVariacao_pos().length; j++) {
					SomaVarPosTemp[j] += varPosTemp[j];
				}
			}
			for (int x = 0; x < this.vetorInstintivo.length; x++) {
				this.vetorInstintivo[x] = SomaVarPosTemp[x]/Soma_de_Todos_os_Fitness;
			}
			
			
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