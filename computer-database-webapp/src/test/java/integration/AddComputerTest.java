/*
 * root
 * 
 * AddComputerTest.java - 2015
 */
package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import org.openqa.selenium.support.ui.Select;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardTest.
 */
public class AddComputerTest {

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
		driver.get("http://localhost:8080/computer-database-webapp/addComputer");
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
		WebElement logoutbutton = driver.findElement(By.id("logout-button"));
		logoutbutton.click();
		WebElement confirmationlogout = driver.findElement(By
				.id("confirmationlogout"));
		confirmationlogout.click();
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
		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		assertEquals(searchsubmit.getAttribute("value"), "Ajouter");
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
		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		assertEquals(searchsubmit.getAttribute("value"), "Add");
	}

	/**
	 * Test submit empty form.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSubmitEmptyForm() throws Exception {

		// GIVEN
		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		// WHENE
		searchsubmit.click();
		// THENE
		WebElement computername = driver.findElement(By.id("computerName"));
		assertEquals(computername.getAttribute("class"), "form-control error");

	}

	/**
	 * Test submit invalid introduced date.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSubmitInvalidIntroducedDate() throws Exception {

		// GIVEN
		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		WebElement computername = driver.findElement(By.id("computerName"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		Select select = new Select(driver.findElement(By.id("companyId")));
		select.selectByVisibleText("RCA");
		computername.sendKeys("Name");
		discontinued.sendKeys("09-28-1989");
		introduced.sendKeys("$$$$");
		// WHENE
		searchsubmit.click();
		// THENE
		assertEquals(introduced.getAttribute("class"), "form-control error");

	}

	/**
	 * Test submit invalid discountinued date.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSubmitInvalidDiscountinuedDate() throws Exception {

		// GIVEN
		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		WebElement computername = driver.findElement(By.id("computerName"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		Select select = new Select(driver.findElement(By.id("companyId")));
		select.selectByVisibleText("RCA");
		computername.sendKeys("Name");
		introduced.sendKeys("09-28-1989");
		discontinued.sendKeys("$$$");
		// WHENE
		searchsubmit.click();
		// THENE
		assertEquals(discontinued.getAttribute("class"), "form-control error");
	}

	/**
	 * Test submit invalid introduced date fr.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSubmitInvalidIntroducedDateFR() throws Exception {

		// GIVEN
		WebElement flagLanuage = driver.findElement(By.id("languagesChange"));
		WebElement changeToFr = driver.findElement(By.id("languagesChangeFr"));
		flagLanuage.click();
		changeToFr.click();

		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		WebElement computername = driver.findElement(By.id("computerName"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		Select select = new Select(driver.findElement(By.id("companyId")));
		// WHENE
		select.selectByVisibleText("RCA");
		computername.sendKeys("Name");
		discontinued.sendKeys("09-28-1989");
		introduced.sendKeys("09-28-1989");
		searchsubmit.click();
		// THENE
		assertEquals(introduced.getAttribute("class"), "form-control error");

	}

	/**
	 * Test submit invalid discountinued date en.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSubmitInvalidDiscountinuedDateEn() throws Exception {

		// GIVEN
		WebElement flagLanuage = driver.findElement(By.id("languagesChange"));
		WebElement changeToEn = driver.findElement(By.id("languagesChangeEn"));
		flagLanuage.click();
		changeToEn.click();

		WebElement searchsubmit = driver.findElement(By.id("submitAdd"));
		WebElement computername = driver.findElement(By.id("computerName"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		Select select = new Select(driver.findElement(By.id("companyId")));
		// WHENE
		select.selectByVisibleText("RCA");
		computername.sendKeys("Name");
		introduced.sendKeys("09-28-1989");
		discontinued.sendKeys("28-09-1989");
		searchsubmit.click();
		// THENE
		assertEquals(discontinued.getAttribute("class"), "form-control error");
	}
	
	

}