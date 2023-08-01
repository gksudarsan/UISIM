package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_02_004_Emp_NoticeOfPotentialCharge extends TestBase {

	@Test()
	public void EE_01_004_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions(); /*
																 * String feinValue1 =StringUtils.left(
																 * String.valueOf((long)
																 * (Math.random()*Math.pow(10,10))),5); String
																 * feinValue2 = "9999" ; String feinValue = feinValue2 +
																 * feinValue1 ; System.out.println("FEIN NUMBER = "
																 * +feinValue);
																 */
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'", "EAN");
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " + EAN);

		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagementLocators em = new employerManagementLocators();

		test = report.createTest(
				"EM_260_004- Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

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
		cf.selectTableWithoutId("Notice of Potential Charges (LO400) Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-700");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
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
		cf.selectTableWithoutId("Notice of Potential Charges (LO400) Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-700");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
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
		//cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.clickButtonContains("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home Page");
		System.out.println("Worked");

	}
}