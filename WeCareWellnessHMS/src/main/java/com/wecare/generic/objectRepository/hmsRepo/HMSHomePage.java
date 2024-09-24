package com.wecare.generic.objectRepository.hmsRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HMSHomePage {
	
	public HMSHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Logins")
	private WebElement loginMenu;

	@FindBy(xpath = "//a[contains(@href,'admin')]/button")
	private WebElement adminLoginButton;
	
	@FindBy(xpath = "//a[contains(@href,'user')]/button")
	private WebElement patientLoginButton;
	
	@FindBy(xpath = "//a[contains(@href,'doctor')]/button")
	private WebElement doctorLoginButton;

	public WebElement getLoginMenu() {
		return loginMenu;
	}

	public WebElement getAdminLoginButton() {
		return adminLoginButton;
	}

	public WebElement getPatientLoginButton() {
		return patientLoginButton;
	}

	public WebElement getDoctorLoginButton() {
		return doctorLoginButton;
	}

	
}
