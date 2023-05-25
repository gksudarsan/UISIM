//----------------------Palak
package com.employerContibution.EM;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_443_002_CSR_Verify_CSR_is_able_to_search_POA_TPR_detail_and_Add_the_association_for_POA_TPR_with_designation_type_in_Limited_Unemployment_Insurance_Matters extends TestBase{

	@Test(priority=1, description = "EM.443.002.Verify CSR is able to search POA/TPR detail and Add the association for POA/TPR with designation type in \"Limited Unemployment Insurance Matters\"",groups = {"Regression"})
	public void EM_443_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		HomePage home = new HomePage(driver);
		test = report.createTest("EM.443.002.Verify CSR is able to search POA/TPR detail and Add the association for POA/TPR with designation type in \"Limited Unemployment Insurance Matters\"");
		//-------Login
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		
		//------Menu
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.screenShot("Account Maintenance", "Pass", "Employer Account Maintenance");
		commonFuntions.clickMenu("Add or Remove POA/TPR Association");
		sleep(2000);
		commonFuntions.screenShot("Add or Remove POA/TPR Association", "Pass", "Add or Remove POA/TPR Association");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		
		//------SREG 430
	
		commonFuntions.errorLabel("Required");
		commonFuntions.screenShot("Required error", "Pass", "Add or Remove POA/TPR Association – Enter ERN");
		Thread.sleep(2000);
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		commonFuntions.enterTextbox("Employer Registration Number", ernValue);
		commonFuntions.clickButtonContains("Continue");
		sleep();
		try {
		commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		}
		catch(Exception e) {
			System.out.println("Error Content Message Not Appeared");
		}

		//query to fetch ern number from database
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS DESC","EAN");
		Thread.sleep(10000);
		//Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND ACCOUNT_STATUS='LIAB'","EAN");
		//Thread.sleep(10000);
		String ernNumber=databaseResults.get("EAN");
		System.out.println("The selected ERN is: "+ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number",ernNumber);
		Thread.sleep(2000);
		commonFuntions.screenShot("ERN", "Pass", "Add or Remove POA/TPR Association – Enter ERN");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		
		//----SREG 537
		
	
		commonFuntions.screenShot("Association to Employer", "Pass", "Add or Remove Third Party Association to Employer");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Designation Type", "Limited Unemployment Insurance Matters ");
		commonFuntions.screenShot("Association to Employer", "Pass", "Add or Remove Third Party Association to Employer");
		commonFuntions.clickButton(" Search POA/TPR ");
		sleep();
		commonFuntions.screenShot("Search POA/Third Party Representative", "Pass", "Search POA/Third Party Representative");
		Thread.sleep(2000);
		
		//----SREG 040
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", "Suma");
		commonFuntions.clickButton(" Search ");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");sleep();
		commonFuntions.screenShot("Search POA/Third Party Representative", "Pass", "Search POA/Third Party Representative");
		commonFuntions.errorContent("Please select a record to proceed further.");
		Thread.sleep(2000);
		
		commonFuntions.selectRadio("Select");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		//--------SREG 537
		commonFuntions.screenShot("required error message", "Pass", "Add or Remove Third Party Association to Employer");sleep();
		sleep(2000);
		commonFuntions.selectRadioInTable("Limited Unemployment Insurance Matters", 1, 1, "Add or Remove Third Party Association to Employer");
		//commonFuntions.selectRadio("Select");
		Thread.sleep(2000);
		//commonFuntions.enterTextboxContains("Comment", "Settest");
		home.commentBox.sendKeys("Testing");
		
		Thread.sleep(2000);
		commonFuntions.selectCheckbox("Additional authorization: Representative is also authorized to receive disclosures of, and review and inspect confidential Federal tax information and to perform any and all acts that I (we) can perform with respect to those tax matters as they bear on Unemployment Insurance matters. Note:  Confidential Federal Tax information shall include any and all information provided to the Department of Internal Revenue Service.");
		Thread.sleep(2000);
		commonFuntions.screenShot("Third Party Associate Employer", "Pass", "Add or Remove Third Party Association to Employer");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		//------Home
		Thread.sleep(2000);
		commonFuntions.screenShot("Confirmation Message", "Pass", "Third Party Representative Association to Employer Confirmation");
        
		Thread.sleep(2000);
        commonFuntions.clickButton("Home ");
        Thread.sleep(2000);
        
        
        //test cases ends here Completed by palak

	}


}
