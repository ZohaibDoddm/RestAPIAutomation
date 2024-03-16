package demo;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;



public class OAuthTest {
	
	
public static void main(String[] args) throws InterruptedException {
	
	
			//System.setProperty("webdriver.chrome.driver","C://Testleaf//chromedriver_win32.exe");
	
			WebDriver driver = new ChromeDriver();
			
			driver.get("https://accounts.google.com/v3/signin/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&theme=glif&ddm=0&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAPSccCptLbok3RE0qbXJnXu7JSVpfD1vg_pTssNS4a5zcOtTCSg4ZcsJZKdFWwRrpPO8Co61721fzE4oEJCFtt_0Z05g2oSSX7_So5E2e3QkUY73pqtjUagaMAyXfPHfW-RLEFu11YWM5c1SU5hg8TLiEPIk5CHrjSUTVpAN6DlbBmdVDD1H8N1Nfw3-VgKVOhKeuq8DvMlQgmznCRqcrV-JYelWid70kzkfGWtg5HMWB28HgSO66N2dDqttiqXqXO1IbfE4jzT5aDPW93LECDVHKDrTS3uXlx9Rln1LwWVMjjDq-S3YkzyyVfOtuxOSFY5xG49Q-mN0MheVACUrrBadgP3GgG7RzPwChUQyNVDS3aBpdExsavtxyFL3VYBCtUVadd-BrKZBj_qXxGjbpvQqfbKgyHHcmsV_nmzJywll26bsDdO93EP2OLclXMtfQf9coltu_0twBbHD2DDfcqC7k3R5Q%26flowName%3DGeneralOAuthFlow%26as%3DS-1207137050%253A1710436407966379%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%26theme%3Dglif%23");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys("muk.srinivasan");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
			Thread.sleep(8000);
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Timeisimportant15");
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			String urllink = driver.getCurrentUrl();
			
			String codeurl = urllink.split("code")[1];
			String codevalue = codeurl.split("scope")[0];
			
			
			//https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AeaYSHBA_NDUR6gypQemcsV7lin6YmB9h0qKLx9-1GvfN--onym92fbC71T0wT48J5XUsg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none
			
			
	String authorisationrep = given().queryParams("scope","https://www.googleapis.com/auth/userinfo.email").
			queryParams("auth_url","https://accounts.google.com/o/oauth2/v2/auth").
			queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			queryParams("response_type","code").
			queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
			queryParams("state","verifyfjdss").
	when().get("https://accounts.google.com/o/oauth2/v2/auth").asString();
	
	JsonPath jz = new JsonPath(authorisationrep);
	//jz.getString("")
	
	
	//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss
	
	String accesstokenresp = given().queryParams("code",codevalue).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
		when().log().all().
		post("https://www.googleapis.com/oauth2/v4/token").
		asString();
	JsonPath js = new JsonPath(accesstokenresp);
	String accesstoken = js.getString("access_token");
	
	//https://www.googleapis.com/oauth2/v4/token?code=4%2F0AeaYSHBA_NDUR6gypQemcsV7lin6YmB9h0qKLx9-1GvfN--onym92fbC71T0wT48J5XUsg&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code#
	
	
	//second
	
	String response = given().queryParam("access_token",accesstoken).
	when().log().all().
	get("https://rahulshettyacademy.com/getCourse.php").asString();
	
	System.out.println(response);
	
	
	
			

		}
	

}
