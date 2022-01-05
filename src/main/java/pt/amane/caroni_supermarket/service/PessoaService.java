package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.domain.Pessoa;

@Path(value = "/pessoa")
public class PessoaService {
	
	@GET
	public String findAll() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Pessoa> pessoas = pessoaDAO.findAll("nome");
			Gson gson = new Gson();
			String pessoaList = gson.toJson(pessoas);
			return pessoaList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed person!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String fidById(@PathParam(value = "id") Long id) {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.findById(id);
			Gson gson = new Gson();
			String pessoaId = gson.toJson(pessoa);
			return pessoaId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded Id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String pessoaJson) {
		Gson gson = new Gson();
		try {
			Pessoa pessoa = gson.fromJson(pessoaJson, Pessoa.class);
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);
			String pessoaSalvo = gson.toJson(pessoa);
			return pessoaSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved person!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String pessoaJson) {
		Gson gson = new Gson();
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = gson.fromJson(pessoaJson, Pessoa.class);
			pessoaDAO.update(pessoa);
			String pessoaEdit = gson.toJson(pessoa);
			return pessoaEdit;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited person!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.findById(id);
			pessoaDAO.delete(pessoa);
			Gson gson = new Gson();
			String pessoadeleted = gson.toJson(pessoa);
			return pessoadeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to removed person!");
			e.printStackTrace();
		}
		return null;
	}

}

