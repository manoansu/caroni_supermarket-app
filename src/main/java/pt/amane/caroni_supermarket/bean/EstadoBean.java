package pt.amane.caroni_supermarket.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.EstadoDAO;
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
public class EstadoBean {

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
	
	@PostConstruct
	public void listar() {
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.findAll("nome");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar listar o estado!");
		}
	}

	public void salvar() {
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
						
			novo();
			estados = estadoDAO.findAll("nome");
			
			Messages.addGlobalInfo("Estado Salvo com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar salvar estado!!");
		}
		

	}

}
