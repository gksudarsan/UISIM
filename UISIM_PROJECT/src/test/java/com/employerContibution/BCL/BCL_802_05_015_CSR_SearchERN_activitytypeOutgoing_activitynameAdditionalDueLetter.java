package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_802_05_015_CSR_SearchERN_activitytypeOutgoing_activitynameAdditionalDueLetter extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Search ERN with select bankruptcy appropriate case status and add Bankruptcy Case Activity for activity type Outgoing and activity name 'Additional Due Letter'", groups = "Regression")
	public void BCL_802_05_015() throws Exception {

		test = report.createTest("BCL_802_05_015 - Verify CSR can Search ERN with select bankruptcy appropriate case status and add Bankruptcy Case Activity for activity type Outgoing and activity name 'Additional Due Letter'");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BclPage bcllocators = new BclPage(driver);
		
		//Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV' AND EAN IS NOT NULL AND LENGTH (EAN)=7", "EAN");
        //String ERN_Num = databaseEanResult.get("EAN");
        
        String ERN_Num = "0464364";
        
        if((ERN_Num==null)||(ERN_Num.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ERN_Num +".");}
		System.out.println("DB connected successfully and fetched ERN is: "+ ERN_Num +".");
        
		// ---Login---
		//commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.Collections_Specialist_3_User, COMMON_CONSTANT.Collections_Specialist_3_pwd);
		//sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.screenShot("Menu1", "Pass", "Click on Menu page");
		//commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(1000);
		commonFuntions.screenShot("Menu2", "Pass", "Contribution Collection");
		sleep(1000);
		bcllocators.clickMenu_Bankruptcy.click();
		sleep(1000);
		commonFuntions.screenShot("Menu3", "Pass", "Click on Bankruptcy");
		//commonFuntions.ScrollMenu("Review/Update Bankruptcy Case Activity");
		commonFuntions.screenShot("Menu4", "Pass", "select Review/Update Bankruptcy");
		commonFuntions.clickMenu("Review/Update Bankruptcy Case Activity");
		sleep(1000);
		
		
		//---Review/Update Bankruptcy Case Activity---COL-474//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Case Activity", "Pass", "Review Bankruptcy COL-474");
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN_Num);
		commonFuntions.selectDropdown("Bankruptcy Status", " Active ");
		commonFuntions.screenShot("Search Case Activity", "Pass", "Details entered COL-474");
		//bcllocators.EnterERN.sendKeys("0526421");05-26421
		commonFuntions.clickButton(" Search ");
		sleep(5000);
		commonFuntions.selectRadioInTable("04-64364", 1, 1, "");
		commonFuntions.screenShot("Select Case Activity", "Pass", "Select Case Activity COL-474");
		//sleep(4000);
		commonFuntions.clickButton("Continue ");
		
		//---View Bankruptcy Case Details/History---COL-475//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("View Bankruptcy Case Details", "Pass", "View Bankruptcy Case Details COL-475");
		commonFuntions.clickButton("Add New Activity ");
		
		//---Add/Edit Activity - Bankruptcy Case Activity---COL-402//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add_Edit Activity Bankruptcy Case Activity", "Pass", "Add_Edit Activity Bankruptcy page open COL-402");
		commonFuntions.selectRadioQuestions("1. Activity Type", "Outgoing activity");
		commonFuntions.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFuntions.selectDropdown("3. Activity Name", "Additional Due Letter");
		bcllocators.EnterRemarks.sendKeys("Additional Due Letter selected");
		commonFuntions.enterTextbox("5. Activity Date", "6/23/2023");
		commonFuntions.selectRadioQuestions("6. Is this a Response to existing activity?", "No ");
		commonFuntions.screenShot("Add_Edit Activity Bankruptcy Case Activity", "Pass", "Detailes Entered COL-402");
		commonFuntions.clickButton("Continue ");
		
		//---Add/Edit Activity Confirmation---COL-513//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add_Edit Activity Confirmation", "Pass", "Confirmation of Details COL-513");
		commonFuntions.clickButton("Submit ");
		
		//---Add/Edit Activity Confirmation---SUC-002//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add_Edit Activity Confirmation", "Pass", "Submission of Details SUC-002");
		commonFuntions.clickButton("Home ");
		commonFuntions.screenShot("Home page", "Pass", "Test Case got Pass BCL_802_05_015");
		System.out.println("Test Case Completed");
		
	}

}
