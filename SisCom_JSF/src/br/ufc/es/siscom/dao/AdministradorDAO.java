package br.ufc.es.siscom.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Administrador;

public class AdministradorDAO {

	
	public static void adicionarAdministrador(Administrador admin){

		Session session = CriarTabelas.preparaSessao();
		session.save(admin);
		session.beginTransaction().commit();
		session.close();
		}
	
	
	public static Administrador retornaAdministradorPorLogin(String login){
		Session session = CriarTabelas.preparaSessao();
		return (Administrador) session.createCriteria(Administrador.class).add(Restrictions.eq("login", login)).uniqueResult();
	
	}
}
