package br.com.coder.loja.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="IMAGEM_PRODUTO")
public class ImagemProduto extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_IMAGEM_PRODUTO")  
	private Long id;
	
	@JsonIgnore
	@ManyToOne(optional=false, cascade={CascadeType.DETACH})
	@JoinColumn(name="ID_PRODUTO", nullable=false)
	@ForeignKey(name="IMAGEM_PRODUTO_PRODUTO_FK")
	private Produto produto;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ARQUIVO", nullable=false)
	@ForeignKey(name="IMAGEM_PRODUTO_ID_ARQUIVO_FK")
	private Arquivo arquivo;

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

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
