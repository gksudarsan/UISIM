package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_008 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_008(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'finishlater')]")
	public WebElement finishLaterButton;
	
	@FindBy(how = How.XPATH, using = "//button//span[text()=' Yes ']")
	public WebElement YesButton;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-group//mat-radio-button)[1]")
	public WebElement firstradiobuttonVerifyAddPopup;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-group//mat-radio-button)[2]")
	public WebElement secondradiobuttonVerifyAddPopup;
	
	
	@FindBy(how = How.XPATH, using = "//h2[text()='Business Physical Address Details']")
	public WebElement businesssreg007;
	
	@FindBy(how = How.XPATH, using = "//mat-cell//a//u[text()='Edit']")
	public WebElement EditBtnsreg007;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' Add Another Business Location ']")
	public WebElement addAnothersreg007;
	
	
	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[text()='TEST'])[1]")
	public WebElement listFirstItemSreg101;
	
	public void Sreg101Results(String xpathParameter) {
		driver.findElement(By.xpath("(//mat-cell//a//u[text()='" + xpathParameter + "'])[1]")).click();
	}
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Ext')]//following::input[1])[1]")
	public WebElement firstExtField;
	
	

}
