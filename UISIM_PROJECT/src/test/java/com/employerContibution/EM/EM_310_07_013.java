package com.employerContibution.EM;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

//import com.relevantcodes.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_310_07_013 extends TestBase
{


	
	@Test(priority=1, description = "\"EM.310.07.013 - Verify Employer is able to change in Legal entity when a return outside of liability exists for the predecessor and system create task for CSR reviews.",groups = {"Regression"})
	public void EM_310_07_003() throws Exception
	{
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 test = report.createTest("EM.310.07.013 - Verify Employer is able to change in Legal entity when a return outside of liability exists for the predecessor and system create task for CSR reviews.");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		 Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT EMPLOYER_TYPE,EAN FROM T_EMPLOYER_ACCOUNT tea","EAN");
	     String eanValue = databaseEanResult.get("EAN");
	     System.out.println(eanValue);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 commonFuntions.login(COMMON_CONSTANT.EMP_USER_1,COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.safeJavaScriptClick(empPage.menuButtonHomepage);
//		 commonFuntions.clickMenu("Menu");
		 sleep();
		 commonFuntions.ScrollMenu("Account Maintenance");
		 sleep();
		 commonFuntions.clickMenu("Account Maintenance");
		 sleep();
		 commonFuntions.ScrollMenu("Change in Legal Entity");
		 sleep();
		 commonFuntions.clickMenu("Change in Legal Entity");
		 sleep();
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("Change in Legal Entity – Enter ERN","Pass","Change in Legal Entity – Enter ERN is launched");
		 sleep();
		 commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		 commonFuntions.selectRadio("Yes ");
		 String FEIN = "999311751";
		 commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) " ,FEIN);
		 commonFuntions.enterTextboxContains("Prior Employer Registration Number",eanValue);
		 commonFuntions.enterCurrentDate("Date of Legal Entity change");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 /*---------------------EM-007-----------------*/
		 commonFuntions.screenShot("ApplicationLogin","Pass","Navigated to EM-007 page");
		 commonFuntions.clickButton("Submit ");
		 sleep();
		 commonFuntions.waitForLoadingIconToDisappear();
		 /*-------------------SUC-002------------------*/
		 commonFuntions.validateNextPageNumber("SUC-002");
		 commonFuntions.screenShot("ApplicationLogin2","Pass","Navigated to SUC-002 page");
		 commonFuntions.clickButton("Home ");
		 sleep(4000);
		 
		 /*-------------------Home Page------------------*/
		 commonFuntions.logoutAndLogin("ndfjp3", "Admin@1234567891");
		 commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
		 PEOPage.queue.click(); 
		 sleep();
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.enterTextboxContains("FEIN", FEIN);
		 commonFuntions.clickButtonContains(" Search ");
		 sleep(3000);
		 commonFuntions.clickOnLinkAnchorTag("Verify Transfer Failed Rules");
		 sleep();
		 commonFuntions.waitForLoadingIconToDisappear();
		 /*-------------------WF-091------------------*/
		 commonFuntions.screenShot("ApplicationLogin2","Pass","Navigated to WF-091 page");
		 commonFuntions.clickButton("Open Work Item ");
		 sleep();
		 commonFuntions.waitForLoadingIconToDisappear();
		 /*-------------------EEWI-014------------------*/
		 commonFuntions.screenShot("ApplicationLogin2","Pass","Navigated to EEWI-014 page");
		 commonFuntions.enterTextboxContains("Comment", "sdjhfs sdfyug");
		 commonFuntions.clickButton("Submit ");
		
		 
	}
}