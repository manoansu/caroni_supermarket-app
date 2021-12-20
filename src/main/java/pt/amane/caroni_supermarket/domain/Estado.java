package pt.amane.caroni_supermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Estado extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 2)
	private String sigla;

	@Column(nullable = false, length = 50)
	private String nome;

//	@OneToMany(mappedBy = "estado")
//	private List<Cidade> cidades = new ArrayList<Cidade>();

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

//	public List<Cidade> getCidades() {
//		return cidades;
//	}

	@Override
	public String toString() {
		return "Estado [sigla=" + sigla + ", nome=" + nome +"]";
	}



}
