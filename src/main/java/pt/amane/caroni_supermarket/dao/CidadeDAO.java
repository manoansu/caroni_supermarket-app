package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	public List<Cidade> buscarPorEstado(Long estadoId){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			//Faz o select cidade
			Criteria criteria = session.createCriteria(Cidade.class);
			//faz o where estado.id = estadoId
			criteria.add(Restrictions.eq("estado.id",estadoId));
			//apenas ordena a cidade por nome
			criteria.addOrder(Order.asc("nome"));
			@SuppressWarnings("unchecked")
			//pega a lista de cidades de uma determinada estado.
			List<Cidade> cidades = criteria.list();
			//retorna a lista de cidades.
			return cidades;
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
