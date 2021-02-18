package java_streams_sort_pagination_filter_tests;

//import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamFilterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Count the number of names in a List that start with the letter 'A'
		//@Test
		
		// Create ArrayList of Strings named 'names' 
		/*ArrayList<String> names = new ArrayList<String>();
		
		// Add 5 names
		names.add("Abhijeet");
		names.add("Donny");
		names.add("Alex");
		names.add("Adam");
		names.add("Raj");
		
		// Initialise variables required in 'for' loop
		String name = "";
		long nameStartsWithACount = 0;
				
		// For each name in names ArrayList
		for (int i=0; i<names.size(); i++)
		{
			// Set name to name in ArrayList with index i
			name = names.get(i);
			
			// If name starts with letter 'A'
			if(name.startsWith("A"))
			{
				System.out.println(name);
				// Increase count by 1
				nameStartsWithACount++;
			}
		}*/
		
		// Replace above code with the use of streams instead
		// The line below shows how elements from a collection object, such as an ArrayList, can be used to create a Stream 
		//names.stream().filter(name -> name.startsWith("A")).count();
		// You can create an initial stream of any type of object (in this case Strings)
		//    instead of dealing with Collections or other types of objects  
		Stream<String> names = Stream.of("Abhijeet", "Donny", "Alex", "Adam", "Raj");
		long nameStartsWithACount = 0L;
		
		// The term 'name->name.startsWith("A")' is the lambda expression
		
		// The left side of the arrow operator is the parameter for the lambda expression, i.e. the String variable 'name'
		// The String variable 'name' fulfils the same function as the String variable 'name' in the 'for' loop above
		// Interpret this to mean that each object in the 'names' ArrayList should be assigned to the variable 'name'
		
		// The right side of the arrow operator specifies the action/condition of the lambda expression, i.e. name.startsWith("A")
		// The condition 'name.startsWith("A")' fulfils the same function as the condition in the 'if' statement in the 'for' loop above
		// Interpret this to mean that each object in the 'names' ArrayList should be filtered if it 'startsWith("A")'
		
		// Note that whereas the 'for' loop goes through each String one by one,
		//    the filter() method is a lot quicker and more powerful
		//    as it scans the entire ArrayList and simultaneously filters as per the condition(s)
		
		// If any objects meet the condition of the filter() method, then the filter() method will return true
		// Otherwise, if no objects meet the condition of the filter() method, it will return false 
		
		// Following are the 3 stages of the working of streams:
		// Stage 1) The Stream.of() method creates a stream
		// Stage 2) The filter() and peek() methods are the intermediate operations
		// Stage 3) The count() method is the terminal operation
		
		// Print out names starting with 'A' and ending with 't'
		System.out.println("Names starting with A and ending with t (and count):");
		System.out.println(nameStartsWithACount = names.filter(name -> name.startsWith("A") && name.endsWith("t")).peek(name -> System.out.println(name)).count());
		
		// Re-assign names as Stream object
		names = Stream.of("Abhijeet", "Donny", "Alex", "Adam", "Raj");
		// Print out names starting with 'A'
		System.out.println("");
		System.out.println("Names starting with A:");
		nameStartsWithACount = names.filter(name -> {	
														String a = "A"; 
														return name.startsWith(a);
													})
									.peek(name -> System.out.println(name))
									.count();
		
		// Print count of names starting with 'A'
		System.out.println("Number of names starting with A: " + nameStartsWithACount);
		
		// Re-assign names as Stream object
		names = Stream.of("Abhijeet", "Donny", "Alex", "Adam", "Raj");
		// Print out names with more than 4 characters
		System.out.println("");
		System.out.println("Names not with 4 characters:");
		names.filter(name -> name.length()<4 || name.length()>4).forEach(name -> System.out.println(name));
		
		// Re-assign names as Stream object
		names = Stream.of("Abhijeet", "Donny", "Alex", "Adam", "Raj");
		// Print out first name with more than 4 characters
		System.out.println("");
		System.out.println("First name with more than 4 characters:");
		names.filter(name -> name.length()>4).limit(1).forEach(name -> System.out.println(name));
	}

}
