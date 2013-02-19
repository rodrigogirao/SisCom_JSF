package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.dao.OrientadorDAO;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;


@ManagedBean(name= "orientadorController")
@SessionScoped

public class OrientadorController {
	
	private Orientador orientador = new Orientador();
	private List<Orientador> orientadores = new ArrayList<Orientador>();
	private ArrayList<Monitor> monitores;
	private Aluno aluno;
	

	
	public ArrayList<Monitor> getMonitores() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController =  (LoginController) session.getAttribute("loginController");
		monitores = (ArrayList<Monitor>) MonitorDAO.retornaMonitoresDoOrientador(loginController.getOrientador());
		return monitores;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public void setMonitores(ArrayList<Monitor> monitores) {
		this.monitores = monitores;
	}
	public String monitorarAluno(){
		ExternalContext externalContext2 = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext2.getSession(true);  
		LoginController loginController =  (LoginController) session.getAttribute("loginController");
		Monitor monitor = new Monitor();
		monitor.setNome(aluno.getNome());
		monitor.setMatricula(aluno.getMatricula());
		monitor.setLogin(aluno.getLogin());
		monitor.setSenha(aluno.getSenha());
		monitor.setOrientador(loginController.getOrientador());
		
		MonitorDAO.adicionarMonitor(monitor);
		
		return "orientadorInicial.xhtml";
	}
	public Orientador getOrientador() {
		return orientador;
	}
	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	public List<Orientador> getOrientadores() {
		orientadores = OrientadorDAO.retornarTodosOsOrientadores(); 
		return orientadores;
	}
	public void setOrientadores(List<Orientador> orientadores) {
		this.orientadores = orientadores;
	}
	
	public String cadastrarOrientador()
	{
		OrientadorDAO.adicionarOrientador(orientador);
		return "listarOrientadores.xhtml";
		
	}
	
	public String deletarOrientador()
	{
		List<Monitor> monitores = MonitorDAO.retornaMonitoresDoOrientador(orientador);
		if(monitores==null||monitores.isEmpty()){
			OrientadorDAO.deletarOrientador(orientador);
			return "listarOrientadores.xhtml";
		}
		else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel deletar Orientador pois ele possui monitores","possui monitores");
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	return "";
		}
	}
	
	public String atualizarOrientador()
	{
		OrientadorDAO.atualizarOrientador(orientador);
		return "listarOrientadores.xhtml";
	}
	public List<Orientador> listarOrientadores()
	{
		return orientadores;
	}
	

}
