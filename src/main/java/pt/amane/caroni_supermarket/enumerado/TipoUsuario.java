package pt.amane.caroni_supermarket.enumerado;

import org.springframework.beans.factory.annotation.Autowired;

public enum TipoUsuario {
	
	 ADMINISTRADOR,
	GERENTE,
	BALCONISTA;
	
	@Autowired
	public String toString() {
		
		switch (this) {
		case ADMINISTRADOR:
			return "Administrador";
		case GERENTE:
			return "Gerente";
		case BALCONISTA:
			return "Balconista";
		default:
			return null;
		}
	}

}
