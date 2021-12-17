package pt.amane.caroni_supermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import pt.amane.caroni_supermarket.enumerado.TipoUsuario;

@Entity
public class Usuario extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 32)
	private String senha;

	@Transient
	private String senhSemCriptografia; // esse campo faz parte de persistente, mas nao esta guardado na base de dado..

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario; // tipo enumerado..

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Usuario() {
	}

	public Usuario(String senha, TipoUsuario tipoUsuario, boolean ativo, Pessoa pessoa) {
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.ativo = ativo;
		this.pessoa = pessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhSemCriptografia() {
		return senhSemCriptografia;
	}

	public void setSenhSemCriptografia(String senhSemCriptografia) {
		this.senhSemCriptografia = senhSemCriptografia;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
	
	/**
	 * informa para o hibernate que este metodo só serve para formatação e nao é um
	 * campo fisico de BD ou seja, nao faz parte de BD caso usa validate ou update
	 * para BD no hibernate .cfg.xml.
	 * 
	 * @return
	 */

//	@Transient
//	public String getTipoFormatado() {
//
//		String tpFormatado = null;
//
//		switch (tipo) {
//		case 'A':
//			tpFormatado = "Administrador";
//			break;
//		case 'B':
//			tpFormatado = "Gerente";
//			break;
//		case 'C':
//			tpFormatado = "Balconista";
//			break;
//		default:
//			System.out.println("O tipo escolhido não existe");
//			break;
//		}
//
//		return tpFormatado;
//	}

	@Override
	public String toString() {
		return "Usuario [senha=" + senha + ", senhSemCriptografia=" + senhSemCriptografia + ", ativo=" + ativo
				+ ", tipoUsuario=" + tipoUsuario + ", pessoa=" + pessoa + "]";
	}

}
