package pt.amane.caroni_supermarket.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(nullable = false)
	private Boolean liberado;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	@OneToMany(mappedBy = "cliente")
	private List<Venda> vendas = new ArrayList<Venda>();

	public Cliente() {
	}

	public Cliente(Date dataCadastro, Boolean liberado, Pessoa pessoa) {
		this.dataCadastro = dataCadastro;
		this.liberado = liberado;
		this.pessoa = pessoa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getLiberado() {
		return liberado;
	}

	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	@Override
	public String toString() {
		return "Cliente [dataCadastro=" + dataCadastro + ", liberado=" + liberado + ", pessoa=" + pessoa + "]";
	}

}
