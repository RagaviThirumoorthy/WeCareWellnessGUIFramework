package com.wecare.generic.objectRepository.doctorRepo;

/**
 * @author RAGAVI
 */

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagePatientsPage {
	
	public ManagePatientsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='sample-table-1']//tr/td")
	private List<WebElement> patientHistory;

	public List<WebElement> getPatientHistory() {
		return patientHistory;
	}
	

}
