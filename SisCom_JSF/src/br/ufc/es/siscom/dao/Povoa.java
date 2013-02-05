package br.ufc.es.siscom.dao;

import br.ufc.es.siscom.model.Administrador;

public class Povoa {
public static void main(String[] args) {
		
		CriarTabelas.reiniciaEsquemaBD();
		
		 Administrador admin = new Administrador();
		 admin.setLogin("admin");
		 admin.setSenha("admin");
	   	 AdministradorDAO.adicionarAdministrador(admin);
	
	}

}
