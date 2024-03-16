import files.payload;
import io.restassured.path.json.JsonPath;

public class JsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.PriceList());
		
		//print the number of courses
		
		int count = js.getInt("courses.size()"); // getInt method of jsonpath helps to get the integer value.
		 									 	// we should get the size of the array using the size method								    
		System.out.println("The Number of  courses size is " +count);
		
		//2 .printing purchase amount
		int pamount = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount is " + pamount);
		
		
		//3 print title of first course
		
		String titlefirst = js.get("courses[0].title");	
		System.out.println("first title name is " + titlefirst);
		
		
		for(int i=0;i<=count;i++)
		{	
			//String titlenames = js.get("courses["+i+"].title");
		//	int prices = js.getInt("courses["+i+"].price");
		//	int copies = js.getInt("courses["+i+"].copies");
	//		System.out.println("titlenames are " + titlenames);
		//	System.out.println("prices are " + prices);
		//	System.out.println("copies are " + copies);
		}
		
		
		//print copies sold by the RPA course
		for(int i=0;i<count;i++) 
		{
			
			String titlename = js.get("courses["+i+"].title");
			if(titlename.equalsIgnoreCase("RPA"))
			{
				int RPAcopy = js.get("courses["+i+"].copies");
				System.out.println("RPA Copies "+RPAcopy);
				break;
			}
		}

	}

}
