package br.com.coder.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="CARACTERISTICA_PRODUTO")
public class CaracteristicaProduto extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARACTERISTICA_PRODUTO_SEQ")
	@SequenceGenerator(sequenceName="CARACTERISTICA_PRODUTO_SEQ", name="CARACTERISTICA_PRODUTO_SEQ") 
	@Column(name="ID_CARACTERISTICA_PRODUTO")  
	private Long id;
	
	@Column(name="TIPO", length=100, nullable=false)
	private String tipo;
	
	@Column(name="VALOR", length=100, nullable=false)
	private String valor;
	
	@JsonIgnore
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRODUTO", nullable=false)
	@ForeignKey(name="CARACTERISTICA_PRODUTO_PRODUTO_FK")
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
