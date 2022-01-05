package pt.amane.caroni_supermarket.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.UsuarioDAO;
import pt.amane.caroni_supermarket.domain.Usuario;

@Path(value = "/usuario")
public class UsuarioService {
	
	@GET
	public String findAll() {
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			List<Usuario> usuarios = usuarioDao.findAll("tipo");
			Gson gson = new Gson();
			String usuarioList = gson.toJson(usuarios);
			return usuarioList;
		} catch (RuntimeException e) {
			System.out.println("Error trying to listed user!");
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path(value = "{id}")
	public String findById(@PathParam(value = "id") Long id) {
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			Usuario usuario = usuarioDao.findById(id);
			Gson gson = new Gson();
			String usuarioId = gson.toJson(usuario);
			return usuarioId;
		} catch (RuntimeException e) {
			System.out.println("Error trying to finded id!");
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	public String save(String usuarioJson) {
		Gson gson = new Gson();
		try {
			Usuario usuario = gson.fromJson(usuarioJson, Usuario.class);
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.salvar(usuario);
			String usuarioSalvo = gson.toJson(usuario);
			return usuarioSalvo;
		} catch (RuntimeException e) {
			System.out.println("Error trying to saved user!");
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	public String edit(String usuarioJson) {
		Gson gson = new Gson();
		try {
		Usuario usuario = gson.fromJson(usuarioJson, Usuario.class);
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.update(usuario);
		String usuarioEdited = gson.toJson(usuario);
		return usuarioEdited;
		} catch (RuntimeException e) {
			System.out.println("Error trying to edited user!");
			e.printStackTrace();
		}
		return null;
	}
	
	@DELETE
	@Path(value = "{id}")
	public String delete(@PathParam(value = "id") Long id) {
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			Usuario usuario = usuarioDao.findById(id);
			Gson gson = new Gson();
			String usuarioDeleted = gson.toJson(usuario);
			return usuarioDeleted;
		} catch (RuntimeException e) {
			System.out.println("Error trying to Edited user!");
			e.printStackTrace();
		}
		return null;
	}

}
