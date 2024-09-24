package com.wecare.generic.objectRepository.patientRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientLoginPage {

	public PatientLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	private WebElement usernameTextField;
	
	@FindBy(name = "password")
	private WebElement passwordTextField;

	@FindBy(name="submit")
	private WebElement loginButton;
	
	@FindBy(partialLinkText = "Create an account")
	private WebElement createAccountButton;

	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getCreateAccountButton() {
		return createAccountButton;
	}

	public void loginApplication(String username, String password) {
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}
}
