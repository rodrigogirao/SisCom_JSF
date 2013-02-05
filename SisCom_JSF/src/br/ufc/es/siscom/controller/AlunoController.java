package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;

@ManagedBean(name = "alunoController")
@SessionScoped
public class AlunoController {
	private Aluno aluno = new Aluno();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private ArrayList<Aluno> alunos;
	private Integer disciplina;
	
	
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
		AlunoDAO.adicionarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}
	
	public String atualizarAluno(){
		AlunoDAO.atualizarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}

	public List<SelectItem> getAllDisciplinas() {
		List<Disciplina> disciplinass = DisciplinaDAO.retornarDisciplinas();
		List<SelectItem> lista = new ArrayList<SelectItem>();
		int i=0;
		for (Disciplina disciplina : disciplinass) {
			
			lista.add(new SelectItem(new Integer(i),disciplina.getNome()));
			i++;
		}
		return lista;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ArrayList<Aluno> getAlunos() {
		this.alunos = AlunoDAO.retornarAlunos();
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
	
	
}
