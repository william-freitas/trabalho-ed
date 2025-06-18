package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import br.edu.fateczl.ordenacao.BubbleSort;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import model.dao.DisciplinaDAO;
import model.dao.InscricaoDAO;
import model.dao.ProfessorDAO;
//import model.ProfessorInscricao;

public class InscricaoController implements ActionListener {

    private JTextField tfCpfProfessor;
    private JTextField tfCodigoDisciplina;
    private JTextField tfCodigoProcesso;
    private JTextArea taInscricao;
    private JTextArea textAreaInscricoesAbertas;
    
    private JTextField tfConsultaCodigoDisciplinaInscricao;

    public InscricaoController(JTextField tfCpfProfessor, JTextField tfCodigoDisciplina, JTextField tfCodigoProcesso, JTextField tfConsultaCodigoDisciplinaInscricao, JTextArea taInscricao, JTextArea textAreaInscricoesAbertas) {
        this.tfCpfProfessor = tfCpfProfessor;
        this.tfCodigoDisciplina = tfCodigoDisciplina;
        this.tfCodigoProcesso = tfCodigoProcesso;
        this.tfConsultaCodigoDisciplinaInscricao = tfConsultaCodigoDisciplinaInscricao;
        this.taInscricao = taInscricao;
        this.textAreaInscricoesAbertas = textAreaInscricoesAbertas;
    }
    
    InscricaoDAO dao = new InscricaoDAO();

