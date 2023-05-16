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
public class EM_321_014 extends TestBase{

	@Test
	public void EM_321_014() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		//EmployerRegisterPage EmpRegPage = PageFactory.initElements(driver, EmployerRegisterPage.class);
		test = 
				report.createTest("EM.321.014 - Verify CSR is able to Edit Executor/Owner Details.");
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
		cf.screenShot("ExecutorOwnerDetailsPage", "Pass", "Executor/Owner Details");
		AddPage.clickOnLink("Add Executor/Owner Details");
		sleep(2000);
		String ssn=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(ssn);
		test.log(Status.INFO, "SSN::"+"ssn");
		cf.enterTextboxContains("SSN", ssn);
		cf.enterRandomString("First Name ");
		cf.enterRandomString("Last Name ");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99) +"Cooper Square");
		cf.enterTextboxContains("City", "NY");
		cf.enterTextboxContains("Zip Code", cf.createRandomInteger(100, 999)+"23");
		cf.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		sleep();
		cf.screenShot("AddExecutorOwnerDetails", "Pass", "Add Executor/Owner Details");
		cf.clickButtonContains("Submit");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(PEOPage.enteredAddress);
			sleep();
			cf.screenShot("PopUpAddressPage", "Pass", "Address PopUp Displayed");
			sleep();
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}
		catch(Exception e) {
			System.out.println("Usps address popup displayed");
		}
		sleep(2000);
		AddPage.editLink.click();
		cf.enterTextboxContains("Contact Number",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		sleep();
		cf.screenShot("EditExecutorOwnerDetails", "Pass", "Edit Executor/Owner Details:SREG-711");
		cf.clickButtonContains("Submit");
		sleep(3000);
		cf.screenShot("ExecutorDetailsPage", "Pass", "Executor/Owner Details");
		
		
	}
}
