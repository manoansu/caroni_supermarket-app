package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.CidadeDAO;
import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to generated person!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed person!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			novo();
			pessoas = pessoaDAO.findAll("nome");
			Messages.addGlobalError("Successfully saved person!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved person!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		try {
			pessoa = (Pessoa) event.getComponent().getAttributes().get("pessoaSelecionado");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.findAll("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited person!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent event) {
		pessoa = (Pessoa) event.getComponent().getAttributes().get("pessoaSelecionado");
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.delete(pessoa);
			novo();
			pessoas = pessoaDAO.findAll("nome");
			Messages.addGlobalInfo("Successfully removed person!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed person!");
			e.printStackTrace();
		}
	}

}
