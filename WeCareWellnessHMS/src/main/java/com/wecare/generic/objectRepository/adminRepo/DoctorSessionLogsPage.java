package com.wecare.generic.objectRepository.adminRepo;

/**
 * @author RAGAVI
 */

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DoctorSessionLogsPage {
	
	public DoctorSessionLogsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr[last()]/td")
	private List<WebElement> lastEntryLogs;

	public List<WebElement> getLastEntryLogs() {
		return lastEntryLogs;
	}
	
	

}
