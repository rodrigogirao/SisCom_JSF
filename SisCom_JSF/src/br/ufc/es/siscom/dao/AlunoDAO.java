package br.ufc.es.siscom.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufc.es.siscom.model.Aluno;

public class AlunoDAO{

	public static void adicionarAluno(Aluno aluno){

		Session session = CriarTabelas.preparaSessao();
		session.save(aluno);
		session.beginTransaction().commit();
		session.close();
		}
	
	public static ArrayList<Aluno> retornarAlunos() {
		Session session = CriarTabelas.preparaSessao();
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) session.createCriteria(Aluno.class).list();
		return alunos;

    }
	
	public static Aluno retornaAlunoPorLogin(String login){
		Session session = CriarTabelas.preparaSessao();
		return (Aluno) session.createCriteria(Aluno.class).add(Restrictions.eq("login", login)).uniqueResult();
	
	}
	
	public static ArrayList<Aluno> retornaAlunosPorNome(String nome) {
		Session session = CriarTabelas.preparaSessao();
		return (ArrayList<Aluno>) session.createCriteria(Aluno.class).add(Restrictions.like("nome", nome)).list();
	}
	
	public static Aluno retornaAlunoPorMatricula(String matricula){
		Session session = CriarTabelas.preparaSessao();
		return (Aluno) session.createCriteria(Aluno.class).add(Restrictions.eq("matricula", matricula)).uniqueResult();
	
	}
	
	public static void deletarAluno(Aluno aluno){
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Aluno alunoDB = (Aluno) session.load(Aluno.class, aluno.getId());
		session.delete(alunoDB);
		transaction.commit();
		session.close();
	}
	
	public static void atualizarAluno(Aluno novoAluno){
		Session session = CriarTabelas.preparaSessao();
		Transaction transaction = session.beginTransaction();
		Aluno alunoDB = (Aluno) session.load(Aluno.class, novoAluno.getId());
		alunoDB = novoAluno;
		session.update(alunoDB);
		transaction.commit();
		session.close();
	}
	
}
