package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SREG_EM_mod {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_EM_mod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Member/Managing Member ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[2]/mat-cell[9]//a[3]")
	public WebElement actionInactive;

	@FindBy(how = How.XPATH, using = "//*[.='Sole Propertor Ship']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[8]//a[1]")
	public WebElement actionEdit;

	@FindBy(how = How.XPATH, using = "//*[.='Individual as Member/Managing Member ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[2]/mat-cell[9]//a[2]")
	public WebElement actionDelete;
	
	@FindBy(how = How.XPATH, using = "//*[.='Executor/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[2]/mat-cell[10]//a[1]")
	public WebElement actionEditExecutor;
	
	@FindBy(how = How.XPATH, using = "//*[.='Executor/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[7]/mat-cell[10]//a[2]")
	public WebElement actionInactiveExecutor;
	
	@FindBy(how = How.XPATH, using = "//*[.='Corporate Officer/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[3]/mat-cell[9]//a[2]")
	public WebElement actionInactiveOfficer;
	
	@FindBy(how = How.XPATH, using = "//*[.='Corporate Officer/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[3]/mat-cell[9]//a[1]")
	public WebElement actionEditOfficer;
	
	@FindBy(how = How.XPATH, using = "//*[.='Corporate/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[1]")
	public WebElement actionEditCorprate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Corporate Officer ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[3]")
	public WebElement actionInactiveCorprate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Corporate Officer ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[2]")
	public WebElement actionDeleteCorprate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Partner ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[10]//a[2]")
	public WebElement actionDeletePartner;
	
	@FindBy(how = How.XPATH, using = "//*[.=' Individual as Trustee ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[2]")
	public WebElement actionInactiveTrustee;
	
	@FindBy(how = How.XPATH, using = "//*[.='Member/Managing Member Details']//following::*[contains(@id ,'dataTable')][1]/mat-row[1]/mat-cell[9]//a[2]//*[.='Member/Managing Member Details']//following::*[contains(@id ,'dataTable')][1]/mat-row[1]/mat-cell[9]//a[2]")
	public WebElement actionInactiveMember;
	
	
	//310 series ---SREG 011
	@FindBy (how = How.XPATH, using = "//textarea")
	public WebElement LegalNameOfBusiness;
	
	//SREG 524

@FindBy(how = How.XPATH, using = ".//*[@data-label='Joint Employment Start Date']//input[@id='dataTableId'][1]")
public WebElement JointEmploymentStartDate;

@FindBy(how = How.XPATH, using = "//*[.='Joint Employment/Management Agreement Arrangement ']//following::*[contains(@id ,'dataTable')][1]/mat-row[1]/mat-cell[5]//input[1]")
public WebElement JointEmploymentStartDate1;

@FindBy(how = How.XPATH, using = "//*[.='Joint Employment/Management Agreement Arrangement ']//following::*[contains(@id ,'dataTable')][1]/mat-row[2]/mat-cell[5]//input[1]")
public WebElement JointEmploymentStartDate2;

@FindBy(how = How.XPATH, using = "//*[.='List of Members of Joint Account']//following::*[contains(@id ,'dataTable')][1]/mat-row[1]/mat-cell[2]")
public WebElement JointAccountRadio;

}

