package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverTest {
	
	static WebDriver driver;
		
	public void fnBrowserStart() {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.york.com/");
			}

		
}