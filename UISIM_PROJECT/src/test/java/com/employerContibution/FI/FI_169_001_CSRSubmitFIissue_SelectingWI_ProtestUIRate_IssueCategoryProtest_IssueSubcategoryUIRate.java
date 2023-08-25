package com.employerContibution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_001_CSRSubmitFIissue_SelectingWI_ProtestUIRate_IssueCategoryProtest_IssueSubcategoryUIRate extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit an FI Issue by selecting a work item 'Protest UI Rate'  (Issue Category - Protest, Issue Subcategory - 'UI Rate')", groups = "Regression")
	public void FI_169_001() throws Exception {

		test = report.createTest("FI_169_001_Verify CSR can submit an FI Issue by selecting a work item 'Protest UI Rate'  (Issue Category - Protest, Issue Subcategory - 'UI Rate')");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%';", "EAN");
        String ernNum = databaseEanResult.get("EAN");
        
        if((ernNum==null)||(ernNum.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ernNum +".");}
		
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Employer Issues");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Create Work Item");
		commonFuntions.clickMenu("Create Work Item");
		
		//---Create Work Item - Enter ERN-FIS-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Create Work Item - Enter ERN", "Pass", "Successful launch to Create WI 001");
		commonFuntions.enterTextbox("Employer Registration Number", "11-11111");
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("Create Work Item - Enter ERN", "Pass", "Invalid Employer Registration Number. No ERN matched.");
		commonFuntions.enterTextbox("Employer Registration Number", "2314");
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("Create Work Item - Enter ERN", "Pass", "Length of this response must be at least 7 characters.");
		
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		sleep();
		commonFuntions.screenShot("Create Work Item - Enter ERN", "Pass", "Valid ERN Entered");
		commonFuntions.clickButton("Continue ");
		
		
		//---Select Work Item-FIS-010---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Work Item", "Pass", "Select Work Item page launched-FIS-010");
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("Select Work Item", "Pass", "got the Error Message 'Required'-FIS-010");
		commonFuntions.selectDropdown("Select Unit", "Employer Accounts Adjustment");
		commonFuntions.selectDropdown("Select Work Item", "Protest UI Rate");
		sleep();
		commonFuntions.screenShot("Select Work Item", "Pass", "Selected the required details-FIS-010");
		commonFuntions.clickButton("Continue ");
		
		//---Protest UI Rate-FIS-003---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Protest UI Rate", "Pass", "Protest UI Rate page launched-FIS-003");
		commonFuntions.clickButton(" Add Additional Employer ");
		
		//---Search Employer-FIS-004---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search Employer", "Pass", "Search Employer page launched-FIS-004");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.clickButton(" Search ");
		sleep();
		commonFuntions.screenShot("Search Employer", "Pass", "Search details desplayed-FIS-004");
		commonFuntions.selectRadioInTable(ernNum, 1, 1, "");
		sleep();
		commonFuntions.screenShot("Search Employer", "Pass", "Selected the data from table-FIS-004");
		commonFuntions.clickButton("Continue ");
		
		//---Protest UI Rate-FIS-003---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Protest UI Rate2", "Pass", "back to Protest UI Rate-FIS-004");
		commonFuntions.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFuntions.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFuntions.screenShot("Protest UI Rate", "Pass", "Protest UI Rate Sample document upload_FIS-003");
 		sleep();
 		commonFuntions.clickButton(" Associate Documents ");
		
 		//---Search and Associate Documents-WF-101---//
 		commonFuntions.waitForLoadingIconToDisappear();
 		commonFuntions.screenShot("Search and Associate Documents", "Pass", "Search and Associate Documents launched-14-FIS-004");
 		commonFuntions.enterTextbox("ERN", ernNum);
 		commonFuntions.screenShot("Search and Associate Documents", "Pass", "Enter ERN-FIS-004");
 		commonFuntions.clickButton(" Search ");
 		sleep(7000);
 		commonFuntions.selectActionTableParameterizedId("Outgoing", 1, 1, "Search and Associate Documents", "dataTableId", "checkBox", "");
 		commonFuntions.screenShot("Search and Associate Documents", "Pass", "Selected the searched doc-FIS-004");
 		commonFuntions.clickButton("Continue ");
 		
 		//---Protest UI Rate-FIS-003---//
 		commonFuntions.waitForLoadingIconToDisappear();
 		commonFuntions.screenShot("Protest UI Rate3", "Pass", "Doc added success-FIS-003");
 		
 		commonFuntions.selectDropdown("Work Basket", "Central Assignment and Collections Module (CACM)");
		commonFuntions.selectDropdown("Source", "Correspondence/ Email/ Efax");
		commonFuntions.enterTextbox("Name", "TCS");
		commonFuntions.enterTextbox("Title", "Corporate");
		commonFuntions.enterTextbox(" Telephone Number ", "0206734455");
		commonFuntions.enterTextbox("Mailing Address", "corporate@tcs.com");
		
		commonFuntions.enterTextbox("Rate Year", "2023");
		commonFuntions.enterTextbox("Specific Calculation Protest Ingredient", "NA");
		commonFuntions.enterTextbox("Remarks/Reasons for submitting Issue (must not exceed 2000 characters)", "CSR can submit an FI Issue by selecting a work item 'Protest UI Rate' ");
		commonFuntions.selectCheckbox("Is this protest a hearing request?");
		commonFuntions.enterCurrentDate("Received Date");
		sleep();
		commonFuntions.screenShot("Protest UI Rate", "Pass", "All the details Entered-FIS-003");
		commonFuntions.clickButton("Continue ");
		
		
		//---Verification - Protest UI Rate-FIS-005---//
 		commonFuntions.waitForLoadingIconToDisappear();
 		commonFuntions.screenShot(" VerificationProtest UI Rate", "Pass", "Verification launched-FIS-005");
 		commonFuntions.clickButton("Submit ");
 		
 		
		//---Work Item Submission Confirmation-SUC-002---//
 		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Work Item Submission Confirmation", "Pass", "WI submission success-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI_169_001", "Pass", "Successfully passed TC FI_169_001");
		
	}
}