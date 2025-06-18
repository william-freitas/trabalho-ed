package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Curso;
import model.dao.CursoDAO;

public class CursoController implements ActionListener {

    private JTextField tfCursoCodigo;
    private JTextField tfCursoNome;
    private JTextField tfCursoAreaConhecimento;
    private JTextArea textAreaCurso;

    public CursoController(JTextField tfCursoCodigo, JTextField tfCursoNome,
                           JTextField tfCursoAreaConhecimento, JTextArea textAreaCurso) {
        this.tfCursoCodigo = tfCursoCodigo;
        this.tfCursoNome = tfCursoNome;
        this.tfCursoAreaConhecimento = tfCursoAreaConhecimento;
        this.textAreaCurso = textAreaCurso;
    }
    
    
    //instancia a classe CursoDAO
    CursoDAO dao = new CursoDAO();
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastrarCurso();
            } else if (comando.equals("Consultar")) {
                consultarCurso();
            } else if (comando.equals("Atualizar")) {
                atualizarCurso();
            } else if (comando.equals("Remover")) {
            	int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            	if(confirmacao == JOptionPane.YES_OPTION) {
            		removerCurso();
            	}else {
            		return;
            	}
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrarCurso() throws IOException {
        Curso curso = new Curso();
        curso.setCodigoCurso(Integer.parseInt(tfCursoCodigo.getText()));
        curso.setNomeCurso(tfCursoNome.getText());
        curso.setAreaConhecimento(tfCursoAreaConhecimento.getText());

        dao.gravarArquivoCurso(curso);

        limparCampos();
        textAreaCurso.setText("Curso cadastrado!");
    }

    private void consultarCurso() throws Exception {
        int codigoBusca = Integer.parseInt(tfCursoCodigo.getText());

        Fila filaCursos = new Fila();
        
        filaCursos = dao.lerArquivoCurso(filaCursos);
           
        boolean encontrado = false;

        while (!filaCursos.isEmpty()) {
            Curso c = (Curso) filaCursos.remove();
            if (c.getCodigoCurso() == codigoBusca) {
                textAreaCurso.setText(
                    "Nome: " + c.getNomeCurso() +
                    "\nÁrea: " + c.getAreaConhecimento()
                );
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            textAreaCurso.setText("Curso não encontrado.");
        }

        limparCampos();
    }

    private void atualizarCurso() throws Exception {
        Lista<Curso> listaCursos = dao.lerArquivoCurso();

        if (listaCursos.isEmpty()) {
        	textAreaCurso.setText("Não há cursos cadastrados para atualizar");
        }

        Curso cursoAtualizado = new Curso();
        cursoAtualizado.setCodigoCurso(Integer.parseInt(tfCursoCodigo.getText()));

        boolean atualizado = false;

        for (int i = 0; i < listaCursos.size(); i++) {
            Curso curso = listaCursos.get(i);
            if (curso.getCodigoCurso() == cursoAtualizado.getCodigoCurso()) {
                curso.setNomeCurso(tfCursoNome.getText());
                curso.setAreaConhecimento(tfCursoAreaConhecimento.getText());
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
        	textAreaCurso.setText("Curso com código " + cursoAtualizado.getCodigoCurso() + " não encontrado.");
        }

        dao.gravarArquivoCurso(listaCursos);

        limparCampos();
        textAreaCurso.setText("Curso atualizado!");
    }

    private void removerCurso() throws Exception {
        Lista<Curso> listaCursos = dao.lerArquivoCurso();

        if (listaCursos.isEmpty()) {
        	textAreaCurso.setText("Não há cursos cadastrados para remover.");
        }

        int codigoRemover = Integer.parseInt(tfCursoCodigo.getText());
        boolean removido = false;

        for (int i = 0; i < listaCursos.size(); i++) {
            Curso curso = listaCursos.get(i);
            if (curso.getCodigoCurso() == codigoRemover) {
                listaCursos.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
        	textAreaCurso.setText("Curso com código " + codigoRemover + " não encontrado.");
        }

        dao.gravarArquivoCurso(listaCursos);

        limparCampos();
        textAreaCurso.setText("Curso removido!");
    }

    private void limparCampos() {
        tfCursoCodigo.setText("");
        tfCursoNome.setText("");
        tfCursoAreaConhecimento.setText("");
    }
}
