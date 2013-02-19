package br.ufc.es.siscom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.dao.HorarioDAO;
import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.dao.OrientadorDAO;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

@ManagedBean(name = "alunoController")
@SessionScoped
public class AlunoController {
	private Aluno aluno = new Aluno();
	private ArrayList<String> nomeDisciplinasSelecionadas;
	private ArrayList<Aluno> alunosNaoMonitores;
	private ArrayList<Aluno> todosAlunos;
	private Map<String, String> todasDisciplinas;
	private Integer disciplina;
	private String nomeAluno;
	private List<Horario> horarios;
	private Horario horario;
	private List<Horario> horariosSessao;
	private List<Horario> horariosParaConfirmar;
	
	public List<Horario> getHorariosSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Aluno aluno =  (Aluno) session.getAttribute("aluno");
		horariosSessao = aluno.getHorarios();
		return horariosSessao;
	}
	
	
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String deletarAluno(){
		AlunoDAO.deletarAluno(aluno);
		return "listarAlunos.xhtml";
	}
	
	public String salvar(){
		List<Disciplina> disciplinasAluno = DisciplinaDAO.retornarListaDeDisciplinaPorListaDeNomes(nomeDisciplinasSelecionadas);
		aluno.setDisciplinas(disciplinasAluno);
		AlunoDAO.adicionarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}
	
	public String atualizarAluno(){
		List<Disciplina> disciplinasAluno = DisciplinaDAO.retornarListaDeDisciplinaPorListaDeNomes(nomeDisciplinasSelecionadas);
		aluno.setDisciplinas(disciplinasAluno);
		AlunoDAO.atualizarAluno(aluno);
		aluno = new Aluno();
		return "listarAlunos.xhtml";
	}
	
	public String confirmarHorario(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Aluno aluno =  (Aluno) session.getAttribute("aluno");
		aluno.getHorarios().add(horario);
		AlunoDAO.atualizarAluno(aluno);
		return "alunoInicial.xhtml";
	}
	
	public String deletarHorario(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Aluno aluno =  (Aluno) session.getAttribute("aluno");
		aluno.getHorarios().remove(horario);
		AlunoDAO.atualizarAluno(aluno);
		return "alunoInicial.xhtml";
	}
		
	public String retornarAlunosNaoMonitores(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true); 
		Orientador orientador = (Orientador) session.getAttribute("orientador");
			List<Aluno> alunosNaoOrientados = AlunoDAO.retornarTodosAlunosNaoMonitores(orientador);
				
			
			
			this.alunosNaoMonitores = (ArrayList<Aluno>) alunosNaoOrientados;
			
		return "orientarAlunos.xhtml";
	}
	
	public String buscarAlunos(){
		ArrayList<Aluno> alunos = AlunoDAO.retornaAlunosPorNome(nomeAluno);
		setAlunos(alunos);
		return "orientarAlunos.xhtml";
	}

	public ArrayList<Aluno> getAlunos() {
		
		return alunosNaoMonitores;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunosNaoMonitores = alunos;
	}

	public Integer getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}

	public ArrayList<Aluno> getTodosAlunos() {
		this.todosAlunos = AlunoDAO.retornarAlunos();
		return todosAlunos;
	}

	public void setTodosAlunos(ArrayList<Aluno> todosAlunos) {
		this.todosAlunos = todosAlunos;
	}


	public Map<String,String> getTodasDisciplinas() {
		List<Disciplina> disciplinass = DisciplinaDAO.retornarDisciplinas();
		Map<String,String> dis = new HashMap<String, String>();
		for (Disciplina disciplina : disciplinass) {
			
			dis.put(disciplina.getNome(),disciplina.getNome());
			
		}
		this.todasDisciplinas = dis;
		return todasDisciplinas;
	}

	public void setTodasDisciplinas(Map<String,String> todasDisciplinas) {
		this.todasDisciplinas = todasDisciplinas;
	}

	public ArrayList<String> getNomeDisciplinasSelecionadas() {
		return nomeDisciplinasSelecionadas;
	}

	public void setNomeDisciplinasSelecionadas(
			ArrayList<String> nomeDisciplinasSelecionadas) {
		this.nomeDisciplinasSelecionadas = nomeDisciplinasSelecionadas;
	}


	public List<Horario> getHorarios() {
		return horarios;
	}


	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}


	public Horario getHorario() {
		return horario;
	}


	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public List<Horario> getHorariosParaConfirmar() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		Aluno aluno =  (Aluno) session.getAttribute("aluno");
		List<Disciplina> disciplinasAluno = aluno.getDisciplinas();
		List<Horario> horarios = new ArrayList<Horario>();
		List<Horario> horariosConfirmar = new ArrayList<Horario>();
		for (Disciplina disciplina : disciplinasAluno) {	
			for (Horario horario : disciplina.getHorariosDisciplina()) {
				horarios.add(horario);
			}
		}
		
HashMap<String, Horario> todosHorarios = new HashMap<String, Horario>();
		
		for (Horario horario : horarios) {
			todosHorarios.put(horario.getCodigoHorario(), horario);
		}
		
				
		for (Horario horario : aluno.getHorarios()) {
			todosHorarios.remove(horario.getCodigoHorario());
		}
		
		for (Horario horario : todosHorarios.values()) {
			horariosConfirmar.add(horario);
		}
		setHorariosParaConfirmar(horariosConfirmar);
		return horariosParaConfirmar;
	}


	public void setHorariosParaConfirmar(List<Horario> horariosParaConfirmar) {
		this.horariosParaConfirmar = horariosParaConfirmar;
	}
	public ArrayList<Aluno> getAlunosNaoMonitores() {
		return alunosNaoMonitores;
	}


	public void setAlunosNaoMonitores(ArrayList<Aluno> alunosNaoMonitores) {
		this.alunosNaoMonitores = alunosNaoMonitores;
	}

	
	
}
