package com.employerContibution.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.employerManagement;
import com.ui.utilities.screenShot;

public class EM_260_001 {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase
	{

	String EAN = prop.getProperty("EAN");
		
		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			 test = report.createTest("Logged into EC Application");
			//Logging in to application...
			claimsIntake cl = new claimsIntake();
			employerManagement em =  new employerManagement();
			System.out.println(EAN);
			screenShot screen = new screenShot();
		      String screenShotPath = screenShot.takeSnapShot(driver, "target\\LoginPage.jpg");
		      test.log(Status.INFO,"HomePage");
		      test.addScreenCaptureFromPath(screenShotPath);
			
			  driver.findElement(By.xpath("//span[text()='LOG IN ']")).click();
				Thread.sleep(5000);
				test.log(Status.PASS,"User Launched website");
				//System.out.println("before refresh");
				driver.navigate().refresh();Thread.sleep(5000);
			
				driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
				
				em.updateAddress(null, screenShotPath, screenShotPath, EAN);
		
		}
}
}