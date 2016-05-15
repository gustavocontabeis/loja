package br.com.coder.loja.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

	private static SessionFactory factory;
	private static Session session = null;
	
	static{
		factory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
	}

	private HibernateUtil() {
		super();
	}
	
	public static Session getSession() {
		
		if(session == null || !session.isOpen()){
			session = factory.openSession();//session = factory.openSession(new BaseEntityInterceptor());
			//session.setFlushMode(FlushMode.COMMIT);
		}
		//else
			//session = factory.getCurrentSession();
		return session;
	}

}