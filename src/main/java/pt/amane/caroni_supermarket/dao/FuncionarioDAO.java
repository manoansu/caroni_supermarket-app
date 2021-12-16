package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import pt.amane.caroni_supermarket.domain.Funcionario;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Funcionario.class);
			
			// o create alias requer 1º args, o nome q se encontra na class Funcionario nesse caso é pessoa ("pessoa", "p")
			//e o 2º é o nome q vai se manipulado para chamar outro atributo da calss 
			//por Ex p.nome, o nome pertence a classe pessoa mas nao esta na classe Funcionario, a pessoa é chave estrangeira na classe cliente..
			criteria.createAlias("pessoa", "p"); 
			
			criteria.addOrder(Order.asc("p.nome"));
			
			List<Funcionario> list = criteria.list();
			return list;
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}
