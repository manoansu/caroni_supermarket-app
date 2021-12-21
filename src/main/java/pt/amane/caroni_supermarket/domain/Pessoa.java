package pt.amane.caroni_supermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pessoa extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@Column(length = 12, nullable = false)
	private String rg;
	
	@Column(length = 100, nullable = false)
	private String rua;
	
	@Column(nullable = false)
	private Short numero;
	
	@Column(length = 30, nullable = false)
	private String bairro;
	
	@Column(length = 10, nullable = false)
	private String cep;
	
	@Column(length = 10)
	private String complemento;
	
	@Column(length = 13, nullable = false)
	private String telefone;

	@Column(length = 14, nullable = false)
	private String celular;
	
	@Column(length = 100, nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = false)
	private Cidade cidade;
	

	public Pessoa() {
	}

	public Pessoa(String nome, String cpf, String rg, String rua, Short numero, String bairro, String cep,
			String complemento, String telefone, String celular, String email, Cidade cidade) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", rua=" + rua + ", numero=" + numero
				+ ", bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento + ", telefone=" + telefone
				+ ", celular=" + celular + ", email=" + email + ", cidade=" + cidade + "]";
	}

}
