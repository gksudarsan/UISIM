package com.employerContibution.EM;

import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_507;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_450_03_001_Verify_JAA_add_member_of_an_joint_account_CSR_reviews_and_Approve extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EM.450.03.001 - Verify JAA is able to add member of an joint account and the system create task for CSR reviews and Approve.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EM_450_03_001() throws Exception {

		test = report.createTest(
				"EM.450.03.001 - Verify JAA is able to add member of an joint account and the system create task for CSR reviews and Approve.");
		
		commonStepDefinitions cf = new commonStepDefinitions();
		EM_005 em = new EM_005(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		PEOPage peoPage = new PEOPage(driver);
		
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS",
				"EAN");
		String eanResult = databaseEanResult.get("EAN");
		System.out.println("EAN is " + eanResult);
		
		Map<String, String> databaseEntityNameResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = " + eanResult +" ORDER BY UPDATED_TS",
				"EAN");
		String entityNameResult = databaseEanResult.get("ENTITY_NAME");
		System.out.println("Entity Name is " + entityNameResult);

		cf.login(COMMON_CONSTANT.EMPLOYER_USER_7.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_7_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Joint Account");
		sleep();
		cf.clickMenu("Maintain Joint Account");
	
		cf.screenShot("List of Members of Joint Account", "Pass", "Launched to SREG-493");
		cf.clickOnLinkAnchorTag("Add Another Member");
		
		cf.screenShot("Add Member", "Pass", "Launched to SREG-400");
		cf.enterTextboxContains("Effective Start Date", "7/21/2023");
		cf.enterTextboxContains("Request Date", "7/21/2023");
		cf.enterTextboxContains("Legal Name of Business", entityNameResult);
		sleep();
		cf.enterTextboxContains("Employer Registration Number(ERN)", eanResult);
		sleep();
		cf.clickButtonContains("Continue ");
		sleep();
		cf.screenShot("List of Members of Joint Account", "Pass", "Launched to SREG-493");
		em.selectToggle.click();
		cf.enterCommentBoxContains("okay");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.screenShot("Maintain Joint Account Confirmation", "Pass", "Launched to SUC-002");
		cf.Label("Your Request is under review.");
		
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN = " + eanResult + " ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		
		cf.logoutAndLogin("ndsbb3", "Brijen@1234567");
		cf.screenShot("Application Login Page", "Pass", "Launched to Home");
		peoPage.queue.click();
		Thread.sleep(15000);
		cf.screenShot("Work Item Search", "Pass", " My Q");
		cf.enterTextboxContains("Work Item Description Free Text", "Review Add Joint");
		cf.clickButtonContains(" Search ");
		cf.clickOnLinkAnchorTag("Review Add Joint Account Member Task");
		cf.screenShot("Work Item Details", "Pass", "Launched to WF-091");
		cf.clickButtonContains("Open Work Item ");
		cf.screenShot("Add Joint Account Member Task", "Pass", "EMWI-005");
		cf.enterCommentBoxContains("ok");
		
		
		
		

	}

}
