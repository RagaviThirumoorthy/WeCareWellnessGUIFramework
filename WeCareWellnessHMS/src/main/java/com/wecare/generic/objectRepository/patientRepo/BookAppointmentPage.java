package com.wecare.generic.objectRepository.patientRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAppointmentPage {
	
	public BookAppointmentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "Doctorspecialization")
	private WebElement specializationDropdown;
	
	@FindBy(id = "doctor")
	private WebElement doctorDropdown;
	
	@FindBy(xpath = "//select[@id='fees']/option")
	private WebElement consultancyFeesValue;
	
	@FindBy(xpath = "//input[@name='appdate']")
	private WebElement datePicker;
	
	@FindBy(xpath = "(//div[@class='datepicker-days']//td[text()=27])[2]")
	private WebElement dateValue;
	
	@FindBy(xpath = "//form[@name='book']")
	private WebElement form;
	
	@FindBy(id = "timepicker1")
	private WebElement timePicker;
	
	@FindBy(xpath = "//input[contains(@class,'bootstrap-timepicker-hour')]")
	private WebElement timePickerHour;
	
	@FindBy(xpath = "//input[contains(@class,'bootstrap-timepicker-minute')]")
	private WebElement timePickerMinute;
	
	@FindBy(xpath = "//input[contains(@class,'bootstrap-timepicker-meridian')]")
	private WebElement timePickerMeridian;	
	
	@FindBy(xpath = "//button[@name='submit']")
	private WebElement submitButton;

	public WebElement getSpecializationDropdown() {
		return specializationDropdown;
	}

	public WebElement getDoctorDropdown() {
		return doctorDropdown;
	}

	public WebElement getConsultancyFeesValue() {
		return consultancyFeesValue;
	}

	public WebElement getDatePicker() {
		return datePicker;
	}

	public WebElement getForm() {
		return form;
	}

	public WebElement getTimePicker() {
		return timePicker;
	}

	public WebElement getDateValue() {
		return dateValue;
	}

	public WebElement getTimePickerHour() {
		return timePickerHour;
	}

	public WebElement getTimePickerMinute() {
		return timePickerMinute;
	}

	public WebElement getTimePickerMeridian() {
		return timePickerMeridian;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	

}
