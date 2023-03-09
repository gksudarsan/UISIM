package com.ui.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class HomePage {
	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[text()='Menu']")
	public WebElement menuButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Maintenance']")
	public WebElement accountMaintenance;
	
	//@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	//public WebElement maintenanceAccountFlagInfo;

	//@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	//public WebElement maintenanceAccountFla;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Employer Account Maintenance']") 
	public WebElement empAccMaintenance;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Status']") 
	public WebElement maintainAccStatus;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Sale of Business']") 
	public WebElement saleOfBussiness;
	
	
	
	public void navigateToAccountMaintenance() throws InterruptedException {
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(empAccMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(maintainAccStatus);
		Thread.sleep(3000);
	}
	
	public void navigateToSaleOfBussiness() throws InterruptedException {
			stepDef.clickElement(menuButton);
			Thread.sleep(3000);
			stepDef.clickElement(accountMaintenance);
			Thread.sleep(3000);
			stepDef.clickElement(saleOfBussiness);
			Thread.sleep(3000);	
			
	
	}
}
