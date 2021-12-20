package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

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
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.findAll("descricao");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to listed factories!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			novo();
			fabricantes = fabricanteDAO.findAll("descricao");
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
