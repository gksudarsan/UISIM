package com.employerContibution.EM;


		import java.util.Map;

		import org.testng.annotations.Test;

		import com.aventstack.extentreports.Status;
		import com.ui.base.TestBase;
		import com.ui.pages.EM_005;
		import com.ui.pages.EmployerRegisterPage;
		import com.ui.pages.HomePage;
		import com.ui.pages.SREG_027;
		import com.ui.pages.SREG_030;
		import com.ui.pages.SREG_503;
		import com.ui.pages.SREG_504;
		import com.ui.pages.SUC_002;
		import com.ui.utilities.COMMON_CONSTANT;

		import net.bytebuddy.description.annotation.AnnotationList.Empty;
		import stepDefinitions.commonStepDefinitions;

		public class EM_318_006_Verify_CSR_is_able_to_update_Legal_entity_type_information_for_type_of_legal_entity_ESTATE_and_employer_type_HOUSEHOLD_ extends TestBase{


			@Test(priority = 1, description = "Test sample", groups = { "Regression" })
			public void Testing123() throws Exception {
				
				test = report.createTest("Verify CSR is able to update Legal entity type information for type of legal entity 'Limited Partnership' and employer type 'Business'");
				
				commonStepDefinitions commonFunction = new commonStepDefinitions();
				EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
				HomePage home = new HomePage(driver);
				SREG_030 sreg030 = new SREG_030(driver);
				SREG_027 sreg27 = new SREG_027(driver);
				SUC_002 suc002 = new SUC_002(driver);
				 Map<String, String> databaseEanResult =
		/*commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'ESTA' AND EAN LIKE '8%'"
						  , "EAN");*/
						 
						 commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'ESTA'"
								  , "EAN");
						  String eanValue = databaseEanResult.get("EAN");
						  System.out.println("The EAN is " + eanValue);
				
				test.log(Status.INFO, "Logging to the application");
				//stepDef.login(prop.getProperty("EMPLOYER_USER_4"), prop.getProperty("CSR_Pass"));
				//stepDef.login(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

				commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
				sleep(2000);
				commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
				
				//---Menu Click---
				commonFunction.clickMenu("Menu");
				commonFunction.ScrollMenu("Account Maintenance");
				commonFunction.clickMenu("Account Maintenance");
				commonFunction.ScrollMenu("Employer Account Maintenance");
				commonFunction.clickMenu("Employer Account Maintenance");
				sleep();
				commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
				
				empRegPage.employerAccountMaintanceMenu.click();
				commonFunction.clickMenu("Employer Account Maintenance");
				commonFunction.clickMenu("Maintain Accounts");
				sleep(2000);
				commonFunction.screenShot("Employer Account Maintenance", "Pass", "Employer Account Maintenance page is displayed successfully");
				//00-00387
				
				// --- SREG-030 ---
				sleep(2000);		
				commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
				commonFunction.clickButton("Continue ");
				commonFunction.screenShot("SREG30", "Pass", "Successful launch to Modify Employer Account Details");
				sleep(2000);
				commonFunction.selectDropdown("Type of Legal Entity", "Estate");
				commonFunction.selectDropdown("Employer Type", "Household/Domestic Employer");
				commonFunction.selectDropdown("Source", "Miscellaneous");
				commonFunction.selectDropdown("Source Type", "IA15 – Change of Business Information");
				commonFunction.clickButton("Submit ");
				sleep(2000);
				commonFunction.screenShot("Employer Account Maintenance Confirmation", "Pass", "Employer Account Maintenance Confirmation page is displayed");
				commonFunction.clickButton("Home ");
				sleep(2000);
				commonFunction.clickMenu("Menu");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry");
				commonFunction.clickMenu("Contribution Inquiry");
				commonFunction.clickMenu("Inquiry Employer Account Profile Changes");
				sleep(2000);
				commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Profile Changes page is displayed");
				commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
				commonFunction.clickButton("Continue ");
				sleep(5000);
				
				commonFunction.screenShot("Employer Account Profile Changes", "Pass", "Employer Account Profile Changes page is displayed");
				
			}

		}
