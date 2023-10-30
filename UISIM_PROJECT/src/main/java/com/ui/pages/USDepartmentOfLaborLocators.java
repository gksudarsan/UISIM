package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class USDepartmentOfLaborLocators extends TestBase {

	public WebDriver driver;

	public USDepartmentOfLaborLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;

	@FindBy(how = How.XPATH, using = "//*[.=' OK '][@class='mat-button-wrapper']")
	public WebElement okPopUpButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Nonmonetary Review (Non Separations)']//following::mat-select[1]")
	public WebElement nonMonNonSepDropdown;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Nonmonetary Review (Separations)']//following::mat-select[1]")
	public WebElement nonMonSepDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
	public WebElement value_2;
	
	@FindBy(how = How.XPATH, using = "//*[@id='nonsepyearidyear']")
	public WebElement nonSepYearId;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Nonmonetary Review (Non Separations)']//following::mat-label[contains(.,'Seed Number')]//following::input[1]")
	public WebElement nonSepSeedNo;
	
	@FindBy(how = How.XPATH, using = "//*[@id='sepyearidyear']")
	public WebElement sepYearId;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Nonmonetary Review (Separations)']//following::mat-label[contains(.,'Seed Number')]//following::input[1]")
	public WebElement sepSeedNo;
}
