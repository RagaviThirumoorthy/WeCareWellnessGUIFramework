package com.wecare.generic.objectRepository.doctorRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DoctorDashboardPage {
	
	public DoctorDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='username']")
	private WebElement profileDropdown;
	
	@FindBy(partialLinkText = "My Profile")
	private WebElement myProfileButton;
	
	@FindBy(partialLinkText = "Change Password")
	private WebElement changePasswordButton;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private WebElement dashboardModule;
	
	@FindBy(xpath = "//a[contains(@href,'appointment-history')]")
	private WebElement appointmentHistoryModule;
	
	@FindBy(xpath = "//span[contains(text(),'Patients')]")
	private WebElement patientsModule;
	
	@FindBy(xpath = "//a[contains(@href,'add-patient')]")
	private WebElement addpatientModule;
	
	@FindBy(xpath = "//a[contains(@href,'manage-patient')]")
	private WebElement managePatientModule;
	
	public WebElement getProfileDropdown() {
		return profileDropdown;
	}

	public WebElement getMyProfileButton() {
		return myProfileButton;
	}

	public WebElement getChangePasswordButton() {
		return changePasswordButton;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public WebElement getDashboardModule() {
		return dashboardModule;
	}

	public WebElement getAppointmentHistoryModule() {
		return appointmentHistoryModule;
	}

	public WebElement getPatientsModule() {
		return patientsModule;
	}

	public WebElement getAddpatientModule() {
		return addpatientModule;
	}

	public WebElement getManagePatientModule() {
		return managePatientModule;
	}

	public void setManagePatientModule(WebElement managePatientModule) {
		this.managePatientModule = managePatientModule;
	}
	
	
	
	
}
