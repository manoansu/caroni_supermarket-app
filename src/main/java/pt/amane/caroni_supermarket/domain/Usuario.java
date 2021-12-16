package pt.amane.caroni_supermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 32)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = false)
	private boolean ativo;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Usuario() {
	}

	public Usuario(String senha, Character tipo, boolean ativo, Pessoa pessoa) {
		this.senha = senha;
		this.tipo = tipo;
		this.ativo = ativo;
		this.pessoa = pessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Usuario [senha=" + senha + ", tipo=" + tipo + ", ativo=" + ativo + ", pessoa=" + pessoa + "]";
	}

}
