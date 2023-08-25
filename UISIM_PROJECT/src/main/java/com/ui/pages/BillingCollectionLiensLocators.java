package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class BillingCollectionLiensLocators extends TestBase {
	
	public WebDriver webDriver;
	
	public BillingCollectionLiensLocators(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId')]//preceding::span[@class='mat-radio-container'][1]")
	public WebElement dataTableIdRadio;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement noRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement noRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reasonExplanation']")
	public WebElement reasonExplanation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement commentsId;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'mat-checkbox')]")
	public WebElement matCheckboxId;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement yesRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement yesRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//mat-icon[.='task']")
	public WebElement queue;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Contribution Collection'][1]//following::*[text()='Bankruptcy'][1]")
	public WebElement bankruptcyMenuLocator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='remarks']")
	public WebElement remarks;

	@FindBy(how = How.XPATH, using = "//*[@id='apprSelected']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement approveRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='apprSelected']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement approveRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='comments']")
	public WebElement comments;
}
