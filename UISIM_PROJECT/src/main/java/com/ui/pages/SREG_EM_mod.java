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
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Member/Managing Member ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[3]")
	public WebElement actionInactive;

	@FindBy(how = How.XPATH, using = "//*[.='Sole Proprietor Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[8]//a[1]")
	public WebElement actionEdit;

	@FindBy(how = How.XPATH, using = "//*[.='Individual as Member/Managing Member ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[2]")
	public WebElement actionDelete;
	
	@FindBy(how = How.XPATH, using = "//*[.='Executor/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[2]/mat-cell[10]//a[1]")
	public WebElement actionEditExecutor;
	
	@FindBy(how = How.XPATH, using = "//*[.='Executor/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[7]/mat-cell[10]//a[2]")
	public WebElement actionInactiveExecutor;
	
	@FindBy(how = How.XPATH, using = "//*[.='Corporate Officer/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[3]/mat-cell[9]//a[2]")
	public WebElement actionInactiveOfficer;
	
	@FindBy(how = How.XPATH, using = "//*[.='Corporate Officer/Owner Details']//following::*[contains(@id ,'dataTableId')][1]/mat-row[3]/mat-cell[9]//a[1]")
	public WebElement actionEditOfficer;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Corporate Officer ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[3]")
	public WebElement actionInactiveCorprate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Corporate Officer ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[9]//a[2]")
	public WebElement actionDeleteCorprate;
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Partner ']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[10]//a[2]")
	public WebElement actionDeletePartner;
	
	//310 series ---SREG 011
	@FindBy (how = How.XPATH, using = "//textarea")
	public WebElement LegalNameOfBusiness;
}

