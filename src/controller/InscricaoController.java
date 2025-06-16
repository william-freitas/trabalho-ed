package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista.Lista;
import br.edu.fateczl.filaObj.Fila;
import br.edu.fateczl.ordenacao.BubbleSort;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
//import model.ProfessorInscricao;

public class InscricaoController implements ActionListener {

    private JTextField tfCpfProfessor;
    private JTextField tfCodigoDisciplina;
    private JTextField tfCodigoProcesso;
    private JTextArea taInscricao;
    private JTextArea textAreaInscricoesAbertas;
    
    private JTextField tfConsultaCodigoDisciplinaInscricao;

    public InscricaoController(JTextField tfCpfProfessor, JTextField tfCodigoDisciplina, JTextField tfCodigoProcesso, JTextField tfConsultaCodigoDisciplinaInscricao, JTextArea taInscricao, JTextArea textAreaInscricoesAbertas) {
        this.tfCpfProfessor = tfCpfProfessor;
        this.tfCodigoDisciplina = tfCodigoDisciplina;
        this.tfCodigoProcesso = tfCodigoProcesso;
        this.tfConsultaCodigoDisciplinaInscricao = tfConsultaCodigoDisciplinaInscricao;
        this.taInscricao = taInscricao;
        this.textAreaInscricoesAbertas = textAreaInscricoesAbertas;
    }
    


