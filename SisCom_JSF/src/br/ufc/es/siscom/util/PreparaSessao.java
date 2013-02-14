package br.ufc.es.siscom.util;

import org.hibernate.classic.Session;

public class PreparaSessao {

	private static Session session = CriarTabelas.preparaSessao();

	public static Session pegarSessao(){
		if ( session == null  ) {
			session = CriarTabelas.preparaSessao(); 
		} else if ( !session.isOpen() ) {
			session = session.getSessionFactory().openSession();
		}
		return session;
	}


}
