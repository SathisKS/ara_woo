/* ******************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Direct Debit SEPA
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ********************************************************************************/

package com.nn.VendorScript;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class DirectDebitSEPA extends TestInputData {
	TestInputData Data = new TestInputData();

	// Method used to execute 'RETURN_DEBIT_SEPA'
	@Test
	public void returnDebitSEPA() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for SEPA 'RETURN_DEBIT_SEPA'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			element.Sepa_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Sepa_Label.isDisplayed()) {
				if (element.Sepa_Radio_button.isDisplayed() == true) {
					element.Sepa_Radio_button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Direct Debit SEPA");
					test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("RETURN_DEBIT_SEPA");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message
							.contains("Novalnet callback received. Chargeback executed successfully for the TID:")) {
						Thread.sleep(2000);
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Backend order note: " + BEOrderNotesMessage);
						if (callback_message_updated.equals(BEOrderNotesMessage)) {
							System.out.println(
									"TC PASSED: RETURN_DEBIT_SEPA execution message and back end order note message text was matched successfully.");
							test.log(Status.PASS,
									"TC PASSED: RETURN_DEBIT_SEPA execution message and back end order note message text was matched successfully.");
						} else {
							System.out.println(
									"TC FAILED: RETURN_DEBIT_SEPA execution message and back end order note message text was not matched.");
							test.log(Status.FAIL,
									"TC FAILED: RETURN_DEBIT_SEPA execution messageA and back end order note message text was not matched.");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'returnDebitSEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'returnDebitSEPA' method");
		}
	}

	// Method used to execute 'CREDIT_ENTRY_SEPA'
	@Test
	public void creditEntrySEPA() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'CREDIT_ENTRY_SEPA'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			element.Sepa_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the datafrom excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Sepa_Label.isDisplayed()) {
				if (element.Sepa_Radio_button.isDisplayed() == true) {
					element.Sepa_Radio_button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Direct Debit SEPA");
					test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
					Thread.sleep(3000); // Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("CREDIT_ENTRY_SEPA");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();					
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
						Thread.sleep(2000);
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Back end order note: " + BEOrderNotesMessage);
						if (callback_message_updated.equals(BEOrderNotesMessage)) {
							System.out.println(
									"TC PASSED: CREDIT_ENTRY_SEPA execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: CREDIT_ENTRY_SEPA execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: CREDIT_ENTRY_SEPA execution message and back end order note message text was not matched");
							test.log(Status.FAIL,
									"TC FAILED: CREDIT_ENTRY_SEPA execution message and back end order note message text was not matched");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditEntrySEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditEntrySEPA' method");
		}
	}

	// Method used to execute 'REFUND_BY_BANK_TRANSFER_EU'
	@Test
	public void refundByBankTransferEuSEPA() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for SEPA 'REFUND_BY_BANK_TRANSFER_EU'");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			element.Sepa_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Sepa_Label.isDisplayed()) {
				if (element.Sepa_Radio_button.isDisplayed() == true) {
					element.Sepa_Radio_button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Direct Debit SEPA");
					test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("REFUND_BY_BANK_TRANSFER_EU");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();					
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message.contains(
							"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {
						// Go to shop front end and check the order status update
						driver.navigate().to(Constant.shopbackendurl);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(3000);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
						// Verify the callback execution message is updated in the order note
						if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
							System.out.println(
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution and back end order note message text was not matched successfully");
						}

					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");

					}
				} else {
					System.out.println("ERROR: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'refundByBankTransferEuSEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'refundByBankTransferEuSEPA' method");
		}
	}

	// Method used to execute 'TRANSACTION_CANCELLATION'
	@Test
	public void transactionCancelSEPA() throws Exception {
		try {
			Thread.sleep(3000);
			Utility.initConfiguration();
			Thread.sleep(3000);
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			test = extend.createTest("Vendor script execution for SEPA 'TRANSACTION_CANCELLATION");
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			// Go to Novalnet Global Configuration Tab
			element.Novalnet_Global_Config_Tab.click();
			Thread.sleep(2000);
			String BECancellationOrderStatus = element.Cancellation_Order_Status.getText();
			Thread.sleep(4000);
			// Go to Payments Tab
			element.Payment_Tab.click();
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Sepa_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Sepa_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Checking SEPA payment method displayed or not
			if (element.Sepa_Label.isDisplayed()) {
				if (element.Sepa_Radio_button.isDisplayed() == true) {
					element.Sepa_Radio_button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Direct Debit SEPA");
					test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Storing the TID from order success page front end
					Thread.sleep(2000);
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					// Navigate to card portal
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					// Checking the order status code 75 or 99 and payment name
					if (StoreTheStatusCode.equals("99")
							&& (element.Cardportal_Payment_Name.getText().equals("Lastschrift (SEPA)"))) {
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("TRANSACTION_CANCELLATION");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("103");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("103");
						element.Tid.clear();
						element.Tid.sendKeys(TID);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("callback execution message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been canceled")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							String BEOrdersStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();							
							System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
							if (callback_message_updated.equals(BEOrderNotes_CallBackMessage)) {
								System.out.println(
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note call back message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note call back message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: TRANSACTION_CANCELLATION execution message and back end order note call back message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: TRANSACTION_CANCELLATION execution message and back end order note call back message text was not matched");
							}
							// Verify order status from back end payment order completion status to back end order
							System.out.println("Cancellation order status: " + BECancellationOrderStatus);
							System.out.println("Back end order status: " + BEOrdersStatus);
							if (BECancellationOrderStatus.equals(BEOrdersStatus)) {
								System.out.println("TC PASSED: Cancellation order status is updated successfully in shop back end");
								test.log(Status.PASS, "TC PASSED: Cancellation order status is updated successfully in shop back end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop back end");
							}
							// Navigate to shop front end URL
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrdersStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrdersStatus);
							// Verify status from back end Payment Order Completion Status to front end oder status
							if (BECancellationOrderStatus.equals(FEOrdersStatus)) {
								System.out.println("TC PASSED: Cancellation order status is updated successfully in shop front end");
								test.log(Status.PASS, "TC PASSED: Cancellation order status is updated successfully in shop front end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println(
								"TC FAILED: Transaction is not in the pending status or order not placed in Direct Debit SEPA");
						test.log(Status.FAIL,
								"TC FAILED: Transaction is not in the pending status or order not placed in Direct Debit SEPA");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'transactionCancelSEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'transactionCancelSEPA' method");
		}
	}

	// Method used to execute 'DIRECT_DEBIT_SEPA' onhold to confirm
	@Test
	public void onholdToConfirmSEPA() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'DIRECT_DEBIT_SEPA' onhold to confirm");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Read the order completion status
			String OrderCompletionStatus = element.Sepa_Order_Completion_Status_Selectbox.getText();
			Thread.sleep(3000);			
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Sepa_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Sepa_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Sepa_Label.isDisplayed() == true) {
				if (element.Sepa_Radio_button.isDisplayed()) {
					element.Sepa_Radio_button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Direct Debit SEPA");
					test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the Tid is 99
					if (tid_status == 99) {
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("DIRECT_DEBIT_SEPA");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys(TID);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());						
						if (callback_message
								.contains("Novalnet callback received. The transaction has been confirmed")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							// Verify order completion status is updated in shop back end after the execution
							System.out.println("Order completion status: " + OrderCompletionStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (OrderCompletionStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Direct Debit SEPA order completion status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED:  Direct Debit SEPA order completion status is updated successfully in shop back end");
							} else {
								System.out.println(
										"TC FAILED:  Direct Debit SEPA order completion status is not updated successfully in shop back end");
								test.log(Status.FAIL,
										"TC FAILED:  Direct Debit SEPA order completion status is not updated successfully in shop back end");
							}
							// Verify order completion status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (OrderCompletionStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Direct Debit SEPA order completion status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Direct Debit SEPA order completion status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: Direct Debit SEPA order completion status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Direct Debit SEPA order completion status is not updated in shop front end");
							}
							System.out.println("callback execution message: " + callback_message_updated);
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: DIRECT_DEBIT_SEPA execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: DIRECT_DEBIT_SEPA execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: DIRECT_DEBIT_SEPA execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: DIRECT_DEBIT_SEPA execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Transaction is already confirmed or not in pending status");
						test.log(Status.FAIL, "TC FAILED: Transaction is already confirmed or not in pending status");
					}

				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'onholdToConfirmSEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'onholdToConfirmSEPA' method");
		}
	}
	// Method used to execute 'DEBT_COLLECTION_SEPA'
		@Test
		public void debitCollectionSEPA() throws Exception {
			try {
				// Launch the browser and load the default URL
				Utility.initConfiguration();
				Thread.sleep(3000);
				// Login to back end and check payment method is enabled or disabled
				Utility.wooCommerceBackEndLogin();
				Thread.sleep(3000);
				ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
				// Title for HTML report
				test = extend.createTest("Vendor script execution for SEPA 'DEBT_COLLECTION_SEPA'");
				// Steps
				Actions actions = new Actions(driver);
				actions.moveToElement(element.WooCommerce).perform();
				Thread.sleep(3000);
				element.WooCommerce_Settings.click();
				element.Payment_Tab.click();
				Thread.sleep(2000);
				element.Sepa_Payment_Display.click();
				// Checking payment method enabled or disabled
				if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Sepa_Enable_Payment_Method_Checkbox.click();
					Thread.sleep(3000);
				}
				// Checking Guarantee payment enabled or disabled
				if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
					element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
					Thread.sleep(3000);
				}
				element.Sepa_Payment_Save_Changes.click();
				Thread.sleep(3000);
				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(4000);
				// Read the data from excel sheet
				Map<String, String> UserData = new HashMap<String, String>();
				UserData = Data.ExcelReader_PaymentMethods();
				// Check whether payment method is displayed in checkout page
				if (element.Sepa_Label.isDisplayed()) {
					if (element.Sepa_Radio_button.isDisplayed() == true) {
						element.Sepa_Radio_button.click();
					}
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
					element.Place_Order.click();
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					// After order placed successfully verify Thank you message displayed
					String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
					if (thankyoumessage.equals("Thank you. Your order has been received.")) {
						System.out.println("Order placed successfully using Direct Debit SEPA");
						test.log(Status.INFO, "Order placed successfully using Direct Debit SEPA");
						Thread.sleep(3000);
						// Get the amount from order success page front end
						String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
						// Get the TID from order success page front end
						String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("DEBT_COLLECTION_SEPA");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Tid_Payment.sendKeys(TID);
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys("13245678945612345");
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: DEBT_COLLECTION_SEPA execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: DEBT_COLLECTION_SEPA execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: DEBT_COLLECTION_SEPA execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: DEBT_COLLECTION_SEPA execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}					
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Direct Debit SEPA");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Direct Debit SEPA");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'debitCollectionSEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'debitCollectionSEPA' method");
		}
	}
}