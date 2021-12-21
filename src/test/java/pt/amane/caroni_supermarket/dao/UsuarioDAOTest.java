package pt.amane.caroni_supermarket.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import pt.amane.caroni_supermarket.domain.Pessoa;
import pt.amane.caroni_supermarket.domain.Usuario;
import pt.amane.caroni_supermarket.enumerado.TipoUsuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.findById(2L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
				
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhSemCriptografia("q1w2e3r4");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhSemCriptografia()); //hash que guarda o md5 e senha que nao vai ser criptografado
		usuario.setSenha(hash.toHex()); // salva a senha criptografada
		usuario.setTipoUsuario(TipoUsuario.BALCONISTA);;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
	
	@Test
	//@Ignore
	public void autenticar() {
		String cpf = "888.888.888-88";
		String senha = "12345678";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuario Autenticado: " + usuario);

	}

}
