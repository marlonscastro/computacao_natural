/* 3a Prática - Algoritmo ACO aplicado ao problema do caxeiro viajante
 * Author: Marlon Castro
 * Data: 26 de maio de 2019 
 * Disciplina: Computação Natural 
 * Professor: Carmelo Bastos
 */

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Caminho> caminhos = new ArrayList<Caminho>();
		caminhos.add(new Caminho("A", "B", 22.0));
		caminhos.add(new Caminho("A", "C", 50.0));
		caminhos.add(new Caminho("A", "D", 48.0));
		caminhos.add(new Caminho("A", "E", 29.0));
		caminhos.add(new Caminho("B", "C", 30.0));
		caminhos.add(new Caminho("B", "D", 34.0));
		caminhos.add(new Caminho("B", "E", 32.0));		
		caminhos.add(new Caminho("C", "D", 22.0));
		caminhos.add(new Caminho("C", "E", 23.0));
		caminhos.add(new Caminho("D", "E", 35.0));		
		ACO aco = new ACO(caminhos, 1, 1.0, 0.6, 0.01);
		aco.executar();
		
	}

}
