package br.com.coder.loja.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.coder.loja.util.HibernateUtil;

@WebListener
public class AppListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0)  { 
		//HibernateUtil.getSession();
	}
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

}
