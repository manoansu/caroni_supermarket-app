package pt.amane.caroni_supermarket.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * http://localhost:8080/caroni_supermarket/rest
 * @author manoansu
 *
 */
@ApplicationPath("/rest")
public class CaroniSupermarketResourceConfig extends ResourceConfig{

	//passa o caminho de package no construtor..
	public CaroniSupermarketResourceConfig() {
		packages("pt.amane.caroni_supermarket.service");
	}
}
