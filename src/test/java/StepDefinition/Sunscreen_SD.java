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
public class Sunscreen_SD {
	private WebDriver driver = GlobalVariables.driver;
	//@TestCase4   
    @Then("^user should be able to click on the purchase button for Sunscreen$")
    public void user_should_be_able_to_click_on_the_purchase_button_for_sunscreen() throws Throwable {
    	int currentTempValue=Integer.parseInt(SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "CurrentTemperatureValue"))).getText().split(" ")[0]);		    	
	    	while(!(currentTempValue>=34))
    	{
    		 driver.navigate().refresh();
   	    	 currentTempValue=Integer.parseInt(SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "CurrentTemperatureValue"))).getText().split(" ")[0]);		    	  	    	
    	}  	
    	SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuySunscreenButton"))).click();
    	String sunscreenPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("MoisturizerAndSunscreenPage", "PageHeaderText"))).getText();
    	assertTrue("User is able to click on Moisturizer purchase button and navigate to next page",sunscreenPageHeader.contains("Sunscreen"));

    }

    @Then("^user should be able to click on the Add button for lowest price of SPF50$")
    public void user_should_be_able_to_click_on_the_add_button_for_lowest_price_of_spf50() throws Throwable {
    	List<WebElement> elemSPF50Btns = SeleniumHelper.FindElements(driver, By.xpath("//button[contains(@onclick,'SPF-50')]"));
        int minPrice =Integer.MAX_VALUE;
        int minPriceIndex = 0;
        for(int i=0;i<elemSPF50Btns.size();i++) {
        	String onClickStr = elemSPF50Btns.get(i).getAttribute("onclick");
        	int currPrice = Integer.parseInt(onClickStr.replaceAll("[^0-9]", ""));
        	System.out.println(currPrice);
        	if(currPrice<minPrice) {
        		minPrice = currPrice;
        		minPriceIndex = i;        		
        	}
        }
        elemSPF50Btns.get(minPriceIndex).click();
        Thread.sleep(5000);
    }

    @Then("^user should be able to click on the Add button for lowest price of SPF30$")
    public void user_should_be_able_to_click_on_the_add_button_for_lowest_price_of_spf30() throws Throwable {
    	List<WebElement> elemSPF30Btns = SeleniumHelper.FindElements(driver, By.xpath("//button[contains(@onclick,'SPF-30')]"));
        int minPrice =Integer.MAX_VALUE;
        int minPriceIndex = 0;
        for(int i=0;i<elemSPF30Btns.size();i++) {
        	String onClickStr = elemSPF30Btns.get(i).getAttribute("onclick");
        	int currPrice = Integer.parseInt(onClickStr.replaceAll("[^0-9]", ""));
        	System.out.println(currPrice);
        	if(currPrice<minPrice) {
        		minPrice = currPrice;
        		minPriceIndex = i;        		
        	}
        }
        elemSPF30Btns.get(minPriceIndex).click();
        Thread.sleep(5000);
    }

}
