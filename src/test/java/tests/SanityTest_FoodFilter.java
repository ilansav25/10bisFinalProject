package tests;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jdk.internal.org.jline.utils.Log;
import pages.Login;
import pages.Main;
import pages.Order;
import pages.Registration;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class SanityTest_FoodFilter {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPaht = System.getProperty("user.dir") + "\\test-output\\reportSanity.html";

	private WebDriver driver;

	//pages
	private Main main;
	private Login login;
	private Order order;
	
	private static final Logger logger = LogManager.getLogger(SanityTest_FoodFilter.class);
	private static String userName;
	private static String password;
	private static String browser;
	private static String baseUrl;
	private static String name;
	

	

	@BeforeClass
	public void beforeClass() throws ParserConfigurationException, SAXException, IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

		extent = new ExtentReports(reportPaht);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\extent-config.xml"));
		
		baseUrl = Utilities.getDataFromXML("info.xml", "website", 0);
		browser = Utilities.getDataFromXML("info.xml", "browser", 0);
		userName = Utilities.getDataFromXML("info.xml", "userName", 0);
		password = Utilities.getDataFromXML("info.xml", "password", 0);
		name = "Ilan";
		
		driver = GetDriver.getDriver(browser, baseUrl);
		
		main = new Main(driver);
		login = new Login(driver);
		order = new Order(driver);

	}

	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
	}
	

	
	/*  Prerequisite: getting into https://www.10bis.co.il/
	 * 		Given: Client click connection and  
	 * 		When: give Facebook login details and click login
	 *  	Then: Getting into 10bis as registered user
	 */
	
	
	@Test(priority = 1, enabled = true, description = "Login 10bis using Facebook")
	public void LoginUsingFacebook() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		logger.info("Going to connection page");
		main.login();
		login.doLoginFacebook(userName, password, name);
		
		logger.info("Successfully Get into 10bis as registred user page");

	}
	
	
	/*  Prerequisite: getting into https://www.10bis.co.il/
	 * 		Given: Client is an registered user at 10bis 
	 * 		When: searching for a Restaurant in search bar 
	 *  	Then: entering the restaurant page after searching for restaurant
	 */
	
	@Test(priority = 2, enabled = true, description = "resturant name search")
	public void ResturantSearch() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		
	main.searchClickRest("טורטיה פקטורי");
		logger.info("Successfully Get into resturant page");
	}
	
	
	/*  Prerequisite: getting into https://www.10bis.co.il/
	 * 		Given: Client is an registered user at 10bis 
	 * 		When: searching for a Restaurant in search bar 
	 *  	Then: entering the restaurant page and making an order but not paying due to not passing minimal order requirement
	 */
	
	@Test(priority = 3, enabled = true, description = "Filing up an minimal Food order")
	public void MinimalFoodOrder() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
	
		main.MinimalMealOrder(baseUrl);
		logger.info("Ordered an meal but didn't pay for meal");

	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
	
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
		driver.quit();

	}
	
}
