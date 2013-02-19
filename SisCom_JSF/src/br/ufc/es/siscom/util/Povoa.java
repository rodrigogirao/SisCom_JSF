package br.ufc.es.siscom.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufc.es.siscom.controller.AlunoController;
import br.ufc.es.siscom.controller.DisciplinaController;
import br.ufc.es.siscom.controller.MonitorController;
import br.ufc.es.siscom.controller.OrientadorController;
import br.ufc.es.siscom.dao.AdministradorDAO;
import br.ufc.es.siscom.dao.AlunoDAO;
import br.ufc.es.siscom.dao.DisciplinaDAO;
import br.ufc.es.siscom.dao.MonitorDAO;
import br.ufc.es.siscom.dao.OrientadorDAO;
import br.ufc.es.siscom.model.Administrador;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Disciplina;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

public class Povoa {
	public static void main(String[] args) {

		CriarTabelas.reiniciaEsquemaBD();

		Administrador admin = new Administrador();
		admin.setLogin("admin");
		admin.setSenha("admin");
		AdministradorDAO.adicionarAdministrador(admin);

//		Disciplina disciplina = new Disciplina();
//		disciplina.setCodigo("QX020");
//		disciplina.setNome("POO");
//
//		DisciplinaController dc = new DisciplinaController();
//		dc.setDisciplina(disciplina);
//		dc.cadastrarDisciplina();
//
//
//		Aluno aluno = new Aluno();
//		aluno.setDataNascimento(new Date(14/10/1990));
//		aluno.setNome("Rodrigo");
//		aluno.setMatricula("0308845");
//		aluno.setLogin("rodrig");
//		aluno.setSenha("12345");
		
//		Orientador orientador = OrientadorDAO.retornaOrientadorPorLogin("fabio");
//		
//		List<Aluno> alunos = AlunoDAO.retornarTodosAlunosNaoMonitores(orientador);
//		
//		for (Aluno aluno2 : alunos) {
//			System.out.println(aluno2.getLogin());
//		}
		
//		Orientador orientador = OrientadorDAO.retornaOrientadorPorLogin("fabio");
//		
//		List<Monitor> monitores = MonitorDAO.retornaMonitoresDoOrientador(orientador);
//		
//		for (Monitor aluno2 : monitores) {
//			System.out.println(aluno2.getLogin());
//		}
//
//		AlunoController ac = new AlunoController();
//		ArrayList<String> nomeDisciplinasSelecionadas = new ArrayList<String>();
//		nomeDisciplinasSelecionadas.add(disciplina.getNome());
//		ac.setAluno(aluno);
//		ac.setNomeDisciplinasSelecionadas(nomeDisciplinasSelecionadas);
//		ac.salvar();
//
//		Orientador orientador = new Orientador();
//		orientador.setNome("Fabio");
//		orientador.setSiape("0290780");
//		orientador.setLogin("fabio");
//		orientador.setDataNascimento(new Date(11/11/1111));
//		orientador.setSenha("123");
//
//		OrientadorController oc = new OrientadorController();
//		oc.setOrientador(orientador);
//		oc.cadastrarOrientador();
//
//		Monitor monitor = new Monitor();
//		monitor.setNome(aluno.getNome());
//		monitor.setMatricula(aluno.getMatricula());
//		monitor.setLogin(aluno.getLogin());
//		monitor.setSenha(aluno.getSenha());
//		monitor.setOrientador(OrientadorDAO.retornaOrientadorPorLogin(orientador.getLogin()));
//		MonitorDAO.adicionarMonitor(monitor);
		
//		MonitorController mc = new MonitorController();
//		mc.setNomeDisciplinasSelecionadas(nomeDisciplinasSelecionadas);
//		mc.adicionarDisciplinas();

	}

}
