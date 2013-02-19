package br.ufc.es.siscom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Aluno implements Serializable{
	
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String login;
	@Column
	private String senha;
	@Column
	private String matricula;
	@Column
	private String nome;
	@Column
	private Date dataNascimento;
	
	@ManyToMany
	@JoinTable(name = "Horario_Aluno", 
			joinColumns = { @JoinColumn(name = "idHorario") }, 
			inverseJoinColumns = { @JoinColumn(name = "idAluno") })
	private List<Horario> horarios = new ArrayList<Horario>();
	
	@ManyToMany
	@JoinTable(name = "Aluno_Disciplina", 
			joinColumns = { @JoinColumn(name = "id_aluno") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_codigo") })
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	

	public Aluno() {
		// TODO Auto-generated constructor stub
	}	
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}


}
