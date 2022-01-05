package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.MovimentacaoDAO;
import pt.amane.caroni_supermarket.domain.Movimentacao;

@Path(value = "/movimentacao")
public class MovimentacaoService {
	
	@GET
	public String findAll() {
		try {
			MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
			List<Movimentacao> movimentacaos = movimentacaoDao.findAll("precoTotal");
			Gson gson = new Gson();
			String movimentacaoList = gson.toJson(movimentacaos);
			return movimentacaoList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed moviment!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
			Movimentacao movimentacao = movimentacaoDao.findById(id);
			Gson gson = new Gson();
			String movimentacaoId = gson.toJson(movimentacao);
			return movimentacaoId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String movimentacaoJson) {
		Gson gson = new Gson();
		try {
			Movimentacao movimentacao = gson.fromJson(movimentacaoJson, Movimentacao.class);
			MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
			movimentacaoDao.salvar(movimentacao);
			String movimentacaoSalvo = gson.toJson(movimentacao);
			return movimentacaoSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved moviment!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String movimentacaoJson) {
		Gson gson = new Gson();
		try {
		Movimentacao movimentacao = gson.fromJson(movimentacaoJson, Movimentacao.class);
		MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
		movimentacaoDao.update(movimentacao);
		String movimentacaoEdited = gson.toJson(movimentacao);
		return movimentacaoEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited moviment!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
			Movimentacao movimentacao = movimentacaoDao.findById(id);
			Gson gson = new Gson();
			String movimentacaoDeleted = gson.toJson(movimentacao);
			return movimentacaoDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed moviment!");
			e.printStackTrace();
		}
		return null;	
	}

}