	@Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("Cadastrar")) {
                cadastrarInscricao();
            } else if (comando.equals("Consultar")) {
                consultarInscricao();
            } else if (comando.equals("Atualizar")) {
                atualizarInscricao();
            } else if (comando.equals("Remover")) {
                removerInscricao();
            } else if (comando.equals("Consultar Inscricoes Abertas")) {
            	consultarInscricoesAbertas();
            } else if (comando.equals("Consultar por disciplina")) {
            	int codigoConsultaDisciplina = Integer.parseInt(tfConsultaCodigoDisciplinaInscricao.getText());
            	consultarInscritosPorDisciplina(codigoConsultaDisciplina);
            } 
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrarInscricao() throws Exception {
        String cpfProfessor = tfCpfProfessor.getText();
        int codigoDisciplina = Integer.parseInt(tfCodigoDisciplina.getText());
        int codigoProcesso = Integer.parseInt(tfCodigoProcesso.getText());

        boolean professorExiste = validarProfessor(cpfProfessor);
        boolean disciplinaExiste = validarDisciplina(codigoDisciplina);

        if (!professorExiste && !disciplinaExiste) {
            taInscricao.setText("Erro: Professor não encontrado e Disciplina não encontrada. Cadastre ambos antes de prosseguir.");
            return;
        } else if (!professorExiste) {
            taInscricao.setText("Erro: Professor não encontrado. Cadastre o professor antes de prosseguir.");
            return;
        } else if (!disciplinaExiste) {
            taInscricao.setText("Erro: Disciplina não encontrada. Cadastre a disciplina antes de prosseguir.");
            return;
        }

        // Se passou nas validações, pode cadastrar
        Inscricao inscricao = new Inscricao(cpfProfessor, codigoDisciplina, codigoProcesso);

        gravarArquivoInscricaoCsv(inscricao.toString());

        limparCampos();
        taInscricao.setText("Inscrição cadastrada com sucesso.");
    }
    
    private boolean validarProfessor(String cpf) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "professores.csv");

        if (!arquivo.exists()) {
            return false;
        }

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

    private boolean validarDisciplina(int codigo) throws Exception {
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

            if (codigoArquivo == codigo) {
                encontrado = true;
                break;
            }
        }

        br.close();
        return encontrado;
    }



    private void consultarInscricao() throws Exception {
        String cpfBusca = tfCpfProfessor.getText();

        Fila filaInscricoes = new Fila();
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "inscricoes.csv");

        if (arquivo.exists()) {
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
        }

        boolean encontrada = false;

        while (!filaInscricoes.isEmpty()) {
            Inscricao inscricao = (Inscricao) filaInscricoes.remove();
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                taInscricao.setText(
                    "CPF Professor: " + inscricao.getCpfProfessor() +
                    "\nCódigo Disciplina: " + inscricao.getCodigoDisciplina() +
                    "\nCódigo Processo: " + inscricao.getCodigoProcesso()
                );
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            taInscricao.setText("Inscrição não encontrada.");
        }

        limparCampos();
    }

    private void atualizarInscricao() throws Exception {
        Lista<Inscricao> listaInscricoes = lerArquivoInscricaoCsv();

        if (listaInscricoes.isEmpty()) {
            throw new Exception("Não há inscrições cadastradas para atualizar.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean atualizado = false;

        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao inscricao = listaInscricoes.get(i);
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                inscricao.setCodigoDisciplina(Integer.parseInt(tfCodigoDisciplina.getText()));
                inscricao.setCodigoProcesso(Integer.parseInt(tfCodigoProcesso.getText()));
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new Exception("Inscrição com CPF " + cpfBusca + " não encontrada.");
        }

        gravarArquivoInscricaoCsv(listaInscricoes);
        limparCampos();
        taInscricao.setText("Inscrição atualizada com sucesso.");
    }

    private void removerInscricao() throws Exception {
        Lista<Inscricao> listaInscricoes = lerArquivoInscricaoCsv();

        if (listaInscricoes.isEmpty()) {
            throw new Exception("Não há inscrições cadastradas para remover.");
        }

        String cpfBusca = tfCpfProfessor.getText();
        boolean removido = false;

        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao inscricao = listaInscricoes.get(i);
            if (inscricao.getCpfProfessor().equals(cpfBusca)) {
                listaInscricoes.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
            throw new Exception("Inscrição com CPF " + cpfBusca + " não encontrada.");
        }

        gravarArquivoInscricaoCsv(listaInscricoes);
        limparCampos();
        taInscricao.setText("Inscrição removida com sucesso.");
    }

    private void gravarArquivoInscricaoCsv(String inscricao) throws IOException {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File(path, "inscricoes.csv");
        boolean append = arquivo.exists();

        FileWriter fw = new FileWriter(arquivo, append);
        PrintWriter pw = new PrintWriter(fw);
        pw.write(inscricao + "\r\n");
        pw.flush();
        pw.close();
        fw.close();
    }

    private void gravarArquivoInscricaoCsv(Lista<Inscricao> lista) throws Exception {
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

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

    private Lista<Inscricao> lerArquivoInscricaoCsv() throws Exception {
        Lista<Inscricao> lista = new Lista<>();
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroFaculdade";
        File arquivo = new File(path, "inscricoes.csv");

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

    private void limparCampos() {
        tfCpfProfessor.setText("");
        tfCodigoDisciplina.setText("");
        tfCodigoProcesso.setText("");
    }
    
    
    private void consultarInscricoesAbertas() throws Exception {
        // Ler disciplinas
        Lista<Disciplina> disciplinas = lerArquivoDisciplinaCsv2();
        if (disciplinas.isEmpty()) {
        	textAreaInscricoesAbertas.setText("Não há disciplinas cadastradas.");
            return;
        }

        // Descobrir quantos cursos existem (vamos assumir que códigos de curso são consecutivos a partir de 1)
        int maiorCodigoCurso = 0;
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            if (d.getCodigoCurso() > maiorCodigoCurso) {
                maiorCodigoCurso = d.getCodigoCurso();
            }
        }

        // Criar a tabela de espalhamento (vetor de listas)
        @SuppressWarnings("unchecked")
		Lista<Inscricao>[] tabela = new Lista[maiorCodigoCurso];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new Lista<>();
        }

        // Ler todas as inscrições
        Lista<Inscricao> inscricoes = lerArquivoInscricaoCsv();

        // Distribuir as inscrições na tabela de espalhamento
        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao insc = inscricoes.get(i);

            // Encontrar o curso da disciplina
            int codigoCursoDaDisciplina = -1;

            for (int j = 0; j < disciplinas.size(); j++) {
                Disciplina disc = disciplinas.get(j);
                if (disc.getCodigoDisciplina() == insc.getCodigoDisciplina()) {
                    codigoCursoDaDisciplina = disc.getCodigoCurso();
                    break;
                }
            }

            // Se encontrou o curso
            if (codigoCursoDaDisciplina != -1) {
                int indiceTabela = codigoCursoDaDisciplina - 1;
                tabela[indiceTabela].addLast(insc);
            }
        }

        // Exibir na textArea
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < tabela.length; i++) {
            resultado.append("=== Curso ").append(i + 1).append(" ===\n");

            Lista<Inscricao> listaCurso = tabela[i];
            if (listaCurso.isEmpty()) {
                resultado.append("Nenhuma inscrição.\n");
            } else {
                for (int j = 0; j < listaCurso.size(); j++) {
                    resultado.append(listaCurso.get(j).toString()).append("\n");
                }
            }
            resultado.append("\n");
        }

        textAreaInscricoesAbertas.setText(resultado.toString());
    }
    
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

    private void consultarInscritosPorDisciplina(int codigoConsultaDisciplina) throws Exception {
    	//int codigoConsultaDisciplina = Integer.parseInt(tfConsultaCodigoDisciplinaInscricao.getText());

        Lista<Inscricao> listaInscricoes = lerArquivoInscricaoCsv();
         

        Lista<Inscricao> inscricoesFiltradas = new Lista<>();
        Lista<Professor> professoresFiltrados = new Lista<>();

        // Filtra as inscrições da disciplina
        for (int i = 0; i < listaInscricoes.size(); i++) {
            Inscricao insc = listaInscricoes.get(i);
            if (insc.getCodigoDisciplina() == codigoConsultaDisciplina) {
                inscricoesFiltradas.addLast(insc);
                Professor prof = buscarProfessor(insc.getCpfProfessor());
                if (prof != null) {
                    professoresFiltrados.addLast(prof);
                }
            }
        }

        if (inscricoesFiltradas.isEmpty()) {
        	textAreaInscricoesAbertas.setText("Nenhuma inscrição encontrada para esta disciplina.");
            return;
        }

        // Cria vetor de pontuações para ordenar
        int[] pontuacoes = new int[professoresFiltrados.size()];
        for (int i = 0; i < professoresFiltrados.size(); i++) {
            pontuacoes[i] = professoresFiltrados.get(i).getPontos();
        }

        
        BubbleSort bs = new BubbleSort();
        int[] pontuacoesOrdenadas = bs.bubbleSort(pontuacoes);

        // Monta saída com base nas pontuações ordenadas
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== Inscrições para a disciplina ").append(codigoConsultaDisciplina).append(" ===\n");

        for (int p : pontuacoesOrdenadas) {
            for (int i = 0; i < professoresFiltrados.size(); i++) {
                Professor prof = professoresFiltrados.get(i);
                if (prof.getPontos() == p) {
                    resultado.append("CPF: ").append(prof.getCpfProfessor()).append(" | ");
                    resultado.append("Nome: ").append(prof.getNomeProfessor()).append(" | ");
                    resultado.append("Pontuação: ").append(prof.getPontos()).append("\n");

                    // Evita duplicação
                    professoresFiltrados.remove(i);
                    break;
                }
            }
        }

        textAreaInscricoesAbertas.setText(resultado.toString());

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
    
    private Professor buscarProfessor(String cpf) throws Exception {
        Lista<Professor> professores = lerArquivoProfessorCsv(); // método que lê o arquivo e devolve a lista

        for (int i = 0; i < professores.size(); i++) {
            Professor professor = professores.get(i);
            if (professor.getCpfProfessor().equals(cpf)) {
                return professor;
            }
        }

        return null; // se não encontrar
    }
}
