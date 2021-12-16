package pt.amane.caroni_supermarket.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pt.amane.caroni_supermarket.domain.Caixa;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{

	@SuppressWarnings("deprecation")
	public Caixa buscar(Date dataAbertura) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = session.createCriteria(Caixa.class); // consulta na tabela caixa..
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura)); // criar were de SQL
			Caixa result = (Caixa) consulta.uniqueResult(); // pegar o resultado
			return result;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			session.close();
		}
			
	}
}
