package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage  {
	public WebDriver driver;
public LoginPage(WebDriver driver){
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
@FindBy(how=How.XPATH,using="//span[text()='LOG IN ']")
public WebElement loginLink;

@FindBy(how=How.XPATH,using="//*[.=' OK '][@class='mat-button-wrapper']")
public WebElement okPopUpButton;

}
