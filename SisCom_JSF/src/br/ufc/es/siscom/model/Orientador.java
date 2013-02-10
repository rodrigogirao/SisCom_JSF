package br.ufc.es.siscom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Orientador implements Serializable  {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String siape;
	@Column
	private String nome;
	@Column
	private String senha;
	@Column(unique=true)
	private String login;
	@Column
	private Date dataNascimento;
	
	@OneToMany(mappedBy="orientador")
	private List<Monitor> monitoresOrientados = new ArrayList<Monitor>();
	

public Orientador(){
		
	}

public Date getDataNascimento() {
	return dataNascimento;
}
public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getSiape() {
	return siape;
}

public void setSiape(String siape) {
	this.siape = siape;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public List<Monitor> getMonitoresOrientados() {
	return monitoresOrientados;
}

public void setMonitoresOrientados(List<Monitor> monitoresHorientados) {
	this.monitoresOrientados = monitoresHorientados;
}


		
	}

