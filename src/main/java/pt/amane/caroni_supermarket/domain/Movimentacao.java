package pt.amane.caroni_supermarket.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "caixa_id", nullable = false)
	private Caixa caixa;

	public Movimentacao() {
	}

	public Movimentacao(Date horario, BigDecimal valor, Caixa caixa) {
		this.horario = horario;
		this.valor = valor;
		this.caixa = caixa;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	@Override
	public String toString() {
		return "Movimentacao [horario=" + horario + ", valor=" + valor + ", caixa=" + caixa + "]";
	}

}
