package poo.sca.io;

import java.util.LinkedList;
import java.util.List;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public class SCAPersistenciaMemoria implements SCAPersistencia{
	private List <Turma> turmas;
	private List <Professor> professores;
	private List <Disciplina> disciplinas;
	private List <Curso> cursos;
	
	
	public SCAPersistenciaMemoria(){
		this.turmas = new LinkedList<Turma>();
		this.professores = new LinkedList<Professor>();
		this.disciplinas = new LinkedList<Disciplina>();
		this.cursos = new LinkedList<Curso>();
	}
	
	public void salvar(Turma turma) {
		turmas.add(turma);
	}

	public List<Turma> recuperarTurmas() {
		return turmas;
	}
	
	public void salvar(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	public List<Disciplina> recuperarDisciplinas() {
		return disciplinas;
	}

	public void salvar(Professor professor) {
		this.professores.add(professor);
	}
	
	public List<Professor> recuperarProfessores() {
		return professores;
	}
	
	public void salvar(Curso curso) {	
		this.cursos.add(curso);
	}
	
	public List<Curso> recuperarCursos() {
		return cursos;
	}
}
