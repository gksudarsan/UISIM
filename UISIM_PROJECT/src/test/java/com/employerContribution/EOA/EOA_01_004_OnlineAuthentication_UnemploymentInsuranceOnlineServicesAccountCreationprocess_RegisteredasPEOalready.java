package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_004_OnlineAuthentication_UnemploymentInsuranceOnlineServicesAccountCreationprocess_RegisteredasPEOalready extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Registered as a PEO already)", groups = "Regression")
	public void EOA_01_004() throws Exception {

		test = report.createTest("EOA.01.004 Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Registered as a PEO already)");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoalocators = new EOAPage(driver);
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT where EMPLOYER_REGISTRATION_NUMBER LIKE '047%';", "EMPLOYER_REGISTRATION_NUMBER");
        String ernNum = databaseEanResult.get("EMPLOYER_REGISTRATION_NUMBER");
        
        if((ernNum==null)||(ernNum.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ernNum +".");}
		System.out.println("DB connected successfully and fetched ERN is: "+ ernNum +".");
        
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		
		test.log(Status.PASS, "Login with Apeal User is successful");
		
		commonFuntions.screenShot("UI Services Account Creation", "Pass", "UI Services Account Creation Popup is displayed");sleep();
		eoalocators.peoRegisteredRadio.click();sleep();
		commonFuntions.screenShot("UI Services Account Creation", "Pass", "Selected the PEO radio button");
		commonFuntions.clickButton("Continue ");
		
		//UI Services PEO Account Creation-SREG-612---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UI Services PEO Account Creation", "Pass", "UI Services PEO Account Creation page launched-SREG-612");
		commonFuntions.enterTextbox("Employer Registration Number (ERN)", ernNum);
		commonFuntions.enterTextbox("Legal Name", "Ut");sleep();
		commonFuntions.screenShot("UI Services PEO Account Creation", "Pass", "UISAC details Filled ");
		commonFuntions.clickButton("Continue ");
		
		//Contact Information-EOA-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Contact Information-EOA-002", "Pass", "Contact Information page launched-EOA-002");
		
		commonFuntions.enterTextbox("First Name", "Rajesh");
		commonFuntions.enterTextbox("Middle Initial", "Kumar");
		commonFuntions.enterTextbox("Last Name", "Khanna");
		commonFuntions.screenShot("Contact Information-EOA-002", "Pass", "Contact Information Details-1");
		commonFuntions.selectRadioQuestions("Do you want to sign up for Electronic Correspondence?", "Yes ");
		commonFuntions.enterTextbox("E-mail Address", "Rajesh.Khanna@gmail.com");
		commonFuntions.enterTextboxContains("Contact Number", "9765378456");
		commonFuntions.enterTextboxContains("Cell Number", "9766678460");
		commonFuntions.selectCheckbox("Same as Contact Number");
		commonFuntions.screenShot("Contact Information-EOA-002", "Pass", "Contact Information Details-2");
		commonFuntions.clickButton("Submit ");

		//---UI Services Account Creation Confirmation-SUC-002---//
 		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UI Services Account Creation Confirmation", "Pass", "UISAC confirmation success-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("EOA_01_004", "Pass", "Successfully passed FirstPart Batch-Remaining TC EOA_01_004");
		
	}
} 