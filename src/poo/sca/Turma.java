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
		return this.horario;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getPeriodo() {
		return this.periodo;
	}

	public void setNumero(int numero) {
		if(numero <= 0 || numero >= 99999 )
			throw new SCARuntimeException("Número inválido: '"+ numero);
		this.numero = numero;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		Iterator<Disciplina> itd = getDisciplinaIterator();
		Iterator<Curso> itc = getCursosIterator();
		Iterator<Professor> itp = getProfessoresIterator();
		
		str.append(">>> Turma\n");
		if(itd != null){
			str.append("Disciplina: ");
			while(itd.hasNext()){
				Disciplina aux = itd.next();
				str.append(aux.getNome()+" - "+aux.getCodigo()+"\n");
			}
		}
		str.append("Número: "+getNumero()+"\n");
		str.append("Período: "+getPeriodo()+"\n");
		str.append("Horário: "+getHorario()+"\n");
		
		if(itc != null){
			str.append("Cursos: ");
			while(itc.hasNext()){
				Curso aux = itc.next();
				str.append(aux.getNome()+" - "+aux.getCodigo()+"\n");
			}
		}
		
		if(itp != null){
			str.append("Professores: ");
			while(itp.hasNext()){
				Professor aux = itp.next();
				str.append(aux.getNome()+" - "+aux.getMatricula()+"\n");
			}
		}
		return str.toString();
	}
}
