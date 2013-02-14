package br.ufc.es.siscom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable{

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nome;

	@Column(unique=true)
	private String codigo;
	
	@OneToMany(mappedBy="disciplina")
	private List<Horario> horariosDisciplina = new ArrayList<Horario>();
	
	public Disciplina(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Horario> getHorariosDisciplina() {
		return horariosDisciplina;
	}

	public void setHorariosDisciplina(List<Horario> horariosDisciplina) {
		this.horariosDisciplina = horariosDisciplina;
	}
}
