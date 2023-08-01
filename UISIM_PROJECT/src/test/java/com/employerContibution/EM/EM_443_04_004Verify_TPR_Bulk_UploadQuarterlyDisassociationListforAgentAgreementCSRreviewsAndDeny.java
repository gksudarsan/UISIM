package com.employerContibution.EM;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_443_04_004Verify_TPR_Bulk_UploadQuarterlyDisassociationListforAgentAgreementCSRreviewsAndDeny
		extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.443.04.004 Verify TPR is able to bulk upload Quarterly disassociation list for Agent Agreement (AA) and AA Plus and the system create task for CSR reviews and Deny", groups = {
			"Regression" })
	public void EM_443_04_004() throws Exception {
		test = report.createTest(
				"EM.443.04.004 Verify TPR is able to bulk upload Quarterly disassociation list for Agent Agreement (AA) and AA Plus and the system create task for CSR reviews and Deny");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_504 sreg504 = new SREG_504(driver);
		PEOPage peoPage = new PEOPage(driver);
		employerManagementLocators empMgmt = new employerManagementLocators();

		// Login
		cf.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Employer Account Maintenance");
		sleep();
		cf.clickMenu("Bulk Association");
		sleep();
		cf.clickMenu("Upload List of Associations");
		sleep();
		cf.screenShot("Upload List of Associations", "Pass", "Launched to EM-001");

		cf.clickButtonContains("Choose File");
		Thread.sleep(2000);
		cf.uploadDoc("BulkAssociationTemplate.xls");
		Thread.sleep(2000);
		cf.screenShot("Upload List of Associations", "Pass", "Uploaded file");
		cf.clickButton("Submit ");
		cf.waitForLoadingIconToDisappear();
		
		String msg =  empMgmt.successMsgSuc002().getText();
		System.out.println(msg);
		cf.screenShot("Upload List of Associations", "Pass", "Launched to SUC-002");
		
		cf.clickButton("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home");
		
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='150241896' ORDER BY UPDATED_TS desc)");
		
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(3000);
		peoPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		

		System.out.println("Worked");
	}
}
