import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.brsimoes.core.DriverFactory;

public class TestePrime {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=2c146");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
//	Mostra como localizar Radio "escondido" e que IDs com "j_idt" gerados automáticamente
//	podem mudar sua nomenclatura quebrando os testes
	
	@Test
	public void deveInteragirComRadioPrime() throws InterruptedException{
		Thread.sleep(3500);
		dsl.clicarRadio(By.xpath("//input[@id='j_idt303:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMark("j_idt303:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isRadioMark("j_idt303:console:1"));
	}

}
