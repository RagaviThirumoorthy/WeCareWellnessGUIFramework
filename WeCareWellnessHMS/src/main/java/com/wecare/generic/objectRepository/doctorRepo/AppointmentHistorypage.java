package com.wecare.generic.objectRepository.doctorRepo;

/**
 * @author RAGAVI
 */

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentHistorypage {
	
	public AppointmentHistorypage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='sample-table-1']//td")
	private List<WebElement> bookingHistory;
	

	public List<WebElement> getBookingHistory() {
		return bookingHistory;
	}
	
	

}
