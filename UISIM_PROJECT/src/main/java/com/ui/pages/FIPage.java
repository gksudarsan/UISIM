package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FIPage {

	
	public WebDriver driver;

	public FIPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='remarksId']")
	public WebElement remarksReason;

	@FindBy(how = How.XPATH, using = "//u[normalize-space()='Remove']")
	public WebElement removeLink;
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-1']")
	public WebElement quarter1;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 4 ']")
	public WebElement quarterValue1;
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-3']")
	public WebElement year1;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement yearValue1;
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-5']")
	public WebElement quarter2;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 4 ']")
	public WebElement quarterValue2;
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-7']")
	public WebElement year2;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement yearValue2;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='commentsId']")
	public WebElement comments;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='bankArtryId']")
	public WebElement resolutionDetails;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='resolutionDetailsId']")
	public WebElement resDetails;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,' Sustain BCP')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-outer-circle']")
	public WebElement sustainBCP;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,' Cancel BCP ')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-outer-circle']")
	public WebElement cancelBCP;
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-1']")
	public WebElement sustainBcp;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Insufficient Evidence Furnished ']")
	public WebElement sustainBcpValue;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='reasonBasisWageGarnishmentProtestId']")
	public WebElement offsetInterceptProtestReason;
	
}
