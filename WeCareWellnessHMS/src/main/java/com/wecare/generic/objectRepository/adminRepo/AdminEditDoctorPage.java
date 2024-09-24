package com.wecare.generic.objectRepository.adminRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminEditDoctorPage {
	
	public AdminEditDoctorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@name='Doctorspecialization']")
	private WebElement specializationDropdown;
	
	@FindBy(xpath = "//input[@name='docname']")
	private WebElement nameTextfield;
	
	@FindBy(xpath = "//textarea[@name='clinicaddress']")
	private WebElement clinicAddressTextarea;
	
	@FindBy(xpath = "//input[@name='docfees']")
	private WebElement feesTextfield;
	
	@FindBy(xpath="//input[@name='doccontact']")
	private WebElement contactNoTextfield;
	
	@FindBy(id = "docemail")
	private WebElement emailTextfield;
	
	@FindBy(name="npass")
	private WebElement passwordTextfield;
	
	@FindBy(name = "cfpass")
	private WebElement confirmPasswordTextfield;
	
	@FindBy(name = "submit")
	private WebElement submitButton;
	
	@FindBy(xpath = "//h5[contains(text(),'Doctor Details updated Successfully')]")
	private WebElement updateSuccessMessage;

	public WebElement getSpecializationDropdown() {
		return specializationDropdown;
	}

	public WebElement getNameTextfield() {
		return nameTextfield;
	}

	public WebElement getClinicAddressTextarea() {
		return clinicAddressTextarea;
	}

	public WebElement getFeesTextfield() {
		return feesTextfield;
	}

	public WebElement getContactNoTextfield() {
		return contactNoTextfield;
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

	public WebElement getUpdateSuccessMessage() {
		return updateSuccessMessage;
	}
	
	

}
