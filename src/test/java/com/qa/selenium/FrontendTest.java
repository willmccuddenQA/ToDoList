//package com.qa.selenium;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//@TestMethodOrder(OrderAnnotation.class)
//public class FrontendTest {
//	private static WebDriver driver;
//	private static WebElement target;
//	private final String URL = "http://localhost:8080/index.html";
//
//	@BeforeAll
//	public static void beforeAll() {
//		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
//		ChromeOptions config = new ChromeOptions();
//		driver = new ChromeDriver(config);
//	}
//
//	@AfterAll
//	public static void afterAll() {
//		driver.quit();
//	}
//
//	@Test
//	@Order(1)
//	public void deleteListTest() throws InterruptedException {
//		driver.get(URL);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[1]/li[1]/div/div/div[4]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[1]/li[1]/div/div/div[1]/h4"));
//		String str = target.getText();
//		assertEquals(str,"Targets for the year");
//
//	}
//	
//	@Test
//	@Order(2)
//	public void createListTest() throws InterruptedException {
//		driver.get(URL);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[2]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[2]/input"));
//		target.sendKeys("New List");
//		target = driver.findElement(By.xpath("/html/body/div/div[2]/button"));
//		target.click();
//		
//		target = driver.findElement(By.id("listTitle4"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[1]/li[3]/div/div/div[1]/h4"));
//		System.out.print(target.getText());									 
//		String str = target.getText();
//		assertEquals(str,"New List");
//
//	}
//	
//	@Test
//	@Order(3)
//	public void createItemTest() throws InterruptedException {
//		driver.get(URL);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[1]/li[3]/div/div/div[2]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[4]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[4]/input[1]"));
//		target.sendKeys("Important Thing");
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[4]/input[2]"));
//		target.sendKeys("Thursday");
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[4]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[3]/li/div/div[1]/div[1]/h5"));
//		String str = target.getText();
//		
//		assertEquals(str,"Important Thing");
//	}
//	
//	@Test
//	@Order(4)
//	public void updateItemTest() throws InterruptedException {
//		driver.get(URL);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[1]/li[3]/div/div/div[2]/button"));
//		target.click();
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[3]/li/div/div[1]/div[3]/button"));
//		target.click();                       
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[3]/li/div/div[3]/input[1]"));
//		target.sendKeys("Another Thing");
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[3]/li/div/div[3]/input[2]"));
//		target.sendKeys("Friday");
//		
//		target = driver.findElement(By.xpath("/html/body/div/div[3]/li/div/div[3]/button"));
//		target.click();
//		
//		Thread.sleep(500);
//		target = driver.findElement(By.id("itemTitle8"));
//		String str = target.getText();
//		
//		assertEquals(str,"Another Thing");
//
//	}
//
//	public static ChromeOptions chromeCfg() {
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		ChromeOptions cOptions = new ChromeOptions();
//
//		// Settings
//		prefs.put("profile.default_content_setting_values.cookies", 2);
//		prefs.put("network.cookie.cookieBehavior", 2);
//		prefs.put("profile.block_third_party_cookies", true);
//
//		// Create ChromeOptions to disable Cookies pop-up
//		cOptions.setExperimentalOption("prefs", prefs);
//
//		return cOptions;
//	}
//}
