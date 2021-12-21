package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.FuncionarioDAO;
import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.domain.Funcionario;
import pt.amane.caroni_supermarket.domain.Pessoa;

@ViewScoped
@ManagedBean
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void novo() {
		try {
			funcionario = new Funcionario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated employeer!");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.findAll("dataAdmissao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed employeer!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			novo();
			funcionarios = funcionarioDAO.findAll("dataAdmissao");
			Messages.addGlobalInfo("Successfully saved employeer!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved employeer!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			funcionario = (Funcionario) event.getComponent().getAttributes().get("funcionarioSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited employeer!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		funcionario = (Funcionario) event.getComponent().getAttributes().get("funcionarioSelecionado");
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.delete(funcionario);
			novo();
			funcionarios = funcionarioDAO.findAll("dataAdmissao");
			Messages.addGlobalInfo("Successfully removed employeer!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed employeer!");
			e.printStackTrace();
		}
	}
}
