import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteRegrasDeNegocios {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// propriedade que acessa a pasta de recursos
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void regrasDeNegocios() {
		
		// Preenche nome e alerta obrigatoriedade do sobrenome
		page.setNome("Geraldo");
		page.cadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
		alerta.accept();
		System.out.println("Teste Nome OK");

		// Prenche sobrenome e alerta obrigatoriedade do nome
		dsl.limpa("elementosForm:nome");
		page.setNome("Aldo");
		page.cadastrar();
		Alert alerta1 = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta1.getText());
		alerta1.accept();
		System.out.println("Teste Sobrenome OK");

		// Alerta obrigatoriedade do Sexo
		page.setNome("Geraldo");
		page.cadastrar();
		Alert alerta2 = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta2.getText());
		alerta2.accept();
		System.out.println("Teste sexo Ok");

		// Alerta marcação carne e vegetariano

		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Alert alerta3 = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta3.getText());
		alerta3.accept();
		System.out.println("Teste vegertariano Ok");

		// Alerta marcar esporte e marcar "o que é esporte?"
		page.setComidaVegetariano();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Alert alerta4 = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta4.getText());
		alerta4.accept();
		System.out.println("Teste esportes Ok");

	}
}
