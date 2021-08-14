
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// propriedade que acessa a pasta de recursos
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
	//	driver.quit();
	}

//	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}

//	@Test
	public void deveInteragirComtextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	}

//	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

//	@Test
	public void deveInteragirComCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(dsl.isRadioMark("elementosForm:comidaFavorita:2"));
	}

//	@Test
	public void deveInteragirComCombo() {
		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		// combo.selectByIndex(2);
		// combo.selectByValue("superior");
		//combo.selectByVisibleText("2o grau completo");
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

//	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;

			}
		}
		Assert.assertTrue(encontrou);
	}

//	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}

//	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotão("buttonSimple");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}

//	@Test
	// Ignora o teste
	// @Ignore
	public void deveInteragirComLinks() {

		dsl.clicarLink("Voltar");
		// caso o teste fique em aberto, o assert fail irá falhar para que o
		// teste incompleto não gere falso positivo
		// Assert.fail();
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

//	@Test
	public void deveBuscarTextos() {
		// não é a melhor forma de verifiar texto pois verifica o HTML inteiro
		// para tazer um pequeno texto
		// Assert.assertTrue(driver.findElement(By.tagName("body"))
		// .getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
//	@Test
	public void testTextFieldDuplo(){
		
		dsl.escreve("elementosForm:nome", "João");
		Assert.assertEquals("João", dsl.obterValorCampo("elementosForm:nome"));
		
		dsl.escreve("elementosForm:nome", " Gordo");
		Assert.assertEquals("João Gordo", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	

}
