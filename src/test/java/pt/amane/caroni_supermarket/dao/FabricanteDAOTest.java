package pt.amane.caroni_supermarket.dao;

import org.junit.Ignore;
import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void save() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = new Fabricante("Teste F");
//		Fabricante fabricante1 = new Fabricante("Fabricante B");
//		Fabricante fabricante2 = new Fabricante("Fabricante C");
//		Fabricante fabricante3 = new Fabricante("Fabricante D");
//		Fabricante fabricante4 = new Fabricante("Fabricante E");
		
		fabricanteDAO.salvar(fabricante);
//		fabricanteDAO.salvar(fabricante1);
//		fabricanteDAO.salvar(fabricante2);
//		fabricanteDAO.salvar(fabricante3);
//		fabricanteDAO.salvar(fabricante4);
		
		System.out.println("Factory saved successfuly!");
	}
	
	@Test
	@Ignore
	public void findAll() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		
		for(Fabricante fabricante: fabricanteDAO.findAll()) {
			System.out.println("Id: " + fabricante.getId() + " " + fabricante.getDescricao());
		}
	}
	
	
	@Test
	@Ignore
	public void findId() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Long id = 8L;
		Fabricante fabricante = fabricanteDAO.findById(id);
		
		if(fabricante == null) {
			System.out.println("Register not Found!!");
		}else {
			System.out.println("Register Founded!");
			System.out.println("Id: " + fabricante.getId() + " " + fabricante.getDescricao());
			System.out.println("Id founded successfull!");
		}
	}
	
	@Test
	@Ignore
	public void update() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Long id = 6L;
		Fabricante fabricante = fabricanteDAO.findById(id);
		
		if(fabricante == null) {
			System.out.println("Register not found!");
		}else {
			System.out.println("Register founded!");
			fabricante.setDescricao("Fabricante F");
			fabricanteDAO.merge(fabricante);
			System.out.println("Factory updated successfull!");
		}
		
	}
	
	@Test
	//@Ignore
	public void delete() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Long id = 7L;
		Fabricante fabricante = fabricanteDAO.findById(id);
		
		if(fabricante == null) {
			System.out.println("Register not found!");
		}else {
			System.out.println("Register founded!");
			fabricanteDAO.delete(fabricante);
			System.out.println("Factory deleted successfull!");
		}
		
	}

}
