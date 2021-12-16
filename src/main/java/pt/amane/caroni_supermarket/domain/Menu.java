package pt.amane.caroni_supermarket.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Menu extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String rotulo;

	@Column(length = 50)
	private String caminho;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "id")
	@Fetch(FetchMode.SELECT)
	private List<Menu> menus;

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
