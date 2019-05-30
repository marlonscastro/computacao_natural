
public class Caminho {
	private Cidade origem;
	private Cidade destino;
	private Double distancia;
	private Double feromonio;
	private Double Thao_xy;
	private Double probabilidade;  // p_xy
	public Caminho(String o, String d, Double dist){
		setOrigem(new Cidade(o));
		setDestino(new Cidade(d));
		setDistancia(dist);
		setFeromonio(0.1);
		setThao_xy(1/dist);
	}
	public Cidade getOrigem() {
		return origem;
	}
	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}
	public Cidade getDestino() {
		return destino;
	}
	public void setDestino(Cidade destino) {
		this.destino = destino;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Double getFeromonio() {
		return feromonio;
	}
	public void setFeromonio(Double feromonio) {
		this.feromonio = feromonio;
	}
	public Double getThao_xy() {
		return Thao_xy;
	}
	public void setThao_xy(Double thao_xy) {
		Thao_xy = thao_xy;
	}
	public String getRota(){
		return origem.getRotulo()+"->"+destino.getRotulo();
	}
	public Double getProbabilidade() {
		return probabilidade;
	}
	public void setProbabilidade(Double probabilidade) {
		this.probabilidade = probabilidade;
	}


}
