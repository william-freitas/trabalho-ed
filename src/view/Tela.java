package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DisciplinaController;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaDiaDaSemana;
	private JTextField tfDisciplinaHorarioDaAula;
	private JTextField tfDisciplinaHorasDiarias;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextField tfCursoCodigoCurso;
	private JTextField tfCursoNomeCurso;
	private JTextField tfCursoAreaConhecimento;
	private JTextField tfProfessorCpfProfessor;
	private JTextField taProfessorNomeProfessor;
	private JTextField taProfessorAreaConhecimento;
	private JTextField taProfessorPontos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setTitle("Faculdade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 614, 430);
		contentPane.add(tabbedPane);
		
		JPanel tabDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplinas", null, tabDisciplinas, "Cadastro de Disciplinas");
		tabDisciplinas.setLayout(null);
		
		JLabel lblDisciplinaCodigoDisciplina = new JLabel("código da matéria");
		lblDisciplinaCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodigoDisciplina.setBounds(10, 14, 119, 25);
		tabDisciplinas.add(lblDisciplinaCodigoDisciplina);
		
		JLabel lblDisciplinaNomeDisciplina = new JLabel("nome");
		lblDisciplinaNomeDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaNomeDisciplina.setBounds(78, 56, 60, 25);
		tabDisciplinas.add(lblDisciplinaNomeDisciplina);
		
		JLabel lblDisciplinaDiaSemana = new JLabel("dia da semana");
		lblDisciplinaDiaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaDiaSemana.setBounds(25, 100, 104, 25);
		tabDisciplinas.add(lblDisciplinaDiaSemana);
		
		JLabel lblDisciplinaHorarioAula = new JLabel("horario da aula");
		lblDisciplinaHorarioAula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaHorarioAula.setBounds(327, 56, 104, 25);
		tabDisciplinas.add(lblDisciplinaHorarioAula);
		
		JLabel lblDisciplinaHorasDiarias = new JLabel("horas diárias ");
		lblDisciplinaHorasDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaHorasDiarias.setBounds(343, 100, 104, 25);
		tabDisciplinas.add(lblDisciplinaHorasDiarias);
		
		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCodigo.setBounds(139, 11, 88, 31);
		tabDisciplinas.add(tfDisciplinaCodigo);
		tfDisciplinaCodigo.setColumns(10);
		
		tfDisciplinaNome = new JTextField();
		tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaNome.setBounds(137, 53, 173, 31);
		tabDisciplinas.add(tfDisciplinaNome);
		tfDisciplinaNome.setColumns(10);
		
		tfDisciplinaDiaDaSemana = new JTextField();
		tfDisciplinaDiaDaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaDiaDaSemana.setBounds(139, 97, 173, 31);
		tabDisciplinas.add(tfDisciplinaDiaDaSemana);
		tfDisciplinaDiaDaSemana.setColumns(10);
		
		tfDisciplinaHorarioDaAula = new JTextField();
		tfDisciplinaHorarioDaAula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaHorarioDaAula.setBounds(452, 53, 88, 31);
		tabDisciplinas.add(tfDisciplinaHorarioDaAula);
		tfDisciplinaHorarioDaAula.setColumns(10);
		
		tfDisciplinaHorasDiarias = new JTextField();
		tfDisciplinaHorasDiarias.setBounds(452, 99, 88, 31);
		tabDisciplinas.add(tfDisciplinaHorasDiarias);
		tfDisciplinaHorasDiarias.setColumns(10);
		
		JButton btnDisciplinaCadastrar = new JButton("Cadastrar");
		btnDisciplinaCadastrar.setBackground(Color.GREEN);
		btnDisciplinaCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaCadastrar.setBounds(10, 168, 126, 23);
		tabDisciplinas.add(btnDisciplinaCadastrar);
		
		JButton btnDisciplinaAtualizar = new JButton("Atualizar");
		btnDisciplinaAtualizar.setBackground(Color.GREEN);
		btnDisciplinaAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaAtualizar.setBounds(164, 167, 131, 25);
		tabDisciplinas.add(btnDisciplinaAtualizar);
		
		JButton btnDisciplinaConsultar = new JButton("Consultar");
		btnDisciplinaConsultar.setBackground(Color.GREEN);
		btnDisciplinaConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDisciplinaConsultar.setBounds(316, 167, 131, 25);
		tabDisciplinas.add(btnDisciplinaConsultar);
		
		JButton btnDisciplinaRemover = new JButton("Remover");
		btnDisciplinaRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaRemover.setBackground(Color.RED);
		btnDisciplinaRemover.setBounds(468, 168, 131, 23);
		tabDisciplinas.add(btnDisciplinaRemover);
		
		JLabel lblDisciplinaCodigoCurso = new JLabel("código do curso");
		lblDisciplinaCodigoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodigoCurso.setBounds(321, 11, 110, 25);
		tabDisciplinas.add(lblDisciplinaCodigoCurso);
		
		tfDisciplinaCodigoCurso = new JTextField();
		tfDisciplinaCodigoCurso.setBounds(452, 8, 88, 31);
		tabDisciplinas.add(tfDisciplinaCodigoCurso);
		tfDisciplinaCodigoCurso.setColumns(10);
		
		JTextArea taDisciplina = new JTextArea();
		taDisciplina.setBounds(10, 212, 589, 179);
		tabDisciplinas.add(taDisciplina);
		
		JPanel tabCursos = new JPanel();
		tabbedPane.addTab("Cursos", null, tabCursos, "Cadastro de Cursos");
		tabCursos.setLayout(null);
		
		JLabel lblCursoCodigoCurso = new JLabel("código do curso");
		lblCursoCodigoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoCodigoCurso.setBounds(157, 11, 119, 25);
		tabCursos.add(lblCursoCodigoCurso);
		
		JLabel lblCursoNomeCurso = new JLabel("nome do curso");
		lblCursoNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoNomeCurso.setBounds(166, 60, 110, 25);
		tabCursos.add(lblCursoNomeCurso);
		
		JLabel lblCursoAreaConhecimento = new JLabel("área de conhecimento");
		lblCursoAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoAreaConhecimento.setBounds(124, 108, 168, 25);
		tabCursos.add(lblCursoAreaConhecimento);
		
		tfCursoCodigoCurso = new JTextField();
		tfCursoCodigoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoCodigoCurso.setBounds(286, 11, 162, 25);
		tabCursos.add(tfCursoCodigoCurso);
		tfCursoCodigoCurso.setColumns(10);
		
		tfCursoNomeCurso = new JTextField();
		tfCursoNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoNomeCurso.setBounds(286, 60, 163, 25);
		tabCursos.add(tfCursoNomeCurso);
		tfCursoNomeCurso.setColumns(10);
		
		tfCursoAreaConhecimento = new JTextField();
		tfCursoAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoAreaConhecimento.setBounds(286, 110, 162, 21);
		tabCursos.add(tfCursoAreaConhecimento);
		tfCursoAreaConhecimento.setColumns(10);
		
		JButton btnCursoCadastra = new JButton("Cadastrar");
		btnCursoCadastra.setBackground(Color.GREEN);
		btnCursoCadastra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastra.setBounds(0, 162, 132, 23);
		tabCursos.add(btnCursoCadastra);
		
		JButton btnCursoAtualiza = new JButton("Atualizar");
		btnCursoAtualiza.setBackground(Color.GREEN);
		btnCursoAtualiza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoAtualiza.setBounds(160, 162, 132, 23);
		tabCursos.add(btnCursoAtualiza);
		
		JButton btnCursoConsulta = new JButton("Consultar");
		btnCursoConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoConsulta.setBackground(Color.GREEN);
		btnCursoConsulta.setBounds(316, 162, 132, 23);
		tabCursos.add(btnCursoConsulta);
		
		JButton btnCursoRemove = new JButton("Remover");
		btnCursoRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoRemove.setBackground(Color.RED);
		btnCursoRemove.setBounds(477, 162, 132, 23);
		tabCursos.add(btnCursoRemove);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 211, 589, 180);
		tabCursos.add(textArea);
		
		JPanel tabProfessores = new JPanel();
		tabbedPane.addTab("Professores", null, tabProfessores, "Cadastro de professores");
		tabProfessores.setLayout(null);
		
		JLabel lblProfessorCpfProfessor = new JLabel("cpf professor");
		lblProfessorCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorCpfProfessor.setBounds(27, 36, 102, 23);
		tabProfessores.add(lblProfessorCpfProfessor);
		
		JLabel lblProfessorNomeProfessor = new JLabel("nome professor");
		lblProfessorNomeProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorNomeProfessor.setBounds(291, 36, 102, 23);
		tabProfessores.add(lblProfessorNomeProfessor);
		
		tfProfessorCpfProfessor = new JTextField();
		tfProfessorCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfessorCpfProfessor.setBounds(125, 36, 126, 23);
		tabProfessores.add(tfProfessorCpfProfessor);
		tfProfessorCpfProfessor.setColumns(10);
		
		taProfessorNomeProfessor = new JTextField();
		taProfessorNomeProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taProfessorNomeProfessor.setBounds(418, 36, 126, 23);
		tabProfessores.add(taProfessorNomeProfessor);
		taProfessorNomeProfessor.setColumns(10);
		
		JLabel lblProfessorAreaConhecimento = new JLabel("area conhecimento");
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setBounds(275, 117, 136, 17);
		tabProfessores.add(lblProfessorAreaConhecimento);
		
		JLabel lblProfessorPonrtos = new JLabel("pontos");
		lblProfessorPonrtos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorPonrtos.setBounds(56, 114, 62, 23);
		tabProfessores.add(lblProfessorPonrtos);
		
		taProfessorAreaConhecimento = new JTextField();
		taProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taProfessorAreaConhecimento.setBounds(418, 117, 126, 20);
		tabProfessores.add(taProfessorAreaConhecimento);
		taProfessorAreaConhecimento.setColumns(10);
		
		taProfessorPontos = new JTextField();
		taProfessorPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taProfessorPontos.setBounds(125, 117, 86, 20);
		tabProfessores.add(taProfessorPontos);
		taProfessorPontos.setColumns(10);
		
		JButton btnProfessorCadastra = new JButton("Cadastrar");
		btnProfessorCadastra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastra.setBackground(Color.GREEN);
		btnProfessorCadastra.setBounds(10, 177, 126, 27);
		tabProfessores.add(btnProfessorCadastra);
		
		JButton btnProfessorAtualiza = new JButton("Atualizar");
		btnProfessorAtualiza.setBackground(Color.GREEN);
		btnProfessorAtualiza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorAtualiza.setBounds(146, 175, 136, 30);
		tabProfessores.add(btnProfessorAtualiza);
		
		JButton btnProfessorConsulta = new JButton("Consultar");
		btnProfessorConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorConsulta.setBackground(Color.GREEN);
		btnProfessorConsulta.setBounds(302, 175, 136, 30);
		tabProfessores.add(btnProfessorConsulta);
		
		JButton btnProfessorRemove = new JButton("Remover");
		btnProfessorRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorRemove.setBackground(Color.RED);
		btnProfessorRemove.setBounds(458, 175, 141, 30);
		tabProfessores.add(btnProfessorRemove);
		
		JTextArea taProfessor = new JTextArea();
		taProfessor.setBounds(10, 215, 589, 176);
		tabProfessores.add(taProfessor);
		
		
		//instandcias das classes Controllers:
		DisciplinaController dCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, tfDisciplinaDiaDaSemana, tfDisciplinaHorarioDaAula, tfDisciplinaHorasDiarias, tfDisciplinaCodigoCurso, taDisciplina);
		
		
		//iniciar ações dos botões:
		btnDisciplinaCadastrar.addActionListener(dCont);
		btnDisciplinaConsultar.addActionListener(dCont);
		btnDisciplinaAtualizar.addActionListener(dCont);
		btnDisciplinaRemover.addActionListener(dCont);
	}
}
