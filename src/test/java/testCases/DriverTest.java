package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DriverTest {
	
	static WebDriver driver;
	@Test
	public void fnBrowserStart() {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/Drivers/chromedriver1.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to("https://www.york.com/");
			
			}

		
}