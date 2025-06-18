package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Professor;

public class ProfessorDAO {

	private String path;
	private File arquivo;
	
	public ProfessorDAO() {
		 path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	     arquivo = new File(path, "professores.csv");
	}
	
	public boolean validarProfessor(String cpf) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        
        boolean encontrado = false;

        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(";");
            String cpfArquivo = campos[0];

            if (cpfArquivo.equals(cpf)) {
                encontrado = true;
                break;
            }
        }

        br.close();
        return encontrado;
    }
	
	public Professor buscarProfessor(String cpf) throws Exception {
        Lista<Professor> professores = lerArquivoProfessor(); // método que lê o arquivo e devolve a lista

        for (int i = 0; i < professores.size(); i++) {
            Professor professor = professores.get(i);
            if (professor.getCpfProfessor().equals(cpf)) {
                return professor;
            }
        }

        return null; // se não encontrar
    }
	
	public void gravarArquivoProfessor(String professor) throws IOException {

        FileWriter fw = new FileWriter(arquivo, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.write(professor + "\r\n");
        pw.flush();
        pw.close();
        fw.close();
    }
	
	public void gravarArquivoProfessor(Lista<Professor> lista) throws Exception {

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

	public Lista<Professor> lerArquivoProfessor() throws Exception {
        Lista<Professor> lista = new Lista<>();

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
	
	public Fila lerArquivoProfessor(Fila filaProfessores) throws Exception{
		
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
			return filaProfessores;
    }

	
}
