package br.ufc.es.siscom.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.HorarioDAO;
import br.ufc.es.siscom.model.Horario;


@ManagedBean(name= "horarioController")
@RequestScoped
public class HorarioController {
	
	private Horario horario = new Horario();
	
	public String salvar(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController =  (LoginController) session.getAttribute("loginController");
		horario.gerarCodigoHorario();
		horario.setMonitor(loginController.getMonitor());
		HorarioDAO.adicionarHorario(horario);
		horario = new Horario();
		return "monitorInicial.xhtml";
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

}
