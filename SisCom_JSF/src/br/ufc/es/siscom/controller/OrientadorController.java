package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufc.es.siscom.dao.OrientadorDAO;
import br.ufc.es.siscom.model.Orientador;


@ManagedBean(name= "orientadorController")
@SessionScoped

public class OrientadorController {
	
	private Orientador orientador = new Orientador();
	private List<Orientador> orientadores = new ArrayList<Orientador>();
	
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
		OrientadorDAO.deletarOrientador(orientador);
		return "listarOrientadores.xhtml";
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
