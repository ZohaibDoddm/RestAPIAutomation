package files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumOfCourses() 
	{
		JsonPath js = new JsonPath(payload.PriceList());
		int count = js.getInt("courses.size()"); // getInt method of jsonpath helps to get the integer value.
		
		int sum = 0;
		for(int i=0;i<count;i++)
		{
			
			int prices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int priceproduct = prices * copies ;
			System.out.println("product prices is "  +priceproduct);
		    
			sum = sum+priceproduct; 
			System.out.println("total price is " +sum);
			continue;
		}
			
			 int purchaseAmount = js.getInt("dashboard.purchaseAmount");
			 System.out.println(purchaseAmount);
			 Assert.assertEquals(purchaseAmount,sum);
			
	}

}
