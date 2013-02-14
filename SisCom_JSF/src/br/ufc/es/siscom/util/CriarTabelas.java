package br.ufc.es.siscom.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.ufc.es.siscom.model.Administrador;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

public class CriarTabelas {
	
	public static void exportarEsquema(List<Class<? extends Object>> classes){
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(classes);
		
		SchemaExport se = new SchemaExport(annotConfig);
		se.create(true, true);
	}

private static AnnotationConfiguration adicionaClassesConfiguracao(
		List<Class<? extends Object>> classes) {
	AnnotationConfiguration annotConfig = new AnnotationConfiguration(); // dom4j.jar, slf4j api e log4j
	
	for (Class classe : classes) {
		annotConfig.addAnnotatedClass(classe);
	}
	return annotConfig;
}

	public static Session preparaSessao() {
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(initialize());
		SessionFactory sf = annotConfig.buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	public static void reiniciaEsquemaBD() {
		exportarEsquema(initialize());
	}

	private static List<Class<? extends Object>> initialize() {
		List<Class<? extends Object>> classes = new ArrayList<Class<? extends Object>>();
		
		// classes.add(SuaClasseAqui.class);
		classes.add(Aluno.class);
		classes.add(Orientador.class );
		classes.add(Disciplina.class);
		classes.add(Horario.class);
		classes.add(Monitor.class);
		classes.add(Administrador.class);
//		
		return classes;
	}

}



