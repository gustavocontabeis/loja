package br.com.coder.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="PERFIL")
public class Perfil extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_SEQ")
	@SequenceGenerator(sequenceName="PERFIL_SEQ", name="PERFIL_SEQ")
	@Column(name="ID_PERFIL")  
	private Long id;
	
	@Column(name="NOME", length=100, nullable=false)
	private String nome;
	
	public Perfil() {
		super();
	}
	
	public Perfil(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

}
