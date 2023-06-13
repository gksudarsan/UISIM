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
public class EM_321_011 extends TestBase{

	@Test
	public void EM_321_011() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		EmployerRegisterPage EmpRegPage = PageFactory.initElements(driver, EmployerRegisterPage.class);
		test = 
				report.createTest("EM.321.011 - Verify CSR is able to Delete Trustee/Owner Details");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.ScrollMenu("Maintain Business Ownership");
		cf.screenShot("NavigateToMaintainBusinessOwnership", "Pass", "Select Maintain Business Ownership");
		cf.clickMenu("Maintain Business Ownership");
		sleep();
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE='TRUS' ORDER BY UPDATED_TS", "EAN");
		String eanValue = databaseResults.get("EAN"); 
		System.out.println("The EAN Value is:"+ eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		test.log(Status.INFO, "EAN::"+"eanValue");
		cf.screenShot("MaintainBusinessOwnershipDetailsEnterERN", "Pass", "Maintain Business Ownership Details - Enter ERN");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		AddPage.clickOnLink("Add Trustee/Owner Details");
		sleep();
		String ssn=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(ssn);
		cf.enterTextboxContains("SSN", ssn);
		cf.enterRandomString("First Name ");
		cf.enterRandomString("Last Name ");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99) +"avenue");
		cf.enterTextboxContains("City", "NY");
		cf.enterTextboxContains("Zip Code", cf.createRandomInteger(100, 999)+"23");
		cf.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		cf.clickButtonContains("Submit");
		sleep(3000);
		cf.screenShot("TrusteeOwnerDetails", "Pass", "Trustee/Owner Details");
		cf.clickOnLink("Delete");
		sleep();
		cf.screenShot("ConfirmationForDeletion", "Pass", "Confirmation Message For Deletion");
		try {
			cf.clickButtonContains("Yes");
		}
		catch(Exception e) {
			System.out.println("Confirmation for deleting message");
		}
		sleep();
		cf.screenShot("AfterDeletingMessage", "Pass", "Deleted Address Page");
	}
}
