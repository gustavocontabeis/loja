package br.com.coder.loja.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="CARRINHO_COMPRAS")
public class CarrinhoCompras extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARRINHO_COMPRAS_SEQ")
	@SequenceGenerator(sequenceName="CARRINHO_COMPRAS_SEQ", name="CARRINHO_COMPRAS_SEQ") 
	@Column(name="ID_CARRINHO_COMPRAS")  
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_COMPRA", nullable=false)
	private Date dataCompra;

	//@JsonIgnore
	@ManyToOne(optional=false, cascade={CascadeType.DETACH})
	@JoinColumn(name="ID_CLIENTE", nullable=false) 
	@ForeignKey(name="CARRINHO_COMPRAS_CLIENTE_FK")
	private Cliente cliente;
	
	//@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_ENDERECO_ENTREGA", nullable=false) 
	@ForeignKey(name="CARRINHO_COMPRAS_ENDERECO_ENTREGA_FK")
	private EnderecoEntrega enderecoEntrega;
	
	@OneToMany(mappedBy="carrinhoCompras", cascade=CascadeType.ALL)
	private List<Compra>compras;
	
	@Column(name="VALOR_TOTAL", scale=2, precision=5)
	private BigDecimal valorTotal;

	@Column(name="VALOR_DESCONTO", scale=2, precision=5)
	private BigDecimal valorDesconto;

	@Column(name="VALOR_FINAL", scale=2, precision=5)
	private BigDecimal valorFinal;

	@Column(name="VALOR_FRETE", scale=2, precision=5)
	private BigDecimal valorFrete;
	
	@OneToOne(mappedBy="carrinho", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Pagamento pagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

}
