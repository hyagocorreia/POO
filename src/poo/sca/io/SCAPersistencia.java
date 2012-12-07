package poo.sca.io;

import java.util.List;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public interface SCAPersistencia {
	public void salvar(Turma turma) throws SCAPersistenciaException;
	public List<Turma> recuperarTurmas() throws SCAPersistenciaException;
	
	public void salvar(Disciplina disciplina) throws SCAPersistenciaException;
	public List<Disciplina> recuperarDisciplinas() throws SCAPersistenciaException;
	
	public void salvar(Professor professor) throws SCAPersistenciaException;
	public List<Professor> recuperarProfessores() throws SCAPersistenciaException;
	
	public void salvar(Curso curso) throws SCAPersistenciaException;
	public List<Curso> recuperarCursos() throws SCAPersistenciaException;
}
