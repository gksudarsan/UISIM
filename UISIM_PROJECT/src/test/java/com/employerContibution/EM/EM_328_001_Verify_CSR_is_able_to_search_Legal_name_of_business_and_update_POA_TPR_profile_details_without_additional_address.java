package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_600;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_328_001_Verify_CSR_is_able_to_search_Legal_name_of_business_and_update_POA_TPR_profile_details_without_additional_address
		extends TestBase {

	@Test
	public void Tc_EM_328_001() throws Exception {

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_600 sreg600 = new SREG_600(driver);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"Verify_CSR_is_able_to_search_Legal_name_of_business_and_update_POA_TPR_profile_details_without_additional_address");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 3 -- Navigate to   Home page > Account Maintenance > Maintain Reporting Service Details");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Reporting Service Details");
		commonFuntions.clickMenu("Maintain Reporting Service Details");
		Thread.sleep(2000);
		commonFuntions.screenShot("Account Maintenance", "Pass", "Add Power of Attorney/Third Party Representative");

		test.info("Step: 4 --Do not  Enter/Select the information in Search POA/TPR  Details-(SREG-041) screen");
		commonFuntions.clickMenu(" Search ");
		Thread.sleep(2000);
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("Maintain Reporting Service Details", "Pass", "Error : Required");

		test.info("Step: 5 --Enter/Select the information in Search POA/TPR  Details-(SREG-041) screen");
		Map<String, String> databaseResults = PEOPage
				.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea;");
		String feinValue = databaseResults.get("Fein");
		String ernValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + ernValue);
		System.out.println("legalName " + legalName);

		commonFuntions.enterTextboxContains("POA/TPR Legal Name", legalName);
		// commonFuntions.enterTextboxContains(" Federal Identification Number (FEIN) ",
		// feinValue);
		commonFuntions.enterTextboxContains("TPR ID", "yu778999");
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.errorLabel(" First character should be A/a followed by numeric characters.");
		commonFuntions.screenShot("Maintain Reporting Service Details", "Pass", "Error : TPR ID Error");

		test.info("Step: 6 --Enter/Select the information in  \"Search POA/TPR  Details-(SREG-041)\" screen");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg041.searchButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());
		commonFuntions.screenShot("Maintain Reporting Service Details", "Pass", "Buttons is visible");

		test.info("Step: 7 -- Do not Select any record in the table on \"Search POA/TPR  Details-(SREG-041)\" screen");
		commonFuntions.forceClearText(sreg041.legalnameField);
		commonFuntions.forceClearText(sreg041.tprID);
		commonFuntions.enterTextboxContains("POA/TPR Legal Name", "ABCD");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		Assert.assertTrue(sreg041.legalnameError.isDisplayed());
		commonFuntions.screenShot("Maintain Reporting Service Details", "Pass", "Error : Legal Name Error");

		test.info("Step: 8 -- Select any record in the table on \"Search POA/TPR  Details-(SREG-041)\" screen");
		commonFuntions.forceClearText(sreg041.legalnameField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("POA/TPR Legal Name", "CSRTestone");
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(1000);
		sreg084.selectradioBtn1.click();
		Thread.sleep(1000);
		sreg084.continueButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "SREG-600 page is visible");

		test.info(
				"Step: 9 --Enter/Select the details on  \"POA/Third Party Representative Details -(SREG-600)\" screen");
		//verified 13 step
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(1000);
		commonFuntions.errorLabel(" Required ");
		//
		Thread.sleep(2000);
		
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As", "C/o Test");
		commonFuntions.enterTextboxContains(" Contact Number ", "7999799564");
		commonFuntions.selectDropdown("Source", " IA602 ");
		commonFuntions.selectDropdown("Source Type", " Correspondence ");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu(" Trade Name cannot contain ATTN, DBA, C/0, or c/o");
		Thread.sleep(1000);
		commonFuntions.errorLabel(" Trade Name cannot contain ATTN, DBA, C/0, or c/o");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Error: Trade name");

		test.info(
				"Step: 10&11 -- PTIN OR FEIN is required");
		commonFuntions.forceClearText(sreg600.tradeNameField);
		Thread.sleep(1000);
		String ptinValue = sreg600.ptinField.getText(); //P67676767
		commonFuntions.forceClearText(sreg600.ptinField);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Either PTIN OR FEIN is required.");
		Thread.sleep(1000);
		Assert.assertTrue(sreg600.ptinErrorMessage.isDisplayed());
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Error: PTIN OR FEIN is required");
		
		test.info(
				"Step: 12 -- Attention/Careof cannot contain ATTN, DBA, C/0, c/o");
		commonFuntions.enterTextboxContains("PTIN", "P67676767");
		commonFuntions.enterTextboxContains("Mailing Address ", "Attn");
		commonFuntions.enterTextboxContains("Care Of", "test");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Attention/Careof cannot contain ATTN, DBA, C/0, c/o");
		Thread.sleep(1000);
		Assert.assertTrue(sreg600.careOfErrorMessage.isDisplayed());
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Error: Attention/Careof cannot contain ATTN, DBA, C/0, c/o");
		
		commonFuntions.forceClearText(sreg600.mailingAddField);
		commonFuntions.enterTextboxContains("Mailing Address ", "test");
		
		test.info(
				"Step: 13 -- All mandatory fields required");
		//verified in 9th step
		
		test.info(
				"Step: 14 -- Address Line error");
		commonFuntions.forceClearText(sreg600.addressLineField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test%554");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu("only Alphabets, Numbers .-',!#_:;&/ allowed");
		//Thread.sleep(1000);
		//commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		Assert.assertTrue(sreg600.alphabetsErrorMessage.isDisplayed());
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Error: only Alphabets, Numbers .-',!#_:;&/ allowed");
		commonFuntions.forceClearText(sreg600.addressLineField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test");
		
		test.info(
				"Step: 15 -- City contains invalid character");
		commonFuntions.forceClearText(sreg600.CityField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("City ", "Test$%");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu("only Alphabets, Numbers .-',!#_:;&/ allowed");
		//Thread.sleep(1000);
		//commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		Assert.assertTrue(sreg600.alphabetsErrorMessage.isDisplayed());
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Error: only Alphabets, Numbers .-',!#_:;&/ allowed");
		
		commonFuntions.forceClearText(sreg600.CityField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("City ", "Test");
		
		test.info(
				"Step: 16 -- Zip code must have 5 numbers only");
		String zipCodeValue = sreg600.zipCodeField.getText(); // A1B 2C3
		
		commonFuntions.forceClearText(sreg600.zipCodeField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "12");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu("Zip Code must have 5 numbers only.");
		//Thread.sleep(1000);
		commonFuntions.errorLabel("Zip Code must have 5 numbers only.");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Zip Code must have 5 numbers only.");
		
		test.info(
				"Step: 17&18 -- Zip code is invalid");
		commonFuntions.selectDropdown("Country", "United States");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("State", " New York ");
		Thread.sleep(1000);
		commonFuntions.forceClearText(sreg600.zipCodeField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "00000");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Zip Code is invalid.");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Zip Code is invalid.");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Zip Code is invalid.");
		
		//
		commonFuntions.forceClearText(sreg600.zipCodeField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		//
		
		test.info(
				"Step: 19 -- Phone number must be numeric and of 10 digits");
		commonFuntions.forceClearText(sreg600.contactNoField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains(" Contact Number ", "314777");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu("Phone Number should be 10 digits minimum");
		//Thread.sleep(1000);
		commonFuntions.errorLabel("Phone Number should be 10 digits minimum");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", "Phone Number should be 10 digits minimum");
		
		test.info(
				"Step: 20 -- Contact No is Invalid");
		commonFuntions.forceClearText(sreg600.contactNoField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains(" Contact Number ", "9999999999");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Please enter valid number");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Please enter valid number");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Please enter valid number");
		
		//
		commonFuntions.forceClearText(sreg600.contactNoField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains(" Contact Number ", "7999799564");
		//
		
		test.info(
				"Step: 21 -- Email Address contains  invalid character(s).");
		commonFuntions.forceClearText(sreg600.emailAddField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("E-mail Address", "naresh^%$");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Email Address entered is invalid.Correct format of email address is a@b.com.");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Email Address entered is invalid.Correct format of email address is a@b.com.");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Email Address entered is invalid.Correct format of email address is a@b.com.");
		
		//
		commonFuntions.forceClearText(sreg600.emailAddField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("E-mail Address", "Test2@gmail.com");
		//
		
		test.info(
				"Step: 22 -- Fax Number is Invalid");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains(" Fax Number ", "0000000000");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Please enter valid number");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Please enter valid number");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Please enter valid number");
		
		//
		commonFuntions.forceClearText(sreg600.FaxNoField);
		Thread.sleep(1000);
		//
		
		test.info(
				"Step: 23 -- TPR No. is Invalid");
		String tprIdValue = sreg600.tprIdField.getText(); //A000000001
		
		commonFuntions.forceClearText(sreg600.tprIdField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("TPR ID", "U000000004");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" First character should be A/a followed by numeric characters.");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" First character should be A/a followed by numeric characters.");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " First character should be A/a followed by numeric characters.");
		
		//
		commonFuntions.forceClearText(sreg600.tprIdField);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("TPR ID", "A000000001");
		//
		
		test.info(
				"Step: 24 -- Effective Date of Closure is required.");
		commonFuntions.selectRadioQuestions("Are you requesting to terminate your Third Party Representation which will disassociate all of your clients?","Yes");
		Thread.sleep(1000);
		//commonFuntions.enterTextboxContains("Comments", "ok Test");
		sreg600.commentAreaField.sendKeys("ok Test");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		
		//commonFuntions.ScrollMenu(" Required ");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Required ");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Required ");
		
		test.info(
				"Step: 25 -- Cannot be past date");
		commonFuntions.enterPastDate("If yes, please enter Effective Date of disassociation (MM/DD/YYYY)", 7);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		
		//commonFuntions.ScrollMenu(" Cannot be a past date");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Cannot be a past date");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Cannot be a past date");
		
		
		test.info(
				"Step: 26 -- Comments required field");
		commonFuntions.forceClearText(sreg600.commentAreaField);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		
		//commonFuntions.ScrollMenu(" Required");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Required");
		
		commonFuntions.selectRadioQuestions("Are you requesting to terminate your Third Party Representation which will disassociate all of your clients?","No");
		
		test.info(
				"Step: 27 -- Submitter comments exceeds 256 characters.");
		//N/A as the comment field size is not more than 256 characters on ths screen
		
		test.info(
				"Step: 28 -- Cannot be past date (SIDES BROKER)");
		commonFuntions.selectRadioQuestions("SIDES BROKER Designation","Yes");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("SIDES BROKER ID", "66565565");
		
		commonFuntions.enterFutureDate("SIDES BROKER Start Date", 7); //enterFutureDate
		commonFuntions.enterPastDate("SIDES BROKER End Date", 7); //enterPastDate
		Thread.sleep(1000);
		
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Must be Current Date. Cannot be a past date");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Must be Current Date. Cannot be a past date");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Must be Current Date. Cannot be a past date");
		
		//
		commonFuntions.forceClearText(sreg600.sidesBrokerStartDateField);
		Thread.sleep(1000);
		commonFuntions.forceClearText(sreg600.sidesBrokerEndDateField);
		Thread.sleep(1000);
		//
		
		test.info(
				"Step: 29 -- Cannot be future date (SIDES BROKER)");
		commonFuntions.enterFutureDate("SIDES BROKER Start Date", 7); //enterFutureDate
		commonFuntions.enterPastDate("SIDES BROKER End Date", 14); //enterPastDate
		Thread.sleep(1000);
		
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		//commonFuntions.ScrollMenu(" Must be Current Date. Cannot be a future date");
		//Thread.sleep(1000);
		commonFuntions.errorLabel(" Must be Current Date. Cannot be a future date");
		commonFuntions.screenShot("POA/Third Party Representative Details", "Pass", " Must be Current Date. Cannot be a future date");
		
		//
		/*commonFuntions.forceClearText(sreg600.sidesBrokerStartDateField);
		Thread.sleep(1000);
		commonFuntions.forceClearText(sreg600.sidesBrokerEndDateField);
		Thread.sleep(1000);
		*/
		//
		
		test.info(
				"Step: 30&31&32 -- Submit and navigate to next page");
		commonFuntions.selectRadioQuestions("SIDES BROKER Designation","No");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(4000);
		
		commonFuntions.screenShot("POA/Third Party Representative Confirmation", "Pass", "SUC-002");
		Assert.assertTrue(suc_002.homeButton.isDisplayed());
		suc_002.homeButton.click();
		
		
	}

}