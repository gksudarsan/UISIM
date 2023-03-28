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
public class EL_03_14 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.03.14 - Verify CSR can Add manually New Clients details for PEO Exempt Information by using Upload Client list / Add New Clients Hyperlink", groups = { COMMON_CONSTANT.REGRESSION })
	public void Test_EL_03_14() throws Exception {
		
		test = report.createTest("EL.03.14 - Verify CSR can Add manually New Clients details for PEO Exempt Information by using Upload Client list / Add New Clients Hyperlink");
		
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
		
		commonFunctions.clickOnLink(" UPLOAD CLIENT LIST | ADD NEW CLIENTS");
		commonFunctions.screenShot("UploadClientListPage", "Pass", "Upload CLient List Page dislaying");
		sleep();
		
		//step 6 ends here
		
		/* manual failure at step 7 */
		commonFunctions.screenShot(COMMON_CONSTANT.FAILURE, "Fail" , COMMON_CONSTANT.WRONG_PAGE_DISPALY);
		
	
	}

}
