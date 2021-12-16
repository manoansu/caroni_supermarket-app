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
public class Venda extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precototal;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable = false)
	private Funcionario funcionario;

	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itemVendas = new ArrayList<ItemVenda>();

	public Venda() {
	}

	public Date getHorario() {
		return horario;
	}

	public Venda(Date horario, BigDecimal precototal, Cliente cliente, Funcionario funcionario) {
		this.horario = horario;
		this.precototal = precototal;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getPrecototal() {
		return precototal;
	}

	public void setPrecototal(BigDecimal precototal) {
		this.precototal = precototal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	@Override
	public String toString() {
		return "Venda [horario=" + horario + ", precototal=" + precototal + ", cliente=" + cliente + ", funcionario="
				+ funcionario + "]";
	}

}
