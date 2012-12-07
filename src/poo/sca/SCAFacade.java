package poo.sca;

import java.util.Iterator;
import java.util.List;

import poo.sca.Turma;
import poo.sca.io.SCAPersistencia;
import poo.sca.io.SCAPersistenciaArquivo;
import poo.sca.io.SCAPersistenciaException;
public class SCAFacade{

	private SCAPersistencia persistencia;
	
	public SCAFacade(){
		persistencia = new SCAPersistenciaArquivo();
	}

	public Iterator <Turma> getTurmasIterator() throws SCAPersistenciaException{
		return persistencia.recuperarTurmas().iterator();
	}
	
	public Iterator <Professor> getProfessoresIterator() throws SCAPersistenciaException{
		return persistencia.recuperarProfessores().iterator();
	}
	
	public Iterator <Curso> getCursosIterator() throws SCAPersistenciaException{
		return persistencia.recuperarCursos().iterator();
	}
	
	public Iterator <Disciplina> getDisciplinasIterator() throws SCAPersistenciaException{
		return persistencia.recuperarDisciplinas().iterator();
	}
	
	public Turma criarTurma(String periodo, int codDisciplina, int numero,
			int matProf, int codCurso, String horario) throws SCAException, SCAPersistenciaException{
		List <Turma> turmas = persistencia.recuperarTurmas();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == numero){
					throw new SCAException("Turma j� existe!");
			}
		}
		
		Turma turma = new Turma(periodo, numero, horario);
		
		List<Disciplina> disciplinas = persistencia.recuperarDisciplinas();
		Disciplina disciplina = null;
		for(Disciplina d: disciplinas){
			if(d.getCodigo() == codDisciplina){
				disciplina = d;
			}else{
				throw new SCAException("Disciplina n�o existe!");
			}
		}
		turma.addDisciplina(disciplina);
		
		if(codCurso != -1){
			List <Curso> cursos = persistencia.recuperarCursos();
			Curso curso = null;
			for(Curso c: cursos){
				if(c.getCodigo() == codCurso){
					curso = c;
				}else{
					throw new SCAException("Curso n�o existe!");
				}
			}
			turma.addCurso(curso);
		}
		
		if(matProf != -1){
			List <Professor> professores = persistencia.recuperarProfessores();
			Professor professor = null;
			for(Professor p: professores){
				if(p.getMatricula() == matProf){
					professor = p;
				}else{
					throw new SCAException("Professor n�o existe!");
				}
			}
			turma.addProfessor(professor);
		}
		
		persistencia.salvar(turma);
		return turma;
	}
	
	public Turma getTurma(String p, int c, int n) throws SCAException, SCAPersistenciaException{
		List<Turma> turmas = persistencia.recuperarTurmas();
		for(int i = 0; i<turmas.size(); ++i){
			if(turmas.get(i).getNumero() == n && turmas.get(i).getPeriodo().equals(p)){
				Iterator <Disciplina> d = turmas.get(i).getDisciplinaIterator();
				if(d.hasNext()){
					while(d.hasNext()){
						if(d.next().getCodigo() == c){
							return turmas.get(i);
						}else{
							throw new SCAException("Turma n�o existe!");
						}
					}
				}else{
					throw new SCAException("Turma n�o existe!");
				}
			}else{
				throw new SCAException("Turma n�o existe!");
			}
		}
		return null;
	}
	
	public Curso criarCurso(String nome, int codigo) throws SCAException, SCAPersistenciaException{
		List <Curso> aux = persistencia.recuperarCursos();
		for(Curso c: aux){
			if(c.getCodigo() == codigo || c.getNome().equals(nome))
				throw new SCAException("C�digo ou nome j� existem!");
		}
		Curso curso = new Curso(nome, codigo);
		persistencia.salvar(curso);
		return curso;
	}
	
	public Disciplina criarDisciplina(String nome, int codigo) throws SCAException, SCAPersistenciaException {
		List <Disciplina> aux = persistencia.recuperarDisciplinas();
		for(Disciplina d: aux){
			if(d.getCodigo() == codigo || d.getNome().equals(nome))
				throw new SCAException("C�digo ou nome j� existem!");
		}
		Disciplina disciplina = new Disciplina(nome,codigo);
		persistencia.salvar(disciplina);
		return disciplina;
	}
	
	public Professor criarProfessor(String nome, int matricula) throws SCAException, SCAPersistenciaException{
		List <Professor> aux = persistencia.recuperarProfessores();
		for(Professor p: aux){
			if(p.getMatricula() == matricula)
				throw new SCAException("Professor j� existe!");
		}
		Professor professor = new Professor(nome, matricula);
		persistencia.salvar(professor);
		return professor;
	}
	
	public List<Turma> listarTurmas() throws SCAPersistenciaException{
		List<Turma> turmas = persistencia.recuperarTurmas();
		return turmas;
	}
	
	public List<Disciplina> listarDisciplinas() throws SCAPersistenciaException{
		List<Disciplina> disciplinas = persistencia.recuperarDisciplinas();
		return disciplinas;
	}
	
	public List<Professor> listarProfessores() throws SCAPersistenciaException{
		List<Professor> professores = persistencia.recuperarProfessores();
		return professores;
	}
	
	public List<Curso> listarCursos() throws SCAPersistenciaException{
		List<Curso> cursos = persistencia.recuperarCursos();
		return cursos;
	}
}
