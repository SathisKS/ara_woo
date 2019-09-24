/* **********************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Instant Bank Transfer
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ************************************************************************************/

package com.nn.VendorScript;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class InstantBankTransfer extends TestInputData {
	TestInputData Data = new TestInputData();

	// Method used to execute 'REFUND_BY_BANK_TRANSFER_EU'
	@Test
	public void refundByBankTransferEuInstantBank() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Instant Bank Transfer 'REFUND_BY_BANK_TRANSFER_EU'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			Thread.sleep(2000);
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Instant_Bank_Transfer_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Payment_Save_Changes.click();
				Thread.sleep(3000);
			}
			// Navigate to shop front end and place the order
			driver.navigate().to(Constant.shopfrontendurl);
			Utility.wooCommerceCheckOutProcess();
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Instant_Bank_Transfer_Label.isDisplayed()) {
				if (element.Instant_Bank_Transfer_Radio_Button.isDisplayed() == true) {
					element.Instant_Bank_Transfer_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(10000);
				WebDriverWait waitforcheckout = new WebDriverWait(driver, 20);
				waitforcheckout.until(ExpectedConditions.visibilityOf(element.Instant_Bank_Transfer_BankName));
				element.Instant_Bank_Transfer_BankName.sendKeys(UserData.get("InstantBankName_BIC"));
				element.Instant_Bank_Transfer_BankName.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Account_Number.sendKeys(UserData.get("InstantAccountNumber"));
				Thread.sleep(1000);
				element.Instant_Bank_Transfer_PIN.sendKeys(UserData.get("InstantPINCode"));
				element.Instant_Bank_Transfer_PIN.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Select_Account.click();
				Thread.sleep(2000);
				element.Instant_Bank_Transfer_Account_Select_Next_Button.click();
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_TAN.sendKeys(UserData.get("InstantTAN"));
				Thread.sleep(1000);
				element.Instant_Bank_Transfer_TAN.sendKeys(Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Instant Bank Transfer");
					test.log(Status.INFO, "Order placed successfully using Instant Bank Transfer");
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
									"TC PASSED: 'REFUND_BY_BANK_TRANSFER_EU' execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: 'REFUND_BY_BANK_TRANSFER_EU' execution message and back end order note message text was matched successfully");
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
					System.out.println("ERROR: Order was not placed successfully using Instant Bank Transfer");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Instant Bank Transfer");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'refundByBankTransferEuInstantBank' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'refundByBankTransferEuInstantBank' method");
		}
	}

	// Method used to execute 'REVERSAL'
	@Test
	public void reversalInstantBank() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Instant Bank Transfer 'REVERSAL'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			Thread.sleep(2000);
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Instant_Bank_Transfer_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Payment_Save_Changes.click();
				Thread.sleep(3000);
			}
			// Navigate to shop front end and place the order
			driver.navigate().to(Constant.shopfrontendurl);
			Utility.wooCommerceCheckOutProcess();
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Instant_Bank_Transfer_Label.isDisplayed()) {
				if (element.Instant_Bank_Transfer_Radio_Button.isDisplayed() == true) {
					element.Instant_Bank_Transfer_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(10000);
				WebDriverWait waitforcheckout = new WebDriverWait(driver, 20);
				waitforcheckout.until(ExpectedConditions.visibilityOf(element.Instant_Bank_Transfer_BankName));
				element.Instant_Bank_Transfer_BankName.sendKeys(UserData.get("InstantBankName_BIC"));
				element.Instant_Bank_Transfer_BankName.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Account_Number.sendKeys(UserData.get("InstantAccountNumber"));
				Thread.sleep(1000);
				element.Instant_Bank_Transfer_PIN.sendKeys(UserData.get("InstantPINCode"));
				element.Instant_Bank_Transfer_PIN.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_Select_Account.click();
				Thread.sleep(2000);
				element.Instant_Bank_Transfer_Account_Select_Next_Button.click();
				Thread.sleep(3000);
				element.Instant_Bank_Transfer_TAN.sendKeys(UserData.get("InstantTAN"));
				Thread.sleep(1000);
				element.Instant_Bank_Transfer_TAN.sendKeys(Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Instant Bank Transfer");
					test.log(Status.INFO, "Order placed successfully using Instant Bank Transfer");
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
					selectpaymenttype.selectByVisibleText("REVERSAL");
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
									"TC PASSED: 'REVERSAL' execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: 'REVERSAL' execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: 'REVERSAL' execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: 'REVERSAL' execution and back end order note message text was not matched successfully");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("ERROR: Order was not placed successfully using Instant Bank Transfer");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Instant Bank Transfer");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'reversalInstantBank' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'reversalInstantBank' method");
		}
	}

	// Method used to execute 'ONLINE_TRANSFER_CREDIT'
	@Test
	public void onlineTransferCreditInstantBank() throws Exception {
		// Launch the browser and load the default URL
		Utility.initConfiguration();
		Thread.sleep(3000);
		// Login to back end and check payment method is enabled or disabled
		Utility.wooCommerceBackEndLogin();
		Thread.sleep(3000);
		ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
		// Title for HTML report
		test = extend.createTest("Vendor script execution for Instant Bank Transfer 'ONLINE_TRANSFER_CREDIT'");
		// Steps
		Actions actions = new Actions(driver);
		actions.moveToElement(element.WooCommerce).perform();
		Thread.sleep(3000);
		element.WooCommerce_Settings.click();
		Thread.sleep(2000);
		element.Payment_Tab.click();
		Thread.sleep(2000);
		element.Instant_Bank_Transfer_Payment_Display.click();
		// Checking payment method enabled or disabled
		if (!element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.isSelected()) {
			element.Instant_Bank_Transfer_Enable_Payment_Method_Checkbox.click();
			Thread.sleep(3000);
			element.Instant_Bank_Transfer_Payment_Save_Changes.click();
			Thread.sleep(3000);
		}
		// Navigate to shop front end and place the order
		driver.navigate().to(Constant.shopfrontendurl);
		Utility.wooCommerceCheckOutProcess();
		try {
			// Check whether payment method is displayed in checkout page
			if (element.Instant_Bank_Transfer_Label.isDisplayed()) {
				if (element.Instant_Bank_Transfer_Radio_Button.isDisplayed() == true) {
					element.Instant_Bank_Transfer_Radio_Button.click();
				}
				Thread.sleep(4000);
				element.Place_Order.click();
				Thread.sleep(10000);
				WebDriverWait waitforcheckout = new WebDriverWait(driver, 20);
				waitforcheckout.until(ExpectedConditions.visibilityOf(element.Instant_Bank_Transfer_BankName));
				element.Sofort_Cancel_Link.click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
				element.Sofort_Cancel_Payment_Button.click();
				String errormessage = element.Checkout_Page_Error_Message.getText();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				if (errormessage.equals("Customer has abandoned the transaction")) {
					System.out.println("Order has been cancelled and the response has been returned to the shop");
					test.log(Status.INFO, "Order has been cancelled and the response has been returned to the shop");
					Thread.sleep(1000);
					element.MyAccount_Menu.click();
					element.MyAccount_Orders.click();
					String ordernumber = element.Frontend_Order_Number.getText().replaceAll("[^0-9]", "");
					element.Frontend_Order_Number.click();
					String totalOrderAmount = element.MyAccount_OrderDetails_TotalAmount.getText().replaceAll("[^0-9]",
							"");
					String TID = element.MyAccount_OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
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
					selectpaymenttype.selectByVisibleText("ONLINE_TRANSFER_CREDIT");
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
					element.Order_Number.clear();
					element.Order_Number.sendKeys(ordernumber);
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();
					if (callback_message.contains(
							"Please verify received amount and TID details, and update the order status accordingly")) {
						// Go to shop front end and check the order status update
						driver.navigate().to(Constant.shopbackendurl);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(3000);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
						// Verify the callback execution message is updated in the order note
						if (BEOrderNotes_CallBackMessage.contains(
								"Please verify received amount and TID details, and update the order status accordingly")) {
							System.out.println(
									"TC PASSED: ONLINE_TRANSFER_CREDIT executed and the message has been updated successfully in shop back end order note");
							test.log(Status.PASS,
									"TC PASSED: ONLINE_TRANSFER_CREDIT executed and the message has been updated successfully in shop back end order note");
						} else {
							System.out.println(
									"TC FAILED: ONLINE_TRANSFER_CREDIT executed and the message has not been updated in shop back end order note");
							test.log(Status.FAIL,
									"TC PASSED: ONLINE_TRANSFER_CREDIT executed and the message has not been updated in shop back end order note");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("ERROR: Order was not cancelled or the response was not returned to the shop");
					test.log(Status.ERROR,
							"ERROR: Order was not cancelled or the response was not returned to the shop");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'onlineTransferCreditInstantBank' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'onlineTransferCreditInstantBank' method");
		}
	}
}