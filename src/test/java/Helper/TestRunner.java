package Helper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import StepDefinition.GlobalVariables;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "StepDefinition" }, // the path of the step
																						// definition files
																						// @Functional_Block
																						// @Commands_&_Events
		plugin = { "pretty", "junit:test-output/cucumber.xml", "json:test-output/cucumber.json" }// to generate
																									// different types
																									// of reporting
// plugin = { "pretty","com.epam.reportportal.cucumber.ScenarioReporter",
// "junit:test-output/cucumber.xml", "json:test-output/cucumber.json",
// "html:test-output"},//to generate different types of reporting
)
public class TestRunner extends AbstractTestNGCucumberTests {

	public WebDriver driver;

//	 @Override
//	  @DataProvider(parallel = true)
//	  public Object[][] scenarios() {
//	    return super.scenarios();
//	  }
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) throws IOException, InterruptedException {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
		}

		driver.get("https://weathershopper.pythonanywhere.com/");
		WebElement dashboardheader = SeleniumHelper.WaitForElementVisiblity(driver,
				By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "DashboardHeaderText")), 20);
		if (!SeleniumHelper.StopIfNotExists(dashboardheader)) {
			throw new NoSuchElementException("Landing Page failed to load within 20 seconds!");
		}
		Thread.sleep(10000);

		GlobalVariables.driver = driver;
	}

	@AfterClass
	public void teardown() {
		GlobalVariables.driver.quit();
		GlobalVariables.driver = null;
		System.out.println("End of Test Execution!");
	}
}
