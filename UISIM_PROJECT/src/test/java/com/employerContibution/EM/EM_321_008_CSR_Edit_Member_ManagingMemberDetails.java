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

public class EM_321_008_CSR_Edit_Member_ManagingMemberDetails extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter the term end date and Inactivate Partner Details", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_321_005() throws Exception {
		
		test = report.createTest("Verify CSR is able to enter the term end date and Inactivate Partner Details");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		//Valid EAN for Org Type -> LLCA
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'LLCA' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'LLCA' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is " + eanValue);
		
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The SSN generated is " + ssnValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Maintain Business Ownership");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Maintain Business Ownership");
		commonFunction.clickMenu("Maintain Business Ownership");
		
		// --- SREG-029 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321008", "Pass", "Successful launch to Maintain Business Ownership Details - Enter ERN(SREG-029) Page");
		sleep(2000);
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //8511023
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-708 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321008", "Pass", "Successful launch to Member/Managing Member Details(SREG-708) Page");
		commonFunction.clickOnLink("Add Member/Managing Member Details");
		
		// --- SREG-707 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321008", "Pass", "Successful launch to Add Member/Managing Member(SREG-707) Page");
		commonFunction.selectRadio(" Business Entity");
		sleep();
		commonFunction.enterTextboxContains("Entity Name ", "Test ATTN");
		commonFunction.enterTextboxContains("Federal Identification Number (FEIN) ", "754375843");
		commonFunction.selectDropdownEquals("Title ", " Member");
		commonFunction.clickButtonContains("Submit ");
		
		sleep(3000);
		
		commonFunction.enterTextboxContains("Entity Name ", "");
		commonFunction.enterTextboxContains("Entity Name ", "Test Entity");
		commonFunction.enterTextboxContains("Federal Identification Number (FEIN) ", "");	
		commonFunction.enterTextboxContains("Federal Identification Number (FEIN) ", "5432664326");		
		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "10012");
		commonFunction.screenShot("EM321008", "Pass", "Entered Details in SREG-703 page");
		
		  empManage.sourceId_SREG705.click();
		  sleep();
		  empManage.nys100Paper_SREG705.click();
		  commonFunction.selectDropdownEquals("Source Type", " NYS-100 ");
		  commonFunction.screenShot("EM321008", "Pass", "Entered Details in SREG-705 page");
		  commonFunction.clickButtonContains("Submit ");
		  
		  sleep(); 
		  try { 
			  empManage.uspsBusinessAddress.click(); 
			  } catch (Exception exception) { 
				  empManage.uspsBusinessAddressInnerCircle.click(); 
			  }
		  
		  commonFunction.screenShot("EM321005", "Pass", "USPS Business address selection on SREG-006");
		  try {
			  empManage.continueButton_popUp.click(); 
			  } catch(Exception exception) {}
		  
		  // --- SREG-708 --- 
		  commonFunction.screenShot("EM321008", "Pass", "Successful launch to Member/Managing Member Details(SREG-708) Page");
		  commonFunction.clickOnLinkAnchorTag("inactive");
		  
		  // --- SREG-703 --- 
		  commonFunction.screenShot("EM321005", "Pass", "Successful launch to SREG-703 page");
		  commonFunction.enterTextboxContains("Entity Name ", "");
		  commonFunction.enterTextboxContains("Entity Name ", "Updated Test Entity");
		  commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St New");
		  commonFunction.screenShot("EM321005", "Pass", "Entered Details in SREG-703 page"); empManage.sourceId_SREG705.click();
		  sleep(); 
		  empManage.nys100Paper_SREG705.click();
		  commonFunction.selectDropdownEquals("Source Type", " NYS-100 ");
		  commonFunction.screenShot("EM321005", "Pass",  "Entered Details in SREG-705 page");
		  commonFunction.clickButtonContains("Submit ");
		  
		  sleep(); 
		  try { 
			  		empManage.uspsBusinessAddress.click(); 
			  } catch (Exception  exception) { 
				  	empManage.uspsBusinessAddressInnerCircle.click();
			  }
		  
		  commonFunction.screenShot("EM321005", "Pass", "USPS Business address selection on SREG-006"); 
		  try {
			  empManage.continueButton_popUp.click(); 
			  } catch(Exception exception) {}
		 
		
		// --- SREG-708 ---
		sleep(2000);
		commonFunction.screenShot("EM321008", "Pass", "Successful launch to Member/Managing Member Details(SREG-708) Page");
				
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_321_008 passed succesfully");
		
		System.out.println("pass :)");
	}

}
