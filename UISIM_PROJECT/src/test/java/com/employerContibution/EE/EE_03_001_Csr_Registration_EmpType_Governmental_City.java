package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_001_Csr_Registration_EmpType_Governmental_City extends TestBase{

	@Test()
	public void EE_03_001_Csr_Registration() throws Exception {

		String feinValue1 =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),5);
		String feinValue2 =  "9999"  ;
		String feinValue = feinValue2 + feinValue1 ;  
		System.out.println("FEIN NUMBER = " +feinValue);
		employerManagementLocators eml = new employerManagementLocators();
		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		//Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
		//		"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'", "FEIN");
		//String FEIN = databaseResults.get("FEIN");
		test = report.createTest("EE_03_001 -  Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review. ");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep(3000);
		cf.clickButtonContains("Continue"); sleep(2000);

		cf.selectDropdown("Employer Type", " Governmental ");
		cf.enterTextboxContains("(FEIN)", "546237282"); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," City "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		cf.selectDropdown("Source Type", " NYS-100G ");
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");

		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", "testing registration process");
		cf.clickButtonContains("Continue");sleep();

		cf.enterTextboxContains("Address Line 1","addr1");
		cf.enterTextboxContains("Address Line 2","addr2");
		cf.enterTextboxContains("City","New York");
		cf.enterTextboxContains("Zip Code","10025");
		cf.screenShot("", "Pass", "Employer Registration");

		cf.clickButtonContains("Continue");
		cf.screenShot("", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep();

		cf.selectRadio("Same as Primary Business Physical Address");
		eml.selectradio_locationofbooks().click();
		cf.clickButtonContains("Continue"); cf.clickButtonContains("Continue");

		eml.selectBrowse().click(); Thread.sleep(2000); cf.uploadDoc("Sample.docx");
		Thread.sleep(2000); cf.clickButtonContains("Continue");
		cf.clickButtonContains("Continue"); cf.selectCheckbox("I accept");
		cf.clickButtonContains("Submit"); cf.clickButtonContains("Exit");
		
		//Assigning user to WI Review emp type..................
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
	
		//Resolving WI Review emp type................
		PEOPage.queue.click(); Thread.sleep(15000);
		cf.enterTextboxContains("FEIN",feinValue);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search"); Thread.sleep(2000);
		cf.screenShot("Review emp type","Pass","emp type");
		cf.clickOnLink("Review Employer Type");

		Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("Review","Pass","Review Employer Type Task Details");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); Thread.sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");

		//Assigning user to WI Determine Liability Task.................
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		//Resolving WI Determine Liability Task.................
		PEOPage.queue.click();
		Thread.sleep(15000);
		//eml.searchWI().click();
		//eml.searchWI().sendKeys("Unable to Determine Liability Task");
		cf.screenShot("Search","Pass","Searchbyname");
		Thread.sleep(2000);
		cf.screenShot("Determine Liability Task","Pass","Determine Liability Task");
		cf.clickOnLink("Unable to Determine Liability Task");
		Thread.sleep(2000);
		cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("Review","Pass","Unable to Determine Liability Task");
		cf.selectDropdown("Account Status", "Liable");		
		cf.selectRadio("Contributory");
		cf.enterTextboxContains("Date covered employment began? ", "1212022");
		cf.populateListbox("Comment", "testing");
		cf.clickButtonContains("Submit");
		cf.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
		em.Inquery_fein(feinValue);
		test.log(Status.PASS, "Clicked on Home button");




	}
}