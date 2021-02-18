package java_streams_sort_pagination_filter_tests;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RetrievePriceForOneVegFruit {

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
		
		// Create a WebElement instance for 'Next' button
		WebElement nextButton = driver.findElement(By.cssSelector("[aria-label='Next']"));
		
		// Create variable for storing state of 'Next' button before clicking it
		boolean nextButtonPreviouslyEnabled = true;
		
		// Create variables for required veg/fruit name and price
		String requiredVegFruitName = "Dragon fruit";
		String requiredVegFruitPrice = "Veg/fruit not found";
		
		doLoop: // Label for do-while loop
		// Do the following for each page until required veg/fruit is found
		do
		{
			// Create list for 'Veg/fruit name' column
			List<WebElement> vegFruitNamesColumn = driver.findElements(By.xpath("//tbody/tr/td[1]"));
			
			// Create list of veg/fruit names on page (using streams)
			List<String> vegFruitNames = vegFruitNamesColumn.stream().map(vftc -> vftc.getText()).collect(Collectors.toList());
			
			// For each veg/fruit name
			for (int i=0; i<vegFruitNames.size(); i++)
			{
				// If veg/fruit name is equal to the required veg/fruit name
				if (vegFruitNames.get(i).equalsIgnoreCase(requiredVegFruitName))
				{
					// Set the veg/fruit price from the corresponding row and column
					requiredVegFruitPrice = driver.findElement(By.xpath("//tbody/tr[" + String.valueOf(i+1) + "]/td[2]")).getText();
					
					// Break out of do-while loop
					break doLoop;
				}
			}
			
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
		
		// Print the required veg/fruit name and price (if found)
		System.out.println(requiredVegFruitName + " has the price: " + requiredVegFruitPrice);
	}

}
