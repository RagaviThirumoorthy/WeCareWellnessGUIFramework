package com.wecare.generic.objectRepository.patientRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientDashboardPage {
	
	public PatientDashboardPage(WebDriver driver) {
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

	@FindBy(xpath = "//a[contains(@href,'book-appointment')]")
	private WebElement bookAppointmentModule;
	
	@FindBy(xpath = "//a[contains(@href,'appointment-history')]")
	private WebElement appointmentHistoryModule;
	
	@FindBy(xpath = "//a[contains(@href,'manage-medhistory')]]")
	private WebElement medicalHistoryModule;
	
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

	public WebElement getBookAppointmentModule() {
		return bookAppointmentModule;
	}

	public WebElement getAppointmentHistoryModule() {
		return appointmentHistoryModule;
	}

	public WebElement getMedicalHistoryModule() {
		return medicalHistoryModule;
	}
	
	
}
