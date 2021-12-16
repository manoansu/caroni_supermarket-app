package pt.amane.caroni_supermarket.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Fabricante;
import pt.amane.caroni_supermarket.domain.Produto;

public class ProdutoDAOTest {
	
	@SuppressWarnings({ "removal" })
	@Test
	@Ignore
	public void salvar() {
		
		FabricanteDAO fabricantDao = new FabricanteDAO();
		Fabricante fabricante = fabricantDao.findById(new Long("3"));
		
		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto salvo com sucesso!!");
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> list = produtoDAO.findAll();
		
		for(Produto produto: list) {
			System.out.println("Id: " + produto.getId()  + " Descrição: " + produto.getDescricao() + " Preço: " + produto.getPreco() +
					"Quantidade: " + produto.getQuantidade() + "\nId de Fabricante: " + 
					produto.getFabricante().getId() + "Descrição de Fabricante" + produto.getFabricante().getDescricao());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Long codigo = 5L;
		Produto produto = produtoDAO.findById(codigo);
		
		if (produto == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
			System.out.println("registro encontrado");
			System.out.println("Id: " + produto.getId()  + " Descrição: " + produto.getDescricao() + " Preço: " + produto.getPreco() +
					"Quantidade: " + produto.getQuantidade() + "\nId de Fabricante: " + 
					produto.getFabricante().getId() + "Descrição de Fabricante" + produto.getFabricante().getDescricao());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	//@Ignore
	public void excluir() {
		
		ProdutoDAO produtoDao = new ProdutoDAO();
		Long codigo = 8L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.findById(codigo);
		
		Produto produto = new Produto();
		produto.setFabricante(fabricante);
		
		System.out.println("Id: " + produto.getId()  + " Descrição: " + produto.getDescricao() + " Preço: " + produto.getPreco() +
				"Quantidade: " + produto.getQuantidade() + "\nId de Fabricante: " + 
				produto.getFabricante().getId() + "Descrição de Fabricante" + produto.getFabricante().getDescricao());
		
		if (produto == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
			System.out.println("registro encontrado\n");
			System.out.println("Id: " + produto.getId()  + " Descrição: " + produto.getDescricao() + " Preço: " + produto.getPreco() +
					"Quantidade: " + produto.getQuantidade() + "\nId de Fabricante: " + 
					produto.getFabricante().getId() + "Descrição de Fabricante" + produto.getFabricante().getDescricao());
			produtoDao.delete(produto);
			System.out.println("Fabricante excluido com sucesso!");
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void editar() {
		
		ProdutoDAO produtoDao = new ProdutoDAO();
		Long codigo = 8L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.findById(codigo);
		
		Produto produto = new Produto();
		produto.setFabricante(fabricante);
		
		produto.setPreco(new BigDecimal("20.15"));
		
		if (produto == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
			System.out.println("registro encontrado\n");
			System.out.println("Id: " + produto.getId()  + " Descrição: " + produto.getDescricao() + " Preço: " + produto.getPreco() +
					"Quantidade: " + produto.getQuantidade() + "\nId de Fabricante: " + 
					produto.getFabricante().getId() + "Descrição de Fabricante" + produto.getFabricante().getDescricao());
			
			produtoDao.update(produto);
			System.out.println("Fabricante editado com sucesso!");
		}
		
	}

}
