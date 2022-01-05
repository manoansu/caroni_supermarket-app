package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.CaixaDAO;
import pt.amane.caroni_supermarket.domain.Caixa;

@Path(value = "/caixa")
public class CaixaService {
	
	@GET
	public String findAll() {
		try {
			CaixaDAO caixaDao = new CaixaDAO();
			List<Caixa> caixas = caixaDao.findAll("precoTotal");
			Gson gson = new Gson();
			String caixaList = gson.toJson(caixas);
			return caixaList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed cash!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			CaixaDAO caixaDao = new CaixaDAO();
			Caixa caixa = caixaDao.findById(id);
			Gson gson = new Gson();
			String caixaId = gson.toJson(caixa);
			return caixaId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String caixaJson) {
		Gson gson = new Gson();
		try {
			Caixa caixa = gson.fromJson(caixaJson, Caixa.class);
			CaixaDAO caixaDao = new CaixaDAO();
			caixaDao.salvar(caixa);
			String caixaSalvo = gson.toJson(caixa);
			return caixaSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved cash!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String caixaJson) {
		Gson gson = new Gson();
		try {
		Caixa caixa = gson.fromJson(caixaJson, Caixa.class);
		CaixaDAO caixaDao = new CaixaDAO();
		caixaDao.update(caixa);
		String caixaEdited = gson.toJson(caixa);
		return caixaEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited cash!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			CaixaDAO caixaDao = new CaixaDAO();
			Caixa caixa = caixaDao.findById(id);
			Gson gson = new Gson();
			String caixaDeleted = gson.toJson(caixa);
			return caixaDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed cash!");
			e.printStackTrace();
		}
		return null;
	}

}
