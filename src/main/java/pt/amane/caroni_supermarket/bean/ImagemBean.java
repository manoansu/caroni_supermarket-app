package pt.amane.caroni_supermarket.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean {
	@ManagedProperty("#{param.caminho}")
	private String caminho;
	
	private StreamedContent foto;
	
	public String getCaminho() {
		return caminho;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	@SuppressWarnings("deprecation")
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()){
			Path path = Paths.get("C:\\Users\\\\manoa\\eclipse-workspace\\uploads\\branco.png");
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}else{
			Path path = Paths.get(caminho);
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
}
