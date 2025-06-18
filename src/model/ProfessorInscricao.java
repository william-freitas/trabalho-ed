package model;

public class ProfessorInscricao {
	
    private Professor professor;
    private int pontuacao;

    public ProfessorInscricao(Professor professor, int pontuacao) {
        this.professor = professor;
        this.pontuacao = pontuacao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return "CPF: " + professor.getCpfProfessor() +
               ", Nome: " + professor.getNomeProfessor() +
               ", Área Conhecimento: " + professor.getAreaConhecimentoPretendida() +
               ", Pontuação: " + pontuacao;
    }
}

