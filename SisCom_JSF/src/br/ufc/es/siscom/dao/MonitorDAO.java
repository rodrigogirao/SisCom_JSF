package br.ufc.es.siscom.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.classic.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;
import br.ufc.es.siscom.util.CriarTabelas;
import br.ufc.es.siscom.util.PreparaSessao;

public class MonitorDAO {
	

	private static Session session;
		
	public static void adicionarMonitor(Monitor monitor){

		session = (Session) PreparaSessao.pegarSessao();
		session.save(monitor);
		session.beginTransaction().commit();
		session.close();
	}
	
	public static void atualizarMonitor(Monitor novoMonitor){
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Monitor monitorDB = (Monitor) session.load(Monitor.class, novoMonitor.getId());
		monitorDB = novoMonitor;
		session.update(monitorDB);
		transaction.commit();
		session.close();
	}
	
	public static void deletarMonitor(Monitor monitor) throws ConstraintViolationException{
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		
		Monitor monitorDB = (Monitor) session.load(Monitor.class, monitor.getId());
		try{
			session.delete(monitorDB);
			transaction.commit();
			session.close();
	} catch (ConstraintViolationException e) {
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Não Foi Possivel Deletar - Monitor Possui Horarios", "Monitor Possui Horarios");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	}
	

	public static void associarOrientadorMonitor(long idAluno, Orientador orientador){

		session = (Session) PreparaSessao.pegarSessao();
		session.beginTransaction();

		Monitor monitor = new Monitor();
		session.load(monitor, idAluno);
		monitor.setOrientador(orientador);

		session.save(monitor);
		session.getTransaction().commit();
		session.close();

	}
	
	public static void associarMonitorDisciplina(long idMonitor, Disciplina disciplina){

		session = (Session) PreparaSessao.pegarSessao();
		session.beginTransaction();

		Monitor monitor = new Monitor();
		session.load(monitor, idMonitor);
		monitor.getDisciplinas().add(disciplina);

		session.save(monitor);
		session.getTransaction().commit();
		session.close();

	}
	//Método duplicado na classe HorarioDAO ver qual é o verdadeiro
	public static void adicionarHorarioAoMonitor(long idMonitor,Horario horario){
		session = (Session) PreparaSessao.pegarSessao();
		session.beginTransaction();

		Monitor monitor = new Monitor();
		session.load(monitor, idMonitor);
		monitor.getHorariosMonitor().add(horario);
		session.save(monitor);
		session.getTransaction().commit();
	}
	
	public static List<Monitor> retornaMonitoresDoOrientador(Orientador orientador){
		session = (Session) PreparaSessao.pegarSessao();
		
		List<Monitor> monitores =  session.createCriteria(Monitor.class).add(Restrictions.eq("orientador", orientador)).list();
		
		//session.close();
		return monitores;
	}
	
		
	public static List<Horario> retornaHorariosPorMatriculaDoMonitor(String matricula){
		Monitor monitor = MonitorDAO.retornaMonitorPorMatricula(matricula);
		session = (Session) PreparaSessao.pegarSessao();
		List<Horario> horarios =  session.createCriteria(Horario.class).add(Restrictions.eq("monitor", monitor)).list();
		//session.close();
		return horarios;
		
				
	}
	
	public static List<Disciplina> retornaDisciplinasDoMonitor(Monitor monitor){
		
		session = (Session) PreparaSessao.pegarSessao();
		List<Disciplina> disciplinas =  session.createCriteria(Disciplina.class).add(Restrictions.eq("monitor", monitor)).list();
		session.close();
		return disciplinas;
		
				
	}
	
	public static Monitor retornaMonitorPorMatricula(String matricula){
		session = (Session) PreparaSessao.pegarSessao();
		Monitor monitor = (Monitor) session.createCriteria(Monitor.class).add(Restrictions.eq("matricula", matricula)).uniqueResult();
		//session.close();
		return monitor;
	}
	
	public static Monitor retornaMonitorPorLogin(String login){
		session = (Session) PreparaSessao.pegarSessao();
		Monitor monitor = (Monitor) session.createCriteria(Monitor.class).add(Restrictions.eq("login", login)).uniqueResult();
		//session.close();
		return monitor;
	}
}

