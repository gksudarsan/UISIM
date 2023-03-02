package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.ui.utilities.screenShot;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;

public class commonStepDefinitions extends TestBase {

	public void login(String userName,String password) throws Exception {
		screenShot screen = new screenShot();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
	      String screenShotPath = screenShot.takeSnapShot(driver, "target\\LoginPage.jpg");
	      test.log(Status.INFO,"HomePage");
	      test.addScreenCaptureFromPath(screenShotPath);
	      loginPage.loginLink.click();
		 // driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
			Thread.sleep(5000);
			test.log(Status.PASS,"User Launched website");
			//System.out.println("before refresh");
			driver.navigate().refresh();Thread.sleep(5000);
		
			
			 screenShotPath = screenShot.takeSnapShot(driver, "target\\AfterRefreshPage.jpg");
		       test.log(Status.INFO,"AfterRefresh");
		       test.addScreenCaptureFromPath(screenShotPath);
			  System.out.println("after refresh");
			  driver.navigate().to(driver.getCurrentUrl());Thread.sleep(5000);
			 System.out.println("after navigate");
			  driver.get(driver.getCurrentUrl());Thread.sleep(5000);
			  System.out.println("after navigate last");			
			  enterTextbox("Username",userName);			  
			  test.log(Status.PASS,"User entered Username");
			  enterTextbox("Password",password);			  
			  test.log(Status.PASS,"User entered Password");
			      driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();Thread.sleep(5000);
	     Thread.sleep(15000);     
	     driver.navigate().refresh();
	     Thread.sleep(10000);
	     
	    
	}
	
	public void enterTextbox(String xpathParameter,String value) {
		driver.findElement(By.xpath("//*[.='"+xpathParameter+"']//following::input[1]")).sendKeys(value);
	}
	
	public void clickButton(String xpathParameter) {
		driver.findElement(By.xpath("//button[.='"+xpathParameter+"'][1]"));	
	}

}
