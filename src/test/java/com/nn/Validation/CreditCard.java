/* ************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to validate Credit Card form
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************/

package com.nn.Validation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class CreditCard extends TestInputData {

	TestInputData Data = new TestInputData();

	/* ******* Validating 'Card holder name' field *************/

	@Test
	public void CardholderEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with empty
			System.out.println("Validate Card holder name field with empty");
			String CardholderEmptyData[] = Data.ExcelReader_CardholderEmptyValidation();
			test = extend.createTest("Validation: Card holder name field with empty");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderEmptyData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderEmptyData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderEmptyData[2]);
				element.CVC_TextBox.sendKeys(CardholderEmptyData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card holder name")) {
					System.out.println(
							"TC PASSED: Card holder name field not allows the invalid data: " + CardholderEmptyData[0]);
					test.log(Status.PASS,
							"TC PASSED: Card holder name field not allows the invalid data: " + CardholderEmptyData[0]);
				} else {
					System.out.println(
							"TC FAILED: Card holder name field allows the invalid data:" + CardholderEmptyData[0]);
					test.log(Status.FAIL,
							"TC FAILED: Card holder name field allows the invalid data:" + CardholderEmptyData[0]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderEmpty' method");
		}
	}

	@Test
	public void CardholderNumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with Numerics
			System.out.println("Validate Card holder name field with Numerics");
			String CardholderNumericData[] = Data.ExcelReader_CardholderNumericValidation();
			test = extend.createTest("Validation: Card holder name field with Numerics");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderNumericData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderNumericData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderNumericData[2]);
				element.CVC_TextBox.sendKeys(CardholderNumericData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card holder name")) {
					System.out.println("TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderNumericData[0]);
					test.log(Status.PASS, "TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderNumericData[0]);
				} else {
					System.out.println(
							"TC FAILED: Card holder name field allows the invalid data: " + CardholderNumericData[0]);
					test.log(Status.FAIL,
							"TC FAILED: Card holder name field allows the invalid data: " + CardholderNumericData[0]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderNumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderNumeric' method");
		}
	}

	@Test
	public void CardholderSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with Specialcharacters
			System.out.println("Validate Card holder name field with Specialcharacters");
			String CardholderSpecialcharData[] = Data.ExcelReader_CardholderSpecialcharValidation();
			test = extend.createTest("Validation: Card holder name field with Specialcharacters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardholderSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card holder name")) {
					System.out.println("TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderSpecialcharData[0]);
				} else {
					System.out.println("TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderSpecialcharData[0]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderSpecialchar' method");
		}
	}

	@Test
	public void CardholderAlphanumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with alphanumerics
			System.out.println("Validate Card holder name field with alphanumerics");
			String CardholderAlphanumericData[] = Data.ExcelReader_CardholderAlphanumericValidation();
			test = extend.createTest("Validation: Card holder name field with alphanumerics");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderAlphanumericData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderAlphanumericData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderAlphanumericData[2]);
				element.CVC_TextBox.sendKeys(CardholderAlphanumericData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: Card holder name field not allows the invalid data:"
							+ CardholderAlphanumericData[0]);
					test.log(Status.PASS, "TC PASSED: Card holder name field not allows the invalid data:"
							+ CardholderAlphanumericData[0]);
				}

				else {
					System.out.println("TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderAlphanumericData[0]);
					test.log(Status.FAIL, "TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderAlphanumericData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderAlphanumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderAlphanumeric' method");
		}
	}

	@Test
	public void CardholderAlphaSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with alphabets and special characters (other
			// than hyphen, ampersand and dot)
			System.out.println("Validate Card holder name field with alphabets and special characters");
			String CardholderAlphaSpecialcharData[] = Data.ExcelReader_CardholderAlphaSpecialcharValidation();
			test = extend.createTest("Validation: Card holder name field with alphabets and special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderAlphaSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderAlphaSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderAlphaSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardholderAlphaSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderAlphaSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderAlphaSpecialcharData[0]);
				} else {
					System.out.println("TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderAlphaSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderAlphaSpecialcharData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderAlphaSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderAlphaSpecialchar' method");
		}
	}

	@Test
	public void CardholderNumberSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with numbers and special characters
			System.out.println("Validate Card holder name field with numbers and special characters");
			String CardholderNumberSpecialcharData[] = Data.ExcelReader_CardholderNumberSpecialcharValidation();
			test = extend.createTest("Validation: Card holder name field with numbers and special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderNumberSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderNumberSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderNumberSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardholderNumberSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card holder name")) {
					System.out.println("TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderNumberSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED: Card holder name field not allows the invalid data: "
							+ CardholderNumberSpecialcharData[0]);
				} else {
					System.out.println("TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderNumberSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Card holder name field allows the invalid data: "
							+ CardholderNumberSpecialcharData[0]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderNumberSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderNumberSpecialchar' method");
		}
	}

	@Test
	public void CardholderAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card holder name field with alphabets
			System.out.println("Validate Card holder name field with alphabets");
			String CardholderAlphabetsData[] = Data.ExcelReader_CardholderAlphabetsValidation();
			test = extend.createTest("Validation: Card holder name field with alphabets");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardholderAlphabetsData[0]);
				element.Credit_Number_TextBox.sendKeys(CardholderAlphabetsData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardholderAlphabetsData[2]);
				element.CVC_TextBox.sendKeys(CardholderAlphabetsData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println(
							"TC PASSED: Card holder name field allows valid data: " + CardholderAlphabetsData[0]);
					test.log(Status.PASS,
							"TC PASSED: Card holder name field allows valid data: " + CardholderAlphabetsData[0]);
				}

				else {
					System.out.println(
							"TC FAILED: Card holder name field not allows valid data:: " + CardholderAlphabetsData[0]);
					test.log(Status.FAIL,
							"TC FAILED: Card holder name field not allows valid data:: " + CardholderAlphabetsData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardholderAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardholderAlphabets' method");
		}
	}

	/* ******* Validating 'Card number' field *************/

	@Test
	public void CardnumberEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with empty
			System.out.println("Validate Card number field with empty");
			String CardnumberEmptyData[] = Data.ExcelReader_CardnumberEmptyValidation();
			test = extend.createTest("Validation: Card number field with empty");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberEmptyData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberEmptyData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberEmptyData[2]);
				element.CVC_TextBox.sendKeys(CardnumberEmptyData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println(
							"TC PASSED: Card number field not allows the invalid data: " + CardnumberEmptyData[1]);
					test.log(Status.PASS,
							"TC PASSED: Card number field not allows the invalid data: " + CardnumberEmptyData[1]);
				} else {
					System.out.println(
							"TC FAILED: Card number name field allows the invalid data:" + CardnumberEmptyData[1]);
					test.log(Status.FAIL,
							"TC FAILED: Card number name field allows the invalid data:" + CardnumberEmptyData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberEmpty' method");
		}
	}

	@Test
	public void CardnumberAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with alphabets
			System.out.println("Validate Card number field with albhabets");
			String CardnumberAlphabetsData[] = Data.ExcelReader_CardnumberAlphabetsValidation();
			test = extend.createTest("Validation: Card number field with albhabets");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberAlphabetsData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberAlphabetsData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberAlphabetsData[2]);
				element.CVC_TextBox.sendKeys(CardnumberAlphabetsData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println(
							"TC PASSED: Card number field not allows the invalid data: " + CardnumberAlphabetsData[1]);
					test.log(Status.PASS,
							"TC PASSED: Card number field not allows the invalid data: " + CardnumberAlphabetsData[1]);
				} else {
					System.out.println(
							"TC FAILED: Card number name field allows the invalid data:" + CardnumberAlphabetsData[1]);
					test.log(Status.FAIL,
							"TC FAILED: Card number name field allows the invalid data:" + CardnumberAlphabetsData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberAlphabets' method");
		}
	}

	@Test
	public void CardnumberLessThanLowerLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with less than the lower limit (less than
			// 13-digits)
			System.out.println("Validate Card number field with less than the lower limit(less than 13-digits)");
			String CardnumberLessThanLowerLimitData[] = Data.ExcelReader_CardnumberLessThanLowerLimitValidation();
			test = extend
					.createTest("Validation: Card number field with less than the lower limit(less than 13-digits)");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberLessThanLowerLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberLessThanLowerLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberLessThanLowerLimitData[2]);
				element.CVC_TextBox.sendKeys(CardnumberLessThanLowerLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Card invalid")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberLessThanLowerLimitData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberLessThanLowerLimitData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberLessThanLowerLimitData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberLessThanLowerLimitData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberLessThanLowerLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberLessThanLowerLimit' method");
		}
	}

	@Test
	public void CardnumberSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with special characters
			System.out.println("Validate Card number field with special characters");
			String CardnumberSpecialcharData[] = Data.ExcelReader_CardnumberSpecialcharValidation();
			test = extend.createTest("Validation: Card number field with special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardnumberSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberSpecialcharData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberSpecialcharData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberSpecialcharData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberSpecialchar' method");
		}
	}

	@Test
	public void CardnumberAlphanumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with alphanumerics
			System.out.println("Validate Card number field with alphanumerics");
			String CardnumberAlphanumericData[] = Data.ExcelReader_CardnumberAlphanumericValidation();
			test = extend.createTest("Validation: Card number field with alphanumerics");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberAlphanumericData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberAlphanumericData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberAlphanumericData[2]);
				element.CVC_TextBox.sendKeys(CardnumberAlphanumericData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberAlphanumericData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberAlphanumericData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberAlphanumericData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberAlphanumericData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberAlphanumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberAlphanumeric' method");
		}
	}

	@Test
	public void CardnumberAlphaSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with alphabets and special characters
			System.out.println("Validate Card number field with alphabets and special characters");
			String CardnumberAlphaSpecialcharData[] = Data.ExcelReader_CardnumberAlphaSpecialcharValidation();
			test = extend.createTest("Validation: Card number field with alphabets and special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberAlphaSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberAlphaSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberAlphaSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardnumberAlphaSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberAlphaSpecialcharData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberAlphaSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberAlphaSpecialcharData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberAlphaSpecialcharData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();

			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberAlphaSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberAlphaSpecialchar' method");
		}
	}

	@Test
	public void CardnumberNumberSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with number and special characters
			System.out.println("Validate Card number field with number and special characters");
			String CardnumberNumberSpecialcharData[] = Data.ExcelReader_CardnumberNumberSpecialcharValidation();
			test = extend.createTest("Validation: Card number field with number and special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberNumberSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberNumberSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberNumberSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CardnumberNumberSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberNumberSpecialcharData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberNumberSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberNumberSpecialcharData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberNumberSpecialcharData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberNumberSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberNumberSpecialchar' method");
		}
	}

	@Test
	public void CardnumberLowerLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with lower limit (13-digits)
			System.out.println("Validate Card number field with lower limit (13-digits)");
			String CardnumberLowerLimitData[] = Data.ExcelReader_CardnumberLowerLimitValidation();
			test = extend.createTest("Validation: Card number field with lower limit (13-digits)");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberLowerLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberLowerLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberLowerLimitData[2]);
				element.CVC_TextBox.sendKeys(CardnumberLowerLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out
							.println("TC PASSED: Card number field allows valid data: " + CardnumberLowerLimitData[1]);
					test.log(Status.PASS,
							"TC PASSED: Card number field allows valid data: " + CardnumberLowerLimitData[1]);
				}

				else {
					System.out.println(
							"TC FAILED: Card number field not allows valid data:: " + CardnumberLowerLimitData[1]);
					test.log(Status.FAIL,
							"TC FAILED: Card number field not allows valid data:: " + CardnumberLowerLimitData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberLowerLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberLowerLimit' method");
		}
	}

	@Test
	public void CardnumberUpperLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with upper limit (16-digits)
			System.out.println("Validate Card number field with upper limit (16-digits)");
			String CardnumberUpperLimitData[] = Data.ExcelReader_CardnumberUpperLimitValidation();
			test = extend.createTest("Validation: Card number field with upper limit (16-digits)");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberUpperLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberUpperLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberUpperLimitData[2]);
				element.CVC_TextBox.sendKeys(CardnumberUpperLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out
							.println("TC PASSED: Card number field allows valid data: " + CardnumberUpperLimitData[1]);
					test.log(Status.PASS,
							"TC PASSED: Card number field allows valid data: " + CardnumberUpperLimitData[1]);
				} else {
					System.out.println(
							"TC FAILED: Card number field not allows valid data: " + CardnumberUpperLimitData[1]);
					test.log(Status.FAIL,
							"TC FAILED: Card number field not allows valid data: " + CardnumberUpperLimitData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberUpperLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberUpperLimit' method");
		}
	}

	@Test
	public void CardnumberRandom() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with random number
			System.out.println("Validate Card number field with random number");
			String CardnumberGreaterThanUpperLimitData[] = Data.ExcelReader_CardnumberRandomValidation();
			test = extend.createTest("Validation: Card number field with random number");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CardnumberGreaterThanUpperLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CardnumberGreaterThanUpperLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CardnumberGreaterThanUpperLimitData[2]);
				element.CVC_TextBox.sendKeys(CardnumberGreaterThanUpperLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid card number")) {
					System.out.println("TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberGreaterThanUpperLimitData[1]);
					test.log(Status.PASS, "TC PASSED: Card number field not allows the invalid data: "
							+ CardnumberGreaterThanUpperLimitData[1]);
				} else {
					System.out.println("TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberGreaterThanUpperLimitData[1]);
					test.log(Status.FAIL, "TC FAILED: Card number name field allows the invalid data:"
							+ CardnumberGreaterThanUpperLimitData[1]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CardnumberRandom' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CardnumberRandom' method");
		}
	}

	/* ******* Validating 'Expiry date' field *************/

	@Test
	public void ExpirydateEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with empty
			System.out.println("Validate Expiry date field with empty");
			String ExpirydateEmptyData[] = Data.ExcelReader_ExpirydateEmptyValidation();
			test = extend.createTest("Validation: Expiry date field with empty");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydateEmptyData[0]);
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				element.Credit_Number_TextBox.sendKeys(ExpirydateEmptyData[1]);
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				element.Expiry_Date_TextBox.sendKeys(ExpirydateEmptyData[2]);
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				element.CVC_TextBox.sendKeys(ExpirydateEmptyData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				System.out.println(validation_error_message);
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid expiry month / year in the given format")) {
					System.out.println(
							"TC PASSED: Expiry date field not allows the invalid data: " + ExpirydateEmptyData[2]);
					test.log(Status.PASS,
							"TC PASSED: Expiry date field not allows the invalid data: " + ExpirydateEmptyData[2]);
				} else {
					System.out.println(
							"TC FAILED: Expiry date name field allows the invalid data:" + ExpirydateEmptyData[2]);
					test.log(Status.FAIL,
							"TC FAILED: Expiry date name field allows the invalid data:" + ExpirydateEmptyData[2]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydateEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydateEmpty' method");
		}
	}

	@Test
	public void ExpirydateAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Card number field with alphabets
			System.out.println("Validate expiry date field with alphabets");
			String ExpirydateAlphabetsData[] = Data.ExcelReader_ExpirydateAlphabetsValidation();
			test = extend.createTest("Validation: expiry date field with alphabets");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydateAlphabetsData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydateAlphabetsData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydateAlphabetsData[2]);
				element.CVC_TextBox.sendKeys(ExpirydateAlphabetsData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid expiry month / year in the given format")) {
					System.out.println(
							"TC PASSED: Expiry date field not allows the invalid data: " + ExpirydateAlphabetsData[2]);
					test.log(Status.PASS,
							"TC PASSED: Expiry date field not allows the invalid data: " + ExpirydateAlphabetsData[2]);
				} else {
					System.out.println(
							"TC FAILED: Expiry date name field allows the invalid data:" + ExpirydateAlphabetsData[2]);
					test.log(Status.FAIL,
							"TC FAILED: Expiry date name field allows the invalid data:" + ExpirydateAlphabetsData[2]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydateAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydateAlphabets' method");
		}
	}

	@Test
	public void ExpirydateSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with special characters
			System.out.println("Validate Expiry date field with special characters");
			String ExpirydateSpecialcharData[] = Data.ExcelReader_ExpirydateSpecialcharValidation();
			test = extend.createTest("Validation: Expiry date field with special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydateSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydateSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydateSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(ExpirydateSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid expiry month / year in the given format")) {
					System.out.println("TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydateSpecialcharData[2]);
					test.log(Status.PASS, "TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydateSpecialcharData[2]);
				} else {
					System.out.println("TC FAILED: Expiry date name field allows the invalid data:"
							+ ExpirydateSpecialcharData[2]);
					test.log(Status.FAIL, "TC FAILED: Expiry date name field allows the invalid data:"
							+ ExpirydateSpecialcharData[2]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydateSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydateSpecialchar' method");
		}
	}

	@Test
	public void ExpirydateAlphabetSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with albhabets and special characters
			System.out.println("Validate Expiry date field with albhabets and special characters");
			String ExpirydateAlphabetSpecialcharData[] = Data.ExcelReader_ExpirydateAlphabetSpecialcharValidation();
			test = extend.createTest("Validation: Expiry date field with albhabets and special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydateAlphabetSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydateAlphabetSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydateAlphabetSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(ExpirydateAlphabetSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid expiry month / year in the given format")) {
					System.out.println("TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydateAlphabetSpecialcharData[2]);
					test.log(Status.PASS, "TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydateAlphabetSpecialcharData[2]);
				} else {
					System.out.println("TC FAILED: Expiry date field allows the invalid data:"
							+ ExpirydateAlphabetSpecialcharData[2]);
					test.log(Status.FAIL, "TC FAILED: Expiry date field allows the invalid data:"
							+ ExpirydateAlphabetSpecialcharData[2]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydateAlphabetSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydateAlphabetSpecialchar' method");
		}

	}

	@Test
	public void ExpirydatePastValidation() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with expired date
			System.out.println("Validate Expiry date field with expired date");
			String ExpirydatePastValidationData[] = Data.ExcelReader_ExpirydatePastValidation();
			test = extend.createTest("Validation: Expiry date field with expired date");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydatePastValidationData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydatePastValidationData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydatePastValidationData[2]);
				element.CVC_TextBox.sendKeys(ExpirydatePastValidationData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				if (validation_error_message.equals("Please enter the valid expiry month / year in the given format")) {
					System.out.println("TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydatePastValidationData[2]);
					test.log(Status.PASS, "TC PASSED: Expiry date field not allows the invalid data: "
							+ ExpirydatePastValidationData[2]);
				} else {
					System.out.println(
							"TC FAILED: Expiry date field allows the invalid data:" + ExpirydatePastValidationData[2]);
					test.log(Status.FAIL,
							"TC FAILED: Expiry date field allows the invalid data:" + ExpirydatePastValidationData[2]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydatePastValidation' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydatePastValidation' method");
		}
	}

	@Test
	public void ExpirydateFuture() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with future date
			System.out.println("Validate Expiry date field with future date");
			String ExpirydateFutureData[] = Data.ExcelReader_ExpirydateFutureValidation();
			test = extend.createTest("Validation: Expiry date field with future date");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydateFutureData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydateFutureData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydateFutureData[2]);
				element.CVC_TextBox.sendKeys(ExpirydateFutureData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: Expiry date field allows the valid data:" + ExpirydateFutureData[2]);
					test.log(Status.PASS,
							"TC PASSED: Expiry date field allows the valid data:" + ExpirydateFutureData[2]);
				} else {
					System.out.println(
							"TC FAILED: Expiry date field not allows the valid data: " + ExpirydateFutureData[2]);
					test.log(Status.FAIL,
							"TC FAILED: Expiry date field not allows the valid data: " + ExpirydateFutureData[2]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page  or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydateFuture' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydateFuture' method");
		}
	}

	@Test
	public void ExpirydatePresent() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Expiry date field with current month and year
			System.out.println("Validate Expiry date field with current month and year");
			String ExpirydatePresentData[] = Data.ExcelReader_ExpirydatePresentValidation();
			test = extend.createTest("Validation: Expiry date field with current month and year");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(ExpirydatePresentData[0]);
				element.Credit_Number_TextBox.sendKeys(ExpirydatePresentData[1]);
				element.Expiry_Date_TextBox.sendKeys(ExpirydatePresentData[2]);
				element.CVC_TextBox.sendKeys(ExpirydatePresentData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out
							.println("TC PASSED: Expiry date field allows the valid data:" + ExpirydatePresentData[2]);
					test.log(Status.PASS,
							"TC PASSED: Expiry date field allows the valid data:" + ExpirydatePresentData[2]);
				} else {
					System.out.println(
							"TC FAILED: Expiry date field not allows the valid data: " + ExpirydatePresentData[2]);
					test.log(Status.FAIL,
							"TC FAILED: Expiry date field not allows the valid data: " + ExpirydatePresentData[2]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'ExpirydatePresent' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'ExpirydatePresent' method");
		}
	}

	/* ******* Validating 'CVC/CVV/CID' field *************/
	@Test
	public void CVCEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with empty
			System.out.println("Validate CVC/CVV/CID field with empty");
			String CVCEmptyData[] = Data.ExcelReader_CVCEmptyValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with empty");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCEmptyData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCEmptyData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCEmptyData[2]);
				element.CVC_TextBox.sendKeys(CVCEmptyData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid CVC/CVV/CID")) {
					System.out.println("TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCEmptyData[3]);
					test.log(Status.PASS,
							"TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCEmptyData[3]);
				} else {
					System.out.println("TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCEmptyData[3]);
					test.log(Status.FAIL, "TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCEmptyData[3]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCEmpty' method");
		}
	}

	@Test
	public void CVCAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with alphabets
			System.out.println("Validate CVC/CVV/CID field with alphabets");
			String CVCAlphabetsData[] = Data.ExcelReader_CVCAlphabetsValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with alphabets");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCAlphabetsData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCAlphabetsData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCAlphabetsData[2]);
				element.CVC_TextBox.sendKeys(CVCAlphabetsData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid CVC/CVV/CID")) {
					System.out.println(
							"TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCAlphabetsData[3]);
					test.log(Status.PASS,
							"TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCAlphabetsData[3]);
				} else {
					System.out.println("TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCAlphabetsData[3]);
					test.log(Status.FAIL, "TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCAlphabetsData[3]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCAlphabets' method");
		}
	}

	@Test
	public void CVCSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with special characters
			System.out.println("Validate CVC/CVV/CID field with special characters");
			String CVCSpecialcharData[] = Data.ExcelReader_CVCSpecialcharValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with special characters");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCSpecialcharData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCSpecialcharData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCSpecialcharData[2]);
				element.CVC_TextBox.sendKeys(CVCSpecialcharData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid CVC/CVV/CID")) {
					System.out.println(
							"TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCSpecialcharData[3]);
					test.log(Status.PASS,
							"TC PASSED: CVC/CVV/CID field not allows the invalid data: " + CVCSpecialcharData[3]);
				} else {
					System.out.println("TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCSpecialcharData[3]);
					test.log(Status.FAIL,
							"TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCSpecialcharData[3]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCSpecialchar' method");
		}
	}

	@Test
	public void CVCLessThanLowerLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID name field with less than lower limit
			System.out.println("Validate CVC/CVV/CID field with less than lower limit");
			String CVCLessThanLowerLimitData[] = Data.ExcelReader_CVCLessThanLowerLimitValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with less than lower limit");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCLessThanLowerLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCLessThanLowerLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCLessThanLowerLimitData[2]);
				element.CVC_TextBox.sendKeys(CVCLessThanLowerLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Please enter the valid CVC/CVV/CID")) {
					System.out.println("TC PASSED: CVC/CVV/CID field not allows the invalid data: "
							+ CVCLessThanLowerLimitData[3]);
					test.log(Status.PASS, "TC PASSED: CVC/CVV/CID field not allows the invalid data: "
							+ CVCLessThanLowerLimitData[3]);
				} else {
					System.out.println(
							"TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCLessThanLowerLimitData[3]);
					test.log(Status.FAIL,
							"TC FAILED: CVC/CVV/CID field not allows the invalid:" + CVCLessThanLowerLimitData[3]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCLessThanLowerLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCLessThanLowerLimit' method");
		}
	}

	@Test
	public void CVCLowerLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with lower limit (13-digits)
			System.out.println("Validate CVC/CVV/CID field with lower limit (3-digits)");
			String CVCLowerLimitData[] = Data.ExcelReader_CVCLowerLimitValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with lower limit (3-digits)");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCLowerLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCLowerLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCLowerLimitData[2]);
				element.CVC_TextBox.sendKeys(CVCLowerLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: CVC/CVV/CID field allows the valid data:" + CVCLowerLimitData[3]);
					test.log(Status.PASS, "TC PASSED: CVC/CVV/CID field allows the valid data:" + CVCLowerLimitData[3]);
				} else {
					System.out
							.println("TC FAILED: CVC/CVV/CID field not allows the valid data: " + CVCLowerLimitData[3]);
					test.log(Status.FAIL,
							"TC FAILED: CVC/CVV/CID field not allows the valid data: " + CVCLowerLimitData[3]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCLowerLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCLowerLimit' method");
		}
	}

	@Test
	public void CVCUpperLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with upper limit (4-digits)
			System.out.println("Validate CVC/CVV/CID field with upper limit (4-digits)");
			String CVCUpperLimitData[] = Data.ExcelReader_CVCUpperLimitValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with upper limit (4-digits)");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCUpperLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCUpperLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCUpperLimitData[2]);
				element.CVC_TextBox.sendKeys(CVCUpperLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: CVC/CVV/CID field allows the valid data:" + CVCUpperLimitData[3]);
					test.log(Status.PASS, "TC PASSED: CVC/CVV/CID field allows the valid data:" + CVCUpperLimitData[3]);
				} else {
					System.out
							.println("TC FAILED: CVC/CVV/CID field not allows the valid data: " + CVCUpperLimitData[3]);
					test.log(Status.FAIL,
							"TC FAILED: CVC/CVV/CID field not allows the valid data: " + CVCUpperLimitData[3]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCUpperLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCUpperLimit' method");
		}
	}

	@Test
	public void CVCGreaterThanUpperLimit() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate CVC/CVV/CID field with greater than the upper limit
			System.out.println("Validate CVC/CVV/CID field with greater than the upper limit");
			String CVCGreaterThanUpperLimitData[] = Data.ExcelReader_CVCGreaterThanUpperLimitValidation();
			test = extend.createTest("Validation: CVC/CVV/CID field with greater than the upper limit");
			try {
				Thread.sleep(4000);
				// Check Creditcard payment in checkout page and select
				if (element.Creditcard_Title.isDisplayed()) {
					if (element.Creditcard_Radio_Button.isDisplayed()) {
						element.Creditcard_Radio_Button.click();
					}
				}
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.clear();
				element.Credit_Number_TextBox.clear();
				element.Expiry_Date_TextBox.clear();
				element.CVC_TextBox.clear();
				element.Credit_Holder_Name_TextBox.sendKeys(CVCGreaterThanUpperLimitData[0]);
				element.Credit_Number_TextBox.sendKeys(CVCGreaterThanUpperLimitData[1]);
				element.Expiry_Date_TextBox.sendKeys(CVCGreaterThanUpperLimitData[2]);
				element.CVC_TextBox.sendKeys(CVCGreaterThanUpperLimitData[3]);
				// Switch to default window
				driver.switchTo().defaultContent();
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				Thread.sleep(4000);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: CVC/CVV/CID field not allows the invalid data:"
							+ CVCGreaterThanUpperLimitData[3]);
					test.log(Status.PASS, "TC PASSED: CVC/CVV/CID field not allows the invalid data:"
							+ CVCGreaterThanUpperLimitData[3]);
				} else {
					System.out.println(
							"TC FAILED: CVC/CVV/CID field allows the invalid data: " + CVCGreaterThanUpperLimitData[3]);
					test.log(Status.FAIL,
							"TC FAILED: CVC/CVV/CID field allows the invalid data: " + CVCGreaterThanUpperLimitData[3]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CVCGreaterThanUpperLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'CVCGreaterThanUpperLimit' method");
		}
	}

	// Check alret is present or not
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			System.out.println("Alert is displayed");
			return true;
		} catch (NoAlertPresentException ex) {
			System.out.println("Alert not diplayed");
			return false;
		}
	}
}
