package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.ProdutoDAO;
import pt.amane.caroni_supermarket.domain.Produto;

@Path(value = "/produto")
public class ProdutoService {
	
	@GET
	public String findAll() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			List<Produto> produtos = produtoDAO.findAll("descricao");
			Gson gson = new Gson();
			String produtoList = gson.toJson(produtos);
			return produtoList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed Product!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = produtoDAO.findById(id);
			Gson gson = new Gson();
			String produtoId = gson.toJson(produto);
			return produtoId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String produtoJson) {
		Gson gson = new Gson();
		try {
			Produto produto = gson.fromJson(produtoJson, Produto.class);
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);
			String produtoSalvo = gson.toJson(produto);
			return produtoSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved product!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String produtoJson) {
		Gson gson = new Gson();
		try {
		Produto produto = gson.fromJson(produtoJson, Produto.class);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.update(produto);
		String produtoEdited = gson.toJson(produto);
		return produtoEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited product!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = produtoDAO.findById(id);
			Gson gson = new Gson();
			String produtoDeleted = gson.toJson(produto);
			return produtoDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to Edited produto!");
			e.printStackTrace();
		}
		return null;
	}

}
