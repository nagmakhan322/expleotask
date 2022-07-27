package testpackage;  
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;  
import org.testng.annotations.BeforeTest;  
import org.testng.annotations.Test;
import org.testng.Assert;

 

public class MavenTest1 {  
public String baseUrl = "https://www.xe.com/currencyconverter/";  
String driverPath = "C://chromedriver_win32//chromedriver.exe";  

public  WebDriver driver ;
@Test             
public void test() {      
// set the system property for Chrome driver      
System.setProperty("webdriver.chrome.driver", driverPath);  
// Create driver object for CHROME browser  
WebDriver driver = new ChromeDriver(); 

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  
driver.manage().window().maximize();  
driver.get(baseUrl);  
// get the current URL of the page  
//String URL= driver.getCurrentUrl();  
//System.out.print(URL);

//clicking accept cookies button to remove the popup
driver.findElement(By.cssSelector("button[class='button__BaseButton-sc-1qpsalo-0 ctapkr']")).click();

//input values in the inputbox for conversion
driver.findElement(By.cssSelector(".text-input__TextInput-sc-17mujrb-0.amount-input__Wrapper-sc-1gq6pic-0.jApTut.ezbfAz")).click();
driver.findElement(By.cssSelector("#amount")).sendKeys(String.valueOf(12));

//selecting From currency value from the dropdown
driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
driver.findElement(By.cssSelector("#midmarketFromCurrency")).sendKeys("EUR");
driver.findElement(By.cssSelector("#midmarketFromCurrency")).sendKeys(Keys.ENTER);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//selecting To currency value from the dropdown
driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
driver.findElement(By.cssSelector("#midmarketToCurrency")).sendKeys("GBP");
driver.findElement(By.cssSelector("#midmarketToCurrency")).sendKeys(Keys.ENTER);


//clicking convert button
driver.findElement(By.cssSelector("button[type='submit']")).click();

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  

//getting conversion rate from result page for 1 euro
String currency= driver.findElement(By.cssSelector("div[class='result__Repulsor-sc-1bsijpp-4 dZGBTm'] p:nth-child(1)")).getText();
String currency1=currency.substring(8, 16);

//multiplying the conversion rate with out input value -- in our case its 12
double currency1d = Double.parseDouble(currency1);
double currencyaftermultiply= currency1d*12;
//System.out.println(currencyaftermultiply);


//getting actual conversion value of our input value
String currencyresult= driver.findElement(By.cssSelector(".result__BigRate-sc-1bsijpp-1.iGrAod")).getText();
String currencyresult1=currencyresult.substring(0, 8);
double currencyresult1d = Double.parseDouble(currencyresult1);

//double value1= Math.round(currencyaftermultiply*100.0)/100.0;
//double value2= Math.round(currencyresult1d*100.0)/100.0;

//rounding up the result to check the value
double value1= Math.round(currencyaftermultiply);
double value2= Math.round(currencyresult1d);
//Assert.assertEquals(value1, value2);
driver.quit();  

// test case tocheck weather th converted value is correct or not
try {
    Assert.assertEquals(value1, value2);
} catch (AssertionError e) {
    System.out.println("Not equal");
    throw e;
}
System.out.println("Equal");
}
}


//@Test
//public void test2() {      
//	System.setProperty("webdriver.chrome.driver", driverPath);  
//	// Create driver object for CHROME browser  
//	WebDriver driver = new ChromeDriver(); 
//
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  
//	driver.manage().window().maximize();  
//	driver.get(baseUrl);  
//	// get the current URL of the page  
//	//String URL= driver.getCurrentUrl();  
//	//System.out.print(URL);
//
//	driver.findElement(By.cssSelector("button[class='button__BaseButton-sc-1qpsalo-0 ctapkr']")).click();
//
//	driver.findElement(By.cssSelector(".text-input__TextInput-sc-17mujrb-0.amount-input__Wrapper-sc-1gq6pic-0.jApTut.ezbfAz")).click();
//	driver.findElement(By.cssSelector("#amount")).sendKeys(String.valueOf(12));
//
//
//	driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
//	driver.findElement(By.cssSelector("#midmarketFromCurrency")).sendKeys("CAD");
//	driver.findElement(By.cssSelector("#midmarketFromCurrency")).sendKeys(Keys.ENTER);
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//	driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
//	driver.findElement(By.cssSelector("#midmarketToCurrency")).sendKeys("AUD");
//	driver.findElement(By.cssSelector("#midmarketToCurrency")).sendKeys(Keys.ENTER);
//
//	driver.findElement(By.cssSelector("button[type='submit']")).click();
//
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
//	String newcurrency= driver.findElement(By.cssSelector("div[class='result__Repulsor-sc-1bsijpp-4 dZGBTm'] p:nth-child(1)")).getText();
//	String newcurrency1=newcurrency.substring(8, 16);
//	double newcurrency1d = Double.parseDouble(newcurrency1);
//	double newcurrencyaftermultiply= newcurrency1d*12;
//	
//	String newcurrencyresult= driver.findElement(By.cssSelector(".result__BigRate-sc-1bsijpp-1.iGrAod")).getText();
//	String newcurrencyresult1=newcurrencyresult.substring(0, 8);
//	double newcurrencyresult1d = Double.parseDouble(newcurrencyresult1);
//
//	double newvalue1= Math.round(newcurrencyaftermultiply);
//	double newvalue2= Math.round(newcurrencyresult1d);
//	//Assert.assertEquals(newvalue1, newvalue2);
//
//
//	try {
//	    Assert.assertEquals(newvalue1, newvalue2);
//	} catch (AssertionError e) {
//	    System.out.println("equal");
//	    throw e;
//	}
//	System.out.println("Equal");
//	}
//}


//@BeforeTest  
//public void beforeTest() {    
//System.out.println("before test");  
//}     
//@AfterTest  
//public void afterTest() {  
//driver.quit();  
//System.out.println("after test");  
//}         
//}  