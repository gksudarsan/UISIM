package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;
@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_019_01_002_CSR_Verify_CSR_is_able_to_search_FEIN_and_update_related_Business_Location_Headquarters_Details_for_an_management_agreement extends TestBase {
		
		@Test(priority=1, description = "EM_019_01_001Verify CSR is able to FEIN  and update related Business Location/ Headquarters Details’ for an management agreement.",groups = {"Regression"})
		public void EM_019_01_002() throws Exception
		{
			commonStepDefinitions CommFun= new commonStepDefinitions();
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
			HomePage home = new HomePage(driver);
			EmployerRegisterPage emp = new EmployerRegisterPage(driver);
			test = report.createTest("EM_019_01_002_Verify CSR is able to search FEIN and update related Business Location/ Headquarters Details’ for an management agreement.");
		//-------Login
				
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		CommFun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		
		Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT WHERE FEIN IS NOT NULL AND EAN LIKE '9%' AND ORGANIZATION_TYPE = 'CORP' AND EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_PARTNER tep GROUP BY EMPLOYER_ACCOUNT_ID HAVING COUNT(EMPLOYER_ACCOUNT_ID) > 1)","EAN");
		 
		String ErnNum=databaseResults.get("EAN");
		//CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		System.out.println(ErnNum);
		sleep(2000);
		
		Map<String, String> databaseResults1 = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT WHERE FEIN IS NOT NULL AND EAN LIKE '9%' AND ORGANIZATION_TYPE = 'CORP' AND EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_PARTNER tep GROUP BY EMPLOYER_ACCOUNT_ID HAVING COUNT(EMPLOYER_ACCOUNT_ID) > 1)","FEIN");
		 
		String FeinNum=databaseResults1.get("FEIN");
		//CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		System.out.println(FeinNum);
		sleep(2000);
		
		
		//------Menu
		CommFun.screenShot("ApplicationLogin", "Pass", "Login is successful");
		CommFun.clickMenu("Menu");
		CommFun.ScrollMenu("Account Maintenance");
		CommFun.screenShot("Menu", "Pass", "Account Maintenance");
		CommFun.clickMenu("Account Maintenance");
		CommFun.ScrollMenu("Employer Account Maintenance");
		CommFun.clickMenu("Employer Account Maintenance");
		CommFun.screenShot("Account Maintenance", "Pass", "Employer Account Maintenance");
		CommFun.clickMenu("Joint Employment/Management Agreement Creation");
		sleep(2000);
		CommFun.screenShot("Menu Sroll", "Pass", "Joint Employment/Management Agreement Creation");
		//commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		//------SREG - 520
		
		
		
				CommFun.screenShot("Joint Employment/Management Agreement Creation – Enter ERN", "Pass", "Successfully landed on SREG 520");
				sleep(2000);
				CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
				sleep(2000);
				CommFun.screenShot("Joint Employment/Management Agreement Creation – Enter ERN ", "Pass", "Entered details on SREG 520");
				sleep(2000);
				CommFun.clickButton("Continue ");
				sleep(2000);
			
				
				//------SREG 110
				CommFun.screenShot("Joint Employment/Management Agreements", "Pass", "Successfully landed on SREG 110");
				sleep(2000);
				//CommFun.enterTextboxContains("Legal Name of Business","Testing");
				//sleep(2000);
				//CommFun.enterTextboxContains("FEIN",FeinNum);
				emp.employerFeinId.sendKeys(FeinNum);
				sleep(2000);
				//CommFun.enterTextboxContains("Employer Registration Number (ERN)",ErnNum);
				CommFun.screenShot("Joint Employment/Management Agreements ", "Pass", "Entered details on SREG 110");
				sleep(2000);
				CommFun.clickButton(" Search ");
				sleep(4000);
				CommFun.selectRadioInTable(FeinNum , 1, 1, "Joint Employment/Management Agreements");
				//CommFun.selectRadio("Select");
				sleep(2000);
				CommFun.screenShot("Joint Employment/Management Agreements ", "Pass", "selected details on SREG 110");
				sleep(2000);
				CommFun.clickButton("Continue ");
				sleep(2000);
				
				//------SREG -007
				CommFun.selectRadioQuestions("Select one", "Management Agreement");
				sleep(2000);
				CommFun.selectRadioQuestions("Are businesses financially related, with the same principal(s) owning 50% or more of each business?", "No ");
				sleep(2000);
				CommFun.selectRadioQuestions("Is there a management agreement in place allowing one entity to hire, fire, supervise, direct and control the employees of another entity?", "Yes ");
				sleep(2000);
				home.commentBox.sendKeys("Testing");
				sleep(2000);
				CommFun.clickButton("Continue ");
				sleep(2000);
				CommFun.enterTextboxContains("Claimant ID", "4511529");
				CommFun.screenShot("Home", "Pass", "Home element getting ");
				
				//geting sys failure after step 7 
			}}
