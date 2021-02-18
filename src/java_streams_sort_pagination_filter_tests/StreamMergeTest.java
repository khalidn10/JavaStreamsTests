package java_streams_sort_pagination_filter_tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.testng.Assert;

public class StreamMergeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create 2 lists of names 
		List<String> names1 = Arrays.asList("Abhijeet", "Donny", "Alexa", "Adam", "Raja");
		List<String> names2 = Arrays.asList("Louise", "Tommy", "Don", "Adam", "David");
		
		// Merge 2 lists of names
		Stream<String> mergedNames = Stream.concat(names1.stream(), names2.stream());
		
		// Print alphabetically sorted distinct names in merged stream
		System.out.println("Alphabetically sorted distinct names in merged stream:");
		mergedNames	.distinct()
					.sorted()
					.forEach(name -> System.out.println(name));
		
		// Re-assign merged names as Stream object
		mergedNames = Stream.concat(names1.stream(), names2.stream());
		
		// Check if 'Adam' present in merged stream
		System.out.println("");
		boolean matchedName = mergedNames.anyMatch(name -> name.equalsIgnoreCase("Adam"));
		System.out.println("Name 'Adam' matched?   " + matchedName);
		Assert.assertTrue(matchedName);
	}

}
