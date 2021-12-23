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

import pt.amane.caroni_supermarket.dao.FabricanteDAO;
import pt.amane.caroni_supermarket.domain.Fabricante;

@ViewScoped
@ManagedBean
public class FabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fabricante fabricante;

	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	@PostConstruct
	public void Listar() {
		try {
			// Listar via DAO directo e cria muito acopolamento entre Frontend e Backend
//			FabricanteDAO fabricanteDAO = new FabricanteDAO();
//			fabricantes = fabricanteDAO.findAll("descricao");
			
			//fazer a requisiçõa via restfull chamando fabricanteService..
			// cria o client para requisitar o path de fabricanteService
			Client client = ClientBuilder.newClient();
			
			//Pega o caminho(url) para chamar o serviço..
			WebTarget restServicePath = client.target("http://localhost:8080/caroni_supermarket/rest/fabricante/");
			
			//Dispara uma requisição do tipo desejado por ex:(get, post,put, ou delete..) 
			//para trazer o dado em JSON, retornando em string.
			String json = restServicePath.request().get(String.class);
			
			Gson gson = new Gson();
			
			//pega o resultado em String e converte os dados num vetor de string
			Fabricante[] vetorFabricante = gson.fromJson(json, Fabricante[].class);
			
			//converte o vetor de string em um ArrayList
			fabricantes = Arrays.asList(vetorFabricante);
			
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to listed factories!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			// salvar via DAO directo e cria muito acopolamento entre Frontend e Backend
//			FabricanteDAO fabricanteDAO = new FabricanteDAO();
//			fabricanteDAO.merge(fabricante);
//			novo();
//			fabricantes = fabricanteDAO.findAll("descricao");
			
			//Cria client para chamar serviço para evitar o acopolamento entre front e backend..
			Client client = ClientBuilder.newClient();
			
			WebTarget restServicePath = client.target("http://localhost:8080/caroni_supermarket/rest/fabricante/");
			
			Gson gson = new Gson();
			
			//converte os dados que vai ser salvo para json..
			String json = gson.toJson(fabricante);
			//Passa o dado Json na requisiçao post para salvar na BD.
			restServicePath.request().post(Entity.json(json));
			
			//apenas instancia o fabricante para limpar a tela..
			fabricante = new Fabricante();
			
			//apenas retorna o dados em string para listar/atualizar na tela
			json = restServicePath.request().get(String.class);
			
			//converte os dados em vetor de string
			Fabricante[] vetorFabricante = gson.fromJson(json, Fabricante[].class);
			// converte o vetor de string em um arrayList.
			fabricantes = Arrays.asList(vetorFabricante);
			
			
			Messages.addGlobalInfo("Successfully saved factories!");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to saved factories!");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricanteSelecionado");
	}

	public void excluir(ActionEvent event) {
		fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.delete(fabricante);
			novo();
			fabricantes = fabricanteDAO.findAll("descricao");
			Messages.addGlobalInfo("Successfully removed factories!");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to removed factories!");
			e.printStackTrace();
		}
	}

}
