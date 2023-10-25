package com.employerContribution.EOA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
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
public class EOA_02_006_VerifythatthesystemdisplaysanerrormessagewhenasecondBMAuserattemptstoaccesstheonlineaccountwhichdoesnotexistsinthenysdolsystem
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EOA_02_006_VerifythatthesystemdisplaysanerrormessagewhenasecondBMAuserattemptstoaccesstheonlineaccountwhichdoesnotexistsinthenysdolsystem", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_02_006() throws Exception {
		
		test = report.createTest(
				"EOA_02_006_VerifythatthesystemdisplaysanerrormessagewhenasecondBMAuserattemptstoaccesstheonlineaccountwhichdoesnotexistsinthenysdolsystem");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		
		EOAPage eoaPage = new EOAPage(driver);
		

		

		Map<String, String> databaseResult1 = commonFuntions.database_SelectQuerySingleColumn("SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a\n" + 
				"JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID \n" + 
				"JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID\n" + 
				"JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID\n" + 
				"JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID\n" + 
				"WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN"
				,
				"EAN");

		Map<String, String> databaseResult2 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a\n" + 
				"JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID \n" + 
				"JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID\n" + 
				"JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID\n" + 
				"JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID\n" + 
				"WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN",
				"ENTITY_NAME");
		
		Map<String, String> databaseResult3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a\n" + 
				"JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID \n" + 
				"JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID\n" + 
				"JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID\n" + 
				"JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID\n" + 
				"WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN",
				"NYBE_AGENCY_SPEC_APP_ID");
		
		String eanNumber = databaseResult1.get("EAN");
		System.out.println("The eanNumber is:" +eanNumber);
		
		String entityName = databaseResult2.get("ENTITY_NAME");
		System.out.println("The entityName is:" +entityName);
		
		String uiNumber = databaseResult3.get("NYBE_AGENCY_SPEC_APP_ID");
		System.out.println("The uiNumber is:" +uiNumber);
		
		
		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		commonFuntions.screenShot("UI Services Account Creation", "Pass", "UI Services Account Creation Popup is displayed");
		sleep(1000);
		
		test.info("Step: 2 -- ");
		eoaPage.employerRadio.click();
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass", "SREG-612 screen is displayed");
		

		//-----SREG-612
				test.info("Step: 3 -- ");
				
				commonFuntions.screenShot("UI Services Employer Account Creation", "Pass", "Landed on SREG-612 screen is displayed");
				commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanNumber);
				commonFuntions.enterTextboxContains("Legal Name",entityName);
				commonFuntions.enterTextboxContains("New York Business Express (NYBE) UI Application Number", uiNumber);
				sleep(1000);
				commonFuntions.selectDropdown("Quarter ", " 1 ");
				commonFuntions.selectDropdown("Year ", " 2024 ");
				commonFuntions.enterTextboxContains("Total Remuneration $","100000");
				commonFuntions.screenShot("UI Services Employer Account Creation", "Pass", "Entered information on SREG-612 ");
				commonFuntions.clickButton("Continue ");
				commonFuntions.waitForLoadingIconToDisappear();
				commonFuntions.screenShot("TC:  EOA_02_006", "Pass", "Test completed  ");
				// completed by Palak
		
		
	}

}
