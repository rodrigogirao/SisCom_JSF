package br.ufc.es.siscom.dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufc.es.siscom.model.Disciplina;

public class DisciplinaDAO {
	
	public static void adicionarDisciplina(Disciplina disciplina){

		Session session = CriarTabelas.preparaSessao();
		session.save(disciplina);
		session.beginTransaction().commit();
		session.close();
		
		
		}
	
	public static List<Disciplina> retornarDisciplinas() {
		Session session = CriarTabelas.preparaSessao();
		ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) session.createCriteria(Disciplina.class).list();
		return disciplinas;

    }
	
	public static void atualizarDisciplina(Disciplina novaDisciplina){
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Disciplina disciplinaBd = (Disciplina)session.load(Disciplina.class, novaDisciplina.getId());
		disciplinaBd = novaDisciplina;
		session.update(disciplinaBd);
		transaction.commit();
		session.close();
		
	}
	
	
	public static Disciplina buscarDisciplinaPorCodigo(String codigo) {
		Session session = CriarTabelas.preparaSessao();
		String hql = "from Disciplina disciplina where disciplina.codigo like ?";
		
		Query query = (Query) session.createQuery(hql).setString(0, codigo);
		return (Disciplina) query.uniqueResult();
	}
	public static void deletarDisciplina(Disciplina disciplina)
	{
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Disciplina disciplinaBd = (Disciplina)session.load(Disciplina.class, disciplina.getId());
		session.delete(disciplinaBd);
		transaction.commit();
		session.close();
	
//	public static void associarDisciplinaHorario(long idHorario, Disciplina disciplina){
//
//		Session sessao = CriarTabelas.preparaSessao();
//		sessao.beginTransaction();
//
//		Horario horario = new Horario();
//		sessao.load(horario, idHorario);
//		horario.setDisciplina(disciplina);
//
//		sessao.save(horario);
//		sessao.getTransaction().commit();

	//}
	
       }
	}