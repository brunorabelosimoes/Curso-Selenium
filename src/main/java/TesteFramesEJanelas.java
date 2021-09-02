import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.brsimoes.core.DriverFactory;

public class TesteFramesEJanelas {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

//	@Test
	// Acessa um frame dentro de outro.
	public void deveInteragirComFrames() {

		dsl.entrarFrame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();

		dsl.sairFrame();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	// teste não passou
	public void deveInteragirComFrameEscondido() throws InterruptedException{
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotão("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	

//	@Test
	// Aceesa uma janela, escreve nela, fecha e escreve na janela anterior
	public void deveInteragirComJanelas() {

		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		dsl.trocarJanela("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();
		dsl.trocarJanela("");
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("e agora?");
	}

//	@Test
	// Aceesa uma janela sem título, escreve nela, fecha e escreve na janela
	// anterior
	public void deveInteragirComJanelasSemTítulo() {

		getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
}
