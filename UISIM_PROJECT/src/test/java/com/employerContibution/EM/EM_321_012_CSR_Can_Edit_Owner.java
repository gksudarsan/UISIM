package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;

import stepDefinitions.commonStepDefinitions;

public class EM_321_012_CSR_Can_Edit_Owner extends TestBase{

	@Test
	public void EM_321_012() throws Exception {
		commonStepDefinitions cf= new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("EM.321.012 - Verify CSR is able to enter the term end date and Inactivate Trustee/Owner Details.");
		cf.login("ndfjp3", "Admin@12345678");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Maintain Business Ownership");
		cf.clickMenu("Maintain Business Ownership");
		sleep(3000);
		
		
		/*----------------SREG-029----------------*/
		
		cf.screenShot("ChangeOwner", "Pass", "Navigated to SREG-029 page");
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE ORGANIZATION_TYPE='TRUS 'AND  ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS", "EAN");

//		String ernValue = ernOutput.get("EAN");
		String ernValue = "0000096";
		System.out.println(ernValue);

		test.log(Status.INFO, "ERN : : "+ernValue);

		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(3000);
		
		/*----------------SREG-710----------------*/
		cf.screenShot("ChangeOwner2", "Pass", "Navigated to SREG-710 page and click on Inactive link");
		cf.clickOnLinkAnchorTag("Add Trustee/Owner Details");
		sleep(3000);
//		Map<String, String> ssnOutput = cf.database_SelectQuerySingleColumn("SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
//				+ "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
//				+ "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
//				+ "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
//				+ "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
//				+ "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');", "T10_SSN");
//		
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		cf.enterTextboxContains("SSN", ssnValue);
		
		String fnameValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4);
		String lnameValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4);
		
		String fname = "A"+fnameValue+"A";
		String lname = "J"+lnameValue+"J";
		
		cf.enterTextboxContains("First Name ",fname );
		cf.enterTextboxContains("Last Name ", lname);
		cf.selectDropdown("Title ", " Trustee");
		cf.enterTextboxContains("Address Line 1 ", "Fake Address");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "23432");
		cf.enterTextboxContains(" Contact Number ", "3456345276");
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");
		cf.clickButton("Submit ");
		sleep(3000);
		cf.selectInactiveLinkInTable(fname, lname);
		sleep(2000);
		cf.clickButton(" Yes ");
		/*----------------SREG-709----------------*/
		sleep(3000);
		cf.screenShot("ChangeOwner3", "Pass", "Changing the end date");
		cf.enterCurrentDate("Term End Date");
		cf.clickButton("Submit ");
		sleep();
		cf.screenShot("ChangeOwner4", "Pass", "Updated the term end date");
		
		
		
		
		
	}
	
}
