package pt.amane.caroni_supermarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cidade extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	@OneToMany(mappedBy = "cidade")
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public Cidade() {
	}

	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
