package model;

public class Funcionario {

	private String nomeFuncionario;
	private String senha;
	
	public Funcionario() {
		
	}

	public Funcionario(String nomeFuncionario, String senha) {
		super();
		this.nomeFuncionario = nomeFuncionario;
		this.senha = senha;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nome) {
		this.nomeFuncionario = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return nomeFuncionario + ";" + senha;
	}
	
	
	
}
