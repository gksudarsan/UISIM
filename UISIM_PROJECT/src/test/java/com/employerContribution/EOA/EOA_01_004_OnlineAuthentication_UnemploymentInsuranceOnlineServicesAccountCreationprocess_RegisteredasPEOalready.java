package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_004_OnlineAuthentication_UnemploymentInsuranceOnlineServicesAccountCreationprocess_RegisteredasPEOalready extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Registered as a PEO already)", groups = "Regression")
	public void EOA_01_004() throws Exception {

		test = report.createTest("EOA.01.004 Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Registered as a PEO already)");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_THIRD_PARTY_AGENT;", "FEIN");
        String feinNum = databaseEanResult.get("FEIN");
        
        if((feinNum==null)||(feinNum.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ feinNum +".");}
		System.out.println("DB connected successfully and fetched ERN is: "+ feinNum +".");
        
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.clickMenu("Employer Issues");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Create Work Item");
		commonFuntions.clickMenu("Create Work Item");
		
		

		//---Work Item Submission Confirmation-SUC-002---//
 		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Work Item Submission Confirmation", "Pass", "WI submission success-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("EOA_01_004", "Pass", "Successfully passed TC EOA_01_004");
		
	}
}