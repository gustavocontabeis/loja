package br.com.coder.loja.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.coder.loja.entity.BaseEntity;
import br.com.coder.loja.util.HibernateUtil;

public class BaseDAO<T extends BaseEntity> implements Serializable {
	
	private static final long serialVersionUID 	= 1L;

	public static boolean junit = Boolean.FALSE;
	
	protected Session getSession(){
		return HibernateUtil.getSession();
	}
	
	public void salvar(T obj){
		validate(obj);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		
		if(obj.getId() != null && obj.getId().equals(0L)){
			obj.setId(null);
		}
		boolean salvar = false;
		try {
			if(obj.getId()==null){
				salvar = true;
				session.save(obj);
			}else{
				//session.update(obj);
				session.merge(obj);
				//session.flush();
			}
			session.flush();
			session.clear();
			transaction.commit();
		} catch (HibernateException e) {
			obj.setId(null);
			transaction.rollback();
			session.close();
			aposErroSalvar(obj, salvar);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected void aposErroSalvar(T obj, boolean ehSalvar) {

	}
	
	public void excluir(T obj){
		validate(obj);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.merge(obj);
			session.flush();
			session.clear();
			session.delete(obj);
			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends BaseEntity> T buscar(T obj)  {
		Session session = getSession();
		//Transaction transaction = session.beginTransaction();
		try {
			T load = (T) session.get(obj.getClass(), obj.getId());
			//transaction.commit();
			return load;
		} catch (HibernateException e) {
			//transaction.rollback();
			session.close();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends BaseEntity> T buscar(Class classe, Long obj)  {
		Session session = getSession();
		//Transaction transaction = session.beginTransaction();
		try {
			T load = (T) session.get(classe, obj);
			//transaction.commit();
			return load;
		} catch (HibernateException e) {
			//transaction.rollback();
			session.close();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected void validate(BaseEntity obj) {
		//AssertUtils.validate(obj);
	}
	
}
