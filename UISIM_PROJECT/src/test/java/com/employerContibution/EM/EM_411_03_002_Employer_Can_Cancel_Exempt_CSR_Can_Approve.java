package com.employerContibution.EM;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_434;
import com.ui.pages.SREG_435;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_411_03_002_Employer_Can_Cancel_Exempt_CSR_Can_Approve extends TestBase{

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {
		String EAN = prop.getProperty("EAN");
		
		
		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
//			String EAN = "0000022";
			test = report.createTest("EM.411.03.002.Verify Employer is able to select option Cancel Exempt for Unemployment Insurance with Business never opened and I never had employees and the system creates a task and CSR reviews and Approve");
			commonStepDefinitions cf = new commonStepDefinitions();
//			PEOPage peoPage = new PEOPage(driver);
			EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
			
			HomePage home = new HomePage(driver);
			cf.login(COMMON_CONSTANT.EMPLOYER_USER_4,COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
			sleep(2000);
			empPage.menuButtonHomepage.click();
			sleep(2000);
			cf.clickMenu("Account Maintenance");
			sleep(2000);
			cf.clickMenu("Employer Account Maintenance");
			sleep(2000);
			cf.clickMenu("Close or Cancel Account");
			sleep(4000);
			
			/*-----------------SREG-438-------------------*/
			cf.screenShot("EmpRegister8", "Pass", "Navigated to SREG-438 page");
			cf.selectRadio("Do you want to cancel your account?");
			sleep(2000);
			cf.selectRadio("Cancel Exempt for Unemployment Insurance");
			
			WebElement drp = driver.findElement(By.xpath("//div[contains(@class,'mat-form-field-infix')]//span"));
			cf.safeJavaScriptClick(drp);
			sleep(2000);
			WebElement drpVal = driver.findElement(By.xpath("//span[text()=' Business never opened and I never had employees ']"));
			cf.safeJavaScriptClick(drpVal);
			sleep();
			cf.clickButtonContains("Continue ");
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
			
			/*-----------------SUC-002-------------------*/
			cf.screenShot("EmpRegister8", "Pass", "Navigated to SUC page");
			cf.clickButtonContains("Home ");
			sleep(20000);
			cf.logoutAndLogin("ndfjp3", "Admin@1234567891");
			sleep(3000);
			cf.waitForLoadingIconToDisappear();
			PEOPage.queue.click();
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
			
			cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
			WebElement filter = driver.findElement(By.xpath("//input[contains(@id,'mat-input')]"));
			filter.sendKeys("Account Cancell");
			sleep(3000);
			
			cf.clickOnLinkAnchorTag("Account Cancellation Request");
			sleep();
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
			cf.clickButton("Open Work Item ");
			sleep();
			cf.waitForLoadingIconToDisappear();
			
			sleep(4000);
			
			cf.clickOnLinkAnchorTag("Update Account Status");
			
			sleep(10000);
			Set<String> handles =  driver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			String parentWindowId = it.next();
			String childWindowId = it.next();
			driver.switchTo().window(childWindowId);
			
			/*----------------SREG-435------------*/
			
			cf.selectDropdown("Status of Employer Account", " Cancelled Exempt ");
			cf.enterTextboxContains("Comments", "sdfjh");
			cf.selectDropdown("Source", " Correspondence/Email ");
			cf.selectDropdown("Source Type", " Correspondence/Email ");
			cf.clickButton("Submit ");
			
			
			
			
			

					
		}
	}
}
