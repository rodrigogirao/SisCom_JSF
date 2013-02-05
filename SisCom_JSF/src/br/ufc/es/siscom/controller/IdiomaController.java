package br.ufc.es.siscom.controller;


import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="idiomaController")
@SessionScoped
public class IdiomaController {
	   private Locale currentLocale = new Locale("pt", "BR");
	   public void setLocalePortugues(){
	      currentLocale = new Locale("pt", "BR");
	   }  
	   public void setLocaleIngles(){
	      currentLocale = new Locale("en", "US"); 
	   }
	   public Locale getCurrentLocale() {
	      return currentLocale;
	   }
	   public void setCorrenteLocale(Locale currentLocale) {
	      this.currentLocale = currentLocale;
	   }

}
