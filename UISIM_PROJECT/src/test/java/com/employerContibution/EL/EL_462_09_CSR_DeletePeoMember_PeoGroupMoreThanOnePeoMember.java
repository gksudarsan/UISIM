package com.employerContibution.EL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_462_09_CSR_DeletePeoMember_PeoGroupMoreThanOnePeoMember extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Delete PEO members if PEO Group exist more than one PEO member.", groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EL_462_09() throws Exception {
		
		test = report.createTest("EL.462.09 - Verify CSR can Delete PEO members if PEO Group exist more than one PEO member.");
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage employeeLeasing = new EmployerRegisterPage(driver);
		
		// --- Login ---
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		// --- Navigation ---
		commonFunctions.waitForLoadingIconToDisappear();
		employeeLeasing.menu.click();
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		peoPage.menuPeo.click();
		sleep(1500);
		commonFunctions.screenShot("Menu", "Pass", "Navigated to Menu -> Professional Employer Organization -> Manage PEO");
		peoPage.managePeoInMenu.click();
		
		
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Search for PEO Page");
		
		commonFunctions.enterTextboxContains("PEO Name", "Exempt");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched for Exempted PEO");
		sleep();
		
		commonFunctions.clickButtonContains("Search");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched PEO List");
		
		commonFunctions.selectRadioInTable("787876868", 1, 1, "Search for PEO");
		//driver.findElement(By.xpath("//input[@id='dataTableId_selectedPeoId_1_1_radio_button-input']")).click();
		sleep();
		
		commonFunctions.clickButtonContains("Continue");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Group PEO Details");

		
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "TC EL.462.09 passed successfully.");
	}

}
