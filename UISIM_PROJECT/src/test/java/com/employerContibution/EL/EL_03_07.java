package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_03_07 extends TestBase{

	@Test(priority=1, description = "EL.03.07:Verify CSR can upload document for PEO Exempt Information by using Upload document hyperlink ",groups = {"Regression"})
	public void EL_03_07() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		test = report.createTest("EL.03.07:Verify CSR can upload document for PEO Exempt Information by using Upload document hyperlink ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(2000);
//		 commonFuntions.enterTextbox("PEO Name", peoName);
//		 commonFuntions.clickOnLink(" ADVANCED SEARCH");
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT FEIN FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' AND EMPLOYER_REGISTRATION_NUMBER IS NOT NULL","FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is: " + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(4000);
		commonFuntions.selectRadioWithFeinValue(feinValue);
		commonFuntions.screenShot("Search PEO", "PASS", "Searching PEO");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("PEO Status", " With Drawn ");
		commonFuntions.clickButtonContains("UPDATE");
		Thread.sleep(2000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(2000);
		commonFuntions.screenShot("Exempt PEO", "Pass", "Manage Exempt PEO");
		Assert.assertEquals(PEOPage.updatePeoMessage.getText(), "PEO Status Updated Successfully");
		System.out.println(PEOPage.updatePeoMessage.getText());
		Thread.sleep(2000);
		commonFuntions.clickOnLink("PEO INQUIRY INFORMATION |");
		Thread.sleep(2000);
		commonFuntions.screenShot("PEO Inquiry Information", "Pass", "PEO Inquiry Information");
		Assert.assertEquals(PEOPage.withdrawnMessage.getText(), "Withdrawn");
		System.out.println(PEOPage.withdrawnMessage.getText());
		Thread.sleep(2000);
		commonFuntions.clickButtonContains(" MANAGE PEO ");
		commonFuntions.clickButton(" Home ");
		Thread.sleep(2000);
	
		// test case ends here...
	}
}