package com.employerContibution.EM;



import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_03_001 extends TestBase
{


	
	@Test(priority=1, description = "EM.321.03.001 - Verify CSR is able to enter ERN and update business information of an employer account.",groups = {"Regression"})
	public void EM_321_03_001() throws Exception
	{
		 
		 test = report.createTest("EM.321.03.001 - Verify CSR is able to enter ERN and update business information of an employer account.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFunctions= new commonStepDefinitions();
		 commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFunctions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFunctions.clickMenu("Menu");	
		 commonFunctions.ScrollMenu("Account Maintenance");
		 commonFunctions.clickMenu("Account Maintenance");
		 commonFunctions.clickMenu("Employer Account Maintenance");
		 commonFunctions.screenShot("Menu","Pass","Maintain Accounts");
		 commonFunctions.clickMenu("Maintain Accounts");	
		 Map<String, String> databaseResults = commonFunctions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '8%' AND FEIN IS NOT NULL ORDER BY UPDATED_TS","EAN");
			
			String EAN=databaseResults.get("EAN");
		//Testcase is Blocked as field and page name is coming as different
			commonFunctions.enterTextbox("Employer Registration Number", EAN); 
			commonFunctions.screenShot("ERN","Pass","Populating ERN");
			commonFunctions.clickButtonContains("Continue");
			sleep(2000);
			commonFunctions.enterTextbox("Legal Name of Business","LegalNameUpdated");
			sleep(2000);	
			commonFunctions.enterTextbox("Legal Name of Business","LegalNameUpdated");
			sleep(2000);
			commonFunctions.populateListbox("Comment", "Updating Comments");
			sleep(2000);	     
			commonFunctions.screenShot("Updated1","Pass","Updated Fields");
			commonFunctions.selectLink("Please select a file to upload that provides proof of name change.", "Browse");
		     sleep(2000);
		     commonFunctions.uploadDoc("Sample.docx");
		     sleep(2000);
		     commonFunctions.enterTextboxContains("Business Phone Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,12))),10));
		     commonFunctions.enterTextboxContains("Business Fax Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,12))),10));
		     commonFunctions.enterTextboxContains("Business Email Address","AutoTest"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,12))),4)+"@gmail.com");
		     sleep(2000);
		     commonFunctions.screenShot("Updated2","Pass","Updated Fields");
		     commonFunctions.selectDropdownUsingSearch("Type of Legal Entity", " Other ");
		     sleep(2000);
		     commonFunctions.selectDropdownUsingSearch("Employer Type", " Indian Tribe Employer ");
		     sleep(2000);
		     
		     commonFunctions.selectDropdownUsingSearch("Source", " NYS-100 (paper) ");
		     sleep(2000);
		     commonFunctions.selectDropdownUsingSearch("Source Type", " NYS-100 ");
		     sleep(2000);
		     commonFunctions.clickButtonContains("Submit");
				sleep(4000);
				commonFunctions.Label("The Account Information has been succesfully saved");
				commonFunctions.screenShot("Success","Pass","Success Screenshot");
				commonFunctions.clickButtonContains("Home");
				sleep(2000);
	}
}