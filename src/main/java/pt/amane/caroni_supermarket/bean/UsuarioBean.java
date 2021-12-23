package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.dao.UsuarioDAO;
import pt.amane.caroni_supermarket.domain.Pessoa;
import pt.amane.caroni_supermarket.domain.Usuario;

@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void novo() {
		try {
			usuario = new Usuario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated user!");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.findAll("tipoUsuario");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed user!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			novo();
			usuarios = usuarioDAO.findAll("tipoUsuario");
			Messages.addGlobalInfo("Successfullysaved user!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved user!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			usuario = (Usuario) event.getComponent().getAttributes().get("usuarioSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.findAll("tipoUsuario");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited user!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		usuario = (Usuario) event.getComponent().getAttributes().get("usuarioSelecionado");
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.delete(usuario);
			novo();
			usuarios = usuarioDAO.findAll("tipoUsuario");
			Messages.addGlobalInfo("Successfully removed user!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed user!");
			e.printStackTrace();
		}
	}
}
