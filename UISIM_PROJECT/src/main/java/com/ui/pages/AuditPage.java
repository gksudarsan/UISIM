package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class AuditPage {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public AuditPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[.='Create UIES Case - Employer Search']//following::*[contains(@id ,'dataTable')][1]/mat-row[1]/mat-cell[1]")
	public WebElement SearchRadiotab;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement commentsId;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mat-mdc-checkbox-4-input']//following::*[@class='mdc-checkbox__native-control'][1]")
		public WebElement Selct_checkbox_tab;
	
	@FindBy(how = How.XPATH, using = "//*[.='Reason for Transfer Request']//following::*[@class='angular-editor-textarea']")
    public WebElement Reason_for_Transfer_Requeste;
}

