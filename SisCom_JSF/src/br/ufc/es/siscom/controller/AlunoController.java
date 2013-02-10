package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;

@ManagedBean(name = "alunoController")
@SessionScoped
public class AlunoController {
	private Aluno aluno = new Aluno();
	private ArrayList<String> nomeDisciplinasSelecionadas;
	private ArrayList<Aluno> alunos;
	private ArrayList<Aluno> todosAlunos;
	private Map<String, String> todasDisciplinas;
	private Integer disciplina;
	private String nomeAluno;
	
	
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String deletarAluno(){
		AlunoDAO.deletarAluno(aluno);
		return "listarAlunos.xhtml";
	}
	
	public String salvar(){
		List<Disciplina> disciplinasAluno = DisciplinaDAO.retornarListaDeDisciplinaPorListaDeNomes(nomeDisciplinasSelecionadas);
		aluno.setDisciplinas(disciplinasAluno);
		AlunoDAO.adicionarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}
	
	public String atualizarAluno(){
		List<Disciplina> disciplinasAluno = DisciplinaDAO.retornarListaDeDisciplinaPorListaDeNomes(nomeDisciplinasSelecionadas);
		aluno.setDisciplinas(disciplinasAluno);
		AlunoDAO.atualizarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}
		
	public String retornarAlunos(){
			this.alunos = AlunoDAO.retornarAlunos();
		return "orientarAlunos.xhtml";
	}
	
	public String buscarAlunos(){
		ArrayList<Aluno> alunos = AlunoDAO.retornaAlunosPorNome(nomeAluno);
		setAlunos(alunos);
		return "orientarAlunos.xhtml";
	}

	public ArrayList<Aluno> getAlunos() {
		
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Integer getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}

	public ArrayList<Aluno> getTodosAlunos() {
		this.todosAlunos = AlunoDAO.retornarAlunos();
		return todosAlunos;
	}

	public void setTodosAlunos(ArrayList<Aluno> todosAlunos) {
		this.todosAlunos = todosAlunos;
	}


	public Map<String,String> getTodasDisciplinas() {
		List<Disciplina> disciplinass = DisciplinaDAO.retornarDisciplinas();
		Map<String,String> dis = new HashMap<String, String>();
		for (Disciplina disciplina : disciplinass) {
			
			dis.put(disciplina.getNome(),disciplina.getNome());
			
		}
		this.todasDisciplinas = dis;
		return todasDisciplinas;
	}

	public void setTodasDisciplinas(Map<String,String> todasDisciplinas) {
		this.todasDisciplinas = todasDisciplinas;
	}

	public ArrayList<String> getNomeDisciplinasSelecionadas() {
		return nomeDisciplinasSelecionadas;
	}

	public void setNomeDisciplinasSelecionadas(
			ArrayList<String> nomeDisciplinasSelecionadas) {
		this.nomeDisciplinasSelecionadas = nomeDisciplinasSelecionadas;
	}


	
	
}
