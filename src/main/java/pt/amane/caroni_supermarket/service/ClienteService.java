package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.ClienteDAO;
import pt.amane.caroni_supermarket.domain.Cliente;

@Path(value = "/cliente")
public class ClienteService {
	
	@GET
	public String findAll() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<Cliente> clientes = clienteDAO.findAll("dataCadastro");
			Gson gson = new Gson();
			String clienteList = gson.toJson(clientes);
			return clienteList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed client!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String fidById(@PathParam(value = "id") Long id) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.findById(id);
			Gson gson = new Gson();
			String clienteId = gson.toJson(cliente);
			return clienteId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded Id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String clienteJson) {
		Gson gson = new Gson();
		try {
			Cliente cliente = gson.fromJson(clienteJson, Cliente.class);
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
			String clienteSalvo = gson.toJson(cliente);
			return clienteSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved client!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String clienteJson) {
		Gson gson = new Gson();
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = gson.fromJson(clienteJson, Cliente.class);
			clienteDAO.update(cliente);
			String clienteEdit = gson.toJson(cliente);
			return clienteEdit;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited client!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.findById(id);
			clienteDAO.delete(cliente);
			Gson gson = new Gson();
			String clientedeleted = gson.toJson(cliente);
			return clientedeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed client!");
			e.printStackTrace();
		}
		return null;
	}

}

