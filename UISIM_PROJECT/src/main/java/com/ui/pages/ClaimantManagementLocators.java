package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class ClaimantManagementLocators extends TestBase {
	
	public WebDriver webDriver;
	
	public ClaimantManagementLocators(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//input[@id='amountEarnedFilingWeekId']")
	public WebElement amountEarnedFilingWeekIdFieldCIN200;
	
	

}
