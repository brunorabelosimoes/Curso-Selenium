import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.brsimoes.core.DriverFactory;


public class DesafioPrime {
	
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=c7122");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveSelecionarMenu(){
		dsl.selecionarComboPrime("j_idt302:option", "Option2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt302:option_label"));
		//metodo pasado para DSL
//		dsl.clicarRadio(By.xpath("//*[@id='j_idt302:option_input']/../..//span"));
//		dsl.clicarRadio(By.xpath("//*[@id='j_idt302:option_items']//li[.='Option1']"));
	}
	
	
	
}
	
