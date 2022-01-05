package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.VendaDAO;
import pt.amane.caroni_supermarket.domain.Venda;

@Path(value = "/venda")
public class VendaService {
	
	@GET
	public String findAll() {
		try {
			VendaDAO vendaDao = new VendaDAO();
			List<Venda> vendas = vendaDao.findAll("precoTotal");
			Gson gson = new Gson();
			String vendaList = gson.toJson(vendas);
			return vendaList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed sale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			VendaDAO vendaDao = new VendaDAO();
			Venda venda = vendaDao.findById(id);
			Gson gson = new Gson();
			String vendaId = gson.toJson(venda);
			return vendaId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String vendaJson) {
		Gson gson = new Gson();
		try {
			Venda venda = gson.fromJson(vendaJson, Venda.class);
			VendaDAO vendaDao = new VendaDAO();
			vendaDao.salvar(venda);
			String vendaSalvo = gson.toJson(venda);
			return vendaSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved sale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String vendaJson) {
		Gson gson = new Gson();
		try {
		Venda venda = gson.fromJson(vendaJson, Venda.class);
		VendaDAO vendaDao = new VendaDAO();
		vendaDao.update(venda);
		String vendaEdited = gson.toJson(venda);
		return vendaEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited sale!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			VendaDAO vendaDao = new VendaDAO();
			Venda venda = vendaDao.findById(id);
			Gson gson = new Gson();
			String vendaDeleted = gson.toJson(venda);
			return vendaDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to Edited sale!");
			e.printStackTrace();
		}
		return null;
	}

}
