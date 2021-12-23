package pt.amane.caroni_supermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fabricante extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String descricao;

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
}
