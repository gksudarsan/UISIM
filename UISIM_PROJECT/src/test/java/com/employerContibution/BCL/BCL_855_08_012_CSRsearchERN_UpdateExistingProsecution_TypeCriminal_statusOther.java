package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_08_012_CSRsearchERN_UpdateExistingProsecution_TypeCriminal_statusOther extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search ERN details and update existing prosecution for Prosecution Type is 'Criminal' and status is 'Other' with comments", groups = "Regression")
	public void BCL_855_08_012() throws Exception {

		test = report.createTest("BCL_855_08_012_Verify CSR can search ERN details and update existing prosecution for Prosecution Type is 'Criminal' and status is 'Other' with comments");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BclPage bcllocators = new BclPage(driver);
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_employer WHERE EMPLOYER_ID in(SELECT EMPLOYER_ID FROM T_TX_PROSECUTION WHERE PROSECUTION_STATUS_CD = 'CLSD') AND EAN IS NOT NULL AND LENGTH (EAN)=7", "EAN");
        String ERN_Num = databaseEanResult.get("EAN");
        
        if((ERN_Num==null)||(ERN_Num.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ERN_Num +".");}
		
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.screenShot("Menu1", "Pass", "Click on Menu page");
		commonFuntions.ScrollMenu("Employer Collection");
		sleep(1000);
		commonFuntions.clickMenu("Employer Collection");
		sleep(1000);
		commonFuntions.screenShot("Menu2", "Pass", "Click on Employer Collection");
		commonFuntions.ScrollMenu("Prosecution");
		sleep(1000);
		commonFuntions.clickMenu("Prosecution");
		sleep(1000);
		commonFuntions.screenShot("Menu3", "Pass", "Click on Prosecution");
		
		//---Prosecution-COL-593---//
		commonFuntions.enterTextbox("Employer Registration Number", ERN_Num);
		sleep(1000);
		commonFuntions.screenShot("Prosecution ERN", "Pass", "Prosecution ERN-COL-593");
		commonFuntions.clickButton("Continue ");
		
		
		//---List of Prosecutions-COL-594---//
		sleep(1000);
		commonFuntions.screenShot("Prosecutions List", "Pass", "List of Prosecutions-COL-594");
		commonFuntions.selectRadioInTable("05-09531", 1, 1, "");
		sleep(1000);
		commonFuntions.screenShot("Select Prosecution", "Pass", "Select Prosecutions from list-COL-594");
		commonFuntions.clickButton("Continue ");
		
		//---Update Prosecution Details-COL-596---//
		sleep(2000);
		commonFuntions.screenShot("Update Prosecution Details", "Pass", "Update Prosecution Details-COL-596");
		commonFuntions.enterTextboxContains("Prosecution Case Number", "0102");
		commonFuntions.selectDropdown("4. Prosecution Type", "Criminal");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "6/20/2023");
		commonFuntions.enterTextboxContains("6. Assigned To", "Owner of Agency");
		commonFuntions.selectDropdown("7. Status", "Other (add comments)");
		bcllocators.Entercomments_COL596.clear();
		bcllocators.Entercomments_COL596.sendKeys("As Status is Other so Proceed accordingly");
		sleep(1000);
		commonFuntions.screenShot("Updated Prosecution Details", "Pass", "given updated Prosecution details");
		commonFuntions.clickButton("Submit ");
		
		//---Prosecution Confirmation-SUC-002---//
		sleep(1000);
		commonFuntions.screenShot("Confirmation recieved", "Pass", "Prosecution Confirmation recieved-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep(1000);
		commonFuntions.screenShot("Home Page", "Pass", "Home page");
	}
}