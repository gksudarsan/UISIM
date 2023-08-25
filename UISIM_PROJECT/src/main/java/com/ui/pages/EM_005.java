package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EM_005 extends TestBase {
	
	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public EM_005(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanId']")
	public WebElement registerERNInput;
	

	@FindBy(how = How.XPATH, using = "//button[@type='submit']") 
	public WebElement continueButton;
	

	@FindBy(how = How.XPATH, using = "//mat-label[@id=' EM-005']")
	public WebElement pageNameText;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-button[contains(@class,'mat-radio-button')])[1]")
	public WebElement selectToggle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
    public WebElement noRadioOutCircle;

    @FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
    public WebElement noRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
    public WebElement yesRadioOutCircle;

    @FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
    public WebElement yesRadioInCircle;
    
    @FindBy(how = How.XPATH, using = "//*[@id='reasonExplanation']")
    public WebElement reasonExplanation;
    
    @FindBy(how = How.XPATH, using = "//*[@id='apprSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
    public WebElement approveRadioOutCircle;
    
    @FindBy(how = How.XPATH, using = "//*[@id='apprSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
    public WebElement approveRadioInCircle;
    
    @FindBy(how = How.XPATH, using = "//mat-cell[contains(.,'Remove')]")
    public WebElement removeDoc;
    
    @FindBy(how = How.XPATH, using = "//*[@id='sustainCode-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
    public WebElement sustainRadioInCircle;
    
    @FindBy(how = How.XPATH, using = "//*[@id='sustainCode-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
    public WebElement sustainRadioOutCircle;

    @FindBy(how = How.XPATH, using = "//textarea[@aria-label='Enter Protest Resolution Details']")
    public WebElement enterProtestCommentField;
    
    @FindBy(how = How.XPATH, using = "//textarea[@aria-label='Resolution Details']")
    public WebElement enterCommentField;
	
	public void enterDetailInERNField(String ERN) throws Exception {
		Thread.sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		System.out.println("Inside the ERN page");
		stepDef.screenShot("ERN", "Pass", "ERN field input field");
		String pageNametext = pageNameText.getText();
		System.out.println(pageNametext);
		registerERNInput.sendKeys(ERN);
		Thread.sleep(2000);
		stepDef.clickElement(continueButton);
		Thread.sleep(2000);
//		return pageNametext;
	}	
}
