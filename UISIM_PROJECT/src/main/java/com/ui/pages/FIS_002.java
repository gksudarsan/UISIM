package com.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class FIS_002 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public FIS_002(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Remarks/Reasons for submitting Issue']")
	public WebElement submittingIssueField;
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Comments']")
	public WebElement commentsFieldpfp050;
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Reason/basis for Interest Assessment Surcharge Protest']")
	public WebElement reasonBasisCommentfield;
	
	
    //
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-1']")
    public WebElement quarter1;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
    public WebElement quarterValue1;

    @FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-3']")
    public WebElement year1;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2021 ']")
    public WebElement yearValue1;

    @FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-5']")
    public WebElement quarter2;

    @FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
    public WebElement quarterValue2;

    @FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-7']")
    public WebElement year2;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2022 ']")
    public WebElement yearValue2;

	
	//
    @FindBy(how = How.XPATH, using = "//textarea[@aria-label='Comments']")
	public WebElement PFP020CommentFeild;
    
    @FindBy(how = How.XPATH, using = "(//u[contains(.,'Audit Protest Task')])[1]")
   	public WebElement auditProtestWorkItem;
    
    @FindBy(how = How.XPATH, using = "(//input[@class='mdc-checkbox__native-control'])[1]")
   	public WebElement wf001checkboxFirst;
    
    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Name')]//following::input[@aria-label='Name']")
   	public WebElement fis003NameField;
    
    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Is this protest a hearing request ?')]")
   	public WebElement isThisRequestCheckbox;
  
    
  
  


}
