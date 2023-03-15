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
	
	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	public WebElement maintenanceAccountFlagInfo;

	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	public WebElement maintenanceAccountFla;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Employer Account Maintenance']") 
	public WebElement empAccMaintenance;

	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Status']")
	public WebElement maintainAccStatus;

	@FindBy(how = How.XPATH, using = "//span[text()='Sale of Business']")
	public WebElement saleOfBussiness;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Void Transfer']") 
	public WebElement voidTransfer;
	@FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-tns-c112-41 ng-star-inserted']//span[@id='AccountMaintenanceEmployerAccountMaintenance']")
	public WebElement employerAccountMaintenanceMain;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Accounts']")
	public WebElement maintainAccounts;
	
	@FindBy(how = How.XPATH, using = "//span[@id='EmployerAccountMaintenanceJointAccount']")
	public WebElement EAMjointAccount;
	//span[@id='EmployerAccountMaintenanceJointAccount']
	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	public WebElement maintainAccountflagInfo;
	
	@FindBy(how = How.XPATH, using = "//span[@id='AccountMaintenanceJointAccount']")
	public WebElement jointAccount;

	@FindBy(how = How.XPATH, using = "//span[@id='JointAccountEstablishJointAccount']")
	public WebElement establishJointAccount;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ProfessionalEmployerOrganization(PEO)']")
	public WebElement professionalEmployerOrganization;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ProfessionalEmployerOrganization(PEO)RegisterPEO']")
	public WebElement registerPeo;
	
	public void navigateToAccountMaintenance() throws Exception {
		stepDef.screenShot("Click_menu", "Pass", "Click menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.screenShot("Click_AC_Maintain_menu", "PASS", "Click AC Maintain menu");
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(maintainAccountflagInfo);
	}

	public void navigateToSaleOfBussiness() throws Exception {
		stepDef.screenShot("Menu", "Pass", "Navigating to menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(saleOfBussiness);
		stepDef.screenShot("Menu", "Pass", "Navigating to menu");
	}

	public void navigateToEstablishJointAccount() throws Exception {
		stepDef.screenShot("Menu", "Pass", "Navigating to menu");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(jointAccount);
		Thread.sleep(3000);
		stepDef.screenShot("Menu", "Pass", "Navigating to menu");
		stepDef.clickElement(establishJointAccount);
		Thread.sleep(3000);
		stepDef.screenShot("Menu2", "PASS", "Navigating to menu");
	}
	
	public void navigateToMaintainAccounts() throws Exception {
		stepDef.screenShot("Menu", "Pass", "Navigating to menu fail");
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.screenShot("Menu2", "Pass", "Navigating to employerAccountMaintenanceMain");
		stepDef.waitForElementClicable(employerAccountMaintenanceMain);
		stepDef.waitForElementClicable(maintainAccounts);
	}
	
	/* For Test case------- EM_453_01_001 -------------*/
	public void navigateToMaintainJointAccount() throws Exception {
		stepDef.screenShot("Menu", "Pass", "Navigating to menu ");
		stepDef.clickElement(menuButton);
		Thread.sleep(2000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(2000);
		empAccMaintenance.click();
		stepDef.waitForElementClicable(EAMjointAccount);
	}
			public void navigateToVoidTransfer() throws InterruptedException {
				stepDef.clickElement(menuButton);
				Thread.sleep(3000);
				stepDef.clickElement(accountMaintenance);
				Thread.sleep(3000);
				stepDef.clickElement(empAccMaintenance);
				Thread.sleep(3000);	
				stepDef.clickElement(voidTransfer);
				
	}		
			/* For Test case------- EM_441_007 -------------*/
			public void navigateToPeoRegister() throws InterruptedException {
				stepDef.clickElement(menuButton);
				Thread.sleep(2000);
				stepDef.clickElement(professionalEmployerOrganization);
				Thread.sleep(2000);
				stepDef.clickElement(registerPeo);
		
			}
		
}
