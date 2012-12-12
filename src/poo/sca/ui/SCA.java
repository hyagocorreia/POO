package poo.sca.ui;

import java.util.ArrayList;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.SCAException;
import poo.sca.SCAFacade;
import poo.sca.Turma;
import poo.sca.io.SCAPersistenciaException;

public class SCA {
	private SCAFacade facade;
		
	public SCA(){
		this.facade = new SCAFacade();
	}
	
	public static void main(String[] args) {
		SCA sca = new SCA();
		sca.exibirMenu();
	}
	
	public void exibirMenu(){
		StringBuffer menu = new StringBuffer();
		menu.append(">>> SISTEMA DE CONTROLE ACADÊMICO <<<<\n");
		menu.append("    0 - SAIR\n");
		menu.append("    1 - Cadastrar Disciplina\n");
		menu.append("    2 - Cadastrar Professor\n");
		menu.append("    3 - Cadastrar Curso\n");
		menu.append("    4 - Cadastrar Turma\n");
		menu.append("    5 - Ver Turma\n");
		menu.append("    6 - Listar Turmas\n");
		menu.append("    7 - Listar Disciplinass\n");
		menu.append("    8 - Listar Cursos\n");
		menu.append("    9 - Listar Professores\n");
		menu.append("Digite a opção:");
		
		boolean fim = false;
		
		do{
			switch(Util.lerInteiro(menu.toString())){
			case 0:
				Util.alert("Até a próxima!");
				fim = true;
				break;
			case 1:
				cadastrarDisciplina();
				break;
			case 2:
				cadastrarProfessor();
				break;
			case 3:
				cadastrarCurso();
				break;
			case 4:
				cadastrarTurma();
				break;
			case 5:
				verTurma();
				break;
			case 6:
				listarTurmas();
				break;
			case 7:
				listarDisciplinas();
				break;
			case 8:
				listarCursos();
				break;
			case 9:
				listarProfessores();
				break;
			default:
				Util.alert("Opção inválida!");
			}
		}while(!fim);
	}
	
	private void verTurma() {
		int c = Util.lerInteiro("Digite o codigo da disciplina: ");
		int n = Util.lerInteiro("Digite o numero da turma: ");
		String p = Util.lerString("Digite o período da turma: ");
		Turma t;
		try {
			t = facade.getTurma(p, c, n);
			Util.alert(t.toString());
		} catch (SCAException e1) {
			Util.alert(e1.getMessage());
		} catch (SCAPersistenciaException e2) {
			Util.alert(e2.getMessage());
		}
	}

	private void cadastrarDisciplina() {
		String nome = Util.lerString("Digite o nome da disciplina:");
		int codigo = Util.lerInteiro("Digite o código da disciplina:");
		Disciplina d = null;
		try {
			d = facade.criarDisciplina(nome,codigo);
			Util.alert("Disciplina criada com sucesso!\n" + d.getNome());
		} catch (SCAException e1) {
			Util.alert(e1.getMessage());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
	
	private void cadastrarProfessor() {
		String nome = Util.lerString("Digite o nome do professor:");
		int mat = Util.lerInteiro("Digite a matrícula do professor:");
		Professor p;
		try {
			p = facade.criarProfessor(nome,mat);
			Util.alert("Professor criado com sucesso!\n" + p.getNome());
		} catch (SCAException e1) {
			Util.alert(e1.getMessage());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}

	private void cadastrarCurso() {
		String nome = Util.lerString("Digite o nome do curso: ");
		int codigo = Util.lerInteiro("Digite o código do curso: ");
		Curso c;
		try {
			c = facade.criarCurso(nome,codigo);
			Util.alert("Curso criado com sucesso!\n" + c.getNome());
		} catch (SCAException e1) {
			Util.alert(e1.getMessage());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}

	private void cadastrarTurma(){
		int codDisciplina = Util.lerInteiro("Digite o código da disciplina");
		String periodo = Util.lerString("Digite o período da turma:");
		int numero = Util.lerInteiro("Digite o número da turma:");
		String horario = Util.lerString("Digite o horário da turma:");
		
		int opCurso = Util.lerInteiro("Deseja adicionar curso?\n1-SIM   2-NÃO");
		int codCurso = -1;
		if(opCurso == 1){
			codCurso = Util.lerInteiro("Digite o código do curso:");
		}
		
		int opProf = Util.lerInteiro("Deseja adicionar professor?\n1-SIM   2-NÃO");
		int matProf = -1;
		if(opProf == 1){
			matProf = Util.lerInteiro("Digite a matrícula do professor:");
		}
		
		Turma t;
		try {
			t = facade.criarTurma(periodo, codDisciplina, numero, matProf, codCurso, horario);
			Util.alert("Turma adicionada com sucesso!\n"+ t.toString());
		} catch (SCAException e1) {
			Util.alert(e1.getMessage());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
	
	public void listarTurmas(){
		try{
			ArrayList<Turma> turmas = (ArrayList<Turma>) facade.listarTurmas();
			Util.alert(turmas.toString());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
	
	public void listarDisciplinas(){
		try{
			ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) facade.listarDisciplinas();
			Util.alert(disciplinas.toString());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
	
	public void listarCursos(){
		try{
			ArrayList<Curso> cursos = (ArrayList<Curso>) facade.listarCursos();
			Util.alert(cursos.toString());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
	
	public void listarProfessores(){
		try{
			ArrayList<Professor> professores = (ArrayList<Professor>) facade.listarProfessores();
			Util.alert(professores.toString());
		} catch(SCAPersistenciaException e2){
			Util.alert(e2.getMessage());
		}
	}
}
