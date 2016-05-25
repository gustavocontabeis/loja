package br.com.coder.loja.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity(name="PRODUTO")
public class Produto extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_PRODUTO")  
	private Long id;
	
	@Column(name="NOME", length=100, nullable=false)
	private String nome;

	@Column(name="DESCRICAO", length=500, nullable=false)
	private String descricao;

	@Column(name="COD_REFERENCIA_FABRICANTE", length=50, nullable=true)
	private String codReferenciaFabricante;

	@Column(name="ATIVO", length=100, nullable=false)
	private Boolean ativo;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_FABRICANTE", nullable=false) 
	@ForeignKey(name="PRODUTO_FABRICANTE_FK")
	private Fabricante fabricante;

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_DEPARTAMENTO", nullable=false)
	@ForeignKey(name="PRODUTO_DEPARTAMENTO_FK")
	private Departamento departamento;
	
	@ManyToOne(optional=false, cascade={CascadeType.ALL}) //@Where(clause=" ativo = true")
	@JoinColumn(name="ID_VALOR", nullable=false)
	@ForeignKey(name="PRODUTO_VALOR_FK")
	private Valor valor;
	
	@OneToMany(mappedBy="produto", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CaracteristicaProduto> caracteristicas;
	
	@OneToMany(mappedBy="produto", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ImagemProduto>imagens;
	
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

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodReferenciaFabricante() {
		return codReferenciaFabricante;
	}

	public void setCodReferenciaFabricante(String codReferenciaFabricante) {
		this.codReferenciaFabricante = codReferenciaFabricante;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public List<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicaProduto> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemProduto> imagens) {
		this.imagens = imagens;
	}

}
