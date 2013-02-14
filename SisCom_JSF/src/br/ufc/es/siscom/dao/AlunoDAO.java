package br.ufc.es.siscom.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Horario;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.util.PreparaSessao;

public class AlunoDAO{
	
	private static Session session;

	public static void adicionarAluno(Aluno aluno){

		session = (Session) PreparaSessao.pegarSessao();
		session.save(aluno);
		session.beginTransaction().commit();
		session.close();
		}
	
	public static ArrayList<Aluno> retornarAlunos() {
		session = (Session) PreparaSessao.pegarSessao();
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) session.createCriteria(Aluno.class).list();
		session.close();
		return alunos;

    }
	
	public static Aluno retornaAlunoPorLogin(String login){
		session = (Session) PreparaSessao.pegarSessao();
		Aluno aluno = (Aluno) session.createCriteria(Aluno.class).add(Restrictions.eq("login", login)).uniqueResult();
		session.close();
		return aluno;
	}
	
	public static ArrayList<Aluno> retornaAlunosPorNome(String nome) {
		session = (Session) PreparaSessao.pegarSessao();
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) session.createCriteria(Aluno.class).add(Restrictions.like("nome", nome)).list();
		session.close();
		return alunos;
	}
	
	public static Aluno retornaAlunoPorMatricula(String matricula){
		session = (Session) PreparaSessao.pegarSessao();
		Aluno aluno = (Aluno) session.createCriteria(Aluno.class).add(Restrictions.eq("matricula", matricula)).uniqueResult();
		session.close();
		return aluno;
	}
	
	public static void deletarAluno(Aluno aluno){
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Aluno alunoDB = (Aluno) session.load(Aluno.class, aluno.getId());
		session.delete(alunoDB);
		transaction.commit();
		session.close();
	}
	
	public static void atualizarAluno(Aluno novoAluno){
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();
		Aluno alunoDB = (Aluno) session.load(Aluno.class, novoAluno.getId());
		alunoDB = novoAluno;
		session.update(alunoDB);
		transaction.commit();
		session.close();
	}

	public static List<Horario> retornaHorariosDoAluno(Aluno aluno) {
		session = (Session) PreparaSessao.pegarSessao();
		String hql = "select horarios from Aluno aluno where aluno.id like ?";
		Query query = (Query) session.createQuery(hql).setLong(0, aluno.getId());
		List<Horario> horarios = query.list();
		session.close();
		return horarios;
	}
	
	public static void associarAlunoAoHorario(long id, Horario horario) {
		session = (Session) PreparaSessao.pegarSessao();
		Transaction transaction = session.beginTransaction();

		Aluno aluno = new Aluno();
		session.load(aluno, id);
		
		aluno.getHorarios().add(horario);
		transaction.commit();
		session.close();
		
		
	}
	
}
