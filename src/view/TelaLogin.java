package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.FuncionarioController;
import java.awt.Color;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfLogin;
	private JTextField tfSenha;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("Login/Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 436, 263);
		contentPane.add(tabbedPane);
		
		JPanel tabLogin = new JPanel();
		tabbedPane.addTab("login", null, tabLogin, "login/cadastro");
		tabLogin.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(139, 69, 64, 20);
		tabLogin.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(139, 124, 64, 20);
		tabLogin.add(lblSenha);
		
		tfLogin = new JTextField();
		tfLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfLogin.setBounds(213, 71, 96, 20);
		tabLogin.add(tfLogin);
		tfLogin.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSenha.setBounds(213, 126, 96, 20);
		tabLogin.add(tfSenha);
		tfSenha.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(114, 175, 89, 23);
		tabLogin.add(btnLogin);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(213, 175, 96, 23);
		tabLogin.add(btnCadastrar);
		
		//instancia ProfissionalController
		FuncionarioController pCont = new FuncionarioController(tfLogin, tfSenha); 
		
		JLabel lblNewLabel = new JLabel("SISTEMA FACULDADE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(24, 11, 397, 47);
		tabLogin.add(lblNewLabel);
		
		JLabel lblRemoverFuncionario = new JLabel("Remover Funcionario");
		lblRemoverFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRemoverFuncionario.setBounds(223, 209, 104, 14);
		tabLogin.add(lblRemoverFuncionario);
		
		JButton btnRemoverFuncionario = new JButton("Excluir");
		btnRemoverFuncionario.setBackground(Color.RED);
		btnRemoverFuncionario.setBounds(337, 206, 84, 23);
		tabLogin.add(btnRemoverFuncionario);
		
		
		//iniciar ações dos botões tela Login
		btnLogin.addActionListener(pCont);
		btnCadastrar.addActionListener(pCont);
		btnRemoverFuncionario.addActionListener(pCont);
		
	}
}
