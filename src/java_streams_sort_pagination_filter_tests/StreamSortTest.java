package java_streams_sort_pagination_filter_tests;

import java.util.Arrays;
import java.util.List;

public class StreamSortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create list of names 
		List<String> names = Arrays.asList("Abhijeet", "Donny", "Alexa", "Adam", "Raja");
		
		// Filter names on last character 'a', turn to uppercase and print
		System.out.println("Uppercase names ending with 'A':");
		names.stream().filter(name -> name.endsWith("a")).map(name -> name.toUpperCase()).forEach(name -> System.out.println(name));
		
		// Filter names on first character 'A', turn to uppercase, sort and print
		System.out.println("");
		System.out.println("Alphabetically sorted uppercase names starting with 'A':");
		names.stream()	.filter(name -> name.startsWith("A"))
			 			.map(name -> name.toUpperCase())
			 			.sorted()
			 			.forEach(name -> System.out.println(name));
		
	}

}
