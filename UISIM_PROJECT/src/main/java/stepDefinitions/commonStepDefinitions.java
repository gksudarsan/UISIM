package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.ui.utilities.screenShot;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;

public class commonStepDefinitions extends TestBase {

	public void login(String userName,String password) throws Exception {
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Thread.sleep(2000);
	      screenShot("LoginPage","Pass","HomePage");
	      loginPage.loginLink.click();		 
			Thread.sleep(2000);
			test.log(Status.PASS,"User Launched website");			
			driver.navigate().refresh();
			Thread.sleep(2000);
			screenShot("AfterRefreshPage","Pass","AfterRefresh");
			  driver.navigate().to(driver.getCurrentUrl());
			  Thread.sleep(5000);
			 
			  driver.get(driver.getCurrentUrl());Thread.sleep(5000);
			  			
			  enterTextbox("Username",userName);			  
			  test.log(Status.PASS,"User entered Username");
			  enterTextbox("Password",password);			  
			  test.log(Status.PASS,"User entered Password");
			      driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();Thread.sleep(5000);
	     Thread.sleep(15000);     
	     driver.navigate().refresh();
	     Thread.sleep(10000);
	     screenShot("okPopUpButton","Pass","okPopUp");
	     loginPage.okPopUpButton.click();	
	     Thread.sleep(5000);
	     driver.navigate().refresh();
	     Thread.sleep(3000);
	    
	}
	
	public void enterTextbox(String xpathParameter,String value) {
		driver.findElement(By.xpath("//*[.='"+xpathParameter+"']//following::input[1]")).sendKeys(value);
	}
	
	public void clickButton(String xpathParameter) {
		driver.findElement(By.xpath("//button[.='"+xpathParameter+"'][1]")).click();	
	}
	
	public void clickMenu(String xpathParameter) {
		driver.findElement(By.xpath("//*[text()='"+xpathParameter+"'][1]")).click();	
	}
	
	public void selectRadio(String xpathParameter) {
		driver.findElement(By.xpath("//*[contains(.,'"+xpathParameter+"')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-outer-circle']")).click();
		
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

}
