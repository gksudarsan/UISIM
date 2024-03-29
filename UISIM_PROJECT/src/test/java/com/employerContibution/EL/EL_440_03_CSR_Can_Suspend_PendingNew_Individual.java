package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_440_03_CSR_Can_Suspend_PendingNew_Individual extends TestBase {

	@Test
	public void EL_440_03() throws Exception {
		
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='PNDN' AND TYPE_OF_REQUEST='PEOIR' ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is" + feinValue);
		
		test = report.createTest("EL.440.03- Verify CSR can update PEO Status 'Suspend' for PEO Individual Information");
		
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Manage PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("(FEIN)", feinValue);
//		commonFuntions.enterTextboxContains("(FEIN)", "786458765");
		
		test.log(Status.INFO, "FEIN : : "+feinValue);
		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioWithFeinValue(feinValue);
		Thread.sleep(2000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(4000);
		commonFuntions.screenShot("DropDownValue", "Pass", "Selecting the WithDraw dropdown value");
		Thread.sleep(1000);
		commonFuntions.ScrollMenu("Client List");
		commonFuntions.screenShot("ClientTable", "Pass", "Client table");
		PEOPage.browserLinkManagePEOPage.click();
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		Assert.assertEquals(PEOPage.uploadeDocManagePEOPage.getText(), "Uploaded Documents");
		commonFuntions.ScrollMenu("Uploaded Documents");
		commonFuntions.screenShot("uploadedDocTable", "Pass", "Searching with FEIN ");
		commonFuntions.clickOnLink("Remove");
		Thread.sleep(3000);
		commonFuntions.clickButtonContains(" Yes ");
		Thread.sleep(3000);
		commonFuntions.ScrollMenu("Client List");
		commonFuntions.screenShot("ClientTable", "Pass", "Client table");
		commonFuntions.selectDropdown("PEO Status ", " Suspended ");	
		Thread.sleep(1000);
		commonFuntions.screenShot("DropDownValueSelected", "Pass", "Selected the Drop Down Valueas :: Suspended ");
		commonFuntions.clickButtonContains("UPDATE ");
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
