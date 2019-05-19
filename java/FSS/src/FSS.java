import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;


class FSS {
	ArrayList<Peixe> peixes;
	int iteracoes = 0;
	int dim = 0;
	int problema;
	Double step_individual_ini = 0.1;
	Double step_individual_final = 0.001;
	Double step_volitivo_ini = 0.01;
	Double step_volitivo_final = 0.001;
	ArrayList<Double> fitness;
	double[] vetorInstintivo;
	double[] baricentro;
	double varFitness;
	// Recebe quantidade de Peixes e Iterações: provisório
	public FSS(int qPeixes, int iteracoes, int dimensions, int problema){
		this.problema = problema;
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
		baricentro = new double[dim];		
		for(int i=1;i<=this.iteracoes;i++){
			int contFitness = 1;
			for (Peixe peixe : peixes) {    
				// ->>>>>> para cada peixe faça
				//Encontre posição vizinha (2.1);
				for (int j = 0; j < posVizinhanca.length; j++) {
					posVizinhanca[j] = peixe.getPos()[j] + peixe.getPos()[j]*step_individual_ini*Constantes.U(-1, 1);
				}
				//Avalie a variação no fitness de acordo com (2.2) e mova o peixe
				//apenas se o fitness melhorou;

				/* Importante observar que como ser trata de encontrar o Minimo da funcao
				 * entao a nova posicao eh melhor se tiver um fitness "MENOR" que o anterior
				 */
				switch (problema){
					case 1: varFitness = (Functions.sphere(peixe.getPos()) - Functions.sphere(posVizinhanca));
					case 2:	varFitness = (Functions.rastrigin(peixe.getPos()) - Functions.rastrigin(posVizinhanca));
					case 3:	varFitness = (Functions.rosenbrock(peixe.getPos()) - Functions.rosenbrock(posVizinhanca));
					case 4:	varFitness = (Functions.parabola(peixe.getPos()) - Functions.parabola(posVizinhanca));
				}
				
				if(varFitness > 0){
					fitness.add(varFitness);
					peixe.setVariacaoFitness(varFitness);
					/* 
					 * Movendo o peixe para a nova posição
					 * já salva a variacao na Posição individual também 
					 */
					peixe.setPos(posVizinhanca);
					//Alimenta os peixes utilizando (2.7);
					peixe.setPeso(peixe.getPeso()+(varFitness/Collections.max(fitness)));
					//
				}
				else{
					fitness.add(0d);
					peixe.setVariacaoFitness(0.0);
				}
				
				
				System.out.printf("it: %d >> PESO(%f), Pos=", i, peixe.getPeso());
				for (int j = 0; j < peixe.getPos().length; j++) {
					System.out.printf("|%.1f", peixe.getPos()[j]);
				}
				System.out.printf("| FITNESS(%f)\n", varFitness);
			}
			
			//Calcule o vetor do movimento Coletivo instintivo utilizando (2.3); 	
			double Soma_de_Todos_os_Fitness = 0.0;
			double[] varPosTemp = new double[dim];
			double[] SomaVarPosTemp = new double[dim];
			for (int j = 0; j < SomaVarPosTemp.length; j++) {
				SomaVarPosTemp[j] = varPosTemp[j] = 0;
			}
			for (Peixe peixe : peixes) {
				Soma_de_Todos_os_Fitness += peixe.getVariacaoFitness();
				for (int j = 0; j < peixe.getVariacao_pos().length; j++) {
					varPosTemp[j] += peixe.getVariacao_pos()[j]*peixe.getVariacaoFitness();
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
				double[] temp = new double[this.dim];
				for (int j = 0; j < peixe.getPos().length; j++) {
					temp[j] = peixe.getPos()[j] + vetorInstintivo[j];
				}
				peixe.setPos(temp);				
			}			
			
			// Calcule o baricentro utilizando (2.6);
			for (Peixe peixe : peixes) {
				for (int j = 0; j < dim; j++) {
					baricentro[j] += (peixe.getPos()[j]*peixe.getPeso())/peixe.getPos()[j];
				}
			}
			
			double[] newPos = new double[dim];
			double[] x_B = new double[dim];
			for (Peixe peixe : peixes) { 
				//->>>>>>>para cada peixe faça
				//Execute o movimento volitivo usando (2.8);
				for (int j = 0; j < x_B.length; j++) {
					x_B[j] = peixe.getPos()[j] - baricentro[j];
				}
				for (int j = 0; j < dim; j++) {
					newPos[j] = peixe.getPos()[j] - sign() * (step_volitivo_ini * Constantes.U(0, 1) * x_B[j])/distanciaEuclidiana(peixe);			
				}
				peixe.setPos(newPos);
			}		
	
			//Atualize sind e svol.
			step_individual_ini -= 0.0001;
			step_volitivo_ini -= 0.0001;
		}
	}

	private int sign(){
		double E = 0;  // E = Somatorio
		for (Peixe peixe : peixes) {
			E += peixe.getPeso();
		}
		return (E>=0)?1:-1;
	}
	// Calcula a distância Euclidiana entre posição do peixe e baricentro (qualquer dimensão)
	private double distanciaEuclidiana(Peixe p){
		double soma = 0;
		for (int i = 0; i < p.getPos().length; i++) {
			soma += Math.pow(p.getPos()[i] - baricentro[i], 2);
		}
		return Math.sqrt(soma);
	}
	public void imprimir(){
		int i = 1;
		for (Peixe peixe : peixes) {
			System.out.printf("peixe No. %d, pos: %.3f, peso: %.3f, fitness: %.3f\n", i++, peixe.getPos()[1], peixe.getPeso(), peixe.getFitness());
		}
	}
}