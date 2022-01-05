package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import pt.amane.caroni_supermarket.dao.CidadeDAO;
import pt.amane.caroni_supermarket.dao.EstadoDAO;
import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Estado;

@ViewScoped
@ManagedBean
public class CidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void novo() {
		try {
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");			
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to generated city!");
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidades = cidadeDAO.findAll("nome");
			
			//fazer a requisiçõa via restfull chamando cidadeService..
			// cria o client para requisitar o path de cidadeService
			Client client = ClientBuilder.newClient();
			
			//Pega o caminho(url) para chamar o serviço..
			WebTarget serverPath = client.target("http://localhost:8080/caroni_supermarket/rest/cidade/");
			
			//Dispara uma requisição do tipo desejado por ex:(get, post,put, ou delete..) 
			//para trazer o dado em JSON, retornando em string.
			String cidadeJson = serverPath.request().get(String.class);
			
			Gson gson = new Gson();
			
			//pega o resultado em String e converte os dados num vetor de string
			Cidade[] vetorCidades = gson.fromJson(cidadeJson, Cidade[].class);
			
			//converte o vetor de string em um ArrayList
			cidades = Arrays.asList(vetorCidades);
			
		} catch (Exception e) {
			Messages.addGlobalError("Error trying listed city!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidadeDAO.merge(cidade);
//			novo();
//			cidades = cidadeDAO.findAll("nome");
			
			
			//fazer a requisiçõa via restfull chamando cidadeService..
			// cria o client para requisitar o path de cidadeService
			Client client = ClientBuilder.newClient();
			
			WebTarget restServerPath = client.target("http://localhost:8080/caroni_supermarket/rest/cidade/");
			
			Gson gson = new Gson();
			
			//converte os dados que vai ser salvo para json..
			String cidadeJson = gson.toJson(cidade);
			
			//Passa o dado Json na requisiçao post para salvar na BD.
			restServerPath.request().post(Entity.json(cidadeJson));
			
			//apenas instancia o cidade para limpar a tela..
			novo();
			
			//apenas retorna o dados em string para listar/atualizar na tela
			cidadeJson = restServerPath.request().get(String.class);
			
			//converte os dados em vetor de string
			Cidade[] vetorCidades = gson.fromJson(cidadeJson, Cidade[].class);
			
			// converte o vetor de string em um arrayList.
			cidades = Arrays.asList(vetorCidades);
					
			Messages.addGlobalInfo("Successfully saved city!");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to saved city!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		
		try {
			cidade = (Cidade) event.getComponent().getAttributes().get("cidadeSelecionado");
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to selected a city!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		cidade = (Cidade) event.getComponent().getAttributes().get("cidadeSelecionado");
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.delete(cidade);
			novo();
			cidades = cidadeDAO.findAll("nome");
			Messages.addGlobalError("Successfully removed city!");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to removed city!");
			e.printStackTrace();
		}
	}

}
