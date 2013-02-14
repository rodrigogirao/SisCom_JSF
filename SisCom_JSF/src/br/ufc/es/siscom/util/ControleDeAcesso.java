package br.ufc.es.siscom.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufc.es.siscom.model.Administrador;
import br.ufc.es.siscom.model.Aluno;
import br.ufc.es.siscom.model.Monitor;
import br.ufc.es.siscom.model.Orientador;

public class ControleDeAcesso implements PhaseListener{
	private FacesMessage msg = null;

    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext(); //pego o contexto do Faces
        String currentPage = fc.getViewRoot().getViewId(); // armazeno numa String o id da minha página que está sendo requisitada
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		
		if(verificarPaginaAdmin(currentPage)){
			
			Administrador admin = (Administrador) session.getAttribute("admin");
			
			if( admin == null){ //verifico se a página requisitada é a admin
				this.redirecionarParaLogin(fc);
			}
		}
		
		if(verificarPaginaOrientador(currentPage)){
 
			Orientador orientador = (Orientador) session.getAttribute("orientador");
			
			if( orientador == null){
				this.redirecionarParaLogin(fc);
			}
		}
		
		if(verificarPaginaAluno(currentPage)){
			 
			Aluno aluno = (Aluno) session.getAttribute("aluno");
			
			if( aluno == null){
				this.redirecionarParaLogin(fc);
			}
		}
		
		if(verificarPaginaMonitor(currentPage)){
			 
			Monitor monitor = (Monitor) session.getAttribute("monitor");
			
			if( monitor == null){
				this.redirecionarParaLogin(fc);
			}
		}
		
