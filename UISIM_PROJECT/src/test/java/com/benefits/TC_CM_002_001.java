package com.benefits;



import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class TC_CM_002_001 extends TestBase
{

String SSN = prop.getProperty("SSN");
	
	@Test(priority=1, description = "Test sample",groups = {"Regression"})
	public void Testing123() throws Exception
	{
		 test = report.createTest("Log in to Benefits Unemployment Insurance");
		
		claimsIntake cl = new claimsIntake();
		System.out.println(SSN);
		
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	      screenShot screen = new screenShot();
	     String  screenShotPath = screenShot.takeSnapShot(driver, "target\\ApplicationLogin.jpg");
	       test.log(Status.PASS,"Login is  successful");
	       test.info("Login is  successful");
	       test.addScreenCaptureFromPath(screenShotPath);
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).click();
	       Thread.sleep(000);
	       driver.findElement(By.xpath("//span[text()='Menu']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='File Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//input[@id='ssnBeanId']")).sendKeys(SSN);Thread.sleep(1000);
	       driver.findElement(By.xpath("//input[@id='confirmSsnBeanId']")).sendKeys(SSN);
	       driver.findElement(By.xpath("//button[@type='submit']")).click();  driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
			Thread.sleep(5000);
			//System.out.println("before refresh");
			driver.navigate().refresh();Thread.sleep(5000);
		
			  System.out.println("after refresh");
			 // driver.navigate().to(driver.getCurrentUrl());Thread.sleep(5000);
			 System.out.println("after navigate");
			 // driver.get(driver.getCurrentUrl());Thread.sleep(5000);
			  System.out.println("after navigate last");
			
		  
	      driver.findElement(By.xpath("//input[@name='loginform:username']")).sendKeys("NDFJP3");
	       
	       driver.findElement(By.xpath("//input[@name='loginform:password']")).sendKeys("Admin@12345678");
	       driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();Thread.sleep(5000);
	       //driver.navigate().refresh();Thread.sleep(5000);
	       driver.navigate().refresh();Thread.sleep(5000);
	       driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']"));
	       driver.findElement(By.xpath("//span[text()='Menu']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='File Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//input[@id='ssnBeanId']")).sendKeys(SSN);Thread.sleep(1000);
	       driver.findElement(By.xpath("//input[@id='confirmSsnBeanId']")).sendKeys(SSN);
	       driver.findElement(By.xpath("//button[@type='submit']")).click();  driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
			Thread.sleep(5000);
			//System.out.println("before refresh");
			driver.navigate().refresh();Thread.sleep(5000);
		
			  System.out.println("after refresh");
			 // driver.navigate().to(driver.getCurrentUrl());Thread.sleep(5000);
			 System.out.println("after navigate");
			 // driver.get(driver.getCurrentUrl());Thread.sleep(5000);
			  System.out.println("after navigate last");
			
		  
	      driver.findElement(By.xpath("//input[@name='loginform:username']")).sendKeys("NDFJP3");
	       
	       driver.findElement(By.xpath("//input[@name='loginform:password']")).sendKeys("Admin@12345678");
	       driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();Thread.sleep(5000);
	       //driver.navigate().refresh();Thread.sleep(5000);
	       driver.navigate().refresh();Thread.sleep(5000);
	       driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']"));
	       driver.findElement(By.xpath("//span[text()='Menu']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='File Unemployment Claim']")).click();Thread.sleep(2000);
	       driver.findElement(By.xpath("//input[@id='ssnBeanId']")).sendKeys(SSN);Thread.sleep(1000);
	       driver.findElement(By.xpath("//input[@id='confirmSsnBeanId']")).sendKeys(SSN);
	       driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		
		
		
		System.out.println("test test ..................");
		
	}
	
	public void enterTextbox(String xpathParameter,String value) {
		driver.findElement(By.xpath("//*[.='"+xpathParameter+"']//following::input[1]")).sendKeys(value);
	}
	
	public void clickButton(String xpathParameter) {
		driver.findElement(By.xpath("//button[.='"+xpathParameter+"'][1]"));	
	}

	
	//Logging out of application...
	@Test
	public void Testing() throws Exception
	{
		ExtentTest test = report.createTest("Log in to second testcase");
		test.log(Status.PASS, "Tested successfully");
		//Logging in to application...
		claimsIntake cl = new claimsIntake();
		System.out.println(SSN);
		 // driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
			Thread.sleep(5000);
			screenShot screen = new screenShot();
		     String  screenShotPath = screenShot.takeSnapShot(driver, "target\\LoginPage.jpg");
		       //test.log(Status.FAIL,"Login is not successful");
		       test.info("Login is  successful");
		       test.addScreenCaptureFromPath(screenShotPath);
	}
	
	@Test(groups = {"Regression"})
	public void Testing_thirdTestcase() throws Exception
	{
		ExtentTest test = report.createTest("Log in to second testcase");
		test.log(Status.PASS, "Tested successfully");
		//Logging in to application...
		claimsIntake cl = new claimsIntake();
		System.out.println(SSN);
		 // driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
			Thread.sleep(5000);
	}

}