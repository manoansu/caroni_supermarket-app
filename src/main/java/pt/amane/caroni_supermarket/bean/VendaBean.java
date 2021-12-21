package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.ClienteDAO;
import pt.amane.caroni_supermarket.dao.FuncionarioDAO;
import pt.amane.caroni_supermarket.dao.VendaDAO;
import pt.amane.caroni_supermarket.domain.Cliente;
import pt.amane.caroni_supermarket.domain.Funcionario;
import pt.amane.caroni_supermarket.domain.Venda;

@ViewScoped
@ManagedBean
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venda venda;
	private List<Venda> vendas;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void novo() {
		try {
			venda = new Venda();
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.findAll("dataCadastro");
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.findAll("dataAdmissao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated sale!");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.findAll("precoTotal");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed sale!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.merge(venda);
			novo();
			vendas = vendaDAO.findAll("precoTotal");
			Messages.addGlobalInfo("Successfully saved sale!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved sale!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			venda = (Venda) event.getComponent().getAttributes().get("vendaSelecionado");
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.findAll("dataCadastro");
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.findAll("dataAdmissao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited sale!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		venda = (Venda) event.getComponent().getAttributes().get("vendaSelecionado");
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.delete(venda);
			novo();
			vendas = vendaDAO.findAll("prcoTotal");
			Messages.addGlobalInfo("Successfully removed sale!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed sale!");
			e.printStackTrace();
		}
	}

}
