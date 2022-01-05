package pt.amane.caroni_supermarket.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pt.amane.caroni_supermarket.dao.FabricanteDAO;
import pt.amane.caroni_supermarket.dao.ProdutoDAO;
import pt.amane.caroni_supermarket.domain.Fabricante;
import pt.amane.caroni_supermarket.domain.Produto;

@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void novo() {
		try {
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to selected Product!");
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to listed Product!");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			if(produto.getCaminho() == null) {
				Messages.addGlobalError("The Picture field is required!");
				return;
			}
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C:\\Users\\manoa\\eclipse-workspace\\uploads/" + produtoRetorno.getId() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			novo();
			produtos = produtoDAO.findAll("descricao");
			Messages.addGlobalInfo("Successfully saved Product!");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Error trying to saved Product!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		try {
			produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");
			// Evita que qd vais editar o produto ele nao pede o caminho na foto no metodo salvar..
			produto.setCaminho("C:\\Users\\manoa\\eclipse-workspace\\uploads/" + produto.getId() + ".png");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.findAll("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Error trying to edited Product!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent event) {
		produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.delete(produto);
			
			Path arquivo = Paths.get("C:\\Users\\manoa\\eclipse-workspace\\uploads/" +produto.getId() + ".png");
			Files.deleteIfExists(arquivo);
			novo();
			produtos = produtoDAO.findAll("descricao");
			Messages.addGlobalInfo("Successfuly removed Product!");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Error trying to removed Product!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Upload file
	 * @param event
	 */
	public void upload(FileUploadEvent evento) {
		System.out.println("Chamou o Upload!!");
		try {
			// Pega o arquivo de upload do primefaces..
			UploadedFile arquivoUpload = evento.getFile();
			//cria arquivo temporario dentro de SO..
			Path arquivoTemp = Files.createTempFile(null, null);
			// copia o ficheiro de origem para o destino q é SO..
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
			Messages.addGlobalInfo("Sucessfully Uploaded file!");
		} catch (IOException e) {
			Messages.addGlobalError("Error trying to uploaded file!");
			e.printStackTrace();
		}		
	}

}
