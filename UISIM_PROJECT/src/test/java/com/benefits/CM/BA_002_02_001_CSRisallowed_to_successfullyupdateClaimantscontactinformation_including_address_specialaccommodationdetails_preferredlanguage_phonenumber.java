package com.benefits.CM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.B_BAD_Page;
import com.ui.pages.B_CM_Page;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BA_002_02_001_CSRisallowed_to_successfullyupdateClaimantscontactinformation_including_address_specialaccommodationdetails_preferredlanguage_phonenumber
		extends TestBase {

	@Test(priority = 1, description = "Verify CSR is allowed to successfully update Claimant's contact information including address, special accommodation details, preferred language and phone number", groups = {
			"Regression" })
	public void BA_002_02_001() throws Exception {

		test = report.createTest(
				"BA-002-02-001 - Verify CSR is allowed to successfully update Claimant's contact information including address, special accommodation details, preferred language and phone number");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		B_BAD_Page Badlocators = new B_BAD_Page(driver);
		//String CLAIMANT_IDENTIFIER_value ="2348450947"; 
		
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn("SELECT c2.* FROM T_claimant c1, T_claim c2\r\n"
				+ "WHERE c1.CLAIMANT_ID = c2.CLAIMANT_ID\r\n"
				+ "AND c2.STATUS = 'ACTV'\r\n"
				+ "AND c2.BYE_DATE > CURRENT_DATE\r\n"
				+ "AND c2.EFFECTIVE_DATE < CURRENT_DATE\r\n"
				+ "ORDER BY CLAIMANT_ID DESC LIMIT 200", "CLAIMANT_ID");
		String CLAIMANT_ID_value = databaseEanResult.get("CLAIMANT_ID");
		System.out.println("CLAIMANT_ID is: " + CLAIMANT_ID_value + ".");
		
		Map<String, String> databaseEanResult1 = commonFunctions.database_SelectQuerySingleColumn("SELECT * FROM T_CLAIMANT WHERE CLAIMANT_ID= " + CLAIMANT_ID_value + ";", "CLAIMANT_IDENTIFIER");
		String CLAIMANT_IDENTIFIER_value = databaseEanResult1.get("CLAIMANT_IDENTIFIER");
		System.out.println("CLAIMANT_IDENTIFIER is: " + CLAIMANT_IDENTIFIER_value + ".");

		if ((CLAIMANT_IDENTIFIER_value == null) || (CLAIMANT_IDENTIFIER_value.isEmpty())) {
			System.out.println("CLAIMANT_IDENTIFIER Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched CLAIMANT_IDENTIFIER is: " + CLAIMANT_IDENTIFIER_value + ".");
		}

		// ---Login---
		commonFunctions.benefitsLogin(COMMON_CONSTANT.BASIC_BENEFITS_ACCESS, COMMON_CONSTANT.BASIC_BENEFITS_ACCESS_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits access is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");
		commonFunctions.clickMenu("Benefit Maintenance");
		commonFunctions.clickMenu("Update Contact Information");
		commonFunctions.screenShot("Menu", "Pass", " Open Contact Details page");
		commonFunctions.clickMenu("Contact Details");
		
		// ---Claimant User Search CFM-001----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Claimant User Search", "Pass", "Claimant User Search launched CFM-001");
		
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.screenShot("Claimant User Search", "Pass", "search criteria is required CFM-001");
				
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.enterTextbox("First Name", ",");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.screenShot("Claimant User Search", "Pass", "Invalid first name, Only alpha and hyphen are allowed.");
		
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.enterTextbox("First Name", "qwertyuiop asdfghjkl zxcvbnmhgf");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.screenShot("Claimant User Search", "Pass", " The response is too long. The length of this response must not exceed 30 characters.");
		
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.screenShot("Claimant User Search", "Pass", "CLAIMANT_IDENTIFIER record found");
		
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.screenShot("Claimant User Search", "Pass", "To continue, you must choose one of the Search results and select the 'Continue' button.");
		
		Badlocators.select_Claimant.click();
		commonFunctions.screenShot("Claimant User Search", "Pass", "CLAIMANT_IDENTIFIER record selected");
		commonFunctions.clickButtonContains("Continue ");
		
		// ---Update Contact Information CFM-002----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Update Contact Information", "Pass", "Update Contact Information page launched CFM-002");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.screenShot("Update Contact Information", "Pass", "Recieved Error as Required");
		
		commonFunctions.enterTextbox("Date of Birth", "12/31/1899");
		commonFunctions.screenShot("Update Contact Information", "Pass", " Minimum date should be Oct 18, 1923");
		commonFunctions.enterTextbox("Date of Birth", "31/12/1899");
		commonFunctions.screenShot("Update Contact Information", "Pass", "Please enter a valid date in MM/DD/YYYY format");
		commonFunctions.enterTextbox("Date of Birth", "10/24/1950");
		
		// Step 13 - Enter more than one single capital letters - unable to write more than one letter in the field. 
		
		commonFunctions.enterTextbox("First Name", "12.-");
		commonFunctions.enterTextbox("Last Name", "34.-");
		commonFunctions.screenShot("Update Contact Information", "Pass", "F name and L name Validation");
		driver.navigate().refresh();
		
		commonFunctions.enterTextbox(" Cell Phone Number ", "1234");
		commonFunctions.enterTextbox(" Other Phone Number ", "123456789");
		commonFunctions.screenShot("Update Contact Information", "Pass", "Phone number Validation");
		driver.navigate().refresh();
		
		//Step 16 - Not working for different residential address error message
		
		commonFunctions.enterTextbox("Date of Birth", "05/25/2023");
		commonFunctions.screenShot("Update Contact Information", "Pass", "Maximum date should be Oct 18, 2021");
		
		commonFunctions.enterTextbox("Date of Birth", "10/17/1923");
		commonFunctions.screenShot("Update Contact Information", "Pass", " Minimum date should be Oct 18, 1923");
		
		driver.navigate().refresh();
		// Start filling the Form
		
		commonFunctions.selectDropdown("Gender", "Male");
		
		//Change Address
		commonFunctions.enterTextbox("Address Line 1 ", "15 NORTH PINEKNOLL RD");
		commonFunctions.enterTextbox("Address Line 2 ", "ASTORIA");
		commonFunctions.enterTextbox("City ", "Alabama");
		
		commonFunctions.selectDropdown("State", "Alabama");
		commonFunctions.enterTextbox("Zip Code", "13306");
		
		Badlocators.differentFromMailing.click();
		Badlocators.sameAsMailingAdd.click();
		
		commonFunctions.selectCheckbox("If you are a victim of domestic violence, human trafficking or sexual assault and need to keep your address confidential, check this box");
		commonFunctions.enterTextbox(" Cell Phone Number ", "1234567890");
		Badlocators.selectYesForCellPhone.click();
		
		commonFunctions.enterTextbox(" Other Phone Number ", "9087654321");
		Badlocators.selectYesForOtherPhone.click();
		
		//Returned mail, paper forms and manually generate weekly report - no details on page
		
		commonFunctions.selectDropdown("Do you require any accessibility or special accommodation?", "Other");
		commonFunctions.enterTextbox("Special Accommodation Description (if selected Other to 23.)", "SpecialOne");
		
		commonFunctions.selectDropdown("What is your preferred language ?", "Francais");
		
		//Do you want to request interpreter - not pre-populated
		//I would like to repay my over payment by off setting my unemployment insurance benefits at 100%. - not pre-populated, there are two values in dropdown
		//Identify theft investigation and claimant impacted option not there on page.
		
		Badlocators.selectYesForInterpreter.click();
		commonFunctions.selectDropdown("I would like to repay my overpayment by offsetting my unemployment insurance benefits at 100%.", "Deduction is 100 Percentage");
		
		commonFunctions.enterTextbox("Secret Phrase", "Abc");
		commonFunctions.enterTextbox("Confirm Secret Phrase", "Abc");
		commonFunctions.enterTextbox("Secret Phrase Hint", "Abc");
		
		commonFunctions.clickButtonContains("Submit ");
		
		// ---Update Contact Information CFM-002----
		commonFunctions.waitForLoadingIconToDisappear();
		
		
		
		//---Test Case Not Completed BLocked(involve batch execution dependency also )---//
	}

}
