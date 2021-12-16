package pt.amane.caroni_supermarket.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Caixa extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable = false)
	private Funcionario funcionario;

	@OneToMany(mappedBy = "caixa")
	private List<Movimentacao> movimentacaos = new ArrayList<Movimentacao>();

	public Caixa() {
	}

	public Caixa(Date dataAbertura, Date dataFechamento, BigDecimal valor) {
		super();
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.valor = valor;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Movimentacao> getMovimentacaos() {
		return movimentacaos;
	}

	@Override
	public String toString() {
		return "Caixa [dataAbertura=" + dataAbertura + ", dataFechamento=" + dataFechamento + ", valor=" + valor
				+ ", funcionario=" + funcionario + "]";
	}

}
