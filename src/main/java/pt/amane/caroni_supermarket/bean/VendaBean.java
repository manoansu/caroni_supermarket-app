package pt.amane.caroni_supermarket.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import pt.amane.caroni_supermarket.dao.FuncionarioDAO;
import pt.amane.caroni_supermarket.dao.ProdutoDAO;
import pt.amane.caroni_supermarket.domain.Cliente;
import pt.amane.caroni_supermarket.domain.Funcionario;
import pt.amane.caroni_supermarket.domain.ItemVenda;
import pt.amane.caroni_supermarket.domain.Produto;
import pt.amane.caroni_supermarket.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda;
	
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));
			venda.setHorario(new Date());
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.findAll("descricao");

			itensVenda = new ArrayList<>();	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Error trying to generated sale!");
			erro.printStackTrace();
		}
	}

	@SuppressWarnings("removal")
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		
		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			if(itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())){
				achou = posicao;
			}
		}
		
		if(achou > -1){
			itensVenda.remove(achou);
		}
		
		calcular();
	}
	
	public void calcular(){
		venda.setPrecoTotal(new BigDecimal("0.00"));
		
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}
	
	public void finalizar(){
		try{
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.findAll("pessoa.nome");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Error trying to finalided sale!");
			erro.printStackTrace();
		}
	}
}