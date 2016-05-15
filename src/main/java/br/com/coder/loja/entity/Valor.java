package br.com.coder.loja.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import br.com.coder.loja.xml.DateBRAdapter;

@XmlRootElement
@Entity(name="VALOR")
public class Valor extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_VALOR")  
	private Long id;
	
	@Column(name="VALOR", scale=2)
	private BigDecimal valor;
	
	@XmlJavaTypeAdapter(DateBRAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO")
	private Date dtInicio;
	
	@XmlJavaTypeAdapter(DateBRAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_FIM")
	private Date dtFim;
	
	@Column(name="ATIVO")
	private Boolean ativo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_PRODUTO")	@ForeignKey(name="VALOR_PRODUTO_FK")
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	} 
	
}
