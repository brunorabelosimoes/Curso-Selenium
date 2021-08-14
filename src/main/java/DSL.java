import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	// ======= TextField e TextArea =====//
	
	
	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");

	}
	
	public void limpa(String id){
		driver.findElement(By.id(id)).clear();
	}
	
	// ======= Radio e Check =====//
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isRadioMark(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	
	public void clicarCheck(String id){
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isCheckMark(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	// ======= Combo =====//
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void selecionarCombo(String id, String valor){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	// ======= Botão =====//
	
	public void clicarBotão(String id){
		driver.findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id){
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	// ======= Link =====//
	
	public void clicarLink(String link){
		driver.findElement(By.linkText(link)).click();
	}
	
	// ======= Textos =====//

	public String obterTexto(By by){
		return driver.findElement(by).getText();
	}
	public String obterTexto(String id){
		return obterTexto(By.id(id));
	}
	
	// ======= Alerts =====//
	
	public String alertaObterTexto(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
	
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}

	// ======= Frames e janelas =====//
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id){
		driver.switchTo().window(id);
	}
	
	/**************************** JS *****************************/
	
	public Object executarJS(String cmd, Object... param){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
}

