package pt.amane.caroni_supermarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Fabricante extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String descricao;

	@OneToMany(mappedBy = "fabricante")
	private List<Produto> produtos = new ArrayList<Produto>();

	public Fabricante() {
	}

	public Fabricante(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
