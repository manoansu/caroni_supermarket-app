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

	@OneToMany(mappedBy = "funcionario")
	private List<Venda> vendas = new ArrayList<Venda>();

	@OneToMany(mappedBy = "funcionario")
	private List<Caixa> caixas = new ArrayList<Caixa>();

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

	public List<Venda> getVendas() {
		return vendas;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}

	@Override
	public String toString() {
		return "Funcionario [carteiraTrabalho=" + carteiraTrabalho + ", dataAdmissao=" + dataAdmissao + ", pessoa="
				+ pessoa + "]";
	}

}
