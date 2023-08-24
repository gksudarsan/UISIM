package com.employerContibution.EL;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_084;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_007 extends TestBase {
	@Test(priority = 1, description = "EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'", groups = {
			"Regression" })
	public void Testing123() throws Exception {
		test = report.createTest(
				"EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'");
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		PEOPage peoPg = new PEOPage(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration pemo = new PEO_001_ProfessionalEmployerOrganizationRegistration(
				driver);

		/*
		 * test.log(Status.INFO, "Logging to the application");
		 * stepDef.login(prop.getProperty("CSR_UserID") , prop.getProperty("CSR_Pass"));
		 * test.log(Status.INFO, "Navigating to professional employer organization");
		 * home.navigateToPeoRegister(); test.log(Status.PASS, "PEO Register");
		 * peoRegDetails.clickContinueButton(); test.log(Status.INFO,
		 * "Clicking on the button"); sleep(2000); PEOPage.groupRegPeo.click();
		 * stepDef.enterTextbox("Name of Professional Employer Organization","Test_auto"
		 * +stepDef.createRandomInteger(1000,9999)); stepDef.
		 * enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently"
		 * ,"auto_test"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.screenShot("peor", "Pass",
		 * "Professional Employer Organization Registration");
		 * stepDef.clickButtonContains("Save & Continue"); sleep(2000);
		 * 
		 * test.log(Status.INFO, "Entering the Details"); test.log(Status.PASS,
		 * "Entered the details"); stepDef.
		 * selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?"
		 * , "Yes"); long number = stepDef.createRandomInteger(10000,99999); String
		 * ernValue=StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),7); String feinValue=StringUtils.left(
		 * String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 * stepDef.enterTextboxContains("Employer Registration Number", ernValue);
		 * stepDef.selectDropdown("Type of Legal Entity", " Corporation ");
		 * stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)",
		 * feinValue); stepDef.selectRadioQuestions("Type of Ownership", "Public");
		 * stepDef.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
		 * stepDef.screenShot("IndividualPeo",
		 * "Pass","Professional Employer Organization Registration");
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * stepDef.screenShot("UnemploymentInsurance",
		 * "Pass","Unemployment Insurance Account Details"); try {
		 * PEOPage.peoRadioButton.click(); stepDef.selectRadioInTable(ernValue,1,
		 * 1,"Unemployment Insurance Account Details"); } catch(Exception e) {}
		 * stepDef.screenShot("Unemployment Insurance", "PASS",
		 * "Unemployment Insurance Account Details");
		 * 
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * peoPg.attentionCareOf.sendKeys("Test");
		 * peoPg.addressLine1.sendKeys("addressLine1"+stepDef.createRandomInteger(1000,
		 * 9999));
		 * peoPg.addressLine2.sendKeys("addressLine2"+stepDef.createRandomInteger(1000,
		 * 9999)); peoPg.addressCity.sendKeys("NewYork");
		 * peoPg.addressZip.sendKeys("13420");
		 * 
		 * stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.
		 * createRandomInteger(10000000,99999999))+Long.toString(stepDef.
		 * createRandomInteger(10,99)));
		 * stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.
		 * toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		 * stepDef.clickButtonContains("Save & Continue"); Thread.sleep(2000);
		 * 
		 * peoPg.uspsAddress.click(); peoPg.currentAdditionalAddress.click();
		 * stepDef.screenShot("UspsAddress","Pass","UspsAddress");
		 * peoPg.UspsContinueButton.click(); Thread.sleep(2000);
		 * stepDef.screenShot("CurrentAdditionalAddress",
		 * "Pass","Verify Current Additional Address(es) in New York");
		 * stepDef.clickButtonContains("Continue"); Thread.sleep(2000);
		 * stepDef.screenShot("Mailing Address","Pass","Mailing Address");
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * stepDef.enterTextboxContains("Address Line 1","PrioraddressLine1"+stepDef.
		 * createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2","PrioraddressLine2"+stepDef.
		 * createRandomInteger(1000,9999)); stepDef.enterTextboxContains("City",
		 * "NewYork"); stepDef.enterTextboxContains("Zip Code","13429");
		 * stepDef.enterTextboxContains("other name(s)","automation");
		 * stepDef.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		 * stepDef.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		 * stepDef.screenShot("Prior Address","Pass","Prior Address(es) in New York");
		 * stepDef.clickButtonContains("Save & Continue"); Thread.sleep(2000);
		 * stepDef.screenShot("verifyPriorAddress",
		 * "Pass","Verify Prior Address(es) in New York");
		 * stepDef.clickButtonContains("Continue"); Thread.sleep(2000);
		 * stepDef.enterTextboxContains("Entity or Person","Automation_entity");
		 * stepDef.enterTextboxContains("Ownership Percentage","50");
		 * stepDef.enterTextboxContains("Address Line 1","owneraddressLine1"+stepDef.
		 * createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2","owneraddressLine2"+stepDef.
		 * createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.enterTextboxContains("Zip Code","13430"); stepDef.
		 * screenShot("Ownership Information - privately or closely held company",
		 * "PASS", "Ownership Information - privately or closely held company");
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000); try {
		 * PEOPage.uspsSuggestedAddress.click();
		 * stepDef.screenShot("VerifyContactDetails","Pass","UspsAddress");
		 * PEOPage.UspsContinueButton.click(); sleep(2000); } catch(Exception e) {}
		 * 
		 * stepDef.screenShot("Verify Ownership Information", "PASS",
		 * "Verify Ownership Information"); stepDef.clickButtonContains("Continue ");
		 * Thread.sleep(2000);
		 * 
		 * //Prior Ownership Information
		 * stepDef.enterTextboxContains("Entity or Person", "Automation_entity");
		 * stepDef.enterTextboxContains("Ownership Percentage", "40");
		 * stepDef.enterTextboxContains("Address Line 1",
		 * "ownershipAddressLine1"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2",
		 * "ownershipAddressLine2"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.enterTextboxContains("Zip Code","13430");
		 * stepDef.screenShot("Prior Ownership Information", "PASS",
		 * "Prior Ownership Information");
		 * stepDef.clickButtonContains("Save & Continue "); peoPg.uspsAddress.click();
		 * stepDef.screenShot("UspsAddress","Pass","UspsAddress");
		 * peoPg.UspsContinueButton.click(); Thread.sleep(2000);
		 * stepDef.screenShot("Verify Prior Ownership Information", "PASS",
		 * "Verify Prior Ownership Information");
		 * stepDef.clickButtonContains("Continue ");
		 * stepDef.screenShot("Submission Instructions and Responsibilities", "PASS",
		 * "Submission Instructions and Responsibilities");
		 * stepDef.clickButtonContains("Continue "); Thread.sleep(2000);
		 * 
		 * //upload files stepDef.selectCheckbox("Proof of NYS Workers");
		 * stepDef.selectLink("Proof of NYS Workers", "Browse"); sleep(2000);
		 * stepDef.uploadDoc("Sample.docx"); sleep(4000);
		 * stepDef.clickButtonContains("Upload"); sleep(2000);
		 * stepDef.screenShot("Upload Documents", "PASS", "Upload Documents");
		 * stepDef.clickButtonContains("Save & Continue"); sleep(2000);
		 * stepDef.clickButtonContains("Choose File"); sleep(2000);
		 * stepDef.uploadDoc("PEO Client List template_TestData2.xls"); sleep(2000);
		 * stepDef.screenShot("Upload Client List", "PASS", "Upload Client List");
		 * stepDef.clickButtonContains("Continue");
		 * stepDef.screenShot("Verify Client List", "PASS", "Verify Client List");
		 * stepDef.clickButtonContains("Continue "); Thread.sleep(2000); //stepDef.
		 * enterTextboxContains("Federal Employer Identification Number (FEIN)",
		 * "123456789"); stepDef.clickButtonContains(" Search "); Thread.sleep(2000);
		 * peoPg.addPeoMember.click(); Thread.sleep(2000);
		 * stepDef.enterTextboxContains("PEO Member Name", "test321");
		 * stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)",
		 * "401101502"); stepDef.
		 * selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?"
		 * , "Yes "); stepDef.enterTextboxContains("Employer Registration Number",
		 * "1691721"); stepDef.enterTextboxContains("Address Line 1",
		 * "primaryAddressLine1"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2",
		 * "primaryAddressLine2"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.selectDropdown("State", " New York ");
		 * stepDef.enterTextboxContains("Zip Code","13430");
		 * stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.
		 * createRandomInteger(10000000,99999999))+Long.toString(stepDef.
		 * createRandomInteger(10,99)));
		 * stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.
		 * toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		 * Thread.sleep(2000); stepDef.selectRadio("Different");
		 * stepDef.enterTextboxContains("Address Line 1",
		 * "peoMembersAddressLine1"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2",
		 * "peoMembersAddressLine2"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.selectDropdown("State", " New York ");
		 * stepDef.enterTextboxContains("Zip Code","13430");
		 * stepDef.screenShot("PEO Member Information", "PASS",
		 * "PEO Member Information"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Save & Continue "); peoPg.uspsAddress.click();
		 * peoPg.currentAdditionalAddress.click();
		 * stepDef.screenShot("UspsAddress","Pass","UspsAddress");
		 * peoPg.UspsContinueButton.click(); Thread.sleep(2000);
		 * stepDef.screenShot("Verify Current Additional Address(es) in New York",
		 * "PASS", "Verify Current Additional Address(es) in New York");
		 * Thread.sleep(2000); stepDef.clickButtonContains("Continue ");
		 * stepDef.screenShot("Prior Address(es) in New York", "PASS",
		 * "Prior Address(es) in New York"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Save & Continue ");
		 * stepDef.screenShot("Verify Prior Address(es) in New York", "PASS",
		 * "Verify Prior Address(es) in New York"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Continue "); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Choose File"); sleep(2000);
		 * stepDef.uploadDoc("PEO Client List template_TestData2.xls"); sleep(4000);
		 * stepDef.clickButtonContains("Continue"); sleep(2000);
		 * 
		 * Thread.sleep(2000); stepDef.screenShot("Upload Client List", "PASS",
		 * "Upload Client List"); stepDef.clickButtonContains("Continue ");
		 * Thread.sleep(2000); stepDef.screenShot("Verify Client List", "PASS",
		 * "Verify Client List"); stepDef.clickButtonContains("Continue ");
		 * Thread.sleep(2000); stepDef.screenShot("List of Members of PEO Group",
		 * "PASS", "List of Members of PEO Group");
		 * driver.findElement(By.xpath("//a[@class='static-link']")).click();
		 * Thread.sleep(2000);
		 * 
		 * //adding again peo members stepDef.clickButtonContains(" Search ");
		 * Thread.sleep(2000); peoPg.addPeoMember.click();
		 * stepDef.enterTextboxContains("PEO Member Name", "test321");
		 * stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)",
		 * "401101502"); stepDef.
		 * selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?"
		 * , "Yes "); stepDef.enterTextboxContains("Employer Registration Number",
		 * "1691721"); stepDef.enterTextboxContains("Address Line 1",
		 * "primaryAddressLine1"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2",
		 * "primaryAddressLine2"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.selectDropdown("State", " New York ");
		 * stepDef.enterTextboxContains("Zip Code","13430");
		 * stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.
		 * createRandomInteger(10000000,99999999))+Long.toString(stepDef.
		 * createRandomInteger(10,99)));
		 * stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.
		 * toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		 * Thread.sleep(2000); stepDef.selectRadio("Different");
		 * stepDef.enterTextboxContains("Address Line 1",
		 * "peoMembersAddressLine1"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("Address Line 2",
		 * "peoMembersAddressLine2"+stepDef.createRandomInteger(1000,9999));
		 * stepDef.enterTextboxContains("City","NewYork");
		 * stepDef.selectDropdown("State", " New York ");
		 * stepDef.selectDropdown("Country", " United States ");
		 * stepDef.enterTextboxContains("Zip Code","13430");
		 * stepDef.screenShot("PEO Member Information", "PASS",
		 * "PEO Member Information"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Save & Continue "); peoPg.uspsAddress.click();
		 * peoPg.currentAdditionalAddress.click();
		 * stepDef.screenShot("UspsAddress","Pass","UspsAddress");
		 * peoPg.UspsContinueButton.click(); Thread.sleep(2000);
		 * stepDef.screenShot("Verify Current Additional Address(es) in New York",
		 * "PASS", "Verify Current Additional Address(es) in New York");
		 * Thread.sleep(2000); stepDef.clickButtonContains("Continue ");
		 * stepDef.screenShot("Prior Address(es) in New York", "PASS",
		 * "Prior Address(es) in New York"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Save & Continue ");
		 * stepDef.screenShot("Verify Prior Address(es) in New York", "PASS",
		 * "Verify Prior Address(es) in New York"); Thread.sleep(2000);
		 * stepDef.clickButtonContains("Continue "); sleep(2000);
		 * stepDef.clickButtonContains("Choose File"); sleep(2000);
		 * stepDef.uploadDoc("PEO Client List template_TestData2.xls"); sleep(4000);
		 * stepDef.clickButtonContains("Continue"); sleep(2000);
		 * 
		 * Thread.sleep(2000); stepDef.screenShot("Upload Client List", "PASS",
		 * "Upload Client List"); stepDef.clickButtonContains("Continue ");
		 * Thread.sleep(2000); stepDef.screenShot("Verify Client List", "PASS",
		 * "Verify Client List"); stepDef.clickButtonContains("Continue ");
		 * Thread.sleep(2000); stepDef.screenShot("List of Members of PEO Group",
		 * "PASS", "List of Members of PEO Group");
		 * stepDef.clickButtonContains("Continue "); Thread.sleep(2000);
		 * stepDef.screenShot("PEO Details Review screen", "PASS",
		 * "PEO Details Review screen");
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000); stepDef.
		 * enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
		 * "Testing"); stepDef.screenShot("Declaration", "PASS", "Declaration");
		 * stepDef.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * stepDef.screenShot("Group Guaranty and Statement of Acknowledgment", "PASS",
		 * "Group Guaranty and Statement of Acknowledgment");
		 * stepDef.clickButtonContains("Accept & Submit "); Thread.sleep(2000);
		 * stepDef.screenShot("Completion", "PASS", "Register/Renew Confirmation");
		 * Thread.sleep(2000); stepDef.clickButtonContains("Home");
		 * 
		 * //datebase querry stepDef.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
		 * +COMMON_CONSTANT.
		 * CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901228')"
		 * );
		 */

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		//
		/*
		 * Thread.sleep(2000); driver.navigate().refresh();
		 * commonFuntions.waitForLoadingIconToDisappear();
		 * 
		 * commonFuntions.enterTextboxContains("First Name", "Dev Test");
		 * commonFuntions.enterTextboxContains("Last Name", "nys");
		 * commonFuntions.enterTextboxContains("Job Title", "Tester");
		 * commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com"); //
		 * 
		 */
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.groupRegPeo.click();
		String peoName = "Test_auto" + commonFuntions.createRandomInteger(1000, 9999);
		System.out.println(peoName);
		commonFuntions.enterTextbox("Name of Professional Employer Organization", peoName);
		commonFuntions.enterTextbox("Additional Name(s), if any, under which the PEO currently Conducts Business",
				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		Thread.sleep(2000);
		// long number = commonFuntions.createRandomInteger(10000, 99999);
		// String ernValue = "78" + Long.toString(number);
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println(ernValue);

		// String feinValue =
		// Long.toString(commonFuntions.createRandomInteger(100000000, 999999999));
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);

		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
		commonFuntions.enterTextboxContains("Fiscal Year Start Date", "01/01/2023");
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// Step7
		commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			Thread.sleep(2000);
		}
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Address Information", "PASS", "EAS-001 page displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Address Information", "PASS", "PEO-003 page displayed");

		// Step 8 PEO-003
		Thread.sleep(2000);
		PEOPage.attentionCareofFieldPEO003.sendKeys("Test");
		PEOPage.addressLine1.sendKeys("123state");
		// PEOPage.addressLine2.sendKeys("addressLine2" +
		// commonFuntions.createRandomInteger(1000, 9999));
		// commonFuntions.selectDropdown("State", " Alaska ");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");

		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));

		PEOPage.extFieldList.get(0).sendKeys("091");

		// commonFuntions.enterTextboxContains("Business Email Address",
		// "autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999))
		// + "@gmail.com");

		commonFuntions.enterTextboxContains("Business Email Address", "test2@gmail.com");

		//
		PEOPage.listtheCurrentAddressaddressLine1.sendKeys("123state");
		PEOPage.listtheCurrentCity.sendKeys("albany");
		PEOPage.listtheCurrentZipcode.sendKeys("12010");
		//

		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();

		// Step 9
		Thread.sleep(2000);
		commonFuntions.screenShot("Current Additional Address", "PASS",
				"PEO-005 Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		try {
			// Step 10
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("Mailing Address", "PASS", "PEO-004 Mailing Address");
			commonFuntions.clickButtonContains("Save & Continue");
			Thread.sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			Thread.sleep(2000);
		}

		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.UspsContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Prior Address(es) in New York", "PASS", "PEO-006 Screen is diaplyed");

		// Step 11
		// commonFuntions.enterTextboxContains("Address Line 1",
		// "PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 1", "123state");
		commonFuntions.enterTextboxContains("Address Line 2",
				"PrioraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		// commonFuntions.selectDropdown("State", " Alabama ");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");
		// commonFuntions.enterTextboxContains("other name(s)", "automation");
		// commonFuntions.enterTextboxContains("Predecessor(s) Name",
		// "AutoPredecessor");
		// commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFuntions.screenShot("Prior Address", "Pass", "Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Prior Details", "PASS", "PEO-007 Verify Prior Address(es) in New York");

		// Step 12
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Ownership Information - privately or closely held company", "PASS",
				"PEO-012 Screen is displayed");

		// Step 13&14
		commonFuntions.enterTextboxContains("Entity or Person", "50");
		commonFuntions.enterTextboxContains("Ownership Percentage", "50");
		/*
		 * commonFuntions.enterTextboxContains("Address Line 1", "owneraddressLine1" +
		 * commonFuntions.createRandomInteger(1000, 9999));
		 * commonFuntions.enterTextboxContains("Address Line 2", "owneraddressLine2" +
		 * commonFuntions.createRandomInteger(1000, 9999));
		 */
		commonFuntions.enterTextboxContains("Address Line 1", "123state");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.UspsContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Ownership Information", "PASS", "PEO-013 Verify Ownership Information");

		// Step 14
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		// Step 15
		commonFuntions.screenShot("Verify Ownership Information", "PASS", "PEO-014 Verify Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//Step-16
		commonFuntions.screenShot("Upload Documents", "PASS", "SREG-006 Upload Documents");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");

		// Step 22&23&24
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Client List", "PASS", "LEAS-012 client list");

		// Step 25
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search For PEO Member", "PASS", "PEOG-001 screen is displayed");

		// 26&27
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton(" Search ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sreg008.firstradiobuttonVerifyAddPopup.click();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Member Information", "PASS", "PEOG-002 screen is displayed");

		// sreg008.firstExtField.sendKeys("091");
		// commonFuntions.enterTextboxContains("Address Line 1",
		/// "PriorOwneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		PEOPage.uspsAddress.click();
		PEOPage.mailingAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Member Information", "PASS", "PEO-005 screen is displayed");
		// 28
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search For PEO Member", "PASS", "PEO-006 screen is displayed");

		//
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Prior Address(es) in New York", "PASS", "PEO-007 screen is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		//////////////////////
		commonFuntions.screenShot("Ownership Information - Publicly Traded Company", "PASS", "PEO-012 screen is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Ownership Information - Publicly Traded Company", "PASS", "PEO-013 screen is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Ownership Information - Publicly Traded Company", "PASS", "PEO-014 screen is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Upload Documents", "PASS", "SREG-006 screen is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Upload Client List", "PASS", "PEO-015 screen is displayed");

		//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");

		// 
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Client List", "PASS", "LEAS-012 client list");
		
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Members of PEO Group", "PASS", "PEOG-003 client list");

		//
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Details Review screen", "PASS", "PEOR-001 page is displayed");

		//
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Declaration", "PASS", "PEO-016 screen is displayed");

		//
		PEOPage.officerPartnerField.sendKeys("devanshu");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Group Guaranty and Statement of Acknowledgment", "PASS",
				"PEOG-006 screen is displayed");

		//
		commonFuntions.clickButtonContains("Accept & Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Register/Renew Confirmation", "PASS", "SUC-002 screen is displayed");

		//
		commonFuntions.clickButtonContains("Home");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "PASS", "Home screen is displayed");
		
		
		//////////////////////////

		//
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Review PEO", "Pass", "Review PEO");
		commonFuntions.clickOnLink("Review PEO ");
		commonFuntions.waitForLoadingIconToDisappear();
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 page is displayed");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Review PEO Registration", "Pass", "CPEO-001 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("General Information", "Pass", "PEO-002 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			Thread.sleep(2000);
		}

		//
		commonFuntions.screenShot("Unemployment Insurance Account Details", "Pass", "EAS-001 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Address Information", "Pass", "PEO-003 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Current Additional Address(es) in New York", "Pass",
				"PEO-005 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Mailing Address", "Pass", "PEO-004 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Prior Address(es) in New York", "Pass", "PEO-007 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//////////////////////////////////
		
		commonFuntions.screenShot("Verify Ownership Information", "Pass", "PEO-013 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Submission Instructions and Responsibilities", "Pass", "PEO-014 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Upload Documents", "Pass", "SREG-006 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		
		commonFuntions.screenShot("Verify Client List", "Pass", "LEAS-012 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("PEO Details Review screen", "Pass", "PEOG-003 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("PEO Details Review screen", "Pass", "PEOR-001 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("Declaration", "Pass", "PEO-016 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.screenShot("Group Guaranty and Statement of Acknowledgment", "Pass",
				"PEOG-006 page is displayed");
		commonFuntions.clickButtonContains("Accept & Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.screenShot("PEO Registration Approval", "Pass", "RPEO-003 page is displayed");
		PEOPage.approvedToggeleButton.click();

		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Registration Confirmation", "Pass", "SUC-002 page is displayed");

		commonFuntions.clickButtonContains("Home");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "PASS", "Home screen is displayed");
		
		//////////////////////////////////
		

		// Step-55
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		commonFuntions.clickMenu("Professional Employer Organization (PEO)");
		sleep(2000);
		commonFuntions.ScrollMenu("Inquiry PEO Information");
		commonFuntions.clickMenu("Inquiry PEO Information");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		sleep(2000);

		//
		commonFuntions.enterTextboxContains("PEO Name", peoName);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		Thread.sleep(5000);
		sreg084.selectradioBtn1.click();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Group Inquiry", "Pass", "IPEO-002 screen is displayed");

	}

}
