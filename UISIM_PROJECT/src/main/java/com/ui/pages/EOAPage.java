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
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am a Professional Employer Organization that needs to create an online account for maintaining my client’s associations and Professional Employer Organization registration status.']")
	public WebElement peoRegisteredRadio;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' Same as Contact Number ')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement cellSameasContactNumber;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' TPR - All UI Matters')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement  tprAllUIMatters;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' Employer - Contributions')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement  select_Employer_Contributions;
	
	
	
	
	
	
	
	

}
