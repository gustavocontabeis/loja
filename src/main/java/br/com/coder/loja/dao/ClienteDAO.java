package br.com.coder.loja.dao;

import org.hibernate.Session;

import br.com.coder.loja.entity.Cliente;
import br.com.coder.loja.entity.Usuario;

public class ClienteDAO extends BaseDAO<Cliente> {

	private static final long serialVersionUID = 1L;

	public Cliente buscarEnderecos(Usuario obj) {
		String hql = 
				"select obj "
				+ "from br.com.coder.loja.entity.Cliente obj "
				+ "left join fetch obj.enderecos ender "
				+ "inner join fetch obj.usuario usu "
				+ "where usu.id = :id";
		Session session = getSession();
		Cliente cliente = (Cliente) session.createQuery(hql).setLong("id", obj.getId()).uniqueResult();
		cliente.getUsuario().getPerfis().size();
		session.close();
		return cliente;
	}

	public Cliente buscarPorUsuario(Usuario obj) {
		String hql = 
				"select obj "
				+ "from br.com.coder.loja.entity.Cliente obj "
				+ "inner join fetch obj.usuario usu "
				+ "where usu.id = :id";
		Session session = getSession();
		Cliente cliente = (Cliente) session.createQuery(hql).setLong("id", obj.getId()).uniqueResult();
		cliente.getUsuario().getPerfis().size();
		cliente.getEnderecos().size();
		session.close();
		return cliente;
	}

}
