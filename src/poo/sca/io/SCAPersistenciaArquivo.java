package poo.sca.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public class SCAPersistenciaArquivo implements SCAPersistencia{

	private List <Turma> turmas;
	private List <Professor> professores;
	private List <Disciplina> disciplinas;
	private List <Curso> cursos;
	
	public SCAPersistenciaArquivo(){
		this.cursos = new ArrayList <Curso>();
		this.professores = new ArrayList <Professor>();
		this.disciplinas = new ArrayList <Disciplina>();
		this.turmas = new ArrayList <Turma>();
	}
	
	public void salvar(Turma turma) throws SCAPersistenciaException{
		turmas.add(turma);
		gravar(turmas,"turmas.ser");		
	}

	public void salvar(Disciplina disciplina) throws SCAPersistenciaException{
		disciplinas.add(disciplina);
		gravar(disciplinas,"disciplinas.ser");
	}

	public void salvar(Professor professor) throws SCAPersistenciaException{
		professores.add(professor);
		gravar(professores,"professores.ser");	
	}

	public void salvar(Curso curso) throws SCAPersistenciaException{
		cursos.add(curso);
		gravar(cursos,"cursos.ser");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public List<Turma> recuperarTurmas() throws SCAPersistenciaException {
		ObjectInputStream in;
		turmas = new ArrayList<Turma>();
		
		try {
			in = new ObjectInputStream(new FileInputStream("turmas.ser"));
			turmas = (ArrayList<Turma>) in.readObject();
			in.close();
		} catch(FileNotFoundException e1) {
			//throw new SCAPersistenciaException("Arquivo não encontrado!",e1);
		} catch(IOException e2){
			throw new SCAPersistenciaException("Erro de entrada e saída!", e2);
		} catch(ClassNotFoundException e3){
			throw new SCAPersistenciaException("Classe não encontrada!",e3);
		}
		
		return turmas;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public List<Disciplina> recuperarDisciplinas() throws SCAPersistenciaException {
		ObjectInputStream in;
		disciplinas = new ArrayList<Disciplina>();
		try {
			in = new ObjectInputStream(new FileInputStream("disciplinas.ser"));
			disciplinas = (ArrayList<Disciplina>) in.readObject();
			in.close();
		} catch(FileNotFoundException e1) {
			//throw new SCAPersistenciaException("Arquivo não encontrado!",e1);
		} catch(IOException e2){
			throw new SCAPersistenciaException("Erro de entrada e saída!", e2);
		} catch(ClassNotFoundException e3){
			throw new SCAPersistenciaException("Classe não encontrada!",e3);
		}
		
		return disciplinas;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public List<Professor> recuperarProfessores() throws SCAPersistenciaException{
		ObjectInputStream in;
		professores = new ArrayList<Professor>();
		try {
			in = new ObjectInputStream(new FileInputStream("professores.ser"));
			professores = (ArrayList<Professor>) in.readObject();
			in.close();
		} catch(FileNotFoundException e1) {
			//throw new SCAPersistenciaException("Arquivo não encontrado!",e1);
		} catch(IOException e2){
			throw new SCAPersistenciaException("Erro de entrada e saída!", e2);
		} catch(ClassNotFoundException e3){
			throw new SCAPersistenciaException("Classe não encontrada!",e3);
		}
		
		return professores;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public List<Curso> recuperarCursos() throws SCAPersistenciaException {
		ObjectInputStream in;
		cursos = new ArrayList<Curso>();
		try {
			in = new ObjectInputStream(new FileInputStream("cursos.ser"));
			cursos = (ArrayList<Curso>) in.readObject();
			in.close();
		} catch(FileNotFoundException e1) {
			//throw new SCAPersistenciaException("Arquivo não encontrado!",e1);
		} catch(IOException e2){
			throw new SCAPersistenciaException("Erro de entrada e saída!",e2);
		} catch(ClassNotFoundException e3){
			throw new SCAPersistenciaException("Classe não encontrada!",e3);
		}
		return cursos;
	}
	
	@SuppressWarnings("rawtypes")
	public static void gravar(List lista, String caminho) throws SCAPersistenciaException{ 
		lista = (ArrayList) lista;
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(caminho));
			out.writeObject(lista);
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			throw new SCAPersistenciaException("Arquivo não encontrado!",e1);
		} catch (IOException e2) {
			throw new SCAPersistenciaException("Erro na Gravação do Arquivo!",e2);
		}
	}
}
