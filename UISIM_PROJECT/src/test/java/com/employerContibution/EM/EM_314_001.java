package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_314_001 extends TestBase {


	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter request for change method of financing to reimbursable and new ERN for employer type non profit. ", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EM_413_02_002() throws Exception {
		
		test = report.createTest("EM.314.001: Verify CSR is able to enter request for change method of financing to reimbursable and new ERN for employer type non profit. ");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManage = new employerManagement(driver);
		
		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_REGULAR_EMPLOYER tre JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID WHERE tre.BUSINESS_TYPE  = 'NONP' AND tea.REGISTRATION_STATUS = 'C'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);
				
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.ScrollMenu("Change in Method of Financing");
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Change in Method of Financing");
		commonFunction.clickMenu("Change in Method of Financing");
		
		
		// --- ETR-228 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing(ETR-228) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.errorLabel("Required");
		commonFunction.screenShot("EM314001", "Pass", "ERN cannot be blank Error");
		
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", "5715438");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EM314001", "Pass", "Error - The Employer Registration Number (ERN) provided does not exist in the system.");
		
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //0658736
		sleep();
		commonFunction.screenShot("EM314001", "Pass", "Valid ERN with employerType - NONP");
		commonFunction.clickButton("Continue ");
		
		// --- ETR-229 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing(ETR-229) page");
		commonFunction.clickButton("Continue ");
		
		sleep();
//		commonFunction.errorLabelFollowingField("Do you want to change the method of financing to reimbursable?", "Required");
//		sleep();
//		commonFunction.errorLabelFollowingField("Requested Effective Date", " Required ");
//		sleep();
//		commonFunction.errorLabelFollowingField("Comment", " Required");
//		sleep();
//		commonFunction.errorLabelFollowingField("Source", " Required ");
//		sleep();
//		commonFunction.errorLabelFollowingField("Source Type", " Required ");
//		sleep();
		commonFunction.screenShot("EM314001", "Pass", "Mandatory fields needed to Continue");
		
		commonFunction.selectRadioQuestions("Do you want to change the method of financing to reimbursable?", "Yes ");
		commonFunction.enterTextboxContains("Enter the receipt date of written request.", "22/10/2023");
		commonFunction.enterTextboxContains("Requested Effective Date", "23/10/20203");
		commonFunction.enterTextboxContains("New ERN", "1111");
		empManage.commentId.sendKeys("Ok Tested");
		
		empManage.sourceId_SREG435.click();
		sleep();
		empManage.nys100Paper_SREG705.click();
		empManage.sourceTypeId_SREG435.click();
		sleep();
		empManage.nys100.click();
		sleep();
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EM314001", "Pass", "Some mandatory fields are required to continue");
		
		sleep();
		commonFunction.enterTextboxContains("Enter the receipt date of written request.", "");
		commonFunction.enterPastDate("Enter the receipt date of written request.", 12);
		commonFunction.enterTextboxContains("Requested Effective Date", "");
		commonFunction.enterCurrentDate("Requested Effective Date");
		commonFunction.enterTextboxContains("New ERN", "8600013");
		sleep();
		commonFunction.screenShot("EM314001", "Pass", "All fields are entered and clicked on continue");
		commonFunction.clickButton("Continue ");
		
		// --- ETR-230 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing Verification(ETR-230) page");
		commonFunction.selectRadioQuestions("Suppress Correspondence", "Yes ");
		commonFunction.screenShot("EM314001", "Pass", "Radio selected in ETR-230 page");
		sleep();
		commonFunction.clickButton("Submit ");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing Confirmation(SUC-002) page");
		commonFunction.clickButtonContains("Home");
		
		sleep(4000);
		commonFunction.screenShot("EM314001", "Pass", "TC EM.314.001 passed successfully");
		
		System.out.println("Pass");
	}
		
		

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
