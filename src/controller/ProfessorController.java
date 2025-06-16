package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Professor;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastraProfessor();
            } else if (comando.equals("Consultar")) {
                consultaProfessor();
            } else if (comando.equals("Atualizar")) {
                atualizaProfessor();
            } else if (comando.equals("Remover")) {
                removeProfessor();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void cadastraProfessor() throws Exception {
        Professor professor = new Professor();

        professor = new Professor(
                tfCpfProfessor.getText(),
                tfNomeProfessor.getText(),
                tfAreaConhecimento.getText(),
                Integer.parseInt(tfPontos.getText())
        );

        gravarArquivoProfessorCsv(professor.toString());

        limpaCampos();
        taProfessor.setText("Professor cadastrado com sucesso.");
    }

    private void consultaProfessor() throws Exception {
        String cpfBusca = tfCpfProfessor.getText();

        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "professores.csv");

        Fila filaProfessores = new Fila();

        if (arquivo.exists() && arquivo.isFile()) {
            FileInputStream fis = new FileInputStream(arquivo);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(isr);

            String linha = buffer.readLine();

            while (linha != null) {
                String[] campos = linha.split(";");
                String cpf = campos[0];
                String nome = campos[1];
                String area = campos[2];
                int pontos = Integer.parseInt(campos[3]);

                Professor p = new Professor(cpf, nome, area, pontos);
                filaProfessores.insert(p);

                linha = buffer.readLine();
            }

            buffer.close();
            isr.close();
            fis.close();
        }

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

        limpaCampos();
    }

    private void atualizaProfessor() throws Exception {
        Lista<Professor> listaProfessores = lerArquivoProfessorCsv();

        if (listaProfessores.isEmpty()) {
            throw new Exception("Não há professores cadastrados para atualizar.");
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

        gravarArquivoProfessorCsv(listaProfessores);

        limpaCampos();
        taProfessor.setText("Professor atualizado com sucesso.");
    }

    private void removeProfessor() throws Exception {
        Lista<Professor> listaProfessores = lerArquivoProfessorCsv();

        if (listaProfessores.isEmpty()) {
            throw new Exception("Não há professores cadastrados para remover.");
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
            throw new Exception("Professor com CPF " + cpfBusca + " não encontrado.");
        }

        gravarArquivoProfessorCsv(listaProfessores);

        limpaCampos();
        taProfessor.setText("Professor removido com sucesso.");
    }

    private void gravarArquivoProfessorCsv(String professor) throws IOException {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File(path, "professores.csv");

        boolean existe = arquivo.exists();

        FileWriter fw = new FileWriter(arquivo, existe);
        PrintWriter pw = new PrintWriter(fw);
        pw.write(professor + "\r\n");
        pw.flush();
        pw.close();
        fw.close();
    }

    private void gravarArquivoProfessorCsv(Lista<Professor> lista) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File(path, "professores.csv");

        FileWriter fw = new FileWriter(arquivo, false);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < lista.size(); i++) {
            Professor p = lista.get(i);
            pw.println(p.toString());
        }

        pw.flush();
        pw.close();
        fw.close();
    }

    private Lista<Professor> lerArquivoProfessorCsv() throws Exception {
        Lista<Professor> lista = new Lista<>();

        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "professores.csv");

        if (!arquivo.exists()) {
            return lista;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                String[] campos = linha.split(";");

                String cpf = campos[0];
                String nome = campos[1];
                String area = campos[2];
                int pontos = Integer.parseInt(campos[3]);

                Professor p = new Professor(cpf, nome, area, pontos);
                lista.addLast(p);
            }
        }

        br.close();
        return lista;
    }

    private void limpaCampos() {
        tfCpfProfessor.setText("");
        tfNomeProfessor.setText("");
        tfAreaConhecimento.setText("");
        tfPontos.setText("");
    }
}
