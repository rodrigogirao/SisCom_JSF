package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.model.Disciplina;

@ManagedBean(name= "disciplinaController")
@SessionScoped

public class DisciplinaController {

private Disciplina disciplina = new Disciplina();
private List<Disciplina> disciplinas =  new ArrayList<Disciplina>();
private List<Disciplina> disciplinasComHorario;

	public List<Disciplina> getDisciplinas() {
	disciplinas = DisciplinaDAO.retornarDisciplinas(); 
		return disciplinas;
}
	public void setDisciplinas(List<Disciplina> disciplinas) {
	this.disciplinas = disciplinas;
}
	public String cadastrarDisciplina() {
		DisciplinaDAO.adicionarDisciplina(disciplina);
		return "listarDisciplinas.xhtml";
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public String atualizarDisciplina(){
		DisciplinaDAO.atualizarDisciplina(this.disciplina);
		return "listarDisciplinas.xhtml";
	}
	public String deletarDisciplina()
	{
		DisciplinaDAO.deletarDisciplina(disciplina);
		return "listarDisciplinas.xhtml";
}
	
	public void setDisciplinasComHorario(List<Disciplina> disciplinasComHorario) {
		this.disciplinasComHorario = disciplinasComHorario;
	}
}
