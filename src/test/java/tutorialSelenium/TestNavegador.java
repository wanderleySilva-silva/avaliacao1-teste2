package tutorialSelenium;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TestNavegador {

	static WebDriver driver;
	private static Document doc;
	private static NodeList listaProcedimentos;
	private static NodeList listaCasos;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
		driver = new FirefoxDriver();

		try {
			File inputFile = new File("src/test/java/Dados.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			listaProcedimentos = doc.getElementsByTagName("procedimento");

		} catch (Exception e) {
			System.out.println("Erro na leitura do documento");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		// driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("https://calculadoraonline.com.br/basica/");
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void multiplicacaoTest() {

		Element procedimento = (Element) listaProcedimentos.item(0);
		listaCasos = procedimento.getElementsByTagName("caso");

		for (int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.get("https://calculadoraonline.com.br/basica/");
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero1").item(0).getTextContent());
			driver.findElement(By.id("b20")).click();
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero2").item(0).getTextContent());
			driver.findElement(By.id("b27")).click();

			WebElement resultadoMultiplicacao = driver.findElement(By.cssSelector(
					"#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(1) > div"));

			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),
					resultadoMultiplicacao.getText());

		}
	}

	@Test
	public void divisaoTest() {

		Element procedimento = (Element) listaProcedimentos.item(1);
		listaCasos = procedimento.getElementsByTagName("caso");

		for (int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.get("https://calculadoraonline.com.br/basica/");
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero1").item(0).getTextContent());
			driver.findElement(By.id("b28")).click();
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero2").item(0).getTextContent());
			driver.findElement(By.id("b27")).click();

			WebElement resultadoDivisao = driver.findElement(By.cssSelector(
					"#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(1) > div"));

			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(), resultadoDivisao.getText());

		}

	}

	@Test
	public void subtracaoTest() {

		Element procedimento = (Element) listaProcedimentos.item(2);
		listaCasos = procedimento.getElementsByTagName("caso");

		for (int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.get("https://calculadoraonline.com.br/basica/");
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero1").item(0).getTextContent());
			driver.findElement(By.id("b12")).click();

			// Colocar parentes de abertura
			driver.findElement(By.id("b5")).click();
			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero2").item(0).getTextContent());

			// Colocar parentes de fechamento
			driver.findElement(By.id("b13")).click();

			driver.findElement(By.id("b27")).click();

			WebElement resultadoSubtracao = driver.findElement(By.cssSelector(
					"#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(1) > div"));

			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(), resultadoSubtracao.getText());

		}

	}

	@Test
	public void potenciacaoTest() {

		Element procedimento = (Element) listaProcedimentos.item(3);
		listaCasos = procedimento.getElementsByTagName("caso");

		for (int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.get("https://calculadoraonline.com.br/basica/");

			driver.findElement(By.id("b31")).click();

			driver.findElement(By.id("cx31_0")).sendKeys(caso.getElementsByTagName("base").item(0).getTextContent());

			driver.findElement(By.id("cx31_1"))
					.sendKeys(caso.getElementsByTagName("expoente").item(0).getTextContent());

			driver.findElement(By.cssSelector("#dpb31 > button")).click();

			WebElement resultadoPotenciacao = driver.findElement(By.cssSelector(
					"#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(1) > div"));

			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),
					resultadoPotenciacao.getText());

		}
	}

	@Test
	public void adicaoTest() {

		Element procedimento = (Element) listaProcedimentos.item(4);
		listaCasos = procedimento.getElementsByTagName("caso");

		for (int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.get("https://calculadoraonline.com.br/basica/");

			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero1").item(0).getTextContent());
			driver.findElement(By.id("b4")).click();

			driver.findElement(By.cssSelector("#TIExp"))
					.sendKeys(caso.getElementsByTagName("numero2").item(0).getTextContent());
			
			driver.findElement(By.id("b27")).click();
			
			WebElement resultadoSoma = driver.findElement(By.cssSelector(
					"#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(1) > div"));

			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(), resultadoSoma.getText());
		}

	}

}
