package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

public class EL_462_06 extends TestBase{

	@Test()
	public void EL_462_06_() throws Exception {
		
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='PNDN' AND TYPE_OF_REQUEST='PEOGR'", "FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is" + feinValue);
		
		test = report.createTest("EL.462.06 - Verify CSR can update PEO Status 'Withdrawn'  for PEO Group Information");
		
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Manage PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("(FEIN)", feinValue);
		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(2000);
		commonFuntions.selectRadioWithFeinValue(feinValue);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(4000);
		commonFuntions.selectDropdown("PEO Status ", " With Drawn ");
		commonFuntions.screenShot("DropDownValue", "Pass", "Selecting the WithDraw dropdown value");
		commonFuntions.ScrollMenu("Client List");
		commonFuntions.screenShot("ClientTable", "Pass", "Client table");
		commonFuntions.clickButtonContains("UPDATE ");
		Thread.sleep(3000);
		commonFuntions.screenShot("PopUpBox", "Pass", "Pop up warning verified");
		Thread.sleep(1000);
		commonFuntions.clickButtonContains(" Yes ");
		Thread.sleep(3000);
		commonFuntions.screenShot("peoInquiry", "Pass", "Clicked on Peo Inquiry");
		commonFuntions.clickOnLink("PEO INQUIRY INFORMATION |");
		Thread.sleep(2000);
		commonFuntions.screenShot("peoInquiryPage", "Pass", "PEO Inquiry page verified");
		String feinOnInquiry =  PEOPage.feinValue.getText();
		String feinTrimmed =  feinOnInquiry.replace("-", "");
		Assert.assertEquals(feinTrimmed , feinValue );
		commonFuntions.clickButton("Close ");
		test.log(Status.PASS, "Clicked on close button");
		
		
		
		
	}
}
