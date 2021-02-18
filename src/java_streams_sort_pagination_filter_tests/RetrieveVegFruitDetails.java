package java_streams_sort_pagination_filter_tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RetrieveVegFruitDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Load GreenKart website
		driver.get("https://www.rahulshettyacademy.com/seleniumPractise/");
		
		// Select 'Top Deals' link
		driver.findElement(By.cssSelector("[href='#/offers']")).click();
		
		// Retrieve handles to both windows
		Set<String> windowHandles = driver.getWindowHandles();
		
		// Create instance of iterator to iterate window handles
		Iterator<String> windowIterator = windowHandles.iterator();
		
		// Iterate twice through window handles
		windowIterator.next();
		String childWindowHandle = windowIterator.next();
		
		// Switch to veg/fruit details window
		driver.switchTo().window(childWindowHandle);
		
		// Try to
		try 
		{
			// Click on veg/fruit descending icon
			driver.findElement(By.cssSelector(".sort-icon.sort-descending")).click();
		}
		// If no such icon found
		catch (NoSuchElementException e)
		{
			// Print icon not found
			System.out.println("Veg/fruit descending icon not found");
		}
		
		// Create a WebElement instance for 'Next' button
		WebElement nextButton = driver.findElement(By.cssSelector("[aria-label='Next']"));
		
		// Create variable for storing state of 'Next' button before clicking it
		boolean nextButtonPreviouslyEnabled = true;
		
		// Create lists for all veg/fruit names, prices and discount prices
		List<String> vegFruitNames = new ArrayList<String>();
		List<String> vegFruitPrices = new ArrayList<String>(); 
		List<String> vegFruitDisPrices = new ArrayList<String>(); 
		
		// Do the following for each page having veg and fruit
		do
		{
			// Create lists for table columns having veg/fruit names, prices and discounted prices on page
			List<WebElement> vegFruitNamesColumn = driver.findElements(By.xpath("//tbody/tr/td[1]"));
			List<WebElement> vegFruitPricesColumn = driver.findElements(By.xpath("//tbody/tr/td[2]"));
			List<WebElement> vegFruitDisPricesColumn = driver.findElements(By.xpath("//tbody/tr/td[3]"));
			
			// Create lists for names, prices and discounted prices of all veg/fruit on page (using streams)
			List<String> vegFruitNamesTemp = vegFruitNamesColumn.stream().map(vftc -> vftc.getText()).collect(Collectors.toList());
			List<String> vegFruitPricesTemp = vegFruitPricesColumn.stream().map(vftc -> vftc.getText()).collect(Collectors.toList());
			List<String> vegFruitDisPricesTemp = vegFruitDisPricesColumn.stream().map(vftc -> vftc.getText()).collect(Collectors.toList());
			
			// Add names, prices and discounted prices to corresponding lists
			vegFruitNames.addAll(vegFruitNamesTemp);
			vegFruitPrices.addAll(vegFruitPricesTemp);
			vegFruitDisPrices.addAll(vegFruitDisPricesTemp);
			
			// If 'Next' button is enabled
			if (!Boolean.parseBoolean(nextButton.getAttribute("aria-disabled")))
			{
				// Click on 'Next' button
				nextButton.click();
			}
			// Otherwise
			else
			{
				// Update previous state of 'Next' button
				nextButtonPreviouslyEnabled = false;
			}
			
		} while (nextButtonPreviouslyEnabled); //Stop looping if previous state of 'Next' button has been updated
		
		// Description for veg/fruit names, prices and discounted prices
		System.out.println("");
		System.out.print(padSpacesRight("VEG/FRUIT",15));
		System.out.print(padSpacesRight("PRICES",9));
		System.out.println("DISCOUNTED PRICES");
		System.out.println("");
		
		// For each veg and fruit
		for (int i=0; i<vegFruitNames.size(); i++)
		{
			// Print out veg/fruit names, prices and discounted prices
			System.out.print(padSpacesRight(vegFruitNames.get(i),17));
			System.out.print(padSpacesRight(vegFruitPrices.get(i),14));
			System.out.println(vegFruitDisPrices.get(i));
		}
		
	}
	
	// Method to pad out given string by given no. of spaces
	public static String padSpacesRight(String s, int i)
	{
		// Set required length of string to i 
		int requiredLength = i;
		
		// Set string length to actual length
		int stringLength = s.length();
		
		// Set no. of spaces needed to difference between above 2 string lengths
		int noOfSpaces = requiredLength - stringLength;
		
		// If no. of spaces needed is more than 0
		if (noOfSpaces > 0)
		{
			// For no. of spaces needed
			for (int j=0; j<=noOfSpaces; j++)
			{
				// Add an extra space to the right 
				s = s.concat(" ");
			}
		}
		
		// Return the padded string
		return s;
	}

}
