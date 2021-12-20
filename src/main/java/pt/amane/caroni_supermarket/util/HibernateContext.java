package pt.amane.caroni_supermarket.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContext implements ServletContextListener{

	//Force tomcat to load the hibernate, when tomcat is switch on..
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		
	}

	//tomca is distroed..
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();;
	}

}
