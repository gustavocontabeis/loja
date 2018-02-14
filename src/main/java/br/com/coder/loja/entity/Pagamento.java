package br.com.coder.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="PAGAMENTO")
public class Pagamento extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAGAMENTO_SEQ")
	@SequenceGenerator(sequenceName="PAGAMENTO_SEQ", name="PAGAMENTO_SEQ") 
	@Column(name="ID_PAGAMENTO")  
	private Long id;
	
	@Column(name="BANDEIRA", length=50, nullable=false)
	private String bandeira;

	@Column(name="NR_CARTAO", length=50, nullable=false)
	private String nrCartao;

	@Column(name="VENCIMENTO", length=50, nullable=false)
	private String vencimento;

	@Column(name="NOME_TITULAR", length=50, nullable=false)
	private String nomeTitular;

	@Column(name="COD_SEGURANCA", length=50, nullable=false)
	private String codSeguranca;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CARRINHO_COMPRAS")	@ForeignKey(name="PAGAMENTO_CARRINHO_COMPRAS_FK")
	private CarrinhoCompras carrinho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNrCartao() {
		return nrCartao;
	}

	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getCodSeguranca() {
		return codSeguranca;
	}

	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}

	public CarrinhoCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoCompras carrinho) {
		this.carrinho = carrinho;
	}

}
