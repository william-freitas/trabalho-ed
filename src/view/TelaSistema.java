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

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscricaoController;
import controller.ProfessorController;

public class TelaSistema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaDiaDaSemana;
	private JTextField tfDisciplinaHorarioDaAula;
	private JTextField tfDisciplinaHorasDiarias;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextField tfCursoCodigo;
	private JTextField tfCursoNome;
	private JTextField tfCursoAreaConhecimento;
	private JTextField tfCpfProfessor;
	private JTextField tfNomeProfessor;
	private JTextField tfAreaConhecimento;
	private JTextField tfPontos;
	private JTextField tfCpfProfessorInscricao;
	private JTextField tfCodDisciplinaInscricao;
	private JTextField tfCodProcesso;
	private JTextField tfConsultaCodigoDisciplinaInscricao;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaSistema() {
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
		
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoCodigo.setBounds(286, 11, 162, 25);
		tabCursos.add(tfCursoCodigo);
		tfCursoCodigo.setColumns(10);
		
		tfCursoNome = new JTextField();
		tfCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoNome.setBounds(286, 60, 163, 25);
		tabCursos.add(tfCursoNome);
		tfCursoNome.setColumns(10);
		
		tfCursoAreaConhecimento = new JTextField();
		tfCursoAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCursoAreaConhecimento.setBounds(286, 110, 162, 21);
		tabCursos.add(tfCursoAreaConhecimento);
		tfCursoAreaConhecimento.setColumns(10);
		
		JButton btnCursoCadastrar = new JButton("Cadastrar");
		btnCursoCadastrar.setBackground(Color.GREEN);
		btnCursoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastrar.setBounds(0, 162, 132, 23);
		tabCursos.add(btnCursoCadastrar);
		
		JButton btnCursoAtualizar = new JButton("Atualizar");
		btnCursoAtualizar.setBackground(Color.GREEN);
		btnCursoAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoAtualizar.setBounds(160, 162, 132, 23);
		tabCursos.add(btnCursoAtualizar);
		
		JButton btnCursoConsultar = new JButton("Consultar");
		btnCursoConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoConsultar.setBackground(Color.GREEN);
		btnCursoConsultar.setBounds(316, 162, 132, 23);
		tabCursos.add(btnCursoConsultar);
		
		JButton btnCursoRemover = new JButton("Remover");
		btnCursoRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoRemover.setBackground(Color.RED);
		btnCursoRemover.setBounds(477, 162, 132, 23);
		tabCursos.add(btnCursoRemover);
		
		JTextArea textAreaCurso = new JTextArea();
		textAreaCurso.setBounds(10, 211, 589, 180);
		tabCursos.add(textAreaCurso);
		
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
		
		tfCpfProfessor = new JTextField();
		tfCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCpfProfessor.setBounds(125, 36, 126, 23);
		tabProfessores.add(tfCpfProfessor);
		tfCpfProfessor.setColumns(10);
		
		tfNomeProfessor = new JTextField();
		tfNomeProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNomeProfessor.setBounds(418, 36, 126, 23);
		tabProfessores.add(tfNomeProfessor);
		tfNomeProfessor.setColumns(10);
		
		JLabel lblProfessorAreaConhecimento = new JLabel("area conhecimento");
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setBounds(275, 117, 136, 17);
		tabProfessores.add(lblProfessorAreaConhecimento);
		
		JLabel lblProfessorPonrtos = new JLabel("pontos");
		lblProfessorPonrtos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorPonrtos.setBounds(56, 114, 62, 23);
		tabProfessores.add(lblProfessorPonrtos);
		
		tfAreaConhecimento = new JTextField();
		tfAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAreaConhecimento.setBounds(418, 117, 126, 20);
		tabProfessores.add(tfAreaConhecimento);
		tfAreaConhecimento.setColumns(10);
		
		tfPontos = new JTextField();
		tfPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPontos.setBounds(125, 117, 86, 20);
		tabProfessores.add(tfPontos);
		tfPontos.setColumns(10);
		
		JButton btnProfessorCadastrar = new JButton("Cadastrar");
		btnProfessorCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastrar.setBackground(Color.GREEN);
		btnProfessorCadastrar.setBounds(10, 177, 126, 27);
		tabProfessores.add(btnProfessorCadastrar);
		
		JButton btnProfessorAtualizar = new JButton("Atualizar");
		btnProfessorAtualizar.setBackground(Color.GREEN);
		btnProfessorAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorAtualizar.setBounds(146, 175, 136, 30);
		tabProfessores.add(btnProfessorAtualizar);
		
		JButton btnProfessorConsultar = new JButton("Consultar");
		btnProfessorConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorConsultar.setBackground(Color.GREEN);
		btnProfessorConsultar.setBounds(302, 175, 136, 30);
		tabProfessores.add(btnProfessorConsultar);
		
		JButton btnProfessorRemover = new JButton("Remover");
		btnProfessorRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorRemover.setBackground(Color.RED);
		btnProfessorRemover.setBounds(458, 175, 141, 30);
		tabProfessores.add(btnProfessorRemover);
		
		JTextArea taProfessor = new JTextArea();
		taProfessor.setBounds(10, 215, 589, 176);
		tabProfessores.add(taProfessor);
		
		
		JPanel tabInscricoes = new JPanel();
		tabbedPane.addTab("Inscricoes", null, tabInscricoes, null);
		tabInscricoes.setLayout(null);
		
		JLabel lblCpfInscricao = new JLabel("Cpf professor");
		lblCpfInscricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfInscricao.setBounds(164, 31, 100, 28);
		tabInscricoes.add(lblCpfInscricao);
		
		JLabel lblCodDisciplinaInscricao = new JLabel("Código disciplina");
		lblCodDisciplinaInscricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodDisciplinaInscricao.setBounds(165, 83, 114, 21);
		tabInscricoes.add(lblCodDisciplinaInscricao);
		
		JLabel lblCodProcesso = new JLabel("Código processo");
		lblCodProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodProcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodProcesso.setBounds(164, 133, 114, 28);
		tabInscricoes.add(lblCodProcesso);
		
		tfCpfProfessorInscricao = new JTextField();
		tfCpfProfessorInscricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCpfProfessorInscricao.setBounds(301, 37, 162, 22);
		tabInscricoes.add(tfCpfProfessorInscricao);
		tfCpfProfessorInscricao.setColumns(10);
		
		tfCodDisciplinaInscricao = new JTextField();
		tfCodDisciplinaInscricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodDisciplinaInscricao.setBounds(301, 85, 162, 20);
		tabInscricoes.add(tfCodDisciplinaInscricao);
		tfCodDisciplinaInscricao.setColumns(10);
		
		tfCodProcesso = new JTextField();
		tfCodProcesso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodProcesso.setBounds(301, 139, 162, 20);
		tabInscricoes.add(tfCodProcesso);
		tfCodProcesso.setColumns(10);
		
		JButton btnInscricaoCadastrar = new JButton("Cadastrar");
		btnInscricaoCadastrar.setBackground(Color.GREEN);
		btnInscricaoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoCadastrar.setBounds(10, 180, 114, 21);
		tabInscricoes.add(btnInscricaoCadastrar);
		
		JButton btnInscricaoAtualizar = new JButton("Atualizar");
		btnInscricaoAtualizar.setBackground(Color.GREEN);
		btnInscricaoAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoAtualizar.setBounds(164, 181, 114, 23);
		tabInscricoes.add(btnInscricaoAtualizar);
		
		JButton btnInscricaoConsultar = new JButton("Consultar");
		btnInscricaoConsultar.setBackground(Color.GREEN);
		btnInscricaoConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoConsultar.setBounds(316, 181, 114, 23);
		tabInscricoes.add(btnInscricaoConsultar);
		
		JButton btnInscricaoRemover = new JButton("Remover");
		btnInscricaoRemover.setBackground(Color.RED);
		btnInscricaoRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoRemover.setBounds(470, 181, 114, 23);
		tabInscricoes.add(btnInscricaoRemover);
		
		JTextArea textAreaInscricao = new JTextArea();
		textAreaInscricao.setBounds(10, 212, 589, 179);
		tabInscricoes.add(textAreaInscricao);
		
		
		
		JPanel tabInscricoesAbertas = new JPanel();
		tabbedPane.addTab("Inscricoes Abertas", null, tabInscricoesAbertas, null);
		tabInscricoesAbertas.setLayout(null);
		
		JButton btnConsultarInscricoesAbertas = new JButton("Consultar Inscricoes Abertas");
		btnConsultarInscricoesAbertas.setBackground(Color.GREEN);
		btnConsultarInscricoesAbertas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarInscricoesAbertas.setBounds(368, 41, 231, 50);
		tabInscricoesAbertas.add(btnConsultarInscricoesAbertas);
		
		JTextArea textAreaInscricoesAbertas = new JTextArea();
		textAreaInscricoesAbertas.setBounds(10, 102, 589, 289);
		tabInscricoesAbertas.add(textAreaInscricoesAbertas);
		
		
		
		JLabel lblConsultaCodigoDisciplina = new JLabel("Consulte pelo código disciplina");
		lblConsultaCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultaCodigoDisciplina.setBounds(10, 0, 204, 36);
		tabInscricoesAbertas.add(lblConsultaCodigoDisciplina);
		
		tfConsultaCodigoDisciplinaInscricao = new JTextField();
		tfConsultaCodigoDisciplinaInscricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfConsultaCodigoDisciplinaInscricao.setBounds(203, 10, 96, 20);
		tabInscricoesAbertas.add(tfConsultaCodigoDisciplinaInscricao);
		tfConsultaCodigoDisciplinaInscricao.setColumns(10);
		
		JButton btnConsultarPeloCodigoDisciplina = new JButton("Consultar por disciplina");
		btnConsultarPeloCodigoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarPeloCodigoDisciplina.setBounds(10, 41, 231, 50);
		tabInscricoesAbertas.add(btnConsultarPeloCodigoDisciplina);
		
	
		
		//instancias das classes Controllers:
		DisciplinaController dCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, tfDisciplinaDiaDaSemana, tfDisciplinaHorarioDaAula, tfDisciplinaHorasDiarias, tfDisciplinaCodigoCurso, taDisciplina);
		CursoController cCont = new CursoController(tfCursoCodigo, tfCursoNome, tfCursoAreaConhecimento, textAreaCurso);
		ProfessorController pCont = new ProfessorController(tfCpfProfessor, tfNomeProfessor, tfAreaConhecimento, tfPontos, taProfessor);
		InscricaoController iCont = new InscricaoController(tfCpfProfessorInscricao, tfCodDisciplinaInscricao, tfCodProcesso, tfConsultaCodigoDisciplinaInscricao, textAreaInscricao, textAreaInscricoesAbertas);
		
	
		
		//iniciar ações dos botões disciplina
		btnDisciplinaCadastrar.addActionListener(dCont);
		btnDisciplinaConsultar.addActionListener(dCont);
		btnDisciplinaAtualizar.addActionListener(dCont);
		btnDisciplinaRemover.addActionListener(dCont);
		
		
		//iniciar ações dos botões curso
		btnCursoCadastrar.addActionListener(cCont);
		btnCursoConsultar.addActionListener(cCont);
		btnCursoAtualizar.addActionListener(cCont);
		btnCursoRemover.addActionListener(cCont);
		
		
		//iniciar ações dos botões professor
		btnProfessorCadastrar.addActionListener(pCont);
		btnProfessorConsultar.addActionListener(pCont);
		btnProfessorAtualizar.addActionListener(pCont);
		btnProfessorRemover.addActionListener(pCont);
		
		//iniciar ações dos botões inscrição
		btnInscricaoCadastrar.addActionListener(iCont);
		btnInscricaoConsultar.addActionListener(iCont);
		btnInscricaoAtualizar.addActionListener(iCont);
		btnInscricaoRemover.addActionListener(iCont);
		btnConsultarInscricoesAbertas.addActionListener(iCont);
		btnConsultarPeloCodigoDisciplina.addActionListener(iCont);
		
		
	}
}
