package Helper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ini4j.Ini;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper 
{
	
	public static String WebElementNameMap(String block, String key) throws IOException
	{
		try
		{
			String CurrentDir =  System.getProperty("user.dir")+"//ObjectRepo//WebUIElements.ini";
			Ini ini = new Ini(new File(CurrentDir)); //import on WebUIElements.ini file            
			return ini.get(block,key).toString().trim();
		}
		catch(Exception e)
		{
			throw new IOException("Requested block and element name was not found in the ini file. Please check the ini file path and value provided for fetch.");
		}

	}
	
	public static WebElement WaitForElementVisiblity(WebDriver driver, By by,int timeout)
	{			
		try 
		{
			return (new WebDriverWait(driver,timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));		     
		}
		catch(NoSuchElementException e)
		{
			return null;
		}
	}

	public static WebElement FindElement(WebDriver driver, By by)
	{
		try
		{        	
			return (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		catch (NoSuchElementException e)
		{
			return null;
		}
	}	

	public static List<WebElement> FindElements(WebDriver driver, By by)
	{
		try
		{
			return (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			//return driver.findElements(by);
		}
		catch (NoSuchElementException e)
		{
			return null;
		}
	}

	public static boolean Exists(WebElement element)
	{
		if (element == null)
		{ 
			return false; 
		}
		return true;
	}

	public static boolean StopIfNotExists(WebElement element)
	{
		if (element == null)
		{ 
			throw new NoSuchElementException("No Such Element: "+ element.getClass().toString()); 
		}
		return true;
	}
}
