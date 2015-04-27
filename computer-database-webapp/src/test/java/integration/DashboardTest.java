package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

		String number = driver.findElement(By.id("homeTitle")).getText();
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));

		searchbox.clear();
		searchsubmit.click();
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple");
		searchsubmit.click();
		WebElement page = driver.findElement(By.id("paginate50"));
		page.click();
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		verifyComputerList(8);
		WebElement sorted = driver.findElement(By.id("orderByComputerName"));
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		verifyComputerList(8);
		WebElement sorted = driver.findElement(By.id("orderByIntroduced"));
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		verifyComputerList(8);
		WebElement sorted = driver.findElement(By.id("orderByDiscontinued"));
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
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

		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		searchbox.clear();
		searchbox.sendKeys("Apple II");
		searchsubmit.click();
		verifyComputerList(8);
		WebElement sorted = driver.findElement(By.id("orderByCompanyName"));
		sorted.click();
		WebElement listComputer = driver.findElement(By.id("results"));
		List<WebElement> tableRows = listComputer
				.findElements(By.tagName("tr"));
		List<WebElement> tableRowTds = tableRows.get(0).findElements(
				By.tagName("td"));
		assertEquals(tableRowTds.get(4).getText(), "Apple Inc.");

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