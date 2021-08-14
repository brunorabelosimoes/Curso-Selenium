import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteCadastroCompleto {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// propriedade que acessa a pasta de recursos
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}
		
	@Test
	public void deveCriarCadastro() {
		page.setNome("Bruno");
		page.setSobrenome("Rabelo Simões");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("Especializacao");
		page.setEsporte("O que eh esporte?");		

		// A forma abaixo deu certo!
		// WebElement element =
		// driver.findElement(By.id("elementosForm:escolaridade"));
		// Select combo = new Select(element);
		// combo.selectByVisibleText("Especializacao");
		// WebElement element1 =
		// driver.findElement(By.id("elementosForm:esportes"));
		// Select combo1 = new Select(element1);
		// combo1.selectByVisibleText("O que eh esporte?");

		page.cadastrar();

		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Bruno"));
		Assert.assertEquals("Sobrenome: Rabelo Simões", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Carne", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: especializacao", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: O que eh esporte?", page.obterEsportesCadastro());
		
		}

}
