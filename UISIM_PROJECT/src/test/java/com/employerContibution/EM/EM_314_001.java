package com.employerContibution.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_314_001 extends TestBase {

	@Test(priority = 0, description = "EM.314.001.Verify CSR is able to enter request for change method of financing to reimbursable and new ERN for employer type non profit.")
	public void Test_EM_314_001() throws Exception {

		// Object creation for the locator
		employerManagement em = new employerManagement();
		employerManagementLocators emlocator = new employerManagementLocators();

		// For reading the data from .property file
		String EAN = prop.getProperty("EAN");

		test = report.createTest(
				"EM.314.001.Verify CSR is able to enter request for change method of financing to reimbursable and new ERN for employer type non profit.");
		// LoginPage loginpage=new LoginPage(driver);
		commonStepDefinitions commonfunction = new commonStepDefinitions();

		commonfunction.login("peouseranup", "Admin@12345678");
		commonfunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		// Steps for reaching the the option
		sleep(2000);
		commonfunction.waitForLoadingIconToDisappear();
		commonfunction.clickMenu("Menu");
		sleep();
		commonfunction.clickMenu("Account Maintenance");
		sleep();
		commonfunction.screenShot("Menu", "Pass", "Account Maintenance");
		sleep();
		commonfunction.clickMenu("Employer Account Maintenance");
		sleep();
		commonfunction.clickMenu("Change in Method of Financing");
		sleep();
		commonfunction.screenShot("ETR-228", "Pass",
				"landing successfully on Change in Method of Financing - Enter ERN ");

		commonfunction.clickButton("Continue ");
		sleep(4000);
		// Verifying the Validation message
		WebElement mandatoryMessage = driver.findElement(By.xpath("//*[@id=\"mat-error-2\"]"));
		String name = mandatoryMessage.getText();
		System.out.println("Required message" + name);
		Assert.assertEquals(name, "Required");

		/*
		 * Remvoing the script as the functionality get updatedd //Entering the ERN
		 * Number em.updateAddress(EAN); commonfunction.clickButton("Continue ");
		 * 
		 * // Navigating to the pervious screen by clicking on the pervoius button
		 * Step-8 commonfunctionname.clickButton("Previous ");
		 * 
		 * //Step 9 //Entering the ERN Number em.updateAddress(EAN);
		 * commonfunction.clickButton("Continue");
		 * 
		 * //Step 10 commonfunction.clickButton("Continue "); WebElement requiredMessage
		 * = driver.findElement(By.xpath("//*[text()='Required']"));
		 * Assert.assertEquals(requiredMessage, "Required");
		 */
		// Step 12
		commonfunction.selectRadio("Yes ");
		Thread.sleep(2000);

		commonfunction.enterTextboxContains("Requested Effective Date", "3/17/2023");
		Thread.sleep(2000);
		// emlocator.entercommentbox("Test Message");

		driver.findElement(By.xpath("//textarea[@placeholder='Please provide Comments']")).sendKeys("Test Cooments");
		Thread.sleep(1000);
		commonfunction.clickButton("Continue");
		sleep(4000);
		commonfunction.uploadDoc("Upload");
		sleep(2000);
		commonfunction.clickButton("Submit");
		sleep(4000);
		commonfunction.screenShot("change method of financing", "Pass", "Change Successfully");
		commonfunction.clickButton("Home");

	}

}
