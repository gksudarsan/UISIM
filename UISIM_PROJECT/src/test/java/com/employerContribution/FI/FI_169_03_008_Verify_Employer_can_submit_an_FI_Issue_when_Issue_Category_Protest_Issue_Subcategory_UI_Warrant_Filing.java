package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIpage;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_008_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_UI_Warrant_Filing extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'UI Warrant Filing'", groups = "Regression")
	public void FI_169_03_008()throws Exception {
	test = report.createTest("FI.169.03.008 : Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'UI Warrant Filing'");

	commonStepDefinitions commonFunctions = new commonStepDefinitions();
	FraudInvestigationLocators fiLocators = new FraudInvestigationLocators(driver);
	FIpage filocators	= new FIpage(driver);
	/*
	// Query
    Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
            "SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
            "EAN");
    String eanValue = databaseEanResult.get("EAN");
    System.out.println("The EAN is " + eanValue);
    
    if ((eanValue == null) || (eanValue.isEmpty())) {
		System.out.println("ERN Value is null");
	} else {
		test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + eanValue + ".");
	}*/
    
    // --- Login ---
	commonFunctions.login(COMMON_CONSTANT.EMPLOYER_User02.toUpperCase(), COMMON_CONSTANT.EMPLOYER_PASS_User02);
	test.log(Status.PASS, "Login with Employer is successful");
   

    // -----Menu
	commonFunctions.waitForLoadingIconToDisappear();
	fiLocators.menu.click();
	commonFunctions.ScrollMenu("Secure Messaging");
	commonFunctions.clickMenu("Secure Messaging");
	commonFunctions.ScrollMenu("Write Message");
	sleep();
	commonFunctions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Write Message - Enter ERN");
	commonFunctions.clickMenu("Write Message");
	
	// --- SM-101 ---
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Successfully launched Write Message (SM-101) screen");
	commonFunctions.selectDropdown("Category", " Protest ");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.selectDropdown("Subcategory", " How do I protest a UI Warrant Filing? ");
	sleep();
	commonFunctions.screenShot("FI16903008", "Pass", "Data enetered in SM-101 screen");
	
	commonFunctions.clickOnLinkAnchorTag("click here");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.switchTab();
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Data enetered in SM-101 screen");
	
	// --- FIS-002 ---
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Successfully launched Submit Issue(FIS-002) screen");
	commonFunctions.clickButton("Continue ");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Error on blank continue for FIS-002 screen");
	
	commonFunctions.enterCurrentDate("Warrant File Date");
	commonFunctions.enterTextboxContains("Warrant Amount ($)", "1000");
	filocators.EnterRemarks.sendKeys("Testing");
	
	sleep(2000);
	commonFunctions.screenShot("FI16903008", "Pass", "Data entered in FIS-002 screen");
	
	sleep();
	commonFunctions.selectLink("Document", "Browse");
	sleep(2000);
	commonFunctions.uploadDoc("Sample.docx");
	sleep(2000);
	commonFunctions.screenShot("FI16903008", "Pass", "Document entered in FIS-002 screen");
	
	commonFunctions.clickButton("Continue ");
	
	// ---Submit issue verification---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Submit issue verification screen launched");
	commonFunctions.clickButton("Submit ");

	// ---SUC-002---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Successfully launched Issue Submission COnfirmation(SUC-002) screen");
	commonFunctions.clickButton("Home ");
	
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("FI16903008", "Pass", "Successfully passed TC FI.169.03.008");
	
	System.out.println("Pass :)");
	
	//-----Executed by palak
	
}

}
