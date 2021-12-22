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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import Helper.SeleniumHelper;
import Helper.TestRunner;

@RunWith(Cucumber.class)
public class Moisturizer {
	
	private WebDriver driver = GlobalVariables.driver;
    //@TestCase3
    @When("^user click on the Pay with card button$")
    public void user_click_on_the_pay_with_card_button() throws Throwable {
    	SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CheckoutPage", "PaywithCard"))).click();
    	Thread.sleep(5000);
    }

    @Then("^user should be able to click on the purchase button for Moisturizer$")
    public void user_should_be_able_to_click_on_the_purchase_button_for_moisturizer() throws Throwable {
      	int currentTempValue;
    	do
    	{
    		driver.navigate().refresh();
    	 currentTempValue=Integer.parseInt(SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "CurrentTemperatureValue"))).getText().split(" ")[0]);		    	
    	}while(currentTempValue>19);
    	
    	SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CurrentTemperature", "BuyMoisturizerButton"))).click();
    	String moisturizerPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("MoisturizerAndSunscreenPage", "PageHeaderText"))).getText();
    	assertTrue("User is able to click on Moisturizer purchase button and navigate to next page",moisturizerPageHeader.contains("Moisturizers"));
    	
    }

    @Then("^user should be able to click on the Add button for lowest price of Aloe$")
    public void user_should_be_able_to_click_on_the_add_button_for_lowest_price_of_aloe() throws Throwable {
    	List<WebElement> elemAloeBtns = SeleniumHelper.FindElements(driver, By.xpath("//button[contains(@onclick,'Aloe')]"));
        int minPrice =Integer.MAX_VALUE;
        int minPriceIndex = 0;
        for(int i=0;i<elemAloeBtns.size();i++) {
        	String onClickStr = elemAloeBtns.get(i).getAttribute("onclick");
        	int currPrice = Integer.parseInt(onClickStr.replaceAll("[^0-9]", ""));
        	System.out.println(currPrice);
        	if(currPrice<minPrice) {
        		minPrice = currPrice;
        		minPriceIndex = i;        		
        	}
        }
        elemAloeBtns.get(minPriceIndex).click();
        Thread.sleep(5000);
    }

    @Then("^user should be able to click on the Add button for lowest price of Almond$")
    public void user_should_be_able_to_click_on_the_add_button_for_lowest_price_of_almond() throws Throwable {
    	List<WebElement> elemAlmondBtns = SeleniumHelper.FindElements(driver, By.xpath("//button[contains(@onclick,'Almond')]"));
        int minPrice =Integer.MAX_VALUE;
        int minPriceIndex = 0;
        for(int i=0;i<elemAlmondBtns.size();i++) {
        	String onClickStr = elemAlmondBtns.get(i).getAttribute("onclick");
        	int currPrice = Integer.parseInt(onClickStr.replaceAll("[^0-9]", ""));
        	System.out.println(currPrice);
        	if(currPrice<minPrice) {
        		minPrice = currPrice;
        		minPriceIndex = i;
        	}
        }
        elemAlmondBtns.get(minPriceIndex).click();
        Thread.sleep(5000);
    }

    @Then("^user should be able to click on the Cart button to navigate to check out page$")
    public void user_should_be_able_to_click_on_the_cart_button_to_navigate_to_check_out_page() throws Throwable {
    	SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("MoisturizerAndSunscreenPage", "CartElementButton"))).click();
    	String checkoutPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CheckoutPage", "CheckoutHeaderText"))).getText();
    	assertTrue("User is able to navigate to checkout page on click of cart button",checkoutPageHeader.contains("Checkout"));	    	
    }

    @Then("^user validates that the payment pop up is displayed$")
    public void user_validates_that_the_payment_pop_up_is_displayed() throws Throwable {
    	driver.switchTo().frame("stripe_checkout_app");
    	String paymentPageHeader=SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("PaymentPage", "PaymentHeaderText"))).getText();
    	System.out.println(paymentPageHeader);
    	assertTrue("User is able to navigate to checkout page on click of cart button",paymentPageHeader.contains("Stripe"));
    }

    @Then("^user should input the values for email,card number,monthyear and cvv$")
    public void user_should_input_the_values_for_emailcard_numbermonthyear_and_cvv() throws Throwable {
    	JavascriptExecutor j = (JavascriptExecutor)driver;
    	j.executeScript ("document.getElementById('email').value='abc@email.com'");
    	j.executeScript ("document.getElementById('card_number').value='4000 0027 6000 3184'");
    	j.executeScript ("document.getElementById('cc-exp').value='11/25'");
    	j.executeScript ("document.getElementById('cc-csc').value='345'");
    	Thread.sleep(5000);
    }

    @Then("^user should click on the payment button and payment success message should be displayed$")
    public void user_should_click_on_the_payment_button_and_payment_success_message_should_be_displayed() throws Throwable {
    	SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("PaymentPage", "PayButton"))).click();
    	Thread.sleep(10000);
    	String confirmationPageHeader=driver.getTitle();
    	System.out.println("Title of the confirmation page"+confirmationPageHeader);
    	
    	assertTrue("User is able to navigate to payment confirmation page",confirmationPageHeader.contains("Confirmation"));	
    	String windowId=driver.getWindowHandle();
    	driver.switchTo().window(windowId);
    	Thread.sleep(5000);
    	String payment=driver.findElement(By.xpath("//*[contains(text(),\"PAYMENT\")]")).getText();
    	assertTrue("payment success or failure message should be displayed",(payment.contains("SUCCESS")) || (payment.contains("FAILED")));
    }

    @And("^user should validate the sum of the items with the total sum$")
    public void user_should_validate_the_sum_of_the_items_with_the_total_sum() throws Throwable {
        List<WebElement> priceList=SeleniumHelper.FindElements(driver, By.xpath(SeleniumHelper.WebElementNameMap("CheckoutPage", "ItemsPrice")));
        int sum=0;
        for(WebElement price:priceList)
        {
        	int priceValue=Integer.parseInt(price.getText());
        	sum+=priceValue;
        }
        int totalAmount=Integer.parseInt(SeleniumHelper.FindElement(driver, By.xpath(SeleniumHelper.WebElementNameMap("CheckoutPage", "TotalAmount"))).getText().replaceAll("[^0-9]", ""));
    	assertEquals("User is able to navigate to checkout page on click of cart button",sum,totalAmount);
    }

}
