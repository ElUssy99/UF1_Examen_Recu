package Ej1;

public class VideojocObj {

	private int numJoc;
	private String nomJoc;
	private String plataforma;
	private double preu;
	
	public VideojocObj(int numJoc, String nomJoc, String plataforma, double preu) {
		super();
		this.numJoc = numJoc;
		this.nomJoc = nomJoc;
		this.plataforma = plataforma;
		this.preu = preu;
	}

	public VideojocObj() {
		super();
	}

	public int getNumJoc() {
		return numJoc;
	}

	public void setNumJoc(int numJoc) {
		this.numJoc = numJoc;
	}

	public String getNomJoc() {
		return nomJoc;
	}

	public void setNomJoc(String nomJoc) {
		this.nomJoc = nomJoc;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

	@Override
	public String toString() {
		return "VideojocObj [numJoc=" + numJoc + ", nomJoc=" + nomJoc + ", plataforma=" + plataforma + ", preu=" + preu
				+ "]";
	}
	
}
