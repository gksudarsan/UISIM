package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class B_BCL_Page {

	public WebDriver driver;

	public B_BCL_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'mat-mdc-input-element')]")   
	public WebElement AddCommentPrintJudgment;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mdc-checkbox']//following::*[contains(@id, 'dataTable_2599_selectCheckBox')]")
	public WebElement selectCheckBoxPrintJudgment;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'')]//following::mat-select[1]")
	public WebElement clickDropdown1PrintJudgment;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' New York']")
	public WebElement selectDropdown1PrintJudgment;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'')]//following::mat-select[2]")
	public WebElement clickDropdown2PrintJudgment;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' DOL Judgment']")
	public WebElement selectDropdown2PrintJudgment;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'static-link')]")
	public WebElement clickRepaymentIdLink;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,' Contact Information Correct')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement checkBoxContactInfoCorrect;
	

}
