package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import model.Inscricao;

public class InscricaoDAO {

	private String path;
	private File arquivo;
	
	public InscricaoDAO() {
		 path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	     arquivo = new File(path, "inscricoes.csv");
	}
	
	
	public void gravarArquivoInscricao(Inscricao inscricao) throws IOException {

        FileWriter fw = new FileWriter(arquivo, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.write(inscricao + "\r\n");
        pw.flush();
        pw.close();
        fw.close();
    }
	
	public void gravarArquivoInscricao(Lista<Inscricao> lista) throws Exception {
       
        File arquivo = new File(path, "inscricoes.csv");
        FileWriter fw = new FileWriter(arquivo, false);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < lista.size(); i++) {
            Inscricao inscricao = lista.get(i);
            pw.println(inscricao.toString());
        }

        pw.flush();
        pw.close();
        fw.close();
    }
	
	public Lista<Inscricao> lerArquivoInscricao() throws Exception {
        Lista<Inscricao> lista = new Lista<>();
      
        if (!arquivo.exists()) {
            return lista;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                String[] campos = linha.split(";");
                String cpf = campos[0];
                int codigoDisciplina = Integer.parseInt(campos[1]);
                int codigoProcesso = Integer.parseInt(campos[2]);

                Inscricao inscricao = new Inscricao(cpf, codigoDisciplina, codigoProcesso);
                lista.addLast(inscricao);
            }
        }

        br.close();
        return lista;
    }

	
	public Fila lerArquivoInscricao(Fila filaInscricoes) throws Exception{
		
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                String cpf = campos[0];
                int codigoDisciplina = Integer.parseInt(campos[1]);
                int codigoProcesso = Integer.parseInt(campos[2]);

                Inscricao inscricao = new Inscricao(cpf, codigoDisciplina, codigoProcesso);
                filaInscricoes.insert(inscricao);
            }

            br.close();
			return filaInscricoes;
        }
	

}
