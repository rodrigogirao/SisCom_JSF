package br.ufc.es.siscom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Horario implements Serializable{

	
	@Id
	@GeneratedValue
	private long id;
	@ManyToMany
	@JoinTable(name = "Horario_Aluno", 
			joinColumns = { @JoinColumn(name = "idAluno") }, 
			inverseJoinColumns = { @JoinColumn(name = "idHorario") })
	private List<Aluno> alunos = new ArrayList<Aluno>();
	@Column
	private String horaInicio;
	@Column
	private String horaFim;
	@Column
	private Date data;
	@Column
	private String codigoHorario;
	@ManyToOne
	private Monitor monitor;
	
	@ManyToOne
	private Disciplina disciplina;

	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void gerarCodigoHorario(){
		this.setCodigoHorario(this.getData() + "_"+this.getHoraInicio() +  "-"+this.getHoraFim()); 
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getCodigoHorario() {
		return codigoHorario;
	}

	public void setCodigoHorario(String codigoHorario) {
		this.codigoHorario = codigoHorario;
	}
	
}

