package pt.amane.caroni_supermarket.dao;

import java.lang.reflect.ParameterizedType;

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

	

}
