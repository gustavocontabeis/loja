package br.com.coder.loja.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public final class HibernateUtil {

	private static SessionFactory factory;
	private static Session session = null;

	// static{
	//
	// Configuration configuration = new Configuration();
	//
	// factory = (SessionFactory) configuration.configure("hibernate.cfg.xml")
	// .buildSessionFactory();
	// //SchemaExport se = new SchemaExport(configuration);
	// //se.create(true, true);
	// }

	private HibernateUtil() {
		super();
	}

	public static Session getSession() throws RuntimeException {
		
		if (session == null || !session.isOpen()) {

			if (factory == null) {
				criarSessionFactory();
			}
			session = factory.openSession();// session = factory.openSession(new
											// BaseEntityInterceptor());
			// session.setFlushMode(FlushMode.COMMIT);
		}
		// else
		// session = factory.getCurrentSession();
		return session;
	}

	private static void criarSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			factory = (SessionFactory) configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (java.lang.OutOfMemoryError e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}