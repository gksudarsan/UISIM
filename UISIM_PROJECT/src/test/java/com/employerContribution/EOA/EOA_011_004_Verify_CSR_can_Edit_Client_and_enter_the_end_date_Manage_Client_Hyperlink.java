package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_011_004_Verify_CSR_can_Edit_Client_and_enter_the_end_date_Manage_Client_Hyperlink extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process (TPR) - (FEIN, Legal name)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_07_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);

		test = report.createTest("EOA.07.001-Verify CSR can access Employer Users screen and add, edit user(s)");
		sleep(3000);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		try {
			commonFuntions.clickButtonContains(" I agree with the Terms and Conditions ");
			test.log(Status.PASS, "Accepted 'Terms and Conditions for PEO'");
		} catch (Exception exception) {
			test.log(Status.PASS, "Accepted 'Terms and Conditions for Professional Employer Organizations(PEO)'");
		}

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		// commonFuntions.ScrollMenu("Employer Users");
		// commonFuntions.clickMenu("Employer Users");
		eoaPage.employerUserOption.click();
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen is visible");

		/*
		 * Map<String, String> EAN_Value =
		 * commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;"
		 * , "EAN");
		 */
		/*Map<String, String> EAN_Value = commonFuntions
				.database_SelectQuerySingleColumn("SELECT b.FEIN AS SEARCHSCREENFEIN,\r\n"
						+ "b.COMPANY_NAME AS SEARCHSCREENLEGALNAME,\r\n" + "E.EAN AS AddClientAssociation_ean,\r\n"
						+ "E.FEIN AS AddClientAssociation_Fein ,\r\n" + "D.TRADE_NAME AS ClienttradeName,\r\n"
						+ "E.ENTITY_NAME AS AddClientAssociation_entityname\r\n"
						+ "FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION A\r\n"
						+ "JOIN T_THIRD_PARTY_AGENT B ON A.THIRD_PARTY_CDS_VENDOR_ID = B.THIRD_PARTY_AGENT_ID\r\n"
						+ "JOIN T_EMPLOYER C ON A.EMPLOYER_ID = C.EMPLOYER_ID\r\n"
						+ "JOIN T_REGULAR_EMPLOYER D ON C.EMPLOYER_ID = D.EMPLOYER_ID\r\n"
						+ "JOIN T_EMPLOYER_ACCOUNT E ON d.EMPLOYER_ACCOUNT_ID = E.EMPLOYER_ACCOUNT_ID\r\n"
						+ "WHERE A.ASSOCIATION_STATUS ='ACTIVE' AND A.THIRD_PARTY_CDS_VENDOR_START_DATE IS NOT NULL AND \r\n"
						+ "(A.THIRD_PARTY_CDS_VENDOR_END_DATE > CURRENT_DATE OR A.THIRD_PARTY_CDS_VENDOR_END_DATE IS NULL)\r\n"
						+ "AND A.DESIGNATION_TYPE IS NOT NULL;", "ADDCLIENTASSOCIATION_EAN");

		String ean = EAN_Value.get("ADDCLIENTASSOCIATION_EAN");
		System.out.println(ean);
		test.log(Status.INFO, "EAN used : " + ean);*/
		
		String ean = "5239382";

		test.info("Step: 2 -- ");
		sleep(1000);
		commonFuntions.selectRadioQuestions("Account Type: ", "Third Party Representative");
		sleep(2000);
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", ean);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 Screen is displayed");
		
		////////////////////////////////////////////////////////////////////////////////////
		//Adding User
		/*eoaPage.addUserLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");
		
		test.info("Step:  -- ");
		commonFuntions.enterTextboxContains("First Name", "Test");
		commonFuntions.enterTextboxContains("Last Name", "Acc");
		commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ", "1234567890");
		commonFuntions.enterTextboxContains(" Cell Number ", "1234567809");

		String userId = "dev007@" + commonFuntions.createRandomString() + "12";
		System.out.println(userId);
		test.log(Status.INFO, "userId: " + userId);

		String password = "143W" + commonFuntions.createRandomString() + "*" + commonFuntions.createRandomString()
				+ "12";
		System.out.println(password);
		test.log(Status.INFO, "password: " + password);

		commonFuntions.enterTextboxContains("User ID", userId);
		commonFuntions.enterTextboxContains("Temporary Password", password);
		commonFuntions.enterTextboxContains("Confirm Temporary Password", password);
		commonFuntions.selectDropdown("User Types", " TPR Sub-User ");
		sleep();
		eoaPage.tprAllCheckbox.click();
		sleep();
		commonFuntions.screenShot("Add TPR User", "Pass", "SREG-531 screen is visible");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add User Confirmation", "Pass", "SUC-002 screen is displayed");
		
		//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		// commonFuntions.ScrollMenu("Employer Users");
		// commonFuntions.clickMenu("Employer Users");
		eoaPage.employerUserOption.click();
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen is visible");
		
		//
		sleep(1000);
		commonFuntions.selectRadioQuestions("Account Type: ", "Third Party Representative");
		sleep(2000);
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", ean);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 Screen is displayed");
		sleep(3000);
		*/
		///////////////////////////////////////////////////////////////////////////////////

		test.info("Step: 3 -- ");
		//eoaPage.manageClientClickSREG061(userId);
		eoaPage.manageClientButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Manage View User Client Association", "Pass", "SREG-682 Screen is displayed");

		test.info("Step: 4 -- ");
		/*commonFuntions.clickOnLink1("ADD CLIENT");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Add Client Association", "Pass", "SREG-681 Screen is displayed");
		*/
		sleep(2000);
		//eoaPage.firstCheckboxAll.click();
		eoaPage.firstCheckbox.click();
		eoaPage.firstEnterDateField.sendKeys("12/12/2023");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Client Association Confirmation", "Pass", "SUC-002 Screen is displayed");
		
		test.info("Step: 5 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		
		test.info("Step: 6 -- ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		// commonFuntions.ScrollMenu("Employer Users");
		// commonFuntions.clickMenu("Employer Users");
		eoaPage.employerUserOption.click();
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen is visible");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 Screen is displayed");
		
		//SREG-682
		//eoaPage.manageClientClickSREG061("userId");
		eoaPage.manageClientButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Manage View User Client Association", "Pass", "SREG-682 Screen is displayed");
	
		test.info("Step: 7 -- ");
		commonFuntions.clickOnLink1(" + ADD CLIENT");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Add Client Association", "Pass", "SREG-681 Screen is displayed");
		
		

	}

}
