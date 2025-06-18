package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Professor;
import model.dao.ProfessorDAO;

public class ProfessorController implements ActionListener {

    private JTextField tfCpfProfessor;
    private JTextField tfNomeProfessor;
    private JTextField tfAreaConhecimento;
    private JTextField tfPontos;
    private JTextArea taProfessor;

    public ProfessorController(JTextField tfCpfProfessor, JTextField tfNomeProfessor,
                               JTextField tfAreaConhecimento, JTextField tfPontos, JTextArea taProfessor) {
        this.tfCpfProfessor = tfCpfProfessor;
        this.tfNomeProfessor = tfNomeProfessor;
        this.tfAreaConhecimento = tfAreaConhecimento;
        this.tfPontos = tfPontos;
        this.taProfessor = taProfessor;
    }
    
    
    ProfessorDAO dao = new ProfessorDAO();

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastrarProfessor();
            } else if (comando.equals("Consultar")) {
                consultarProfessor();
            } else if (comando.equals("Atualizar")) {
                atualizarProfessor();
            } else if (comando.equals("Remover")) {
            	int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            	if(confirmacao == JOptionPane.YES_OPTION) {
            		removerProfessor();
            	}else {
            		return;
            	}
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void cadastrarProfessor() throws Exception {
        Professor professor = new Professor();

        professor = new Professor(
                tfCpfProfessor.getText(),
                tfNomeProfessor.getText(),
                tfAreaConhecimento.getText(),
                Integer.parseInt(tfPontos.getText())
        );

        dao.gravarArquivoProfessor(professor.toString());

        limparCampos();
        taProfessor.setText("Professor cadastrado com sucesso.");
    }

    private void consultarProfessor() throws Exception {
        String cpfBusca = tfCpfProfessor.getText();

        Fila filaProfessores = new Fila();
        
        filaProfessores = dao.lerArquivoProfessor(filaProfessores);

        
        boolean encontrado = false;

        while (!filaProfessores.isEmpty()) {
            Professor p = (Professor) filaProfessores.remove();
            if (p.getCpfProfessor().equals(cpfBusca)) {
                taProfessor.setText(
                        "Nome: " + p.getNomeProfessor() +
                                "\nÁrea de Conhecimento: " + p.getAreaConhecimentoPretendida() +
                                "\nPontos: " + p.getPontos()
                );
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            taProfessor.setText("Professor não encontrado.");
        }

        limparCampos();
    }

    private void atualizarProfessor() throws Exception {
    	
        Lista<Professor> listaProfessores = dao.lerArquivoProfessor();

        if (listaProfessores.isEmpty()) {
            taProfessor.setText("Não há professores cadastrados para atualizar.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean atualizado = false;

        for (int i = 0; i < listaProfessores.size(); i++) {
            Professor p = listaProfessores.get(i);
            if (p.getCpfProfessor().equals(cpfBusca)) {
                p.setNomeProfessor(tfNomeProfessor.getText());
                p.setAreaConhecimentoPretendida(tfAreaConhecimento.getText());
                p.setPontos(Integer.parseInt(tfPontos.getText()));
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new Exception("Professor com CPF " + cpfBusca + " não encontrado.");
        }

        dao.gravarArquivoProfessor(listaProfessores);

        limparCampos();
        taProfessor.setText("Professor atualizado com sucesso.");
    }

    private void removerProfessor() throws Exception {
        Lista<Professor> listaProfessores = dao.lerArquivoProfessor();

        if (listaProfessores.isEmpty()) {
        	taProfessor.setText("Não há professores cadastrados para remover.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean removido = false;

        for (int i = 0; i < listaProfessores.size(); i++) {
            Professor p = listaProfessores.get(i);
            if (p.getCpfProfessor().equals(cpfBusca)) {
                listaProfessores.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
        	taProfessor.setText("Professor com CPF " + cpfBusca + " não encontrado.");
        }

        dao.gravarArquivoProfessor(listaProfessores);

        limparCampos();
        taProfessor.setText("Professor removido com sucesso.");
    }

   
    private void limparCampos() {
        tfCpfProfessor.setText("");
        tfNomeProfessor.setText("");
        tfAreaConhecimento.setText("");
        tfPontos.setText("");
    }
}
