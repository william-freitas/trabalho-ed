package model;

public class Professor {

	private String cpfProfessor;
	private String nomeProfessor;
	private String areaConhecimentoPretendida;
	private int pontos;
	

	public Professor() {
		
	}


	public Professor(String cpfProfessor, String nomeProfessor, String areaConhecimentoPretendida, int pontos) {
		super();
		this.cpfProfessor = cpfProfessor;
		this.nomeProfessor = nomeProfessor;
		this.areaConhecimentoPretendida = areaConhecimentoPretendida;
		this.pontos = pontos;
	}


	public String getCpfProfessor() {
		return cpfProfessor;
	}


	public String getNomeProfessor() {
		return nomeProfessor;
	}


	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}


	public String getAreaConhecimentoPretendida() {
		return areaConhecimentoPretendida;
	}


	public void setAreaConhecimentoPretendida(String areaConhecimentoPretendida) {
		this.areaConhecimentoPretendida = areaConhecimentoPretendida;
	}


	public int getPontos() {
		return pontos;
	}


	@Override
	public String toString() {
		return cpfProfessor + ";" + nomeProfessor
				+ ";" + areaConhecimentoPretendida + ";" + pontos;
	}
	
	
}
