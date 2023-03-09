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

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Menu']")
	public WebElement menuButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Account Maintenance']")
	public WebElement accountMaintenance;

	@FindBy(how = How.XPATH, using = "//span[@id='AccountMaintenanceJointAccount']")
	public WebElement jointAccount;

	@FindBy(how = How.XPATH, using = "//span[@id='JointAccountEstablishJointAccount']")
	public WebElement establishJointAccount;

	// @FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag
	// Information']")
	// public WebElement maintenanceAccountFlagInfo;

	// @FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag
	// Information']")
	// public WebElement maintenanceAccountFla;

	@FindBy(how = How.XPATH, using = "//span[text()='Employer Account Maintenance']")
	public WebElement empAccMaintenance;

	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Status']")
	public WebElement maintainAccStatus;

	@FindBy(how = How.XPATH, using = "//span[text()='Sale of Business']")
	public WebElement saleOfBussiness;

	public void navigateToAccountMaintenance() throws Exception {
		stepDef.screenShot("Click_menu", "INFO", "Click menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.screenShot("Click_AC_Maintain_menu", "PASS", "Click AC Maintain menu");
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(empAccMaintenance);
		Thread.sleep(3000);
		stepDef.screenShot("Click_empu_AC_maintainmenu", "PASS", "Click empu AC maintainmenu");
		stepDef.clickElement(maintainAccStatus);
		Thread.sleep(3000);
		stepDef.screenShot("Click_menu_final_ss", "PASS", "Click menu");
	}

	public void navigateToSaleOfBussiness() throws Exception {
		stepDef.screenShot("Menu", "INFO", "Navigating to menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(saleOfBussiness);
		stepDef.screenShot("Menu", "PASS", "Navigating to menu");
	}

	public void navigateToEstablishJointAccount() throws Exception {
		stepDef.screenShot("Menu", "INFO", "Navigating to menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(jointAccount);
		Thread.sleep(3000);
		stepDef.screenShot("Menu", "INFO", "Navigating to menu");
		stepDef.clickElement(establishJointAccount);
		Thread.sleep(3000);
		stepDef.screenShot("Menu", "PASS", "Navigating to menu");
	}
}
