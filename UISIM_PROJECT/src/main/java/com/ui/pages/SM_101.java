package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SM_101 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SM_101(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='angular-editor-textarea']")
	public WebElement sm101TextMessageField;
	
	@FindBy(how = How.XPATH, using = "(//mat-cell[@aria-label='Submitted']//preceding::mat-radio-button[1])[1]")
	public WebElement sm114SubmittedRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@value='text']//label")
	public WebElement sm112TextRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//mat-dialog-container//button//span[text()='Yes ']")
	public WebElement sm112PopupYesBtn;
	
	
	

}
