package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PEO_019_PEO_Registration_ContactDetails {
	
	public WebDriver driver;

	public PEO_019_PEO_Registration_ContactDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	public WebElement continueButton;
	
	public void clickContinueButton() {
		continueButton.click();
	}

}
