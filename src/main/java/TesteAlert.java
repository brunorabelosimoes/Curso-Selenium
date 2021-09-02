import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import br.ce.brsimoes.core.DriverFactory;

public class TesteAlert {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();;
	}

	// @Test
	public void deveInteragirComAlertSimples() {

		dsl.clicarBotão("alert");
		Alert alert = getDriver().switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();

		dsl.escreve("elementosForm:nome",texto);
	}

	@Test

	public void deveInteragirComAlertConfirm() {

		dsl.clicarBotão("confirm");
		Alert alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();

		dsl.clicarBotão("confirm");
		alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();
	}

	@Test

	public void deveInteragirComAlertPrompt() {

		dsl.clicarBotão("prompt");
		Alert alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
}
