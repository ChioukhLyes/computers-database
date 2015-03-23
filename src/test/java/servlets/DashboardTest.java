package servlets;

import com.thoughtworks.selenium.SeleneseTestCase;

@SuppressWarnings("deprecation")
public class DashboardTest extends SeleneseTestCase {
	
    public void setUp() throws Exception {
        setUp("http://localhost:8080/computers-database", "/dashboard");
    }
    
//    public void testTemp script() throws Exception {
//        selenium.open("/BrewBizWeb/");
//        selenium.click("link=Start The BrewBiz Example");
//        selenium.waitForPageToLoad("30000");
//        selenium.type("name=id", "bert");
//        selenium.type("name=Password", "biz");
//        selenium.click("name=dologin");
//        selenium.waitForPageToLoad("30000");
//    }
}