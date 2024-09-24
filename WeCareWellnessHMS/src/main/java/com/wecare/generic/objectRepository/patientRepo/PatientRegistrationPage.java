package com.wecare.generic.objectRepository.patientRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientRegistrationPage {
	
	public PatientRegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "full_name")
	private WebElement fullNameTextfield;
	
	@FindBy(name = "address")
	private WebElement addressTextfield;
	
	@FindBy(name = "city")
	private WebElement cityTextfield;
	
	@FindBy(xpath = "//label[@for='rg-female']")
	private WebElement femaleRadioIcon;
	
	@FindBy(xpath = "//label[@for='rg-male']")
	private WebElement maleRadioIcon;
	
	@FindBy(id = "email")
	private WebElement emailTextfield;
	
	@FindBy(id = "password")
	private WebElement passwordTextfield;
	
	@FindBy(id = "password_again")
	private WebElement confirmPasswordTextfield;
	
	@FindBy(id = "submit")
	private WebElement submitButton;
	
	@FindBy(partialLinkText = "Log-in")
	private WebElement loginButton;

	public WebElement getFullNameTextfield() {
		return fullNameTextfield;
	}

	public WebElement getAddressTextfield() {
		return addressTextfield;
	}

	public WebElement getCityTextfield() {
		return cityTextfield;
	}

	public WebElement getFemaleRadioIcon() {
		return femaleRadioIcon;
	}

	public WebElement getMaleRadioIcon() {
		return maleRadioIcon;
	}

	public WebElement getEmailTextfield() {
		return emailTextfield;
	}

	public WebElement getPasswordTextfield() {
		return passwordTextfield;
	}

	public WebElement getConfirmPasswordTextfield() {
		return confirmPasswordTextfield;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	

}
