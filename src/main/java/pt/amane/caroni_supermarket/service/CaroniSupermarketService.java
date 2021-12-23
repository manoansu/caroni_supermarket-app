package pt.amane.caroni_supermarket.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * http://localhost:8080/caroni_supermarket/rest/supermarket
 * @author manoansu
 *
 */
@Path(value = "/supermarket")
public class CaroniSupermarketService {
	
	@GET
	public String  exibir() {
		return "Programação Web com java!!";
	}
}
