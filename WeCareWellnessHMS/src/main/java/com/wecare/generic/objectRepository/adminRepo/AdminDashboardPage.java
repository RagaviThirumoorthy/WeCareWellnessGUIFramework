package com.wecare.generic.objectRepository.adminRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {
	
	public AdminDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='username']")
	private WebElement profileDropdown;
	
	@FindBy(partialLinkText = "Change Password")
	private WebElement changePasswordButton;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//span[text()=' Doctors ']")
	private WebElement doctorModule;
	
	@FindBy(xpath = "//a[contains(@href,'add-doctor')]")
	private WebElement addDoctorModule;
	
	@FindBy(xpath = "//a[contains(@href,'Manage-doctors')]")
	private WebElement manageDoctorsModule;
	
	@FindBy(xpath = "//a[contains(@href,'doctor-logs')]")
	private WebElement doctorSessionLogs;
	
	@FindBy(xpath = "//a[contains(@href,'user-logs')]")
	private WebElement patientSessionLogs;

	public WebElement getProfileDropdown() {
		return profileDropdown;
	}

	public WebElement getChangePasswordButton() {
		return changePasswordButton;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public WebElement getDoctorModule() {
		return doctorModule;
	}

	public WebElement getAddDoctorModule() {
		return addDoctorModule;
	}

	public WebElement getManageDoctorsModule() {
		return manageDoctorsModule;
	}

	public WebElement getDoctorSessionLogs() {
		return doctorSessionLogs;
	}

	public WebElement getPatientSessionLogs() {
		return patientSessionLogs;
	}
	

}
