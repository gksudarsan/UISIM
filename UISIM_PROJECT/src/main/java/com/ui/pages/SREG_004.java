package com.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_004 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_004(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='First Name']")
	public List<WebElement> listOfFirstname;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Last Name']")
	public List<WebElement> listOfLastName;
	
	@FindBy(how = How.XPATH, using = "(//button[.='Continue '][1])[2]")
	public WebElement popUpContinueButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Address Line 1 ')]//following::input[1]")
	public List<WebElement> addresslinelist;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Care Of')]//following::input[1]")
	public List<WebElement> careOfFieldlist;
	
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'City ')]//following::input[1]")
	public List<WebElement> citylist;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Zip Code')]//following::input[1]")
	public List<WebElement> zipCodelist;
	
	@FindBy(how = How.XPATH, using = "//input[@id='agadAddressId_careOf']")
	public WebElement agadCareOfBtn;
	
	@FindBy(how = How.XPATH, using = "//a//*[text()='Add Member/Managing Member Details']")
	public WebElement addMemberLinkSreg005;
	
	
}
