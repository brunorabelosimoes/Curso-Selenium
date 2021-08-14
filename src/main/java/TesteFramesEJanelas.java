import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// propriedade que acessa a pasta de recursos
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
//		driver.quit();
	}

//	@Test
	// Acessa um frame dentro de outro.
	public void deveInteragirComFrames() {

		dsl.entrarFrame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();

		dsl.sairFrame();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	// teste não passou
	public void deveInteragirComFrameEscondido() throws InterruptedException{
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotão("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	

//	@Test
	// Aceesa uma janela, escreve nela, fecha e escreve na janela anterior
	public void deveInteragirComJanelas() {

		driver.findElement(By.id("buttonPopUpEasy")).click();
		dsl.trocarJanela("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("e agora?");
	}

//	@Test
	// Aceesa uma janela sem título, escreve nela, fecha e escreve na janela
	// anterior
	public void deveInteragirComJanelasSemTítulo() {

		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
}
