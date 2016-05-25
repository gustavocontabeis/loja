package br.com.coder.loja.entity;

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
@Entity(name="ENDERECO_ENTREGA")
public class EnderecoEntrega extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ENDERECO_ENTREGA")  
	private Long id;
	
	@Column(name="CONTATO", length=100, nullable=false)
	private String contato;

	@Column(name="LOGRADOURO", length=100, nullable=false)
	private String logradouro;

	@Column(name="CIDADE", length=100, nullable=false)
	private String cidade;

	@Enumerated(EnumType.STRING)
	@Column(name="UF", nullable=false, length=25)
	private UF uf;

	@Column(name="CEP", length=100, nullable=false)
	private String cep;
	
	@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_CLIENTE", nullable=false)
	@ForeignKey(name="ENDERECO_ENTREGA_CLIENTE_FK")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

}
