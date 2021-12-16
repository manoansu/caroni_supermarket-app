package pt.amane.caroni_supermarket.domain;

import javax.persistence.Entity;

@Entity
public class Estado extends GenericDomain {

	private static final long serialVersionUID = 1L;

	private String sigla;
	private String nome;

	public Estado() {
	}

	public Estado(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Estado [sigla=" + sigla + ", nome=" + nome + "]";
	}

}
