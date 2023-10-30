package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EOAPage {
	public WebDriver driver;

	public EOAPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//*[contains(.,'I am an Employer who needs to create an online account for Unemployment Insurance purposes.')][@class='mdc-form-field']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement radioButton_EOA_Employer;

	@FindBy(how = How.XPATH, using = "//mat-checkbox[contains(.,'')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement selectCheckBox_AutoEnrollment;
	
	@FindBy(how = How.XPATH, using = "//input[@id='ernumberId']")
	public WebElement ernFieldSreg_612;
	
	@FindBy(how = How.XPATH, using = "//input[@id='feinameId']")
	public WebElement feinFieldSreg_612;
	
	@FindBy(how = How.XPATH, using = "//mat-checkbox[contains(.,' Same as Contact Number ')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement selectCheckBox_SameContactNumber;
	
	@FindBy(how = How.XPATH, using = "//mat-checkbox[contains(.,'TPR - All UI Matters')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement selectCheckBox_TprAllUiMatters;
	
	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}
	public void clickOnLinkAnchorTag(String xpathParameter, String value) {
		By element = By.xpath("//mat-cell[@aria-label='" + xpathParameter + "']//following::mat-cell//u[text()='" + value + "']");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='yesNoRadioId_1']//preceding::*[@class='mdc-radio__native-control'][1]")
	public WebElement tpr_radioButton;
}

