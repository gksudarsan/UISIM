package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PEO_001_ProfessionalEmployerOrganizationRegistration {
	
	public WebDriver driver;

	public PEO_001_ProfessionalEmployerOrganizationRegistration(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//label[@for='mat-radio-3-input']//span[@class='mat-radio-inner-circle']")
	public WebElement radioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='peoNameId']")
	public WebElement nameofProfessionalEmployerOrganization;
	
	@FindBy(how = How.XPATH, using = "//input[@id='peoNameId']")
	public WebElement additionalNames;
	
	@FindBy(how = How.XPATH, using = "//button[@id='PEO-001access.saveandcontinue']")
	public WebElement saveAndContinue;
	
	public void selectRegDetails() throws InterruptedException {
		radioButton.click();
		nameofProfessionalEmployerOrganization.sendKeys("testtest");
		additionalNames.sendKeys("test1");
		Thread.sleep(2000);
		saveAndContinue.click();
	}

}
