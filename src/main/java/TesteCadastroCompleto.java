import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.brsimoes.core.DriverFactory;


public class TesteCadastroCompleto {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		// propriedade que acessa a pasta de recursos
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
		
	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("Bruno");
		page.setSobrenome("Rabelo Simões");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("Especializacao");
		page.setEsporte("O que eh esporte?");		
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Bruno", page.obterNomeCadastro());
		Assert.assertEquals("Rabelo Simões", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("especializacao", page.obterEscolaridadeCadastro());
		Assert.assertEquals("O que eh esporte?", page.obterEsportesCadastro());
		
		}

}
