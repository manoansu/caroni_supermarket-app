package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.ClienteDAO;
import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.domain.Cliente;
import pt.amane.caroni_supermarket.domain.Pessoa;

@ViewScoped
@ManagedBean
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void novo() {
		try {
			cliente = new Cliente();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated client!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.findAll("dataCadastro");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed client!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			novo();
			clientes = clienteDAO.findAll("dataCadastro");
			Messages.addGlobalInfo("Successfully seved client!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to seved client!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		try {
			cliente = (Cliente) event.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited client!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent event) {
		cliente = (Cliente) event.getComponent().getAttributes().get("clienteSelecionado");
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.delete(cliente);
			novo();
			clientes = clienteDAO.findAll("dataCadastro");
			Messages.addGlobalInfo("Successfully removed client!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed client!");
			e.printStackTrace();
		}
	}

}
