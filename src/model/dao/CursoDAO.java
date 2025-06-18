package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Curso;

public class CursoDAO {
	
	 private String path;
	 private File arquivo;
	 
	    public CursoDAO() {
	        path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	        arquivo = new File(path, "cursos.csv");
	    }
	    
	    
	    public boolean validarCurso(int codigoCurso) throws Exception {

	        if (!arquivo.exists()) {
	            return false;
	        }

	        BufferedReader br = new BufferedReader(new FileReader(arquivo));
	        String linha;
	        boolean encontrado = false;

	        while ((linha = br.readLine()) != null) {
	            String[] campos = linha.split(";");
	            int codigoArquivo = Integer.parseInt(campos[0]);

	            if (codigoArquivo == codigoCurso) {
	                encontrado = true;
	                break;
	            }
	        }

	        br.close();
	        return encontrado;
	    }    
	    
	    
	    public void gravarArquivoCurso(Curso curso) throws IOException {
	     
	        FileWriter fw = new FileWriter(arquivo, true);
	        PrintWriter pw = new PrintWriter(fw);

	        pw.write(curso + "\r\n");

	        pw.flush();
	        pw.close();
	        fw.close();
	    }
	    
	    public void gravarArquivoCurso(Lista<Curso> lista) throws Exception {
	 
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


	    public Lista<Curso> lerArquivoCurso() throws Exception {
	        Lista<Curso> lista = new Lista<>();

	        BufferedReader br = new BufferedReader(new FileReader(arquivo));
	        String linha;
	        
	        if (!arquivo.exists()) {
	        	br.close();
	            return lista; // ou uma fila vazia
	        }

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
	    
	    public Fila lerArquivoCurso(Fila filaCursos) throws Exception {
	    	
	    	BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            
            if (!arquivo.exists()) {
            	br.close();
                return filaCursos; // ou uma fila vazia
            }

        	while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                int codigoCurso = Integer.parseInt(campos[0]);
                String nomeCurso = campos[1];
                String areaConhecimento = campos[2];

                Curso curso = new Curso(codigoCurso, nomeCurso, areaConhecimento);
                filaCursos.insert(curso);
            }

            br.close();
	        

        return filaCursos;
	    }
}
