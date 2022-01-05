package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.ItemVendaDAO;
import pt.amane.caroni_supermarket.domain.ItemVenda;

@Path(value = "/itemVenda")
public class ItemVendaService {
	
	@GET
	public String findAll() {
		try {
			ItemVendaDAO itemVendaDao = new ItemVendaDAO();
			List<ItemVenda> itemVendas = itemVendaDao.findAll("precoParcial");
			Gson gson = new Gson();
			String itemVendaList = gson.toJson(itemVendas);
			return itemVendaList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed itemSale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			ItemVendaDAO itemVendaDao = new ItemVendaDAO();
			ItemVenda itemVenda = itemVendaDao.findById(id);
			Gson gson = new Gson();
			String itemVendaId = gson.toJson(itemVenda);
			return itemVendaId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String itemVendaJson) {
		Gson gson = new Gson();
		try {
			ItemVenda itemVenda = gson.fromJson(itemVendaJson, ItemVenda.class);
			ItemVendaDAO itemVendaDao = new ItemVendaDAO();
			itemVendaDao.salvar(itemVenda);
			String itemVendaSalvo = gson.toJson(itemVenda);
			return itemVendaSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved itemSale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String itemVendaJson) {
		Gson gson = new Gson();
		try {
		ItemVenda itemVenda = gson.fromJson(itemVendaJson, ItemVenda.class);
		ItemVendaDAO itemVendaDao = new ItemVendaDAO();
		itemVendaDao.update(itemVenda);
		String itemVendaEdited = gson.toJson(itemVenda);
		return itemVendaEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited itemSale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			ItemVendaDAO itemVendaDao = new ItemVendaDAO();
			ItemVenda itemVenda = itemVendaDao.findById(id);
			Gson gson = new Gson();
			String itemVendaDeleted = gson.toJson(itemVenda);
			return itemVendaDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed itemSale!");
			e.printStackTrace();
		}
		return null;
	}

}
