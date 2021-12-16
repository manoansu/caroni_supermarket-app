package pt.amane.caroni_supermarket.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;
import pt.amane.caroni_supermarket.domain.Caixa;

@SuppressWarnings("deprecation")
public class CaixaDAOTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("11/05/2021"));
		caixa.setValor(new BigDecimal("100.00"));
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
	}
	
	
	@Test
	//@Ignore
	public void buscar() throws ParseException {
		
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("13/05/2021"));
		System.out.println(caixa);
		Assert.assertNull(caixa);
		
	}
}
