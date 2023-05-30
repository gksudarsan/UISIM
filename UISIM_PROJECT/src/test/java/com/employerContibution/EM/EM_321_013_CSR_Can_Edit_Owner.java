package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;

import stepDefinitions.commonStepDefinitions;

public class EM_321_013_CSR_Can_Edit_Owner extends TestBase{

	@Test
	public void EM_321_012() throws Exception {
		commonStepDefinitions cf= new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("EM.321.013 - Verify CSR is able to Edit existing Trustee/Owner Details.");
		cf.login("ndfjp3", "Admin@123456789");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Maintain Business Ownership");
		cf.clickMenu("Maintain Business Ownership");
		sleep(3000);
		
		
		/*----------------SREG-029----------------*/
		
		cf.screenShot("ChangeOwner", "Pass", "Navigated to SREG-029 page");
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE ORGANIZATION_TYPE='TRUS ' ORDER BY UPDATED_TS", "EAN");

		String ernValue = ernOutput.get("EAN");
//		String ernValue = "0000096";
		System.out.println(ernValue);

		test.log(Status.INFO, "ERN : : "+ernValue);

		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(3000);
		
		/*----------------SREG-710----------------*/
		cf.screenShot("ChangeOwner2", "Pass", "Navigated to SREG-710 page and click on Inactive link");
		cf.clickOnLinkAnchorTag("Edit");
		sleep(3000);
		String add = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4);
		String zipcode = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 5);
		String contactNo = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 10);
		cf.enterTextboxContains("Address Line 1 ", "Fake Address "+add+"");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", zipcode);
		cf.enterTextboxContains(" Contact Number ", contactNo);
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");
		cf.clickButton("Submit ");
		test.log(Status.INFO, "Updated address : : "+"Fake Address "+add+"");
		test.log(Status.INFO, "Updated ZipCode : : "+zipcode);
		test.log(Status.INFO, "Updated Contact Number : : "+contactNo);
		/*----------------SREG-709----------------*/
		sleep(3000);
//		cf.ScrollMenu("Fake Address "+add+"");
		sleep();
		cf.screenShot("ChangeOwner3", "Pass", "Updated the address");

	}
	
}
