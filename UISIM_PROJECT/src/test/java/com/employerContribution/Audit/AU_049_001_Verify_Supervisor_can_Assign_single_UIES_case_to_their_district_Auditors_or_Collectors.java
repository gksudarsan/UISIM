package com.employerContribution.Audit;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AuditPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class AU_049_001_Verify_Supervisor_can_Assign_single_UIES_case_to_their_district_Auditors_or_Collectors extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "AU_049_001_Verify_Supervisor_can_Assign_single_UIES_case_to_their_district_Auditors_or_Collectors", groups = "Regression")
	
	public void AU_049_001()throws Exception {
	test = report.createTest("AU_049_001_Verify_Supervisor_can_Assign_single_UIES_case_to_their_district_Auditors_or_Collectors");

	commonStepDefinitions cf = new commonStepDefinitions();
	AuditPage audpg = new AuditPage(driver);
	
	//---DB
	
	Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
            "SELECT A.* FROM T_EMPLOYER_ACCOUNT A \r\n" + 
            "JOIN T_REGULAR_EMPLOYER RE ON RE.EMPLOYER_ACCOUNT_ID = A.EMPLOYER_ACCOUNT_ID\r\n" + 
            "JOIN T_EMPLOYER E ON E.EMPLOYER_ID = RE.EMPLOYER_ID\r\n" + 
            "JOIN T_BUSINESS_LOCATION BL ON BL.EMPLOYER_ACCOUNT_ID = RE.EMPLOYER_ACCOUNT_ID\r\n" + 
            "JOIN T_MST_FIPS_ZIP_TOWN_COUNTY_REGION_DISTRICT_MAPPING MST ON LEFT(MST.ZIP,5) = LEFT(BL.ZIP,5)\r\n" + 
            "WHERE A.REGISTRATION_STATUS = 'C' AND A.CREATED_TS > '2023-01-01' AND A.EAN IS NOT NULL\r\n" + 
            "AND A.EAN <> '' AND MST.DISTRICT = 1", "EAN");
    String eanValue = databaseResults.get("EAN");
    
    if ((eanValue == null) || (eanValue.isEmpty())) {
		System.out.println("ERN Value is null");
	} else {
		test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + eanValue + ".");
	}
    
    
	//----Login 
    cf.login(COMMON_CONSTANT.CSR_UIES_Audit_Supervisor.toUpperCase(), COMMON_CONSTANT.CSR_UIES_Audit_Supervisor_pass);
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
			//----Menu
			cf.clickMenu("menu");
			sleep(2000);
			cf.clickMenu("UIES Case");
			sleep();
			cf.screenShot("Menu", "Pass", "Selected Menu");
			cf.clickMenu("UIES Work List");
			sleep(2000);
			
			
		    //----AUD-507
			
			cf.screenShot("UIES Work List", "Pass", "Successfully landed on UIES Work List : AUD-507");
			//
			sleep(2000);
	        cf.clickButton(" Search ");
	        cf.screenShot("UIES Work List", "Pass", "Please enter at least one record on UIES Work List : AUD-507");
	        cf.enterTextboxContains("ERN", eanValue);
	        cf.clickButton(" Search ");
	        sleep(2000);
	        cf.screenShot("UIES Work List", "Pass", "Please enter Valid ERN on UIES Work List : AUD-507");
	        
	        //---To be continue from step 6-7 Due to Defect not proceeing
	        
	        
	        audpg.SearchRadiotab.click();
	        cf.screenShot("Create UIES Case - Employer Search", "Pass", "Selected record on AUD-640");
	        cf.clickButton("Continue ");
	        cf.waitForLoadingIconToDisappear();
	        
	        //----AUD-641
	        cf.screenShot("Create UIES Case - Enter Details - Field District", "Pass", "Successfully landed on AUD-641");
	        cf.selectDropdown("Type of Assignment", " Miscellaneous Investigation ");
            sleep(2000);
            cf.selectDropdown("Assignment Source", " A2*-  Local Sources ");
            sleep(2000);
            cf.selectRadioQuestions("Do you want to assign now?", "Yes ");
            sleep(1000);
            cf.screenShot("Create UIES Case - Enter Details - Field District", "Pass", "Entered info on AUD-641");
            cf.selectLink("Document", "Browse");
    		Thread.sleep(2000);
    		cf.uploadDoc("Sample.docx");
    		cf.waitForLoadingIconToDisappear();
           audpg.commentsId.sendKeys("Automation Testing");
           sleep(1000);
           cf.screenShot("Create UIES Case - Enter Details - Field District", "Pass", "Entered info on AUD-641");
           cf.clickButton("Continue ");
	        cf.waitForLoadingIconToDisappear();
	      //----AUD-642
	        cf.screenShot("Create UIES Case Verification - Field District", "Pass", "Successfully landed on AUD-642");
	        sleep(1000);
	        cf.clickButton("Submit ");
	        cf.waitForLoadingIconToDisappear();
	      //-----SUC-002
			cf.screenShot("Create UIES Case Confirmation", "Pass", "Launched to SUC-002");
			//cf.Label("Your Request is under review.");
			//-----Home
			cf.clickButtonContains("Home ");
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
			
			//----to be continue
			
	}}
