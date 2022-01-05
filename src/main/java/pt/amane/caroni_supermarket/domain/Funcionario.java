package pt.amane.caroni_supermarket.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Funcionario extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 15)
	private String carteiraTrabalho;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Funcionario() {
	}

	public Funcionario(String carteiraTrabalho, Date dataAdmissao, Pessoa pessoa) {
		this.carteiraTrabalho = carteiraTrabalho;
		this.dataAdmissao = dataAdmissao;
		this.pessoa = pessoa;
	}

	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Funcionario [carteiraTrabalho=" + carteiraTrabalho + ", dataAdmissao=" + dataAdmissao + ", pessoa="
				+ pessoa + "]";
	}

}
