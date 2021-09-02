import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.brsimoes.core.DriverFactory;

public class TesteAjax {
	
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=5dca6");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testAjax(){
		dsl.escrever(By.id("j_idt302:name"), "escreveu?");
		dsl.clicarBotão("j_idt302:j_idt306");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "escreveu?"));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt345"))); //Espera o simbolo de espera sair
		Assert.assertEquals("escreveu?", dsl.obterTexto("j_idt302:display"));
	}
}
