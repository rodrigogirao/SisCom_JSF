package br.ufc.es.siscom.controller;

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
	
	public String logout(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "index.xhtml";
	}
	
	public String validarLogin(){
		if(tipoLogin.equals("administrador")){
			Administrador adm = AdministradorDAO.retornaAdministradorPorLogin(login);
			if(adm!=null && adm.getSenha().equals(senha)){
				return "/telasAdministrador/opcoesAdministrador.xhtml";	
			}
		}
		
		if(tipoLogin.equals("aluno")){
			Aluno aluno = AlunoDAO.retornaAlunoPorLogin(login);
			if(aluno!=null && aluno.getSenha().equals(senha)){
				return "/telasAluno/alunoInicial.xhtml";
			}
		}
		
		if(tipoLogin.equals("orientador")){
			 this.orientador = OrientadorDAO.retornaOrientadorPorLogin(login);
			if(orientador != null && orientador.getSenha().equals(senha)){
				return "/telasOrientador/orientadorInicial.xhtml";
			}
		}
		
		if(tipoLogin.equals("monitor")){
			this.monitor  = MonitorDAO.retornaMonitorPorLogin(login);
			if(monitor!=null && monitor.getSenha().equals(senha)){
				return "/telasMonitor/monitorInicial.xhtml";
			}
		}
		return "index.xhtml";
	}

}
