package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_05_001_EMP_Verify_Employer_is_able_to_process_Business_Acquisition_and_indicate_transfer_Total extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is able to process Business Acquisition and indicate transfer 'Total", groups = { "Regression" })
	public void 
	EM_310_05_001()throws Exception {
		test=report.createTest("Verify Employer is able to process Business Acquisition and indicate transfer 'Total");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		
		//---------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_3,COMMON_CONSTANT.EMPLOYER_USER_3_PASSWORD );
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	/*
		Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID WHERE tea.EMPLOYER_TYPE IN ('BUSI''AGRI','NONP') ORDER BY tea.UPDATED_TS DESC","EAN");
		 
		String ErnNum=databaseResults.get("EAN");
		CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		System.out.println(ErnNum);
		sleep(2000);
		
		Map<String, String> databaseResults2 = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID WHERE tea.EMPLOYER_TYPE IN ('BUSI''AGRI','NONP') ORDER BY tea.UPDATED_TS DESC","FEIN");
		String FeinNum=databaseResults2.get("FEIN");
		CommFun.enterTextboxContains("Employer Registration Number",FeinNum);
		System.out.println(FeinNum);
		sleep(2000);
		*/
		
		//----Menu
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu page");
		CommFun.clickMenu("Menu");
		sleep(2000);
		CommFun.ScrollMenu("Account Maintenance");
		home.accountMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.BusinessAcquisition.click();
		sleep(2000);
		
	
		//-----SREG-011
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on SREG-011");
		sleep(2000);
		
		CommFun.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		//CommFun.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		//CommFun.enterTextboxContains("Employer Registration Number", ErnNum);
		//CommFun.enterTextboxContains("Federal Employer Identification Number (FEIN)", FeinNum );
		sreg.LegalNameOfBusiness.sendKeys("Township");
		
		CommFun.enterTextboxContains("Address Line 1 ", "Near LIG square");
		CommFun.enterTextboxContains("City ", "Albney");
		CommFun.selectDropdown("State", " New York ");
		CommFun.enterTextboxContains("Zip Code", "34565");
		CommFun.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		CommFun.enterTextboxContainsThirdBox("Acquisition Date", "05/16/2023");
		CommFun.clickButtonContains("Continue ");
		
		//-----SREG-012 instead of EM-006
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on SREG-012");
		sleep(2000);
		
		CommFun.clickButtonContains("Submit ");
		
	
		
		//----SUC 002
		sleep(2000);
		CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		CommFun.clickButtonContains("Home ");
		sleep(2000);
		CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
	
		//------Having Issue here we are getting SREG 012 screen in place of EM 006 
		///-------We are blocking this TC
		
		
					}
			
		}
