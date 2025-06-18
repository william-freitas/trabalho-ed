package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.edu.fateczl.Lista.Lista;
import model.Funcionario;

public class FuncionarioDAO {

	private String path;
	private File arquivo;
	
	
	public FuncionarioDAO() {
		 path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	     arquivo = new File(path, "funcionarios.csv");
	}
	
	public void gravarArquivoFuncionario(Funcionario funcionario) throws IOException {
     
        FileWriter fw = new FileWriter(arquivo, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.write(funcionario + "\r\n");

        pw.flush();
        pw.close();
        fw.close();
		
	}

	public void gravarArquivoFuncionario(Lista<Funcionario> lista) throws Exception {
       
        FileWriter fw = new FileWriter(arquivo, false);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < lista.size(); i++) {
            Funcionario funcionario = lista.get(i);
            pw.println(funcionario.toString());
        }

        pw.flush();
        pw.close();
        fw.close();
    }
	
	public Lista<Funcionario> lerArquivoFuncionario() throws Exception {

		
		Lista<Funcionario> lista = new Lista<>();

        if (!arquivo.exists()) {
            return lista;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                String[] campos = linha.split(";");

                String nomeFuncionario = campos[0];
                String senha = campos[1];

                Funcionario funcionario = new Funcionario(nomeFuncionario, senha);
                lista.addLast(funcionario);
            }
        }

        br.close();
        return lista;
		
	}




}
