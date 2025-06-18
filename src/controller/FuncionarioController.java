package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.edu.fateczl.Lista.Lista;
import model.Funcionario;
import model.dao.FuncionarioDAO;
import view.TelaSistema;

public class FuncionarioController implements ActionListener{

	private JTextField tfLogin;
	private JTextField tfSenha;
	

	public FuncionarioController(JTextField tfLogin, JTextField tfSenha) {
		super();
		this.tfLogin = tfLogin;
		this.tfSenha = tfSenha;
	}

	FuncionarioDAO dao = new FuncionarioDAO();

	@Override
	public void actionPerformed(ActionEvent e) {
		 String comando = e.getActionCommand();

	        try {
	            if (comando.equals("Cadastrar")) {
	                cadastrarFuncionario();
	            } else if (comando.equals("Login")) {
	            	validarLogin();
	            } else if (comando.equals("Excluir")) {
	            	int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
	            	if(confirmacao == JOptionPane.YES_OPTION) {
	            		removerFuncionario();
	            	}else {
	            		return;
	            	}
	            }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}


	private void cadastrarFuncionario() throws IOException {

		Funcionario funcionario = new Funcionario();
		
		funcionario.setNomeFuncionario(tfLogin.getText());
        funcionario.setSenha(tfSenha.getText());
        
        dao.gravarArquivoFuncionario(funcionario);
        
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

        limparCampos();	
	}
	
	private void removerFuncionario() throws Exception {
		
		Lista<Funcionario> listaFuncionario = new Lista<>();
		
		listaFuncionario = dao.lerArquivoFuncionario();
		
		int tamanho = listaFuncionario.size();
		
		String nomeDigitado = tfLogin.getText();
		String senhaDigitadda = tfSenha.getText();
		
		boolean removido = false;
		
		for(int i = 0; i < tamanho; i++) {
			Funcionario funcionario = listaFuncionario.get(i);
			if(funcionario.getNomeFuncionario().equals(nomeDigitado) && funcionario.getSenha().equals(senhaDigitadda)) {
				listaFuncionario.remove(i);
				dao.gravarArquivoFuncionario(listaFuncionario);
				removido = true;
				break;
			}
		}
		
		if(removido) {
			JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
		}
		
		limparCampos();
	}
	
	private void validarLogin() throws Exception {
		
		Lista<Funcionario> listaFuncionario = new Lista<>();
		
		listaFuncionario = dao.lerArquivoFuncionario();
		
		boolean loginValido = false;
		
		String nomeDigitado = tfLogin.getText();
		String senhaDigitada = tfSenha.getText();
		
	    for (int i = 0; i < listaFuncionario.size(); i++) {
	    	Funcionario funcionario = listaFuncionario.get(i);
	        if (funcionario.getSenha().equals(senhaDigitada) && funcionario.getNomeFuncionario().equals(nomeDigitado)) {
	            loginValido = true;    
	        break;
	        }
	    }
		
		if (loginValido) {
			
			//chama a tela do sistema
			TelaSistema telaSistema = new TelaSistema();
			telaSistema.setVisible(true);
			
			//fecha tela login
			JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(tfLogin);
	        loginFrame.dispose();
		}  else {
	        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
	        limparCampos();
	    }
	}
	
	private void limparCampos() {
		 	tfLogin.setText("");
	        tfSenha.setText("");
	 }

}
