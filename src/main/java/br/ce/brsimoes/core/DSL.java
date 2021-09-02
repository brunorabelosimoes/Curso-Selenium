package br.ce.brsimoes.core;

import static br.ce.brsimoes.core.DriverFactory.getDriver;

import java.util.List;
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
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");

	}
	
	public void limpa(String id){
		getDriver().findElement(By.id(id)).clear();
	}
	
	// ======= Radio e Check =====//
	
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}

	public boolean isRadioMark(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	
	public void clicarCheck(String id){
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMark(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	// ======= Combo =====//
	
	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void selecionarCombo(String id, String valor){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void selecionarComboPrime(String radical, String valor){
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}
	
	// ======= Botão =====//
	
	public void clicarBotão(String id){
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id){
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	// ======= Link =====//
	
	public void clicarLink(String link){
		getDriver().findElement(By.linkText(link)).click();
	}
	
	// ======= Textos =====//

	public String obterTexto(By by){
		return getDriver().findElement(by).getText();
	}
	public String obterTexto(String id){
		return obterTexto(By.id(id));
	}
	
	// ======= Alerts =====//
	
	public String alertaObterTexto(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
	
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}

	// ======= Frames e janelas =====//
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void sairFrame(){
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id){
		getDriver().switchTo().window(id);
	}
	
	/**************************** JS *****************************/
	
	public Object executarJS(String cmd, Object... param){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
	
	/**************************** Tabela *****************************/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String clicarCelula){ 
		
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath(".//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar botao da celula encontrado
		WebElement celula =  tabela.findElement(By.xpath("//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
		
	}
		
}

