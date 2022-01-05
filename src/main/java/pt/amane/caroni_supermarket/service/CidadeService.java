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

import pt.amane.caroni_supermarket.dao.CidadeDAO;
import pt.amane.caroni_supermarket.domain.Cidade;

@Path(value = "/cidade")
public class CidadeService {
	
	@GET
	public String findAll() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			List<Cidade> cidades = cidadeDAO.findAll("nome");
			Gson gson = new Gson();
			String cidadejson = gson.toJson(cidades);
			return cidadejson;
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed City!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String fidById(@PathParam(value = "id") Long id) {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.findById(id);
			Gson gson = new Gson();
			String cidadeJson = gson.toJson(cidade);
			return cidadeJson;
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to finded Id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String cidadeJson) {
		Gson gson = new Gson();
		try {
			Cidade cidade = gson.fromJson(cidadeJson, Cidade.class);
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);
			String cidadeSalvo = gson.toJson(cidade);
			return cidadeSalvo;
			} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved city!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String cidadeJson) {
		Gson gson = new Gson();
		try {
			Cidade cidade = gson.fromJson(cidadeJson, Cidade.class);
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.update(cidade);
			String cidadeEdit = gson.toJson(cidade);
			return cidadeEdit;
			} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited city!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String Delete(@PathParam(value = "id") Long id) {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.findById(id);
			cidadeDAO.delete(cidade);
			Gson gson = new Gson();
			String cidadeJson = gson.toJson(cidade);
			return cidadeJson;
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to deleted City!");
			e.printStackTrace();
		}
		return null;
	}

}
