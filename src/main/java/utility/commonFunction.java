package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseClass.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExcelReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class commonFunction extends ExcelReader{
	
	public static WebDriver driver;
	WebDriverWait wait;
	Actions act;
	public String flash="yes";
	//ExtentReportHtml report;
	protected static ExtentReportHtml report = new ExtentReportHtml(); 
	static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
	static String strDate = dateFormat.format(date);
	public static String vFolder = System.getProperty("user.dir") +"/reports/SummaryReport_" + strDate;	
	public static String tFolder3;
	
protected void openBrowser(String vFileName){	
		
		System.out.println("Inside open browser");
		WebDriverManager.chromedriver().setup();

		/*ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = null;
		prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_settings.geolocation", 2);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", downloadFilepath);	
		
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("test-type");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized");
		options.addArguments("--disable-extensions");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");*/		
		
//		tFolder = vFolder + "/" + vFileName;
//		tFolder1 = tFolder + "/" + "DocumentDownloaded";		
		tFolder3 = report.tFolder ;
		System.out.println(tFolder3);
		String downloadFilepath=tFolder3.replace('/', '\\');
		System.out.println("final path :-" +downloadFilepath);
//		 String downloadFilepath = "D:\\GEProficyReports";
         HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
         chromePrefs.put("profile.default_content_settings.popups", 0);
         chromePrefs.put("download.default_directory", downloadFilepath);
         ChromeOptions options = new ChromeOptions();
         options.setExperimentalOption("prefs", chromePrefs);
//         options.addArguments("start-maximized");
         DesiredCapabilities cap = DesiredCapabilities.chrome();
         cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
         cap.setCapability(ChromeOptions.CAPABILITY, options);
         cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
         cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
         driver = new ChromeDriver(cap);         
         
		//driver = new ChromeDriver(options);
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// new			
	   
	}	


public static void navigateBack() {
    
    driver.navigate().back();
}

public static void scrollDown() {
    
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollBy(0,550)", "");
   
}

    public static void refreshPage() {
        driver.navigate().refresh();
        }
       
        public static void goToURL(String URL) {
            driver.get(URL);
           
        }
   

	public boolean waitForElementVisibility(WebElement element) throws InterruptedException {		
		jsWaitForPageLoad();
		WebElement placeholder = null;
		// wait.until(ExpectedConditions.
		placeholder = wait.until(ExpectedConditions.visibilityOf(element));
		if (placeholder == null)
			return false;
		else
			return true;
	}
	
	//Inprocess
	public boolean waitForElementVisibilityByLocator(By byLocator) throws InterruptedException {	
		System.out.println("In function waitForElementVisibilityByLocator");
		jsWaitForPageLoad();
		WebElement placeholder = null;
		// wait.until(ExpectedConditions.
		
		placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		if (placeholder == null)
			return false;
		else
			return true;
	}
	
	
	public boolean waitForElementInvisibility(WebElement element) throws InterruptedException {		
		jsWaitForPageLoad();
		boolean placeholder = false;
		 placeholder = wait.until(ExpectedConditions.invisibilityOf(element));		
		 return placeholder;
		
	}
	
	
	
	public boolean waitforElement(WebElement element) throws InterruptedException {		
		jsWaitForPageLoad();
		boolean placeholder = false;
		 //placeholder = wait.until(ExpectedConditions.(element));		
		 return placeholder;
		
	}
	
	//el.IGF_clickDate(MonthYear, days, "25", "3", "2020");
	
	/***
	 * Sample Call:  el.IGF_clickDate(MonthYear, days, "25", "3", "2020")   
	 * Note that  "dd","MM","yyyy" is input. Also month is one month behind in application. 3 denotes April and so on
	 * @param parentMonthYear  
	 * @param days
	 * @param day
	 * @param month
	 * @param year
	 */
	
	public static boolean isElementPresent(By locator, String ElementName)
	{
		 
	  try {
		  System.out.println("In function isElementPresent");
		  getElement(locator).isDisplayed();		    
		  report.InfoTest(ElementName + "is present");
		    return true;
		  }
	  catch (NoSuchElementException e) {
		  report.addFailLog("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage(),"Failed");
		    return false;
	  }
	}

//	Anika Kokkula 			isElementPresentwithoutreturn
    public static boolean isElementPresentwithoutResult(By locator, String ElementName ) {
            
          try {
              System.out.println("In function isElementPresentwithoutResult");
             WebElement elementz= getElementwithoutresult(locator);
                     
                     if(elementz==null)
                     {
                         return false;
                     }
                     else
                     {
                         elementz.isDisplayed();
                         report.InfoTest(ElementName + " is present");
                            return true;
                     }            
            
              }
          catch (NoSuchElementException e) {
              //Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
                return false;
              }
            } 

    public static WebElement getElementwithoutresult(By locator) {
            waitForElementPresentwithoutresult(locator);
            WebElement element = null;
            try {
                element = driver.findElement(locator);
//            if (flash.equalsIgnoreCase("yes")) {
//                JavaScriptUtil.flash(element, driver);               
        }catch(Exception e) {
                //System.out.println("Some exception occurred while creating webelement " + locator);
            }
            return element;
        }
    
    public static void waitForElementPresentwithoutresult(By locator) {
            try {
            System.out.println("In function waitForElementPresent");
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("Waiting for "+locator);
            }
            catch(Exception e)
            {
                //report.addFailLog("Class Utils | Method OpenBrowser | Exception Desc : "+e.getMessage(), "Failed");
            }
        }

//Added by Pooja
	public static void isElementPresentwithoutreturn(By locator, String ElementName) throws InterruptedException
	    {	        
	          System.out.println("In function isElementPresentwithoutreturn");
	          if (isElementPresent(locator,  ElementName) == true ){ 	        	  
	        	  report.addPassLog(ElementName + " is present",ElementName + " is present");
	        	  Thread.sleep(1000);
	        	  System.out.println(ElementName + " is present");
	          } else{
	        	  report.addFailLog(ElementName + " is not present",ElementName + " not present");	
		          System.out.println(ElementName + " is not present");
	          	}
	     }

//
//	public static boolean isElementPresentwithoutResult(By locator, String ElementName ) {
//		 
//	  try {
//		  System.out.println("In function isElementPresentwithoutResult");
//		  getElement(locator).isDisplayed();
//		  report.InfoTest(ElementName + "is present");
//		    return true;
//		  }
//	  catch (NoSuchElementException e) {
//		  //Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
//		    return false;
//		  }
//		}	
	
	public void IGF_clickDate(List<WebElement> parentMonthYear,List<WebElement> days,String day,String month,String year) {	
		System.out.println("In function IGF_clickDate");
		Actions act = new Actions(driver);
		for (int j = 0; j < parentMonthYear.size(); j++) {
			if (parentMonthYear.get(j).getAttribute("data-month").equals(month)) {
				for (int i = 0; i < days.size(); i++) {
					if (days.get(i).getText().equals(day)) {
						act.moveToElement(days.get(i)).click().sendKeys(Keys.ESCAPE).build().perform();
						act.sendKeys(Keys.ESCAPE).build().perform();
						break;
				}
				}
				break;
			}
		}		
	}
	
	
		
	public boolean waitForElementStaleReference(WebElement element) {
		WebElement placeholder = null;
		wait
			.ignoring(StaleElementReferenceException.class)	
			.pollingEvery(Duration.ofSeconds(1));
		     placeholder = wait.until(ExpectedConditions.visibilityOf(element));
		if (placeholder == null)
			return false;
		else
			return true;
	}
	
	/***
	 * 
	 * Takes user to webelement by scrolling the screen
	 * 
	 * @param element
	 * @throws InterruptedException 
	 */

	//modified by ankita (changed locator type, passed value parameter and added infotest in try)
	public void jsScrolltoElement(/*WebElement*/ By element, String ElementName) throws InterruptedException {
		try {
			System.out.println("In function jsScrolltoElement");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			report.InfoTest("Scrolled to "+ElementName);
			
		} catch (Exception e) {
			wait
			.ignoring(StaleElementReferenceException.class)	;
			Thread.sleep(2000);
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}

	/***
	 * 
	 * Clicks WebElement with Actions class
	 * 
	 * @param element
	 */
	public void clickWithActions(WebElement element) {
		 //try {
		act.click(element);
		System.out.println("Clicked with Actions");
		// } catch (Exception e) {

		// System.out.println("Error while clicking element with Actions class:
		// "+e.getMessage());
		// }
	}

	/*//modified by ankita (Changed locator type, added parameter value and added infotest)
	public static void jsClick(WebElement By element, String ElementName) {
		try {
			System.out.println("In function jsClick");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			report.InfoTest("Clicked on "+ElementName);
			
		} catch (Exception e) {
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}*/

	public static void jsClick(By element, String ElementName) {
		try {
			System.out.println("In function jsClick");

//			System.out.println("Element path in jsClick "+element);
			WebElement ele = driver.findElement(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			report.InfoTest("Clicked on "+ElementName);
			
		} catch (Exception e) {
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}

	
	public void jsscrollPixel(int pixels, String ElementName) {
		try {
			System.out.println("In function jsscrollPixel");
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
			report.InfoTest("Scrolled to"+ ElementName );
		} catch (Exception e) {
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}
	
	public static void scrollDown(String pixel)
    {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,"+pixel+")","");
    	report.InfoTest("Scrolled down the page");
	 }
	
	public static void scrollUp() {
    	 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-450)", "");
	}
	
	
	public void jsWaitForPageLoad() throws InterruptedException {
		  boolean status=false;
		  int count=1;
		while(status==false) {
		status= ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		Thread.sleep(1000);
		count++;
		if(count>30)
		break;
	}}
	

	public static String screenshot() {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String strImagepath=System.getProperty("user.dir")+"\\"+"target\\ExtentReports\\"+getDatetime()+".png";
		System.out.println("Screenshotpath Generated "+strImagepath);
		try {
			FileUtils.copyFile(scr, new File(strImagepath));
		} catch (IOException e) {
			System.out.println("error while copying file :" + e.getMessage());
		}
		return strImagepath;
	}
	
	
	public static String  getDatetime() {	
		System.out.println("In function getDatetime");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");//18-4-2020 07:01:23
		Date d=new Date();
		d.getTime();
		String data=sdf.format(d.getTime());
		return data.replace(":", "_");//18-4-2020 07_03_41
		
	}

	/**
	 * Takes format and daystoadd to currentdate
	 * Ex getDate("dd/MM/yyyy",0)
	 * @param plusminusDays
	 * @return currentdate+plusminusDays
	 */
	public static String getDate(String formatofDate,int plusminusDays) {
		System.out.println("In function getDate");
		SimpleDateFormat sdf = new SimpleDateFormat(formatofDate); //M should always be Capital
		//Getting current date
		Calendar cal = Calendar.getInstance();
		//Displaying current date in the desired format
		//System.out.println("Current Date: "+sdf.format(cal.getTime()));
		   
		//Number of Days to add
	     cal.add(Calendar.DAY_OF_MONTH, plusminusDays);  
		//Date after adding the days to the current date
		String datevalue = sdf.format(cal.getTime());  
		//Displaying the new Date after addition of Days to current date
		//System.out.println("Date after Addition: "+newDate);	
		
		return datevalue;
		
	}
	
	
	
	public void selectDatejs(WebElement element, String dateVal) {
		System.out.println("In function selectDatejs");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value', '" + dateVal + "');", element);
	}
//	
//	public static void main(String[] args) {
//		System.out.println(getDate("dd/MM/yyyy",0));
//		System.out.println(getDate("dd/MM/yyyy",9));
//		System.out.println(getDate("dd/MM/yyyy",15));	
////		
////		21/04/2020
////		30/04/2020
////		06/05/2020
//		
//		
//	}
	
	public static WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = null;
		//try {
			element = driver.findElement(locator);
//		if (flash.equalsIgnoreCase("yes")) {
//			JavaScriptUtil.flash(element, driver);
//			}
//		} catch (Exception e) {
			//System.out.println("Some exception occurred while creating webelement " + locator);
		//}
		return element;
	}
	
	//added report test by ankita
	public static void waitForElementPresent(By locator) {
		
		System.out.println("In function waitForElementPresent");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		System.out.println("Waiting for "+locator);
		
	}
	
	public static void doSendKeys(By locator, String value) {
		try {
		System.out.println(" in function doSendKeys");
			//getElement(locator).clear();
			getElement(locator).sendKeys(value);
			//myListener.test.info("<b>"+ value+"</b>" + " entered in inputbox ");
			report.InfoTest("<b>"+ value+"</b>" + " entered in inputbox ");
			} catch (Exception e) {
			System.out.println("Some exception occurred while sending to webelement " + locator);
			report.addFailLog("Class Utils | Method OpenBrowser | Exception Desc : "+e.getMessage(), "Failed");
		}
	}
	
	
	public static void doClick(By locator,String stepDescription) {
		try {
			System.out.println("In function doClick");
			getElement(locator).click();
			report.InfoTest(stepDescription);
			//BasePage.test.info(stepDescription);	
		} catch (Exception e) {
			System.out.println("Some exception occurred while clicking on webelement " + locator);
			report.addFailLog("Unable to click on Element | "+locator + "| Class Utils | Method OpenBrowser | Exception Desc : "+e.getMessage(), "Failed");
		}
	}	
	
	//functions added by ankita
	
	//for verifying whether element is enabled or not
	public static boolean IsElementEnabled(By locator, String ElementName ) {
			
		  try 
		  	{
			  System.out.println("In function IsElementEnabled");
			  getElement(locator).isEnabled();
			  report.InfoTest(ElementName+" is enabled");
			  	return true;
			  }
		  catch (NoSuchElementException e) {
			  	report.addFailLog("Class Utils | Method OpenBrowser | Exception Desc : "+e.getMessage(), "Failed");
			    return false;
			  }
			}
	
	
	//To get text from element
	public static String GetElementText(By locator, String ElementName) {
			
			System.out.println("In function GetElementText");
			String Text=getElement(locator).getText();
			report.InfoTest("<b>" +Text+ "</b>" + " value extracted from "+ElementName+"");
			return Text;
			}
		
	
	//Added by Pooja = To get text from element using attribute
		public static String GetElementTextusingAttribute(By locator, String ElementName) {
				
				System.out.println("In function GetElementText");
				String Text=getElement(locator).getAttribute("id");
				report.InfoTest("<b>" +Text+ "</b>" + " value extracted from "+ElementName+"");
				return Text;
				}
	//To verify whether element is selected or not
	public static boolean isElementSelected(By locator )
		{
			try 
			{
				System.out.println("In function isElementSelected");
				getElement(locator).isSelected();
				report.InfoTest(locator+" is selected");
			    return true;
			 }
			 catch (NoSuchElementException e) {
				  report.addFailLog("Class Utils | Method OpenBrowser | Exception Desc : "+e.getMessage(), "Failed");
				   return false;
			  }
		}
		
	//Mouse hover
	public static void mousehover(By lnkApplication, String ElementName)
		{
			System.out.println("In function mousehover");
			Actions act = new Actions(driver);
			WebElement we = driver.findElement(lnkApplication);
			act.moveToElement(we).perform();
			report.InfoTest("Mouse hovering on "+ElementName);
		}
		
	//To clear element
	public static void elementClear(By locator, String ElementName)
	      {
			System.out.println("In function elementClear");
			getElement(locator).clear();
			report.InfoTest("Cleared "+ElementName);
	      }
	
	//To select value from dropdown using visible text
	 public static void dropdownSelectAllOptions(By locator) 
		    {
		 	   System.out.println("In function dropdownSelect");
		       WebElement Elementby=driver.findElement((locator));
		       Select drp = new Select(Elementby);
		       List<WebElement> ele= drp.getOptions();
		      // System.out.println("");
		       for(int i=0;i<ele.size();i++) {
		    	   String opt=ele.get(i).getText();
		    	   System.out.println("Values in DropDown "+ opt);
		       }
		      }
	 
	 public static void dropdownSelectByValue1(WebElement ele, String val, String ElementName)
		{
//	 		System.out.println("In function dropdownSelectByValue");
//	    	WebElement Elementby=driver.findElement((locator));
	        Select drp = new Select(ele);
	        drp.selectByValue(val);
	        report.InfoTest("<b>" +val+ "</b>" + "selected from "+ElementName+" dropdown");
  }

	
	
	   public static void scrollIntoView(By xpath) throws InterruptedException {
           
	        WebElement element = driver.findElement(xpath);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        Thread.sleep(500);
	       
	       
	 }
	
	
	
		
	//To select value from dropdown using visible text
		 public static void dropdownSelect(By locator, String val, String ElementName) 
			    {
			 	   System.out.println("In function dropdownSelect");
			       WebElement Elementby=driver.findElement((locator));
			       Select drp = new Select(Elementby);
			       drp.selectByVisibleText(val);
			       report.InfoTest("<b>" +val+ "</b>" + " selected from "+ElementName+" dropdown");
			      }
		 
	 //To select value from dropdown using index
	 public static void dropdownSelectByindex(By locator, int val, String ElementName)
		    {
		 	   System.out.println("In function dropdownSelectByindex");
		       WebElement Elementby=driver.findElement((locator));
		       Select drp = new Select(Elementby);
		       drp.selectByIndex(val);
		       report.InfoTest("<b>" +val+ "</b>" + "selected from "+ElementName+" dropdown");
		     }
			
	 //To select value from dropdown
	 public static void dropdownSelectByValue(By locator, String val, String ElementName)
	 		{
		 		System.out.println("In function dropdownSelectByValue");
		    	WebElement Elementby=driver.findElement((locator));
		        Select drp = new Select(Elementby);
		        drp.selectByValue(val);
		        report.InfoTest("<b>" +val+ "</b>" + "selected from "+ElementName+" dropdown");
	     }
	 
	 public static String GetValue(By locator, String attri, String ElementName)
	 	{
		 	System.out.println("In function GetValue");
		 	String Elementby=driver.findElement((locator)).getAttribute(attri);
		 	report.InfoTest("<b>" +Elementby+ "</b>" + "value extracted from"+ElementName);
		 	return Elementby;
	 	}
	 
	//For unique Name
	public static String UniqueName(String Name) throws Exception
	    {
	    	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    	String Name1= Name+dateName;
	  	    return Name1;
	    }
	 
	//To press Enter key from Keyboard
	public static void KeyPressEnter(By locator)
	    {
			System.out.println("In function KeyPressEnter");
	    	getElement(locator).sendKeys(Keys.ENTER);
	     }
	
	//to switch into iframe 
	 public static void SwitchToFrame(By locator, String StepDescription) 
	 	{
		   System.out.println("In function SwitchToFrame");
		   WebElement iframeElement = driver.findElement((locator));
		   driver.switchTo().frame(iframeElement);
		   report.InfoTest(StepDescription);
	     }
	 
	 //To switch to default frame
	 public static void SwitchToDefaultContent()
	 	{
		    System.out.println("In function SwitchToDefaultContent");
   	  		driver.switchTo().defaultContent();
   	  		report.InfoTest("Switched to default frame");
	 	}
		
	 //To click accept on popup
	 public static void popUpAccept() 
	 	{ 	
		 	System.out.println("In function popUpAccept"); 
			driver.switchTo().alert().accept();
			report.InfoTest("Accepted popup");
	 	}
	 
	 //To refresh the page
	 public static void refresh()
	 { 	
		 	System.out.println("In function refresh"); 
			driver.navigate().refresh();
			report.InfoTest("Refreshing page");
		}
	 
	 //Added By Pooja
	 //Comparing Element
	 public static void CompareElement(By locator, String ElementName , String Stringtext) throws InterruptedException
	 {
		 System.out.println("Comparing Elements"); 	
		 //		 Thread.sleep(10000);
		 String Elementtext = GetElementText(locator, ElementName).trim(); 	
		 System.out.println(Elementtext);
		 if(Elementtext.equals(Stringtext)){
			 System.out.println(Elementtext +" is displayed as expected");
			 report.addPassLogWithoutScreenshot(Elementtext +" is displayed as expected");
		 }else{
			 System.out.println(Elementtext +" is displayed as expected");
			 report.addFailLog(Elementtext +" is not displayed as expected",Elementtext+" is not displayed as expected");
		 }		  
	 }

	 //Added By Pooja Verify Print Window
	 public static void VerifyPrintWindow () throws IOException, InterruptedException {

		 System.out.println("Print functionality");	
		 try{

			 //Switch to Print dialog
			 Set<String> windowHandles = driver.getWindowHandles();
			 if (!windowHandles.isEmpty()) {
				 driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
				 System.out.println("Print Window is opened");
				 report.addPassLog("Print Window is opened","Validation Successfull");
				 driver.close();
			 } 						
		 }catch (Exception e) {
			 System.out.println("Some exception occurred while Verifying ");
			 report.addFailLog("Unable to identify Element", "Failed");
		 }
	 }

	 //Added By Pooja -- Verifying Date Functionality
	 public static void VerifyDatefunctionality (By locator, String Stringtext) throws IOException, InterruptedException {

		 System.out.println("Date functionality"); 
		 Thread.sleep(1000);
		 String Appdate= GetElementText(locator, Stringtext);
		 if(Appdate != null){	
			 try{			
				 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
				 
				 //get current date 
				 Date date = new Date();
				 
				 // Now format the date
				 String date1= dateFormat.format(date);
				 System.out.println(date1);
				 System.out.println(Appdate);
				 String s1= Appdate.substring(0,10);		 
				 System.out.println(s1.trim()); 				
				 if((date1.trim()).equals(s1.trim())){
					 System.out.println(Stringtext + " is displayed as System date");
					 report.addPassLog(Stringtext +  " is displayed as System date",Stringtext +  " is displayed as System date");
				 }else{
					 System.out.println(Stringtext +  " is not displayed as System date");
					 report.addFailLog(Stringtext +  " is not displayed as System date",Stringtext +  " is not displayed as System date");
				 }								
			 } catch (Exception e) {
				 System.out.println("Some exception occurred while Verifying ");
				 report.addFailLog("Unable to identify Element", "Failed");
			 }	
		 }else {
			 report.addFailLog("Unable to identify date Element", "Failed");
		 }
	 }
	 
// Added By Pooja- Verifying table is having data or not , with Printing col values--
	 public static void Verifytable (By locator, String Stringtext, String AllColNames) throws IOException, InterruptedException {

		 System.out.println("Verify table functionality");

		 //	Shift End Table is present
		 if(isElementPresent(locator, Stringtext)== true){
			 try{

				 // checks if data is present in the table of shift end tab	
				 WebElement table =driver.findElement(locator);

				 //To locate rows of table. 
				 List<WebElement> rows=table.findElements(By.tagName("th"));

				 //To calculate no of rows In table.
				 int rows_count = rows.size();
				 System.out.println("No of rows are : " + rows_count);		
				 List<WebElement> col=table.findElements(By.tagName("td"));
				 //To calculate no of columns (cells). In that specific row.
				 int col_count = col.size();
				 System.out.println("No of col are : " + col_count);			
				 if (rows_count > 2)	{
					 report.InfoTest("Data is present in " +Stringtext);
					 System.out.println("Data is present in " +Stringtext);
				 }else {
					 report.InfoTest("Data is not present in " +Stringtext);
					 System.out.println("Data is not present in " +Stringtext);
				 }		
				 Thread.sleep(3000);

				 //Printing col values--ShftEndtabrow
				 List < WebElement > rows_table = table.findElements(locator);

				 //To calculate no of rows In table.
				 int rowscount = rows_table.size();
				 System.out.println(rowscount);
				 String ColNames = AllColNames;
				 System.out.println(ColNames);
				 String[] ColName = ColNames.split(",");		
				 for(int row=0;row<rowscount;row++){
					 List<WebElement> Columns_row=table.findElements(By.tagName("th"));
					 int columns_count = Columns_row.size();
					 System.out.println("Number of cells In Row " + row + " are " + columns_count);
					 for(int j=0; j<columns_count; j++)
					 {
						 String celltxt = Columns_row.get(j).getText().trim();
						 System.out.println("Cell Value of row number " + row + " and column number " + j + " Is " + celltxt);
						 char[] Arrcelltxt = celltxt.toCharArray();					
						 System.out.println(Arrcelltxt);			

						 if(celltxt.equals(ColName[j])){			
							 report.InfoTest(celltxt+ " Column is present in Table");
							 System.out.println(celltxt+ " Column is present in Table");
						 }else {
							 report.InfoTest(celltxt+ " Column is present in Table");
							 System.out.println(celltxt+ " Column is present in Table");				
						 }					
					 } 
				 }
			 }catch (Exception e) {
				 System.out.println("Some exception occurred while Verifying Table ");
				 report.addFailLog("Unable to identify Element", "Failed");
			 }	
			 report.addPassLog(Stringtext +" is Visible",Stringtext +" is Visible");
		 }else {
			 report.addFailLog(Stringtext +" is not Visible",Stringtext +" is not Visible");
		 }	
	 }

	 //Added By Pooja- Verifying table is having data or not , with Printing col values--
	 public static void ClickonFirst_Data_of_row (By locator, String Stringtext, By locatorRow, By locator2, String Stringtext1, By locator4) throws IOException, InterruptedException {

		 System.out.println("Verify table functionality");
		 
		 //	Check if Table is present
		 if(isElementPresent(locator, Stringtext)== true) {			
			 try{	
				 Thread.sleep(10000);
				 WebElement shiftend_table =driver.findElement(locator);		
				 List<WebElement> rows=shiftend_table.findElements(By.tagName("tr"));		
				 int rows_count = rows.size();
				 System.out.println("No of rows are : " + rows_count);		
				 List<WebElement> col=shiftend_table.findElements(By.tagName("th"));
				 int col_count = col.size();
				 System.out.println("No of col are : " + col_count);					
				 if (rows_count > 2)
				 {
					 report.InfoTest("Data is present in " +Stringtext);
					 System.out.println("Data is present in " +Stringtext);	       
					 WebElement tableRow = shiftend_table.findElement(locatorRow); 
					 tableRow.click();
					 Thread.sleep(10000);
					 if ( isElementPresent(locator2, Stringtext) == true ) {
						 //				if(GetElementText(locator2, Stringtext1) == Stringtext1){
						 report.addPassLog(Stringtext1 + " Page is open","Validation Successfull");
						 System.out.println(Stringtext1 +" Page is open");			
						 if(isElementPresent(locator4, Stringtext1)== true){					 
							 report.addPassLog(Stringtext1 + " Record is open in detail view","Validation Successfull");
							 System.out.println(Stringtext1 +"Record is open in detail view ");
						 }else{
							 report.addFailLog(Stringtext1 + " Record is not open in detail view","Validation Un-Successfull");
							 System.out.println(locator2 +"  Record is not open in detail view");					 
						 }				 
					 }else {
						 report.addFailLog(Stringtext1 + " Page is not open","Validation Un-Successfull");
						 System.out.println(locator2 +"  Page is not open ");
					 }		
				 }else {
					 report.InfoTest("Data is not present in " +Stringtext);
					 System.out.println("Data is not present in " +Stringtext);
				 }		
			 }catch (Exception e) {
				 System.out.println("Some exception occurred while Verifying Table ");
				 report.addFailLog("Unable to identify Element", "Failed");
			 }	
		 }else {
			 report.addFailLog(Stringtext +" is not Visible","Validation Unsuccessfull");
		 }		
	 }

		 
	 
	 //	 Verify Excel button present in new window and download file 
	 //  Verify if downloaded then rename excel file	
	 //path == C:\\Users\\cdeshms1SA2\\Downloads\\
	 //Filename == DownTime Raw Data.xlsx

	public static void VerifyDownloadfunctionality(By locator, String Filename, String Pagename) throws IOException, InterruptedException
	{

		tFolder3 = report.tFolder ;
		System.out.println(tFolder3);
		String Actualpath=tFolder3.replace('/', '\\');
		System.out.println("Actual Download Path :-" +Actualpath);

		String DestinationPath1 = Actualpath+"\\" +Filename;
		System.out.println("DestinationPath" +DestinationPath1);	

		String DestinationPath = Actualpath + "\\" ;	 
		System.out.println("Actual Download Path " +DestinationPath); 

		Thread.sleep(2000);
		if (isElementPresent(locator, "Export Button ") == true ){
			doClick(locator, "Clicked on Export Button ");
			report.addPassLog("Clicked on Export Button","Export Button");
			Thread.sleep(5000);

			boolean flag = false;
			File dir = new File(DestinationPath);
			File[] dir_contents = dir.listFiles(); 
			for (int i = 0; i < dir_contents.length; i++) 
			{
				if (dir_contents[i].getName().equals(Filename)) {
					System.out.println(dir_contents[i]);
					report.addPassLogWithoutScreenshot(Filename +" File is downloaded Successfully");

					File oldName = new File(DestinationPath1);
					System.out.println(oldName);
					File newName = new File(DestinationPath+ Pagename+"_"+ Filename);
					System.out.println(newName);
					Thread.sleep(1000);
					if(oldName.renameTo(newName)) {
						System.out.println("File renamed successfully as " + newName);
						report.InfoTest("File renamed successfully as " + newName);
					} else {
						System.out.println("Error");
						report.addFailLogWithoutScreenshot("File renamed is notsuccessfully as " + newName);
					}
				}
			}
		}else {
			report.addFailLog(Filename +" Failed to download Excel","File is not downloaded Successfully");
			Thread.sleep(2000);
		}

 }
	 

	 //Added by Jaideep: VerifyExportedData
	 //Prequisite: Get the xPath of the first cell of the first row of data displayed up to (..tr[) and store in the variable BeforeXpath in Excel.
	 //Example: For Unit Cycle Time result Table below is the xpath
	 // "//div[@id = 'CycleTimeLineGrid']/div[3]/table/tbody/tr["
	 public static void VerifyExportedData(By locator, String Stringtext, String ExcelPath, String BeforeXpath) throws IOException, InterruptedException {

		 System.out.println("Inside Fn: VerifytableAndExportedData");

		 //Verifying if the table is present or not.
		 if (isElementPresent(locator, Stringtext) == true) {
			 report.addPassLog("Table is present", "Verify Table");

			 //Table Row Count
			 WebElement myTable = driver.findElement(locator);
			 List<WebElement> rows = myTable.findElements(By.tagName("tr")); // Locating Rows of table
			 int TableRowCount = rows.size();
			 System.out.println("Table Row Count is: " + TableRowCount);

			 //Table Column Count
			 List<WebElement> cols = myTable.findElements(By.tagName("th")); // Locating Columns of table
			 int TableColumnCount = cols.size();
			 System.out.println("Table Column Count is: " + TableColumnCount);

			 //Verifying if the Data is present in the table or not.
			 if (TableRowCount > 1) {
				 report.InfoTest("Data is present in " + Stringtext);
				 System.out.println("Data is present in " + Stringtext);

				 //Method to read the Excel
				 FileInputStream fis = new FileInputStream(ExcelPath);
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
				 XSSFSheet ws = wb.getSheet("Sheet1");
				 Thread.sleep(5000);
				 int ExcelRowCount = (ws.getLastRowNum() + 1);
				 System.out.println("Excel Row Count is: " + ExcelRowCount); //Excel Row Count

				 //Setting the flag for final validation
				 boolean finalflag = true;

				 //Iteration for comparing the data
				 for (int i = 1; i < TableRowCount; i++) {
					 Row row = ws.getRow(i);	//Setting the second row as current row in Excel
					 for (int j = 1; j <= TableColumnCount; j++) {

						 //Fetching the data from the Table
						 //String BeforeXpath = "//div[@id = 'CycleTimeLineGrid']/div[3]/table/tbody/tr[";
						 String AfterXpath = "]";
						 String FullRowColumnXpath = BeforeXpath + i + "]/td[" + j + AfterXpath;
						 //String FullRowColumnXpath = "//div[@id = 'CycleTimeLineGrid']/div[3]/table/tbody/tr[" + i + "]/td[" + j + "]";
						 String TableValue = driver.findElement(By.xpath(FullRowColumnXpath)).getText().trim();

						 //Fetching the data from Excel Cells on the basis of their DataTypes
						 CellType type = row.getCell(j-1).getCellType();						
						 switch (type) {
						 case NUMERIC : //Numeric value in Excel
							 double temp_one = row.getCell(j-1).getNumericCellValue();
							 
							//Formatting the double number by 2 decimal number	         
							 DecimalFormat df = new DecimalFormat("0.00");	
							 String DoubleExcelValue = df.format(temp_one).trim();
							 if (TableValue.equals(DoubleExcelValue)) {
								 System.out.println("Table Value is :" + TableValue);
								 System.out.println("Excel Value is :" + DoubleExcelValue);
								 report.InfoTest("Data Matched for the cell: ("+i+","+j+")");
							 } else {
								 System.out.println("Table Value is :" + TableValue);
								 System.out.println("Excel Value is :" + DoubleExcelValue);
								 report.addFailLog("Data not Matched for the cell: ("+i+","+j+")", "Verify Data for the cell: ("+i+","+j+")");
								 finalflag = false;
							 }
							 break;
						 case STRING : //String value in Excel					        
							 String StringExcelValue = row.getCell(j-1).toString().trim();
							 if (TableValue.equals(StringExcelValue)) {
								 System.out.println("Table Value is :" + TableValue);
								 System.out.println("Excel Value is :" + StringExcelValue);
								 report.InfoTest("Data Matched for the cell: ("+i+","+j+")");
							 } else {
								 System.out.println("Table Value is :" + TableValue);
								 System.out.println("Excel Value is :" + StringExcelValue);
								 report.addFailLog("Data not Matched for the cell: ("+i+","+j+")", "Verify Data for the cell: ("+i+","+j+")");
								 finalflag = false;
							 }
							 break;
						 default: throw new RuntimeException("No support for this type of cell");
						 }					
					 }
					 Thread.sleep(1000);
				 }
				 if (finalflag == true) {
					 System.out.println("Final flag value is: " +finalflag);
					 report.InfoTest("Data Matched for all cells");
					 report.addPassLog("Data Matched", "Verify Data for the cells");
				 }	
			 } else {
				 report.InfoTest("Data is not present in " + Stringtext);
				 System.out.println("Data is not present in " + Stringtext);
			 }
		 } else {
			 report.addFailLog("Table is not present", "Verify Table");
		 }
	 }

	 
	 //EroorPopup_HeatpumpSelected_With_AirCon_And_Furnace
}

