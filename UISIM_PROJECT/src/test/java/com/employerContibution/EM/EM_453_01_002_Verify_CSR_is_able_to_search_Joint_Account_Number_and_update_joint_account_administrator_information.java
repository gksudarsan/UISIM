package com.employerContibution.EM;

import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_507;
import com.ui.pages.SREG_EM_mod;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;
@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_453_01_002_Verify_CSR_is_able_to_search_Joint_Account_Number_and_update_joint_account_administrator_information extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EM_453_01_002_Verify_CSR_is_able_to_search_Joint_Account_Number_and_update_joint_account_administrator_information", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EM_453_01_002() throws Exception {

		test = report.createTest(
				"EM_453_01_002_Verify_CSR_is_able_to_search_Joint_Account_Number_and_update_joint_account_administrator_information");
		
		commonStepDefinitions cf = new commonStepDefinitions();
		EM_005 em = new EM_005(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		PEOPage peoPage = new PEOPage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		/*
		// ----DB-----
		 Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
	                "Select  JOINT_ACCOUNT_NUMBER,* From T_TX_EMPLOYER_RELATIONSHIP_ACCOUNT where account_status = 'ACTV' and end_date is null and RELATIONSHIP_ROLE = 'JAA' or RELATIONSHIP_ROLE = 'JAP' and RELATIONSHIP_END_REASON IS NULL", "EAN");
	        
				String eanNumber = databaseResults.get("EAN");
				// System.out.println("The EAN is:" +eanNumber);

				if ((eanNumber == null) || eanNumber.isEmpty()) {
					System.out.println("EAN value is null");
				} else {
					test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
				}
		*/
		//----Login 
		cf.login(COMMON_CONSTANT.LND_Clerical_Supervisor.toUpperCase(), COMMON_CONSTANT.LND_Clerical_Supervisor_Password);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		//----Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Joint Account");
		sleep();
		cf.screenShot("Menu", "Pass", "Selected Menu");
		cf.clickMenu("Maintain Joint Account");
	    //----SREG-492
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Search For Joint Account", "Pass", "Launched to SREG-492");
		//cf.enterTextboxContains("Joint Account Number", eanNumber);
		cf.enterTextboxContains("Joint Account Number", "9500041");
		cf.screenShot("Search For Joint Account", "Pass", "Entered Joint Account number on SREG-492");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		//------SREG-493
		cf.screenShot("List of Members of Joint Account", "Pass", "Launched to SREG-493");
		sleep(2000);
		cf.clickOnLinkAnchorTag("Maintain Joint Account Administrator");
		cf.waitForLoadingIconToDisappear();
		//------SREG-509
		cf.screenShot("Maintain Joint Account Administrator", "Pass", "Launched to SREG-509");
		cf.enterTextboxContains("Address Line 1 ", "36 East 8th Street");
		cf.enterTextboxContains("Zip Code", "10003");
		cf.screenShot("Maintain Joint Account Administrator", "Pass", "Updated details on SREG-509");
		cf.selectLink("Document", "Browse");
		Thread.sleep(2000);
		cf.uploadDoc("Sample.docx");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Maintain Joint Account Administrator", "Pass", "Entered details to SREG-509");
		cf.selectDropdown("Source", " NYS-100 (paper) ");
        sleep(2000);
        cf.selectDropdown("Source Type", " NYS-100 ");
        sleep(2000);
        cf.screenShot("Maintain Joint Account Administrator", "Pass", "Entered details to SREG-509");
        cf.clickButton("Submit ");
        cf.waitForLoadingIconToDisappear();
		
      //------SREG-493
      		cf.screenShot("List of Members of Joint Account", "Pass", "Launched to SREG-493");
      		sleep(2000);
      		cf.selectDropdown("Source", " NYS-100 (paper) ");
            sleep(2000);
            cf.selectDropdown("Source Type", " NYS-100 ");
            sleep(2000);
            cf.screenShot("List of Members of Joint Account", "Pass", "Entered details on SREG-493");
        sreg.JointAccountRadio.click();
        sleep(1000);
        cf.enterFutureDate("Joint Account Dissolution Date", 300);
        cf.enterFutureDate("Joint Account Dissolution Request Date",300);
        cf.enterCommentBoxContains("Dissolve the account");
        cf.selectCheckbox("As a member and Administrator of this");
        sleep(1000);
        cf.screenShot("List of Members of Joint Account", "Pass", "Entered details on SREG-493");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
        //-----SUC-002
		cf.screenShot("Maintain Joint Account Confirmation", "Pass", "Launched to SUC-002");
		//cf.Label("Your Request is under review.");
		//-----HOme
		cf.clickButtonContains("Home ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		test.info("Step: Home : TC Passed-- ");
		
		
		
		
		
		
		
		
		

	}

}
