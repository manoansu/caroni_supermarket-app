package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pt.amane.caroni_supermarket.domain.ItemVenda;
import pt.amane.caroni_supermarket.domain.Produto;
import pt.amane.caroni_supermarket.domain.Venda;
import pt.amane.caroni_supermarket.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{
	
	@SuppressWarnings("deprecation")
	public void salvar(Venda venda, List<ItemVenda> itemVendas) {

		Session session = HibernateUtil.getSessionFactory().openSession(); // abre a sessao
		Transaction transaction = null; // cria atransasao
		try {
			transaction = session.beginTransaction(); // inicia a transação
			 session.save(venda);// salva o objeto na base de dados.
			 
			 for(int i = 0; i < itemVendas.size(); i++) {
				 ItemVenda itemVenda = itemVendas.get(i);
				 itemVenda.setVenda(venda);
				 
				 session.save(itemVenda);
				 
				 Produto produto = itemVenda.getProduto();
				 int qtd = produto.getQuantidade() - itemVenda.getQuantidade();
				 if (qtd > 0) { // verifica se a qtd em estoque de produto é suficiente
					 produto.setQuantidade(new Short((qtd) + "")); // apenas atualiza..
					 session.update(produto);
				 }else {//caso a qtd é insuficiente gera um exceptio que o valor é insuficiente..
					throw new RuntimeException("Quantidade insuficiente em estoque!");
				}
				
			 }
			
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

}
