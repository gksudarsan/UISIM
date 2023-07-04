//------------------------Palak---------------------------------------

package com.employerContibution.CA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class CA_455_07 extends TestBase {
	@Test
	public void CA_455_07() throws Exception
	{
		
		 test = report.createTest("CA.455.007 - Verify CSR can Calculate interest with enter ERN and Receivable Type 'Audit'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 CaPage CaPage = PageFactory.initElements(driver, CaPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("menu");
		 sleep(2000);
		 commonFuntions.clickMenu("Inquiry");
		 commonFuntions.clickMenu("Contribution Inquiry");	
		 
		 commonFuntions.ScrollMenu("Calculate Interest");
		 commonFuntions.screenShot("Menu","Pass","Calculate Interest");
		 commonFuntions.clickMenu("Calculate Interest");	
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("CalculateInterest","Pass","Calculate Interest Screen");
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER te WHERE EAN = '0463015' ORDER BY UPDATED_TS","EAN");
			String ernNumber=databaseResults.get("EAN");
			commonFuntions.enterTextboxContains("Employer Registration Number", ernNumber);
			sleep(2000);
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			 commonFuntions.screenShot("AfterSearch","Pass","After Search");
			CaPage.dueDateFromTable.click();
			 commonFuntions.screenShot("ValueRetrieved","Pass","Values Retrieved");
			 commonFuntions.clickButtonContains(" Calculate ");
				sleep(2000);
				commonFuntions.waitForLoadingIconToDisappear();
				String interestAmountValue=commonFuntions.retrieveTextboxContains("Interest Amount");
				commonFuntions.screenShot("interestAmount","Pass","Interest Amount");
				Assert.assertEquals("0.00", interestAmountValue);
				
			
	}

}
