package poo.sca.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public class SCAPersistenciaArquivo implements SCAPersistencia {

	private static File dataDir;
	static {
		URL url = SCAPersistenciaArquivo.class.getResource("/");
		File classesDir = new File(url.getPath());
		dataDir = new File(classesDir.getParentFile().getPath() + "/data");

		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
	}

	private File disciplinaFile;
	private File turmaFile;
	private File cursoFile;
	private File professorFile;

	public SCAPersistenciaArquivo() {
		disciplinaFile = new File(dataDir.getPath() + "/disciplinas.ser");
		turmaFile = new File(dataDir.getPath() + "/turmas.ser");
		cursoFile = new File(dataDir.getPath() + "/cursos.ser");
		professorFile = new File(dataDir.getPath() + "/professores.ser");
	}

	public void salvar(Disciplina disciplina) throws SCAPersistenciaException {
		StringBuilder line = new StringBuilder();
		line.append(disciplina.getCodigo() + ";" + disciplina.getNome() + "\n");
		
		try {
			FileWriter out = new FileWriter(disciplinaFile, true);
			out.write(line.toString());
			out.close();
		} catch (IOException e) {
			throw new SCAPersistenciaException(e);
		}
	}

	@SuppressWarnings("resource")
	public List<Disciplina> recuperarDisciplinas() throws SCAPersistenciaException {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		if (!disciplinaFile.exists())
			return disciplinas;

		FileInputStream in;
		int nlinha = 0;
		try {
			in = new FileInputStream(disciplinaFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String linha;
			while ((linha = reader.readLine()) != null) {
				nlinha++;
				StringTokenizer tokens = new StringTokenizer(linha,";");
				if(tokens.countTokens() != 2)
					throw new SCAPersistenciaException("Erro no formato do arquivo!\n Arquivo: "+disciplinaFile.toString()+"\n Linha: "+nlinha);
				Disciplina disc = new Disciplina(linha, nlinha);
				disc.setCodigo(Integer.parseInt(tokens.nextToken()));
				disc.setNome(tokens.nextToken());
				disciplinas.add(disc);
			}
			reader.close();
		} catch (Exception e) {
			throw new SCAPersistenciaException("Erro ao ler o arquivo "+disciplinaFile.toString()+", na linha:"+nlinha,e);
		}
		return disciplinas;
	}

	public void salvar(Turma turma) throws SCAPersistenciaException {
		StringBuilder line = new StringBuilder();
		
		Iterator<Disciplina> discs = turma.getDisciplinaIterator();
		Iterator<Curso> cursos = turma.getCursosIterator();
		Iterator<Professor> profs = turma.getProfessoresIterator();
		
		List<Disciplina> discsTurma = new ArrayList<Disciplina>();
		List<Curso> cursosTurma = new ArrayList<Curso>();
		List<Professor> profsTurma = new ArrayList<Professor>();
		
		StringBuilder strDiscs = new StringBuilder();
		StringBuilder strCursos = new StringBuilder();
		StringBuilder strProfs = new StringBuilder();
		
		while(discs.hasNext()){
			discsTurma.add(discs.next());
		}
		while(cursos.hasNext()){
			cursosTurma.add(cursos.next());
		}
		while(profs.hasNext()){
			profsTurma.add(profs.next());
		}
		
		for(int i = 0; i<discsTurma.size(); ++i){
			strDiscs.append("|" + discsTurma.get(i).getCodigo() + ";" + discsTurma.get(i).getNome() + ";");
		}
		strDiscs.append("|");
		for(int i = 0; i<cursosTurma.size(); ++i){
			strCursos.append(cursosTurma.get(i).getCodigo() + ";" + cursosTurma.get(i).getNome() + ";");
		}
		strCursos.append("|");
		for(int i = 0; i<profsTurma.size(); ++i){
			strProfs.append(profsTurma.get(i).getMatricula() + ";" + profsTurma.get(i).getNome() + ";");
		}
		
		line.append(turma.getNumero() + ";" + turma.getPeriodo() + ";" + turma.getHorario() + 
				strDiscs + strCursos + strProfs + "\n");
		
		try {
			FileWriter out = new FileWriter(turmaFile, true);
			out.write(line.toString());
			out.close();
		} catch (IOException e) {
			throw new SCAPersistenciaException(e);
		}
	}

	public List<Turma> recuperarTurmas() throws SCAPersistenciaException {
		List<Turma> turmas = new ArrayList<Turma>();
		if (!turmaFile.exists())
			return turmas;
		
		int nlinha = 0;
		try {
			FileInputStream in = new FileInputStream(turmaFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String linha;
			Turma turma = new Turma("", 0,"");
			while ((linha = reader.readLine()) != null) {
				nlinha++;
							
				String[] arrayLinha = linha.split("\\|");

				String strNuPeHo = arrayLinha[0].toString();
				String[] arrayNuPeHo = strNuPeHo.split("\\;");
				
				turma.setNumero(Integer.parseInt(arrayNuPeHo[0]));
				turma.setPeriodo(arrayNuPeHo[1]);
				turma.setHorario(arrayNuPeHo[2]);
				
				String strDiscs = arrayLinha[1].toString();
				String[] arrayDiscs = strDiscs.split("\\;");
				Disciplina disc = null;
				for(int i = 0; i < arrayDiscs.length; ++i){
					if(i%2 == 0){
						disc =  new Disciplina("",0);
						disc.setCodigo(Integer.parseInt(arrayDiscs[i]));
					}else{
						disc.setNome(arrayDiscs[i]);
					}
					if(i%2 == 1){
						turma.addDisciplina(disc);
					}
				}

				String strCursos = arrayLinha[2].toString();
				String[] arrayCursos = strCursos.split("\\;");
				Curso curso = null;
				for(int i = 0; i < arrayCursos.length; ++i){
					if(i%2 == 0){
						curso = new Curso("",0);
						curso.setCodigo(Integer.parseInt(arrayCursos[i]));
					}else{
						curso.setNome(arrayCursos[i]);
					}
					if(i%2 == 1){
						turma.addCurso(curso);
					}
				}
				
				String strProfs = arrayLinha[3].toString();
				String[] arrayProfs = strProfs.split("\\;");
				Professor prof = null;
				for(int i = 0; i < arrayProfs.length; ++i){
					if(i%2 == 0){
						prof =  new Professor("",0);
						prof.setMatricula(Integer.parseInt(arrayProfs[i]));
					}else{
						prof.setNome(arrayProfs[i]);
					}
					if(i%2 == 1){
						turma.addProfessor(prof);
					}
				}
				
			}
			reader.close();
			
			turmas.add(turma);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SCAPersistenciaException("Erro ao ler o arquivo "+turmaFile.toString()+", na linha:"+nlinha,e);
		}
		return turmas;
	}

	public void salvar(Professor professor) throws SCAPersistenciaException {
		StringBuilder line = new StringBuilder();
		line.append(professor.getMatricula() + ";" + professor.getNome() + "\n");
		
		try {
			FileWriter out = new FileWriter(professorFile, true);
			out.write(line.toString());
			out.close();
		} catch (IOException e) {
			throw new SCAPersistenciaException(e);
		}
	}

	@SuppressWarnings("resource")
	public List<Professor> recuperarProfessores() throws SCAPersistenciaException {
		List<Professor> professores = new ArrayList<Professor>();
		if (!professorFile.exists())
			return professores;

		FileInputStream in;
		int nlinha = 0;
		try {
			in = new FileInputStream(professorFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String linha;
			while ((linha = reader.readLine()) != null) {
				nlinha++;
				StringTokenizer tokens = new StringTokenizer(linha,";");
				if(tokens.countTokens() != 2)
					throw new SCAPersistenciaException("Erro no formato do arquivo!\n Arquivo: "+professorFile.toString()+"\n Linha: "+nlinha);
				Professor prof = new Professor(linha, nlinha);
				prof.setMatricula(Integer.parseInt(tokens.nextToken()));
				prof.setNome(tokens.nextToken());
				professores.add(prof);
			}
			reader.close();
		} catch (Exception e) {
			throw new SCAPersistenciaException("Erro ao ler o arquivo "+professorFile.toString()+", na linha:"+nlinha,e);
		}
		return professores;
	}

	public void salvar(Curso curso) throws SCAPersistenciaException {
		StringBuilder line = new StringBuilder();
		line.append(curso.getCodigo() + ";" + curso.getNome() + "\n");
		
		try {
			FileWriter out = new FileWriter(cursoFile, true);
			out.write(line.toString());
			out.close();
		} catch (IOException e) {
			throw new SCAPersistenciaException(e);
		}
	}

	@SuppressWarnings("resource")
	public List<Curso> recuperarCursos() throws SCAPersistenciaException {
		List<Curso> cursos = new ArrayList<Curso>();
		if (!cursoFile.exists())
			return cursos;

		FileInputStream in;
		int nlinha = 0;
		try {
			in = new FileInputStream(cursoFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String linha;
			while ((linha = reader.readLine()) != null) {
				nlinha++;
				StringTokenizer tokens = new StringTokenizer(linha,";");
				if(tokens.countTokens() != 2)
					throw new SCAPersistenciaException("Erro no formato do arquivo!\n Arquivo: "+cursoFile.toString()+"\n Linha: "+nlinha);
				Curso curso = new Curso(linha, nlinha);
				curso.setCodigo(Integer.parseInt(tokens.nextToken()));
				curso.setNome(tokens.nextToken());
				cursos.add(curso);
			}
			reader.close();
		} catch (Exception e) {
			throw new SCAPersistenciaException("Erro ao ler o arquivo "+cursoFile.toString()+", na linha:"+nlinha,e);
		}
		return cursos;
	}

}
