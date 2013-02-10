package br.ufc.es.siscom.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;



public class HorarioDAO {

	public static void adicionarHorario(Horario horario){

		Session session = CriarTabelas.preparaSessao();
		session.save(horario);
		session.beginTransaction().commit();
		session.close();
		}
	public static Horario buscarHorarioPorCodigo(String codigo) {
		Session session = CriarTabelas.preparaSessao();
		String hql = "from Horario horario where horario.codigoHorario like ?";
		
		Query query = (Query) session.createQuery(hql).setString(0, codigo);
		return (Horario) query.uniqueResult();
	}
	
	public static void adicionarMonitorAoHorario(long idHorario, Monitor monitor){
		Session sessao = CriarTabelas.preparaSessao();
		sessao.beginTransaction();

		Horario horario = new Horario();
		sessao.load(horario, idHorario);
		horario.setMonitor(monitor);
		sessao.save(horario);
		sessao.getTransaction().commit();
	}
//	public static void associarAlunoAoHorario(long id, Aluno aluno) {
//		Session sessao = CriarTabelas.preparaSessao();
//		sessao.beginTransaction();
//
//		Horario horario = new Horario();
//		sessao.load(horario, id);
//		
//		horario.getAlunos().add(aluno);
//		sessao.save(horario);
//		sessao.getTransaction().commit();
//		
//		
//	}
}
