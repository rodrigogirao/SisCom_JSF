package br.ufc.es.siscom.dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.util.CriarTabelas;
import br.ufc.es.siscom.util.PreparaSessao;

public class DisciplinaDAO {
	
	private static Session session;
	
	public static void adicionarDisciplina(Disciplina disciplina){

		session = (Session) PreparaSessao.pegarSessao();
		session.save(disciplina);
		session.beginTransaction().commit();
		session.close();
		
		
		}
	
	public static List<Disciplina> retornarDisciplinas() {
		session = (Session) PreparaSessao.pegarSessao();
		ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) session.createCriteria(Disciplina.class).list();
		session.close();
		return disciplinas;

    }
	
	public static List<Disciplina> retornarListaDeDisciplinaPorListaDeNomes(List<String> nomes){
		session = (Session) PreparaSessao.pegarSessao();
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (String nomeDisciplina : nomes) {
			Disciplina disciplina = (Disciplina) session.createCriteria(Disciplina.class).add(Restrictions.eq("nome", nomeDisciplina)).uniqueResult();
			disciplinas.add(disciplina);
		}
		session.close();
		return disciplinas;
		
	}
	
	public static Disciplina retornarDisciplinaPorNome(String nomeDisciplina) {
		session = (Session) PreparaSessao.pegarSessao();
		Disciplina disciplina = (Disciplina) session.createCriteria(Disciplina.class).add(Restrictions.eq("nome", nomeDisciplina)).uniqueResult();
		session.close();
		return disciplina;
	}

	public static void atualizarDisciplina(Disciplina novaDisciplina){
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Disciplina disciplinaBd = (Disciplina)session.load(Disciplina.class, novaDisciplina.getId());
		disciplinaBd = novaDisciplina;
		session.update(disciplinaBd);
		transaction.commit();
		session.close();
		
	}
	
	
	public static Disciplina buscarDisciplinaPorCodigo(String codigo) {
		session = (Session) PreparaSessao.pegarSessao();
		String hql = "from Disciplina disciplina where disciplina.codigo like ?";	
		Query query = (Query) session.createQuery(hql).setString(0, codigo);
		Disciplina disciplina = (Disciplina) query.uniqueResult();
		session.close();
		return disciplina;
	}
	public static void deletarDisciplina(Disciplina disciplina)
	{
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Disciplina disciplinaBd = (Disciplina)session.load(Disciplina.class, disciplina.getId());
		session.delete(disciplinaBd);
		transaction.commit();
		session.close();
	
       }
	}
