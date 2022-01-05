package pt.amane.caroni_supermarket.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Produto extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 80)
	private String descricao;

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;

	@Transient
	private String caminho;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fabricante_id", nullable = false)
	private Fabricante fabricante;

	public Produto() {
	}

	public Produto(String descricao, Short quantidade, BigDecimal preco, Fabricante fabricante) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.fabricante = fabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@Override
	public String toString() {
		return "Produto [descricao=" + descricao + ", quantidade=" + quantidade + ", preco=" + preco + ", fabricante="
				+ fabricante + "]";
	}

}
