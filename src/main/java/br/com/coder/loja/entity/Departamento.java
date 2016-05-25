package br.com.coder.loja.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="DEPARTAMENTO")
public class Departamento extends AbstractBaseEntity implements Comparable<Departamento> {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_DEPARTAMENTO")  
	private Long id;
	
	@Column(name="ESTRUTURA", length=100, nullable=false)
	private String estrutura;

	@Column(name="NOME", length=100, nullable=false)
	private String nome;

	@Column(name="DESCRICAO", length=100, nullable=false)
	private String descricao;
	
	@Transient
	private Integer nivel;
	
	@Transient
	private List<Departamento>filhos;
	
	public Departamento() {
		super();
	}
	
	public Departamento(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(String estrutura) {
		this.estrutura = estrutura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Departamento> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Departamento> filhos) {
		this.filhos = filhos;
	}

	@Override
	public int compareTo(Departamento o) {
		return this.getEstrutura().compareTo(o.getEstrutura());
	}
	
	public void setNivel(Integer nivel){
		this.nivel = nivel;
	}
	
	public int getNivel(){
		if(estrutura == null)
		return 0;
		if(estrutura.endsWith("000.000.000"))
			return 1;
		if(estrutura.endsWith("000.000"))
			return 2;
		if(estrutura.endsWith("000"))
			return 3;
		return 4;
				
	}

}
