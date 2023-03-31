package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_03_06 extends TestBase{

	@Test(priority=1, description = "EL.03.06 - Verify CSR can update PEO Status 'Withdrawn' for PEO Exempt Information",groups = {"Regression"})
	public void EL_03_06() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest("EL.03.06 - Verify CSR can update PEO Status 'Withdrawn' for PEO Exempt Information");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(2000);
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE TYPE_OF_REQUEST='PEOER' ORDER BY UPDATED_TS DESC", "PEO_NAME");
		String PEOName =databaseResults.get("PEO_NAME");
		commonFuntions.enterTextboxContains("PEO Name",PEOName);
		commonFuntions.screenShot("enter peo name", "Pass", "Peo Name");
		System.out.println("The PeoName is: "+PEOName);
		commonFuntions.clickButtonContains("Search ");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("Selecting the record", "Pass", "Selected.");
		commonFuntions.clickButtonContains("Continue");sleep();
		commonFuntions.selectDropdown("PEO Status ", " With Drawn ");
		commonFuntions.screenShot("Manage Peo Exempt", "Pass", "Manage Peo Exemept");
		//commonFuntions.selectDropdown("PEO Conversion", " PEO Exempt to PEO Individual ");
		commonFuntions.clickButton("UPDATE ");
		commonFuntions.screenShot("Select questions", "Pass", "Select as YES");
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
		commonFuntions.clickButtonContains("MANAGE PEO");
		//commonFuntions.clickButtonContains("Home");
		Thread.sleep(2000);
	
		// test case ends here...
		
		
	}
}
