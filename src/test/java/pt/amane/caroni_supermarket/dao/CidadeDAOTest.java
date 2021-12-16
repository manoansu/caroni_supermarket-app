package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void save() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.findById(3L);

		CidadeDAO cidadeDAO = new CidadeDAO();

		if (estado == null) {
			System.out.println("Register not found!!");
		} else {
			System.out.println("Register Founded!");
			Cidade cidade = new Cidade("Niteroy", estado);
			Cidade cidade1 = new Cidade("Rio de Janeiro", estado);
			cidadeDAO.salvar(cidade);
			cidadeDAO.salvar(cidade1);
			System.out.println("City saved successfuly!");
		}
	}
	
	@Test
	@Ignore
	public void listar() {
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> list = cidadeDAO.findAll();
		
		for(Cidade cidade: list) {
			System.out.println("Id: " + cidade.getId() + " Nome: " + cidade.getNome() + 
					"\nCodigo de Estado: " + cidade.getEstado().getId() + " Nome: " +
					cidade.getEstado().getNome() + " Sigla:" + cidade.getEstado().getSigla() + "\n");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		
		Long codigo = 12L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(codigo);
		System.out.println("Id: " + cidade.getId() + " Nome: " + cidade.getNome() + 
				"\nCodigo de Estado: " + cidade.getEstado().getId() + " Nome: " +
				cidade.getEstado().getNome() + " Sigla:" + cidade.getEstado().getSigla() + "\n");
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		Long codigo = 15L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(codigo);
		
		if (cidade == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
			System.out.println("registro encontrado\n");
			cidadeDAO.delete(cidade);
			System.out.println("Cidade Removida!!");
			System.out.println("Id: " + cidade.getId() + " Nome: " + cidade.getNome() + 
					"\nCodigo de Estado: " + cidade.getEstado().getId() + " Nome: " +
					cidade.getEstado().getNome() + " Sigla:" + cidade.getEstado().getSigla() + "\n");
		}		
	}
	
	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void editar() {
		
		Long codigo = 15L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(codigo);
		
		cidade.setNome("Guarapuava");
		
		if (cidade == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
			System.out.println("registro encontrado\n");
			cidadeDAO.delete(cidade);
			System.out.println("Cidade Removida!!");
			System.out.println("Id: " + cidade.getId() + " Nome: " + cidade.getNome() + 
					"\nCodigo de Estado: " + cidade.getEstado().getId() + " Nome: " +
					cidade.getEstado().getNome() + " Sigla:" + cidade.getEstado().getSigla() + "\n");
		}		
		
	}
	
	@Test
	@Ignore
	public void buscarPorEstado() {
		
		Long estadoId = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> list = cidadeDAO.buscarPorEstado(estadoId);
		
		for(Cidade cidade: list) {
			System.out.println("Id: " + cidade.getId() + " Nome: " + cidade.getNome() + 
					"\nCodigo de Estado: " + cidade.getEstado().getId() + " Nome: " +
					cidade.getEstado().getNome() + " Sigla:" + cidade.getEstado().getSigla() + "\n");
		}
	}

}
