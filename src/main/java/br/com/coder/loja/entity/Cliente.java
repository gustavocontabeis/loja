package br.com.coder.loja.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.ForeignKey;

import br.com.coder.loja.xml.DateBRAdapter;

@XmlRootElement
@Entity(name="CLIENTE")
public class Cliente extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_SEQ")
	@SequenceGenerator(sequenceName="CLIENTE_SEQ", name="CLIENTE_SEQ") 
	@Column(name="ID_CLIENTE")  
	private Long id;
	
	@Column(name="TELEFONES", length=100, nullable=false)
	private String telefones;

	@Column(name="CPF_CNPJ", length=100, nullable=false, unique=true)
	private String cpfCnpj;
	
	@XmlJavaTypeAdapter(DateBRAdapter.class)
	@Column(name="DT_NASCIMENTO", nullable=false)
	private Date dtNascimento;
	
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	private List<EnderecoEntrega>enderecos;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO")
	@ForeignKey(name="CLIENTE_USUARIO_FK")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public List<EnderecoEntrega> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntrega> enderecos) {
		this.enderecos = enderecos;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
