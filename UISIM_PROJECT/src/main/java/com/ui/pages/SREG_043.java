package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_043 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_043(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Submitter Comments may be entered below.')]//following::mat-form-field")
	public WebElement submitterCommentsField;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'mat-form-field')]//textarea[contains(@id,'comment')]")
	public WebElement EEWI002CommentsField;
	
	
	
	
	
	

}
