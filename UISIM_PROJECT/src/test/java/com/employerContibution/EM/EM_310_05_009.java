package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_310_05_009 extends TestBase{

	@Test
	public void EM_310_05_009() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		
		//login with employer account 
		test = 
				report.createTest("EM_310_05_009:Verify Employer is able to process Business Acquisition and indicate transfer 'Total' when the same transfer was processed previously and system create task for CSR reviews.  ");
		cf.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.screenShot("NavigateToBusinessAcquisition", "Pass", "Click on Business Acquisition");
		cf.clickMenu("Business Acquisition");
		sleep(2000);
		cf.screenShot("BusinessAcquisition", "Pass", "BusinessAcquisition-SREG:011");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		sleep();
		Map<String, String> databaseResults = cf.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID ORDER BY tea.UPDATED_TS DESC");
		String eanValue = databaseResults.get("Ean");
		System.out.println("The EAN Value is:"+ eanValue);
		test.log(Status.INFO, "Ean::"+"eanValue");
		String feinValue = databaseResults.get("Fein");
		System.out.println("The Fein Value is:"+ feinValue);
		test.log(Status.INFO, "Fein::"+"feinValue");
		sleep(2000);
		AddPage.EmployerRegNumber.sendKeys(eanValue);
		cf.enterRandomStringLegalName("Legal Name of Business");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99) +"Cooper");
		cf.enterTextboxContains("City", "NY");
		cf.enterTextboxContains("Zip Code", cf.createRandomInteger(100, 999)+"23");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		cf.enterFutureDate("Acquisition Date", 5);
		sleep(3000);
		cf.ScrollMenu("Business Acquisition");
		sleep(2000);
		cf.screenShot("BusinessAcquisitionEnterDetails", "Pass", "Business Acquisition Enter Deatails");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.screenShot("BusinessAcquisitionDetails", "Pass", "BusinessAcquisitionDetails:SREG-012");
		sleep();
		cf.clickButtonContains("Submit");
		sleep();
		cf.screenShot("BusinessAcquisitionConfirmation", "Pass", "Business Acquisition Confirmation:SUC-002");
		cf.clickButtonContains("Home");
		sleep(3000);
		
		//login with CSR account *************************
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		cf.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN", feinValue);
		cf.screenShot("SerachWithFein","Pass","Searching with fein number");
		cf.clickButtonContains("Search");
		sleep(3000);
		cf.screenShot("VerifyTransferFailedRules","Pass","Verify Transfer Failed Rules");
		cf.clickOnLink("Verify Transfer Failed Rules");
		sleep(3000);
		cf.clickButtonContains("Open Work Item");
		cf.screenShot("OpenWorkItem","Pass","Open work item:WF-091");
		sleep(3000);
		AddPage.commentBox.sendKeys("ok");
		cf.screenShot("ValidateTotal/PartialTransferFailedRulesTask","Pass","Validate Total/Partial Transfer Failed Rules Task");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("TaskConfirmation","Pass","Task Confirmation");
				
	}
}
