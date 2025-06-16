package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Curso;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastraCurso();
            } else if (comando.equals("Consultar")) {
                consultaCurso();
            } else if (comando.equals("Atualizar")) {
                atualizaCurso();
            } else if (comando.equals("Remover")) {
                removeCurso();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastraCurso() throws IOException {
        Curso curso = new Curso();
        curso.setCodigoCurso(Integer.parseInt(tfCursoCodigo.getText()));
        curso.setNomeCurso(tfCursoNome.getText());
        curso.setAreaConhecimento(tfCursoAreaConhecimento.getText());

        gravarArquivoCursoCsv(curso.toString());

        limparCampos();
        textAreaCurso.setText("Curso cadastrado!");
    }

    private void consultaCurso() throws Exception {
        int codigoBusca = Integer.parseInt(tfCursoCodigo.getText());

        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "cursos.csv");

        Fila filaCursos = new Fila();

        if (arquivo.exists() && arquivo.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                int codigoCurso = Integer.parseInt(campos[0]);
                String nomeCurso = campos[1];
                String areaConhecimento = campos[2];

                Curso curso = new Curso(codigoCurso, nomeCurso, areaConhecimento);
                filaCursos.insert(curso);
            }

            br.close();
        }

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

    private void atualizaCurso() throws Exception {
        Lista<Curso> listaCursos = lerArquivoCursoCsv();

        if (listaCursos.isEmpty()) {
            throw new Exception("Não há cursos cadastrados para atualizar.");
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
            throw new Exception("Curso com código " + cursoAtualizado.getCodigoCurso() + " não encontrado.");
        }

        gravarArquivoCursoCsv(listaCursos);

        limparCampos();
        textAreaCurso.setText("Curso atualizado!");
    }

    private void removeCurso() throws Exception {
        Lista<Curso> listaCursos = lerArquivoCursoCsv();

        if (listaCursos.isEmpty()) {
            throw new Exception("Não há cursos cadastrados para remover.");
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
            throw new Exception("Curso com código " + codigoRemover + " não encontrado.");
        }

        gravarArquivoCursoCsv(listaCursos);

        limparCampos();
        textAreaCurso.setText("Curso removido!");
    }

    private void gravarArquivoCursoCsv(String curso) throws IOException {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File(path, "cursos.csv");
        boolean existe = arquivo.exists();

        FileWriter fw = new FileWriter(arquivo, existe);
        PrintWriter pw = new PrintWriter(fw);

        pw.write(curso + "\r\n");

        pw.flush();
        pw.close();
        fw.close();
    }

    private void gravarArquivoCursoCsv(Lista<Curso> lista) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File(path, "cursos.csv");

        FileWriter fw = new FileWriter(arquivo, false);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < lista.size(); i++) {
            Curso curso = lista.get(i);
            pw.println(curso.toString());
        }

        pw.flush();
        pw.close();
        fw.close();
    }

    private Lista<Curso> lerArquivoCursoCsv() throws Exception {
        Lista<Curso> lista = new Lista<>();

        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "cursos.csv");

        if (!arquivo.exists()) {
            return lista;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                String[] campos = linha.split(";");

                int codigoCurso = Integer.parseInt(campos[0]);
                String nomeCurso = campos[1];
                String areaConhecimento = campos[2];

                Curso curso = new Curso(codigoCurso, nomeCurso, areaConhecimento);
                lista.addLast(curso);
            }
        }

        br.close();
        return lista;
    }

    private void limparCampos() {
        tfCursoCodigo.setText("");
        tfCursoNome.setText("");
        tfCursoAreaConhecimento.setText("");
    }
}
