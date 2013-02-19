package br.ufc.es.siscom.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.dialog.Dialog;

import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;


@ManagedBean(name = "monitorController")
@SessionScoped
public class MonitorController {
	private Monitor monitor = new Monitor();
	private List<Horario> horarios;
	private List<Horario> horariosSessao;
	private ArrayList<String> nomeDisciplinasSelecionadas;
	private Map<String, String> disciplinasMonitor;	
	
	public String deletarMonitor(){
		if(monitor.getHorariosMonitor()==null||monitor.getHorariosMonitor().isEmpty()){
			MonitorDAO.deletarMonitor(monitor);
			return "orientadorInicial.xhtml";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel deletar Monitor pois ele possui horarios", "Monitor possui horarios");
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	return "";
		}
		
		
	}
	
	public String verHorarios(){
		setHorarios(MonitorDAO.retornaHorariosPorMatriculaDoMonitor(monitor.getMatricula()));
		return "horariosMonitor.xhtml";
	}
	
	public String adicionarDisciplinas(){
		List<Disciplina> disciplinasMonitor = DisciplinaDAO.retornarListaDeDisciplinaPorListaDeNomes(nomeDisciplinasSelecionadas);
		monitor.setDisciplinas(disciplinasMonitor);
		MonitorDAO.atualizarMonitor(monitor);
		return "orientadorInicial.xhtml";
	}
	
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	public List<Horario> getHorariosSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Monitor monitor =  (Monitor) session.getAttribute("monitor");
		horariosSessao = MonitorDAO.retornaHorariosPorMatriculaDoMonitor(monitor.getMatricula());
		return horariosSessao;
	}
	public void setHorariosSessao(List<Horario> horarios) {
		this.setHorarios(horarios);
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	public Map<String,String> getDisciplinasMonitor() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Monitor monitor =  (Monitor) session.getAttribute("monitor");
		
		List<Disciplina> disciplinass =monitor.getDisciplinas();
		Map<String,String> dis = new HashMap<String, String>();
		for (Disciplina disciplina : disciplinass) {
			
			dis.put(disciplina.getNome(),disciplina.getNome());
			
		}
		this.disciplinasMonitor = dis;
		return disciplinasMonitor;
	}

	public ArrayList<String> getNomeDisciplinasSelecionadas() {
		return nomeDisciplinasSelecionadas;
	}

	public void setNomeDisciplinasSelecionadas(
			ArrayList<String> nomeDisciplinasSelecionadas) {
		this.nomeDisciplinasSelecionadas = nomeDisciplinasSelecionadas;
	}

}
