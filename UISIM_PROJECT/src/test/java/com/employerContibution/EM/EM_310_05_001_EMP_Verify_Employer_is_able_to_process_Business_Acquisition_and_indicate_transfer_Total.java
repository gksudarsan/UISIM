package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_011;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod; 
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_05_001_EMP_Verify_Employer_is_able_to_process_Business_Acquisition_and_indicate_transfer_Total extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is able to process Business Acquisition and indicate transfer 'Total", groups = { "Regression" })
	public void 
	EM_310_05_001()throws Exception {
		test=report.createTest("Verify Employer is able to process Business Acquisition and indicate transfer 'Total");
		commonStepDefinitions cf = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		SREG_011 sreg011 = new SREG_011(driver);
		PEOPage peoPage= new PEOPage(driver);
		
		
		//---------Login
		cf.login(COMMON_CONSTANT.EMP_USER_1,COMMON_CONSTANT.EMP_USER_1_PASSWORD );
		sleep(2000);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		
		//----Menu
		Thread.sleep(5000);
		cf.clickMenu("menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		Thread.sleep(2000);
		cf.ScrollMenu("Business Acquisition");
		cf.clickMenu("Business Acquisition");
		Thread.sleep(2000);
		cf.screenShot("Menu", "Pass", "Business Acquisition");
		
		Map<String, String> databaseResults = peoPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		System.out.println("legalName " + legalName);
		
		cf.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		Thread.sleep(2000);
		sreg011.eanField.sendKeys(eanValue);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); 
		sreg011.legalNameField.sendKeys(legalName);
		
		cf.enterTextboxContains("Address Line 1 ", "Near LIG square");
		cf.enterTextboxContains("City ", "Albney");
		cf.selectDropdown("State", " New York ");
		Thread.sleep(2000);
		cf.enterTextboxContains("Zip Code", "34565");
		Thread.sleep(1000);
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		Thread.sleep(1000);
		//cf.enterTextboxContainsThirdBox("Acquisition Date", "05/16/2023");
		cf.enterCurrentDate("Acquisition Date");
		Thread.sleep(1000);
		cf.clickButtonContains("Continue ");
		
		try {
			cf.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		
		Thread.sleep(5000);
		cf.screenShot("Business Acquisition Details", "Pass", "EM-006 screen is visible");
		sleep(2000);
		
		test.info("Step: 6 -- ");
		Thread.sleep(2000);
		cf.clickButton("Submit ");
		Thread.sleep(2000);
		cf.screenShot("Business Acquisition Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 7 -- ");
		cf.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		cf.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");
		
		
		
		}
			
	}
