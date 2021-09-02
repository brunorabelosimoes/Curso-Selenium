import static br.ce.brsimoes.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.brsimoes.core.DriverFactory;

public class TesteSincronismo {
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
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException{
		dsl.clicarBotão("buttonDelay");
		//Para o teste por determinado tempo antes de prosseguir
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu certo?");
		
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException{
		//Acha o elemento dentro de determinado tempo, (irá funcionar para todos os testes abaixo se não for finalizada)
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotão("buttonDelay");
		dsl.escreve("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		//sem controle, caso a aplicação esteja com erro irá esperar os x tempo e irá quebrar 		
	}
	
	@Test
	public void deveUtilizarExplicita() throws InterruptedException{
		dsl.clicarBotão("buttonDelay");
		//Para o teste por até x tempo antes de prosseguir de forma explícita
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu certo?");
		//mais controle sobre as esperas colocando exatamente onde deseja
		
	}

}
