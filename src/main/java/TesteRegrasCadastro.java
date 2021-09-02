import static br.ce.brsimoes.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;

import br.ce.brsimoes.core.DriverFactory;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	//private WebDriver driver;
	private CampoTreinamentoPage page;
	//private DSL dsl;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		new DSL();
		page = new CampoTreinamentoPage();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"","","", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Bruno","","", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Bruno","Rabelo","", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Bruno","Rabelo","Masculino", Arrays.asList("Carne","Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Bruno","Rabelo","Masculino", Arrays.asList("Carne"), new String[]{"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?"},
		
		});
	}
	
	@Test
	public void deveValidarRegras() throws InterruptedException{
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		} 
		if(sexo.equals("Masculino")){
			page.setSexoMasculino();
		}	
		if(comidas.contains("Carne"))page.setComidaCarne();
		if(comidas.contains("Vegetariano"))	page.setComidaVegetariano();
		
		
	page.setEsporte(esportes);
	page.cadastrar();
	Thread.sleep(10000);
	System.out.println(msg);
	//Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	
	Alert alerta4 = getDriver().switchTo().alert();
	Assert.assertEquals(msg, alerta4.getText());
	alerta4.accept();

	}
}