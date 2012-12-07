package poo.sca;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Curso implements Serializable{
	int codigo;
	String nome;
	public Curso(String nome, int codigo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public int getCodigo() {
		return this.codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(">>> Curso\n");
		str.append("Nome: "+this.nome+"\n");
		str.append("Codigo: "+this.codigo+"\n");
		return str.toString();
	}
}
