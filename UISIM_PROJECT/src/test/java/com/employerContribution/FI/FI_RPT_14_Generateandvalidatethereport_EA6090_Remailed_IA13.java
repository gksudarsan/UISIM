package com.employerContribution.FI;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_RPT_14_Generateandvalidatethereport_EA6090_Remailed_IA13 extends TestBase {

	@Test
	public void FI_RPT_14() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"'FI.RPT.14-Generate and validate the report_EA6090_Re-mailed IA13.2 and IA13.8 Notices");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.clickMenu("Inquiry");
		sleep();
		cf.clickMenu("Reports");
		cf.screenShot("navigatingToSearchReports", "Pass", "Navigating to search reports");
		cf.clickMenu("Search Reports");
		sleep(2000);
		
		//Search Reports
		cf.screenShot("searchReports", "Pass", "Search Reports");
		cf.selectDropdown("Report", " EA6090 - REMAILED IA13.2 AND IA13.8 NOTICES ");
		cf.enterPastDate("Start Date", 60);sleep();
		cf.enterCurrentDate("End Date");sleep();
		cf.screenShot("searchReports1", "Pass", "Search Reports Entered Details");
		cf.clickButtonContains(" Search ");
		sleep(3000);
		cf.screenShot("serachResults", "Pass", "Search Results");
		cf.clickOnLinkAnchorTag("REMAILED IA13.2 AND IA13.8 NOTICES");
		sleep(3000);
		//cf.clickButtonContains(" Home ");
		//sleep(5000);
		//cf.screenShot("homePage", "Pass", "Home Page");
		
		
	}

}
