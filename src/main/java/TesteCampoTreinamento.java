import static br.ce.brsimoes.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.brsimoes.core.DriverFactory;

public class TesteCampoTreinamento {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

//	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
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
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

//	@Test
	public void deveInteragirComCheckBox() {
		getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(dsl.isRadioMark("elementosForm:comidaFavorita:2"));
	}

//	@Test
	public void deveInteragirComCombo() {
		//WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		// combo.selectByIndex(2);
		// combo.selectByValue("superior");
		//combo.selectByVisibleText("2o grau completo");
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

//	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
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
		
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}

//	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBot??o("buttonSimple");
		
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}

//	@Test
	// Ignora o teste
	// @Ignore
	public void deveInteragirComLinks() {

		dsl.clicarLink("Voltar");
		// caso o teste fique em aberto, o assert fail ir?? falhar para que o
		// teste incompleto n??o gere falso positivo
		// Assert.fail();
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

//	@Test
	public void deveBuscarTextos() {
		// n??o ?? a melhor forma de verifiar texto pois verifica o HTML inteiro
		// para tazer um pequeno texto
		// Assert.assertTrue(getDriver().findElement(By.tagName("body"))
		// .getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
//	@Test
	public void testTextFieldDuplo(){
		
		dsl.escreve("elementosForm:nome", "Jo??o");
		Assert.assertEquals("Jo??o", dsl.obterValorCampo("elementosForm:nome"));
		
		dsl.escreve("elementosForm:nome", " Gordo");
		Assert.assertEquals("Jo??o Gordo", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
//	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}

}
