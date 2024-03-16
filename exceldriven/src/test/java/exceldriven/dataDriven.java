package exceldriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {


	public ArrayList<String> getData(String testcasename) throws IOException {


		ArrayList<String> a =new ArrayList<String>();  // created an arraylist to store all the values of Strings

		// READINF FILES USING FILEINPUSTREAM ARGUYMENT

		FileInputStream fis = new FileInputStream("C:\\Users\\Hp\\OneDrive\\Documents\\datademo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // workbook class used. an object of it used to access files.

		int countofsheet = workbook.getNumberOfSheets();

		for(int i=0;i<countofsheet;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata")) 
			{
				XSSFSheet Sheet = workbook.getSheetAt(i); 
				java.util.Iterator<Row> rows = Sheet.iterator();                                           // Sheet is collection of rows
				Row firstrow = rows.next();   // return type is Row. it moves from one row to another. 
				java.util.Iterator<Cell> cel = firstrow.cellIterator(); 							     	// cell is collection of rows


				int k=0;
				int column = 0;

				while(cel.hasNext())   // will check if there is presence of next cell.
				{
					Cell cellvalue = cel.next();
					if(cellvalue.getStringCellValue().equalsIgnoreCase("TestData"))
					{
						column = k;
					}   //checking the condition if the cell value is equals to "testcase"

					k++;
				}

				System.out.println(column);


				while(rows.hasNext()) {

					Row r = rows.next();	
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) /// passed value is coming here
					{
						java.util.Iterator<Cell> cellval = r.cellIterator();

						while(cellval.hasNext()) 
						{

							// we are checking if the cell value is string or an integer. if it is string we will add it into the arraylist else we ill add it into the array by converting the numeric to string
							Cell cv = cellval.next();

							if(cv.getCellTypeEnum() == CellType.STRING) 
							{	
								a.add(cv.getStringCellValue());
							}

							else 
							{
								//using POI NumberToTextCoverter class 
								a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));	  		    //converting the numeric value to string value.so that it ca beadded to the string of arraylist
							}

						}
					}
				}
			}
		}
		return a;
	}




	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub


	}

}
