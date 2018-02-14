package br.com.coder.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="FABRICANTE")
@NamedQueries(value={
		@NamedQuery(name="Fabricante.buscarTodos", query="select obj from br.com.coder.loja.entity.Fabricante obj order by obj.nome asc"),
		@NamedQuery(name="Fabricante.buscarPorNome", query="select obj from br.com.coder.loja.entity.Fabricante obj where obj.nome like(:nome) order by obj.nome asc"),
})
public class Fabricante extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FABRICANTE_SEQ")
	@SequenceGenerator(sequenceName="FABRICANTE_SEQ", name="FABRICANTE_SEQ") 
	@Column(name="ID_FABRICANTE")  
	private Long id;
	
	@Column(name="NOME", length=100, nullable=false)
	private String nome;

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
