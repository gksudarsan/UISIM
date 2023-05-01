package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_13_001 extends TestBase {

	@Test()
	public void EE_13_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				" EE.13.001");
		Map<String, String> databaseFeinResult = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.safeJavaScriptClick(empPage.employerRegisterMenu);
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		cf.selectRadioQuestions("Short Form Registration", "Yes ");
		sleep();
		cf.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		empPage.legalNameTextBox.sendKeys("sdfjkhsf");
		feinValue =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(feinValue);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.selectDropdown("Source", " IA602 ");
		cf.safeJavaScriptClick(empPage.browserLink);
		cf.uploadDoc("Sample");
		sleep(4000);
		cf.clickButton("Submit ");
		cf.screenShot("EmpRegi sort form", "Pass", "");

		sleep(20000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextbox("FEIN", feinValue);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		cf.clickOnLink("Short Form Registration Review");
		sleep();
		cf.clickButtonContains("Open Work Item");
		cf.clickOnLink("Short Form Registration");
		cf.clickButton("Continue ");
		


		/**************** SREG-025 ************************/

		cf.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		cf.selectDropdown("Employer Type", " Business ");
		cf.selectDropdown("Type of Legal Entity", " Other ");
		cf.enterTextboxContains("If Other, provide the type of Legal Entity.", "not known");
		
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.clickButton("Continue ");
		cf.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		cf.selectDropdown("Source", " IA602 ");
		cf.selectDropdown("Source Type", " Other ");
		cf.clickButton("Continue ");
		sleep(3000);

		/**************** SREG-003 ************************/
		cf.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		cf.clickButton("Continue ");
		sleep(3000);
		cf.screenShot("EmpRegister4", "Pass", "Required text on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("Random Test OP LLC");
		cf.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Random Test OP LLC");
		cf.enterTextboxContains(" Business Phone Number  ", "6732111111");
		cf.enterTextboxContains("Total number of covered employees", "50");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();

		sleep();
		empPage.yearDropDown.click();
		empPage.yearValue_2022.click();

		sleep();

		/**************** SREG-004 ************************/

		sleep(3000);

		cf.enterTextboxContains("Address Line 1 ", "20 Square cooper");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34562");
		cf.selectRadioQuestions("County", " Albany ");

		empPage.addressLine1_Form1_SREG_004.sendKeys("20 cooper square");
		empPage.city_Form1.sendKeys("Albany");
		empPage.zipCode_Form1.sendKeys("43424");
		cf.safeJavaScriptClick(empPage.countyDropDown_Form1);
		cf.safeJavaScriptClick(empPage.countyValue_Form1);
		cf.clickButton("Continue ");

		cf.screenShot("Bussiness Aquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		;
		cf.screenShot(" business ac", "Pass", " business ac");
		cf.clickButtonContains("Continue");	
		sleep(2000);
		cf.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
		cf.selectRadioQuestions("Have you changed legal entity?", " No ");
		
		
		sleep(3000);
		cf.screenShot("EmpRegister10", "Pass", "Navigated to  page");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		cf.safeJavaScriptClick(empPage.browserLink);
		cf.uploadDoc("Sample");
		sleep(4000);
		cf.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
		sleep(3000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-043----------------*/
		sleep(3000);
		cf.screenShot("EmpRegister13", "Pass", "Navigated to SREG-043 page and click on I accept and submit");
	
		sleep();
		cf.clickButton("Submit ");
		cf.waitForLoadingIconToDisappear();
		
		
		



	}
}
