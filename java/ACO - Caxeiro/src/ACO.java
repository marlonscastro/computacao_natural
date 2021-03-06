import java.util.ArrayList;

public class ACO {
	private ArrayList<Caminho> melhorSolucao;
	private ArrayList<Caminho> caminhos;
	private ArrayList<Formiga> formigas;
	private ArrayList<Cidade> cidades;
	private Double feromonio; 	// alpha: 1
	private Double distancia; 	// beta: 1
	private Double Q;			// Constante de atualizacao do Feromonio = 10
	private Double evaporacao;	// Taxa de evaporacao = 0.01

	private int iteracoes;
	public ACO(ArrayList<Caminho> c, int it, Double a, Double b, Double evap){
		setFeromonio(a);
		setDistancia(b);
		evaporacao = evap;
		Q = 10.0;
		caminhos = c;
		iteracoes = it;
		cidades = new ArrayList<Cidade>();
		formigas = new ArrayList<Formiga>();
		for (Caminho caminho : c) {
			if(!cidades.contains(caminho.getOrigem())){
				cidades.add(caminho.getOrigem());
			}			
			if(!cidades.contains(caminho.getDestino())){
				cidades.add(caminho.getDestino());
				continue;
			}
		}
		// Coloca cada formiga numa cidade, não é aleatorio.
		for (Cidade cid : cidades) {
			formigas.add(new Formiga(cid));
		}
	}
	public ArrayList<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(ArrayList<Cidade> cidades) {
		this.cidades = cidades;
	}
	public void executar(){
		Double Pk_ij;
		// Coloque cada formiga em uma cidade aleatoria
		/*
		 * para t = 1 a numero de iterações
		 * 		para k = 1 até m 
		 * 			enquanto a formiga k não construir a viagem Sk
		 * 				Selecione a próxima cidade pela regra Pk_ij
		 * 			fim_enquanto
		 * 			Calcule a distância Lk da viagem Sk
		 * 			se Lk < L* então 
		 * 				S* = Sk
		 * 				L* = Lk
		 * 			fim_se
		 * 		fim_para
		 *      Atualize os feromonios
		 * fim_para
		 * Retornar S*
		 */
		
		// para t = 1 a numero de iterações

		Double s = 0d;
		for (int x = 1; x <= iteracoes; x++) {
			System.out.printf("################### %da ITERAÇÃO #####################\n", x);			
			System.out.println("ROTA     d       Thao     n_xy     Thao*n_xy    p(xy)    p(%)");			
			// Atualiza probabilidades
			for (Caminho caminho : caminhos) {
				Pk_ij = caminho.getThao_xy()*caminho.getFeromonio();
				s = 0d;
				for (Caminho ctemp : caminhos) {
					if(caminho.getOrigem().equals(ctemp.getOrigem()) || caminho.getOrigem().equals(ctemp.getDestino())){
						s += ctemp.getThao_xy()*ctemp.getFeromonio();
						System.out.printf("%s : %.5f ## ", ctemp.getRota(), ctemp.getThao_xy()*ctemp.getFeromonio()); 						
					}
				}
				System.out.println();

				Pk_ij /= s;
				caminho.setProbabilidade(Pk_ij);
				System.out.printf("%s   %.3f    %.3f    %.3f    %.7f    %.3f    %.1f\n", 
						caminho.getRota(), 
						caminho.getDistancia(), 
						caminho.getThao_xy(),
						caminho.getFeromonio(), // n_xy
						caminho.getThao_xy()*caminho.getFeromonio(),
						caminho.getProbabilidade(),
						caminho.getProbabilidade()*100
						);
				System.out.println("-----------------------------------------------------------");
			}
			
			// para k = 1 até m 
			for (Formiga f : formigas) {
				// enquanto a formiga k não construir a viagem Sk
				
					// Selecione a próxima cidade pela regra Pk_ij
			}
			// Atualize os feromonios
			
		}
	}
	public Double getFeromonio() {
		return feromonio;
	}
	public void setFeromonio(Double feromonio) {
		this.feromonio = feromonio;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
}
