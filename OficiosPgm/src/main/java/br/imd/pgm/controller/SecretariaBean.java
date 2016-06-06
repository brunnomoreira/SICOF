package br.imd.pgm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.imd.pgm.model.Secretaria;
import br.imd.pgm.repository.Secretarias;
import br.imd.pgm.util.jpa.Transacional;
import br.imd.pgm.util.message.FacesMessages;

@Named
@ViewScoped
public class SecretariaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Secretaria secretariaEdicao;
	private List<Secretaria> todasSecretarias;
	
	@Inject
	private Secretarias secretarias;
	
	@Inject
	private FacesMessages message;
	
	@PostConstruct
	private void iniciar(){
		//this.secretariaEdicao = new Secretaria();
	}
	
	@Transacional
	public void gravar(){
		this.secretarias.gravar(this.secretariaEdicao);
		this.secretariaEdicao = new Secretaria();
		this.consulta();
		
		this.message.info("Secretaria adicionada com sucesso!");
	}

	public void consulta() {
		this.todasSecretarias = this.secretarias.todas();
	}
	
	@Transacional
	public void excluir(Secretaria secretaria){
		this.secretarias.excluir(secretaria);
		this.consulta();
		
		this.message.info("Secretaria excluída com sucesso!");
	}

	public Secretaria getSecretariaEdicao() {
		return secretariaEdicao;
	}

	public void setSecretariaEdicao(Secretaria secretariaEdicao) {
		this.secretariaEdicao = secretariaEdicao;
	}

	public List<Secretaria> getTodasSecretarias() {
		return todasSecretarias;
	}

}
