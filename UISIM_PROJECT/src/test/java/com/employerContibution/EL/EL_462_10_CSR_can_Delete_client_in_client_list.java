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
public class EL_462_10_CSR_can_Delete_client_in_client_list extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.462.10 - Verify CSR can Delete client in client list.", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_462_10() throws Exception{

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EL.462.10 - Verify CSR can Delete client in client list.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_2, COMMON_CONSTANT.CSR_USER_2_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFunctions.screenShot("Menu", "Pass", "Manage PEO");
		driver.findElement(By.xpath(".//span[@id='ProfessionalEmployerOrganization(PEO)']//following::*[.='Manage PEO'][1]")).click();
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Search for PEO Page");
		
		commonFunctions.enterTextboxContains("PEO Name", "Auto");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched for Auto PEO");
		sleep();
		
		commonFunctions.clickButtonContains("Search");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched PEO List");
		
		commonFunctions.selectRadioInTable("400166513", 1, 1, "Search for PEO");
		//driver.findElement(By.xpath("//input[@id='dataTableId_selectedPeoId_1_1_radio_button-input']")).click();
		commonFunctions.clickButtonContains("Continue");
		sleep();
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Group PEO Details");
		commonFunctions.selectDropdown("PEO Conversion", " PEO Exempt to PEO Individual ");
	    commonFunctions.selectTableTypeList("123456789", 10, 1, "Client List");
	    commonFunctions.clickHyperlink("delete client");
		sleep();
		commonFunctions.screenShot("Confirmation Popup", "Pass", "Confirmation Popup");
		commonFunctions.clickButtonContains(" Yes ");
		commonFunctions.screenShot("AllPeoListPage", "Pass", "Deletion Successful");// fAIL At this step
		sleep();
		
		commonFunctions.screenShot(COMMON_CONSTANT.SUCCESS, "Pass", COMMON_CONSTANT.SUCCESS_PAGE);
	}

}
