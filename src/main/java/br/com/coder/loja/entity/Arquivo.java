package br.com.coder.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity(name="ARQUIVO")
public class Arquivo extends AbstractBaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="arquivo_seq")
	@SequenceGenerator(sequenceName="arquivo_seq", name="arquivo_seq")
	@Column(name="ID_ARQUIVO")
	private Long id;
	
	//@Lob
	@Column(name="DADOS", nullable=false, columnDefinition="bytea not null" )
	private byte[] dados;
	
	@Column(name="TAMANHO")
	private Long tamanho;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="EXTENCAO")
	private String extencao;
	
	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExtencao() {
		return extencao;
	}

	public void setExtencao(String extencao) {
		this.extencao = extencao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

}
