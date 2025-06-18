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
import model.Disciplina;

public class DisciplinaDAO {

	private String path;
	private File arquivo;
	 
    public DisciplinaDAO() {
        path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        arquivo = new File(path, "disciplinas.csv");
    }
	
    
    public boolean validarDisciplina(int codigoDisciplina) throws Exception {

        if (!arquivo.exists()) {
            return false;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        boolean encontrado = false;

        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(";");
            int codigoArquivo = Integer.parseInt(campos[0]);

            if (codigoArquivo == codigoDisciplina) {
                encontrado = true;
                break;
            }
        }

        br.close();
        return encontrado;
    }
    
    
    public void gravarArquivoDisciplina(Disciplina disciplina) throws IOException {
		
		FileWriter fw = new FileWriter(arquivo, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(disciplina+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
		
	}
    
    public void gravarArquivoDisciplina(Lista<Disciplina> lista) throws Exception {

	    FileWriter fw = new FileWriter(arquivo, false); // false para sobrescrever
	    PrintWriter pw = new PrintWriter(fw);

	    for (int i = 0; i < lista.size(); i++) {
	        Disciplina disciplina = lista.get(i);
	        pw.println(disciplina.toString());
	    }

	    pw.flush();
	    pw.close();
	    fw.close();
	}
    
    
    
    
    public Lista<Disciplina> lerArquivoDisciplina() throws Exception {
	    Lista<Disciplina> lista = new Lista<>();

	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	    File arquivo = new File(path, "disciplinas.csv");

	    if (!arquivo.exists()) {
	        // Se o arquivo não existir, retorna lista vazia
	    	System.out.println("Arquivo não encontrado ou não existe");
	        return lista;
	    }

	    BufferedReader br = new BufferedReader(new FileReader(arquivo));
	    String linha;

	    while ((linha = br.readLine()) != null) {
	        if (!linha.trim().isEmpty()) {
	            String[] campos = linha.split(";");
	            
	            int codigoDisciplina = Integer.parseInt(campos[0]);
	            String nomeDisciplina = campos[1];
	            String diaSemana = campos[2];
	            String horarioAula = campos[3];
	            int horasDiarias = Integer.parseInt(campos[4]);
	            int codigoCurso = Integer.parseInt(campos[5]);

	            Disciplina disciplina = new Disciplina(codigoDisciplina, nomeDisciplina, diaSemana, horarioAula, horasDiarias, codigoCurso);
	            lista.addLast(disciplina);
	        }
	    }

	    br.close();
	    return lista;
	}
    
    public Fila lerArquivoDisciplina(Fila filaDisciplinas) throws Exception {
	    
	    if (!arquivo.exists()) {
            return filaDisciplinas;
	    }

        FileInputStream fis = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader buffer = new BufferedReader(isr);

        String linha = buffer.readLine();

        while (linha != null) {
            String[] campos = linha.split(";");
            int codigoDisciplina = Integer.parseInt(campos[0]);
            String nomeDisciplina = campos[1];
            String diaSemana = campos[2];
            String horarioAula = campos[3];
            int horasDiarias = Integer.parseInt(campos[4]);
            int codigoCurso = Integer.parseInt(campos[5]);

            Disciplina disciplina = new Disciplina(codigoDisciplina, nomeDisciplina, diaSemana, horarioAula, horasDiarias, codigoCurso);
            filaDisciplinas.insert(disciplina); 

            linha = buffer.readLine();
        }

        buffer.close();
        isr.close();
        fis.close();
        
        return filaDisciplinas;
	}


}
