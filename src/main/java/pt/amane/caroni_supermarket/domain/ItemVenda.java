package pt.amane.caroni_supermarket.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoParcial;

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "venda_id", nullable = false)
	private Venda venda;

	public ItemVenda() {
	}

	public ItemVenda(Short quantidade, BigDecimal precoParcial, Produto produto, Venda venda) {
		super();
		this.quantidade = quantidade;
		this.precoParcial = precoParcial;
		this.produto = produto;
		this.venda = venda;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "ItemVenda [quantidade=" + quantidade + ", precoParcial=" + precoParcial + ", produto=" + produto
				+ ", venda=" + venda + "]";
	}

}
