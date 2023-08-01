package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_260_02_001 extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.02.001.Verify Employer is able to update employer address for address type 'Primary business physical address'", groups = {
		"Regression" })
		public void EM_260_02_001()throws Exception
	{
		test = report.createTest(
				"EM.260.02.001.Verify Employer is able to update employer address for address type 'Primary business physical address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		employerManagementLocators em = new employerManagementLocators();
		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
//			"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",

				"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
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

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Primary Business Physical Address", 6, 1, "Maintain Address Details");

		// SREG-008
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-008");
		cf.clearTextboxContains("Address Line 1");
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("County", " Albany ");
		cf.clickButtonContains("Submit ");
		
		//SUC-002
		String msg = em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");
		sleep();
		cf.screenShot("Employer Address and Contact Person Details Confirmation", "Pass", "Launched to SUC-002");
		cf.clickOnLinkAnchorTag(" Maintain Addess Details Screen");
		
		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.clickButtonContains("home");
		cf.screenShot("Home", "Pass", "Launched to Home Page");

		cf.LogoutAndLoginIfOktaPageDisplayed("ndfjp3", "Admin@123456789");
		// Validation
		cf.clickMenu("Menu");
		cf.clickMenu("Inquiry");
		cf.clickMenu("Contribution Inquiry");
		cf.clickMenu("Inquiry Employer Address History");
		sleep(2000);
		cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Launched to EM-051");
		cf.enterTextboxContains("Employer Registration Number", ean);
		cf.clickButtonContains("Continue ");
		sleep();

		// SREG-057
		cf.screenShot("Inquiry Employer Address History", "Pass", "Launched to SREG-057");
		addPage.verifyInquiryAddressEmployerHistory("Physical Address");
		System.out.println("Worked");

	}
}