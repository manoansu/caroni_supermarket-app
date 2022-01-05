package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.FuncionarioDAO;
import pt.amane.caroni_supermarket.domain.Funcionario;

@Path(value = "/funcionario")
public class FuncionariService {
	
	@GET
	public String findAll() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			List<Funcionario> funcionarios = funcionarioDAO.findAll("dataAdmissao");
			Gson gson = new Gson();
			String funcionarioList = gson.toJson(funcionarios);
			return funcionarioList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed employee!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = funcionarioDAO.findById(id);
			Gson gson = new Gson();
			String funcionarioId = gson.toJson(funcionario);
			return funcionarioId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String funcionarioJson) {
		Gson gson = new Gson();
		try {
			Funcionario funcionario = gson.fromJson(funcionarioJson, Funcionario.class);
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionario);
			String funcionarioSalvo = gson.toJson(funcionario);
			return funcionarioSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved employee!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String funcionarioJson) {
		Gson gson = new Gson();
		try {
		Funcionario funcionario = gson.fromJson(funcionarioJson, Funcionario.class);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.update(funcionario);
		String funcionarioEdited = gson.toJson(funcionario);
		return funcionarioEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited employee!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = funcionarioDAO.findById(id);
			Gson gson = new Gson();
			String funcionarioDeleted = gson.toJson(funcionario);
			return funcionarioDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to Edited employee!");
			e.printStackTrace();
		}
		return null;
	}

}
