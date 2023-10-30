package com.employerContibution.EM;

import java.util.Map;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_443_001 extends TestBase {

	@Test
	public void EM_443_001_CSR() throws Exception {
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = report.createTest(
				"EM.443.001:Verify CSR is able to enter ERN and Add POA/TPR association for designation type \"All Unemployment Insurance Matters\"");
		
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_9.toUpperCase(), COMMON_CONSTANT.CSR_USER_9_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
	    commonFuntions.waitForLoadingIconToDisappear();
	    AddPage.menu.click();
		sleep();
		//step 3
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		sleep();
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		sleep();
		commonFuntions.screenShot("AccountMaintenance", "Pass", "Employer Account Maintenance");
		commonFuntions.clickMenu("Add or Remove POA/TPR Association");
		sleep(2000);
		
		//Add or Remove POA/TPR Association – Enter ERN -----SREG-430
		//step 4
		commonFuntions.screenShot("AddRemovePaoTransaction", "Pass", "Add and Remone Poa Transaction");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.errorLabel("Required");
		commonFuntions.screenShot("RequiredError", "Pass", "Add or Remove POA/TPR Association:Enter ERN");
		
		//step 5
		commonFuntions.enterTextbox("Employer Registration Number", "1111111");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		commonFuntions.screenShot("RequiredErrorERNDoesNotExist", "Pass",
				"Add or Remove POA/TPR Association:ERN Does Not Exist");

		//query to fetch ERN number from database
		//step 6
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB'",
				"EAN");
		String eanNumber = databaseResults.get("EAN");
		System.out.println("The selected ERN is: " + eanNumber);
		test.log(Status.INFO, "EAN::" + eanNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number", "5427284");
		commonFuntions.screenShot("ERN", "Pass", "Add or Remove POA TPR Association–Enter ERN");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);commonFuntions.waitForLoadingIconToDisappear();
		
		//Add or Remove POA/Third Party Representative to Employer ---- SREG-537
		//step 7
		commonFuntions.screenShot("AddorRemoveThirdPartyAssociationEmployer", "Pass",
				"Add or Remove Third Party Association Employer");
		commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		commonFuntions.clickButtonContains("Submit ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("requiredErrorMessageForAllField", "Pass", "Required Error Message For All Field");
		
		//step 8
		commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		AddPage.selectRadio_Button1.click();sleep();
		commonFuntions.forceClearText(PEOPage.dateFeild1);sleep();
		PEOPage.dateFeild1.sendKeys("01/01/2024"); sleep();
		AddPage.commentField.sendKeys("automation testing");sleep();
		commonFuntions.selectCheckbox("Additional authorization");sleep();
		commonFuntions.clickButtonContains("Submit ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		//commonFuntions.errorContent("Association Start Date cannot be in future");
		commonFuntions.screenShot("InavalidDateFormat", "Pass", "Association Start Date cannot be in future");
		sleep(2000);
		
		//step 9
		driver.navigate().refresh();
		commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		commonFuntions.forceClearText(PEOPage.dateFeild1);sleep();
		PEOPage.dateFeild1.sendKeys("09/01/2023"); sleep();
		AddPage.commentField.sendKeys("automation testing");sleep();
		commonFuntions.selectCheckbox("Additional authorization");sleep();
		commonFuntions.clickButtonContains("Submit ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		//commonFuntions.errorContent("Select one of the records to proceed.")
	    commonFuntions.screenShot("SelectOneOfTheRecordsToProceed", "Pass", "Select one of the records to proceed.");sleep();
	    AddPage.selectRadio_Button1.click();sleep();
	    
	    //Now clicking on search POA/TPR
	    //step 10
		commonFuntions.clickButtonContains(" Search POA/TPR ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SearchPOAThirdPartyRepresentative", "Pass", "Search POA Third Party Representative");
		
		//step 11
		commonFuntions.clickButtonContains("Previous ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("AddRemoveThirdPartyAssociationEmployer", "Pass", 
				"Add Remove Third Party Association to Employer");
		
		//step 12
		commonFuntions.clickButtonContains(" Search POA/TPR ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SearchPOAThirdPartyRepresentative1", "Pass",
				"Search POAThird Party Representative1");
		
		//step 13
		commonFuntions.clickButtonContains(" Search ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("LegalNameIsRequired.", "Pass", "Legal name of business is required.");
		commonFuntions.errorContent("Legal name of business is required.");
		
		//Search POA/Third Party Representative --- SREG-040
		//step 14
		Map<String, String> databaseResults_legalName = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_THIRD_PARTY_AGENT ttpa ORDER BY UPDATED_TS DESC",
				"COMPANY_NAME");
		sleep();
		String tprLegalName = databaseResults_legalName.get("COMPANY_NAME");
		System.out.println("The TPR Legal Name: " + tprLegalName);
		test.log(Status.INFO, "TPR Legal Name::" + tprLegalName);
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", tprLegalName);
		commonFuntions.screenShot("SearchPOAThirdPartyRepresentativeLeagalName", "Pass", "Search POAThird Party Representative Legal Name");
		commonFuntions.clickButtonContains(" Search ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("recordFound", "Pass", "Records Found");
		
		//step 15
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SearchPOAThirdPartyRepresentativeSelectTheRecordError", "Pass",
				"Search POAThird Party Representative:Select Record Error Appear");
		commonFuntions.errorContent("Please select a record to proceed further.");
		
		//step 16
		AddPage.selectRadio_Button.click();
		commonFuntions.screenShot("recordFoundSelected", "Pass", "Records Found Selected");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		
		//step 17
		AddPage.selectRadio_Button1.click();sleep();
		AddPage.commentField.sendKeys("automation testing");sleep();
		commonFuntions.selectCheckbox("Additional authorization");sleep();
		commonFuntions.forceClearText(PEOPage.dateFeild1);sleep();
		PEOPage.dateFeild1.sendKeys("01/01/2023"); sleep();
		commonFuntions.forceClearText(PEOPage.dateFeild2);sleep();
		commonFuntions.screenShot("Add or Remove Third Party Association to Employer", "Pass", "Add or Remove Third Party Association to Employer");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.clickButtonContains("Submit");
			sleep(3000);
		}
		catch(Exception e) {		
		}
		commonFuntions.screenShot("AddRemovePOAThirdPartyRepresentativeAssociationEmployerConfirmationSUC_002", "Pass", "Add Remove POA Third Party Representative Association to Employer Confirmation (SUC- 002");
		
		//step18
		commonFuntions.clickButtonContains("Home");
		sleep(5000);
        try{
        	loginPage.okPopUpButton.click();
    		sleep(2000);	
        }catch(Exception e){ 	
        }
		
		//Navigating to Inquiry Employer Account 
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.GeneralInquiry_UserName.toUpperCase(), COMMON_CONSTANT.GeneralInquiry_PASSWORD);
		AddPage.menu.click();sleep();
		commonFuntions.clickMenu("Inquiry");sleep();
		commonFuntions.clickMenu("Contribution Inquiry");sleep();
		commonFuntions.clickMenu("Inquiry Employer Account");sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number", "5427284");
		commonFuntions.screenShot("InquiryEmployerAccount", "Pass", "Inquiry Employer Account");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("InquiryEmployerAccountInformation", "Pass", "Inquiry Employer Account Information");
		AddPage.getERN.isDisplayed();
		AddPage.getLegalName.isDisplayed();
		
	}

}
