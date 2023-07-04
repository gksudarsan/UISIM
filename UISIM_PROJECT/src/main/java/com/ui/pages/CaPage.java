package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CaPage {
	public WebDriver driver;

	public CaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_dueAmount_0']")
	public WebElement dueDateFromTable;

	@FindBy(how = How.XPATH, using = "//*[.=' OK '][@class='mat-button-wrapper']")
	public WebElement okPopUpButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='paymentNumber']//mat-row[1]/mat-cell[1]//u[1]")
	public WebElement paymentNumberLink;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId']//mat-row[1]/mat-cell[1]//u[1]")
	public WebElement currentPaymentAllocationDetailsLink;
	
	
	
	

}
