/* ******************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to validate Direct Debit SEPA form
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ********************************************************************/

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
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;
import com.nn.TestConfiguration.Constant;

public class DirectDebitSEPA extends TestInputData {

	TestInputData Data = new TestInputData();

	/* ******* Validating 'Account holder' field *************/
	@Test
	public void AccountholderEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with empty
			System.out.println("Validate Account holder name field with empty");
			String AccountholderEmptyData[] = Data.ExcelReader_AccountholderEmptyValidation();
			test = extend.createTest("Validation: Account holder name field with empty");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderEmptyData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderEmptyData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// String screenshotPath = Utility.takeScreenshot("Validation- Account holder
				// name field with empty");
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data: "
							+ AccountholderEmptyData[0]);
					test.log(Status.PASS, "TC PASSED : Account holder field not allows the invalid data: "
							+ AccountholderEmptyData[0]);
				} else {
					System.out.println(
							"TC FAILED: Account holder field allows the invalid data:" + AccountholderEmptyData[0]);
					test.log(Status.FAIL,
							"TC FAILED: Account holder field allows the invalid data: " + AccountholderEmptyData[0]);
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
			System.out.println("ERROR: Unexpected error from 'AccountholderEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderEmpty' method");
		}
	}

	@Test
	public void AccountholderNumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with numbers
			System.out.println("Validate Account holder name field with numbers");
			String AccountholderNumericData[] = Data.ExcelReader_AccountholderNumericValidation();
			test = extend.createTest("Validation: Account holder name field with numbers");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderNumericData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderNumericData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data: "
							+ AccountholderNumericData[0]);
					test.log(Status.PASS, "TC PASSED : Account holder field not allows the invalid data: "
							+ AccountholderNumericData[0]);
				} else {
					System.out.println(
							"TC FAILED: Account holder field allows the invalid data:" + AccountholderNumericData[0]);
					test.log(Status.FAIL,
							"TC FAILED: Account holder field allows the invalid data: " + AccountholderNumericData[0]);
				}
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				alert.accept();				
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			 //Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'AccountholderNumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderNumeric' method");
		}
	}

	@Test
	public void AccountholderSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with special characters
			System.out.println("Validate Account holder name field with special characters");
			String AccountholderSpecialcharData[] = Data.ExcelReader_AccountholderSpecialcharValidation();
			test = extend.createTest("Validation: Account holder name field with special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data: "
							+ AccountholderSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED : Account holder field not allows the invalid data: "
							+ AccountholderSpecialcharData[0]);
				} else {
					System.out.println("TC FAILED: Account holder field allows the invalid data:"
							+ AccountholderSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Account holder field allows the invalid data:"
							+ AccountholderSpecialcharData[0]);
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
			System.out.println("ERROR: Unexpected error from 'AccountholderSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderSpecialchar' method");
		}
	}

	@Test
	public void AccountholderAlphanumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with alphanumerics
			System.out.println("Validate Account holder name field with alphanumerics");
			String AccountholderAlphanumericData[] = Data.ExcelReader_AccountholderAlphanumericValidation();
			test = extend.createTest("Validation: Account holder name field with alphanumerics");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderAlphanumericData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderAlphanumericData[1]);
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data:"
							+ AccountholderAlphanumericData[0]);
					test.log(Status.PASS, "TC PASSED: Account holder field not allows the invalid data:"
							+ AccountholderAlphanumericData[0]);
				}
				else {
					System.out.println("TC FAILED: Account holder field allows the invalid data: "
							+ AccountholderAlphanumericData[0]);
					test.log(Status.FAIL, "TC FAILED: Account holder field allows the invalid data: "
							+ AccountholderAlphanumericData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'AccountholderAlphanumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderAlphanumeric' method");
		}
	}

	@Test
	public void AccountholderAlphaSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with alphabets and special characters
			System.out.println("Validate Account holder name field with alphabets and special characters");
			String AccountholderAlphaSpecialcharData[] = Data.ExcelReader_AccountholderAlphaSpecialcharValidation();
			test = extend.createTest("Validation: Account holder name field with alphabets and special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderAlphaSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderAlphaSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data:"
							+ AccountholderAlphaSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED: Account holder field not allows the invalid data:"
							+ AccountholderAlphaSpecialcharData[0]);
				}
				else {
					System.out.println("TC FAILED: Account holder field allows the invalid data: "
							+ AccountholderAlphaSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Account holder field allows the invalid data:"
							+ AccountholderAlphaSpecialcharData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'AccountholderAlphaSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderAlphaSpecialchar' method");
		}
	}

	@Test
	public void AccountholderNumberSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with number and special characters
			System.out.println("Validate Account holder name field with number and special characters");
			String AccountholderNumberSpecialcharData[] = Data.ExcelReader_AccountholderNumberSpecialcharValidation();
			test = extend.createTest("Validation: Account holder name field with number and special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderNumberSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderNumberSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: Account holder field not allows the invalid data: "
							+ AccountholderNumberSpecialcharData[0]);
					test.log(Status.PASS, "TC PASSED: Account holder field not allows the invalid data: "
							+ AccountholderNumberSpecialcharData[0]);
				} else {
					System.out.println("TC FAILED: Account holder field allows the invalid data:"
							+ AccountholderNumberSpecialcharData[0]);
					test.log(Status.FAIL, "TC FAILED: Account holder field allows the invalid data:"
							+ AccountholderNumberSpecialcharData[0]);
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
			System.out.println("ERROR: Unexpected error from 'AccountholderNumberSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderNumberSpecialchar' method");
		}
	}

	@Test
	public void AccountholderAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate Account holder field with alphabets
			System.out.println("Validate Account holder name field with alphabets");
			String AccountholderAlphabetsData[] = Data.ExcelReader_AccountholderAlphabetsValidation();
			test = extend.createTest("Validation: Account holder name field with alphabets");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(AccountholderAlphabetsData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(AccountholderAlphabetsData[1]);
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println(
							"TC PASSED: Account holder field allows the valid data:" + AccountholderAlphabetsData[0]);
					test.log(Status.PASS,
							"TC PASSED: Account holder field allows the valid data:" + AccountholderAlphabetsData[0]);
				}
				else {
					System.out.println("TC FAILED: Account holder field not allows the invalid data: "
							+ AccountholderAlphabetsData[0]);
					test.log(Status.FAIL, "TC FAILED: Account holder field not allows the invalid data: "
							+ AccountholderAlphabetsData[0]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'AccountholderAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'AccountholderAlphabets' method");
		}
	}

	@Test
	public void IbanEmpty() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with empty
			System.out.println("Validate IBAN field with empty");
			String IbanEmptyData[] = Data.ExcelReader_IbanEmptyValidation();
			test = extend.createTest("Validation: IBAN field with empty");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanEmptyData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanEmptyData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: IBAN field not allows the invalid data: " + IbanEmptyData[1]);
					test.log(Status.PASS, "TC PASSED: IBAN field not allows the invalid data: " + IbanEmptyData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanEmptyData[1]);
					test.log(Status.FAIL, "TC FAILED: IBAN field allows the invalid data:" + IbanEmptyData[1]);
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
			System.out.println("ERROR: Unexpected error from 'IbanEmpty' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanEmpty' method");
		}
	}

	@Test
	public void IbanNumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with numerics
			System.out.println("Validate IBAN field with numerics");
			String IbanNumericData[] = Data.ExcelReader_IbanNumericValidation();
			test = extend.createTest("Validation: IBAN field with numerics");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanNumericData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanNumericData[1]);
				// Place order
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				String validation_error_message = element.Woocommerce_Checkout_Error.getText();
				// Verify the server error message
				if (validation_error_message.equals("No proper value specified for IBAN")) {
					System.out.println("TC PASSED: IBAN field not allows the invalid data: " + IbanNumericData[1]);
					test.log(Status.PASS, "TC PASSED: IBAN field not allows the invalid data: " + IbanNumericData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanNumericData[1]);
					test.log(Status.FAIL, "TC FAILED: IBAN field allows the invalid data:" + IbanNumericData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanNumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanNumeric' method");
		}
	}

	@Test
	public void IbanSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with special characters
			System.out.println("Validate IBAN field with special characters");
			String IbanSpecialcharData[] = Data.ExcelReader_IbanSpecialcharValidation();
			test = extend.createTest("Validation: IBAN field with special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				// Handle alert and get the alert message
				WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
				waitForAlert.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String validation_error_message = alert.getText();
				// Verify the alert message and close the alert
				if (validation_error_message.equals("Your account details are invalid")) {
					System.out.println("TC PASSED: IBAN field not allows the invalid data: " + IbanSpecialcharData[1]);
					test.log(Status.PASS,
							"TC PASSED: IBAN field not allows the invalid data: " + IbanSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanSpecialcharData[1]);
					test.log(Status.FAIL, "TC FAILED: IBAN field allows the invalid data:" + IbanSpecialcharData[1]);
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
			System.out.println("ERROR: Unexpected error from 'IbanSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanSpecialchar' method");
		}
	}

	@Test
	public void IbanAlphaSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with alphabets and special characters
			System.out.println("Validate IBAN field with alphabets and special characters");
			String IbanAlphaSpecialcharData[] = Data.ExcelReader_IbanAlphaSpecialcharValidation();
			test = extend.createTest("Validation: IBAN field with alphabets and special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanAlphaSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanAlphaSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				String validation_error_message = element.Woocommerce_Checkout_Error.getText();
				// Verify the server error message
				if (validation_error_message.equals("No proper value specified for IBAN")) {
					System.out.println(
							"TC PASSED: IBAN field not allows the invalid data: " + IbanAlphaSpecialcharData[1]);
					test.log(Status.PASS,
							"TC PASSED: IBAN field not allows the invalid data: " + IbanAlphaSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanAlphaSpecialcharData[1]);
					test.log(Status.FAIL,
							"TC FAILED: IBAN field allows the invalid data:" + IbanAlphaSpecialcharData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanAlphaSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanAlphaSpecialchar' method");
		}
	}

	@Test
	public void IbanNumberSpecialchar() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with number and special characters
			System.out.println("Validate IBAN field with number and special characters");
			String IbanNumberSpecialcharData[] = Data.ExcelReader_IbanNumberSpecialcharValidation();
			test = extend.createTest("Validation: IBAN field with number and special characters");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanNumberSpecialcharData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanNumberSpecialcharData[1]);
				// Place order
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				String validation_error_message = element.Woocommerce_Checkout_Error.getText();
				// Verify the server error message
				if (validation_error_message.equals("No proper value specified for IBAN")) {
					System.out.println(
							"TC PASSED: IBAN field not allows the invalid data: " + IbanNumberSpecialcharData[1]);
					test.log(Status.PASS,
							"TC PASSED: IBAN field not allows the invalid data: " + IbanNumberSpecialcharData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanNumberSpecialcharData[1]);
					test.log(Status.FAIL,
							"TC FAILED: IBAN field allows the invalid data:" + IbanNumberSpecialcharData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanNumberSpecialchar' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanNumberSpecialchar' method");
		}
	}

	@Test
	public void IbanAlphabets() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with alphabets
			System.out.println("Validate IBAN field with alphabets");
			String IbanAlphabetsData[] = Data.ExcelReader_IbanAlphabetsValidation();
			test = extend.createTest("Validation: IBAN field with alphabets");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanAlphabetsData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanAlphabetsData[1]);
				// Place order
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				String validation_error_message = element.Woocommerce_Checkout_Error.getText();
				// Verify the server error message
				if (validation_error_message.equals("No proper value specified for IBAN")) {
					System.out.println("TC PASSED: IBAN field not allows the invalid data: " + IbanAlphabetsData[1]);
					test.log(Status.PASS, "TC PASSED: IBAN field not allows the invalid data: " + IbanAlphabetsData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanAlphabetsData[1]);
					test.log(Status.FAIL, "TC FAILED: IBAN field allows the invalid data:" + IbanAlphabetsData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanAlphabets' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanAlphabets' method");
		}
	}

	@Test
	public void IbanWrongAlphanumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with Alphanumeric (Wrong format or Incorrect IBAN value)
			System.out.println("Validate IBAN field with Alphanumeric (Wrong format or Incorrect IBAN value)");
			String IbanWrongAlphanumericData[] = Data.ExcelReader_IbanWrongAlphanumericValidation();
			test = extend.createTest("Validation: IBAN field with Alphanumeric (Wrong format or Incorrect IBAN value)");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanWrongAlphanumericData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanWrongAlphanumericData[1]);
				// Place order
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				String validation_error_message = element.Woocommerce_Checkout_Error.getText();
				// Verify the server error message
				if (validation_error_message.equals("No proper value specified for IBAN")) {
					System.out.println(
							"TC PASSED: IBAN field not allows the invalid data: " + IbanWrongAlphanumericData[1]);
					test.log(Status.PASS,
							"TC PASSED: IBAN field not allows the invalid data: " + IbanWrongAlphanumericData[1]);
				} else {
					System.out.println("TC FAILED: IBAN field allows the invalid data:" + IbanWrongAlphanumericData[1]);
					test.log(Status.FAIL,
							"TC FAILED: IBAN field allows the invalid data:" + IbanWrongAlphanumericData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanWrongAlphanumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanWrongAlphanumeric' method");
		}
	}

	@Test
	public void IbanValidAlphanumeric() throws Exception {
		try {
			Utility.initConfiguration();
			driver.navigate().to(Constant.shopfrontendurl);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop and proceed to checkout
			Utility.wooCommerceFrontEndLogin();
			Utility.wooCommerceCheckOutProcess();
			// Validate IBAN field with Alphanumeric (correct format or valid IBAN value)
			System.out.println("Validate IBAN field with Alphanumeric (correct format or valid IBAN value)");
			String IbanValidAlphanumericData[] = Data.ExcelReader_IbanValidAlphanumericValidation();
			test = extend.createTest("Validation: IBAN field with Alphanumeric (correct format or valid IBAN value)");
			try {
				Thread.sleep(4000);
				// Check Direct Debit SEPA payment in checkout page and select
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed()) {
						element.Sepa_Radio_button.click();
					}
				}
				// Enter the values in Direct Debit form
				element.Sepa_Account_holder_TextBox.clear();
				element.Sepa_Account_holder_TextBox.sendKeys(IbanValidAlphanumericData[0]);
				element.Sepa_Iban_TextBox.clear();
				element.Sepa_Iban_TextBox.sendKeys(IbanValidAlphanumericData[1]);
				// Place order
				element.Place_Order.click();
				// Check alert not present and thank you page received
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				if ((isAlertPresent() == false) && (element.FE_Thank_You_Page_Text.isDisplayed())) {
					System.out.println("TC PASSED: IBAN field allows the valid data:" + IbanValidAlphanumericData[1]);
					test.log(Status.PASS,
							"TC PASSED: IBAN field allows the valid data:" + IbanValidAlphanumericData[1]);
				}
				else {
					System.out.println(
							"TC FAILED: IBAN field not allows the invalid data: " + IbanValidAlphanumericData[1]);
					test.log(Status.FAIL,
							"TC FAILED: IBAN field not allows the invalid data: " + IbanValidAlphanumericData[1]);
				}
			} catch (Exception e) {
				System.out.println("Error: Unexpected error in the checkout page or in the thank you page");
				test.log(Status.ERROR, "Error: Unexpected error in the checkout page or in the thank you page");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'IbanValidAlphanumeric' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'IbanValidAlphanumeric' method");
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
