package poo.sca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Turma implements Serializable{
	private String horario, periodo;
	private int numero;
	private ArrayList <Disciplina> disciplinas = new ArrayList <Disciplina>();
	private ArrayList <Professor> professores = new ArrayList <Professor>();
	private ArrayList <Curso> cursos = new ArrayList <Curso>();
	
	public Turma(String periodo, int numero, String horario) {
		this.periodo = periodo;
		this.numero = numero;
		this.horario = horario;
	}
	
	public void addProfessor(Professor professor){
		professores.add(professor);
	}
	
	public void addCurso(Curso curso){
		cursos.add(curso);
	}
	
	public Iterator <Professor> getProfessoresIterator(){
		return professores.iterator();
	}
	
	public Iterator <Curso> getCursosIterator(){
		return cursos.iterator();
	}
	
	public void addDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}
	
	public Iterator<Disciplina> getDisciplinaIterator() {
		return disciplinas.iterator();
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public String getHorario() {
		return horario;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getPeriodo() {
		return periodo;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(">>> Turma\n");
		str.append("Disciplina: "+this.getDisciplinaIterator()+"\n");
		str.append("Número: "+this.getNumero()+"\n");
		str.append("Período: "+this.getPeriodo()+"\n");
		str.append("Horário: "+this.getHorario()+"\n");
		str.append("Cursos: "+this.getCursosIterator()+"\n");
		str.append("Professores: "+this.getProfessoresIterator()+"\n");
		
		return str.toString();
	}
}
