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
public class EL_03_13 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.03.13 - Verify CSR can Review and Update other PEO details  for PEO Exempt Information by using Review and Update other PEO details hyperlink", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_03_13() throws Exception {
		
		test = report.createTest("EL.03.13 - Verify CSR can Review and Update other PEO details  for PEO Exempt Information by using Review and Update other PEO details hyperlink");
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_2, COMMON_CONSTANT.CSR_USER_2_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFunctions.screenShot("Menu", "Pass", "Manage PEO");
		driver.findElement(By.xpath(".//span[@id='ProfessionalEmployerOrganization(PEO)']//following::*[.='Manage PEO'][1]")).click();
		sleep();
		
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
		
		commonFunctions.clickOnLink("REVIEW AND UPDATE OTHER PEO DETAILS | ");
		commonFunctions.screenShot("PeoDetailsReviewPage", "Pass", "PEO Details Review screen");
		sleep();
		
		
		//commonFunctions.clickButtonContains("Edit",3);
		//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]
		driver.findElement(By.xpath("//mat-label[contains(.,'Mailing Address')]//following::button[contains(.,' Edit ')][1]")).click();
		sleep();
		commonFunctions.screenShot("MailingAddressPage", "Pass", "Mailing Address verification details");
		
		commonFunctions.clickButtonContains("Previous");
		commonFunctions.screenShot("MailingAddressPage", "Pass", "Navigation to previous page");
		sleep();
		
		commonFunctions.clickButtonContains(" Yes ");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Manage PEO pag launch successful");
		sleep();
		
		commonFunctions.screenShot(COMMON_CONSTANT.SUCCESS, "Pass", COMMON_CONSTANT.SUCCESS_PAGE);
	}

}
