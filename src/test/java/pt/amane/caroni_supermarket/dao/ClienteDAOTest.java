package pt.amane.caroni_supermarket.dao;

import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import com.google.protobuf.TextFormat.ParseException;

import pt.amane.caroni_supermarket.domain.Cliente;
import pt.amane.caroni_supermarket.domain.Pessoa;

public class ClienteDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws ParseException, java.text.ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.findById(2L);

		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2015"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Cliente salvo com sucesso.");
	}

}
