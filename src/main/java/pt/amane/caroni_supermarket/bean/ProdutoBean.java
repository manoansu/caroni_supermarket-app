package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.FabricanteDAO;
import pt.amane.caroni_supermarket.dao.ProdutoDAO;
import pt.amane.caroni_supermarket.domain.Fabricante;
import pt.amane.caroni_supermarket.domain.Produto;

@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void novo() {
		try {
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to selected Product!");
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed Product!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			novo();
			produtos = produtoDAO.findAll("descricao");
			Messages.addGlobalInfo("Successfully saved Product!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved Product!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited Product!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.delete(produto);
			novo();
			produtos = produtoDAO.findAll("descricao");
			Messages.addGlobalInfo("Successfuly removed Product!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed Product!");
			e.printStackTrace();
		}
	}

}
