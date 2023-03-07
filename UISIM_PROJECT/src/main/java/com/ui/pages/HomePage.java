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
	
	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	public WebElement maintenanceAccountFlagInfo;

	@FindBy(how = How.XPATH, using = "//span[text()='Maintain Account Flag Information']")
	public WebElement maintenanceAccountFla;

	@FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-tns-c112-33 ng-star-inserted']//span[@id='AccountMaintenanceEmployerAccountMaintenance']")
	public WebElement employerAccountMaintenence;
	
	@FindBy(how = How.XPATH, using = "//span[@id='AccountMaintenanceJointAccount']")
	public WebElement jointAccount;
	
	@FindBy(how = How.XPATH, using = "//app-collapsible[@class='child-indent ng-tns-c112-31 ng-tns-c112-29 AccountMaintenance ng-star-inserted open']//app-node[2]//div[1]//a[1]//div[1]//div[1]//span[1]")
	public WebElement maintainJointAccount;
	
	@FindBy(how = How.XPATH, using = "//body/app-root/app-pages[@class='ng-star-inserted']/mat-sidenav-container[@class='mat-drawer-container mat-sidenav-container h-100 mat-drawer-container-explicit-backdrop mat-drawer-transition mat-drawer-container-has-open']/mat-sidenav[@class='mat-drawer mat-sidenav ng-tns-c27-1 ng-trigger ng-trigger-transform mat-drawer-over ng-star-inserted mat-drawer-opened']/div[@class='mat-drawer-inner-container ng-tns-c27-1']/app-navigation[@class='ng-tns-c27-1']/div[@class='ng-star-inserted']/app-collapsible[@id='AccountMaintenance']/div[@class='children ng-tns-c112-37 ng-trigger ng-trigger-slideInOut ng-star-inserted']/div[@class='ng-tns-c112-37']/app-node[1]/div[1]/a[1]/div[1]/div[1]/span[1]")
	public WebElement saleOfBusiness;
	
	@FindBy(how = How.XPATH, using = "//body/app-root/app-pages[@class='ng-star-inserted']/mat-sidenav-container[@class='mat-drawer-container mat-sidenav-container h-100 mat-drawer-container-explicit-backdrop mat-drawer-transition mat-drawer-container-has-open']/mat-sidenav[@class='mat-drawer mat-sidenav ng-tns-c27-1 ng-trigger ng-trigger-transform mat-drawer-over ng-star-inserted mat-drawer-opened']/div[@class='mat-drawer-inner-container ng-tns-c27-1']/app-navigation[@class='ng-tns-c27-1']/div[@class='ng-star-inserted']/app-collapsible[@id='AccountMaintenance']/div[@class='children ng-tns-c112-29 ng-trigger ng-trigger-slideInOut ng-star-inserted']/div[@class='ng-tns-c112-29']/app-node[1]/div[1]/a[1]/div[1]/div[2]/mat-icon[1]")
	public WebElement aleOfBusinesArrow;
	
	public void navigateToAccountMaintain() throws InterruptedException {
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(maintenanceAccountFlagInfo);
		Thread.sleep(3000);
	}
	
	public void navigateToSaleOfBusiness() throws InterruptedException {
		stepDef.clickElement(menuButton);
		Thread.sleep(3000);
		stepDef.clickElement(accountMaintenance);
		Thread.sleep(3000);
		stepDef.clickElement(aleOfBusinesArrow);
		Thread.sleep(3000);
		
	}
}
