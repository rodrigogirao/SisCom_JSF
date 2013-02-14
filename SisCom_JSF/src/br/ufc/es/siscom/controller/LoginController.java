package br.ufc.es.siscom.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.dao.AdministradorDAO;
import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.dao.OrientadorDAO;
import br.ufc.es.siscom.model.Administrador;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {
	
	private String tipoLogin;
	private String login;
	private String senha;
	private Orientador orientador;
	private Monitor monitor;
	private Aluno aluno;
	private Administrador admin;
	private FacesMessage msg = null;
	
	public String logout(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "/index.xhtml";
	}
	
	public String validarLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		
		if(tipoLogin.equals("administrador")){
			this.admin = AdministradorDAO.retornaAdministradorPorLogin(login);
			
			if(admin!=null && admin.getSenha().equals(senha)){
				session.setAttribute("admin", this.admin);
				return "/telasAdministrador/opcoesAdministrador.xhtml";	
			}else{
		    	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Invalidos", "Crendenciais Invalidas");
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	
			}
		}
		
		if(tipoLogin.equals("aluno")){
			this.aluno = AlunoDAO.retornaAlunoPorLogin(login);
			
			if(aluno!=null && aluno.getSenha().equals(senha)){
				session.setAttribute("aluno", this.aluno);
				return "/telasAluno/alunoInicial.xhtml";
			}else{
		    	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Invalidos", "Crendenciais Invalidas");
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	
			}
		}
		
		if(tipoLogin.equals("orientador")){
			 this.orientador = OrientadorDAO.retornaOrientadorPorLogin(login);
			if(orientador != null && orientador.getSenha().equals(senha)){
				session.setAttribute("orientador", this.orientador);
				return "/telasOrientador/orientadorInicial.xhtml";
			}else{
		    	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Invalidos", "Crendenciais Invalidas");
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	
			}
		}
		
		if(tipoLogin.equals("monitor")){
			this.monitor  = MonitorDAO.retornaMonitorPorLogin(login);
			if(monitor!=null && monitor.getSenha().equals(senha)){
				session.setAttribute("monitor", this.monitor);
				return "/telasMonitor/monitorInicial.xhtml";
			}else{
		    	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Invalidos", "Crendenciais Invalidas");
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	
			}
		}
		return "";
	}
	
		
	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Orientador getOrientador() {
		return orientador;
	}
	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	public String getTipoLogin() {
		return tipoLogin;
	}
	public void setTipoLogin(String tipoLogin) {
		this.tipoLogin = tipoLogin;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

}
