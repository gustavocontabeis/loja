package br.com.coder.loja.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="COMPRA")
public class Compra extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_COMPRA")  
	private Long id;
	
	@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_CARRINHO_COMPRAS", nullable=false) 
	@ForeignKey(name="COMPRA_CARRINHO_COMPRAS_FK")
	private CarrinhoCompras carrinhoCompras;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PRODUTO", nullable=false)
	@ForeignKey(name="COMPRA_PRODUTO_FK")
	private Produto produto;

	@Column(name="ID_QUANTIDADE")  
	private Long quantidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_QUANTIDADE")  
	private TipoQuantidade tipoQuantidade;
	
	@Column(name="VALOR_UNITARIO", scale=2, precision=5)
	private BigDecimal valorUnitario;

	@Column(name="VALOR_DESCONTO", scale=2, precision=5)
	private BigDecimal valorDesconto;

	@Column(name="VALOR_TOTAL", scale=2, precision=5)
	private BigDecimal valorTotal;

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

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public TipoQuantidade getTipoQuantidade() {
		return tipoQuantidade;
	}

	public void setTipoQuantidade(TipoQuantidade tipoQuantidade) {
		this.tipoQuantidade = tipoQuantidade;
	}

	public CarrinhoCompras getCarrinhoCompras() {
		return carrinhoCompras;
	}

	public void setCarrinhoCompras(CarrinhoCompras carrinhoCompras) {
		this.carrinhoCompras = carrinhoCompras;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
