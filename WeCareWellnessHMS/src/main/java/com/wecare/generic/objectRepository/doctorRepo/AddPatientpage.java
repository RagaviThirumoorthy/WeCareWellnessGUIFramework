package com.wecare.generic.objectRepository.doctorRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPatientpage {
	
	public AddPatientpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "patname")
	private WebElement patientNameTextfield;
	
	@FindBy(name = "patcontact")
	private WebElement patientContactNoTextfield;
	
	@FindBy(id = "patemail")
	private WebElement patientEmailTextfield;
	
	@FindBy(xpath = "//label[@for='rg-female']")
	private WebElement genderFemaleRadioButton;
	
	@FindBy(xpath = "//label[@for='rg-male']")
	private WebElement genderMaleRadioButton;
	
	@FindBy(name = "pataddress")
	private WebElement patientAddressTextarea;
	
	@FindBy(name = "patage")
	private WebElement patientAgeTextfield;
	
	@FindBy(name = "medhis")
	private WebElement patientMedicalHistoryTextfield;
	
	@FindBy(id="submit")
	private WebElement addPatientButton;

	public WebElement getPatientNameTextfield() {
		return patientNameTextfield;
	}

	public WebElement getPatientContactNoTextfield() {
		return patientContactNoTextfield;
	}

	public WebElement getPatientEmailTextfield() {
		return patientEmailTextfield;
	}

	public WebElement getGenderFemaleRadioButton() {
		return genderFemaleRadioButton;
	}

	public WebElement getGenderMaleRadioButton() {
		return genderMaleRadioButton;
	}

	public WebElement getPatientAddressTextarea() {
		return patientAddressTextarea;
	}

	public WebElement getPatientAgeTextfield() {
		return patientAgeTextfield;
	}

	public WebElement getPatientMedicalHistoryTextfield() {
		return patientMedicalHistoryTextfield;
	}

	public WebElement getAddPatientButton() {
		return addPatientButton;
	}
	
	

}
