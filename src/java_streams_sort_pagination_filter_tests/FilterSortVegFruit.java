package java_streams_sort_pagination_filter_tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FilterSortVegFruit {

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
		
		// Set filter string
		String filterString = "Ap";
		
		// Create lists for filtered veg/fruit names and prices
		List<String> filteredVegFruitNamesExpected = new ArrayList<String>();
		List<String> filteredVegFruitPricesExpected = new ArrayList<String>();
		
		// Do the following for each page having veg and fruit
		do
		{
			// Create list for 'Veg/fruit name' column
			List<WebElement> vegFruitNamesColumn = driver.findElements(By.xpath("//tbody/tr/td[1]"));
			
			// Create list for filtered veg/fruit columns on page (using streams)
			List<WebElement> filteredVegFruitNamesColumnTemp = vegFruitNamesColumn.stream()
																.filter(vftc -> vftc.getText().toLowerCase().contains(filterString.toLowerCase()))
																.collect(Collectors.toList());
			
			// Create list for filtered veg/fruit names on page (using streams)
			List<String> filteredVegFruitNamesTemp = filteredVegFruitNamesColumnTemp.stream()
														.map(vftc -> vftc.getText())
														.collect(Collectors.toList());
			
			// Create list for filtered veg/fruit prices on page (using streams)
			List<String> filteredVegFruitPricesTemp = filteredVegFruitNamesColumnTemp.stream()
														.map(vftc -> priceVegFruit(vftc))
														.collect(Collectors.toList());
			
			// Add veg/fruit names and prices to corresponding lists
			filteredVegFruitNamesExpected.addAll(filteredVegFruitNamesTemp);
			filteredVegFruitPricesExpected.addAll(filteredVegFruitPricesTemp);
			
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
		
		// Reverse the order of the filtered veg/fruit name (using stream) and price lists
		filteredVegFruitNamesExpected = filteredVegFruitNamesExpected.stream().sorted().collect(Collectors.toList());
		Collections.reverse(filteredVegFruitPricesExpected);
		
		// Description for veg/fruit names and prices
		System.out.println("");
		System.out.print(RetrieveVegFruitDetails.padSpacesRight("EXPECTED VEG/FRUIT",20));
		System.out.println("EXPECTED PRICE");
		System.out.println("");
		
		// For each veg and fruit
		for (int i=0; i<filteredVegFruitNamesExpected.size(); i++)
		{
			// Print the expected veg/fruit names and prices
			System.out.print(RetrieveVegFruitDetails.padSpacesRight(filteredVegFruitNamesExpected.get(i),22));
			System.out.println(filteredVegFruitPricesExpected.get(i));
		}
		
		// Reset variable for storing state of 'Next' button before clicking it
		nextButtonPreviouslyEnabled = true;
		
		// Click on 'First' button
		driver.findElement(By.cssSelector("[aria-label='First']"));
		
		// Enter filter string into 'Search' field
		driver.findElement(By.id("search-field")).sendKeys(filterString);
		
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
		
		// Create lists for filtered veg/fruit names and prices
		List<String> filteredVegFruitNamesActual = new ArrayList<String>();
		List<String> filteredVegFruitPricesActual = new ArrayList<String>();
		
		// Do the following for each page having veg and fruit
		do
		{
			// Create list for 'Veg/fruit name' column
			List<WebElement> vegFruitNamesColumn = driver.findElements(By.xpath("//tbody/tr/td[1]"));
			
			// Create lists for names and prices of filtered veg/fruit on page (using streams)
			List<String> vegFruitNamesTemp = vegFruitNamesColumn.stream().map(vftc -> vftc.getText()).collect(Collectors.toList());
			List<String> vegFruitPricesTemp = vegFruitNamesColumn.stream().map(vftc -> priceVegFruit(vftc)).collect(Collectors.toList());
			
			// Add veg/fruit names and prices to corresponding lists
			filteredVegFruitNamesActual.addAll(vegFruitNamesTemp);
			filteredVegFruitPricesActual.addAll(vegFruitPricesTemp);
			
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
		
		// Description for veg/fruit names and prices
		System.out.println("");
		System.out.print(RetrieveVegFruitDetails.padSpacesRight("ACTUAL VEG/FRUIT",20));
		System.out.println("ACTUAL PRICE");
		System.out.println("");
		
		// For each veg and fruit
		for (int i=0; i<filteredVegFruitNamesActual.size(); i++)
		{
			// Print the actual veg/fruit names and prices
			System.out.print(RetrieveVegFruitDetails.padSpacesRight(filteredVegFruitNamesActual.get(i),22));
			System.out.println(filteredVegFruitPricesActual.get(i));
		}
		
		// New line
		System.out.println("");
		
		// If actual veg/fruit names match expected ones
		if (filteredVegFruitNamesActual.equals(filteredVegFruitNamesExpected))
		{
			// Print names are correct
			System.out.println("Displayed veg/fruit names are correct");
		}
		// Otherwise
		else
		{
			// Print names are incorrect
			System.out.println("Displayed veg/fruit names are incorrect");
		}
		
		// If actual veg/fruit prices match expected ones
		if (filteredVegFruitPricesActual.equals(filteredVegFruitPricesExpected))
		{
			// Print prices are correct
			System.out.println("Displayed veg/fruit prices are correct");
		}
		// Otherwise
		else
		{
			// Print prices are incorrect
			System.out.println("Displayed veg/fruit prices are incorrect");
		}
		
	}
	
	// Method to return price of veg/fruit
	private static String priceVegFruit(WebElement vf)
	{
		// Set String variable to price of sent veg/fruit's table cell element
		String pvf = vf.findElement(By.xpath("following-sibling::td[1]")).getText();
		
		// Return price
		return pvf;
	}

}
