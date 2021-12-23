package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.FabricanteDAO;
import pt.amane.caroni_supermarket.domain.Fabricante;

/**
 *  http://localhost:8080/caroni_supermarket/rest/fabricante
 * @author manoansu
 *
 */
@Path(value = "/fabricante")
public class FabricanteService {
	
	//http://localhost:8080/caroni_supermarket/rest/fabricante
	@GET
	public String findAll() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Fabricante> fabricantes = fabricanteDAO.findAll("descricao");
			
			Gson gson = new Gson();
			String json = gson.toJson(fabricantes);
			return json;
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed factory!");
			e.printStackTrace();
		}
		return null;
	}
	
	//http://localhost:8080/caroni_supermarket/rest/fabricante/2
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.findById(id);
			
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);
			return json;
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to get Id factory!");
			e.printStackTrace();
		}
		return null;
	}
	
	//http://localhost:8080/caroni_supermarket/rest/fabricante
	@POST
	public String save(String fabricanteJson) {
		Gson gson = new Gson();
		try {
			Fabricante fabricante = gson.fromJson(fabricanteJson, Fabricante.class);
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			System.out.println("Successfully saved factory!");
			String fabricanteSalvo = gson.toJson(fabricante);
			return fabricanteSalvo;
 		} catch (RuntimeException e) {
			System.out.println("Error trying to saved factory!");
			e.printStackTrace();
		}
		return null;
	}
	
	//http://localhost:8080/caroni_supermarket/rest/fabricante
	@PUT
	public String edit(String fabricanteJson) {
		Gson gson = new Gson();
		try {
			Fabricante fabricante = gson.fromJson(fabricanteJson, Fabricante.class);
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			System.out.println("Successfully edited factory!");
			String fabricanteEditado = gson.toJson(fabricante);
			return fabricanteEditado;
 		} catch (RuntimeException e) {
			System.out.println("Error trying to edited factory!");
			e.printStackTrace();
		}
		return null;
	}
	
	//http://localhost:8080/caroni_supermarket/rest/fabricante/2
	@DELETE
	@Path(value = "{id}")
	public String edit(@PathParam(value = "id") Long id) {
		Gson gson = new Gson();
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.findById(id);
			fabricanteDAO.delete(fabricante);
			System.out.println("Successfully deleted factory!");
			String fabricanteDeletado = gson.toJson(fabricante);
			return fabricanteDeletado;
 		} catch (RuntimeException e) {
			System.out.println("Error trying to deleted factory!");
			e.printStackTrace();
		}
		return null;
	}

}
