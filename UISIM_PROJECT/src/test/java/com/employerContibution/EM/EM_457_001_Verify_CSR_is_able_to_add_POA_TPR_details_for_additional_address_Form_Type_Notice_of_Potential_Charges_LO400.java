package com.employerContibution.EM;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;

import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.locators.employerManagementLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_457_001_Verify_CSR_is_able_to_add_POA_TPR_details_for_additional_address_Form_Type_Notice_of_Potential_Charges_LO400
		extends TestBase {

	@Test
	public void Tc_EM_457_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();

		test = report.createTest(
				"Verify_CSR_is_able_to_add_POA_TPR_details_for_additional_address_Form_Type_Notice_of_Potential_Charges");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info(
				"Step: 3 -- Navigate to Home page > Account Maintenance > Add Power of Attorney/Third Party Representative");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Add Power of Attorney/Third Party Representative");
		commonFuntions.clickMenu("Add Power of Attorney/Third Party Representative");
		Thread.sleep(2000);
		commonFuntions.screenShot("Account Maintenance", "Pass", "Add Power of Attorney/Third Party Representative");

		test.info(
				"Step: 4 -- Do not enter/select details on \"Add Power of Attorney/Third Party Representative - (SREG-084)\" screen ");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(4000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Required Error - Add Power of Attorney/Third Party Representative");
		commonFuntions.errorLabel(" Required");
		sleep(2000);

		test.info(
				"Step: 5--enter/select details on \\\"Add Power of Attorney/Third Party Representative - (SREG-084)\\\" screen ");
		commonFuntions.enterTextboxContains("POA/TPR Name", "CSRTestone");
		commonFuntions.enterTextboxContains("Address Line 1", "Test1");
		commonFuntions.enterTextboxContains("E-Mail Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains("First Name", "testF");
		commonFuntions.enterTextboxContains("Last Name", "testL");
		//
		commonFuntions.enterTextboxContains("City", "test%%%%###");
		commonFuntions.enterTextboxContains("Zip Code", "6666");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(4000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Required Error - City & Zip Code");

		Assert.assertTrue(sreg084.cityErrorMsg.getText().contains("only Alphabets, Numbers .-',!#_:;&/ allowed"));
		Assert.assertTrue(sreg084.zipCodeErrorMsg.getText().contains("Zip Code must have 5 numbers only."));

		test.info(
				"Step: 6--Enter/select details on \"Add Power of Attorney/Third Party Representative - (SREG-084)\" screen");
		//
		driver.navigate().refresh();
		Thread.sleep(5000);
		//
		commonFuntions.enterTextboxContains("POA/TPR Name", "CSRTestone");
		String Ptin = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 8);
		commonFuntions.enterTextboxContains("PTIN", "p"+Ptin);
		//
		commonFuntions.enterTextboxContains("Address Line 1", "Test1");
		commonFuntions.enterTextboxContains("City", "testtest");
		commonFuntions.selectDropdown("Country", " Canada ");
		commonFuntions.selectDropdown("State", " Alberta ");
		commonFuntions.enterTextboxContains("Zip Code", "678486556");
		commonFuntions.enterTextboxContains("E-Mail Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains("First Name", "testF");
		commonFuntions.enterTextboxContains("Last Name", "testL");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Required Error - Valid Canadian Postal Code");
		Assert.assertTrue(sreg084.PostalCodeErrorMsg.getText()
				.contains("Zip Code must be a valid Canadian Postal Code in the form of A1B 2C3."));

		test.info("Step: 7-- Ability to upload document");
		sreg084.browseLink.click();
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Upload Document Section");
		Assert.assertTrue(sreg084.uploadDocSec.getText().contains("Uploaded Documents"));

		test.info(
				"Step: 8--Click \"Cancel\" button on  \"Add Power of Attorney/Third Party Representative - (SREG-084)\" screen");
		commonFuntions.clickButtonContains("Cancel");
		Thread.sleep(5000);

		test.info(
				"Step: 9&10&11--Navigate again to \"Add Power of Attorney/Third Party Representative - (SREG-084)\" screen");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Add Power of Attorney/Third Party Representative");
		commonFuntions.clickMenu("Add Power of Attorney/Third Party Representative");
		Thread.sleep(2000);

		test.info("Step: 12--Help/ Previous / Continue button is available");
		commonFuntions.enterTextboxContains("POA/TPR Name", "CSRTestone");
		//
		String Ptin1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 8);
		commonFuntions.enterTextboxContains("PTIN", "p"+Ptin1);
		//
		commonFuntions.enterTextboxContains("Address Line 1", "Test1");
		commonFuntions.enterTextboxContains("E-Mail Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains("First Name", "testF");
		commonFuntions.enterTextboxContains("Last Name", "testL");
		//
		commonFuntions.enterTextboxContains("City", "testcity");
		commonFuntions.selectDropdown("Country", " Canada ");
		commonFuntions.selectDropdown("State", " Alberta ");
		commonFuntions.enterTextboxContains("Zip Code", "A1B 2C3"); // A1B 2C3
		commonFuntions.ScrollMenu(" Add Additional Address ");
		Thread.sleep(1000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass", "Add Additional Address");
		sreg084.addAddtionalAddress.click();
		Thread.sleep(2000);
		// Verify Help/ Previous & Continue buttons
		commonFuntions.screenShot("Add Additional Address", "Pass", "Verify Help/ Previous & Continue buttons");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg084.previousButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());

		test.info("Step: 13--Click \"Previous\" button on \"Add Additional Addresses (SREG-085)\" screen");
		commonFuntions.clickButtonContains("Previous");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass", "SREG-084 Page display");

		test.info(
				"Step: 14--Enter/select details on \"Add Power of Attorney/Third Party Representative - (SREG-084)\" screen");
		commonFuntions.ScrollMenu(" Add Additional Address ");
		sreg084.setUpPOARadioBtn.click();
		Thread.sleep(1000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass", "Add Additional Address");
		sreg084.addAddtionalAddress.click();
		Thread.sleep(2000);

		test.info("Step: 15-- SREG-085 All required fields");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("Add Additional Address", "Pass", "verify-Error/ Add Additional Address");
		commonFuntions.errorLabel(" Required");

		test.info("Step: 16-- SREG-085 -- Zip code must have five numbers only");
		commonFuntions.selectDropdown("Form Type", " Notice of experience Ratings (IA96) ");
		commonFuntions.enterTextboxContains("First Name", "testF");
		commonFuntions.enterTextboxContains("Last Name", "testL");
		commonFuntions.enterTextboxContains(" Contact Number ", "7436365666");
		commonFuntions.enterTextboxContains("Address Line 1", "Test1");
		commonFuntions.enterTextboxContains("City", "city");
		commonFuntions.enterTextboxContains("Zip Code", "7576");
		commonFuntions.selectDropdown("Country", "United States");
		commonFuntions.selectDropdown("State", " Alaska ");
		
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Required Error -  Zip Code");
		Assert.assertTrue(sreg084.zipCodeErrorMsg.getText().contains("Zip Code must have 5 numbers only."));

		test.info("Step: 17-- SREG-085 -- City Contains an invalid character");
		commonFuntions.forceClearText(sreg084.cityField);
		commonFuntions.clearTextboxContains("City");
		commonFuntions.enterTextboxContains("City", "test%%%%###");
		commonFuntions.forceClearText(sreg084.zipCodeField);
		commonFuntions.enterTextboxContains("Zip Code", "75768");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Required Error -  City Field Error");
		Assert.assertTrue(sreg084.cityErrorMsg.getText().contains("only Alphabets, Numbers .-',!#_:;&/ allowed"));

		test.info("Step: 18-- Navigating from SREG-085 to SREG-084 page by clicking on continue buton");
		commonFuntions.forceClearText(sreg084.cityField);
		commonFuntions.enterTextboxContains("City", "city");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"SREG-084 page is visible");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg084.cancelButton.isDisplayed());
		Assert.assertTrue(sreg084.submitButton.isDisplayed());

		test.info("Step: 19-- Table is displayed");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Form Type");
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass", "SREG-084- Table");
		Assert.assertTrue(sreg084.tableHeadingList.isDisplayed());
		Assert.assertTrue(sreg084.editActionBtn.isDisplayed());
		Assert.assertTrue(sreg084.deleteActionBtn.isDisplayed());
		sreg084.submitButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("POA/Third Party Representative Conformation", "Pass", "SUC-002");
		Assert.assertTrue(suc_002.screenIdText.isDisplayed());
		Assert.assertTrue(suc_002.homeButton.isDisplayed());

		test.info(
				"Step: 20-- Click on \"Add or remove POA/Thrid Party Representative to Employer(hyperlink)\" in \"POA/Third Party Representative Confirmation (SUC-002)\" screen.");
		suc_002.addOrRemoveLink.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Add or Remove Third Party Association to Employer", "Pass", "SREG-537");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg084.previousButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());

		test.info(
				"Step: 21-- Click on Previous button  at \"Add or Remove Third Party Representative to Employer (SREG-537)\" screen");
		sreg084.previousButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("POA/Third Party Representative Confirmation", "Pass", "SUC-002");
		Assert.assertTrue(suc_002.homeButton.isDisplayed());

		test.info(
				"Step: 22-- Click on \"Home\" button in \"POA/Third Party Representative Confirmation (SUC-002)\" screen.");
		suc_002.homeButton.click();

	}
}
