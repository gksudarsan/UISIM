package com.employerContibution.EL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_462_09 extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.462.09 - Verify CSR can Delete PEO members if PEO Group exist more than one PEO member.", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_462_09() throws Exception {
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EL.462.09 - Verify CSR can Delete PEO members if PEO Group exist more than one PEO member.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_2, COMMON_CONSTANT.CSR_USER_2_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFunctions.screenShot("Menu", "Pass", "Manage PEO");
		driver.findElement(By.xpath(".//span[@id='ProfessionalEmployerOrganization(PEO)']//following::*[.='Manage PEO'][1]")).click();
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
		
		//manual step 5 ends here
		
		/* manual step 6 failed */
		
		commonFunctions.screenShot("ManageGroupPeoPage", "Fail", COMMON_CONSTANT.MANUAL_FAILURE_AT_THIS_STEP);
	}

}
