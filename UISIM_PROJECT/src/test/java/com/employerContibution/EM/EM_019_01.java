package com.employerContibution.EM;



import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_019_01 extends TestBase
{


	
	@Test(priority=1, description = "EM.019.01- Verify CSR is able to search Legal name of business and add Related Business Details of a Joint Employment.",groups = {"Regression"})
	public void EM_019_01() throws Exception
	{
		 
		 test = report.createTest("EM.019.01- Verify CSR is able to search Legal name of business and add Related Business Details of a Joint Employment.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");			 
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");		 
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Joint Employment/Management Agreement Creation");
		 commonFuntions.clickMenu("Joint Employment/Management Agreement Creation");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);		 
		 commonFuntions.errorLabel("Required");
		 commonFuntions.enterTextbox("Employer Registration Number","6543210" );
		 commonFuntions.screenShot("Error_Required","Pass","Error message shown as expected");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		 commonFuntions.screenShot("Error_Required1","Pass","Error message shown as expected");
		 commonFuntions.enterTextbox("Employer Registration Number","9300016" );
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.errorContent("To continue, you must search record and click continue button.");
		 commonFuntions.screenShot("Error_Required2","Pass","Error message shown as expected");
		 commonFuntions.enterTextbox("Legal Name of Business","ENSEC INC" );
		 commonFuntions.clickButtonContains("Search");
		 Thread.sleep(2000);
		 commonFuntions.screenShot("Joint","Pass","Screen as expected");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.errorContent("Please select a record to proceed further.");
		 commonFuntions.screenShot("Error_Required3","Pass","Error message shown as expected");
		 commonFuntions.selectRadioInTable("65-0292225", 1, 1, "Joint Employment/Management Agreements");
		 commonFuntions.screenShot("Joint1","Pass","Screen as expected");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.selectRadio("Joint Employment");
		 commonFuntions.selectRadioQuestions("Are businesses financially related, with the same principal(s) owning 50% or more of each business?","Yes");
		 commonFuntions.selectRadioQuestions("Joint Employment - Are Employees concurrently employed by both businesses?","Yes");
		 commonFuntions.selectRadioQuestions("Are businesses required to report separately for FUTA?","No");
		 commonFuntions.populateListbox("Remarks","Test");
		 commonFuntions.screenShot("Joint3","Pass","Screen as expected");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.screenShot("Joint4","Pass","Screen as expected");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.clickButtonContains("Submit");
		 Thread.sleep(2000);
		 commonFuntions.errorContent("Required");
		 commonFuntions.screenShot("Error_Required4","Pass","Error message shown as expected");
		 commonFuntions.selectDateInTable("65-0292225", 5, 1, "Joint Employment/Management Agreement Arrangement ","03/10/2023");
		 commonFuntions.selectDateInTable("65-0292225", 6, 1, "Joint Employment/Management Agreement Arrangement ","02/10/2023");
		 commonFuntions.screenShot("Joint5","Pass","Screen as expected");		 
		 commonFuntions.clickButtonContains("Submit");
		 Thread.sleep(2000);
		 commonFuntions.errorContent("Effective end date for row 1 cannot be prior to the Effective Start Date.");
		 commonFuntions.screenShot("Error_Required4","Pass","Error message shown as expected");
		 
		 
	     
		 
		   	   
	}
	
}