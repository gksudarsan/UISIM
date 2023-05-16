package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_016 extends TestBase{

	@Test
	public void EM_321_016() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		EmployerRegisterPage EmpRegPage = PageFactory.initElements(driver, EmployerRegisterPage.class);
		test = 
				report.createTest("EM.321.016 - Verify CSR is able to enter the term end date and Inactivate Executor/Owner Details");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.ScrollMenu("Maintain Business Ownership");
		cf.screenShot("NavigateToMaintainBusinessOwnership", "Pass", "Select Maintain Business Ownership");
		cf.clickMenu("Maintain Business Ownership");
		sleep();
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE='ESTA' AND REGISTRATION_STATUS='C'", "EAN");
		String eanValue = databaseResults.get("EAN"); 
		System.out.println("The EAN Value is:"+ eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		test.log(Status.INFO, "EAN::"+"eanValue");
		cf.screenShot("MaintainBusinessOwnershipDetailsEnterERN", "Pass", "Maintain Business Ownership Details - Enter ERN");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		AddPage.clickOnLink("Add Executor/Owner Details");
		sleep();
		String ssn=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(ssn);
		cf.enterTextboxContains("SSN", ssn);
		String firstname = cf.enterRandomString("First Name ");
		String lastname = cf.enterRandomString("Last Name ");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99) +"avenue");
		cf.enterTextboxContains("City", "NY");
		cf.enterTextboxContains("Zip Code", cf.createRandomInteger(100, 999)+"23");
		cf.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		cf.clickButtonContains("Submit");
		sleep(3000);
		cf.screenShot("TrusteeOwnerDetails", "Pass", "Trustee/Owner Details");
		cf.selectInactiveLinkInTable(firstname, lastname);
		sleep();
		cf.screenShot("ConfirmationForInactivateLink", "Pass", "Confirmation For Inactivate Link");
		try {
			cf.clickButtonContains("Yes");
		}
		catch(Exception e) {
			System.out.println("Confirmation for inactivate link");
		}
		sleep(2000);
		cf.errorLabel(" In order to inactivate a member, please enter the term end date ");
		cf.screenShot("AfterInactivateLink", "Pass", "Inactivate Link Page");
		cf.enterFutureDate("Term End Date", 10);
		cf.clickButtonContains("Submit");
		sleep();
		cf.screenShot("ConfirmationInactivated", "Pass", "Confirmation For Inactivated - SREG-712");
	}
}
