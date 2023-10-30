package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_011_003_VerifyCSRAddClientToUserAddClientHyperlink extends TestBase {
	
	@Test
	public void  EOA_011_003_AddClientHyperlink() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EOAPage eoa = new EOAPage(driver);
		test = report.createTest(
				"Verify CSR can Add Client to a User (Add Client Hyperlink)");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Users");
		cf.clickMenu("Users");
		cf.screenShot("navigateToEmployerUsers", "Pass", "Navigating to employer users");
		cf.clickMenu("Employer Users");
		sleep(3000);
		
		//User Search -- SREG-533
		cf.screenShot("userSearch", "Pass", "User Search");
		eoa.tpr_radioButton.click();
		
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT b.FEIN AS SEARCHSCREENFEIN,\r\n" + 
				"b.COMPANY_NAME AS SEARCHSCREENLEGALNAME,\r\n" + 
				"E.EAN AS AddClientAssociation_ean,\r\n" + 
				"E.FEIN AS AddClientAssociation_Fein ,\r\n" + 
				"D.TRADE_NAME AS ClienttradeName,\r\n" + 
				"E.ENTITY_NAME AS AddClientAssociation_entityname\r\n" + 
				"FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION A\r\n" + 
				"JOIN T_THIRD_PARTY_AGENT B ON A.THIRD_PARTY_CDS_VENDOR_ID = B.THIRD_PARTY_AGENT_ID\r\n" + 
				"JOIN T_EMPLOYER C ON A.EMPLOYER_ID = C.EMPLOYER_ID\r\n" + 
				"JOIN T_REGULAR_EMPLOYER D ON C.EMPLOYER_ID = D.EMPLOYER_ID\r\n" + 
				"JOIN T_EMPLOYER_ACCOUNT E ON d.EMPLOYER_ACCOUNT_ID = E.EMPLOYER_ACCOUNT_ID\r\n" + 
				"WHERE A.ASSOCIATION_STATUS ='ACTIVE' AND A.THIRD_PARTY_CDS_VENDOR_START_DATE IS NOT NULL AND \r\n" + 
				"(A.THIRD_PARTY_CDS_VENDOR_END_DATE > CURRENT_DATE OR A.THIRD_PARTY_CDS_VENDOR_END_DATE IS NULL)\r\n" + 
				"AND A.DESIGNATION_TYPE IS NOT NULL;","ADDCLIENTASSOCIATION_EAN");
		String eanValue = databaseResults.get("ADDCLIENTASSOCIATION_EAN");
		System.out.println("ERN Number is :: " + eanValue);
		test.log(Status.INFO, "ERN No.::" + eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.screenShot("userSearchSelectTpr", "Pass", "User Search-Select TPR");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Add TPR User -- SREG-531
		cf.clickOnLinkAnchorTag(" ADD USER");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("AddTPRUser", "Pass", "Add TPR User");
		cf.enterTextboxContains("First Name", "Sachin");
		cf.enterTextboxContains("Last Name", "Tendulkar");
		cf.enterTextboxContains("Email Address","autoTest"+Long.toString(cf.createRandomInteger(10000,99999))+"@gmail.com");
		cf.enterTextboxContains(" Contact Number ",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
	    eoa.selectCheckBox_SameContactNumber.click();
	    String userId = "testsample" + Long.toString(cf.createRandomInteger(10,99));
	    cf.enterTextboxContains("User ID", userId);
	    String password = "Automation@"+Long.toString(cf.createRandomInteger(10000,99999));
	    System.out.println("The selected password is :" + password);
	    cf.enterTextboxContains("Temporary Password", password);
	    cf.enterTextboxContains("Confirm Temporary Password",password);
		cf.selectDropdown("User Types", " TPR Sub-User ");
		eoa.selectCheckBox_TprAllUiMatters.click();
		cf.screenShot("AddTPRUserEnteredDetails", "Pass", "Add TPR User Enter Details");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Navigating to User Search
		cf.clickMenu("Menu");
		cf.ScrollMenu("Users");
		cf.clickMenu("Users");
		cf.clickMenu("Employer Users");
		sleep(3000);
		eoa.tpr_radioButton.click();
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Third Party Representative User -- SREG-061
		cf.screenShot("ThirdPartyRepresentativeUser", "Pass", "Third Party Representative User");
		eoa.clickOnLinkAnchorTag(userId,"Manage Client");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Manage/View User Client Association -- SREG-682
		cf.screenShot("ManageViewUserClientAssociation", "Pass", "Manage View User Client Association");
		cf.clickOnLinkAnchorTag(" + ADD CLIENT");
		sleep();cf.waitForLoadingIconToDisappear();
	}
}
