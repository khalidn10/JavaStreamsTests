package java_streams_sort_pagination_filter_tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create 2 lists of names 
		List<String> names1 = Arrays.asList("Abhijeet", "Donny", "Alexa", "Adam", "Raja");
		List<String> names2 = Arrays.asList("Louise", "Tommy", "Don", "Adam", "David");
		
		// Merge 2 lists of names
		Stream<String> mergedNames = Stream.concat(names1.stream(), names2.stream());
		
		// Convert alphabetically sorted distinct names back into a list
		List<String> nameList = mergedNames	.distinct()
											.sorted()
											.collect(Collectors.toList());
		System.out.println("Converted merged stream back into list with alphabetically sorted distinct names");
		
		// Print names in list
		System.out.println("");
		System.out.println("Names in List are as follows:");
		System.out.println("");
		
		// For each name in list
		for (String name : nameList)
		{
			// Print name
			System.out.println(name);
		}
		
	}

}
