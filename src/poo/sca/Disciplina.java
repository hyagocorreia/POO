package poo.sca;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Disciplina implements Serializable{
	int codigo;
	String nome;
	public Disciplina(String nome, int codigo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public int getCodigo() {
		return this.codigo;
	}
	public void setCodigo(int codigo) {
		if(codigo <= 0 || codigo >= 99999)
			throw new SCARuntimeException("Código inválido: "+codigo);
		this.codigo = codigo;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		if(nome == null || nome.equals(""))
			throw new SCARuntimeException("Nome inválido: '"+nome+"'");
		this.nome = nome;
	}
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(">>> Disciplina\n");
		str.append("Nome: "+this.nome+"\n");
		str.append("Codigo: "+this.codigo+"\n");
		return str.toString();
	}
	
}
