package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class BSPPage extends TestBase{
	
	public WebDriver driver;

	public BSPPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@id='helpTextIdicon']//img")
	public WebElement helpIconATA_033;

	@FindBy(how = How.XPATH, using = "//mat-icon[text()='close']")
	public WebElement helpPopupCloseBtnATA_033;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Employer Name']")
	public WebElement employerNameFieldATA035;
	
	@FindBy(how = How.XPATH, using = "//input[@id='empPayPeriodId']")
	public WebElement employerPayPeriodFieldATA035;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='remarksId']")
	public WebElement remarksFieldATA035;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-group//mat-radio-button)[1]")
	public WebElement firstradiobuttonINQ001;
	
	
	

}
