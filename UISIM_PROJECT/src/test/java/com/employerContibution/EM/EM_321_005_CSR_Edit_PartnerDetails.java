package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_005_CSR_Edit_PartnerDetails extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to Edit Partner Details.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_321_005() throws Exception {
		
		test = report.createTest("Verify CSR is able to Edit Partner Details.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for Org Type -> SPRI
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE IN('PART','LIMP','JVNT') AND EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE IN('PART','LIMP','JVNT') AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The SSN generated is " + ssnValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Maintain Business Ownership");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Maintain Business Ownership");
		commonFunction.clickMenu("Maintain Business Ownership");
		
		// --- SREG-029 ---
		commonFunction.screenShot("MenuNavigation", "Pass", "Successful launch to Maintain Business Ownership Details - Enter ERN(SREG-029) Page");
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //5434567
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-704 ---		
		commonFunction.screenShot("EM321005", "Pass", "Successfully launched to SREG-704 page");
		commonFunction.ScrollMenu("Add Partner Details");
		sleep();
		commonFunction.screenShot("EM321005", "Pass", "Click on 'Add Partner Details' hyperlink");
		commonFunction.clickOnLink("Add Partner Details");
		
		// --- SREG-703 ---
		commonFunction.screenShot("EM321005", "Pass", "Successful launch to SREG-703 page");
		commonFunction.selectRadio(" Individual");
		sleep();
		commonFunction.enterTextboxContains("SSN", ssnValue);
		commonFunction.enterTextboxContains("First Name ", "Robert");
		commonFunction.enterTextboxContains("Last Name ", "Clive");
		commonFunction.selectDropdownEquals("Title ", " Partner");
		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "10012");
		commonFunction.screenShot("EM321005", "Pass", "Entered Details in SREG-703 page");
		/*
		 * empManage.sourceId_SREG705.click(); sleep();
		 * empManage.nys100Paper_SREG705.click();
		 * commonFunction.selectDropdownEquals("Source Type", " NYS-100 ");
		 * commonFunction.screenShot("EM321005", "Pass",
		 * "Entered Details in SREG-705 page");
		 * commonFunction.clickButtonContains("Submit ");
		 * 
		 * sleep(); try { empManage.uspsBusinessAddress.click(); } catch (Exception
		 * exception) { empManage.uspsBusinessAddressInnerCircle.click(); }
		 * 
		 * commonFunction.screenShot("EM321005", "Pass",
		 * "USPS Business address selection on SREG-006"); try {
		 * empManage.continueButton_popUp.click(); } catch(Exception exception) {}
		 * 
		 * // --- SREG-704 --- commonFunction.screenShot("EM321005", "Pass",
		 * "Successful launch to SREG-704 page");
		 * commonFunction.clickOnLinkAnchorTag("Edit");
		 * 
		 * // --- SREG-703 --- commonFunction.screenShot("EM321005", "Pass",
		 * "Successful launch to SREG-703 page");
		 * commonFunction.enterTextboxContains("Address Line 1 ", "");
		 * commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		 * commonFunction.enterTextboxContains("City ", "");
		 * commonFunction.enterTextboxContains("City ", "New York");
		 * commonFunction.selectDropdown("State", " New York ");
		 * commonFunction.enterTextboxContains("Zip Code", "");
		 * commonFunction.enterTextboxContains("Zip Code", "10012");
		 * commonFunction.screenShot("EM321005", "Pass",
		 * "Entered Details in SREG-703 page"); empManage.sourceId_SREG705.click();
		 * sleep(); empManage.nys100Paper_SREG705.click();
		 */
		commonFunction.selectDropdownEquals("Source Type", " NYS-100 ");
		commonFunction.screenShot("EM321005", "Pass", "Entered Details in SREG-705 page");
		commonFunction.clickButtonContains("Submit ");
		
		//
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_321_005 passed succesfully");
		
		System.out.println("pass");
	}

}
