package br.com.coder.loja.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.coder.loja.entity.CarrinhoCompras;
import br.com.coder.loja.util.HibernateUtil;

public class CarrinhoComprasDAO extends BaseDAO<CarrinhoCompras> {
	
	private static final long serialVersionUID = 1L;

	public List<CarrinhoCompras> buscarTodos() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select obj from br.com.coder.loja.entity.CarrinhoCompras obj order by obj.id");
		List list = query.list();
		return list;
	}

//	public List<Fabricante>buscarTodos(){
//	Session session = HibernateUtil.getSession();
//	Query query = session.getNamedQuery("Fabricante.buscarTodos");
//	List list = query.list();
//	return list;
//}
}
