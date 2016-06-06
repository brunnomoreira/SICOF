package br.imd.pgm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.imd.pgm.enun.Procuradorias;
import br.imd.pgm.model.Procurador;
import br.imd.pgm.repository.Procuradores;
import br.imd.pgm.util.jpa.Transacional;
import br.imd.pgm.util.message.FacesMessages;

@Named
@ViewScoped
public class ProcuradoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Procuradores procuradores;

	@Inject
	private FacesMessages messages;
	
	@Inject
	private Procurador procuradorEdicao;
	
	private List<Procurador> todosProcuradores;
	
	@PostConstruct
	public void iniciar(){
		//this.procuradorEdicao = new Procurador();
	}

	@Transacional
	public void gravar() {
		this.procuradores.gravar(this.procuradorEdicao);
		this.procuradorEdicao = new Procurador();
		this.consultar();
		
		this.messages.info("Procurador salvo com sucesso!");
	}
	
	@Transacional
	public void excluir(Procurador procurador){
		this.procuradores.excluir(procurador);
		this.consultar();
		
		this.messages.info("Procurador excluído com sucesso!");
	}

	public void consultar() {
		this.todosProcuradores = this.procuradores.todos();
	}

	public Procurador getProcuradorEdicao() {
		return procuradorEdicao;
	}

	public void setProcuradorEdicao(Procurador procuradorEdicao) {
		this.procuradorEdicao = procuradorEdicao;
	}

	public List<Procurador> getTodosProcuradores() {
		return todosProcuradores;
	}
	
	public Procuradorias[] getProcuradorias(){
		return Procuradorias.values();
	}

}
