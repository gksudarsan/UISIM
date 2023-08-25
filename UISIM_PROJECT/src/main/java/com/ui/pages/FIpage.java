package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FIpage {

	public WebDriver driver;

	public FIpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'textarea')]")
	public WebElement Entertextarea;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Comments']//following::*[@id='commentsId']")
	public WebElement Entercomments;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement Entercomments_497_002;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement ClickMenu;
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='remarksId']//following::*[@id='remarksId']")
	public WebElement EnterRemarks;
	
	
	@FindBy(how = How.XPATH, using = "//*[text()='Resolution Details']//following::*[@id='bankArtryId']")
	public WebElement Textarea;
	
	
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year Start ')]//following::mat-select[1]")
    public WebElement Quarter_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year Start ')]//following::mat-select[2]")
    public WebElement Year_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year End ')]//following::mat-select[1]")
    public WebElement Quarter_end;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year End ')]//following::mat-select[2]")
    public WebElement Year_end;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
    public WebElement Value_Quarter_start;

    @FindBy(how = How.XPATH, using = "//span[text()=' 3 ']")
    public WebElement Value_Quarter_end;

    @FindBy(how = How.XPATH, using = "//span[text()= ' 2022 ']")
    public WebElement Value_Year;
}
