package com.employerContibution.EE;



		
		import java.util.Map;

		import org.apache.commons.lang3.StringUtils;
		import org.openqa.selenium.support.PageFactory;
		import org.openqa.selenium.support.ui.Sleeper;
		import org.testng.annotations.Test;

		import com.aventstack.extentreports.Status;
		import com.ui.base.TestBase;
		import com.ui.locators.employerManagementLocators;
		import com.ui.pages.EmployerRegisterPage;
		import com.ui.pages.PEOPage;
		import com.ui.pages.employerManagement;
		import com.ui.utilities.COMMON_CONSTANT;

		import stepDefinitions.commonStepDefinitions;

		public class EE_01_004_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Guardianship_and_work_items_will_be_created_for_CSR_to_review extends TestBase{

			@Test()
			public void EE_01_004_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Guardianship_and_work_items_will_be_created_for_CSR_to_review() throws Exception {

				commonStepDefinitions commonFunction = new commonStepDefinitions();	
				 //String EntityName = prop.getProperty("Entity");
				employerManagement em =  new employerManagement();
				EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
				PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
                test = report.createTest("EE_01_004_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Guardianship_and_work_items_will_be_created_for_CSR_to_review");
                test.log(Status.INFO, "Logging to the application");
    			
    			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
    			
    			commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");	
    			sleep(3000);
    			commonFunction.clickMenu("menu"); sleep();
    			commonFunction.ScrollMenu("Employer Registration");sleep();
    			commonFunction.screenShot("Menu", "Pass", "Employer Registration");
    			commonFunction.clickMenu("Employer Registration");sleep(2000);
    			commonFunction.screenShot("Menu", "Pass", "Employer Registration");
    			commonFunction.clickMenu("Register Employer"); sleep(3000);
    			sleep(3000);
    			commonFunction.clickButtonContains("Continue"); sleep(2000);

    			commonFunction.selectDropdown("Employer Type", " Business ");
    			commonFunction.enterTextboxContains("(FEIN)", "95-9970608"); 
    			commonFunction.screenShot("file1","Pass", "Searching with FEIN "); 
    			commonFunction.selectDropdown("*Type of Legal Entity"," Guardianship  Internal User Only "); 
    			commonFunction.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
    			commonFunction.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
    			commonFunction.screenShot("Menu", "Pass", "Employer Registration");
    			commonFunction.clickButtonContains("Continue");sleep(6000);

    			commonFunction.populateListbox("Legal Name", "NORTO-ADJUSTED TAXPAYER");sleep(2000);
    			commonFunction.screenShot("Menu", "Pass", "Employer Registration");
    		 	commonFunction.clickButtonContains("Continue");sleep(5000);

    			commonFunction.enterTextboxContains("Address Line 1","7th Street 40 E 7th St");sleep(2000);
    			commonFunction.enterTextboxContains("Address Line 2","");
    		    commonFunction.enterTextboxContains("City","New York");sleep(2000);
    		    commonFunction.enterTextboxContains("Zip Code","10003");sleep(2000);
    			commonFunction.screenShot("SREG008", "Pass", "Add primary physical address is displayed");		 
    			commonFunction.clickButtonContains("Continue ");sleep(6000);

    			//commonFunction.safeJavaScriptClick(empPage.uspsAddressRadio);
    		

    			//commonFunction.clickButtonContains("Continue");
    			commonFunction.screenShot("SREG-007", "Pass", "Employer Registration");
    			commonFunction.clickButtonContains("Continue ");sleep();sleep(2000);

    			commonFunction.selectRadio("Same as Primary Business Physical Address");sleep();

    			commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
    			commonFunction.clickButtonContains("Continue ");sleep(2000);
    			
    			commonFunction.screenShot("SREG521", "Pass", "Employer Verify Contact Details");
    			commonFunction.clickButtonContains("Continue ");
    			
    			commonFunction.screenShot("Bussiness Aquisition", "Pass", "Bussiness Aquisition(SREG-011)");
    			commonFunction.clickButtonContains("Continue ");	
    			sleep(2000);
    			commonFunction.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
    			commonFunction.selectRadioQuestions("Have you changed legal entity?", "No ");
    			commonFunction.clickButtonContains("Continue ");
    			sleep(2000);
    			commonFunction.screenShot("SREG-006", "Pass", "Add Corporate Officer/Owner  is displayed");
    			commonFunction.enterTextboxContains("First Name", "Test");
    			commonFunction.enterTextboxContains("Last Name", "AutoTest");
    			commonFunction.enterTextboxContains("Address Line 1", "Ave"+ commonFunction.createRandomInteger(10,99));
    			commonFunction.enterTextboxContains("City","NY");
    			commonFunction.enterTextboxContains("Zip Code","13429");
    			commonFunction.screenShot("Individual as Corporate Officer", "Pass", "Add Corporate Officer/Owner(SREG-006)");
    			commonFunction.clickButtonContains("Continue ");
    			
    			commonFunction.screenShot("SREG-006", "Pass", "Add Corporate Officer/Owner  is displayed");
    			commonFunction.enterTextboxContains("First Name", "Test");
    			commonFunction.enterTextboxContains("Last Name", "AutoTest");
    			commonFunction.enterTextboxContains("Address Line 1", "Ave"+ commonFunction.createRandomInteger(10,99));
    			commonFunction.enterTextboxContains("City","NY");
    			commonFunction.enterTextboxContains("Zip Code","13429");
    			commonFunction.screenShot("Entity as Corporate Officer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
    			commonFunction.clickButtonContains("Continue ");
    			sleep(2000);
    			try {
    				PEOPage.uspsAdd.click();
    				commonFunction.screenShot("UspsAddress","Pass","UspsAddress");
    				PEOPage.UspsContinueButton.click();
    				Thread.sleep(2000);
    			}
    			catch(Exception e) {
    				System.out.println("usps pop up dispalyed");
    			}
    			sleep(2000);
    			commonFunction.screenShot("SREG-005", "Pass", "add executor confirm");
    			commonFunction.clickButtonContains("Continue ");
    			sleep(2000);
    			//commonFunction.selectLink("Supporting documents like", "Browse");
    			//sleep(3000);
    			//D:\AutomationFiles\Sample.docx
    			//commonFunction.uploadDoc("Sample.docx");
    		     sleep(2000);
    			commonFunction.screenShot("Upload Documents", "Pass", "Upload Documents(SREG-683)");
    			commonFunction.clickButtonContains("Continue");
    			sleep(5000);
    			commonFunction.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
    			commonFunction.clickButtonContains("Continue");
    			sleep(3000);
    			commonFunction.selectCheckbox("I accept");
    			commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
    			sleep(2000);
    			commonFunction.clickButtonContains("Submit");
    			sleep(20000);
    			commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
    			sleep(2000);
    			//commonFunction.clickButtonContains("Home");

    			//Assigning user to WI Review emp type..................

    			PEOPage.queue.click(); Thread.sleep(15000);
    			//Resolving 1 WI................
    			//PEOPage.queue.click(); Thread.sleep(15000);
    			//commonFunction.enterTextboxContains("FEIN",FEIN);
    			commonFunction.screenShot("FeinSearch","Pass","feinSearch");
    			//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
    			commonFunction.screenShot("dol dtf","Pass","dol dtf work item ");
    			//commonFunction.clickOnLink("Review Employer Type");

    			Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
    			Thread.sleep(2000);
    			commonFunction.screenShot("EEWI-005","Pass","review emp type  ");

//    			commonFunction.selectDropdown("*Account Status ", "Liable");
    			//commonFunction.enterTextboxContains("Comment", "registration in process");
    			commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
    			commonFunction.screenShot("GeneralInfo","Pass","General Information");
    			commonFunction.clickButtonContains("Home");


    			
    			//Resolving 2ND WI ................
    			PEOPage.queue.click(); Thread.sleep(15000);
    			//commonFunction.enterTextboxContains("FEIN","99-9950123");
    			commonFunction.screenShot("FeinSearch","Pass","feinSearch");
    			//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
    			commonFunction.screenShot("verify agent rep task","Pass","verify agent rep task");
    			//commonFunction.clickOnLink("DOL DTF Discrepancy");

    			//Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
    			Thread.sleep(2000);
    			commonFunction.screenShot("EEWI-007","Pass","DOL DTF ");
    			
    			commonFunction.screenShot("GeneralInfo","Pass","General Information");
    			commonFunction.screenShot("SUC-002","Pass","Work Item Completed.");
    			commonFunction.clickButtonContains("Home");
    			
    			PEOPage.queue.click(); Thread.sleep(15000);
    			//Resolving 1 WI................
    			//PEOPage.queue.click(); Thread.sleep(15000);
    			//commonFunction.enterTextboxContains("FEIN",FEIN);
    			commonFunction.screenShot("FeinSearch","Pass","feinSearch");
    			//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
    			commonFunction.screenShot("normalize address task ","Pass","normalize address task ");
    			//commonFunction.clickOnLink("Review Employer Type");

    			Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
    			Thread.sleep(2000);
    			commonFunction.screenShot("EEWI-011","Pass","normalize address task");

//    			commonFunction.selectDropdown("*Account Status ", "Liable");
    			//commonFunction.enterTextboxContains("Comment", "registration in process");
    			commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
    			commonFunction.screenShot("GeneralInfo","Pass","General Information");
    			commonFunction.clickButtonContains("Home");


    			//Verify Registered employer in Inquery page 	...........
                
    			//Resolving 1 WI................
    			PEOPage.queue.click(); Thread.sleep(15000);
    			//commonFunction.enterTextboxContains("FEIN",FEIN);
    			commonFunction.screenShot("FeinSearch","Pass","feinSearch");
    			//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
    			commonFunction.screenShot("verifypredessocer task ","Pass","verifypredessocer task ");
    			//commonFunction.clickOnLink("Review Employer Type");

    			Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
    			Thread.sleep(2000);
    			commonFunction.screenShot("EEWI-013","Pass","verifypredessocer task");

//    			commonFunction.selectDropdown("*Account Status ", "Liable");
    			//commonFunction.enterTextboxContains("Comment", "registration in process");
    			commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
    			commonFunction.screenShot("GeneralInfo","Pass","General Information");
    			commonFunction.clickButtonContains("Home");
    			

    			//Resolving 1 WI................
    			PEOPage.queue.click(); Thread.sleep(15000);
    			//commonFunction.enterTextboxContains("FEIN",FEIN);
    			commonFunction.screenShot("FeinSearch","Pass","feinSearch");
    			//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
    			commonFunction.screenShot("total transfer failed rules ","Pass","total transfer failed rules ");
    			//commonFunction.clickOnLink("Review Employer Type");

    			Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
    			Thread.sleep(2000);
    			commonFunction.screenShot("EEWI-014","Pass","total transfer failed rules ");

//    			commonFunction.selectDropdown("*Account Status ", "Liable");
    			//commonFunction.enterTextboxContains("Comment", "registration in process");
    			commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
    			commonFunction.screenShot("GeneralInfo","Pass","General Information");
    			commonFunction.clickButtonContains("Home");
                sleep(5000);


    			commonFunction.clickMenu("menu");
    			commonFunction.ScrollMenu("Inquiry");
    			commonFunction.clickMenu("Inquiry");
    			commonFunction.clickMenu("Contribution Inquiry");
    			commonFunction.screenShot("Menu", "Pass", "Inquiry Employer Account");
    			sleep(2000);
    			commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Profile Changes page is displayed");
    			commonFunction.enterTextboxContains("Employer Registration Number", "");
    			commonFunction.clickButton("Continue ");
    			sleep(5000);
    			commonFunction.screenShot("SREG-051", "Pass", "Inquiry Employer Account Information");
    			sleep(3000);
    			commonFunction.clickButtonContains("Previous ");
    			sleep(3000);
    			commonFunction.clickButtonContains(" Home ");
    			commonFunction.screenShot("Home", "Pass", "Home page is displayed");
    			
    			


    	      
    		}
    	}




