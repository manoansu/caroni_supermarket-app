package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import pt.amane.caroni_supermarket.domain.Cliente;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente>{
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Cliente.class);
			
			// o create alias requer 1º args, o nome q se encontra na class cliente nesse caso é pessoa ("pessoa", "p")
			//e o 2º é o nome q vai se manipulado para chamar outro atributo da calss 
			//por Ex p.nome, o nome pertence a classe pessoa mas nao esta na classe cliente, a pessoa é chave estrangeira na classe cliente..
			criteria.createAlias("pessoa", "p"); 
			
			criteria.addOrder(Order.asc("p.nome"));
			
			List<Cliente> list = criteria.list();
			return list;
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
