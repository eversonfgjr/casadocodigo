package casadocodigo;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.controllers.ProdutosController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

class ProdutoTest {

	
	private ProdutosController produtosController;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private Produto criarProduto() {
		Produto produto = new Produto();
		produto.setId(1);
		produto.setPaginas(200);
		produto.setPrecos(new ArrayList<>());
		produto.setTitulo("titulo");
				
		return produto;
	}
	
	

	@Test
	void testGravar() {
		Produto p = criarProduto();
		ProdutoDAO spy = Mockito.spy(new ProdutoDAO());
		Mockito.doNothing().when(spy).gravar(p);
		produtosController = new ProdutosController();
		produtosController.setProdutoDao(spy);
		produtosController.gravar(p);
		
	}

	@Test
	void testListar() {
		
		List<Produto> lista = new ArrayList<Produto>();
		
		Produto p1 = criarProduto();
		Produto p2 = criarProduto();
		Produto p3 = criarProduto();
		
		p1.setId(1);
		p2.setId(2);
		p3.setId(3);
		
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		
		
		
		ProdutoDAO dao = Mockito.mock(ProdutoDAO.class);
		Mockito.when(dao.listar()).thenReturn(lista);
		produtosController = new ProdutosController();
		produtosController.setProdutoDao(dao);
		ModelAndView mv = produtosController.listar();
		assertNotNull(mv);
		
		
	}

}
