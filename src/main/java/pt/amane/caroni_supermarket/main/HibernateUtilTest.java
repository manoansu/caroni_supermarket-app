package pt.amane.caroni_supermarket.main;

import org.hibernate.Session;

import pt.amane.caroni_supermarket.util.HibernateUtil;

public class HibernateUtilTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
		HibernateUtil.getSessionFactory().close();

	}

}
