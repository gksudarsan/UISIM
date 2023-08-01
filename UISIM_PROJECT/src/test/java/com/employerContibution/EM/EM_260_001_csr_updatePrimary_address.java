package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_001_csr_updatePrimary_address {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class EM_260_001 extends TestBase {

		String EAN = prop.getProperty("EAN");

		@Test(priority = 1, description = "EM.260.002.Verify CSR is able to update employer address for address type 'business mailing address'", groups = {
				"Regression" })
		public void EM_260_002() throws Exception {
			test = report.createTest(
					"EM.260.002.Verify CSR is able to update employer address for address type 'business mailing address'");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf = new commonStepDefinitions();
			AddressPage addPage = new AddressPage(driver);
			employerManagementLocators em = new employerManagementLocators();
			// DB Query
			// Valid ERN
			Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
//					"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",

					"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
					"EAN");
			String eanValue = databaseEanResult.get("EAN");
			System.out.println(eanValue);

			// Login
			cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
			cf.clickMenu("Menu");
			sleep(2000);
			cf.clickMenu("Account Maintenance");
			sleep();
			cf.clickMenu("Maintain Address");
			sleep();

			// SREG-070
			cf.screenShot("Maintain Address – Enter ERN", "Pass", "Launched to SREG-070");
			// EAN Blank
			cf.enterTextboxContains("Employer Registration Number", "");
			cf.clickButton("Continue ");
			cf.errorLabel("Required");
			cf.screenShot("Blank EAN", "Pass", "Successfully received error label");
			// 4 - digit EAN
			sleep();
			cf.enterTextboxContains("Employer Registration Number", "4772");
			cf.clickButton("Continue ");
			cf.errorLabel("Length of this response must be at least 7 characters.");
			cf.screenShot("4-Digit EAN error ", "Pass", "Successfully received error label");

			// EAN doesn't exist
			cf.enterTextboxContains("Employer Registration Number", "22-32222");
			cf.clickButton("Continue ");
			cf.Label("The Employer Registration Number(ERN) provided does not exist in the system.");
			cf.screenShot("EAN doesn't exist", "Pass", "Successfully received error label");

			// Proper Valid EAN
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.clickButton("Continue ");
			sleep();

			// SREG-486
			cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
			cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
			cf.selectTableWithoutId("Primary Business Physical Address", 6, 1, "Maintain Address Details");

			// SREG-008
			cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-008");
			cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
			cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
			cf.enterTextboxContains("City", "NewYork");
			cf.enterTextboxContains("Zip Code", "13429");
			cf.selectDropdown("County", " Albany ");
//			cf.enterTextboxContains("First Name", "Shade");
//			cf.enterTextboxContains("Last Name", "Kernel");
			cf.selectDropdown("Source", "Correspondence/Email");
			sleep(2000);
			cf.selectDropdown("Source Type", "Correspondence/Email");
			cf.clickButtonContains("Submit ");

			// SREG-486
			cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
			sleep();

			cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
			cf.selectDropdown("Source", "Correspondence/Email");
			sleep(2000);
			cf.selectDropdown("Source Type", "Correspondence/Email");
			cf.clickButtonContains("Submit ");
			// SUC-002
			cf.screenShot("Successfully saved", "Pass", "Launched to SUC-002");
			String msg =   em.successMsgSuc002().getText();
			System.out.println(msg);
			Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");
			
			//Validation
			cf.clickMenu("Menu");
			cf.clickMenu("Inquiry");
			cf.clickMenu("Contribution Inquiry");
			cf.clickMenu("Inquiry Employer Address History");
			sleep(2000);
			cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Launched to EM-051");
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.clickButtonContains("Continue ");
			sleep();

			// SREG-057
			cf.screenShot("Inquiry Employer Address History", "Pass", "Launched to SREG-057");
			addPage.verifyInquiryAddressEmployerHistory("Primary Business Physical Address");
			System.out.println("Worked");

		}
	}
}