package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_02_002_Verify_Employer_able_to_update_employer_address_BusinessMailingAddress extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.02.002.Verify Employer is able to update employer address for address type 'business mailing address'", groups = {
			"Regression" })
	public void EM_260_02_002() throws Exception {
		test = report.createTest(
				"EM.260.02.002.Verify Employer is able to update employer address for address type 'business mailing address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		employerManagementLocators em = new employerManagementLocators();

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		String ean = em.storeEan().getText();
		System.out.println(ean);
		cf.clickMenu("Menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Address");
		sleep();

		// Add - SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Business Mailing Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-700");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		cf.clearTextboxContains("Address Line 1");
		cf.enterTextboxContains("Address Line 1", "ADDLine1" + cf.createRandomInteger(1000, 9999));
		cf.clearTextboxContains("Address Line 2");
		cf.enterTextboxContains("Address Line 2", "AddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("County", " Albany ");
		cf.clickButtonContains("Submit ");

		// SUC-002
		String msg = em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are save successfully");
		sleep();
		cf.screenShot("Employer Address and Contact Person Details Confirmation", "Pass", "Launched to SUC-002");
		cf.clickOnLinkAnchorTag(" Maintain Addess Details Screen");

		// Edit - SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Business Mailing Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-700");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		cf.clearTextboxContains("Address Line 1");
		cf.enterTextboxContains("Address Line 1", "UpdatedLine1" + cf.createRandomInteger(1000, 9999));
		cf.clearTextboxContains("Address Line 2");
		cf.enterTextboxContains("Address Line 2", "UpdateLine2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13422");
		cf.selectDropdown("County", " Albany ");
		cf.clickButtonContains("Submit ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.clickButtonContains("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home Page");
		System.out.println("Worked");

	}
}
