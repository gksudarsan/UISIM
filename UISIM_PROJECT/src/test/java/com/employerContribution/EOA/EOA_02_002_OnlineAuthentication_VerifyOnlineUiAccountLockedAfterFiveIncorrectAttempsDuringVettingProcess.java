package com.employerContribution.EOA;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_02_002_OnlineAuthentication_VerifyOnlineUiAccountLockedAfterFiveIncorrectAttempsDuringVettingProcess extends TestBase {
	
	@Test
	public void  EOA_02_002_OnlineAuthentication() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EOAPage eoa = new EOAPage(driver);
		test = report.createTest(
				"EOA.02.002 - Online Authentication - Verify the Online UI account is locked after five incorrect attempts during vetting process");
		cf.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		eoa.radioButton_EOA_Employer.click();sleep();
		cf.screenShot("UIservicesAccountCreation", "Pass", "UI services account creation");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//UI Services Employer Account Creation
		cf.enterTextboxContains("Employer Registration Number (ERN)", "0000000");
		cf.enterTextboxContains("Legal Name", "art");
		cf.screenShot("UIServicesEmployerAccountCreation", "Pass", "UI Services Employer Account Creation");
		
		//1st Attempt
		cf.selectDropdown("Quarter ", " 1 ");sleep();
		cf.selectDropdown("Year ", " 2024 ");sleep();
		cf.enterTextboxContains("Total Remuneration $", "100");
		cf.enterTextboxContains("Online Account PIN", "5234");
		cf.screenShot("UIServicesEmployerAccountCreation1", "Pass", "UI Services Employer Account Creation1");
		
		//2nd Attempt
		cf.enterTextboxContains("Employer Registration Number (ERN)", "7654321");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("UIServicesEmployerAccountCreation2", "Pass", "UI Services Employer Account Creation2");
		
		//3rd Attempt
	    cf.forceClearText(eoa.ernFieldSreg_612);
	    cf.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", "145901345");
	    cf.clickButtonContains("Continue ");
	    sleep();cf.waitForLoadingIconToDisappear();
	    cf.screenShot("UIServicesEmployerAccountCreation3", "Pass", "UI Services Employer Account Creation3");
	    
	    //4th Attempt
	    cf.forceClearText(eoa.feinFieldSreg_612);
	    cf.enterTextboxContains("Employer Registration Number (ERN)", "1111111");
	    cf.clickButtonContains("Continue ");
	    sleep();cf.waitForLoadingIconToDisappear();
	    cf.screenShot("UIServicesEmployerAccountCreation4", "Pass", "UI Services Employer Account Creation4");
	    
	    //5th Attempt
	    cf.forceClearText(eoa.ernFieldSreg_612);
	    cf.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", "009977221");
	    cf.clickButtonContains("Continue ");
	    sleep();cf.waitForLoadingIconToDisappear();
	    cf.screenShot("UIServicesEmployerAccountCreation5", "Pass", "UI Services Employer Account Creation5");
	    
	    //Account Lock Message Appears After 5 Attempt
	    cf.clickButtonContains("Continue ");
	    sleep();cf.waitForLoadingIconToDisappear();
	    cf.screenShot("UIServicesEmployerAccountCreation6", "Pass", "UI Services Employer Account Creation6");
	    cf.errorContent("Your access has been locked and you will need to contact the New York State Department of Labor at (518)-485-6076 for further assistance.");
	    
	}
}
