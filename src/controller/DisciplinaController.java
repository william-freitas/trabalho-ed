package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import model.Disciplina;

public class DisciplinaController implements ActionListener{

    
    private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaDiaDaSemana;
	private JTextField tfDisciplinaHorarioDaAula;
	private JTextField tfDisciplinaHorasDiarias;
	private JTextField tfDisciplinaCodigoCurso;
    private JTextArea taDisciplina;
    
 
    public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome,
			JTextField tfDisciplinaDiaDaSemana, JTextField tfDisciplinaHorarioDaAula,
			JTextField tfDisciplinaHorasDiarias, JTextField tfDisciplinaCodigoCurso, JTextArea taDisciplina) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.tfDisciplinaDiaDaSemana = tfDisciplinaDiaDaSemana;
		this.tfDisciplinaHorarioDaAula = tfDisciplinaHorarioDaAula;
		this.tfDisciplinaHorasDiarias = tfDisciplinaHorasDiarias;
		this.tfDisciplinaCodigoCurso = tfDisciplinaCodigoCurso;
		this.taDisciplina = taDisciplina;
	}
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("Cadastrar")) {
			
			try {
				cadastraDisciplina();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(comando.equals("Consultar")) {
			
			try {
				consultaDisciplina();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(comando.equals("Atualizar")) {
			
			try {
				atualizaDisciplina();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(comando.equals("Remover")) {
			
			try {
				removeDisciplina();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
    

	// CADASTRA no final da lista
    private void cadastraDisciplina() throws Exception {
        int codigoDisciplina = Integer.parseInt(tfDisciplinaCodigo.getText());
        String nomeDisciplina = tfDisciplinaNome.getText();
        String diaSemana = tfDisciplinaDiaDaSemana.getText();
        String horarioAula = tfDisciplinaHorarioDaAula.getText();
        int horasDiarias = Integer.parseInt(tfDisciplinaHorasDiarias.getText());
        int codigoCurso = Integer.parseInt(tfDisciplinaCodigoCurso.getText());

        boolean cursoExiste = validarCurso(codigoCurso);
        boolean disciplinaExiste = validarDisciplina(codigoDisciplina);

        if (!cursoExiste) {
            taDisciplina.setText("Erro: Curso não encontrado. Cadastre o curso antes de cadastrar a disciplina.");
            return;
        }

        if (disciplinaExiste) {
            taDisciplina.setText("Erro: Código da disciplina já cadastrado. Use outro código.");
            return;
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigoDisciplina(codigoDisciplina);
        disciplina.setNomeDisciplina(nomeDisciplina);
        disciplina.setDiaSemana(diaSemana);
        disciplina.setHorarioAula(horarioAula);
        disciplina.setHorasDiarias(horasDiarias);
        disciplina.setCodigoCurso(codigoCurso);

        gravarArquivoDisciplinaCsv2(disciplina.toString());

        // Limpar campos
        tfDisciplinaCodigo.setText("");
        tfDisciplinaNome.setText("");
        tfDisciplinaDiaDaSemana.setText("");
        tfDisciplinaHorarioDaAula.setText("");
        tfDisciplinaHorasDiarias.setText("");
        tfDisciplinaCodigoCurso.setText("");

        taDisciplina.setText("Disciplina cadastrada com sucesso.");
    }

    private boolean validarCurso(int codigoCurso) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "cursos.csv");

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

    private boolean validarDisciplina(int codigoDisciplina) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "disciplinas.csv");

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

    
    
    // CONSULTA usando FILA: todas as disciplinas são inseridas na fila
    private void consultaDisciplina() throws Exception {
        
        int codigoBusca = Integer.parseInt(tfDisciplinaCodigo.getText());
        
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "disciplinas.csv");

        Fila filaDisciplinas = new Fila();

        if (arquivo.exists() && arquivo.isFile()) {
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
                filaDisciplinas.insert(disciplina);  // Insere na fila

                linha = buffer.readLine();
            }

            buffer.close();
            isr.close();
            fis.close();
        }

        boolean encontrada = false;

        while (!filaDisciplinas.isEmpty()) {
            Disciplina d = (Disciplina) filaDisciplinas.remove();
            if (d.getCodigoDisciplina() == codigoBusca) {
                taDisciplina.setText(
                    "Nome: " + d.getNomeDisciplina() +
                    "\nDia da Semana: " + d.getDiaSemana() +
                    "\nHorário da Aula: " + d.getHorarioAula() +
                    "\nHoras Diárias: " + d.getHorasDiarias() +
                    "\nCódigo do Curso: " + d.getCodigoCurso()
                );
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            taDisciplina.setText("Disciplina não encontrada.");
        }

        // Limpar os campos após a consulta
        tfDisciplinaCodigo.setText("");
        tfDisciplinaNome.setText("");
        tfDisciplinaDiaDaSemana.setText("");
        tfDisciplinaHorarioDaAula.setText("");
        tfDisciplinaHorasDiarias.setText("");
        tfDisciplinaCodigoCurso.setText("");
    }

    
    // ATUALIZA posição da lista
    private void atualizaDisciplina() throws Exception {
    	
    	Lista<Disciplina> listaArquivoDisciplina = lerArquivoDisciplinaCsv2();
    	
    	if (listaArquivoDisciplina.isEmpty()) {
	        throw new Exception("Não há disciplinas cadastradas para atualizar.");
	    }
    	
    	Disciplina disciplinaAtualizada = new Disciplina();
    	disciplinaAtualizada.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));
    	
    	boolean atualizado = false;
	    int tamanho = listaArquivoDisciplina.size();

	    for (int i = 0; i < tamanho; i++) {
	        Disciplina disciplina = listaArquivoDisciplina.get(i);
	        if (disciplina.getCodigoDisciplina() == disciplinaAtualizada.getCodigoDisciplina()) {
	        	disciplina.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));
	        	disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
	        	disciplina.setDiaSemana(tfDisciplinaDiaDaSemana.getText());
	        	disciplina.setHorarioAula(tfDisciplinaHorarioDaAula.getText());
	        	disciplina.setHorasDiarias(Integer.parseInt(tfDisciplinaHorasDiarias.getText()));
	        	disciplina.setCodigoCurso(Integer.parseInt(tfDisciplinaCodigoCurso.getText()));
	            atualizado = true;
	            break;
	        }
	    }

	    if (!atualizado) {
	        throw new Exception("Disciplina com código " + disciplinaAtualizada.getCodigoDisciplina() + " não encontrada.");
	    }

	    gravarArquivoDisciplinaCsv2(listaArquivoDisciplina);
	    
	    
	  //para apagar os campos:
    	tfDisciplinaCodigo.setText("");
    	tfDisciplinaNome.setText("");
    	tfDisciplinaDiaDaSemana.setText("");
    	tfDisciplinaHorarioDaAula.setText("");
    	tfDisciplinaHorasDiarias.setText("");
    	tfDisciplinaCodigoCurso.setText("");
    	
    	taDisciplina.setText("Disciplina atualizada");
    	
    }
    
    // REMOVE posição da lista
    private void removeDisciplina() throws Exception {
    	
    	 
    	 Lista<Disciplina> listaArquivoDisciplina = lerArquivoDisciplinaCsv2();

    	    if (listaArquivoDisciplina.isEmpty()) {
    	        throw new Exception("Não há disciplinas cadastradas para remover.");
    	    }
    	    
    	    Disciplina removerDisciplina = new Disciplina();
        	removerDisciplina.setCodigoDisciplina(Integer.parseInt(tfDisciplinaCodigo.getText()));

    	    boolean removido = false;
    	    int tamanho = listaArquivoDisciplina.size();

    	    for (int i = 0; i < tamanho; i++) {
    	        Disciplina disciplina = listaArquivoDisciplina.get(i);
    	        if (disciplina.getCodigoDisciplina() == removerDisciplina.getCodigoDisciplina()) {
    	            listaArquivoDisciplina.remove(i);
    	            removido = true;
    	            break;
    	        }
    	    }

    	    if (!removido) {
    	        throw new Exception("Disciplina com código " + removerDisciplina + " não encontrada.");
    	    }

    	    gravarArquivoDisciplinaCsv2(listaArquivoDisciplina);
    	    
    	    removerDisciplinaDasInscricoes(removerDisciplina.getCodigoDisciplina());
    	    
    	  //para apagar os campos:
        	tfDisciplinaCodigo.setText("");
        	tfDisciplinaNome.setText("");
        	tfDisciplinaDiaDaSemana.setText("");
        	tfDisciplinaHorarioDaAula.setText("");
        	tfDisciplinaHorasDiarias.setText("");
        	tfDisciplinaCodigoCurso.setText("");
        	
        	taDisciplina.setText("Disciplina removida");
    }


    private void removerDisciplinaDasInscricoes(int codDisciplina) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "inscricoes.csv");

        if (!arquivo.exists()) {
            // Se o arquivo ainda nem existir, não precisa fazer nada
            return;
        }

        File tempFile = new File(path, "inscricoes_temp.csv");

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String linha;

        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(";");

            int codigoDisciplinaNaLinha = Integer.parseInt(campos[1]);  // Supondo que campo 1 é o código da disciplina na inscrição

            if (codigoDisciplinaNaLinha != codDisciplina) {
                // Só grava as inscrições que não têm o código da disciplina que está sendo removida
                bw.write(linha);
                bw.newLine();
            }
        }

        br.close();
        bw.close();

        // Agora substitui o arquivo original pelo temporário
        arquivo.delete();
        tempFile.renameTo(arquivo);
    }


 
	private void gravarArquivoDisciplinaCsv2(String disciplina) throws IOException {
		
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
		File dir = new File(path);
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		File arquivo = new File(path,"disciplinas.csv");
		
		boolean existe = false;
		if(arquivo.exists()) {
			existe = true;
		}
		
		FileWriter fw = new FileWriter(arquivo, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(disciplina+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
		
	}
	
	
	private void gravarArquivoDisciplinaCsv2(Lista<Disciplina> lista) throws Exception {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
	    File dir = new File(path);

	    if (!dir.exists()) {
	        dir.mkdir();
	    }

	    File arquivo = new File(path, "disciplinas.csv");

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

	
	//LEITURA EM CSV
	private Lista<Disciplina> lerArquivoDisciplinaCsv2() throws Exception {
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

}
