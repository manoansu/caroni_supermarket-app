package pt.amane.caroni_supermarket.dao;

import org.junit.Ignore;
import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Cidade;
import pt.amane.caroni_supermarket.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void save() {
		Estado estado = new Estado("TE", "Teste");
//		Estado estado1 = new Estado("SP", "São Paulo");
//		Estado estado2 = new Estado("RJ", "Rio de Janeiro");
//		Estado estado3 = new Estado("CE", "Fortaleza");
//		Estado estado4 = new Estado("BA", "Bahia");
//		Estado estado5 = new Estado("MG", "Minas Gerais");
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
//		estadoDAO.salvar(estado1);
//		estadoDAO.salvar(estado2);
//		estadoDAO.salvar(estado3);
//		estadoDAO.salvar(estado4);
//		estadoDAO.salvar(estado5);
	}

	@Test
	//@Ignore
	public void listarEstado() {

		EstadoDAO estadoDAO = new EstadoDAO();
		System.out.println("Estados: \n");

		for(Estado estado : estadoDAO.findAll()) {
			System.out.println("Id: " + estado.getId() + " " + estado.getNome() + "-" + 
					estado.getSigla() + "\nCidade: ");
			for(Cidade cidade: estado.getCidades()) {
				System.out.println("Id: " + cidade.getNome());
			}
		}
	}

	@Test
	@Ignore
	public void findId() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Long id = 7L;
		Estado estado = estadoDAO.findById(id);

		if (estado == null) {
			System.out.println("Register not found!");
		} else {
			System.out.println("Register found!");
			System.out.println("Id: " + estado.getId() + " " + estado.getNome() + "-" + estado.getSigla());
		}
	}

	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void updateEstado() {

		Long id = 7L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.findById(id);

		if (estado == null) {
			System.out.println("Register not found!");
		} else {
			System.out.println("Register found!");
			estado.setNome("Santa Catarina");
			estado.setSigla("SC");
			estadoDAO.merge(estado);
			System.out.println("Estado salvo com sucesso!");
			System.out.println("Id: " + estado.getId() + " " + estado.getNome() + "-" + estado.getSigla());
		}

	}
	
	@Test
	@Ignore
	public void delete() {
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Long id = 8L;
		Estado estado = estadoDAO.findById(id);		
		
		if(estado == null) {
			System.out.println("Register not found!");
		}else {
			System.out.println("Register Found!");
			estadoDAO.delete(estado);
			System.out.println("Registe removed successfull!");
		}
	}

}
