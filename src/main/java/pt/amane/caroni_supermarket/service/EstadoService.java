package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.EstadoDAO;
import pt.amane.caroni_supermarket.domain.Estado;

@Path(value = "/estado")
public class EstadoService {
	
	@GET
	public String findAll() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			List<Estado> estados = estadoDAO.findAll("nome");
			Gson gson = new Gson();
			String estadoList = gson.toJson(estados);
			return estadoList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed state!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String fidById(@PathParam(value = "id") Long id) {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.findById(id);
			Gson gson = new Gson();
			String estadoId = gson.toJson(estado);
			return estadoId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded Id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String estadoJson) {
		Gson gson = new Gson();
		try {
			Estado estado = gson.fromJson(estadoJson, Estado.class);
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);
			String estadoSalvo = gson.toJson(estado);
			return estadoSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved state!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String estadoJson) {
		Gson gson = new Gson();
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = gson.fromJson(estadoJson, Estado.class);
			estadoDAO.update(estado);
			String estadoEdit = gson.toJson(estado);
			return estadoEdit;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited state!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.findById(id);
			estadoDAO.delete(estado);
			Gson gson = new Gson();
			String estadodeleted = gson.toJson(estado);
			return estadodeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed state!");
			e.printStackTrace();
		}
		return null;
	}

}
