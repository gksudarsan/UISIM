package com.employerContribution.EOA;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIS_009;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_009_01_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_EFIN_No_TPRId
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process EFIN_No_TPRId", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_01_009_01() throws Exception {
		
		test = report.createTest(
				"EOA_01_009_01_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_EFIN_No_TPRId");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_009 fis = new FIS_009(driver);
		EOAPage eoaPage = new EOAPage(driver);
		
		//-----DB Query
		Map<String, String> databaseResult1 = commonFuntions.database_SelectQuerySingleColumn("SELECT COMPANY_NAME,EFIN,TPR_ID FROM  T_THIRD_PARTY_AGENT ttpa WHERE EFIN IS NOT NULL ORDER BY UPDATED_TS","EFIN");

		Map<String, String> databaseResult2 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a\n" + 
				"JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID \n" + 
				"JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID\n" + 
				"JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID\n" + 
				"JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID\n" + 
				"WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN",
				"ENTITY_NAME");
		
		/*Map<String, String> databaseResult3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT COMPANY_NAME,EFIN,TPR_ID FROM  T_THIRD_PARTY_AGENT ttpa WHERE EFIN IS NOT NULL ORDER BY UPDATED_TS",
				"TPR_ID");*/
		
		String efinNumber = databaseResult1.get("EFIN");
		System.out.println("The efinNumber is:" +efinNumber);
		
		String entityName = databaseResult2.get("ENTITY_NAME");
		System.out.println("The entityName is:" +entityName);
		
		//String tprid = databaseResult3.get("TPR_ID");
		//System.out.println("The uiNumber is:" +tprid);

		//-----Login

		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		
		//---Home - POPUP
		test.info("Step: 1 -- ");
		commonFuntions.screenShot("UI Services Account Creation", "Pass", "UI Services Account Creation Popup is displayed");
		sleep(1000);
		
		test.info("Step: 2 --  ");
		eoaPage.tprRepresentativeRadio.click();
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//-----SREG-612
		test.info("Step: 3 -- ");
		
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass", "Landed on SREG-612 screen is displayed");
		commonFuntions.enterTextboxContains("Electronic Filer Identification Number (EFIN)", efinNumber);
		//commonFuntions.enterTextboxContains(" TPR ID", tprid);
		commonFuntions.enterTextboxContains("Legal Name of Business",entityName);
		sleep(1000);
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass", "Entered information on SREG-612 ");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass", "Error displayed : TPR id is Required on SREG-612 ");
		
		
					test.info("EOA_01_009_01 -- TestCase Successfully Completed ");
					// -- completed by Palak
	}

}
