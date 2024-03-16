package exceldriven;

import java.io.IOException;
import java.util.ArrayList;

public class testsample {
	
	
	public static void main(String[]args) throws IOException {
		
		
		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("Purchase");  // calling get data method and passing string to search "PURCHASE" fro excel sheet.
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
		
	}

}