		if(verificarPaginaTemplate(currentPage)){
			 
				this.redirecionarParaLogin(fc);
			
		}
    }
    
    public boolean verificarPaginaAdmin(String currentPage){
    	boolean PageAdminInicial = (currentPage.lastIndexOf("/telasAdministrador/opcoesAdministrador.xhtml") > -1);
        boolean PageAdminAtualizarAluno = (currentPage.lastIndexOf("/telasAdministrador/atualizarAluno.xhtml") > -1);
        boolean PageAdminAtualizarDisciplina = (currentPage.lastIndexOf("/telasAdministrador/atualizarDisciplina.xhtml") > -1);
        boolean PageAdminAtualizarOrientador = (currentPage.lastIndexOf("/telasAdministrador/atualizarOrientador.xhtml") > -1);
        boolean PageAdminCadastroAluno = (currentPage.lastIndexOf("/telasAdministrador/cadastroAluno.xhtml") > -1);
        boolean PageAdminCadastroDisciplina = (currentPage.lastIndexOf("/telasAdministrador/cadastroDisciplina.xhtml") > -1);
        boolean PageAdminCadastroOrientador = (currentPage.lastIndexOf("/telasAdministrador/cadastroOrientador.xhtml") > -1);
        boolean PageAdminListarAlunos = (currentPage.lastIndexOf("/telasAdministrador/listarAlunos.xhtml") > -1);
        boolean PageAdminListarDisciplinas = (currentPage.lastIndexOf("/telasAdministrador/listarDisciplinas.xhtml") > -1);
        boolean PageAdminListarOrientadores = (currentPage.lastIndexOf("/telasAdministrador/listarOrientadores.xhtml") > -1);
        
        List<Boolean> paginasAdmin = new ArrayList<Boolean>();
        paginasAdmin.add(PageAdminInicial);
        paginasAdmin.add(PageAdminAtualizarAluno);
        paginasAdmin.add(PageAdminAtualizarDisciplina);
        paginasAdmin.add(PageAdminAtualizarOrientador);
        paginasAdmin.add(PageAdminCadastroAluno);
        paginasAdmin.add(PageAdminCadastroDisciplina);
        paginasAdmin.add(PageAdminCadastroOrientador);
        paginasAdmin.add(PageAdminListarAlunos);
        paginasAdmin.add(PageAdminListarDisciplinas);
        paginasAdmin.add(PageAdminListarOrientadores);
        
        for (Boolean paginaRequisitadaAdmin : paginasAdmin) {
			if(paginaRequisitadaAdmin){
				return true;
			}
		}
        return false;       
    }
    
    public boolean verificarPaginaOrientador(String currentPage){
    	boolean PageOrientadorInicial = (currentPage.lastIndexOf("/telasOrientador/orientadorInicial.xhtml") > -1);
    	boolean PageOrientarAlunos = (currentPage.lastIndexOf("/telasOrientador/orientarAlunos.xhtml") > -1);
    	boolean PageHorariosMonitor = (currentPage.lastIndexOf("/telasOrientador/horariosMonitor.xhtml") > -1);
    	boolean PageGerenciarDisciplinas = (currentPage.lastIndexOf("/telasOrientador/gerenciarDisciplinas.xhtml") > -1);
    	boolean PageBuscarAlunos = (currentPage.lastIndexOf("/telasOrientador/buscarAlunos.xhtml") > -1);
    	
    	 List<Boolean> paginasOrientador = new ArrayList<Boolean>();
    	 paginasOrientador.add(PageBuscarAlunos);
    	 paginasOrientador.add(PageGerenciarDisciplinas);
    	 paginasOrientador.add(PageHorariosMonitor);
    	 paginasOrientador.add(PageOrientadorInicial);
    	 paginasOrientador.add(PageOrientarAlunos);
    	 
    	 for (Boolean paginaRequisitadaOrientador : paginasOrientador) {
 			if(paginaRequisitadaOrientador){
 				return true;
 			}
 		 }
    	 return false;
    }
    
    public boolean verificarPaginaAluno(String currentPage){
    	boolean PageAlunoInicial = (currentPage.lastIndexOf("/telasAluno/alunoInicial.xhtml") > -1);
    	boolean PageConfirmarHorario = (currentPage.lastIndexOf("/telasAluno/confirmarHorario.xhtml") > -1);
    	
    	List<Boolean> paginasAluno = new ArrayList<Boolean>();
    	paginasAluno.add(PageAlunoInicial);
    	paginasAluno.add(PageConfirmarHorario);
    	
    	for (Boolean paginaRequisitadaAluno : paginasAluno) {
 			if(paginaRequisitadaAluno){
 				return true;
 			}
 		 }
    	
    	return false;
    }
    
    public boolean verificarPaginaMonitor(String currentPage){
    	boolean PageMonitorInicial = (currentPage.lastIndexOf("/telasMonitor/monitorInicial.xhtml") > -1);
    	boolean PageCadastrarHorario = (currentPage.lastIndexOf("/telasMonitor/cadastrarHorario.xhtml") > -1);
    	
    	List<Boolean> paginasMonitor = new ArrayList<Boolean>();
    	paginasMonitor.add(PageMonitorInicial);
    	paginasMonitor.add(PageCadastrarHorario);
    	
    	for (Boolean paginaRequisitadaMonitor : paginasMonitor) {
 			if(paginaRequisitadaMonitor){
 				return true;
 			}
 		 }
    	
    	return false;
    }
    

    public boolean verificarPaginaTemplate(String currentPage){
    	boolean PageTemplateAdministrador = (currentPage.lastIndexOf("/templates/templateAdministrador.xhtml") > -1);
    	boolean PageTemplateAluno = (currentPage.lastIndexOf("/templates/templateAluno.xhtml") > -1);
    	boolean PageTemplateIndex = (currentPage.lastIndexOf("/templates/templateIndex.xhtml") > -1);
    	boolean PageTemplateMonitor = (currentPage.lastIndexOf("/templates/templateMonitor.xhtml") > -1);
    	boolean PageTemplateOrientador = (currentPage.lastIndexOf("/templates/templateOrientador.xhtml") > -1);
    	
    	List<Boolean> paginasTemplate = new ArrayList<Boolean>();
    	paginasTemplate.add(PageTemplateAdministrador);
    	paginasTemplate.add(PageTemplateAluno);
    	paginasTemplate.add(PageTemplateIndex);
    	paginasTemplate.add(PageTemplateMonitor);
    	paginasTemplate.add(PageTemplateOrientador);
    	
    	for (Boolean paginaRequisitadaTemplate : paginasTemplate) {
 			if(paginaRequisitadaTemplate){
 				return true;
 			}
 		 }
    	
    	return false;
    }
    
    public void redirecionarParaLogin(FacesContext fc){
    	
    	NavigationHandler nh = fc.getApplication().getNavigationHandler(); // instancio um objeto de navegação
        nh.handleNavigation(fc, null, "/index.xhtml"); // passo o contexto do Faces, null para minha action, e loginPage como from-outcome
        fc.renderResponse(); // chamo a última fase do ciclo de vida
        msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Acesso não autorizado", "Usuario não logado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
    }

    public void beforePhase(PhaseEvent event) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
