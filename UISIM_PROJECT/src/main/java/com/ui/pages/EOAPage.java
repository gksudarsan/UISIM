package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EOAPage extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public EOAPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am a Third Party Representative who needs to create an online account to represent my clients in Unemployment Insurance matters.']")
	public WebElement tprRepresentativeRadio;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mat-mdc-checkbox-2-input']")
    public WebElement checkboxContactNumber;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am an Employer who needs to create an online account for Unemployment Insurance purposes.']")
	public WebElement employerRadio;
	
	@FindBy(how = How.XPATH, using = "//input[@id='tpralluimatters-input']")
    public WebElement tpralluimatters;
	
	@FindBy(how = How.XPATH, using = "//input[@id='employercontributions-input']")
    public WebElement employercontributionsCheckbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='employerbenefits-input']")
    public WebElement employerbenefitsCheckbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='useradmin-input']")
    public WebElement useradminCheckbox;
	
	@FindBy(how = How.XPATH, using = "//*[.='User Listing']//following::*[contains(@id ,'dataTableId')][1]/mat-row[1]/mat-cell[8]//a[1]")
	public WebElement userListingManageUser;
	
	
	
	

}
