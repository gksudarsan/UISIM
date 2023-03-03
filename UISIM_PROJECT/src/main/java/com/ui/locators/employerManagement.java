package com.ui.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ui.base.TestBase;

public class employerManagement extends TestBase {

	public WebElement menuButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Menu']")));
		return driver.findElement(By.xpath("//span[text()='Menu']]"));

	}
	public WebElement AccountMaintenance (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account Maintenance']")));
		return driver.findElement(By.xpath("//span[text()='Account Maintenance']"));

	}
	public WebElement MaintainAddress (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Maintain Address']]")));
		return driver.findElement(By.xpath("//span[text()='Maintain Address']]"));

	}
	public WebElement editButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-label[text()='Business Physical Location']//following::u[text()='Edit")));
		return driver.findElement(By.xpath("//mat-label[text()='Business Physical Location']//following::u[text()='Edit"));

	}
	public WebElement addressLine1 (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='address1']")));
		return driver.findElement(By.xpath("//input[@formcontrolname='address1']"));

	}
	public WebElement city (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //input[@formcontrolname='city']")));
		return driver.findElement(By.xpath(" //input[@formcontrolname='city']"));

	}
	public WebElement addressLine2 (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='address2']")));
		return driver.findElement(By.xpath("//input[@formcontrolname='address2']"));

	}
	public WebElement pageTitle (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='titleId']]")));
		return driver.findElement(By.xpath("//h2[@id='titleId']"));

	}
	public WebElement submitButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		return driver.findElement(By.xpath("//button[@type='submit']"));

	}
	
	 
   
   

}


