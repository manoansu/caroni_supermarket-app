package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.CidadeDAO;
import pt.amane.caroni_supermarket.dao.EstadoDAO;
import pt.amane.caroni_supermarket.dao.PessoaDAO;
import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Estado;
import pt.amane.caroni_supermarket.domain.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private Estado estado;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");
			cidades = new ArrayList<Cidade>();
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
			CidadeDAO cidadeDAO = new CidadeDAO();
			pessoaDAO.merge(pessoa);

			pessoa = new Pessoa();
			estado = new Estado();

			pessoas = pessoaDAO.findAll("nome");
			cidades = cidadeDAO.findAll("nome");
			Messages.addGlobalError("Successfully saved person!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved person!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		try {
			pessoa = (Pessoa) event.getComponent().getAttributes().get("pessoaSelecionado");
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");
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
			pessoa = new Pessoa();
			pessoas = pessoaDAO.findAll("nome");
			Messages.addGlobalInfo("Successfully removed person!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed person!");
			e.printStackTrace();
		}
	}

	public void popularCidade() {
		
		try {
			if (estado != null) { // verica caso o utilizador nao seleciona nenhum estado na tela..

//				Client cliente = ClientBuilder.newClient(); // cria cliente paara usar o web service..
//				WebTarget serverpath = cliente.target("http://localhost:8080/caroni_supermarket/rest/cidade/{estadoId}")
//						.resolveTemplate("estadoId", estado.getId()); // passa o caminho  de URl para conectar co web service..
//				String json = serverpath.request().get(String.class); // serve para disparar uma requisição dos tipos
//																		// desejado(GET,PUT,POST,Delete)
//
//				Gson gson = new Gson(); // usa o API GSON para converter a lista para json..
//				Cidade[] vetor = gson.fromJson(json, Cidade[].class);
//				cidades = Arrays.asList(vetor); // converte o vetor no arrayLista de Java..

				 CidadeDAO cidadeDAO = new CidadeDAO();
				 //apenas busca a cidade por Id..
				 cidades = cidadeDAO.buscarPorEstado(estado.getId());
			} else {
				cidades = new ArrayList<Cidade>(); // caso o estado seja nulo ele so instancia uma lista para nao trazer
													// nada..
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to filtrar city!!");
			e.printStackTrace();
		}
	}
		

}
