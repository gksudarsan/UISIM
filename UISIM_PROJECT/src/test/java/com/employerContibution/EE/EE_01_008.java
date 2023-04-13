//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_008 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Company' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void Test_EE_01_008() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Company' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//DB SELECT query
		//FEIN not in DOL-DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		//EAN not in DOL-DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC","EAN");
		String eanValue = databaseEanResult.get("EAN");

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
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-025 ---
		sleep();
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");		
		//String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " Town ");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100G ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();
		
		
		//--- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("XYZ Corp");
		commonFunction.enterTextboxContains("Other commonly known", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFunction.safeJavaScriptClick(empRegPage.iSyourEntityQuestion_Yes);
		//commonFunction.safeJavaScriptClick(empRegPage.iSyourEntityQuestion_Yes);
		sleep();
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on Information page and click continue");
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Clothing");
		commonFunction.enterTextboxContains("Address Line 1 ", "60 Ave");
		commonFunction.enterTextboxContains("City ", "Albany");
		commonFunction.enterTextboxContains("Zip Code", "44673");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		
		
		commonFunction.screenShot("EmpRegister6", "Fail", "System Failure");
	}

}
