package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_443_001 extends TestBase {

	@Test
	public void EM_443_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(
				"EM.443.001:Verify CSR is able to enter ERN and Add POA/TPR association for designation type \"All Unemployment Insurance Matters\"");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		sleep();
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		sleep();
		commonFuntions.screenShot("AccountMaintenance", "Pass", "Employer Account Maintenance");
		commonFuntions.clickMenu("Add or Remove POA/TPR Association");
		sleep();
		commonFuntions.screenShot("AddorRemovePOA/TPRAssociation", "Pass", "Add or Remove POA/TPR Association");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.errorLabel("Required");
		commonFuntions.screenShot("RequiredError", "Pass", "Add or Remove POA/TPR Association:Enter ERN");
		sleep();
		commonFuntions.enterTextbox("Employer Registration Number", "1111111");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		sleep();
		commonFuntions.screenShot("RequiredErrorERNDoesNotExist", "Pass",
				"Add or Remove POA/TPR Association:ERN Does Not Exist");

		// query to fetch ERN number from database
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS DESC",
				"EAN");
		sleep();
		String ernNumber = databaseResults.get("EAN");
		System.out.println("The selected ERN is: " + ernNumber);
		test.log(Status.INFO, "EAN::" + ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernNumber);
		sleep();
		commonFuntions.screenShot("ERN", "Pass", "Add or Remove POA/TPR Association–Enter ERN");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("AddorRemoveThirdPartyAssociationEmployer", "Pass",
				"Add or Remove Third Party Association Employer");
		sleep();
		commonFuntions.clickButtonContains(" Search POA/TPR ");
		sleep(2000);
		commonFuntions.errorLabel(" Designation Type is required");
		commonFuntions.screenShot("DesignationTypeIsRequired", "Pass", "Designation Type is Required:SREg-537");
		sleep();
		commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		sleep();
		commonFuntions.screenShot("DesignationTypeSelectedDropdown", "Pass",
				"Selected the dropdown value for Designation type");
		commonFuntions.clickButtonContains(" Search POA/TPR ");
		sleep(2000);
		commonFuntions.screenShot("SearchPOA/ThirdPartyRepresentative", "Pass",
				"Search POA/Third Party Representative");
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.errorContent("Legal name of business is required.");
		sleep();
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", "test");
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("SearchPOA/ThirdPartyRepresentativeSelectTheRecordError", "Pass",
				"Search POA/Third Party Representative:Select Record Error Appear");
		commonFuntions.errorContent("Please select a record to proceed further.");
		sleep();
		commonFuntions.selectRadio("Select");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButton("Submit ");
		sleep(2000);

		// Validate the error required message....
		commonFuntions.screenShot("requiredErrorMessage", "Pass", "Validate the required error message");
		AddPage.requiredError_genInfo();
		sleep();
		// validate the association end date
		PEOPage.dateFeild.sendKeys("54835748");
		sleep();
		AddPage.commentField.sendKeys("test test");
		sleep();
		commonFuntions.selectCheckbox("Additional authorization");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("DateErrorMessage", "Pass", "DateErrorMessages");
		commonFuntions.selectRadio("Select");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("ConfirmationMessages", "Pass", "TPR Confirmation Messages");
		commonFuntions.clickButtonContains("Home");
	}

}
