package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Helper.SeleniumHelper;
import Helper.TestRunner;

@RunWith(Cucumber.class)
public class WeatherShopper_SD {

	private WebDriver driver = GlobalVariables.driver;
	//@WeatherShopperTestCase1
	 @Given("^Current temperature page loads successfully$")
	    public void current_temperature_page_loads_successfully() throws Throwable {
		 driver.get("https://weathershopper.pythonanywhere.com/");
		 Thread.sleep(5000);
		 	String currentTempText=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "DashboardHeaderText"))).getText();
	        assertTrue("Current temperature page loads successfully",currentTempText.contains("Current temperature"));
	    }

	    @When("^Curren temperature value is displayed to the user$")
	    public void curren_temperature_value_is_displayed_to_the_user() throws Throwable {
	    	WebElement currentTempValue=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "CurrentTemperatureValue")));
	        assertTrue("Current temperature value is displayed to the user",currentTempValue.isDisplayed());
	    }

	    @Then("^user validates the Moisturizers and Sunscreen description should be displayed along with its title$")
	    public void user_validates_the_moisturizers_and_sunscreen_description_should_be_displayed_along_with_its_title() throws Throwable {
	    	String moisturizerTitle=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "MoisturizerTitle"))).getText();
	    	String moisturizerDescription=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "MoisturizerDescription"))).getText();
	    	String sunscreenTitle=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "SunscreenTitle"))).getText();
	    	String sunscreenDescription=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "SunscreenDescription"))).getText();
	    	String expectedMoisturizerText="Don't let cold weather ruin your skin. Use your favourite moisturizer and keep your skin stay young";
	    	String expectedSunscreenText="Treat your skin right. Don't leave your home without your favorite sunscreen. Say goodbye to sunburns";
	        assertTrue("Moisturizer title and description is displayed",moisturizerTitle.contains("Moisturizers") && moisturizerDescription.contains(expectedMoisturizerText));
	        assertTrue("Sunscreen title and description is displayed",sunscreenTitle.contains("Sunscreens") && sunscreenDescription.contains(expectedSunscreenText));
	    }

	    @And("^user validates that the buy moisturizer and sunscreen button should be displayed on the screen$")
	    public void user_validates_that_the_buy_moisturizer_and_sunscreen_button_should_be_displayed_on_the_screen() throws Throwable {
	    	WebElement moisturizerButton =SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuyMoisturizerButton")));
	    	WebElement sunscreenButton =SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuySunscreenButton")));
	        assertTrue("User Validates that the purchase button of moisturizer and sunscreen is displayed ",moisturizerButton.isDisplayed() && sunscreenButton.isDisplayed());
	    }
	    
	    //@WeatherShopperTestCase2
	    @Then("^user should click on the respective button to purchase moisturizers and sunscreen on the basis of current temperature value$")
	    public void user_should_click_on_the_respective_button_to_purchase_moisturizers_and_sunscreen_on_the_basis_of_current_temperature_value() throws Throwable {
	    	int currentTempValue=Integer.parseInt(SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "CurrentTemperatureValue"))).getText().split(" ")[0]);
	    	System.out.println("Current Temperature Value"+currentTempValue);
	    	if(currentTempValue <=19)
	    	{
	    		SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuyMoisturizerButton"))).click();
	    		String moisturizerPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("MoisturizerAndSunscreenPage", "PageHeaderText"))).getText();
	    		assertTrue("User is able to click on Moisturizer purchase button and navigate to next page",moisturizerPageHeader.contains("Moisturizers"));
	    	}
	    	else if(currentTempValue>=34)
	    	{
	    		SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuySunscreenButton"))).click();
	    		String sunscreenPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("MoisturizerAndSunscreenPage", "PageHeaderText"))).getText();
	    		assertTrue("User is able to click on Sunscreen purchase button and navigate to next page",sunscreenPageHeader.contains("Sunscreens"));
	    	}
	    	else
	    	{
	    		assertFalse("Current Temperature Value is neither less than 19 nor greater than 34",currentTempValue<=19 && currentTempValue>=34);
	    	}	    	
	    }	    
}