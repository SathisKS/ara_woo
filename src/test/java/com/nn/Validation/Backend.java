/* ******************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to validate back-end fields
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ********************************************************************/

package com.nn.Validation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Backend extends TestInputData {

	TestInputData Data = new TestInputData();

	/* ******* Validating 'Product Activation Key' field *************/

	@Test
	public void GlobalConfigMandatory() throws Exception {
		try {
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Novalnet_Global_Config_Tab.click();
			Thread.sleep(4000);
			// Validate Product Activation Key with empty
			System.out.println("Validate Product Activation Key with empty");
			test = extend.createTest("Validation: Product Activation Key with empty");
			// Check whether the product activation key contains value
			try {
				if (element.Product_Activation_Key != null) {
					element.Product_Activation_Key.clear();
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				System.out.println("ERROR: Product activation key field is not available");
				test.log(Status.ERROR, "ERROR: Product activation key field is not available");
			}
			element.Novalnet_Global_Config_Save_Changes.click();
			// Read the error message
			Thread.sleep(2000);
			String validation_error_message = element.Global_Config_Error.getText();
			// Verify the error message
			if (validation_error_message.equals("Please fill in all the mandatory fields")) {
				System.out.println("TC PASSED: Product Activation Key field is validated with proper error message");
				test.log(Status.PASS, "TC PASSED: Product Activation Key field is validated with proper error message");
			} else {
				System.out
						.println("TC FAILED: Product Activation Key field is not validated with proper error message");
				test.log(Status.FAIL,
						"TC FAILED: Product Activation Key field is not validated with proper error message");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'GlobalConfigMandatory' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'GlobalConfigMandatory' method");
		}
	}

	/* ******* Validating 'SEPA due date' field *************/

	@Test
	public void SepaDuedate() throws Exception {
		try {
			String SepaDuedateData[] = Data.ExcelReader_SepaDuedateValidation();
			for (int i = 0; i < SepaDuedateData.length; i++) {
				Utility.initConfiguration();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
				// Login to the shop back-end
				Utility.wooCommerceBackEndLogin();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				// Go to global configuration tab
				element.WooCommerce.click();
				element.WooCommerce_Settings.click();
				element.Payment_Tab.click();
				element.Sepa_Payment_Manage.click();
				// Validate SEPA due date field
				System.out.println("Validate SEPA due date field");
				test = extend.createTest("Validation: SEPA due date field");
				// Enter values in SEPA due date field
				element.Sepa_Duration_In_Days.clear();
				element.Sepa_Duration_In_Days.sendKeys(SepaDuedateData[i]);
				element.Sepa_Payment_Save_Changes.click();
				Thread.sleep(3000);
				// Get the error message and verify
				String validation_error_message = element.Payment_Page_Error.getText();
				if (validation_error_message.equals("SEPA Due date is not valid")) {
					System.out.println(
							"TC PASSED: SEPA due date field not accepts an invalid data: " + SepaDuedateData[i]);
					test.log(Status.PASS,
							"TC PASSED: SEPA due date field not accepts an invalid data: " + SepaDuedateData[i]);
				} else {
					System.out.println("TC FAILED: SEPA due date field accepts an invalid data: " + SepaDuedateData[i]);
					test.log(Status.FAIL,
							"TC FAILED: SEPA due date field accepts an invalid data: " + SepaDuedateData[i]);
				}
				// Close browser
				Utility.closeBrowser();
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'SepaDuedate' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'SepaDuedate' method");
		}
	}

	/*
	 * ******* Validating 'Minimum order amount' field for Guarantee SEPA payment
	 ***/

	@Test
	public void SepaGuaranteeMinamountString() throws Exception {
		try {
			String SepaGuaranteeMinamountStringData[] = Data.ExcelReader_GuaranteeMinamountStringValidation();
			for (int i = 0; i < SepaGuaranteeMinamountStringData.length; i++) {
				Utility.initConfiguration();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
				// Login to the shop back-end
				Utility.wooCommerceBackEndLogin();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				// Go to global configuration tab
				element.WooCommerce.click();
				element.WooCommerce_Settings.click();
				element.Payment_Tab.click();
				element.Sepa_Payment_Manage.click();
				// Validate Minimum order amount for Guarantee Direct Debit SEPA with string
				System.out.println("Validate Minimum order amount for Guarantee Direct Debit SEPA with string");
				test = extend
						.createTest("Validation: Minimum order amount for Guarantee Direct Debit SEPA with string");
				try {
					if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
						element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
					}
				} catch (Exception e) {
					System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
					test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
				}
				element.Sepa_Minimum_Order_Amount_Textbox.clear();
				element.Sepa_Minimum_Order_Amount_Textbox.sendKeys(SepaGuaranteeMinamountStringData[i]);

				element.Sepa_Payment_Save_Changes.click();
				driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
				// Get the error message and verify
				String validation_error_message = element.Payment_Page_Error.getText();
				if (validation_error_message.equals("The amount is invalid")) {
					System.out.println("TC PASSED: Guarantee Minimum order amount field not accepts an invalid data: "
							+ SepaGuaranteeMinamountStringData[i]);
					test.log(Status.PASS,
							"TC PASSED: Guarantee Minimum order amount field not accepts an invalid data: "
									+ SepaGuaranteeMinamountStringData[i]);
				} else {
					System.out.println("TC FAILED: Guarantee Minimum order amount field accepts an invalid data: "
							+ SepaGuaranteeMinamountStringData[i]);
					test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field accepts an invalid data: "
							+ SepaGuaranteeMinamountStringData[i]);
				}
				// Close browser
				Utility.closeBrowser();
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'SepaGuaranteeMinamountString' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'SepaGuaranteeMinamountString' method");
		}
	}

	@Test
	public void SepaGuaranteeMinamountLessThanLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Sepa_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Direct Debit SEPA with less than
			// limit
			System.out.println("Validate Minimum order amount for Guarantee Direct Debit SEPA with less than limit");
			test = extend.createTest(
					"Validation: Minimum order amount for Guarantee Direct Debit SEPA with less than limit( less than 9,99 Euro)");
			try {
				if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Sepa_Minimum_Order_Amount_Textbox.clear();
			element.Sepa_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[0]);
			element.Sepa_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			// Get the error message and verify
			String validation_error_message = element.Payment_Page_Error.getText();
			if (validation_error_message.equals("The minimum amount should be at least 9,99 EUR")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
				test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'SepaGuaranteeMinamountLessThanLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'SepaGuaranteeMinamountLessThanLimit' method");
		}
	}

	@Test
	public void SepaGuaranteeMinamountEqualLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Sepa_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Direct Debit SEPA with equal
			// limit
			System.out.println("Validate Minimum order amount for Guarantee Direct Debit SEPA with equal limit");
			test = extend.createTest(
					"Validation: Minimum order amount for Guarantee Direct Debit SEPA with equal limit( 9,99 Euro)");
			try {
				if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Sepa_Minimum_Order_Amount_Textbox.clear();
			element.Sepa_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[1]);
			element.Sepa_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			// Verify the saved text
			if (driver.getPageSource().contains("Your settings have been saved.")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[1]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[1]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[1]);
				test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[1]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'SepaGuaranteeMinamountEqualLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'SepaGuaranteeMinamountEqualLimit' method");
		}
	}

	@Test
	public void SepaGuaranteeMinamountGreaterThanLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Sepa_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Direct Debit SEPA with greater
			// than limit
			System.out.println("Validate Minimum order amount for Guarantee Direct Debit SEPA with greater than limit");
			test = extend.createTest(
					"Validation: Minimum order amount for Guarantee Direct Debit SEPA with greater than limit( greater than 9,99 Euro)");
			try {
				if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Sepa_Minimum_Order_Amount_Textbox.clear();
			element.Sepa_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[2]);
			element.Sepa_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			// Verify the saved text
			if (driver.getPageSource().contains("Your settings have been saved.")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[2]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[2]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[2]);
				test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[2]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'SepaGuaranteeMinamountGreaterThanLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'SepaGuaranteeMinamountGreaterThanLimit' method");
		}
	}

	/*
	 * **** Validating 'Minimum order amount' field for Guarantee Invoice payment
	 *******/

	@Test
	public void InvoiceGuaranteeMinamountString() throws Exception {
		try {
			String InvoiceGuaranteeMinamountStringData[] = Data.ExcelReader_GuaranteeMinamountStringValidation();
			for (int i = 0; i < InvoiceGuaranteeMinamountStringData.length; i++) {
				Utility.initConfiguration();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
				// Login to the shop back-end
				Utility.wooCommerceBackEndLogin();
				driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
				// Go to global configuration tab
				element.WooCommerce.click();
				element.WooCommerce_Settings.click();
				element.Payment_Tab.click();
				element.Invoice_Payment_Manage.click();
				// Validate Minimum order amount for Invoice with string
				System.out.println("Validate Minimum order amount for Invoice with string");
				test = extend.createTest("Validation: Minimum order amount for Invoice with string");
				try {
					if (element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
						element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
					}
				} catch (Exception e) {
					System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
					test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
				}
				element.Invoice_Minimum_Order_Amount_Textbox.clear();
				element.Invoice_Minimum_Order_Amount_Textbox.sendKeys(InvoiceGuaranteeMinamountStringData[i]);
				element.Invoice_Payment_Save_Changes.click();
				driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
				// Get the error message and verify
				String validation_error_message = element.Payment_Page_Error.getText();
				if (validation_error_message.equals("The amount is invalid")) {
					System.out.println("TC PASSED: Guarantee Minimum order amount field not accepts an invalid data: "
							+ InvoiceGuaranteeMinamountStringData[i]);
					test.log(Status.PASS,
							"TC PASSED: Guarantee Minimum order amount field not accepts an invalid data: "
									+ InvoiceGuaranteeMinamountStringData[i]);
				} else {
					System.out.println("TC FAILED: Guarantee Minimum order amount field accepts an invalid data: "
							+ InvoiceGuaranteeMinamountStringData[i]);
					test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field accepts an invalid data: "
							+ InvoiceGuaranteeMinamountStringData[i]);
				}
				// Close browser
				Utility.closeBrowser();
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'InvoiceGuaranteeMinamountString' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'InvoiceGuaranteeMinamountString' method");
		}
	}

	@Test
	public void InvoiceGuaranteeMinamountLessThanLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Invoice_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Invoice with less than limit
			System.out.println("Validate Minimum order amount for Guarantee Invoice with less than limit");
			test = extend.createTest(
					"Validation: Minimum order amount for Guarantee Invoice with less than limit( less than 9,99 Euro)");
			try {
				if (element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			element.Invoice_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[0]);
			element.Invoice_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			// Get the error message and verify
			String validation_error_message = element.Payment_Page_Error.getText();
			if (validation_error_message.equals("The minimum amount should be at least 9,99 EUR")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
				test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field accepts the invalid data: "
						+ GuaranteeMinamountLimitData[0]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'InvoiceGuaranteeMinamountLessThanLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'InvoiceGuaranteeMinamountLessThanLimit' method");
		}
	}

	@Test
	public void InvoiceGuaranteeMinamountEqualLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Invoice_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Invoice with equal limit
			System.out.println("Validate Minimum order amount for Guarantee Invoice with equal limit");
			test = extend
					.createTest("Validation: Minimum order amount for Guarantee Invoice with equal limit( 9,99 Euro)");
			try {
				if (element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			element.Invoice_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[1]);

			element.Invoice_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			// Verify the saved text
			if (driver.getPageSource().contains("Your settings have been saved.")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[1]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[1]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[1]);
				test.log(Status.ERROR, "TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[1]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'InvoiceGuaranteeMinamountEqualLimit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'InvoiceGuaranteeMinamountEqualLimit' method");
		}
	}

	@Test
	public void InvoiceGuaranteeMinamountGreaterThanLimit() throws Exception {
		try {
			String GuaranteeMinamountLimitData[] = Data.ExcelReader_GuaranteeMinamountLimitValidation();
			Utility.initConfiguration();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Login to the shop back-end
			Utility.wooCommerceBackEndLogin();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			// Go to global configuration tab
			element.WooCommerce.click();
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			element.Invoice_Payment_Manage.click();
			// Validate Minimum order amount for Guarantee Invoice with greater than limit
			System.out.println("Validate Minimum order amount for Guarantee Invoice with greater than limit");
			test = extend.createTest(
					"Validation: Minimum order amount for Guarantee Invoice with greater than limit( greater than 9,99 Euro)");
			try {
				if (element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected() == false) {
					element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				}
			} catch (Exception e) {
				System.out.println("ERROR: Enable payment guarantee checkBox is not availble");
				test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			element.Invoice_Minimum_Order_Amount_Textbox.sendKeys(GuaranteeMinamountLimitData[2]);

			element.Invoice_Payment_Save_Changes.click();
			driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);

			// Verify the saved text
			if (driver.getPageSource().contains("Your settings have been saved.")) {
				System.out.println("TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[2]);
				test.log(Status.PASS, "TC PASSED: Guarantee Minimum order amount field accepts the valid data: "
						+ GuaranteeMinamountLimitData[2]);
			} else {
				System.out.println("TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[2]);
				test.log(Status.FAIL, "TC FAILED: Guarantee Minimum order amount field not accepts the invalid data: "
						+ GuaranteeMinamountLimitData[2]);
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'InvoiceGuaranteeMinamountGreaterThanLimit' method");
			test.log(Status.ERROR, "ERROR: Enable payment guarantee checkBox is not availble");
		}
	}
}