	@Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastrarInscricao();
            } else if (comando.equals("Consultar")) {
                consultarInscricao();
            } else if (comando.equals("Atualizar")) {
                atualizarInscricao();
            } else if (comando.equals("Remover")) {
            	int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            	if(confirmacao == JOptionPane.YES_OPTION) {
            		removerInscricao();
            	}else {
            		return;
            	}
            } else if (comando.equals("Consultar Inscricoes Abertas")) {
            	consultarInscricoesAbertas();
            } else if (comando.equals("Consultar por disciplina")) {
            	int codigoConsultaDisciplina = Integer.parseInt(tfConsultaCodigoDisciplinaInscricao.getText());
            	consultarInscritosPorDisciplina(codigoConsultaDisciplina);
            } 
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrarInscricao() throws Exception {
        String cpfProfessor = tfCpfProfessor.getText();
        int codigoDisciplina = Integer.parseInt(tfCodigoDisciplina.getText());
        int codigoProcesso = Integer.parseInt(tfCodigoProcesso.getText());

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        boolean professorExiste = professorDAO.validarProfessor(cpfProfessor);
        boolean disciplinaExiste = disciplinaDAO.validarDisciplina(codigoDisciplina);

        if (!professorExiste && !disciplinaExiste) {
            taInscricao.setText("Erro: Professor não encontrado e Disciplina não encontrada. Cadastre ambos antes de prosseguir.");
            return;
        } else if (!professorExiste) {
            taInscricao.setText("Erro: Professor não encontrado. Cadastre o professor antes de prosseguir.");
            return;
        } else if (!disciplinaExiste) {
            taInscricao.setText("Erro: Disciplina não encontrada. Cadastre a disciplina antes de prosseguir.");
            return;
        }

        // Se passou nas validações, pode cadastrar
        Inscricao inscricao = new Inscricao(cpfProfessor, codigoDisciplina, codigoProcesso);

        dao.gravarArquivoInscricao(inscricao);

        limparCampos();
        taInscricao.setText("Inscrição cadastrada com sucesso.");
    }
    
    private void consultarInscricao() throws Exception {
        String cpfBusca = tfCpfProfessor.getText();

        Fila filaInscricoes = new Fila();
        filaInscricoes = dao.lerArquivoInscricao(filaInscricoes);

        boolean encontrada = false;

        while (!filaInscricoes.isEmpty()) {
            Inscricao inscricao = (Inscricao) filaInscricoes.remove();
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                taInscricao.setText(
                    "CPF Professor: " + inscricao.getCpfProfessor() +
                    "\nCódigo Disciplina: " + inscricao.getCodigoDisciplina() +
                    "\nCódigo Processo: " + inscricao.getCodigoProcesso()
                );
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            taInscricao.setText("Inscrição não encontrada.");
        }

        limparCampos();
    }

    private void atualizarInscricao() throws Exception {
    	
        Lista<Inscricao> listaInscricoes = dao.lerArquivoInscricao();

        if (listaInscricoes.isEmpty()) {
        	taInscricao.setText("Não há inscrições cadastradas para atualizar.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean atualizado = false;

        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao inscricao = listaInscricoes.get(i);
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                inscricao.setCodigoDisciplina(Integer.parseInt(tfCodigoDisciplina.getText()));
                inscricao.setCodigoProcesso(Integer.parseInt(tfCodigoProcesso.getText()));
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
        	taInscricao.setText("Inscrição com CPF " + cpfBusca + " não encontrada.");
        }

        dao.gravarArquivoInscricao(listaInscricoes);
        limparCampos();
        taInscricao.setText("Inscrição atualizada com sucesso.");
    }

    private void removerInscricao() throws Exception {
    	
        Lista<Inscricao> listaInscricoes = dao.lerArquivoInscricao();

        if (listaInscricoes.isEmpty()) {
        	taInscricao.setText("Não há inscrições cadastradas para remover.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean removido = false;

        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao inscricao = listaInscricoes.get(i);
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                listaInscricoes.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
        	taInscricao.setText("Inscrição com CPF " + cpfBusca + " não encontrada.");
        }

        dao.gravarArquivoInscricao(listaInscricoes);
        limparCampos();
        taInscricao.setText("Inscrição removida com sucesso.");
    }

    private void limparCampos() {
        tfCpfProfessor.setText("");
        tfCodigoDisciplina.setText("");
        tfCodigoProcesso.setText("");
    }
    
    private void consultarInscricoesAbertas() throws Exception {
        
    	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Lista<Disciplina> disciplinas = disciplinaDAO.lerArquivoDisciplina();
        
        if (disciplinas.isEmpty()) {
        	textAreaInscricoesAbertas.setText("Não há disciplinas cadastradas.");
            return;
        }

        // Descobrir quantos cursos existem (assumir que códigos de curso são consecutivos a partir de 1)
        int maiorCodigoCurso = 0;
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            if (d.getCodigoCurso() > maiorCodigoCurso) {
                maiorCodigoCurso = d.getCodigoCurso();
            }
        }

        // Criar a tabela de espalhamento (vetor de listas)
        @SuppressWarnings("unchecked")
		Lista<Inscricao>[] tabela = new Lista[maiorCodigoCurso];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new Lista<>();
        }

        // Ler todas as inscrições
        Lista<Inscricao> inscricoes = dao.lerArquivoInscricao();

        // Distribuir as inscrições na tabela de espalhamento
        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao insc = inscricoes.get(i);

            // Encontrar o curso da disciplina
            int codigoCursoDaDisciplina = -1;

            for (int j = 0; j < disciplinas.size(); j++) {
                Disciplina disc = disciplinas.get(j);
                if (disc.getCodigoDisciplina() == insc.getCodigoDisciplina()) {
                    codigoCursoDaDisciplina = disc.getCodigoCurso();
                    break;
                }
            }

            // Se encontrou o curso
            if (codigoCursoDaDisciplina != -1) {
                int indiceTabela = codigoCursoDaDisciplina - 1;
                tabela[indiceTabela].addLast(insc);
            }
        }

        // Exibir na textArea
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < tabela.length; i++) {
            resultado.append("=== Curso ").append(i + 1).append(" ===\n");

            Lista<Inscricao> listaCurso = tabela[i];
            if (listaCurso.isEmpty()) {
                resultado.append("Nenhuma inscrição.\n");
            } else {
                for (int j = 0; j < listaCurso.size(); j++) {
                    resultado.append(listaCurso.get(j).toString()).append("\n");
                }
            }
            resultado.append("\n");
        }

        textAreaInscricoesAbertas.setText(resultado.toString());
    }
    
    private void consultarInscritosPorDisciplina(int codigoConsultaDisciplina) throws Exception {
    	//int codigoConsultaDisciplina = Integer.parseInt(tfConsultaCodigoDisciplinaInscricao.getText());

        Lista<Inscricao> listaInscricoes = dao.lerArquivoInscricao();
         
        Lista<Inscricao> inscricoesFiltradas = new Lista<>();
        
        Lista<Professor> professoresFiltrados = new Lista<>();

        // Filtra as inscrições da disciplina
        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao insc = listaInscricoes.get(i);
            if (insc.getCodigoDisciplina() == codigoConsultaDisciplina) {
                inscricoesFiltradas.addLast(insc);
                ProfessorDAO professorDAO = new ProfessorDAO();
                Professor prof = professorDAO.buscarProfessor(insc.getCpfProfessor());
                if (prof != null) {
                    professoresFiltrados.addLast(prof);
                }
            }
        }

        if (inscricoesFiltradas.isEmpty()) {
        	textAreaInscricoesAbertas.setText("Nenhuma inscrição encontrada para esta disciplina.");
            return;
        }

        // Cria vetor de pontuações para ordenar
        int[] pontuacoes = new int[professoresFiltrados.size()];
        for (int i = 0; i < professoresFiltrados.size(); i++) {
            pontuacoes[i] = professoresFiltrados.get(i).getPontos();
        }

        
        BubbleSort bs = new BubbleSort();
        int[] pontuacoesOrdenadas = bs.bubbleSort(pontuacoes);

        // Monta saída com base nas pontuações ordenadas
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== Inscrições para a disciplina ").append(codigoConsultaDisciplina).append(" ===\n");

        for (int p : pontuacoesOrdenadas) {
            for (int i = 0; i < professoresFiltrados.size(); i++) {
                Professor prof = professoresFiltrados.get(i);
                if (prof.getPontos() == p) {
                    resultado.append("CPF: ").append(prof.getCpfProfessor()).append(" | ");
                    resultado.append("Nome: ").append(prof.getNomeProfessor()).append(" | ");
                    resultado.append("Pontuação: ").append(prof.getPontos()).append("\n");

                    // Evita duplicação
                    professoresFiltrados.remove(i);
                    break;
                }
            }
        }

        textAreaInscricoesAbertas.setText(resultado.toString());

    }

    
    
}
