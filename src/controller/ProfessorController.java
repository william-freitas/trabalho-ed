package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Professor;

public class ProfessorController {

    private static final String ARQUIVO = "dados/professores.csv";

    public void insereProfessor(Professor professor) throws Exception {
        Lista<Professor> listaArquivoProfessor = lerArquivoProfessorCsv();
        listaArquivoProfessor.addLast(professor);
        gravarArquivoProfessorCsv(listaArquivoProfessor);
    }

    public void atualizaProfessor(String cpf, Professor novoProfessor) throws Exception {
        Lista<Professor> listaArquivoProfessor = lerArquivoProfessorCsv();
        boolean atualizado = false;

        for (int i = 0; i < listaArquivoProfessor.size(); i++) {
            Professor p = listaArquivoProfessor.get(i);
            if (p.getCpfProfessor().equals(cpf)) {
                listaArquivoProfessor.remove(i);
                listaArquivoProfessor.add(novoProfessor, i);
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new Exception("Professor com CPF " + cpf + " não encontrado.");
        }

        gravarArquivoProfessorCsv(listaArquivoProfessor);
    }

    public void removeProfessor(String cpf) throws Exception {
        Lista<Professor> listaArquivoProfessor = lerArquivoProfessorCsv();
        boolean removido = false;

        for (int i = 0; i < listaArquivoProfessor.size(); i++) {
            if (listaArquivoProfessor.get(i).getCpfProfessor().equals(cpf)) {
                listaArquivoProfessor.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
            throw new Exception("Professor com CPF " + cpf + " não encontrado.");
        }

        gravarArquivoProfessorCsv(listaArquivoProfessor);
    }

    public Fila consultaProfessores() throws Exception {
        Lista<Professor> listaArquivoProfessor = lerArquivoProfessorCsv();
        Fila filaProfessores = new Fila();
        for (int i = 0; i < listaArquivoProfessor.size(); i++) {
            filaProfessores.insert(listaArquivoProfessor.get(i));
        }
        return filaProfessores;
    }

    private Lista<Professor> lerArquivoProfessorCsv() throws Exception {
        Lista<Professor> lista = new Lista<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                Professor professor = new Professor(
                    campos[0], campos[1], campos[2], Integer.parseInt(campos[3])
                );
                lista.addLast(professor);
            }
        }
        return lista;
    }

    private void gravarArquivoProfessorCsv(Lista<Professor> lista) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (int i = 0; i < lista.size(); i++) {
                Professor p = lista.get(i);
                String linha = p.getCpfProfessor() + ";" +
                               p.getNomeProfessor() + ";" +
                               p.getAreaConhecimentoPretendida() + ";" +
                               p.getPontos();
                writer.write(linha);
                writer.newLine();
            }
        }
        System.out.println("Arquivo de professores gravado com sucesso!");
    }
}
