package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

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
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.findAll("nome");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying listed city!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			novo();
			cidades = cidadeDAO.findAll("nome");
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
