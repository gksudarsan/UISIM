package com.employerContribution.EOA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_001_OnlineAuth_VerifyUiOnlineServices_AccountCreationProcess_EmployerAlreadyRegUi extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Employer already registered for UI)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_01_002() throws Exception {
		
		test = report.createTest("EOA.01.001 : Online Authentication - Verify the Unemployment Insurance Online Services Account Creation process (Employer already registered for UI)");

		test.log(Status.INFO, "Script developed by Ankan Das.");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);
		
		 //FEIN
        Map<String, String> feinValue = commonFuntions.database_SelectQuerySingleColumn(
                "SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN IS NOT NULL AND EAN IS NOT NULL AND EFIN IS NOT NULL;",
                "FEIN");
        String fein = feinValue.get("FEIN");
        System.out.println(fein);
        test.log(Status.INFO, "FEIN generated is " + fein + ".");
        
        //LEGAL NAME
        Map<String, String> COMPANY_NAME_Value = commonFuntions.database_SelectQuerySingleColumn(
                "SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN = '"+ fein + "';",
                "COMPANY_NAME");
        String legalName = COMPANY_NAME_Value.get("COMPANY_NAME");
        System.out.println(legalName);
        test.log(Status.INFO, "Legal Name generated is " + legalName + ".");
        
        //EAN
        Map<String, String> EAN_Value = commonFuntions.database_SelectQuerySingleColumn(
                "SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN = '"+ fein + "';",
                "EAN");
        String ean = EAN_Value.get("EAN");
        System.out.println(ean);
        test.log(Status.INFO, "EAN generated is " + ean + ".");
        
        //EFIN
        /*Map<String, String> EFIN_Value = commonFuntions.database_SelectQuerySingleColumn(
                "SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN IS NOT NULL AND EAN IS NOT NULL AND EFIN IS NOT NULL;",
                "EFIN");
        String efin = EFIN_Value.get("EFIN");
        System.out.println(efin);
        test.log(Status.INFO, "EFIN generated is " + efin + ".");*/
        
        //TPR ID
        /*Map<String, String> TPR_ID_Value = commonFuntions.database_SelectQuerySingleColumn(
                "SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN IS NOT NULL AND EAN IS NOT NULL AND EFIN IS NOT NULL;",
                "TPR_ID");
        String tprID = TPR_ID_Value.get("TPR_ID");
        System.out.println(tprID);
        test.log(Status.INFO, "TPR ID generated is " + tprID + ".");*/
		
        // --- Login Creds ---
		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		test.log(Status.PASS, "Login with Claimant Representative is successful.");
		
		commonFuntions.clickButton("Continue ");
		sleep(2000);
	    commonFuntions.screenShot("EOA01001", "Pass", "Error on no selection of radio");
	        
		sleep(2000);
        eoaPage.employerRadio.click();
        sleep(2000);
        commonFuntions.screenShot("EOA01001", "Pass", "Selected required Employer radio");
        commonFuntions.clickButtonContains("Continue ");
        
        // --- SREG-612 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "Successful launch to UI Services Employer Account Creation(SREG-612) screen");
        commonFuntions.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", fein);
        commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", ean);
        commonFuntions.enterTextboxContains("Legal Name", legalName);
        eoaPage.quarterDropdown.click();
        sleep(2000);
        eoaPage.quarterValue_3.click();
        sleep();
        eoaPage.yearDropdown.click();
        sleep(2000);
        eoaPage.yearValue_2023.click();
        commonFuntions.enterTextboxContains("Total Remuneration $", "2");
        commonFuntions.enterTextboxContains("New York Business Express (NYBE) UI Application Number", "");
        
        sleep(2000);
        commonFuntions.screenShot("EOA01001", "Pass", "Data entered in SREG-612 screen");        
        commonFuntions.clickButton("Continue ");
        
        //test.log(Status.INFO, "If continue is clicked once, and the page remains unnavigated, the Continue button is disappearing.");
        
        // --- EOA-003 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "Successful launch to SIDES E-Response Auto-Enrollment (EOA-003) screen");
        eoaPage.selectCheckboxfis009(" Opt-in of SIDES E-Response auto-enrollment. ");
        sleep(2000);
        commonFuntions.screenShot("EOA01001", "Pass", "SIDES checkbox selected in EOA-003 page"); 
        commonFuntions.clickButtonContains("Submit ");
        
        // --- SUC-002 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "Successful launch to SIDES E-Response Auto-enrollment Confirmation (SUC-002) screen.");
        commonFuntions.clickButtonContains("Continue ");
        
        // --- EOA-002 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "Successful launch to Contact Information (EOA-002) screen.");
 
        commonFuntions.enterTextboxContains("First Name", "Dev");
        commonFuntions.enterTextboxContains("Last Name", "LN");
        commonFuntions.selectRadioQuestions("Do you want to sign up for Electronic Correspondence?","Yes");
        commonFuntions.enterTextboxContains("E-mail Address", "test2@gmail.com");
        commonFuntions.enterTextboxContains(" Contact Number ", "1234567890");
        commonFuntions.enterTextboxContains(" Cell Number ", "1234567098");
        commonFuntions.selectCheckbox("Same as Contact Number");
        sleep(2000);
        commonFuntions.screenShot("EOA01001", "Pass", "Required data entered in EOA-002 page"); 
        commonFuntions.clickButtonContains("Submit ");
        
        // --- SUC-002 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "Successful launch to UI Services Account Creation Confirmation (SUC-002) screen is displayed");
        commonFuntions.clickButtonContains("Home ");
        
        
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA01001", "Pass", "TC EOA.01.001 passed successfully.");
        
		
	}

}
