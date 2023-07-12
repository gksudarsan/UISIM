package com.employerContibution.EM;


	import java.util.Map;

	import org.openqa.selenium.support.PageFactory;
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.Status;
	import com.ui.base.TestBase;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.HomePage;
	import com.ui.pages.PEOPage;
	import com.ui.pages.SREG_027;
	import com.ui.pages.SREG_030;
	import com.ui.pages.SUC_002;
	import com.ui.utilities.COMMON_CONSTANT;

	import stepDefinitions.commonStepDefinitions;

	public class EM_475_02_03_VERIFY_CSRUSER_IS_ABLE_TO_REMOVE_POA_TPR extends TestBase{


		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			
			commonStepDefinitions commonFuntions= new commonStepDefinitions();
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
			
			test = report.createTest("EM_475_02_03_VERIFY_CSRUSER_IS_ABLE_TO_REMOVE_POA_TPR");
			
			commonStepDefinitions commonFunction = new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			HomePage home = new HomePage(driver);
			SREG_030 sreg030 = new SREG_030(driver);
			SREG_027 sreg27 = new SREG_027(driver);
			SUC_002 suc002 = new SUC_002(driver);
			Map<String, String> databaseEanResult =
					commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE EAN IS NOT NULL AND EAN IN (SELECT EAN FROM T_EMPLOYER te WHERE employer_id IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION ttpcva WHERE DESIGNATION_TYPE = 'FAM' ))", "EAN");
									  String eanValue = databaseEanResult.get("EAN");
									  System.out.println("The EAN is " + eanValue);
			
			test.log(Status.INFO, "Logging to the application");
			//stepDef.login(prop.getProperty("EMPLOYER_USER_4"), prop.getProperty("CSR_Pass"));
			//stepDef.login(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

			commonFunction.login(COMMON_CONSTANT.CSR_USER_2.toUpperCase(), COMMON_CONSTANT.CSR_USER_2_PASSWORD);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
			
			//---Menu Click---
			commonFunction.clickMenu("Menu");
			commonFunction.ScrollMenu("Account Maintenance");
			commonFunction.clickMenu("Account Maintenance");
			commonFunction.ScrollMenu("Employer Account Maintenance");
			commonFunction.clickMenu("Employer Account Maintenance");
			sleep(2000);
			commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
			
			empRegPage.employerAccountMaintanceMenu.click();
			commonFunction.clickMenu("Employer Account Maintenance");
			commonFunction.clickMenu("Add or Remove POA/TPR Association");
			sleep(2000);
			commonFunction.screenShot("Add or Remove POA/TPR Association", "Pass", "Add or Remove POA/TPR Associationpage is displayed successfully");
			//00-00387	// TODO Auto-generated constructor stub
			commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
	        commonFunction.clickButton("Continue ");
	        sleep(1000);
	        commonFunction.screenShot("SREG-537", "Pass", "Add or Remove Third Party Association to Employer page is displayed");
		   commonFunction.selectDropdown("Designation Type", "Filing Agent Matters");
	       sleep(1000);
		   //commonFunction.enterTextboxContains("Comment", "REMOVE");
	       commonFunction.selectRadio("Select");
	       PEOPage.enddate.click();
	      //commonFunction.enterTextbox("Association End Date", "05/17/2023");
		    PEOPage.calender.sendKeys("05/19/23");
			
			PEOPage.comment.sendKeys("remove");
		    commonFunction.selectCheckbox("Additional authorization: Representative is also authorized to receive disclosures of, and review and inspect confidential Federal tax information and to perform any and all acts that I (we) can perform with respect to those tax matters as they bear on Unemployment Insurance matters. Note:  Confidential Federal Tax information shall include any and all information provided to the Department of Internal Revenue Service.");
	        commonFunction.clickButton("Submit ");
	        sleep(1000);
	        commonFunction.screenShot("User removed", "pass", "user removed successfully");
	        commonFunction.clickButton("Home ");
	        sleep(1000);
	        commonFunction.screenShot("Home", "Pass", "Home page is displayed");
		}

	}
