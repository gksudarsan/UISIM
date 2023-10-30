package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='LOG IN ']")
	public WebElement loginLink;

	@FindBy(how = How.XPATH, using = "//*[.=' OK '][@class='mdc-button__label']")
	public WebElement okPopUpButton;
	

	@FindBy(how = How.XPATH, using = "//span[.='BATCH']//preceding::*[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'][1]")
	public WebElement batchFolder;
	@FindBy(how = How.XPATH, using = "//span[.='DEV ENVIRONMENT']//preceding::*[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'][1]")
	public WebElement devEnvironmentFolder;
	@FindBy(how = How.XPATH, using = "//span[.='EC']//preceding::*[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'][1]")
	public WebElement ecFolder;
	@FindBy(how = How.XPATH, using = "//span[.='CA']//preceding::*[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'][1]")
	public WebElement caFolder;

	@FindBy(how = How.XPATH, using = "//*[.=' OK '][@class='mdc-button__label']")
	public WebElement okPopUpButtonBenifit;


}
