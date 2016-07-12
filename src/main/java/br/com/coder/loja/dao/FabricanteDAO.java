package br.com.coder.loja
.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.util.HibernateUtil;

public class FabricanteDAO extends BaseDAO<Fabricante> {

	private static final long serialVersionUID = 1L;

	public List<Fabricante> buscarTodos() {
		Session session = HibernateUtil.getSession();
		Query query = session.getNamedQuery("Fabricante.buscarTodos");
		List list = query.list();
		return list;
	}

	public List<Fabricante> buscarPorNome(String nome) {
		Session session = HibernateUtil.getSession();
		Query query = session.getNamedQuery("Fabricante.buscarPorNome");
		query.setString("nome", nome+"%");
		List list = query.list();
		return list;
	}

}
