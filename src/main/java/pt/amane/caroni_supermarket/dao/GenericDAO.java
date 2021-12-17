package pt.amane.caroni_supermarket.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pt.amane.caroni_supermarket.util.HibernateUtil;

/**
 * 
 * @author ansumane.mane
 *
 * @param <T> o parametro T é criado por utilizador para mapear o DAO generico
 *            onde o T, pode ser subustituido para a classe que implementa essa
 *            classe. por exemplo: se queremos salvar usuario no lugar de T vai
 *            ser objecto usuario a ser substituido. E tambem podemos colocar
 *            qualque nome por EX: T, Entidade ou pode até passar os dois
 *            parametro: public class GenericDAO<T, id>
 */
public class GenericDAO<T> {

	private Class<T> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void salvar(T entity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	public List<T> findAll() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(classe);
			@SuppressWarnings("unchecked")
			List<T> obj = criteria.list();
			return obj;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public List<T> findAll(String campoOrdenacao) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(classe);
			criteria.addOrder(Order.asc(campoOrdenacao));
			@SuppressWarnings("unchecked")
			List<T> obj = criteria.list();
			return obj;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(classe);
			criteria.add(Restrictions.idEq(id));
			T entity = (T) criteria.uniqueResult();
			;
			return entity;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(T entity) {

		Session session = HibernateUtil.getSessionFactory().openSession(); // abre a sessao
		Transaction transaction = null; // cria atransasao
		try {
			transaction = session.beginTransaction(); // inicia a transação
			session.delete(entity); // salva o objeto na base de dados.
			transaction.commit();

		} catch (RuntimeException e) {
			if (transaction != null) { // verifica o se a transação ja capturou alguma coisa sem sucesso na BD.
				transaction.rollback(); // desfaz o valor que estava ser salvo
			}
			throw e;
		} finally {
			session.close();// apenas fecha a sessão..
		}
	}

	public void update(T entity) {

		Session session = HibernateUtil.getSessionFactory().openSession(); // abre a sessao
		Transaction transaction = null; // cria atransasao
		try {
			transaction = session.beginTransaction(); // inicia a transação
			session.update(entity); // salva o objeto na base de dados.
			transaction.commit();

		} catch (RuntimeException e) {
			if (transaction != null) { // verifica o se a transação ja capturou alguma coisa sem sucesso na BD.
				transaction.rollback(); // desfaz o valor que estava ser salvo
			}
			throw e;
		} finally {
			session.close();// apenas fecha a sessão..
		}
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {

		Session session = HibernateUtil.getSessionFactory().openSession(); // abre a sessao
		Transaction transaction = null; // cria atransasao
		try {
			transaction = session.beginTransaction(); // inicia a transação
			T entytreturn = (T) session.merge(entity); // salva o objeto na base de dados.
			transaction.commit();
			return entytreturn;

		} catch (RuntimeException e) {
			if (transaction != null) { // verifica o se a transação ja capturou alguma coisa sem sucesso na BD.
				transaction.rollback(); // desfaz o valor que estava ser salvo
			}
			throw e;
		} finally {
			session.close();// apenas fecha a sessão..
		}
	}

}
