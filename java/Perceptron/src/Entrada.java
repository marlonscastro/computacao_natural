
public class Entrada {
	private float peso;
	private float valor;
	
	public Entrada(int valor, float peso){
		this.peso = peso;
		this.valor = valor;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public Entrada setPeso(float peso) {
		this.peso = peso;
		return this;
	}
	
	public float getValor() {
		return valor;
	}
	
	public Entrada setValor(float valor) {
		this.valor = valor;
		return this;
	}
}
