package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_001_csr_updatePrimary_address {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class EM_260_001 extends TestBase
	{
		
		@Test(priority=1, description = "EM.260.001.Verify CSR is able to update employer address for address type 'Primary business physical address'",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			employerManagement em =  new employerManagement();
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			test = report.createTest("EM.260.001.Verify CSR is able to update employer address for address type 'Primary business physical address'");
			Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
					"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE ='BUSI' AND EAN IS NOT NULL" , "EAN"); 
			  String eanValue = databaseResults1.get("EAN");
		        System.out.println(eanValue);
			
		
			cf.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			cf.screenShot("ApplicationLogin","Pass","Login is successful");
			cf.clickMenu("menu");
			cf.ScrollMenu("Account Maintenance");
			cf.clickMenu("Account Maintenance");
			cf.ScrollMenu("Maintain Address");
			cf.clickMenu("Maintain Address");
			cf.screenShot("Menu","Pass","AccountMaintenance");
			sleep();
			
			//---SREG-070---
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "Sucessfully launched to SREG-070 page");
			cf.enterTextboxContains("Employer Registration Number", "");
			cf.clickButton("Continue ");
			sleep();
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "Message 'Required' on SREG-070");
			cf.enterTextboxContains("Employer Registration Number", "4772");
			cf.clickButton("Continue ");
			sleep();
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "Message 'ERN Must be 7 digit' on SREG-070");
			cf.enterTextboxContains("Employer Registration Number", "0000000");
			cf.clickButton("Continue ");
			sleep();
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "Message 'Does not exist in the system' on SREG-070");
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "ERN Entered on SREG-070 page");
			cf.clickButton("Continue ");
			sleep(20000);
			
			//---SREG-486---
			cf.screenShot("Maintain Address Details", "Pass", "Sucessfully launched to SREG-486 page");
			cf.clickOnLink("Edit");
			sleep(20000);
			
			//---SREG-008---
			cf.screenShot("Primary Business Physical Address", "Pass", "Sucessfully launched to SREG-008 page");
			cf.clearTextboxContains("Address Line 1 ");
			cf.enterTextboxContains("Address Line 1 ", "13th Street");
			cf.clearTextboxContains("City ");
			cf.enterTextboxContains("City ", "New York");
			cf.clearTextboxContains("Zip Code");
			cf.enterTextboxContains("Zip Code", "10651");
			sleep();
			cf.selectDropdown("State", " New York ");
			cf.selectDropdown("County", " Albany ");
			sleep(2000);
			cf.enterTextboxContains("Number of employees at this location","10");
			cf.selectDropdown("Source", " NYS-100 (paper) ");
			sleep(1000);
			cf.selectDropdown("Source Type", " NYS-100 ");
			sleep(1000);
			cf.screenShot("Primary Business Physical Address", "Pass", "Enter the details on SREG-008 and click continue");
			cf.clickButton("Submit ");
			sleep(20000);
			try {
			try {
				empRegPage.uspsBusinessAddress.click();
			} catch (Exception exception) {
				
			empRegPage.uspsBusinessAddressInnerCircle.click();
			}
			empRegPage.continueButton_popUp.click();

			sleep(2000);
			}catch (Exception exception) {}
			
			
			//---SREG-486---
			cf.screenShot("Maintain Address Details", "Pass", "Sucessfully launched to SREG-486 page");
			cf.selectRadio("Yes ");
			cf.selectDropdown("Source", " NYS-100 (paper) ");
			sleep(1000);
			cf.selectDropdown("Source Type", " NYS-100 ");
			sleep(1000);
			cf.clickButton("Submit ");
			sleep(10000);
			
			// --- SUC-002 ---
			cf.screenShot("Maintain Address Details - Confirmation", "Pass", "Successfully launched to SUC-002 page");
			cf.clickButton("Home ");
			
			// --- Home ---
			cf.screenShot("EE01007", "Pass", "Successfully launched to Home page");
			cf.screenShot("Home", "Pass", "Home");
			cf.clickMenu("menu");
			cf.screenShot("Menu", "Pass", "ClickMenu");
			cf.ScrollMenu("Inquiry");
			cf.clickMenu("Inquiry");
			cf.ScrollMenu("Contribution Inquiry");
			cf.clickMenu("Contribution Inquiry");
			cf.ScrollMenu("Inquiry Employer Address History");
			cf.clickMenu("Inquiry Employer Address History");
            sleep();
			
			//---EM-051---
			cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Sucessfully launched to EM-051 page");
			cf.enterTextboxContains("Employer Registration Number", "");
			cf.clickButton("Continue ");
			sleep();
			cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Message 'Required' on SREG-070");
			cf.enterTextboxContains("Employer Registration Number", "0000000");
			cf.clickButton("Continue ");
			sleep();
			cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Message 'Does not exist in the system please enter valid ERN' on SREG-070");
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "ERN Entered on SREG-070 page");
			cf.clickButton("Continue ");
			sleep(20000);
			
			//---SREG-057---
			cf.screenShot("Inquiry Employer Address History", "Pass", "Sucessfully launched to SREG-057 page");
			
			//DONE
			
		}
	}
}