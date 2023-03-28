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
public class EL_03_11 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.03.11 - Verify CSR can Manage PEO Additional Address Details for PEO Exempt Information by using Manage Additional Address Details hyperlink", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_03_11() throws Exception {

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EL.03.11 - Verify CSR can Manage PEO Additional Address Details for PEO Exempt Information by using Manage Additional Address Details hyperlink");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
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
		
		commonFunctions.clickOnLink("Manage Addresses | ");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Click on Manage PEO");
		sleep();
		
		commonFunctions.clickButtonContains("Save & Continue");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Click on Save & Continue");
		sleep();
		
		PEOPage.uspsAddress.click();
	    PEOPage.currentAdditionalAddress.click();
	    commonFunctions.screenShot("UspsAddress","Pass","UspsAddress");
	    PEOPage.UspsContinueButton.click();
	    sleep();
		
		commonFunctions.screenShot(COMMON_CONSTANT.SUCCESS, "Pass" ,COMMON_CONSTANT.SUCCESS_PAGE);

	}

}
