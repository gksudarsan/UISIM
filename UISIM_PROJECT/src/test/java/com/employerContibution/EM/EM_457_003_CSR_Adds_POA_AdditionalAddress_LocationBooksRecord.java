package com.employerContibution.EM;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_457_003_CSR_Adds_POA_AdditionalAddress_LocationBooksRecord extends TestBase {
	
	@Test(priority=1, description = "Verify CSR is able to add POA/TPR details for additional address Form Type 'Location of Books and Records'",groups = {"Regression"})
	public void TC_EM_457_003() throws Exception
	{
		
		test = report.createTest("EM.457.003 : Verify CSR is able to add POA/TPR details for additional address Form Type 'Location of Books and Records'");
		
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(12, 12))), 11);
		System.out.println("The generated FEIN is " + feinValue);
		
		String generatedNo = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 8);
		String ptin = "P" + generatedNo;
		System.out.println("The PTIN is " + ptin );
		
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// --- Navigation ---
		commonFuntions.waitForLoadingIconToDisappear();
		empManage.menu.click();		
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		sleep();
		commonFuntions.ScrollMenu("Add Power of Attorney/Third Party Representative");
		commonFuntions.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Add Power of Attorney/Third Party Representative");
		commonFuntions.clickMenu("Add Power of Attorney/Third Party Representative");
		
		// --- SREG-084 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM457003", "Pass", "Successful launch to Add Power of Attorney/Third Party Representative(SREG-084) page");
		commonFuntions.enterTextboxContains("POA/TPR Name", "TestPoaTpr");
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		commonFuntions.enterTextboxContains("PTIN", ptin);
		sleep(2000);
		commonFuntions.screenShot("EM457003", "Pass", "Details entered in SREG-084 page");
		
		commonFuntions.ScrollMenu("Mailing Address ");
		commonFuntions.enterTextboxContains("Mailing Address ", "D'Agostino");
		commonFuntions.enterTextboxContains("Address Line 1 ", "110 W 3rd St");
		commonFuntions.enterTextboxContains("City ", "NewYork");
		commonFuntions.selectDropdown("State", " New York ");
		sleep(2000);
		commonFuntions.screenShot("EM457003", "Pass", "Details entered in SREG-084 page");
		
		commonFuntions.enterTextboxContains("Zip Code", "10012");
		commonFuntions.enterTextboxContains("E-Mail Address", "test123@tmail.com");
		commonFuntions.enterTextboxContains("First Name", "Antonio");
		commonFuntions.enterTextboxContains("Last Name", "Gonzalez");
		sleep(2000);
		commonFuntions.screenShot("EM457003", "Pass", "Details entered in SREG-084 page");
		
		sleep(2000);
		commonFuntions.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFuntions.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFuntions.screenShot("EM457003", "Pass", "Sample document uploaded");
 		
 		sleep();
 		commonFuntions.clickOnLinkAnchorTag(" Add Additional Address ");
 		
 		// ---SREG-085---
 		commonFuntions.waitForLoadingIconToDisappear();
 		commonFuntions.screenShot("EM457003", "Pass", "Successful launch to Add Additional Addreses(SREG-085) page");
 		commonFuntions.selectDropdown("Form Type", " Location of Books and Records ");
 		commonFuntions.enterTextboxContains("First Name", "Javed");
 		commonFuntions.enterTextboxContains("Last Name", "Heyman");
 		commonFuntions.enterTextboxContains(" Contact Number ", "4567829115");
 		sleep(2000);
 		commonFuntions.screenShot("EM457003", "Pass", "Details entered in SREG-085 page");
 		commonFuntions.enterTextboxContains("Additional Address ", "testPoaUser");
 		commonFuntions.enterTextboxContains("Address Line 1 ", "853 Broadway");
 		commonFuntions.enterTextboxContains("City ", "NewYork");
 		commonFuntions.selectDropdown("State", " New York ");
 		commonFuntions.enterTextboxContains("Zip Code", "10003");
 		sleep(2000);
 		commonFuntions.screenShot("EM457003", "Pass", "Details entered in SREG-085 page");
 		commonFuntions.clickButton("Continue ");
 		
 		sleep(2000);
 		try {
 			empManage.additionalAddressInnerRdio.click();
 		} catch(Exception exception) {
 			empManage.additionalAddressOuterRdio.click();
 		}
 		commonFuntions.screenShot("EM457003", "Pass", "Selected Address from pop up");
 		empManage.cin999continueButton.click();
 		
 		sleep(2000);
 		commonFuntions.screenShot("EM457003", "Pass", "Added addresses in SREG-085 page");
 		commonFuntions.clickButton("Submit ");
 		
 		// --- SUC-002 ---
 		commonFuntions.waitForLoadingIconToDisappear();
 		commonFuntions.screenShot("EM457003", "Pass", "Successfully launched POA/Third Party Representative Conformation(SUC-002) page");
 		commonFuntions.clickButton("Home ");
 		
 		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("EM457003", "Pass", "TC EM.475.003 passed successfully");
		
		System.out.println("pass :)");
	}

}
