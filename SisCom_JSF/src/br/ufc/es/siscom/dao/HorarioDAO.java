package br.ufc.es.siscom.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.Transaction;

import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.util.CriarTabelas;
import br.ufc.es.siscom.util.PreparaSessao;



public class HorarioDAO {
	
	private static Session session;

	public static void adicionarHorario(Horario horario){

		session = (Session) PreparaSessao.pegarSessao();
		session.save(horario);
		session.beginTransaction().commit();
		session.close();
		}
	public static Horario buscarHorarioPorCodigo(String codigo) {
		session = (Session) PreparaSessao.pegarSessao();
		String hql = "from Horario horario where horario.codigoHorario like ?";
		
		Query query = (Query) session.createQuery(hql).setString(0, codigo);
		return (Horario) query.uniqueResult();
	}
	
	public static void adicionarMonitorAoHorario(long idHorario, Monitor monitor){
		session = (Session) PreparaSessao.pegarSessao();
		session.beginTransaction();

		Horario horario = new Horario();
		session.load(horario, idHorario);
		horario.setMonitor(monitor);
		session.save(horario);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void deletarHorario(Horario horario){
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Horario horarioDB = (Horario) session.load(Horario.class, horario.getId());
		session.delete(horarioDB);
		transaction.commit();
		session.close();
	}
	public static List<Horario> retornarTodosOsHorarios() {
		session = (Session) PreparaSessao.pegarSessao();
		List<Horario> horarios = session.createCriteria(Horario.class).list();
		session.close();
		return horarios;
		
	}

}
