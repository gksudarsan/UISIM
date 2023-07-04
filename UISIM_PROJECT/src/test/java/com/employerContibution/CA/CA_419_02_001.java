//------------------------Palak---------------------------------------

package com.employerContibution.CA;

import static org.testng.Assert.assertTrue;

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

public class CA_419_02_001 extends TestBase {
	@Test
	public void CA_419_02_001() throws Exception
	{
		
		 test = report.createTest("CA.419.02.001 - Verify CSR can enter ERN and view the payment summary for the employer");
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
		 
		 commonFuntions.ScrollMenu("Employer Payments Inquiry");
		 commonFuntions.screenShot("Menu","Pass","Calculate Interest");
		 commonFuntions.clickMenu("Employer Payments Inquiry");	
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("EmployerPayments","Pass","Employer Payments Inquiry Screen");
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER te WHERE EAN = '0463015' ORDER BY UPDATED_TS","EAN");
			String ernNumber=databaseResults.get("EAN");
			commonFuntions.enterTextboxContains("Employer Registration Number", ernNumber);
			sleep(2000);
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			 commonFuntions.screenShot("EmployerPaymentsInfo","Pass","Employer Payments Information");
			 CaPage.paymentNumberLink.click();
			 sleep(2000);
				commonFuntions.waitForLoadingIconToDisappear();
				boolean actualResult;
				String actualValue=commonFuntions.retrieveLabelContains("Payment Number");
				if(actualValue == null || actualValue.length() == 0) {actualResult = false;}
				else {actualResult=true;}
				Assert.assertTrue(actualResult, "Payment Number is populated");
				actualValue=commonFuntions.retrieveLabelContains("Paid Date");
				if(actualValue == null || actualValue.length() == 0) {actualResult = false;}
				else {actualResult=true;}
				Assert.assertTrue(actualResult, "Paid Date is populated");
				 commonFuntions.screenShot("EmployerPaymentsDetails","Pass","Employer Payments Details");
				 CaPage.currentPaymentAllocationDetailsLink.click();
				 sleep(2000);
					commonFuntions.waitForLoadingIconToDisappear();
					 commonFuntions.screenShot("AppliedPaymentsDetails","Pass","Applied Payments Details");
					 commonFuntions.clickButtonContains("Close");
					 sleep(2000);
						commonFuntions.waitForLoadingIconToDisappear();
						commonFuntions.screenShot("PaymentsDetails","Pass","Payments Details");
						commonFuntions.matLabel("* If the amount shown is different from the Payment Amount ($) in header, the difference is due to an remittance adjustment transaction.");
	}

}
