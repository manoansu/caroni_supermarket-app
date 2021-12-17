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
		List<Cidade> cidades = cidadeDAO.findAll();
		
		for(Cidade cidade: cidades) {
			System.out.println("Cidade: " + cidade.getId() + " " + cidade.getNome() + 
					"\nEstado: " + cidade.getEstado().getId() + " " +
					cidade.getEstado().getNome() + "-" + cidade.getEstado().getSigla() + "\n");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		
		Long codigo = 5L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(codigo);
		
		if(cidade == null) {
			System.out.println("Register not found!");
		}else {		
			System.out.println("Register founded!");
			System.out.println("Cidade: " + cidade.getId() + " " + cidade.getNome() + 
					"\nEstado: " + cidade.getEstado().getId() + " " +
					cidade.getEstado().getNome() + "-" + cidade.getEstado().getSigla() + "\n");
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		Long cidadeId = 15L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(cidadeId);		
		
		if (cidade == null) {
			System.out.println("Register not found");
		}else {
			System.out.println("register founded!\n");
			cidadeDAO.delete(cidade);
			System.out.println("City Removed successfuly!!");
			System.out.println("Cidade: " + cidade.getId() + " " + cidade.getNome() + 
					"\nEstado: " + cidade.getEstado().getId() + " " +
					cidade.getEstado().getNome() + "-" + cidade.getEstado().getSigla() + "\n");
		}		
	}
	
	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void editar() {
		
		Long cidadeId = 15L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.findById(cidadeId);
		
		Long estadoId = 10L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.findById(estadoId);
		
		if(estado == null) {
			System.out.println("State not found!");
		}else if (cidade == null) {
			System.out.println("Register not found!");
		}else {
			System.out.println("register founded!\n");
			cidade.setNome("Guarapuava");
			cidade.setEstado(estado);
			cidadeDAO.merge(cidade);
			System.out.println("City Removed successfuly!!");
			System.out.println("Cidade: " + cidade.getId() + " " + cidade.getNome() + 
					"\nEstado: " + cidade.getEstado().getId() + " " +
					cidade.getEstado().getNome() + "-" + cidade.getEstado().getSigla() + "\n");
		}		
		
	}
	
	@Test
	@Ignore
	public void buscarPorEstado() {
		
		Long estadoId = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoId);
		
		for(Cidade cidade: cidades) {
			System.out.println("Cidade: " + cidade.getId() + " " + cidade.getNome() + 
					"\nEstado: " + cidade.getEstado().getId() + " " +
					cidade.getEstado().getNome() + "-" + cidade.getEstado().getSigla() + "\n");
		}
	}

}
