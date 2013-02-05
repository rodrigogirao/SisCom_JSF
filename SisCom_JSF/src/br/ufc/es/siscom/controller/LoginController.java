package br.ufc.es.siscom.controller;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufc.es.siscom.dao.AdministradorDAO;
import br.ufc.es.siscom.model.Administrador;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {
	
	private String tipoLogin;
	private String login;
	private String senha;
	
	
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
	
	
	public String validarLogin(){
		Administrador adm = new Administrador();
		adm = AdministradorDAO.retornaAdministradorPorLogin(login);
		if(adm==null){
			return "index.xhtml";
		}
		if(tipoLogin.equals("administrador") && (login.equals(adm.getLogin())) && (senha.equals(adm.getSenha())))
			return "menuAdministrador.xhtml";
			else return "index.xhtml";
		
	}

}
