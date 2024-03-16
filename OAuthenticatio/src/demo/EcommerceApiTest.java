package demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import org.testng.Assert;


public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
		.setContentType(ContentType.JSON).build();
		
		//loginrequest classs object used to set email and password.
		
		LoginRequest loginrequest = new LoginRequest();
		loginrequest.setUserEmail("muk121@gmail.com");
		loginrequest.setUserPassword("Timeisimportant1!");
		
		//creating the spec builder class
		RequestSpecification reqlogin = given().log().all().spec(req).body(loginrequest);
		LoginResponse loginresponse = reqlogin.when().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		loginresponse.getToken();
		loginresponse.getMessage();
		//using the oject of loginresponse class to fetch the below detauls
		String token = loginresponse.getToken();
		String userID = loginresponse.getUserId();
		
		System.out.println(loginresponse.getToken());
		System.out.println(loginresponse.getMessage());
		System.out.println(loginresponse.getUserId());
	
	//Add a Product
		RequestSpecification addproduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization",token).build();
		
		// the below is a form parameter( form data) hence we would be using below format instead of using pojo class. 
		RequestSpecification reqaddproduct = given().log().all().spec(addproduct).
		param("productName","Laptop").
		param("productAddedBy",userID). 
		param("productCategory","fashion").
		param("productSubCategory","electronic"). 
		param("productPrice","11500").
		param("productDescription","Lenovo"). 
		param("productFor","men").
		multiPart("productImage",new File("C://Users//Hp//pictures//lenovo.png"));
	
		
		String addProductresponse = reqaddproduct.when().post("/api/ecom/product/add-product"). 
		then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addProductresponse);
		String productId = js.get("productId");
		
		
		// create an order for above product
		RequestSpecification createOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization",token).setContentType(ContentType.JSON). // as in this request we use jspn format for the body
				 build();
		// orderdetails and orders are the 2 classes used here for reference.
		// we are building the body of the 
		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productId);
		
		// creating an array of list of order details as the body has the below BODY:
		
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails> ();
		orderDetailList.add(orderdetails);
		
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		RequestSpecification createorderreq = given().log().all().spec(createOrder).body(orders);
		
		String responseAddorder = createorderreq.when().post("api/ecom/order/create-order").
		then().log().all().extract().response().asString();
		System.out.println(responseAddorder);
	
		
		// DELETE ORDER
		RequestSpecification DeleteOrderbase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization",token).setContentType(ContentType.JSON). // as in this request we use jspn format for the body
				 build();
		
		RequestSpecification  deleteprodreq = given().log().all().spec(DeleteOrderbase).pathParam("productId",productId);
		// add pathparameter and then use it in the request.
		String deleteprodresponse = deleteprodreq.when().delete("/api/ecom/product/delete-product/{productId}").
		then().log().all().extract().response().asString();
		
		JsonPath jsz =  new JsonPath(deleteprodresponse);
		String messg = jsz.get("message");
		
		System.out.println(messg);
		
		Assert.assertEquals("Product Deleted Successfully",messg);
		
		//extracting response 2 TYPES. FOR JSON PATH USE RESPONSE AS STRING()
		// EXTRACTING THE RESPONSE AS POJO USE 
	}

}
