package br.com.codenation.time.model;

import java.time.LocalDate;
import java.util.List;

import br.com.codenation.jogador.model.JogadorModel;

public class TimeModel{

	//implements Comparable<TimeModel>
	
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long capitaoTime;
	private List<JogadorModel> jogadores;
	
	public TimeModel(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario, Long capitaoTime, List<JogadorModel> jogadores) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
		this.capitaoTime = capitaoTime;
		this.jogadores = jogadores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Long getCapitaoTime() {
		return capitaoTime;
	}

	public void setCapitaoTime(Long capitaoTime) {
		this.capitaoTime = capitaoTime;
	}

	public List<JogadorModel> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<JogadorModel> jogadores) {
		this.jogadores = jogadores;
	}
}
