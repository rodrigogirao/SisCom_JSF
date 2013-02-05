package br.ufc.es.siscom.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufc.es.siscom.model.Orientador;


public class OrientadorDAO {
	public static void adicionarOrientador(Orientador orientador){

		Session session = CriarTabelas.preparaSessao();
		session.save(orientador);
		session.beginTransaction().commit();
		session.close();	
	}
	
	public static List<Orientador> retornarTodosOsOrientadores() {
		Session session = CriarTabelas.preparaSessao();
		List<Orientador> orientadores = session.createCriteria(Orientador.class).list();
		session.close();
		return orientadores;
	}
	
	public static void deletarOrientador(Orientador orientador)
	{
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Orientador orientadorBd = (Orientador)session.load(Orientador.class, orientador.getId());
		session.delete(orientadorBd);
		transaction.commit();
		session.close();
		
	}
	
	public static void atualizarOrientador(Orientador novoOrientador)
	{
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Orientador orientadorBd = (Orientador)session.load(Orientador.class, novoOrientador.getId());
		orientadorBd = novoOrientador;
		session.update(orientadorBd);
		transaction.commit();
		session.close();
		
		
	}
}
