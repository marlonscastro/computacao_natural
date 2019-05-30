
public class Cidade {
	private String rotulo;
	private boolean isVisitada = false;

	public Cidade(String r){
		rotulo = r;
	}
	
	public String getRotulo(){
		return rotulo;
	}
	
	public boolean isVisitada() {
		return isVisitada;
	}

	public void setVisitada(boolean isVisitada) {
		this.isVisitada = isVisitada;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cidade)) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        return this.getRotulo().equals(other.getRotulo());
    }	
}
