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
public class EL_03_12_Verify_PEO_Admin_can_Manage_Additional_Address_Details_for_PEO_Exempt_Information_by_using_Manage_Additional_Address_Details_hyperlink extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Review and Update other PEO details  for PEO Exempt Information by using Review and Update other PEO details hyperlink ", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_03_12() throws Exception {

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EL.03.12 -Verify CSR can Review and Update other PEO details  for PEO Exempt Information by using Review and Update other PEO details hyperlink ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		commonFunctions.clickMenu("menu");
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
		
//		sleep(2000);
//		commonFunctions.screenShot("PeoSearchPage", "Fail", "Expected search result not coming");
		
		commonFunctions.selectRadioInTable("400166513", 1, 1, "Search for PEO");
		//driver.findElement(By.xpath("//input[@id='dataTableId_selectedPeoId_1_1_radio_button-input']")).click();
		sleep();
		commonFunctions.clickButtonContains("Continue");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Group PEO Details");
		
		commonFunctions.clickOnLink("Manage Addresses | ");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Click on Manage PEO");
		sleep();
		
		// --- PEO - 003 ---
		sleep(2000);
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Successful launch to PEO-003 page");
		PEOPage.address0_address1_PEO003.sendKeys("181 Mercer Street");
		PEOPage.address0_city_PEO003.sendKeys("New York");
		PEOPage.address0_zip_PEO003.sendKeys("10012");
		PEOPage.phone0_PEO003.sendKeys("2332442465");
		PEOPage.email0_PEO003.sendKeys("test123@gamil.com");
		commonFunctions.clickButtonContains("Save & Continue");
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Click on Save & Continue");
		sleep();
		
		PEOPage.uspsAddress.click();
	    PEOPage.currentAdditionalAddress.click();
	    commonFunctions.screenShot("UspsAddress","Pass","UspsAddress");
	    PEOPage.UspsContinueButton.click();
	    sleep();
		
		commonFunctions.screenShot(COMMON_CONSTANT.SUCCESS, "Pass" ,COMMON_CONSTANT.SUCCESS_PAGE);

		
		
		//inprogress
	}

}
