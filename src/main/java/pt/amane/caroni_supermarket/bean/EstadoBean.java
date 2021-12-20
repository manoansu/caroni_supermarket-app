package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.EstadoDAO;
import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Estado;

/**
 * 
 * @author manoansu Essa classe onde vai estar o modelo e controle qd usamos
 *         essa anotacoes ManagedBean, informando o java que não é uma classe
 *         comum. Mas sim ele diz para java que ele é responsavel para tratar do
 *         nosso modelo e controle, onde criamos o metodo que faz a ponte de
 *         base de dado e com a tela.
 *         
 *         Tempo de Vida:
 *         Em aplicação web, eles tem 3 tipos de tempo de vida: 
 *         Request -> o Objecto vive por um click na tela
 *         
 *         View -> ele existe durante to estas na tela e objecto daquela entidade 
 *         estao vivos e os demais entidade ficam morta.
 *         
 *         Session -> os objecto ficam vivo durante todo o tempo de sessao.
 *         
 *         com essa anotação ViewScoped, estou falano que o meu ManagedBean e do tipo View, e
 *         a entidade por ex: estado existe no momento que estou na tela de estado. 
 *
 */
@ViewScoped
@ManagedBean
public class EstadoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Estado estado;
	
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void novo() {
		estado = new Estado();
	}
	
	/**
	 * é chamado como um constructor da classe
	 */
	@PostConstruct
	public void listar() {
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to list state!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
						
			novo();
			estados = estadoDAO.findAll("nome");
			
			Messages.addGlobalInfo("Successfully saved state!");
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to saved state!!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		estado = (Estado) event.getComponent().getAttributes().get("estadoSelecionado");
	}
	
	public void excluir(ActionEvent event) {
		estado = (Estado) event.getComponent().getAttributes().get("estadoSelecionado");
		
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.delete(estado);
			
			novo();
			estados = estadoDAO.findAll("nome");
			Messages.addGlobalInfo("Successfully removed state!");
			
		} catch (Exception e) {
			Messages.addGlobalError("Error trying to removed state!");
		}
	}

}
