package stepDefinitions;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.ui.utilities.screenShot;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;

public class commonStepDefinitions extends TestBase {

	public void login(String userName, String password) throws Exception {

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Thread.sleep(2000);
		screenShot("LoginPage", "Pass", "HomePage");
		loginPage.loginLink.click();
		Thread.sleep(2000);
//		test.log(Status.PASS, "User Launched website");
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		screenShot("AfterRefreshPage", "Pass", "AfterRefresh");
//		driver.navigate().to(driver.getCurrentUrl());
//		Thread.sleep(5000);

//		driver.get(driver.getCurrentUrl());
		Thread.sleep(5000);

		enterTextbox("Username", userName);
		test.log(Status.PASS, "User entered Username");
		enterTextbox("Password", password);
		test.log(Status.PASS, "User entered Password");
		Thread.sleep(3000);
//		driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();
		
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		
		
		screenShot("okPopUpButton", "Pass", "okPopUp");
		loginPage.okPopUpButton.click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		if (driver.findElements(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).size() > 0) {
			loginPage.okPopUpButton.click();
			Thread.sleep(5000);
		}

	      
	}

	public void enterTextbox(String xpathParameter, String value) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "']//following::input[1]")).clear();
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "']//following::input[1]")).sendKeys(value);
	}
	public void enterTextboxContains(String xpathParameter,String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'"+xpathParameter+"')]//following::input[1]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'"+xpathParameter+"')]//following::input[1]")).sendKeys(value);
	}
	
	public void clickButton(String xpathParameter) {
		driver.findElement(By.xpath("//button[.='"+xpathParameter+"'][1]")).click();	
	}
	
	public void clickButtonContains(String xpathParameter) {
		driver.findElement(By.xpath("//button[contains(.,'"+xpathParameter+"')][1]")).click();	
	}
	public void clickMenu(String xpathParameter) {
		driver.findElement(By.xpath("//*[text()='"+xpathParameter+"'][1]")).click();	
	}
	
	public void selectRadio(String xpathParameter) {
		try {
			driver.findElement(By.xpath("//*[contains(.,'"+xpathParameter+"')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-inner-circle']")).click();
		
		}
		catch(Exception e){
			driver.findElement(By.xpath("//*[contains(.,'"+xpathParameter+"')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-outer-circle']")).click();
		}
	}

	public void selectRadioQuestions(String xpathQuestions, String xpathParameter) {
		try {
			driver.findElement(By.xpath("//*[.='" + xpathQuestions + "']//following::span[contains(.,'" + xpathParameter
					+ "')][1]//preceding::*[@class='mat-radio-outer-circle'][1]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[.='" + xpathQuestions + "']//following::span[contains(.,'" + xpathParameter
					+ "')][1]//preceding::*[@class='mat-radio-inner-circle'][1]")).click();
		}
	}

	public void populateListbox(String xpathParameter, String value) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "']//following::textarea[1]")).sendKeys(value);
	}

	public void selectDropdown(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[1]"))
				.click();
		driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mat-option-text']")).click();

	}

	public void errorLabel(String xpathParameter) {
		driver.findElement(By.xpath("//mat-error[.='" + xpathParameter + "'][1]")).isDisplayed();
	}

	public void errorContent(String xpathParameter) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "'][@id='businessError0'][1]")).isDisplayed();

	}
	public void ScrollMenu(String xpathParameter) throws InterruptedException {		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text()='"+xpathParameter+"'][1]")));
		 Thread.sleep(500); 
	}

	public void clickElement(WebElement ele) throws InterruptedException {
		ele.click();
		Thread.sleep(2000);
	}

	public void screenShot(String fileName, String status, String message) throws Exception {
		screenShot screen = new screenShot();
		 String  screenShotPath = screenShot.takeSnapShot(driver, "target\\"+fileName+".jpg");
		 if(status.equalsIgnoreCase("Pass")) {
	       test.log(Status.PASS,message);
		 }
		 else {
			 test.log(Status.FAIL,message); 
		 }
	       //test.info(message);
	       test.addScreenCaptureFromPath(screenShotPath);
		
	}
	
	public void selectTable(String ssnValue,int columnValue, int tableId,String tableName) {
		WebElement table = driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[@id='dataTableId']["+tableId+"]"));

	    List<WebElement> rows = table.findElements(By.tagName("mat-row"));

	    int row_count = rows.size();
	    System.out.println("Total Row: " + row_count);
	    label1:
	    for (int row = 0; row < row_count; row = row + 1) {
	        List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
	        int columns_count = columns.size();
	        System.out.println("Number of cells In Row " + row + " are " + columns_count);
	        for (int column = 0; column < columns_count; column++) {
	            String celtext = columns.get(column).getText();
	            if (celtext.equals(ssnValue)) {
	            	driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[@id='dataTableId']["+tableId+"]/mat-row["+(row+1)+"]/mat-cell["+(columnValue)+"]/a[1]")).click();
	            	break label1;
	            }
	        }
	       
	    }
	}
	
	public void selectRadioInTable(String ssnValue,int columnValue, int tableId,String tableName) {
		WebElement table = driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[contains(@id ,'dataTable')]["+tableId+"]"));

	    List<WebElement> rows = table.findElements(By.tagName("mat-row"));

	    int row_count = rows.size();
	    System.out.println("Total Row: " + row_count);
	    label1:
	    for (int row = 0; row < row_count; row = row + 1) {
	        List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
	        int columns_count = columns.size();
	        System.out.println("Number of cells In Row " + row + " are " + columns_count);
	        for (int column = 0; column < columns_count; column++) {
	            String celtext = columns.get(column).getText();
	            if (celtext.equals(ssnValue)) {
	            	driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[contains(@id ,'dataTable')]["+tableId+"]/mat-row["+(row+1)+"]/mat-cell["+(columnValue)+"]//following::*[@class='mat-radio-container']")).click();
	            	break label1;
	            }
	        }
	       
	    }
	}
	public void selectDateInTable(String ssnValue,int columnValue, int tableId,String tableName,String value) {
		WebElement table = driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[contains(@id ,'dataTable')]["+tableId+"]"));

	    List<WebElement> rows = table.findElements(By.tagName("mat-row"));

	    int row_count = rows.size();
	    System.out.println("Total Row: " + row_count);
	    label1:
	    for (int row = 0; row < row_count; row = row + 1) {
	        List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
	        int columns_count = columns.size();
	        System.out.println("Number of cells In Row " + row + " are " + columns_count);
	        for (int column = 0; column < columns_count; column++) {
	            String celtext = columns.get(column).getText();
	            if (celtext.equals(ssnValue)) {
	            	driver.findElement(By.xpath("//*[.='"+tableName+"']//following::*[contains(@id ,'dataTable')]["+tableId+"]/mat-row["+(row+1)+"]/mat-cell["+(columnValue)+"]//input[1]")).sendKeys(value);
	            	break label1;
	            }
	        }
	       
	    }
	}

	// *[.='Joint Employment/Management Agreement Arrangement
	// ']//following::*[contains(@id
	// ,'dataTable')][1]/mat-row[1]/mat-cell[5]//input[1]
	public String retrieveValueFromTable(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver
				.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId'][" + tableId + "]"));
		String value = "";
		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					value = driver
							.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]"))
							.getText();

					break label1;
				}
			}

		}
		return value;
	}

	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

	public void enterCurrentDate(WebElement ele) {
		String date = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
		ele.sendKeys(date);
	}

	public void waitForElementClicable(WebElement ele) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
		Thread.sleep(3000);
		ele.click();
	}

	public void doSendKeysWithWait(WebElement ele, String data) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
		Thread.sleep(2000);
		ele.sendKeys(data);
	}
	
	public static long createRandomInteger(long aStart, long aEnd){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    Random aRandom = new Random();
	    long range = aEnd - (long)aStart + 1;
	  
	    long fraction = (long)(range * aRandom.nextDouble());
	    
	    long randomNumber =  fraction + (long)aStart;    
	    System.out.println("Generated : " + randomNumber);
	    return randomNumber;

	  }
	public  void uploadDoc(String fileName) throws AWTException {
		 Robot rb = new Robot();	 
		    StringSelection str = new StringSelection("D:\\AutomationFiles\\"+fileName);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);		    
		     rb.keyPress(KeyEvent.VK_CONTROL);
		     rb.keyPress(KeyEvent.VK_V);	    
		    rb.keyRelease(KeyEvent.VK_CONTROL);
		    rb.keyRelease(KeyEvent.VK_V);   
		    rb.keyPress(KeyEvent.VK_ENTER);
		    rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void selectCheckbox(String xpathParameter) {		
			driver.findElement(By.xpath("//mat-label[contains(.,'"+xpathParameter+"')]//preceding::*[@class='mat-checkbox-inner-container'][1]")).click();
	}
	
	public void selectLink(String xpathParameter, String value) {		
		driver.findElement(By.xpath("//mat-label[contains(.,'"+xpathParameter+"')]//following::*[.='"+value+"'][1]")).click();
}
	public void clickOnLink(String xpathParameter) {
		driver.findElement(By.xpath("//u[contains(.,'"+xpathParameter+"')][1]")).click();

		
	}
	
	public void database_UpdateQuery(String query) throws SQLException, InterruptedException {
	
	
	System.out.println(query);
	
	try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
		Class.forName("com.ibm.db2.jcc.DB2Driver");} 
	catch (ClassNotFoundException e) {e.printStackTrace();
	}
	String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
	String user = prop.getProperty("databaseUserId");
	String password = "Tata@1234";
	Connection con=(Connection) DriverManager.getConnection( url, user, password);
	System.out.println("Connected Successfully");
	PreparedStatement p=null;
	//Statement stmt=con.createStatement();
	//stmt.executeQuery(query);
	Thread.sleep(200000);
	p=con.prepareStatement(query);
	Thread.sleep(2000);
	p.execute();
	Thread.sleep(2000);
	con.close();
	}
	
	public Map<String, String> database_SelectQuery(String query) throws SQLException {
		
		
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");} 
		catch (ClassNotFoundException e) {e.printStackTrace();
		}
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con=(Connection) DriverManager.getConnection( url, user, password);
		System.out.println("Connected Successfully");
		
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery(query);
		while(rs.next())
		{
			
			results.put("Fein",rs.getString("FEIN"));
			results.put("Ean",rs.getString("EAN"));
			break;
		}
		
		con.close();
		return results;
		}
	
	public void selectDropdownEquals(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[1]"))
				.click();
		driver.findElement(By.xpath("//*[.='" + value + "'][@class='mat-option-text']")).click();

	}
}