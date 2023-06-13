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
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_475_001 extends TestBase
{


	
	@Test(priority=1, description = "EM.475.001- Verify CSR is able to search POA/TPR detail and remove the association for POA/TPR with designation type in \"All Unemployment Insurance Matters\".",groups = {"Regression"})
	public void EM_475_001() throws Exception
	{
		 
		 test = report.createTest("EM.475.001- Verify CSR is able to search POA/TPR detail and remove the association for POA/TPR with designation type in \"All Unemployment Insurance Matters\".");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		 Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE EAN IS NOT NULL AND EAN IN (SELECT EAN FROM T_EMPLOYER te WHERE employer_id IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION ttpcva WHERE DESIGNATION_TYPE = 'UIM' ))","EAN");
	     String eanValue = databaseEanResult.get("EAN");
	     System.out.println(eanValue);
	     EmployerRegisterPage Erpage = new EmployerRegisterPage(driver);
	     HomePage HP = new HomePage(driver);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	     commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Employer Account Maintenance");
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.ScrollMenu("Add or Remove POA/TPR Association");
		 commonFuntions.clickMenu("Add or Remove POA/TPR Association");	
		 sleep();
		 commonFuntions.screenShot("Add or Remove POA/TPR Association – Enter ERN","Pass","Add or Remove POA/TPR Association – Enter ERN is launched");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Employer Registration Number","Pass","Blank ERN Employer Registration Number");
		 commonFuntions.enterTextboxContains("Employer Registration Number", "0000000");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Employer Registration Number","Pass","Message display 'The Employer Registration Number(ERN) provided does not exist in the system'.");
		 commonFuntions.enterTextboxContains("Employer Registration Number", eanValue); 
		 commonFuntions.screenShot("Entered Employer Registration Number","Pass","Entered Employer Registration Number");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Add or Remove Third Party Association to Employer","Pass","Add or Remove Third Party Association to Employer Screen Launched");
		 commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		 commonFuntions.selectRadio("Select");
		 Erpage.Select_date_from_calender.sendKeys("17/03/2023");
		 HP.commentBox.sendKeys("for testing");
		 commonFuntions.selectCheckbox("Additional authorization: Representative is also authorized to receive disclosures of, and review and inspect confidential Federal tax information and to perform any and all acts that I (we) can perform with respect to those tax matters as they bear on Unemployment Insurance matters. Note:  Confidential Federal Tax information shall include any and all information provided to the Department of Internal Revenue Service.");
		 commonFuntions.screenShot("Add or Remove Third Party Association to Employer","Pass","Entered Add or Remove Third Party Association to Employer Details");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep();
		 commonFuntions.screenShot("Add or Remove POA/Third Party Representative Association to Employer Confirmation","Pass","Add or Remove POA/Third Party Representative Association to Employer Confirmation Screen Launched");
		 commonFuntions.clickButtonContains("Home ");
		 sleep(2000);
         commonFuntions.clickMenu("Menu");
		 commonFuntions.clickMenu("Inquiry");
		 commonFuntions.clickMenu("Contribution Inquiry");
		 commonFuntions.clickMenu("Inquiry Employer Account");	
		 sleep();
		 commonFuntions.screenShot("Inquiry Employer Account - Enter ERN","Pass","Inquiry Employer Account - Enter ERN Screen Launched");
		 commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		 commonFuntions.screenShot("Entered Inquiry Employer Account - Enter ERN","Pass","Entered Inquiry Employer Account - Enter ERN");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Inquiry Employer Account Information","Pass","Inquiry Employer Account Information Screen Launched");
		 commonFuntions.clickHyperlink(" Joint Account Details ");
		 commonFuntions.selectDropdown("Source", " Correspondence/Email ");
		 commonFuntions.selectDropdown("Source Type", " Correspondence/Email ");
		 commonFuntions.enterTextboxContains("Joint Account Dissolution Date", "17/03/2023");
		 HP.commentBox.sendKeys("for testing");
		 commonFuntions.selectCheckbox("As a member and Administrator of this Joint Account, I acknowledge that prior notice was given to all members regarding this request.");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep(2000);
		 }
	  
}