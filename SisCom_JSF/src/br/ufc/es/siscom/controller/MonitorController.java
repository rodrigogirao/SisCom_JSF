package br.ufc.es.siscom.controller;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;


@ManagedBean(name = "monitorController")
@SessionScoped
public class MonitorController {
	private Monitor monitor = new Monitor();
	private List<Horario> horarios;
	
	
	
	
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	public List<Horario> getHorarios() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController =  (LoginController) session.getAttribute("loginController");
		horarios = MonitorDAO.retornaHorariosPorMatriculaDoMonitor(loginController.getMonitor().getMatricula());
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

}
