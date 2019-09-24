/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Giropay
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

package com.nn.VendorScript;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Giropay extends TestInputData {
	TestInputData Data = new TestInputData();

	// Method used to execute 'REFUND_BY_BANK_TRANSFER_EU'
	@Test
	public void refundByBankTransferEuGiropay() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Giropay 'REFUND_BY_BANK_TRANSFER_EU'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			Thread.sleep(2000);
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Giropay_Payment_Display.click();			
			// Checking payment method enabled or disabled
			if (!element.Giropay_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Giropay_Enable_Payment_Method_Checkbox.click();				
			}
			Thread.sleep(3000);
			element.Giropay_Payment_Save_Changes.click();
			Thread.sleep(3000);
			// Navigate to shop front end and place the order
			driver.navigate().to(Constant.shopfrontendurl);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(1000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			if (element.Giropay_Radio_Button.isDisplayed()) {
				if (element.Giropay_Radio_Button.isDisplayed() == true) {
					element.Giropay_Radio_Button.click();
				}
				Thread.sleep(5000);
				element.Place_Order.click();
				Thread.sleep(10000);
				element.Giropay_Enter_The_Bic.sendKeys(UserData.get("GIROBankNameorBIC"));
				Thread.sleep(4000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.DOWN);
				Thread.sleep(2000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.DOWN, Keys.DOWN);
				Thread.sleep(4000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				element.Giropay_Username.sendKeys(UserData.get("GIROAccountNumber"));
				Thread.sleep(1000);
				element.Giropay_PINCode.sendKeys(UserData.get("GIROPINCode"));
				Thread.sleep(1000);
				element.Giropay_PINCode.sendKeys(Keys.ENTER);
				Thread.sleep(4000);
				element.Giropay_weiter.click();
				Thread.sleep(2000);
				element.Giropay_TAN.sendKeys(UserData.get("GIROTAN"));
				Thread.sleep(2000);
				element.Giropay_PayNow.click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();				
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("TC PASSED: Order placed successfully using Giropay");
					test.log(Status.PASS, "TC PASSED: Order placed successfully using Giropay");
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");					
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					Thread.sleep(2000);
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
					System.out.println("ERROR: Order was not placed successfully using Giropay");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Giropay");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'refundByBankTransferEuGiropay' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'refundByBankTransferEuGiropay' method");
		}
	}

	// Method used to execute 'ONLINE_TRANSFER_CREDIT'
	@Test
	public void onlineTransferCreditGiropay() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Giropay 'ONLINE_TRANSFER_CREDIT'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			Thread.sleep(2000);
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Giropay_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Giropay_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Giropay_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
				element.Giropay_Payment_Save_Changes.click();
				Thread.sleep(3000);
			}
			// Navigate to shop front end and place the order
			driver.navigate().to(Constant.shopfrontendurl);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(1000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			if (element.Giropay_Radio_Button.isDisplayed()) {
				if (element.Giropay_Radio_Button.isDisplayed() == true) {
					element.Giropay_Radio_Button.click();
				}
				Thread.sleep(6000);
				element.Place_Order.click();
				Thread.sleep(10000);
				element.Giropay_Enter_The_Bic.sendKeys(UserData.get("GIROBankNameorBIC"));
				Thread.sleep(4000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.DOWN);
				Thread.sleep(2000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.DOWN, Keys.DOWN);
				Thread.sleep(4000);
				element.Giropay_Enter_The_Bic.sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				element.Giropay_Payment_Abort_Button.click();
				Thread.sleep(4000);
				element.Giropay_Abort_Confiorm_Button.click();
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String errormessage = element.Checkout_Page_Error_Message.getText();
				if (errormessage.equals("Bank offline")) {
					System.out.println("TC PASSED: Order has been cancelled and returned to shop");
					test.log(Status.PASS, "TC PASSED: Order has been cancelled and returned to shop");
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
					}  else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out
							.println("ERROR: TC PASSED: Order hasn't been cancelled or response not returned to shop");
					test.log(Status.ERROR, "ERROR:  Order hasn't been cancelled or response not returned to shop");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'onlineTransferCreditGiropay' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'onlineTransferCreditGiropay' method");
		}
	}
}