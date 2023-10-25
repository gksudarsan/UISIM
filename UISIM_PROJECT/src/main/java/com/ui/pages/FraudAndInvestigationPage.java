package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class FraudAndInvestigationPage {
	public WebDriver driver;
	public FraudAndInvestigationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId_select_0')]//preceding::*[@class='mat-checkbox-inner-container'][1]")
	public WebElement dataTableId_select_0_checkbox;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Period From Quarter/Year ')]//following::mat-select[1]")
	public WebElement Audit_Quarter;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Period From Quarter/Year ')]//following::mat-select[2]")
	public WebElement Audit_Year;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' To Quarter/Year ')]//following::mat-select[1]")
	public WebElement To_Quarter;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' To Quarter/Year ')]//following::mat-select[2]")
	public WebElement To_Year;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
	public WebElement Value_Audit_Quarter;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 4 ']")
	public WebElement Value_To_Quarter;
	
	@FindBy(how = How.XPATH, using = "//span[text()= ' 2023 ']")
	public WebElement Value_Year;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement commentsId;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='commentsId']")
    public WebElement comments;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='resolutionDet']")
    public WebElement resolutionDet;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTable_1831_select_0_1_radio_button-input']//following::*[@class='mat-radio-inner-circle'][1]")
    public WebElement select_employer;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1-input']")
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1']//*[@class='mat-checkbox-inner-container'][1]")
	public WebElement wedgeInformation_checkBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_select_0-input']")
	public WebElement table_checkBox;
	
	@FindBy(how = How.XPATH, using = "//*[.='Remarks/Reasons for submitting Issue']//following::*[@id='remarksId']")
	public WebElement remarksId;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Is this protest a hearing request ?')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement selectcheckbox;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='reasonBasisUnsatisfiedJudgProtestId']")
    public WebElement reasonBasis;
	
	


}
