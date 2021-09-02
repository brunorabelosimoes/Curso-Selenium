import static br.ce.brsimoes.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import br.ce.brsimoes.core.DriverFactory;

public class TesteRegrasDeNegocios {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

	@Test
	public void regrasDeNegocios() {
		
		// Preenche nome e alerta obrigatoriedade do sobrenome
		page.setNome("Geraldo");
		page.cadastrar();
		Alert alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
		alerta.accept();
		System.out.println("Teste Nome OK");

		// Prenche sobrenome e alerta obrigatoriedade do nome
		dsl.limpa("elementosForm:nome");
		page.setNome("Aldo");
		page.cadastrar();
		Alert alerta1 = getDriver().switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta1.getText());
		alerta1.accept();
		System.out.println("Teste Sobrenome OK");

		// Alerta obrigatoriedade do Sexo
		page.setNome("Geraldo");
		page.cadastrar();
		Alert alerta2 = getDriver().switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta2.getText());
		alerta2.accept();
		System.out.println("Teste sexo Ok");

		// Alerta marcação carne e vegetariano

		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Alert alerta3 = getDriver().switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta3.getText());
		alerta3.accept();
		System.out.println("Teste vegertariano Ok");

		// Alerta marcar esporte e marcar "o que é esporte?"
		page.setComidaVegetariano();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Alert alerta4 = getDriver().switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta4.getText());
		alerta4.accept();
		System.out.println("Teste esportes Ok");

	}
}
