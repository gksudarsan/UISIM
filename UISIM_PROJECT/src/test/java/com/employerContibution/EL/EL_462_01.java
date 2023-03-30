package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_462_01 extends TestBase{

	@Test()
	public void EL_462_01_Indi_Exempt() throws Exception {

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'", "FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("feinNumber is" + FEIN);

		test = report.createTest("EL.462.01 - Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Individual' ");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		cf.screenShot("Menu", "Pass", "Manage PEO");
		cf.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		cf.enterTextboxContains("(FEIN)", FEIN);
		cf.screenShot("file1", "Pass", "Searching with FEIN ");
		cf.clickButtonContains("search");
		Thread.sleep(2000);
		cf.selectRadioWithFeinValue(FEIN);
		cf.clickButton("Continue ");
		Thread.sleep(4000);
		cf.selectDropdown("PEO Conversion", " PEO Group to PEO Individual ");
		cf.screenShot("DropDownValue", "Pass", "Selecting the dropdown value PEO Individual to PEO Exempt ");
		cf.clickButtonContains(" CONVERT ");
		cf.screenShot("", "Pass", "");
		try {
		cf.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alaska ");
		cf.enterTextboxContains("State agency that issued it.","homeagency");
		cf.selectRadio("Registration Number");
		cf.enterTextboxContains("Registration Number","2738383838");
		}
		catch (Exception e)
		{
			System.out.println("click on save and continue....");
		}
		cf.clickButton("Save & Continue");
		sleep();
		cf.screenShot("", "Pass", "");
		cf.clickButton("Save & Continue");
		sleep();
		cf.screenShot("", "Pass", "");
		cf.clickButton("Save & Continue");
		sleep();
		cf.screenShot("", "Pass", "");
		cf.clickButton("Save & Continue");
		sleep();
		cf.clickButton("Continue");
		cf.clickButton("Save & Continue");
		sleep();

		cf.clickButtonContains("Choose File");
		Thread.sleep(2000);
		cf.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		cf.clickButtonContains("Continue");
		Thread.sleep(2000);
		cf.screenShot("verifyClient","Pass","Verify Client List");
		cf.clickButtonContains("Continue");
		Thread.sleep(2000);
		cf.screenShot("peoDetails","Pass","Peo conversion");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		cf.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+cf.createRandomInteger(10000,99999));
		cf.screenShot("Declaration","Pass","Declaration");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		cf.screenShot("Acknowledgement","Pass","Statement Of Acknowledgement");
		cf.clickButtonContains("Accept & Submit");
		Thread.sleep(2000);
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.selectRadio("Approved");
		cf.screenShot("Completion","Pass","conversion indi to exempt");
		cf.clickButtonContains("submit");sleep();
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.clickButton("home ");
		em.Inquery(FEIN);
		
		test.log(Status.PASS, "Clicked on Home button");




	}
}
