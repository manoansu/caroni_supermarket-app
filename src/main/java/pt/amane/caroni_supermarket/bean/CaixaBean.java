package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.CaixaDAO;
import pt.amane.caroni_supermarket.domain.Caixa;

@ViewScoped
@ManagedBean
public class CaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Caixa caixa;
	private List<Caixa> caixas;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}
	
	public void novo(){
		caixa = new Caixa();
	}
	
	@PostConstruct
	public void listar() {
		try {
			CaixaDAO caixaDAO = new CaixaDAO();
			caixas = caixaDAO.findAll("dataAbertura");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed cash!");
			e.printStackTrace();
		}
	}
	
	public  void salvar() {
		try {
			CaixaDAO caixaDAO = new CaixaDAO();
			caixaDAO.merge(caixa);
			novo();
			caixas = caixaDAO.findAll("dataAbertura");
			Messages.addGlobalInfo("Successfully saved cash!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to saved cash!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		caixa = (Caixa) event.getComponent().getAttributes().get("caixaSelecionado");
	}
	
	public void excluir(ActionEvent event) {
		caixa = (Caixa) event.getComponent().getAttributes().get("caixaSelecionado");
		try {
			CaixaDAO caixaDAO = new CaixaDAO();
			caixaDAO.delete(caixa);
			novo();
			caixas = caixaDAO.findAll("dataAbertura");
			Messages.addGlobalInfo("Successfully removed cash!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to removed cash!");
			e.printStackTrace();
		}
	}

}
