package integration;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardTest.
 */
public class DashboardTest {

	/** The driver. */
	private static WebDriver driver;

	/** The base url. */
	private static String baseUrl;

	/** The verification errors. */
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * Open driver.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void openDriver() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-database-webapp/dashboard";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl);
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	/**
	 * Close driver.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public static void closeDriver() throws Exception {
		driver.quit();
	}

	/**
	 * Test search empty.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchEmpty() throws Exception {

		//GIVEN
		String number = driver.findElement(By.id("homeTitle")).getText();
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchsubmit.click();
		//THENE
		assertEquals(number, driver.findElement(By.id("homeTitle")).getText());

	}

	/**
	 * Test search word.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchWord() throws Exception {

		//GIVE
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		//THENE
		verifyComputerList(8);
	}

	/**
	 * Test search word with changin page.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSearchWordWithChanginPage() throws Exception {

		//GIVEN
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchbox.sendKeys("Apple");
		searchsubmit.click();
		WebElement page = driver.findElement(By.id("paginate50"));
		page.click();
		//THENE
		verifyComputerList(49);
	}

	/**
	 * Test paging100.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testPaging100() throws Exception {

		WebElement page = driver.findElement(By.id("paginate100"));
		page.click();
		verifyComputerList(100);
	}

	/**
	 * Test paging50.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testPaging50() throws Exception {

		WebElement page = driver.findElement(By.id("paginate50"));
		page.click();
		verifyComputerList(50);
	}

	/**
	 * Test paging10.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testPaging10() throws Exception {

		WebElement page = driver.findElement(By.id("paginate10"));
		page.click();
		verifyComputerList(10);
	}

	/**
	 * Test order by name.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testOrderByName() throws Exception {

		//GIVEN
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		//THENE
		verifyComputerList(8);
		WebElement sorted = driver.findElement(By.id("orderByComputerName"));
		//GIVEN
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		//THENE
		assertEquals(tableRowTds.get(1).getText(), "Apple III Plus");

	}

	/**
	 * Test order by introduced.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testOrderByIntroduced() throws Exception {

		//GIVEN
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		//THENE
		verifyComputerList(8);
		//GIVEN
		WebElement sorted = driver.findElement(By.id("orderByIntroduced"));
		//WHNE
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		//THENE
		assertEquals(tableRowTds.get(2).getText(), "1983-12-01");

	}

	/**
	 * Test order by discontinued.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testOrderByDiscontinued() throws Exception {

		//GIVEN
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		//THENE
		verifyComputerList(8);
		//GIVEN
		WebElement sorted = driver.findElement(By.id("orderByDiscontinued"));
		//WHENE
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		//THENE
		assertEquals(tableRowTds.get(3).getText(), "1993-10-01");

	}

	/**
	 * Test order by company name.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testOrderByCompanyName() throws Exception {

		//GIVEN
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		//WHENE
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		//THEN
		verifyComputerList(8);
		//GIVE
		WebElement sorted = driver.findElement(By.id("orderByCompanyName"));
		//WHENE
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		//THEN
		assertEquals(tableRowTds.get(4).getText(), "Apple Inc.");

	}

	/**
	 * Test change language fr.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testChangeLanguageFr() throws Exception {

		// GIVEN
		WebElement flagLanuage = driver.findElement(By.id("languagesChange"));
		WebElement changeToFr = driver.findElement(By.id("languagesChangeFr"));
		// WHENE
		flagLanuage.click();
		changeToFr.click();
		// THENE
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		assertEquals(searchsubmit.getAttribute("value"), "Filtrer par nom");
	}

	/**
	 * Test change language en.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testChangeLanguageEn() throws Exception {

		// GIVEN
		WebElement flagLanuage = driver.findElement(By.id("languagesChange"));
		WebElement changeToEn = driver.findElement(By.id("languagesChangeEn"));
		// WHENE
		flagLanuage.click();
		changeToEn.click();
		// THENE
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		assertEquals(searchsubmit.getAttribute("value"), "Filter by name");
	}

	/**
	 * Test edition autorisation.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEditionAutorisation() throws Exception {

		// GIVEN
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		WebElement link = tableRowTds.get(1).findElement(By.tagName("a"));
		// WHENE
		link.click();
		// THENE
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/computer-database-webapp/login");
	}

	/**
	 * Test long in button.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testLongInButton() throws Exception {

		// GIVEN
		WebElement link = driver.findElement(By.id("logout-button"));
		// WHENE
		link.click();
		// THENE
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/computer-database-webapp/login");
	}
	
	@Test
	public void testAddButton() throws Exception {

		// GIVEN
		WebElement link = driver.findElement(By.id("addComputer"));
		// WHENE
		link.click();
		// THENE
		assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/computer-database-webapp/login");
	}
	
	@Test
	public void testLogInPageSuccess() throws Exception {

		// GIVEN
		WebElement link = driver.findElement(By.id("logout-button"));
		// WHENE
		link.click();
		WebElement textinput = driver.findElement(By.id("textinput"));
		WebElement passwordinput = driver.findElement(By.id("passwordinput"));
		WebElement submit = driver.findElement(By.id("button1id"));
		textinput.clear();
		textinput.sendKeys("Admin1");
		passwordinput.clear();
		passwordinput.sendKeys("123456");
		submit.click();
		// THENE
		WebElement logoutbutton = driver.findElement(By.id("logout-button")); 
		assertEquals(logoutbutton.getAttribute("class"), "btn btn-danger pull-right");
		logoutbutton.click();
		WebElement confirmationlogout = driver.findElement(By.id("confirmationlogout")); 
		confirmationlogout.click();
		
	}

	@Test
	public void testLogInPageError() throws Exception {

		// GIVEN
		WebElement link = driver.findElement(By.id("logout-button"));
		// WHENE
		link.click();
		WebElement textinput = driver.findElement(By.id("textinput"));
		WebElement passwordinput = driver.findElement(By.id("passwordinput"));
		WebElement submit = driver.findElement(By.id("button1id"));
		textinput.clear();
		textinput.sendKeys("Admin1");
		passwordinput.clear();
		passwordinput.sendKeys("$$$$");
		submit.click();
		// THENE
		WebElement logoutbutton = driver.findElement(By.id("logout-button")); 
		assertEquals(logoutbutton.getAttribute("class"), "btn btn-success pull-right");
	}
	
	/**
	 * Calculate items *.
	 *
	 * @param size
	 *            the size
	 */
	private void verifyComputerList(int size) {
		WebElement listComputer = driver.findElement(By.id("results"));

		List<WebElement> computers = listComputer
				.findElements(By.tagName("tr"));
		assertEquals(size, computers.size());
	}
}