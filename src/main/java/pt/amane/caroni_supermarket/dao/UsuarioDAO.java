package pt.amane.caroni_supermarket.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pt.amane.caroni_supermarket.domain.Usuario;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	@SuppressWarnings("deprecation")
	public Usuario autenticar(String cpf, String senha) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = session.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p"); //cria o alias para ser chamada..
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			
			SimpleHash hash = new SimpleHash("md5", senha); // criptgrafa a senha digitada..
			consulta.add(Restrictions.eq("senha", hash.toHex())); // apenas finaliza a criptografia..
			
			Usuario usuario = (Usuario) consulta.uniqueResult();
			return usuario;
			
		} catch (RuntimeException e) {
			throw e;
		}finally {
			session.close();
		}
	}
}
