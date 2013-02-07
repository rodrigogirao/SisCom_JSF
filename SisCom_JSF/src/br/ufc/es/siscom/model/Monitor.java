package br.ufc.es.siscom.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Monitor {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String login;
	@Column
	private String senha;
	@Column
	private String matricula;
	@Column
	private String nome;
	
	@ManyToOne
	private Orientador orientador;
	
//	@OneToMany(mappedBy="monitor")
//	private List<Horario> horariosMonitor = new ArrayList<Horario>();
	
	@ManyToMany
	@JoinTable(name = "Monitor_Disciplina", 
			joinColumns = { @JoinColumn(name = "id_monitor") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_codigo") })
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

//	public List<Horario> getHorariosMonitor() {
//		return horariosMonitor;
//	}
//
//	public void setHorariosMonitor(List<Horario> horariosMonitor) {
//		this.horariosMonitor = horariosMonitor;
//	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
}
