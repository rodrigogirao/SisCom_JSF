package br.ufc.es.siscom.dao;

import java.util.List;
import org.hibernate.classic.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Orientador;
import br.ufc.es.siscom.util.PreparaSessao;


public class OrientadorDAO {
	
	private static Session session;
	
	public static void adicionarOrientador(Orientador orientador){

		session = (Session) PreparaSessao.pegarSessao();
		session.save(orientador);
		session.beginTransaction().commit();
		session.close();	
	}
	
	public static List<Orientador> retornarTodosOsOrientadores() {
		session = (Session) PreparaSessao.pegarSessao();
		List<Orientador> orientadores = session.createCriteria(Orientador.class).list();
		session.close();
		return orientadores;
	}
	
	public static Orientador retornaOrientadorPorLogin(String login){
		session = (Session) PreparaSessao.pegarSessao();
		Orientador orientador =  (Orientador) session.createCriteria(Orientador.class).add(Restrictions.eq("login", login)).uniqueResult();
		
		//session.close();
		return orientador;
	    
	}
	
	public static void deletarOrientador(Orientador orientador)
	{
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Orientador orientadorBd = (Orientador)session.load(Orientador.class, orientador.getId());
		session.delete(orientadorBd);
		transaction.commit();
		session.close();
		
	}
	
	public static void atualizarOrientador(Orientador novoOrientador)
	{
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Orientador orientadorBd = (Orientador)session.load(Orientador.class, novoOrientador.getId());
		orientadorBd = novoOrientador;
		session.update(orientadorBd);
		transaction.commit();
		session.close();
		
		
	}
}
