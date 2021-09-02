import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {

	@Test
	public void teste() {
	//System.setProperty("webgetDriver().chrome.driver","C:\\drivers\\chromegetDriver().exe");
		WebDriver driver = new ChromeDriver();
	//WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		//Fecha o driver após a execução do teste
		driver.quit();
	}
	
	
}
