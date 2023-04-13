//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_13_005 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Governmental'.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_13_005() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Governmental'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//DB SELECT query
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");

				
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		//commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Employer Registration");
		empRegPage.employerRegisterMenu.click();
		//commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFunction.clickMenu("Register Employer");
		sleep();
		
		//--- SREG-001 ---
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.selectRadioQuestions("Short Form Registration", "Yes ");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-025 ---
		sleep();
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Non-Profit ");		
		//String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " Limited Liability Company (All Types) ");
		commonFunction.selectDropdown("Source", " IA602 ");
		commonFunction.selectDropdown("Source Type", " Coverage Exception ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();
		
		//--- SREG-003 ---
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Mandatory fields Warning message displaying");
		
		empRegPage.legalNameTextBox.sendKeys("XYZ Corp");
		commonFunction.enterTextboxContains("Other name under which you operate", "XYZ Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains("Ext", "003");
		sleep();
		commonFunction.screenShot("EmpRegister4", "Pass", "Warning Message on clicking Continue");
		commonFunction.clickButton("Continue ");
		
		commonFunction.enterTextboxContains("Other name under which you operate", "");
		commonFunction.enterTextboxContains("Other name under which you operate", "New Corp");
		commonFunction.clickButton("Continue ");
		
		// to script from step 8 MC
		
		commonFunction.screenShot("FailureScreen", "Fail", "System Failure");
	}
}
