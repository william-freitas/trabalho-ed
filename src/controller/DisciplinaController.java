package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Disciplina;
import model.dao.CursoDAO;
import model.dao.DisciplinaDAO;

public class DisciplinaController implements ActionListener{

    
    private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaDiaDaSemana;
	private JTextField tfDisciplinaHorarioDaAula;
	private JTextField tfDisciplinaHorasDiarias;
	private JTextField tfDisciplinaCodigoCurso;
    private JTextArea taDisciplina;
    
 
    public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome,
			JTextField tfDisciplinaDiaDaSemana, JTextField tfDisciplinaHorarioDaAula,
			JTextField tfDisciplinaHorasDiarias, JTextField tfDisciplinaCodigoCurso, JTextArea taDisciplina) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.tfDisciplinaDiaDaSemana = tfDisciplinaDiaDaSemana;
		this.tfDisciplinaHorarioDaAula = tfDisciplinaHorarioDaAula;
		this.tfDisciplinaHorasDiarias = tfDisciplinaHorasDiarias;
		this.tfDisciplinaCodigoCurso = tfDisciplinaCodigoCurso;
		this.taDisciplina = taDisciplina;
	}
    
    //instancia a classe DisciplinaDAO
    DisciplinaDAO dao = new DisciplinaDAO();
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		
		try {
			if(comando.equals("Cadastrar")) {
				cadastrarDisciplina();
		    } else if(comando.equals("Consultar")) {
		    	consultarDisciplina();
		    } else if(comando.equals("Atualizar")) {
		    	atualizarDisciplina();
		    } else if(comando.equals("Remover")) {
		    	int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            	if(confirmacao == JOptionPane.YES_OPTION) {
            		removerDisciplina();
            	}else {
            		return;
            	}
		    } 
		}catch (Exception e1) {
				e1.printStackTrace();
			}
    }	
		
 
    private void cadastrarDisciplina() throws Exception {
        int codigoDisciplina = Integer.parseInt(tfDisciplinaCodigo.getText());
        String nomeDisciplina = tfDisciplinaNome.getText();
        String diaSemana = tfDisciplinaDiaDaSemana.getText();
        String horarioAula = tfDisciplinaHorarioDaAula.getText();
        int horasDiarias = Integer.parseInt(tfDisciplinaHorasDiarias.getText());
        int codigoCurso = Integer.parseInt(tfDisciplinaCodigoCurso.getText());

        
        CursoDAO cursoDAO = new CursoDAO();
        boolean cursoExiste = cursoDAO.validarCurso(codigoCurso);
        boolean disciplinaExiste = dao.validarDisciplina(codigoDisciplina);

        if (!cursoExiste) {
            taDisciplina.setText("Erro: Curso não encontrado. Cadastre o curso antes de cadastrar a disciplina.");
            return;
        }

        if (disciplinaExiste) {
            taDisciplina.setText("Erro: Código da disciplina já cadastrado. Use outro código.");
            return;
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigoDisciplina(codigoDisciplina);
        disciplina.setNomeDisciplina(nomeDisciplina);
        disciplina.setDiaSemana(diaSemana);
        disciplina.setHorarioAula(horarioAula);
        disciplina.setHorasDiarias(horasDiarias);
        disciplina.setCodigoCurso(codigoCurso);

        dao.gravarArquivoDisciplina(disciplina);

        limparCampos();

        taDisciplina.setText("Disciplina cadastrada com sucesso.");
    }

    private void consultarDisciplina() throws Exception {
        
        int codigoBusca = Integer.parseInt(tfDisciplinaCodigo.getText());

        Fila filaDisciplinas = new Fila();

        filaDisciplinas = dao.lerArquivoDisciplina(filaDisciplinas);

        boolean encontrada = false;

        while (!filaDisciplinas.isEmpty()) {
            Disciplina d = (Disciplina) filaDisciplinas.remove();
            if (d.getCodigoDisciplina() == codigoBusca) {
                taDisciplina.setText(
                    "Nome: " + d.getNomeDisciplina() +
                    "\nDia da Semana: " + d.getDiaSemana() +
                    "\nHorário da Aula: " + d.getHorarioAula() +
                    "\nHoras Diárias: " + d.getHorasDiarias() +
                    "\nCódigo do Curso: " + d.getCodigoCurso()
                );
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            taDisciplina.setText("Disciplina não encontrada.");
        }

        limparCampos();
    }

    private void atualizarDisciplina() throws Exception {
    	
    	Lista<Disciplina> listaArquivoDisciplina = dao.lerArquivoDisciplina();
    	
    	if (listaArquivoDisciplina.isEmpty()) {
    		taDisciplina.setText("Não há disciplinas cadastradas para atualizar.");
	    }
    	
    	Disciplina disciplinaAtualizada = new Disciplina();
    	disciplinaAtualizada.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));
    	
    	boolean atualizado = false;
	    int tamanho = listaArquivoDisciplina.size();

	    for (int i = 0; i < tamanho; i++) {
	        Disciplina disciplina = listaArquivoDisciplina.get(i);
	        if (disciplina.getCodigoDisciplina() == disciplinaAtualizada.getCodigoDisciplina()) {
	        	disciplina.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));
	        	disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
	        	disciplina.setDiaSemana(tfDisciplinaDiaDaSemana.getText());
	        	disciplina.setHorarioAula(tfDisciplinaHorarioDaAula.getText());
	        	disciplina.setHorasDiarias(Integer.parseInt(tfDisciplinaHorasDiarias.getText()));
	        	disciplina.setCodigoCurso(Integer.parseInt(tfDisciplinaCodigoCurso.getText()));
	            atualizado = true;
	            break;
	        }
	    }

	    if (!atualizado) {
	    	taDisciplina.setText("Disciplina com código " + disciplinaAtualizada.getCodigoDisciplina() + " não encontrada.");
	    }

	    dao.gravarArquivoDisciplina(listaArquivoDisciplina);
	    
	    
	    limparCampos();
    	
    	taDisciplina.setText("Disciplina atualizada");
    	
    }
    
    private void removerDisciplina() throws Exception {
    	
    	 
    	 Lista<Disciplina> listaArquivoDisciplina = dao.lerArquivoDisciplina();

    	    if (listaArquivoDisciplina.isEmpty()) {
    	    	taDisciplina.setText("Não há disciplinas cadastradas para remover.");
    	    }
    	    
    	    Disciplina removerDisciplina = new Disciplina();
        	removerDisciplina.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));

    	    boolean removido = false;
    	    int tamanho = listaArquivoDisciplina.size();

    	    for (int i = 0; i < tamanho; i++) {
    	        Disciplina disciplina = listaArquivoDisciplina.get(i);
    	        if (disciplina.getCodigoDisciplina() == removerDisciplina.getCodigoDisciplina()) {
    	            listaArquivoDisciplina.remove(i);
    	            removido = true;
    	            break;
    	        }
    	    }

    	    if (!removido) {
    	    	taDisciplina.setText("Disciplina com código " + removerDisciplina + " não encontrada.");
    	    }

    	    dao.gravarArquivoDisciplina(listaArquivoDisciplina);
    	    
    	    
    	    //InscricaoDAO inscricaDAO = new InscricaoDAO();
    	    //inscricaoDAO.removerDisciplinaDasInscricoes(removerDisciplina.getCodigoDisciplina());
    	    
    	    limparCampos();
        	
        	taDisciplina.setText("Disciplina removida");
    }

    
	private void limparCampos() {
		tfDisciplinaCodigo.setText("");
        tfDisciplinaNome.setText("");
        tfDisciplinaDiaDaSemana.setText("");
        tfDisciplinaHorarioDaAula.setText("");
        tfDisciplinaHorasDiarias.setText("");
        tfDisciplinaCodigoCurso.setText("");
	}
}
