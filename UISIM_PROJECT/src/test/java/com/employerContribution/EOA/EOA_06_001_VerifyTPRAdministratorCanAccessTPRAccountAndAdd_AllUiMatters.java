package com.employerContribution.EOA;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_06_001_VerifyTPRAdministratorCanAccessTPRAccountAndAdd_AllUiMatters extends TestBase {
	
	@Test
	public void  EOA_06_001_TprAdministrator() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EOAPage eoa = new EOAPage(driver);
		test = report.createTest(
				"EOA.06.001-Verify TPR Administrator can access TPR account and add, edit user(s) (Add User Role - All UI Matters)");
		cf.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Users");
		cf.clickMenu("Users");
		cf.screenShot("navigateToTprUsers", "Pass", "Navigating to tpr users");
		cf.clickMenu("TPR Users");
		sleep(2000);
		
		//Third Party Representative User -- SREG-061
		cf.screenShot("ThirdPartyRepresentativeUser", "Pass", "Third Party Representative User");
		cf.clickOnLinkAnchorTag(" ADD USER");
		cf.waitForLoadingIconToDisappear();
		
		//Add TPR User -- SREG-531
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
		
		//Add User Confirmation -- SUC-002
		cf.screenShot("AddUserConfirmation", "Pass", "Add User Confirmation");
		cf.clickButtonContains("Home ");
		sleep(2000);cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage", "Pass", "Home Page");
		
		//Again Navigating to TPR Users
		cf.clickMenu("Menu");
		cf.ScrollMenu("Users");
		cf.clickMenu("Users");
		cf.screenShot("againNavigateToTprUsers", "Pass", "Again Navigating to tpr users");
		cf.clickMenu("TPR Users");
		sleep(2000);cf.waitForLoadingIconToDisappear();
		
		//Third Party Representative User -- SREG-061
		cf.screenShot("ThirdPartyRepresentativeUser_sreg061", "Pass", "Third Party Representative User - SREG-061");
		eoa.clickOnLinkAnchorTag(userId,"Manage User");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Add TPR User -- SREG-531
		cf.screenShot("updateAddTPRUser", "Pass", "Update TPR User");
		cf.enterTextboxContains("Middle Initial", "S");
		cf.enterTextboxContains("Last Name", "Chauhan");
		cf.enterTextboxContains("Email Address","autoTest"+Long.toString(cf.createRandomInteger(10000,99999))+"@gmail.com");
		cf.enterTextboxContains(" Contact Number ",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
	    eoa.selectCheckBox_SameContactNumber.click();
	    
	    String newpassword = "Automation@"+Long.toString(cf.createRandomInteger(10000,99999));
	    System.out.println("The selected password is :" + newpassword);
	    cf.enterTextboxContains("Temporary Password", newpassword);
	    cf.enterTextboxContains("Confirm Temporary Password", newpassword);
	    cf.screenShot("UpdateTPRUserEnteredDetails", "Pass", "Update TPR User Enter Details");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Updated User Confirmation -- SUC-002
		cf.screenShot("UpdatedUserConfirmation", "Pass", "Updated User Confirmation");
		cf.clickButtonContains("Home ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage1", "Pass", "Home Page1");
	}
}
