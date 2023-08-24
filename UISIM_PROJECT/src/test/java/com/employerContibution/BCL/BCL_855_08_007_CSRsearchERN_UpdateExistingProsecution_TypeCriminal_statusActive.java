package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_08_007_CSRsearchERN_UpdateExistingProsecution_TypeCriminal_statusActive extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search ERN details and update existing prosecution for Prosecution Type is 'Criminal' and status is 'Active'", groups = "Regression")
	public void BCL_855_08_007() throws Exception {

		test = report.createTest("BCL_855_08_007_Verify CSR can search ERN details and update existing prosecution for Prosecution Type is 'Criminal' and status is 'Active'");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		//BclPage bcllocators = new BclPage(driver);
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_employer WHERE EMPLOYER_ID in(SELECT EMPLOYER_ID FROM T_TX_PROSECUTION WHERE PROSECUTION_STATUS_CD = 'CLSD') AND EAN IS NOT NULL AND LENGTH (EAN)=7", "EAN");
        String ernNum = databaseEanResult.get("EAN");
        
        if((ernNum==null)||(ernNum.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ernNum +".");}
      
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		commonFuntions.ScrollMenu("Prosecution");
		sleep();
		commonFuntions.screenShot("Menu2", "Pass", "Click on Employer Collection");
		commonFuntions.clickMenu("Prosecution");
		
		//---Prosecution-COL-593---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution ERN", "Pass", "Successful launch to COL-593 screen");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		sleep();
		commonFuntions.screenShot("Prosecution ERN", "Pass", "Prosecution ERN-COL-593");
		commonFuntions.clickButton("Continue ");
		
		
		//---List of Prosecutions-COL-594---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecutions List", "Pass", "List of Prosecutions-COL-594");
		commonFuntions.selectRadioInTable("05-09531", 1, 1, "");
		sleep();
		commonFuntions.screenShot("Select Prosecution", "Pass", "Select Prosecutions from list-COL-594");
		commonFuntions.clickButton("Continue ");
		
		//---Update Prosecution Details-COL-596---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update Prosecution Details", "Pass", "Update Prosecution Details-COL-596");
		commonFuntions.enterTextboxContains("Prosecution Case Number", "0101");
		commonFuntions.selectDropdown("4. Prosecution Type", "Criminal");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "6/20/2023");
		commonFuntions.enterTextboxContains("6. Assigned To", "Owner of Agency");
		commonFuntions.selectDropdown("7. Status", "Active");
		sleep();
		commonFuntions.screenShot("Updated Prosecution Details", "Pass", "given updated Prosecution details");
		commonFuntions.clickButton("Submit ");
		
		//---Prosecution Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Confirmation recieved", "Pass", "Prosecution Confirmation recieved-SUC-002");
		commonFuntions.clickButton("Home ");

		commonFuntions.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFuntions.screenShot("BCL.855.08.007", "Pass", "Successfully passed TC BCL.855.08.007");
		
		
		
	}
}