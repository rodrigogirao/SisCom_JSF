package br.ufc.es.siscom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



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
	@Column
	private String login;
	@Column
	private Date dataNascimento;
	

	

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


		
	}

