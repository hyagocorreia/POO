package poo.sca;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Professor implements Serializable{
	int matricula;
	String nome;
	public Professor(String nome, int matricula) {
		super();
		this.matricula = matricula;
		this.nome = nome;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(">>> Professor\n");
		str.append("Nome: "+this.nome+"\n");
		str.append("Codigo: "+this.matricula+"\n");
		return str.toString();
	}
}
