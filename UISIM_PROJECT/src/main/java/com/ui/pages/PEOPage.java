package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PEOPage {
	public WebDriver driver;

	public PEOPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId']/mat-row[1]/mat-cell[9]/a/u")
	public WebElement editLink;
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-container']/mat-card/div/app-requestcertificatetax/form/div/mat-card/div/div[2]/app-date/div/div/mat-form-field/div/div[1]")
	public WebElement dateEnter;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id01']/mat-row/mat-cell[1]/mat-checkbox/label/span[1]")
	public WebElement selectRadiobutton; 
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_radio_0_1_radio_button']/label/span[1]/span[1]")
	public WebElement selectRadiobutton1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_radio_0_1_radio_button']/label/span[1]/span[1]")
	public WebElement selectRadiobutton2;
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-container']/mat-card/div/app-requestcertificatetax/form/div/mat-card/div/app-textarea/div/div/mat-form-field/div/div[1]")
	public WebElement textBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='employerRateDetails']/mat-row[1]/mat-cell[2]/a/u")
	public WebElement currentYearwages;
	
	@FindBy(how = How.XPATH, using = "//*[@id='employerRateHistory']/mat-row[1]/mat-cell[1]/a/u")
	public WebElement rateYeartxn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-container']/mat-card/div/app-employeraccountrates/form/div/mat-card/div/mat-table/mat-row[1]/mat-cell[2]/app-row-link/div/div/a")
	public WebElement traxnTYPE;
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-container']/mat-card/div/app-employeraccountinformation/form/div/mat-card/a[2]/u")
	public WebElement pastYear;

	@FindBy(how = How.XPATH, using = "//*[@id='employerRateHistory']/mat-row[1]/mat-cell[1]/a/u")
	public WebElement rateYear;

	@FindBy(how = How.XPATH, using = "//*[@id='id_767']")
	public WebElement accountHistory;
		
	@FindBy(how = How.XPATH, using = "//*[@id='id_768']")
	public WebElement bankAccountsInquiry;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_784']")
	public WebElement viewCorrespondence;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_770']")
	public WebElement addressDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_771']")
	public WebElement ownershipDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_772']")
	public WebElement jointAccountDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_775']")
	public WebElement accountMaintenance;	

	@FindBy(how = How.XPATH, using = "//*[@id='id_774']")
	public WebElement currentBalance;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_773']")
	public WebElement inquiryPEOInformation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_776']")
	public WebElement penaltyDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_777']")
	public WebElement flagDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_778']")
	public WebElement commentsHistory;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_779']")
	public WebElement pOATPRDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_780']")
	public WebElement annualRateCalculation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_781']")
	public WebElement businessPrincipalActivityDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_782']")
	public WebElement crossReferences;
	
	@FindBy(how = How.XPATH, using = "//*[@id='id_783']")
	public WebElement assignmentHistory;
	
	
	
	
	
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"dataTableId_thirdpartyAgentId_0_1_radio_button\"]/label/span[1]/span[1]")
	public WebElement desiradio;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-datepicker-11\"]/div/mat-month-view/table/tbody/tr[4]/td[4]/button/div[1]")
	public WebElement dateselect;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentId']")
	public WebElement comment;
	
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1']/label/span[1]")
	public WebElement checked;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId']/mat-row/mat-cell[6]/mat-form-field/div/div[1]")
	public WebElement enddate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Association End Date']//following::*[@id='dataTableId'][1]//following::input[1]")
	public WebElement calender;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Filter ')]")
	public WebElement filter;
	
	
	@FindBy(how = How.XPATH, using = "//*[.='PEO Individual Registration']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement individualPeo;

	@FindBy(how = How.XPATH, using = "//span[@id='ProfessionalEmployerOrganization(PEO)'][1]")
	public WebElement menuPeo;
	
	@FindBy(how = How.XPATH, using = "//span[@id='InquiryProfessionalEmployerOrganization(PEO)'][1]")
	public WebElement menuInquiryPeo;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'PEO Conversion')]//following::mat-select[1]")
	public WebElement selectionPeoDropdown;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'mat-option mat-focus-indicator')][4]")
	public WebElement conversionPeoDropdown;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']")
	public WebElement addressLine1;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_address2']")
	public WebElement addressLine2;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']")
	public WebElement addressCity;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']")
	public WebElement addressZip;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressLine1ErrorLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_address2']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressLine2ErrorLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressCityErrorLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressZipErrorLabel;

	@FindBy(how = How.XPATH, using = "//*[.='Primary Physical Address']//following::*[@class='mdc-radio__native-control'][2]")
	public WebElement uspsAddress;

	@FindBy(how = How.XPATH, using = "//*[.='Current Additional Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement currentAdditionalAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='Mailing Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement mailingAdditionalAddress;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Approved')][@class='mat-radio-label-content']//preceding::*[@class='mdc-radio__native-control']")
	public WebElement approveRadioButton;
	

	@FindBy(how = How.XPATH, using = "//*[@id='CIN-999access.continue']")
	public WebElement UspsContinueButton;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_attention']")
	public WebElement attentionCareOf;

	@FindBy(how = How.XPATH, using = "//a[text()=' + ADD PEO MEMBER ']")
	public WebElement addPeoMember;

	//@FindBy(how = How.XPATH, using = "//mat-icon[.='task']")
	@FindBy(how = How.XPATH, using = "//*[@mattooltip='My Q']")
	public WebElement queue;

	@FindBy(how = How.XPATH, using = "//*[.='PEO Group Registration']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement groupRegPeo;

	@FindBy(how = How.XPATH, using = "//*[.='USPS Suggested Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement uspsSuggestedAddress;

	@FindBy(xpath = "//*[.='PEO Exempt Registration']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement peoExemptRegisterRadio;

	@FindBy(how = How.XPATH, using = "//a[text()=' ADVANCED SEARCH']")
	public WebElement advancedSearch;

	@FindBy(how = How.XPATH, using = "//strong[text()='Client List']")
	public WebElement clientListHeading;

	@FindBy(how = How.XPATH, using = "//span[text()='Federal Employer Identification Number']/../following-sibling::mat-label")
	public WebElement feinValue;

	@FindBy(how = How.XPATH, using = "//*[.='Mailing Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement mailingAddress;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'ADD PEO MEMBER')][1]")
	public WebElement peoAddMember;

	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_selectedPeoId_0_1_radio_button']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement peoRenewRadioBtn;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'PEO Registration')][1]")
	public WebElement peoRegister;

	@FindBy(how = How.XPATH, using = "//*[@class='mdc-radio__native-control'][1]")
	public WebElement peoRadioButton;

	@FindBy(how = How.XPATH, using = "//strong[text()='Browse']")
	public WebElement browserLinkManagePEOPage;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Uploaded Documents']")
	public WebElement uploadeDocManagePEOPage;

	@FindBy(how = How.XPATH, using = "//a[text()=' Add Additional Address ']")
	public WebElement clickOnAdditionalLink;

	@FindBy(how = How.XPATH, using = "//span[text()='PEO Status']/../following-sibling::mat-label")
	public WebElement withdrawnMessage;

	@FindBy(how = How.XPATH, using = "//li[text()='PEO Status Updated Successfully']")
	public WebElement updatePeoMessage;

	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()-1]")
	public WebElement otherDetails;

	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()]")
	public WebElement otherDetails1;

	@FindBy(how = How.XPATH, using = "//*[.='business Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement uspsAdd;

	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()-1]")
	public WebElement otherDetails_New1;

	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()]")
	public WebElement otherDetails1_New2;

	public Map<String, String> database_SelectQuery(String query) throws SQLException, IOException {

		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java" + "\\com\\ui\\configuration\\config.properties");
		prop.load(ip);
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			i = i + 1;
			if (i > 2) {
				results.put("Fein", rs.getString("FEIN"));
				results.put("Ean", rs.getString("EAN"));
				results.put("legalName", rs.getString("ENTITY_NAME"));
				break;
			}
		}

		ResultSet rs1 = stmt
				.executeQuery("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE FEIN='" + results.get("Fein") + "'");
		if (rs1.next() == false) {
			System.out.println("ResultSet in empty");
		} else {
			long feinValue = Long.valueOf(results.get("Fein")).longValue() + 1;
			String ernValue = StringUtils.left(results.get("Ean"), 5)
					+ String.valueOf((long) (Math.random() * Math.pow(10, 10)));

			String updateQuery = "UPDATE T_TX_PEO_ACCOUNT SET FEIN = '" + feinValue
					+ "' AND EMPLOYER_REGISTRATION_NUMBER = '" + ernValue + "' WHERE FEIN='"
					+ Long.valueOf(results.get("Fein")).longValue() + "'";
			System.out.println(updateQuery);
			PreparedStatement p = null;
			// Statement stmt=con.createStatement();
			// stmt.executeQuery(query);
			p = con.prepareStatement(updateQuery);
			p.execute();
		}

		con.close();
		return results;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_address1']")
	public WebElement listCurrentAddressLine1;

	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_address2']")
	public WebElement listCurrentAddressLine2;

	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_city']")
	public WebElement listCurrentAddressCity;

	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_zip']")
	public WebElement listCurrentAddressZip;

	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddress.uspsaddress-input'][@name='mat-radio-group-34']")
	public WebElement CurrentUspsAddress;

	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddress.mailingaddress-input']")
	public WebElement mailingUspsAddress;

	@FindBy(how = How.XPATH, using = "//*[.=' Address']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement enteredAddress;

	@FindBy(how = How.XPATH, using = "//*[@id='CIN-999access.continue']")
	public WebElement popContinueButton;

	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddres.physicaladdress-input'][@name='mat-radio-group-28']")
	public WebElement PrimaryPhysicalAddress;

	@FindBy(how = How.XPATH, using = "//*[@id='addressTypeId_DIFF-input']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement SameAsPhysicalAddress;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']/following-sibling::mat-label")
	public WebElement getERN;

	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']")
	public WebElement address0_address1_PEO003;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']")
	public WebElement address0_city_PEO003;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']")
	public WebElement address0_zip_PEO003;
	
	@FindBy(how = How.XPATH, using = "//*[@id='phone0']")
	public WebElement phone0_PEO003;
	
	@FindBy(how = How.XPATH, using = "//*[@id='email0']")
	public WebElement email0_PEO003;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'I am a Third Party')]//following::*[@class='mdc-radio__native-control'][@value='3'][1]")
	public WebElement PeoUserSelect;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Federal Employer Identification Number (FEIN)')]//following::input[1]")
	public WebElement clearFeinFieldSection;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Employer Registration Number')]//following::input[1]")
	public WebElement clearErnFieldSection;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Issued']/ancestor::mat-cell/preceding-sibling::mat-cell/mat-radio-group/mat-radio-button//div/input[@class='mdc-radio__native-control']")
	public WebElement Issued_RadioButton;
	
	@FindBy(how = How.XPATH, using = "//span[@id='InquiryProfessionalEmployerOrganization(PEO)'][1]")
	public WebElement PeoMenu;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_address1']")
	public WebElement PeoAddress1;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_city']")
	public WebElement PeoCity1;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_zip']")
	public WebElement PeoZipCode;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"Country\")]//following::mat-select[1])[last()]") 
	public WebElement peoCountry; 

	@FindBy(how = How.XPATH, using = "//span[text()=' Algeria ']") 
	public WebElement peoCountryvalue; 
	
	@FindBy(how = How.XPATH, using = "(//input[@id='dateId'])[last()]") 
	public WebElement dateFeild;
	
	@FindBy(how = How.XPATH, using = "//input[@id='physicalAddressId_address1']") 
	public WebElement peoMemberAddresLine;
	
	@FindBy(how = How.XPATH, using = "//input[@id='physicalAddressId_city']") 
	public WebElement peoMemberCity;
	
	@FindBy(how = How.XPATH, using = "//input[@id='physicalAddressId_zip']") 
	public WebElement peoMemberZipCode;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'State')]//following::mat-select[1])[last()-1]")
	public WebElement peoMemberState;

	@FindBy(how = How.XPATH, using = "//span[text()=' New York ']")
	public WebElement peoMemberStateValue;		
	
	@FindBy(how = How.XPATH, using = "//*[.='']//following::span[contains(.,'Same As Physical Address')][1]//preceding::*[@class='mat-radio-outer-circle'][1]")
	public WebElement peoMember_radioButton;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Quarter ')]//following::mat-select[1])[last()]")
	public WebElement selectQuarter;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Year ')]//following::mat-select[1])[last()]")
	public WebElement selectYear;
			
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Country')]//following::mat-select[1][@id='address0_country']")
	public WebElement PeoCountry;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//span[text()='PEO ID']/../following-sibling::mat-label")
	public WebElement peoIDText;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@value='ISSD']//following::*[@class='mdc-radio__native-control'][@value='ISSD'][1]")
	public WebElement ApprovePeo;
	
	@FindBy(how = How.XPATH, using = ".//span[@id='ProfessionalEmployerOrganization(PEO)']//following::*[.='Manage PEO'][1]")
	public WebElement managePeoInMenu;
	
	@FindBy(how = How.XPATH, using = "//h2[.='Approval Confirmation'][1]")
	public WebElement successMessage;
	
	public Map<String, String> database_SelectQuery2(String query) throws SQLException, IOException {

		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java" + "\\com\\ui\\configuration\\config.properties");
		prop.load(ip);
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			i = i + 2;
			if (i > 3) {
				results.put("Fein", rs.getString("FEIN"));
				results.put("Ean", rs.getString("EAN"));
				results.put("legalName", rs.getString("ENTITY_NAME"));
				break;
			}
		}

		ResultSet rs1 = stmt
				.executeQuery("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE FEIN='" + results.get("Fein") + "'");
		if (rs1.next() == false) {
			System.out.println("ResultSet in empty");
		} else {
			long feinValue = Long.valueOf(results.get("Fein")).longValue() + 1;
			String ernValue = StringUtils.left(results.get("Ean"), 5)
					+ String.valueOf((long) (Math.random() * Math.pow(10, 10)));

			String updateQuery = "UPDATE T_TX_PEO_ACCOUNT SET FEIN = '" + feinValue
					+ "' AND EMPLOYER_REGISTRATION_NUMBER = '" + ernValue + "' WHERE FEIN='"
					+ Long.valueOf(results.get("Fein")).longValue() + "'";
			System.out.println(updateQuery);
			PreparedStatement p = null;
			// Statement stmt=con.createStatement();
			// stmt.executeQuery(query);
			p = con.prepareStatement(updateQuery);
			p.execute();
		}

		con.close();
		return results;
	}
	
	public Map<String, String> database_SelectQuery3(String query) throws SQLException, IOException {

		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java" + "\\com\\ui\\configuration\\config.properties");
		prop.load(ip);
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			i = i + 3;
			if (i > 4) {
				results.put("Fein", rs.getString("FEIN"));
				results.put("Ean", rs.getString("EAN"));
				results.put("legalName", rs.getString("ENTITY_NAME"));
				break;
			}
		}

		ResultSet rs1 = stmt
				.executeQuery("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE FEIN='" + results.get("Fein") + "'");
		if (rs1.next() == false) {
			System.out.println("ResultSet in empty");
		} else {
			long feinValue = Long.valueOf(results.get("Fein")).longValue() + 1;
			String ernValue = StringUtils.left(results.get("Ean"), 5)
					+ String.valueOf((long) (Math.random() * Math.pow(10, 10)));

			String updateQuery = "UPDATE T_TX_PEO_ACCOUNT SET FEIN = '" + feinValue
					+ "' AND EMPLOYER_REGISTRATION_NUMBER = '" + ernValue + "' WHERE FEIN='"
					+ Long.valueOf(results.get("Fein")).longValue() + "'";
			System.out.println(updateQuery);
			PreparedStatement p = null;
			// Statement stmt=con.createStatement();
			// stmt.executeQuery(query);
			p = con.prepareStatement(updateQuery);
			p.execute();
		}

		con.close();
		return results;
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Ext')]//following::input[1]")
	public List<WebElement> extFieldList;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_address1']")
	public WebElement listtheCurrentAddressaddressLine1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_city']")
	public WebElement listtheCurrentCity;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_zip']")
	public WebElement listtheCurrentZipcode;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Enter name of Officer, Partner, Proprietor or Member']")
	public WebElement officerPartnerField;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button//span[text()=' Approved ']")
	public WebElement approvedToggeleButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label//span[text()='PEO Group ID']/following::mat-label[contains(text(),'PM')]")
	public WebElement peoGrpId;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button//span[text()=' Rejected ']")
	public WebElement rejectedToggeleButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='address0_attention']")
	public WebElement attentionCareofFieldPEO003;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalNamesId1']")
	public WebElement additionalNamesId;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,' Approved ')][@class='mdc-form-field']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement radioButton_Approved;

	@FindBy(how = How.XPATH, using = "(//input[@id='dateId'])[1]") 
	public WebElement dateFeild1;
	
	@FindBy(how = How.XPATH, using = "(//input[@id='dateId'])[2]") 
	public WebElement dateFeild2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Same As Physical Address')]//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement sameAsPhysicalAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='System Failure']")
	public WebElement systemFailure;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Error ID: InternalError')]")
	public WebElement internalError;
	

	
}
