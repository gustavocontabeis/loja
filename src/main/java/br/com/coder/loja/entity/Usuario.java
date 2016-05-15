package br.com.coder.loja.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="USUARIO")
public class Usuario extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID_USUARIO")  
	private Long id;
	
	@Column(name="LOGIN", length=60, nullable=false, unique=true)
	private String login;
	
	@Column(name="SENHA", length=20, nullable=false)
	private String senha;
	
	@Column(name="NOME", length=100, nullable=false)
	private String nome;

	@Column(name="EMAIL", length=100, nullable=false, unique=true)
	private String email;
	
	@Transient
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL", 
		joinColumns = { 		@JoinColumn(name = "ID_USUARIO", nullable = false) }, 
		inverseJoinColumns = { 	@JoinColumn(name = "ID_PERFIL", nullable = false) })
	private List<Perfil>perfis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
