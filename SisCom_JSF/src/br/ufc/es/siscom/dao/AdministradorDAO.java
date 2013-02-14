package br.ufc.es.siscom.dao;



import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Administrador;
import br.ufc.es.siscom.util.PreparaSessao;

public class AdministradorDAO {
		private static Session session;
	
	public static void adicionarAdministrador(Administrador admin){
			
		session = (Session) PreparaSessao.pegarSessao();
		session.save(admin);
		session.beginTransaction().commit();
		session.close();
		}
	
	
	public static Administrador retornaAdministradorPorLogin(String login){
		session = (Session) PreparaSessao.pegarSessao();
		Administrador adiministrador = (Administrador) session.createCriteria(Administrador.class).add(Restrictions.eq("login", login)).uniqueResult();
		session.close();
		return adiministrador;
	}
}
