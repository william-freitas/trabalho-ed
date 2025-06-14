package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Curso;

public class CursoController {

    private static final String ARQUIVO = "dados/cursos.csv";

    public void insereCurso(Curso curso) throws Exception {
        Lista<Curso> listaArquivoCurso = lerArquivoCursoCsv();
        listaArquivoCurso.addLast(curso);
        gravarArquivoCursoCsv(listaArquivoCurso);
    }

    public void atualizaCurso(int codigo, Curso novoCurso) throws Exception {
        Lista<Curso> listaArquivoCurso = lerArquivoCursoCsv();
        boolean atualizado = false;

        for (int i = 0; i < listaArquivoCurso.size(); i++) {
            if (listaArquivoCurso.get(i).getCodigoCurso() == codigo) {
                listaArquivoCurso.remove(i);
                listaArquivoCurso.add(novoCurso, i);
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new Exception("Curso com código " + codigo + " não encontrado.");
        }

        gravarArquivoCursoCsv(listaArquivoCurso);
    }

    public void removeCurso(int codigo) throws Exception {
        Lista<Curso> listaArquivoCurso = lerArquivoCursoCsv();
        boolean removido = false;

        for (int i = 0; i < listaArquivoCurso.size(); i++) {
            if (listaArquivoCurso.get(i).getCodigoCurso() == codigo) {
                listaArquivoCurso.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
            throw new Exception("Curso com código " + codigo + " não encontrado.");
        }

        gravarArquivoCursoCsv(listaArquivoCurso);
    }

    public Fila consultaCursos() throws Exception {
        Lista<Curso> listaArquivoCurso = lerArquivoCursoCsv();
        Fila filaCursos = new Fila();
        for (int i = 0; i < listaArquivoCurso.size(); i++) {
            filaCursos.insert(listaArquivoCurso.get(i));
        }
        return filaCursos;
    }

    private Lista<Curso> lerArquivoCursoCsv() throws Exception {
        Lista<Curso> lista = new Lista<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                Curso curso = new Curso(
                    Integer.parseInt(campos[0]), campos[1], campos[2]
                );
                lista.addLast(curso);
            }
        }
        return lista;
    }

    private void gravarArquivoCursoCsv(Lista<Curso> lista) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (int i = 0; i < lista.size(); i++) {
                Curso c = lista.get(i);
                String linha = c.getCodigoCurso() + ";" +
                               c.getNomeCurso() + ";" +
                               c.getAreaConhecimento();
                writer.write(linha);
                writer.newLine();
            }
        }
        System.out.println("Arquivo de cursos gravado com sucesso!");
    }
}
