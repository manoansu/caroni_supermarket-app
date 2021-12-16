package pt.amane.caroni_supermarket.dao;

import java.util.List;

import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Menu;

public class MenuDAOTest {

	@Test
	public void listar() {

		MenuDAO menuDAO = new MenuDAO();

		List<Menu> menus = menuDAO.findAll();

		for (Menu menu : menus) {
			if (menu.getCaminho() == null) {
				System.out.println(menu.getRotulo() + " - " + menu.getCaminho() + "\n");
			}
			for(Menu item: menu.getMenus()) {
				System.out.println("\t" + item.getRotulo() + " - " + item.getCaminho() + "\n");
			}
			
		}
	}

}
