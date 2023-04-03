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

public class EE_01_001_csr_registration_emptype_business_corporation extends TestBase{

	@Test()
	public void EE_03_001_csr_registration() throws Exception {

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

		cf.selectDropdown("Employer Type", " Business ");
		cf.enterTextboxContains("(FEIN)", "546237282"); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," Corporation "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
		cf.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep(2000);

		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", "testing registration process");sleep(2000);
		cf.clickButtonContains("Continue");sleep();

		cf.enterTextboxContains("7th Street 40 E 7th St","addr1");sleep(2000);
		cf.enterTextboxContains("","addr2");
		cf.enterTextboxContains("City","New York");sleep(2000);
		cf.enterTextboxContains("Zip Code","10003");sleep(2000);

		cf.screenShot("", "Pass", "Employer Registration");
		cf.clickButtonContains("Finish Later");sleep(2000);
		cf.clickButtonContains("Yes");sleep(2000);
		cf.clickButtonContains("Home");sleep(2000);

		//-- entering data in incomplete registration.............

		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Incomplete Registration"); sleep(3000);

		cf.enterTextbox("FEIN", "");
		cf.clickButtonContains("Search");sleep(2000);
		cf.clickOnLink("");sleep(2000);
		cf.clickButtonContains("Continue");sleep(2000);

		cf.clickButtonContains("Continue");sleep(2000);

		cf.clickButtonContains("Continue");sleep(2000);

		//cf.clickButtonContains("Continue");
		cf.screenShot("", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep();sleep(2000);

		cf.selectRadio("Same as Primary Business Physical Address");
		eml.selectradio_locationofbooks().click();sleep(2000);
		eml.selectradio_noticeofpotentialcharges().click();sleep(2000);
		cf.clickButtonContains("Continue"); sleep(2000);
		cf.clickButtonContains("Continue");sleep(2000);

		//failing at this step.................
		//Verify Registered employer in Inquery page 	...........
		em.Inquery_fein(feinValue);
		test.log(Status.PASS, "Clicked on Home button");




	}
}