package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class FIS_008 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public FIS_008(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[contains(@aria-label,'I do not believe the business is required to file the')]")
	public WebElement iDoNotCommentField;
	
	@FindBy(how = How.XPATH, using = "//textarea[contains(@aria-label,'Other reason you believe this penalty should not be')]")
	public WebElement otherReasonCommentField;
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Enter Protest Resolution Details']")
	public WebElement enterProtestCommentField;
	
	@FindBy(how = How.XPATH, using = "(//u[text()='Failure To File Penalty Protest'])[1]")
	public WebElement failureToFilePenaltyProtestWorkItem;
	

}
