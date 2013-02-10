package br.ufc.es.siscom.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

public class MonitorDAO {
	
	public static void adicionarMonitor(Monitor monitor){

		Session session = CriarTabelas.preparaSessao();
		session.save(monitor);
		session.beginTransaction().commit();
		session.close();
		}
	

	public static void associarOrientadorMonitor(long idAluno, Orientador orientador){

		Session sessao = CriarTabelas.preparaSessao();
		sessao.beginTransaction();

		Monitor monitor = new Monitor();
		sessao.load(monitor, idAluno);
		monitor.setOrientador(orientador);

		sessao.save(monitor);
		sessao.getTransaction().commit();
		sessao.close();

	}
	
	public static void associarMonitorDisciplina(long idMonitor, Disciplina disciplina){

		Session sessao = CriarTabelas.preparaSessao();
		sessao.beginTransaction();

		Monitor monitor = new Monitor();
		sessao.load(monitor, idMonitor);
		monitor.getDisciplinas().add(disciplina);

		sessao.save(monitor);
		sessao.getTransaction().commit();
		sessao.close();

	}
	
	public static void adicionarHorarioAoMonitor(long idMonitor,Horario horario){
		Session sessao = CriarTabelas.preparaSessao();
		sessao.beginTransaction();

		Monitor monitor = new Monitor();
		sessao.load(monitor, idMonitor);
		monitor.getHorariosMonitor().add(horario);
		sessao.save(monitor);
		sessao.getTransaction().commit();
	}
	
	public static List<Monitor> retornaMonitoresDoOrientador(Orientador orientador){
		Session session = CriarTabelas.preparaSessao();
		List<Monitor> monitores =  session.createCriteria(Monitor.class).add(Restrictions.eq("orientador", orientador)).list();
		
		session.close();
		return monitores;
	}
	
	
	
	public static List<Horario> retornaHorariosPorMatriculaDoMonitor(String matricula){
		Monitor monitor = MonitorDAO.retornaMonitorPorMatricula(matricula);
		Session session = CriarTabelas.preparaSessao();
		List<Horario> horarios =  session.createCriteria(Horario.class).add(Restrictions.eq("monitor", monitor)).list();
		session.close();
		return horarios;
		
				
	}
	
	public static Monitor retornaMonitorPorMatricula(String matricula){
		Session session = CriarTabelas.preparaSessao();
		Monitor monitor = (Monitor) session.createCriteria(Monitor.class).add(Restrictions.eq("matricula", matricula)).uniqueResult();
		session.close();
		return monitor;
	}
	
	public static Monitor retornaMonitorPorLogin(String login){
		Session session = CriarTabelas.preparaSessao();
		Monitor monitor = (Monitor) session.createCriteria(Monitor.class).add(Restrictions.eq("login", login)).uniqueResult();
		session.close();
		return monitor;
	}
}

