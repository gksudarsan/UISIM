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
}

