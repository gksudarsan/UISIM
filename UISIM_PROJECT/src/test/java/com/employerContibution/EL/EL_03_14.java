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
		
		commonFunctions.selectRadioInTable("Issued", 1, 1, "Search for PEO");
		//driver.findElement(By.xpath("//input[@id='dataTableId_selectedPeoId_1_1_radio_button-input']")).click();
		sleep(2000);
		commonFunctions.selectLink("Document", " UPLOAD CLIENT LIST | ADD NEW CLIENTS");
	     sleep(2000);
	     commonFunctions.screenShot("UploadClientListPage", "Pass", "Upload CLient List Page dislaying");
			
	     sleep(2000);
	     commonFunctions.clickButtonContains("ADD CLIENT DETAILS MANUALLY");
	     sleep(2000);
	     commonFunctions.clickButtonContains("Search");
	     sleep(2000);
	     commonFunctions.errorContent("Please enter Employer Registration Number or Legal Name or Federal Employer Identification number (FEIN)");
	     commonFunctions.screenShot("verifyDefaultError","Pass","Verify Default Error");
	     
	     commonFunctions.enterTextboxContains("Employee Registration Number","43");
	     
	     commonFunctions.clickButtonContains("Search");
	     sleep(2000);
	     commonFunctions.errorContent("Invalid Employer Registration Number");
	     commonFunctions.screenShot("verifyErnError","Pass","Verify ERN Error");
	     
	     commonFunctions.enterTextboxContains("Employee Registration Number","");
	     
	     commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)","43");
	     
	     commonFunctions.clickButtonContains("Search");
	     sleep(2000);
	     commonFunctions.errorContent("Invalid Federal Employer Identification Number (FEIN)");
	     commonFunctions.screenShot("verifyFeinError","Pass","Verify FEIN Error");
		
		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)","");	     
	     commonFunctions.enterTextboxContains("Legal Name","test000000000");
	     sleep(2000);		commonFunctions.clickButtonContains("Client Not Found ");
		sleep(2000);
		commonFunctions.screenShot("ManageGroupPeoPage", "Pass", "Group PEO Details");
		
		
		
		
		//step 6 ends here
		
		/* manual failure at step 7 */
		commonFunctions.screenShot(COMMON_CONSTANT.FAILURE, "Fail" , COMMON_CONSTANT.WRONG_PAGE_DISPALY);
		
	
	}

}
