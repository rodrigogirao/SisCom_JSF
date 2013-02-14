package br.ufc.es.siscom.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.dao.HorarioDAO;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;


@ManagedBean(name= "horarioController")
@RequestScoped
public class HorarioController {
	
	private Horario horario = new Horario();
	private String disciplinaSelecionada;
	private List<Horario> todosHorarios;
	
	
	public String salvar(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController =  (LoginController) session.getAttribute("loginController");
		horario.gerarCodigoHorario();
		horario.setMonitor(loginController.getMonitor());
		Disciplina disciplina = DisciplinaDAO.retornarDisciplinaPorNome(disciplinaSelecionada);
		horario.setDisciplina(disciplina);
		HorarioDAO.adicionarHorario(horario);
		horario = new Horario();
		return "monitorInicial.xhtml";
	}
	
	public String deletarHorario(){
		HorarioDAO.deletarHorario(horario);
		return "monitorInicial.xhtml";
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public String getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(String disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public List<Horario> getTodosHorarios() {
		this.setTodosHorarios(HorarioDAO.retornarTodosOsHorarios());
		return todosHorarios;
	}

	public void setTodosHorarios(List<Horario> todosHorarios) {
		this.todosHorarios = todosHorarios;
	}

}
