package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.ItemVendaDAO;
import pt.amane.caroni_supermarket.dao.ProdutoDAO;
import pt.amane.caroni_supermarket.dao.VendaDAO;
import pt.amane.caroni_supermarket.domain.ItemVenda;
import pt.amane.caroni_supermarket.domain.Produto;
import pt.amane.caroni_supermarket.domain.Venda;

@ViewScoped
@ManagedBean
public class ItemVendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ItemVenda itemVenda;
	private List<ItemVenda> itemVendas;
	private List<Produto> produtos;
	private List<Venda> vendas;

	public ItemVenda getItemVenda() {
		
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public List<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void novo() {
		try {
			itemVenda = new ItemVenda();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.findAll("descricao");
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.findAll("precoTotal");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated itevenda!");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itemVendas = itemVendaDAO.findAll("precoParcial");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to lister itemVenda!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itemVendaDAO.merge(itemVenda);
			novo();
			itemVendas = itemVendaDAO.findAll("precoParcial");
			Messages.addGlobalInfo("Successfully saved iemVenda!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved iemVenda!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			itemVenda = (ItemVenda) event.getComponent().getAttributes().get("itemVendaSelecionado");
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.findAll("descricao");
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.findAll("precoTotla");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited iemVenda!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		itemVenda = (ItemVenda) event.getComponent().getAttributes().get("itemVendaSelecionado");
		try {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itemVendaDAO.delete(itemVenda);
			novo();
			itemVendas = itemVendaDAO.findAll("precoParcial");
			Messages.addGlobalInfo("Successfuly removed iemVenda!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed iemVenda!");
			e.printStackTrace();
		}
	}
}
