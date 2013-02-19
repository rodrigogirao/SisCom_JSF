package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.dao.HorarioDAO;
import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.model.Aluno;
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
		List<Aluno> todosAlunos = AlunoDAO.retornarAlunos();
		List<Horario> horariosConfirmados = new ArrayList<Horario>();
		for (Aluno aluno : todosAlunos) {
			if(aluno.getHorarios().contains(horario)){
				horariosConfirmados.add(horario);
			}
		}
		if(horariosConfirmados==null||horariosConfirmados.isEmpty()){
			HorarioDAO.deletarHorario(horario);
			return "monitorInicial.xhtml";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel deletar Horario pois ele possui alunos confirmados", "Monitor possui horarios");
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	return "";
		}
		
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
