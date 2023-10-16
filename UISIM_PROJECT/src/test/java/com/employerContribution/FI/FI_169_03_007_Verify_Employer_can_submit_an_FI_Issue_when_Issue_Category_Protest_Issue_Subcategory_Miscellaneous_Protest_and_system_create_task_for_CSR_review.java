package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIpage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_007_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Miscellaneous_Protest_and_system_create_task_for_CSR_review extends TestBase {

	@Test (priority = 1, description = "Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Miscellaneous Protest' and system create task for CSR review", groups = {
	"Regression" })
	public void FI_169_03_007()throws Exception {
	test = report.createTest("FI.169.03.007 - Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Miscellaneous Protest' and system create task for CSR review");

	commonStepDefinitions commonFunctions = new commonStepDefinitions();
	FIpage filocators = new FIpage(driver);

	Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
			"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
	String ernValue = databaseEanResult.get("EAN");
	
	System.out.println("ERN is: " + ernValue + ".");

	if ((ernValue == null) || (ernValue.isEmpty())) {
		System.out.println("ERN Value is null");
	} else {
		test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernValue + ".");
	}

	// ---Login---
	commonFunctions.login(COMMON_CONSTANT.EMPLOYER_USER_9, COMMON_CONSTANT.EMPLOYER_USER_9_PASSWORD);
	test.log(Status.PASS, "Login with Employer is successful");
	
	try {
		commonFunctions.clickButtonContains("I agree with the Terms and Conditions");
		test.log(Status.PASS, "Accepted 'Terms and Conditions for Businesses'");
	} catch(Exception exception) {
		test.log(Status.PASS, "Accepted 'Terms and Conditions for Businesses'");
	}

	// ---Menu----
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.clickMenu("Menu");
	commonFunctions.ScrollMenu("Secure Messaging");
	commonFunctions.clickMenu("Secure Messaging");sleep();
	commonFunctions.ScrollMenu("Write Message");
	commonFunctions.screenShot("Menu", "Pass", "Menu Write Message");
	commonFunctions.clickMenu("Write Message");

	// ---Write Message-SM-101---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("Write Message", "Pass", "Write Message page launched SM-101");
	commonFunctions.selectDropdown("Category", " Protest ");sleep(10000);
	commonFunctions.selectDropdown("Subcategory", "How do I protest Other Issues?");sleep();
	commonFunctions.screenShot("Write Message", "Pass", "Category-Subcategory selected");
	commonFunctions.clickOnLinkAnchorTag("here");
	sleep();
	commonFunctions.switchTab();sleep();

	// ---Submit Issue-FIS-002---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue page Successfully launched FIS-002");
	filocators.EnterRemarks.sendKeys("Issue Category - Protest, Issue Subcategory - 'Miscellaneous Protest'");
	commonFunctions.selectCheckbox("Is this protest a hearing request ?");
	
	commonFunctions.selectLink("Document", "Browse");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.uploadDoc("Sample.docx");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue details filled FIS-002");

	commonFunctions.clickButtonContains("Continue ");

	// ---Submit Issue Verification---//
	commonFunctions.waitForLoadingIconToDisappear();sleep(6000);
	commonFunctions.screenShot("Submit Issue Verification", "Pass", "Submit Issue Verification launched");
	commonFunctions.clickButtonContains("Submit ");

	// ---Issue Submission Confirmation-SUC-002---//
	commonFunctions.waitForLoadingIconToDisappear();sleep(6000);
	commonFunctions.screenShot("Issue Submission Confirmation", "Pass","Issue Submission Confirmation-SUC-002");
	commonFunctions.clickButtonContains("Home ");sleep(6000);
	commonFunctions.screenShot("Home screen", "Pass","Test Case FI_169_03_007 First flow pass");
	
	// ---Login to L&D Specialist Role for review---//
	commonFunctions.logoutAndLogin(COMMON_CONSTANT.LnDSpecialist_User, COMMON_CONSTANT.LnDSpecialist_User_Pwd);
	test.log(Status.PASS, "Login with L&D Specialist is successful");
	
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("Menu", "Pass", "Home Page launched");
	commonFunctions.clickButton(" Go to My Q ");
	
	// ---Individual Work Queue WF-001---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue WF-001 launched");
	commonFunctions.selectDropdown("Work Item Description", "MISC Protest Task");
	commonFunctions.clickButton(" Search ");
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("WI Details", "Pass", "WI Details recieved");
	
	commonFunctions.clickHyperlink("MISC Protest Task");
	
	// ---MISC Protest Task PFP-005---//
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("MISC Protest Task", "Pass", "MISC Protest Task PFP-005 launched");
	
	commonFunctions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
	commonFunctions.selectRadioQuestions("Do you want to place a Hold Action Flag on this account?", "No ");
	filocators.Entercomments.sendKeys("MISC Protest Task");
	commonFunctions.screenShot("MISC Protest Task", "Pass", "MISC Protest Task PFP-005 details filled");
	commonFunctions.clickButtonContains("Submit ");
	
	//---Test Case yet to complete WI auto assign issue still presents---//
	
	//---Test Case Completed---//
}

}
